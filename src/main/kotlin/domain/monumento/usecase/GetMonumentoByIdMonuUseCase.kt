package domain.monumento.usecase

import domain.monumento.models.Monumento
import domain.monumento.repository.MonumentoInterface
import domain.usuario.models.Usuario

class GetMonumentoByIdMonuUseCase (val repository: MonumentoInterface) {
    var idMonu : String? = null

    suspend operator fun invoke() : Monumento? {
        return if (idMonu?.isNullOrBlank() == true)
            null
        else{
            repository.getMonumentoByIdMonu(idMonu!!)
        }
    }
}