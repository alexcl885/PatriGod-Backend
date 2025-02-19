package data.usuario.inmemory.repository

import domain.usuario.models.UpdateUsuario
import domain.usuario.models.Usuario
import domain.usuario.repository.UsuarioInterface

class MemoryUsuarioRepository: UsuarioInterface {
    override suspend fun getAllUsuarios(): List<Usuario> {
        TODO("Not yet implemented")
    }

    override suspend fun getUsuarioByEmail(email: String): List<Usuario> {
        TODO("Not yet implemented")
    }

    override suspend fun getUsuarioByDni(dni: String): Usuario? {
        TODO("Not yet implemented")
    }

    override suspend fun postUsuario(usuario: Usuario): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun updateUsuario(employee: UpdateUsuario, dni: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUsuario(dni: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun login(dni: String, pass: String): Usuario? {
        TODO("Not yet implemented")
    }

    override suspend fun register(usuario: UpdateUsuario): Usuario? {
        TODO("Not yet implemented")
    }

}