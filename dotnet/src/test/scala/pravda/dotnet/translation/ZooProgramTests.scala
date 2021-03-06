package pravda.dotnet
package translation

import pravda.vm.asm.PravdaAssembler
import utest._

object ZooProgramTests extends TestSuite {

  val tests = Tests {
    'zooProgramTranslation - {
      val Right((_, cilData, methods, signatures)) = parsePeFile("zoo_program.exe")

      assertWithAsmDiff(
        Translator.translateAsm(methods, cilData, signatures).right.get,
        PravdaAssembler
          .parse("""
              |meta translator_mark "jump to methods"
              |meta method {
              |"name":"NewZoo","returnTpe":int8(3)
              |}
              |meta method {
              |"name":"TransferZoo",int32(1):int8(3),int32(0):int8(14),"returnTpe":int8(0)
              |}
              |meta method {
              |"name":"NewPet",int32(0):int8(3),"returnTpe":int8(11)
              |}
              |meta method {
              |"name":"TransferPet",int32(1):int8(3),int32(2):int8(11),int32(0):int8(14),"returnTpe":int8(0)
              |}
              |meta method {
              |"name":"BreedPets",int32(1):int8(11),int32(0):int8(11),"returnTpe":int8(11)
              |}
              |dup
              |push "NewZoo"
              |eq
              |jumpi @method_NewZoo
              |dup
              |push "TransferZoo"
              |eq
              |jumpi @method_TransferZoo
              |dup
              |push "NewPet"
              |eq
              |jumpi @method_NewPet
              |dup
              |push "TransferPet"
              |eq
              |jumpi @method_TransferPet
              |dup
              |push "BreedPets"
              |eq
              |jumpi @method_BreedPets
              |push "Wrong method name"
              |throw
              |meta translator_mark "NewZoo method"
              |@method_NewZoo:
              |meta translator_mark "NewZoo local vars definition"
              |push null
              |meta translator_mark "NewZoo method body"
              |push x5A6F6F546F4F776E6572
              |push "ZooCnt"
              |sget
              |from
              |push int32(2)
              |dupn
              |push int8(14)
              |cast
              |push int32(4)
              |dupn
              |concat
              |swap
              |sput
              |pop
              |pop
              |push "ZooCnt"
              |sget
              |push int32(1)
              |add
              |push "ZooCnt"
              |swap
              |sput
              |push "ZooCnt"
              |sget
              |push int32(1)
              |push int32(-1)
              |mul
              |add
              |push int32(2)
              |swapn
              |pop
              |push int32(1)
              |dupn
              |meta translator_mark "NewZoo local vars clearing"
              |swap
              |pop
              |swap
              |pop
              |meta translator_mark "end of NewZoo method"
              |jump @stop
              |meta translator_mark "TransferZoo method"
              |@method_TransferZoo:
              |meta translator_mark "TransferZoo local vars definition"
              |push null
              |meta translator_mark "TransferZoo method body"
              |push x5A6F6F546F4F776E6572
              |push int32(4)
              |dupn
              |push x
              |call @storage_get_default
              |from
              |eq
              |push int8(3)
              |cast
              |push int32(2)
              |swapn
              |pop
              |push int32(1)
              |dupn
              |push int8(9)
              |cast
              |not
              |push int8(3)
              |cast
              |push int32(1)
              |eq
              |jumpi @TransferZoo_br45
              |push x5A6F6F546F4F776E6572
              |push int32(4)
              |dupn
              |push int32(6)
              |dupn
              |push int32(2)
              |dupn
              |push int8(14)
              |cast
              |push int32(4)
              |dupn
              |concat
              |swap
              |sput
              |pop
              |pop
              |@TransferZoo_br45:
              |meta translator_mark "TransferZoo local vars clearing"
              |pop
              |pop
              |pop
              |pop
              |meta translator_mark "end of TransferZoo method"
              |jump @stop
              |meta translator_mark "NewPet method"
              |@method_NewPet:
              |meta translator_mark "NewPet local vars definition"
              |push null
              |push null
              |push null
              |meta translator_mark "NewPet method body"
              |push x5A6F6F546F4F776E6572
              |push int32(6)
              |dupn
              |push x
              |call @storage_get_default
              |from
              |eq
              |push int8(3)
              |cast
              |push int32(4)
              |swapn
              |pop
              |push int32(3)
              |dupn
              |push int8(9)
              |cast
              |not
              |push int8(3)
              |cast
              |push int32(1)
              |eq
              |jumpi @NewPet_br108
              |push "pet"
              |push "PetId"
              |sget
              |push int8(11)
              |cast
              |swap
              |concat
              |push int32(3)
              |swapn
              |pop
              |push x506574546F4F776E6572
              |push int32(3)
              |dupn
              |from
              |push int32(2)
              |dupn
              |push int8(14)
              |cast
              |push int32(4)
              |dupn
              |concat
              |swap
              |sput
              |pop
              |pop
              |push x5065745369676E6174757265
              |push int32(3)
              |dupn
              |push int32(4)
              |dupn
              |call @func_GenerateSignature
              |push int32(2)
              |dupn
              |push int8(14)
              |cast
              |push int32(4)
              |dupn
              |concat
              |swap
              |sput
              |pop
              |pop
              |push "PetId"
              |sget
              |push int32(1)
              |add
              |push "PetId"
              |swap
              |sput
              |push int32(2)
              |dupn
              |push int32(2)
              |swapn
              |pop
              |jump @NewPet_br116
              |@NewPet_br108:
              |push ""
              |push int32(2)
              |swapn
              |pop
              |@NewPet_br116:
              |push int32(1)
              |dupn
              |meta translator_mark "NewPet local vars clearing"
              |swap
              |pop
              |swap
              |pop
              |swap
              |pop
              |swap
              |pop
              |swap
              |pop
              |meta translator_mark "end of NewPet method"
              |jump @stop
              |meta translator_mark "TransferPet method"
              |@method_TransferPet:
              |meta translator_mark "TransferPet local vars definition"
              |push null
              |meta translator_mark "TransferPet method body"
              |push x506574546F4F776E6572
              |push int32(4)
              |dupn
              |push x
              |call @storage_get_default
              |from
              |eq
              |push int8(3)
              |cast
              |push int8(9)
              |cast
              |not
              |push int8(3)
              |cast
              |push int32(1)
              |eq
              |jumpi @TransferPet_br47
              |push x5A6F6F546F4F776E6572
              |push int32(5)
              |dupn
              |push x
              |call @storage_get_default
              |push int32(6)
              |dupn
              |eq
              |push int8(3)
              |cast
              |jump @TransferPet_br48
              |@TransferPet_br47:
              |push int32(0)
              |@TransferPet_br48:
              |push int32(2)
              |swapn
              |pop
              |push int32(1)
              |dupn
              |push int8(9)
              |cast
              |not
              |push int8(3)
              |cast
              |push int32(1)
              |eq
              |jumpi @TransferPet_br82
              |push x506574546F4F776E6572
              |push int32(4)
              |dupn
              |push int32(7)
              |dupn
              |push int32(2)
              |dupn
              |push int8(14)
              |cast
              |push int32(4)
              |dupn
              |concat
              |swap
              |sput
              |pop
              |pop
              |push x506574546F5A6F6F
              |push int32(4)
              |dupn
              |push int32(6)
              |dupn
              |push int32(2)
              |dupn
              |push int8(14)
              |cast
              |push int32(4)
              |dupn
              |concat
              |swap
              |sput
              |pop
              |pop
              |@TransferPet_br82:
              |meta translator_mark "TransferPet local vars clearing"
              |pop
              |pop
              |pop
              |pop
              |pop
              |meta translator_mark "end of TransferPet method"
              |jump @stop
              |meta translator_mark "BreedPets method"
              |@method_BreedPets:
              |meta translator_mark "BreedPets local vars definition"
              |push null
              |push null
              |push null
              |meta translator_mark "BreedPets method body"
              |push x506574546F4F776E6572
              |push int32(7)
              |dupn
              |push x
              |call @storage_get_default
              |from
              |eq
              |push int8(3)
              |cast
              |push int8(9)
              |cast
              |not
              |push int8(3)
              |cast
              |push int32(1)
              |eq
              |jumpi @BreedPets_br79
              |push x506574546F4F776E6572
              |push int32(6)
              |dupn
              |push x
              |call @storage_get_default
              |from
              |eq
              |push int8(3)
              |cast
              |push int8(9)
              |cast
              |not
              |push int8(3)
              |cast
              |push int32(1)
              |eq
              |jumpi @BreedPets_br79
              |push x506574546F5A6F6F
              |push int32(7)
              |dupn
              |push int32(-1)
              |call @storage_get_default
              |push x506574546F5A6F6F
              |push int32(7)
              |dupn
              |push int32(-1)
              |call @storage_get_default
              |eq
              |push int8(3)
              |cast
              |jump @BreedPets_br80
              |@BreedPets_br79:
              |push int32(0)
              |@BreedPets_br80:
              |push int32(4)
              |swapn
              |pop
              |push int32(3)
              |dupn
              |push int8(9)
              |cast
              |not
              |push int8(3)
              |cast
              |push int32(1)
              |eq
              |jumpi @BreedPets_br157
              |push int32(6)
              |dupn
              |push int32(6)
              |dupn
              |swap
              |concat
              |push int32(3)
              |swapn
              |pop
              |push x506574546F4F776E6572
              |push int32(3)
              |dupn
              |from
              |push int32(2)
              |dupn
              |push int8(14)
              |cast
              |push int32(4)
              |dupn
              |concat
              |swap
              |sput
              |pop
              |pop
              |push x5065745369676E6174757265
              |push int32(3)
              |dupn
              |push x5065745369676E6174757265
              |push int32(9)
              |dupn
              |push int8(14)
              |cast
              |swap
              |concat
              |sget
              |push x5065745369676E6174757265
              |push int32(9)
              |dupn
              |push int8(14)
              |cast
              |swap
              |concat
              |sget
              |swap
              |concat
              |push int32(2)
              |dupn
              |push int8(14)
              |cast
              |push int32(4)
              |dupn
              |concat
              |swap
              |sput
              |pop
              |pop
              |push int32(2)
              |dupn
              |push int32(2)
              |swapn
              |pop
              |jump @BreedPets_br166
              |@BreedPets_br157:
              |push ""
              |push int32(2)
              |swapn
              |pop
              |@BreedPets_br166:
              |push int32(1)
              |dupn
              |meta translator_mark "BreedPets local vars clearing"
              |swap
              |pop
              |swap
              |pop
              |swap
              |pop
              |swap
              |pop
              |swap
              |pop
              |swap
              |pop
              |meta translator_mark "end of BreedPets method"
              |jump @stop
              |meta translator_mark "ctor method"
              |@method_ctor:
              |meta translator_mark "ctor local vars definition"
              |meta translator_mark "ctor method body"
              |push int32(1)
              |push "ZooCnt"
              |swap
              |sput
              |push int32(1)
              |push "PetId"
              |swap
              |sput
              |meta translator_mark "ctor local vars clearing"
              |meta translator_mark "end of ctor method"
              |ret
              |meta translator_mark "GenerateSignature func"
              |@func_GenerateSignature:
              |meta translator_mark "GenerateSignature local vars definition"
              |push null
              |push null
              |push null
              |push null
              |meta translator_mark "GenerateSignature func body"
              |push int32(10)
              |push int8(1)
              |new_array
              |push int32(5)
              |swapn
              |pop
              |push int32(0)
              |push int32(4)
              |swapn
              |pop
              |jump @GenerateSignature_br43
              |@GenerateSignature_br13:
              |push int32(4)
              |dupn
              |push int32(4)
              |dupn
              |push int32(7)
              |dupn
              |push int32(6)
              |dupn
              |push int32(9)
              |dupn
              |length
              |swap
              |mod
              |array_get
              |push int32(2)
              |swap
              |div
              |push int8(1)
              |cast
              |swap
              |array_mut
              |push int32(3)
              |dupn
              |push int32(1)
              |add
              |push int32(4)
              |swapn
              |pop
              |@GenerateSignature_br43:
              |push int32(3)
              |dupn
              |push int32(10)
              |swap
              |lt
              |push int8(3)
              |cast
              |push int32(3)
              |swapn
              |pop
              |push int32(2)
              |dupn
              |push int32(1)
              |eq
              |jumpi @GenerateSignature_br13
              |push int32(4)
              |dupn
              |call @array_to_bytes
              |push int32(2)
              |swapn
              |pop
              |push int32(1)
              |dupn
              |meta translator_mark "GenerateSignature local vars clearing"
              |swap
              |pop
              |swap
              |pop
              |swap
              |pop
              |swap
              |pop
              |swap
              |pop
              |meta translator_mark "end of GenerateSignature func"
              |ret
              |meta translator_mark "helper functions"
              |@array_to_bytes:
              |dup
              |length
              |push x
              |push int32(0)
              |@array_to_bytes_loop:
              |push int32(4)
              |dupn
              |push int32(2)
              |dupn
              |array_get
              |push int8(14)
              |cast
              |push int32(3)
              |dupn
              |concat
              |push int32(3)
              |swapn
              |pop
              |push int32(1)
              |add
              |dup
              |push int32(4)
              |dupn
              |gt
              |jumpi @array_to_bytes_loop
              |pop
              |swap
              |pop
              |swap
              |pop
              |ret
              |@storage_get_default:
              |push int32(2)
              |dupn
              |push int8(14)
              |cast
              |push int32(4)
              |dupn
              |concat
              |sexist
              |jumpi @get_default_if
              |swap
              |pop
              |swap
              |pop
              |ret
              |@get_default_if:
              |pop
              |push int8(14)
              |cast
              |swap
              |concat
              |sget
              |ret
              |@stop:
          """.stripMargin)
          .right
          .get
      )
    }
  }
}
