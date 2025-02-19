package domain.usuario.usercase

import domain.usuario.models.UpdateUsuario
import domain.usuario.repository.UsuarioInterface

class UpdateUsuarioUseCase(val repository : UsuarioInterface) {
    var updateEmployee: UpdateUsuario? = null
    var dni: String? = null

    suspend operator fun invoke() : Boolean {
        return if (updateEmployee == null || dni == null) {
            false
        }else{
            return repository.updateUsuario(updateEmployee!!, dni!!)
        }

    }
}