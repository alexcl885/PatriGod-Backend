package domain.usuario.usercase

import domain.usuario.models.UpdateUsuario
import domain.usuario.models.Usuario
import domain.usuario.repository.UsuarioInterface

class RegisterUseCase(val repository: UsuarioInterface) {
    operator suspend fun invoke(usuario: UpdateUsuario): Usuario? {

        usuario.dni = usuario.dni!!
        usuario.nombre = usuario.nombre!!
        usuario.password = usuario.password!!
        usuario.phone = usuario.phone?:"0000000"
        usuario.token = usuario.token?: ""

        return if (repository.login(usuario.dni!!, usuario.password!!)!=null)
            null
        else
            repository.register(usuario)
    }
}