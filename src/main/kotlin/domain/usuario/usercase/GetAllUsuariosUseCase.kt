package domain.usuario.usercase

import domain.usuario.models.Usuario
import domain.usuario.repository.UsuarioInterface

class GetAllUsuariosUseCase(val repository: UsuarioInterface) {
    suspend operator fun invoke(): List<Usuario> = repository.getAllUsuarios()
}