package domain.monumento.usecase

import domain.monumento.infraestructure.Utils
import domain.monumento.models.Monumento
import domain.monumento.repository.MonumentoInterface
import ktor.ApplicationContext

class PostMonumentoUseCase (val repository: MonumentoInterface) {
    var addMonumento: Monumento? = null
    suspend operator fun invoke() : Monumento? {
        val monum = repository.getMonumentoByIdMonu(addMonumento!!.idMonu)
        return if (monum!=null)    null
        else{
            val isCreateDir = Utils.createDir(addMonumento!!.idMonu)  //creamos su directorio
            if (isCreateDir){
                val img = addMonumento!!.imagen
                if (img.isNotBlank()){  //Si tiene imagen, hay que crearla.
                    addMonumento!!.imagen =
                        Utils.createBase64ToImg(img, addMonumento!!.idMonu).toString()  //creamos la imagen, a partir del Base64 y devolvemos su http
                }
            }else{
                throw IllegalStateException("No se pudo crear el directorio del empleado. Puede que ya exista")
            }

            val new = repository.postMonumento(addMonumento!!)  //insertamos el monumento

            new?.let{  mon->
                if (mon.imagen.isNotBlank())   { //Debemos setear la url correctamente.
                    val local = ApplicationContext.context.environment.config.property("ktor.urlPath.baseUrl").getString()
                    val relativePath = ApplicationContext.context.environment.config.property("ktor.urlPath.images").getString()
                    new.imagen = "$local/$relativePath/${new.idMonu}/${mon.imagen}"
                }
            }

            return new
        }

    }
}