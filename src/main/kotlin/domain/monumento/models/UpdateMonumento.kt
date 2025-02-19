package domain.monumento.models

import kotlinx.serialization.Serializable

@Serializable
data class UpdateMonumento(
    var idMonu : String? = null,
    var nombre : String? = null,
    var ciudad : String? = null,
    var fecha : String? = null,
    var descripcion : String? = null,
    var imagen : String? = null,
    var descripcionPlus : String? = null
)