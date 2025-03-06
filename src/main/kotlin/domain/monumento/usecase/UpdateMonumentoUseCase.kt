package domain.monumento.usecase

import domain.monumento.infraestructure.Utils
import domain.monumento.models.Monumento
import domain.monumento.models.UpdateMonumento
import domain.monumento.repository.MonumentoInterface

class UpdateMonumentoUseCase (val repository: MonumentoInterface) {
    var updateMonumento: UpdateMonumento? = null
    var idMonu: String? = null

    suspend operator fun invoke() : Monumento? {
        return if (updateMonumento == null || idMonu == null) {
            null
        }else{
            /*
            Para actualizar, primero tenemos que ver si tiene imagen para modificar.
            1 - Si tiene imagen a modificar, hay que eliminarla físicamente.
              - Hay que crear la nueva imagen, igual que hemos hecho en el insert.
              - Hay que modificar el nombre del atributo, con el nuevo nombre.
             */
            try {
                updateMonumento?.imagen?.let{  newImg->//siempre que haya una nueva imagen a insertar.
                    //estoy dentro de la nueva imagen a crear.
                    val monument = repository.getMonumentoByIdMonu(idMonu!!)  //necesito el empleado, para la antigua imagen.
                    monument?.let { monument ->
                        monument.imagen?.let{ oldImg->  //Si hay imagen antigua, me la cargo
                            Utils.deleteImage(monument.idMonu, oldImg)  //la elimino.
                        }
                    }
                    //ahora tengo que crear la nueva imagen.
                    val newImagenUrl = Utils.createBase64ToImg(newImg, idMonu!!)
                    updateMonumento!!.imagen = newImagenUrl
                }//fin de si hay nueva imagen a insertar.
                val monumento = repository.updateMonumento(updateMonumento!!, idMonu!!)
                monumento
            }catch (e: Exception){
                e.printStackTrace()
                null
            }
        }

    }
}