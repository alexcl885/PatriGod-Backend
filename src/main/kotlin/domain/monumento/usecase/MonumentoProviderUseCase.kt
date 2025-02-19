package domain.monumento.usecase

import data.monumento.persistence.repository.PersistenceMonumentoRepository


class MonumentoProviderUseCase {
    private val repository  = PersistenceMonumentoRepository()



    private val getAllMonumentosUseCase = GetAllMonumentosUseCase(repository)

    suspend fun getAllMonumentos() = getAllMonumentosUseCase

}