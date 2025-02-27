package domain.monumento.usecase

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
            try {
                //fin de si hay nueva imagen a insertar.
                val employee = repository.updateMonumento(updateMonumento!!, idMonu!!)
                employee
            }catch (e: Exception){
                e.printStackTrace()
                null
            }
        }

    }
}