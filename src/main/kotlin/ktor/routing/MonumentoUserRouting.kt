package ktor.routing

import domain.monumento.usecase.MonumentoProviderUseCase
import domain.usuario.usercase.ProviderUseCase
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ktor.validateToken

fun Route.monumentosuserRouting(){

    route ("/monumentos/usuario/{idUsuario}"){
        authenticate("jwt-auth"){
            get() {
                val token = call.request.headers["Authorization"]?.removePrefix("Bearer ") //token el header
                val validate = call.validateToken(token!!)
                if (!validate) {
                    call.respond(HttpStatusCode.Unauthorized, "Token inválido")
                    return@get
                }

                val idUsuario = call.parameters["idUsuario"]
                if (idUsuario == null){
                    call.respond(HttpStatusCode.BadRequest, "Debes pasar el id/dni a buscar") //Montamos una respuesta con código 400.
                    return@get  //finalizamos en endpoint y mandamos inmediantamente la respuesta.
                }

                val monumento = MonumentoProviderUseCase.monumentosUser(idUsuario)
                call.respond(monumento)
            }

        }
    }
}