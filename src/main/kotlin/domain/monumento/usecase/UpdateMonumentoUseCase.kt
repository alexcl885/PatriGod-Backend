package domain.monumento.usecase

import domain.monumento.models.UpdateMonumento
import domain.monumento.repository.MonumentoInterface
import domain.usuario.models.UpdateUsuario

class UpdateMonumentoUseCase (val repository: MonumentoInterface) {
    var updateMonumento: UpdateMonumento? = null
    var idMonu: String? = null

    suspend operator fun invoke() : Boolean {
        return if (updateMonumento == null || idMonu == null) {
            false
        }else{
            return repository.updateMonumento(updateMonumento!!, idMonu!!)
        }

    }
}