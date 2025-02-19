package domain.usuario.models

import kotlinx.serialization.Serializable

@Serializable
data class Usuario(
    var dni: String,
    var email: String ,
    var password: String ,
    val nombre: String ,
    var phone: String ,
    var token:String
)
