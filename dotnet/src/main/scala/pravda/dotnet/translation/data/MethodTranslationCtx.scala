/*
 * Copyright (C) 2018  Expload.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package pravda.dotnet.translation.data

import pravda.dotnet.data.TablesData.MethodDebugInformationData
import pravda.dotnet.parsers.CIL.CilData
import pravda.dotnet.parsers.Signatures

final case class MethodTranslationCtx(argsCount: Int,
                                      localsCount: Int,
                                      name: String,
                                      signatures: Map[Long, Signatures.Signature],
                                      cilData: CilData,
                                      local: Boolean,
                                      void: Boolean,
                                      debugInfo: Option[MethodDebugInformationData])
