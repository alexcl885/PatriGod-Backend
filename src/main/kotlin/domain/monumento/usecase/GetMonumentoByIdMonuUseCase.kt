package domain.monumento.usecase

import domain.monumento.models.Monumento
import domain.monumento.repository.MonumentoInterface
import domain.usuario.models.Usuario
import ktor.ApplicationContext


class GetMonumentoByIdMonuUseCase (val repository: MonumentoInterface) {
    var idMonu : String? = null

    suspend operator fun invoke() : Monumento? {
        return if (idMonu?.isNullOrBlank() == true)
            null
        else{
            val monumento = repository.getMonumentoByIdMonu(idMonu!!)
            monumento?.let{   monu->
                if (!monu.imagen.isNullOrBlank()) {
                    val local =
                        ApplicationContext.context.environment.config.property("ktor.urlPath.baseUrl").getString()
                    // val relativePath =
                    // ApplicationContext.context.environment.config.property("ktor.path.images").getString()
                    val relativePath = ApplicationContext.context.environment.config.property("ktor.urlPath.images").getString()
                    monu.imagen = "$local/$relativePath/$idMonu/${monu.imagen}"
                }
            }
            return monumento
        }
    }
}