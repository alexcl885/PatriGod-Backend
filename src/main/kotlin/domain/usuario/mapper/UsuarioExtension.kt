package domain.usuario.mapper

import data.usuario.persistence.models.UsuarioDao
import data.usuario.persistence.models.UsuarioTable
import domain.usuario.models.UpdateUsuario
import domain.usuario.models.Usuario

fun Usuario.toUpdateUsuario() :UpdateUsuario{
    return UpdateUsuario(
         dni = dni,
         email = email ,
         password = password,
         nombre=nombre ,
         phone= phone ,
         token=token
    )
}
fun UpdateUsuario.toUsuario():Usuario{
    return Usuario(
        dni = dni!!,
        email = email !!,
        password = password!!,
        nombre=nombre!! ,
        phone= phone !!,
        token=token!!
    )
}
fun UsuarioDao.toUsuario() : Usuario{
    val e = Usuario(
        dni = dni,
        email = email ,
        password = password,
        nombre=nombre?: "Sin nombre" ,
        phone= phone ?: "000-0000-0000",
        token=token ?: "null",

    )
    return e
}
