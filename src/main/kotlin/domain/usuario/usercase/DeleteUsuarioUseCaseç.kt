package domain.usuario.usercase

import domain.usuario.repository.UsuarioInterface

class DeleteUsuarioUseCaseç(val repository : UsuarioInterface) {
    var dni : String? = null

    suspend operator fun invoke() : Boolean {
        return if (dni == null) {
            false
        }else{
            return repository.deleteUsuario(dni!!)
        }

    }
}