package data.usuario.persistence.models


import org.jetbrains.exposed.dao.id.IntIdTable

object UsuarioTable : IntIdTable("Usuario") {
    var dni = varchar("dni", 20).uniqueIndex()
    var email = varchar("email", 100)
    var password = varchar("password", 255)
    val nombre= varchar("nombre", 100)
    var phone = varchar("phone", 100)
    var token = varchar("token", 255).nullable()
}

