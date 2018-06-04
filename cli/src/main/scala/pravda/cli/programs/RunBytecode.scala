package pravda.cli.programs

import cats._
import cats.data.EitherT
import cats.implicits._
import pravda.cli.Config
import pravda.cli.languages.{IoLanguage, VmLanguage}
import pravda.common.bytes
import pravda.vm.state.VmMemory

import scala.language.higherKinds

class RunBytecode[F[_]: Monad](io: IoLanguage[F], vm: VmLanguage[F]) {

  def memoryToJson(memory: VmMemory): String = {
    val stack = memory.stack.map(x => '"' + bytes.byteString2hex(x) + '"')
    val heap = memory.heap.map(x => '"' + bytes.byteString2hex(x) + '"')
    s"""{"stack":[${stack.mkString(",")}],"heap":[${heap.mkString(",")}]}"""
  }

  def apply(config: Config.RunBytecode): F[Unit] = {
    val errorOrMemory: EitherT[F, String, VmMemory] =
      for {
        storagePath <- EitherT.liftF(config.storage.fold(io.createTmpDir())(path => Monad[F].pure(path)))
        executor = bytes.hex2byteString(config.executor)
        program <- useOption(config.input)(io.readFromStdin(),
                                           path => io.readFromFile(path).map(_.toRight(s"`$path` is not found.\n")))
        memory <- EitherT(vm.run(program, executor, storagePath, Long.MaxValue)) // TODO: get wattLimit from command line))
      } yield {
        memory
      }
    errorOrMemory.value flatMap {
      case Left(error) => io.writeStringToStderrAndExit(error)
      case Right(memory) =>
        val json = memoryToJson(memory)
        io.writeStringToStdout(json)
    }
  }
}
