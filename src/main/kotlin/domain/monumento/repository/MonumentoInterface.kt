package domain.monumento.repository

import domain.monumento.models.Monumento
import domain.usuario.models.Usuario

interface MonumentoInterface {
    suspend fun getAllMonumentos () : List <Monumento>
}