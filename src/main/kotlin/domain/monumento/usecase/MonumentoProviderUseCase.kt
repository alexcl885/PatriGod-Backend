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
    private val getMonumentosByUsuario = GetMonumentosByUsuario(repository)

    suspend fun getAllMonumentos() = getAllMonumentosUseCase

    suspend fun updateMonumento(updateMonumento: UpdateMonumento?, idMonu : String) : Monumento?{
        if (updateMonumento == null){
            logger.warn("No existen datos del empleado a actualizar")
            return null
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
    suspend fun postMonumento(monumento: Monumento?): Monumento?{
        if (monumento == null){
            logger.warn( "No existen datos del empleado a insertar")
            return null
        }
        postMonumentoUseCase.addMonumento = monumento  //seteamos
        val new = postMonumentoUseCase()
        if (new==null)
            logger.warn("No se ha insertado el empleado. Posiblemente ya exista")

        return new
    }

    suspend fun monumentosUser(idUsuario: String): List<Monumento> {
        if (idUsuario.isNullOrBlank()){
            logger.warn("El idUsuario está vacío. No podemos buscar el ususario")
            return emptyList()
        }
        getMonumentosByUsuario.idUsuario = idUsuario
        val monumentos = getMonumentosByUsuario()
        return if (monumentos == null) {
            logger.warn("No se ha encontrado monumentos con ese $idUsuario.")
            emptyList()
        }else{
            monumentos
        }
    }
}