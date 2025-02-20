package domain.monumento.usecase

import data.monumento.persistence.repository.PersistenceMonumentoRepository
import domain.monumento.models.Monumento

import domain.monumento.models.UpdateMonumento

import domain.usuario.usercase.ProviderUseCase.logger

object MonumentoProviderUseCase {
    private val repository  = PersistenceMonumentoRepository()


    private val updateMonumentoUseCase = UpdateMonumentoUseCase(repository)
    private val getMonumentoByIdMonuUseCase = GetMonumentoByIdMonuUseCase(repository)
    private val deleteMonumentoUseCase = DeleteMonumentoUseCase(repository)
    private val getAllMonumentosUseCase = GetAllMonumentosUseCase(repository)
    private val postMonumentoUseCase = PostMonumentoUseCase(repository)

    suspend fun getAllMonumentos() = getAllMonumentosUseCase

    suspend fun updateMonumento(updateMonumento: UpdateMonumento?, idMonu : String) : Boolean{
        if (updateMonumento == null){
            logger.warn("No existen datos del empleado a actualizar")
            return false
        }

        updateMonumentoUseCase.updateMonumento = updateMonumento
        updateMonumentoUseCase.idMonu = idMonu
        return updateMonumentoUseCase()
    }

    suspend fun getMonumentoByIdMonu(idMonu: String) : Monumento? {
        if (idMonu.isNullOrBlank()){
            logger.warn("El idMonumento está vacío. No podemos buscar un monumento")
            return null
        }
        getMonumentoByIdMonuUseCase.idMonu = idMonu
        val monumento = getMonumentoByIdMonuUseCase()
        return if (monumento == null) {
            logger.warn("No se ha encontrado un empleado con ese $idMonu.")
            null
        }else{
            monumento
        }
    }

    suspend fun deleteMonumento(idMonu : String) : Boolean{
        deleteMonumentoUseCase.idMonu = idMonu
        return deleteMonumentoUseCase()
    }
    suspend fun postMonumento(monumento: Monumento?): Boolean{
        if (monumento == null){
            logger.warn( "No existen datos del monumento a insertar")
            return false
        }
        postMonumentoUseCase.addMonumento = monumento
        val res = postMonumentoUseCase()
        return if (!res){
            logger.warn("No se ha insertado el monumento. Posiblemente ya exista")
            false
        }else{
            true
        }
    }

}