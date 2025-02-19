package domain.usuario.models

import kotlinx.serialization.Serializable
/*
Sólo para serializar en consultas
patch
 */
@Serializable
data class UpdateUsuario (
    var dni: String? = null,
    var email: String? = null,
    var password: String? = null,
    var nombre: String? = null,
    var phone: String? = null,
    var token:String ? = null
)