package domain.monumento.usecase

import domain.monumento.models.Monumento
import domain.monumento.repository.MonumentoInterface
import domain.usuario.models.Usuario
import domain.usuario.repository.UsuarioInterface

class GetAllMonumentosUseCase(val repository: MonumentoInterface) {
    suspend operator fun invoke(): List<Monumento> = repository.getAllMonumentos()
}
