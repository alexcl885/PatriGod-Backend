package domain.monumento.models

import kotlinx.serialization.Serializable

@Serializable
data class Monumento(
    var idMonu: String,
    var nombre: String,
    var ciudad: String,
    var fecha: String,
    var descripcion: String,
    var imagen: String,
    var descripcionPlus: String
)