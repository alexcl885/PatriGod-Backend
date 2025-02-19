package data.monumento.persistence.models

import org.jetbrains.exposed.dao.id.IntIdTable

object MonumentoTable : IntIdTable("Monumento"){
    var idMonu = varchar("idMonu", 20).uniqueIndex()
    var nombre  = varchar("nombre", 20)
    var ciudad = varchar("ciudad", 40)
    var fecha = varchar("fecha", 40)
    var descripcion = varchar("descripcion", 40)
    var imagen = varchar("imagen", 40).nullable()
    var descripcionPlus = varchar("descripcionPlus", 40)

}