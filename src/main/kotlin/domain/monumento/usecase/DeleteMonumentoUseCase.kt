package domain.monumento.usecase

import domain.monumento.repository.MonumentoInterface

class DeleteMonumentoUseCase (val repository: MonumentoInterface) {
    var idMonu : String? = null

    suspend operator fun invoke() : Boolean {
        return if (idMonu == null) {
            false
        }else{
            return repository.deleteMonumento(idMonu!!)
        }

    }
}