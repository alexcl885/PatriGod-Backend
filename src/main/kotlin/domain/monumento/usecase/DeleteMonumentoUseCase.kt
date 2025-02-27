package domain.monumento.usecase

import domain.monumento.repository.MonumentoInterface

class DeleteMonumentoUseCase (val repository: MonumentoInterface) {
    var idMonu : String? = null

    suspend operator fun invoke() : Boolean {
        return if (idMonu == null) {
            false
        }else{
            val monu = repository.getMonumentoByIdMonu(idMonu!!)
            monu?.let { monu ->

                return repository.deleteMonumento(monu.idMonu!!)
            }
            false
        }

    }
}