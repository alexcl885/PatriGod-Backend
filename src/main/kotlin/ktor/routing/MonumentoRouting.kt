package ktor.routing

import domain.monumento.usecase.MonumentoProviderUseCase
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.monumentosRouting(){

    route ("/monument"){
        get() {
            call.respondText("Estoy en monumentos!!!")
        }
    }




    route ("/monumentos"){
        get(){
            val monumentos = MonumentoProviderUseCase().getAllMonumentos()
            call.respond(monumentos.invoke())
        }

    }


}