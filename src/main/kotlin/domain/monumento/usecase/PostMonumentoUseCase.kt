package domain.monumento.usecase

import domain.monumento.models.Monumento
import domain.monumento.models.UpdateMonumento
import domain.monumento.repository.MonumentoInterface

class PostMonumentoUseCase (val repository: MonumentoInterface) {
    var addMonumento: Monumento? = null
    suspend operator fun invoke() : Monumento? {
        val monu = repository.getMonumentoByIdMonu(addMonumento!!.idMonu)
        return if (monu!=null)    null
        else{
            //aquí tengo que tener la imagen creada y el name en employee!!.urlImage
            val new = repository.postMonumento(addMonumento!!)
            return new
        }

    }
}