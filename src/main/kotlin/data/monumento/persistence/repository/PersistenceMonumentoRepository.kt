package data.monumento.persistence.repository

import data.monumento.persistence.models.MonumentoDao
import data.usuario.persistence.models.UsuarioDao
import data.usuario.persistence.models.suspendTransaction
import domain.monumento.mapper.toMonumento
import domain.monumento.models.Monumento
import domain.monumento.repository.MonumentoInterface
import domain.usuario.mapper.toUsuario

class PersistenceMonumentoRepository : MonumentoInterface {
    override suspend fun getAllMonumentos(): List<Monumento> {
        return suspendTransaction{
            MonumentoDao.all().map { it.toMonumento() }
        }
    }
}