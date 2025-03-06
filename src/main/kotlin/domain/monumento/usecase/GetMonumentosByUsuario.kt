package domain.monumento.usecase

import domain.monumento.models.Monumento
import domain.monumento.repository.MonumentoInterface

/**
 * Esta caso de uso devuelve monumntos de un usuario segun su id
 */
class GetMonumentosByUsuario(val repository: MonumentoInterface) {
    var idUsuario : String? = null

    suspend operator fun invoke() : List<Monumento>? {
        return if (idUsuario?.isNullOrBlank() == true)
            null
        else{
            val listMonumentos = repository.getMonumentosByUsuario(idUsuario!!)

            return listMonumentos
        }
    }
}