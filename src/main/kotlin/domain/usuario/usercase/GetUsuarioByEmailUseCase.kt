package domain.usuario.usercase

import domain.usuario.models.Usuario
import domain.usuario.repository.UsuarioInterface

class GetUsuarioByEmailUseCase(val repository: UsuarioInterface) {
    var email : String? =null

    suspend operator fun invoke() : Usuario? {
        return if (email?.isNullOrBlank() == true)
            null
        else{
            repository.getUsuarioByEmail(email!!)
        }
    }

}