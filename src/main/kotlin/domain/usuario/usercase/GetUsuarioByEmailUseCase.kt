package domain.usuario.usercase

import domain.usuario.models.Usuario
import domain.usuario.repository.UsuarioInterface

class GetUsuarioByEmailUseCase(val repository: UsuarioInterface) {
    var filter : String? =null

    suspend operator fun invoke() : List<Usuario> {
        return filter?.let {
            repository.getUsuarioByEmail(it)
        }?:run{
            emptyList()  //lista vacía
        }
    }

}