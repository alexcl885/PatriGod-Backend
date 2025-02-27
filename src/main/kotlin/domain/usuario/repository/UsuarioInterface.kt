package domain.usuario.repository

import domain.usuario.models.UpdateUsuario
import domain.usuario.models.Usuario

interface UsuarioInterface {
    suspend fun getAllUsuarios () : List <Usuario>


    suspend fun getUsuarioByEmail ( email : String) : Usuario?

    //Debe ser nullable, por si no existe.
    suspend fun getUsuarioByDni (dni: String) : Usuario ?

    suspend fun postUsuario(usuario: Usuario) : Boolean

    suspend fun updateUsuario(usuario: UpdateUsuario, dni:String) : Boolean

    suspend fun deleteUsuario(dni : String) : Boolean

    suspend fun login(email: String, pass: String) : Usuario?  //más adelante, implementaré con token

    suspend fun register(usuario: UpdateUsuario) : Usuario? //Este será el que utilicemos para el registro

    //  suspend fun updateTokenUser(dni: String, token: String) : Boolean
}