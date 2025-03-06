package ktor.routing

import domain.usuario.models.UpdateUsuario
import domain.usuario.models.Usuario
import domain.usuario.usercase.ProviderUseCase
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ktor.validateToken

/*
Contexto para las rutas de authenticación.
Esta función de extensión, sólo es accesible dentro del contexto de Routing.
Aquí pondremos todas las rutas que tienen que ver con la authenticación, como el
login, registro.
 */
fun Route.authRouting(){

    //Para el login
    route("/auth") {
        post {
            try {
                val loginRequest = call.receive<UpdateUsuario>()
                val login: Usuario? = ProviderUseCase.login(loginRequest.dni, loginRequest.password)  // Caso de uso del login

                if (login != null) {
                    val dni: String = loginRequest.dni.toString()
                    val token: String = login.token ?: ""

                    call.respond(mapOf("dni" to dni, "token" to token))
                } else {
                    call.respond(HttpStatusCode.Unauthorized, "Usuario incorrecto")
                }
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Formato de solicitud incorrecto")
            }
        }
    }


    route ("/register"){

        post(){
            try{
                val user = call.receive<UpdateUsuario>()
                val register = ProviderUseCase.register(user)

                if (register != null){
                    val msg = "Se ha insertado correctamente con dni =  ${register.dni}"
                    call.respond(mapOf("msg" to msg!!))
                }
                else
                    call.respond(HttpStatusCode.Conflict, "No se ha podido realizar el registro")

            } catch (e : IllegalStateException){
                call.respond(HttpStatusCode.BadRequest, "Error en el formato de envío de datos o lectura del cuerpo.")
            } catch (e: JsonConvertException){
                call.respond(HttpStatusCode.BadRequest," Problemas en la conversión json")
            }

        } //fin post

    }

    route("/usuario"){
        authenticate("jwt-auth"){
            get("{dni}"){
                val token = call.request.headers["Authorization"]?.removePrefix("Bearer ") //token el header
                val validate = call.validateToken(token!!)
                if (!validate) {
                    call.respond(HttpStatusCode.Unauthorized, "Token inválido")
                    return@get
                }



                val dni = call.parameters["dni"]
                if (dni == null){
                    call.respond(HttpStatusCode.BadRequest, "Debes pasar el dni a buscar")
                    return@get
                }

                val employee = ProviderUseCase.getUsuarioByDni(dni)
                if (employee ==null){
                    call.respond(HttpStatusCode.NotFound,"Empleado no encontrado")
                    return@get
                }
                call.respond(employee)
            }
        }
    }

}