package ktor.routing

import domain.monumento.models.Monumento
import domain.monumento.models.UpdateMonumento
import domain.monumento.usecase.MonumentoProviderUseCase
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


fun Route.monumentosRouting(){

    route ("/monument"){
        get() {
            call.respondText("Estoy en monumentos!!!")
        }
    }


    route ("/monumentos"){
        authenticate("jwt-auth"){
            get(){
                val token = call.request.headers["Authorization"]?.removePrefix("Bearer ") //token el header
                val validate = call.validateToken(token!!)
                if (!validate) {
                    call.respond(HttpStatusCode.Unauthorized, "Token inválido")
                    return@get
                }

                val monumentos = MonumentoProviderUseCase.getAllMonumentos()
                call.respond(monumentos.invoke())
            }
            delete("{idMonu}"){
                val token = call.request.headers["Authorization"]?.removePrefix("Bearer ") //token el header
                val validate = call.validateToken(token!!)
                if (!validate) {
                    call.respond(HttpStatusCode.Unauthorized, "Token inválido")
                    return@delete
                }

                val idMonu = call.parameters["idMonu"]
                ProviderUseCase.logger.warn("Queremos borrar el empleado con dni $idMonu")
                idMonu?.let{
                    val res = MonumentoProviderUseCase.deleteMonumento(idMonu)
                    if (! res){
                        call.respond(HttpStatusCode.NotFound,"Monumento no encontrado para borrar")  //Montamos un 404 de no encontrado.
                    }else{
                        call.respondText("Monumento con idMonu: " + idMonu + " ha sido eliminado")
                    }
                }?:run{
                    call.respond(HttpStatusCode.NoContent,"Debes identintificar el empleado")
                }
                return@delete


            }
            get("{idMonu}") {
                val token = call.request.headers["Authorization"]?.removePrefix("Bearer ") //token el header
                val validate = call.validateToken(token!!)
                if (!validate) {
                    call.respond(HttpStatusCode.Unauthorized, "Token inválido")
                    return@get
                }

                val idMonumento = call.parameters["idMonu"]
                if (idMonumento == null){
                    call.respond(HttpStatusCode.BadRequest, "Debes pasar el dni a buscar") //Montamos una respuesta con código 400.
                    return@get  //finalizamos en endpoint y mandamos inmediantamente la respuesta.
                }

                val monumento = MonumentoProviderUseCase.getMonumentoByIdMonu(idMonumento)
                if (monumento ==null){
                    call.respond(HttpStatusCode.NotFound,"Empleado no encontrado")  //Montamos un 404 de no encontrado.
                    return@get //finalizamos en endpoint y mandamos inmediantamente la respuesta.
                }
                call.respond(monumento)  //mandamos el empleado como respuesta al cliente.
            }
            post() {
                val token = call.request.headers["Authorization"]?.removePrefix("Bearer ") //token el header
                val validate = call.validateToken(token!!)
                if (!validate) {
                    call.respond(HttpStatusCode.Unauthorized, "Token inválido")
                    return@post
                }

                try{
                    val monumento = call.receive<Monumento>()
                    val res = MonumentoProviderUseCase.postMonumento(monumento)
                    if (! res){
                        call.respond(HttpStatusCode.Conflict, "El empleado no pudo insertarse. Puede que ya exista")
                        return@post
                    }
                    call.respond(HttpStatusCode.Created, "Se ha insertado correctamente con nombre =  ${monumento.nombre}")
                } catch (e : IllegalStateException){
                    call.respond(HttpStatusCode.BadRequest, "Error en el formato de envío de datos o lectura del cuerpo.")
                } catch (e: JsonConvertException){
                    call.respond(HttpStatusCode.BadRequest," Problemas en la conversión json")
                } catch (e: Exception){
                    call.respond(HttpStatusCode.BadRequest, "Error en los datos. Probablemente falten.")
                }
            }
            patch("{idMonu}"){
                val token = call.request.headers["Authorization"]?.removePrefix("Bearer ") //token el header
                val validate = call.validateToken(token!!)
                if (!validate) {
                    call.respond(HttpStatusCode.Unauthorized, "Token inválido")
                    return@patch
                }
                try{
                    val idMonu = call.parameters["idMonu"]
                    idMonu?.let{
                        val updateEmployee = call.receive<UpdateMonumento>()
                        val res = MonumentoProviderUseCase.updateMonumento(updateEmployee, idMonu)
                        if (! res){
                            call.respond(HttpStatusCode.Conflict, "El monumento no pudo modificarse. Puede que no exista")
                            return@patch
                        }
                        call.respond(HttpStatusCode.Created, "Se ha actualizado correctamente con idMonumento =  ${idMonu}")
                    }?: run{
                        call.respond(HttpStatusCode.BadRequest,"Debes identificar el monumento")
                        return@patch
                    }
                } catch (e: IllegalStateException){
                    call.respond(HttpStatusCode.BadRequest,"Error en el formado de envío de los datos o lectura del cuerpo.")
                } catch (e: JsonConvertException){
                    call.respond(HttpStatusCode.BadRequest,"Error en el formado de json")
                }
            }

        }
    }


}