package data.monumento.persistence.models

import data.usuario.persistence.models.UsuarioTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class MonumentoDao (id : EntityID<Int>) :  IntEntity(id) {
    companion object : IntEntityClass<MonumentoDao>(MonumentoTable)
    var idMonu by MonumentoTable.idMonu
    var nombre by MonumentoTable.nombre
    var ciudad by MonumentoTable.ciudad
    var fecha by MonumentoTable.fecha
    var descripcion by MonumentoTable.descripcion
    var imagen by MonumentoTable.imagen
    var descripcionPlus by MonumentoTable.descripcionPlus
}