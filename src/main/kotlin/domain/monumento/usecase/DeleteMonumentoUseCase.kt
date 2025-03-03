package domain.monumento.usecase

import domain.monumento.infraestructure.Utils
import domain.monumento.repository.MonumentoInterface

class DeleteMonumentoUseCase (val repository: MonumentoInterface) {
    var idMonu : String? = null

    suspend operator fun invoke() : Boolean {
        return if (idMonu == null) {
            false
        }else{
            val monu = repository.getMonumentoByIdMonu(idMonu!!)
            monu?.let { monumento ->
                monumento.imagen?.let{ img->
                    Utils.deleteImage(monumento.idMonu!!, img)
                    Utils.deleteDirectory(monumento.idMonu!!)
                }
                return repository.deleteMonumento(monu.idMonu!!)
            }
            false
        }

    }
}