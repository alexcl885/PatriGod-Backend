package domain.monumento.repository

import domain.monumento.models.Monumento
import domain.monumento.models.UpdateMonumento
import domain.usuario.models.UpdateUsuario
import domain.usuario.models.Usuario

interface MonumentoInterface {
    suspend fun getAllMonumentos () : List <Monumento>

    suspend fun getMonumentoByIdMonu (idMonu: String) : Monumento ?

    suspend fun postMonumento(monumento: Monumento) : Boolean

    suspend fun updateMonumento(monumento: UpdateMonumento, idMonumento:String) : Boolean

    suspend fun deleteMonumento(idMonu : String) : Boolean
}