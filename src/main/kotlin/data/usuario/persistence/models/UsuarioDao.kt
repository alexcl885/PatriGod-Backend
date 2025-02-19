package data.usuario.persistence.models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UsuarioDao (id : EntityID<Int>) :  IntEntity(id) {
    companion object : IntEntityClass<UsuarioDao>(UsuarioTable)
    var dni by UsuarioTable.dni
    var email by UsuarioTable.email
    var password by UsuarioTable.password
    var nombre by UsuarioTable.nombre
    var phone by UsuarioTable.phone
    var token by UsuarioTable.token
}