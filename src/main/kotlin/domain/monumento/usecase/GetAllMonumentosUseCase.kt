package domain.monumento.usecase

import domain.monumento.models.Monumento
import domain.monumento.repository.MonumentoInterface
import domain.usuario.models.Usuario
import domain.usuario.repository.UsuarioInterface
import ktor.ApplicationContext


class GetAllMonumentosUseCase(val repository: MonumentoInterface) {
    suspend operator fun invoke(): List<Monumento> {
        val listMonumentos = repository.getAllMonumentos()  //toda la lista de monumentos
        return listMonumentos.map{
                monu->  //para cada empleado
            if (!monu.imagen.isNullOrBlank()){  //si la imagen no es nula
                val local = ApplicationContext.context.environment.config.property("ktor.urlPath.baseUrl").getString()
                val relativePath = ApplicationContext.context.environment.config.property("ktor.urlPath.images").getString()
                monu.imagen = "$local/$relativePath/${monu.idMonu}/${monu.imagen}"
            }
            monu
        }

    }
}
