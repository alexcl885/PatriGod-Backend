package domain.usuario.usercase

import domain.usuario.models.Usuario
import domain.usuario.repository.UsuarioInterface

class GetUsuaioByDniUseCase(val repository : UsuarioInterface) {
    var dni : String? = null

    suspend operator fun invoke() : Usuario? {
        return if (dni?.isNullOrBlank() == true)
            null
        else{
            repository.getUsuarioByDni(dni!!)
        }
    }
}