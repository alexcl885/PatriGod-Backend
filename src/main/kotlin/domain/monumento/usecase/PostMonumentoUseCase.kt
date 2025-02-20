package domain.monumento.usecase

import domain.monumento.models.Monumento
import domain.monumento.models.UpdateMonumento
import domain.monumento.repository.MonumentoInterface

class PostMonumentoUseCase (val repository: MonumentoInterface) {
    var addMonumento: Monumento? = null
    suspend operator fun invoke() : Boolean {
        return repository.postMonumento(addMonumento!!)
    }
}