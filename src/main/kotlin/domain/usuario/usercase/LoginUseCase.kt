package domain.usuario.usercase

import domain.usuario.mapper.toUpdateUsuario
import domain.usuario.mapper.toUsuario
import domain.usuario.models.Usuario
import domain.usuario.repository.UsuarioInterface
import domain.usuario.security.JwtConfig

class LoginUseCase(val repository : UsuarioInterface) {
    suspend operator fun invoke(email: String ?, pass:String ?): Usuario ? {
        if (email.isNullOrBlank() || pass.isNullOrBlank()) return null

        return try{
            val em = repository.login(email, pass)  ?: null

            em!!.token = JwtConfig.generateToken(em.dni)

            val updateEmployee = em.toUpdateUsuario()
            val res = repository.updateUsuario(updateEmployee, email)
            return if (res)
                updateEmployee.toUsuario()
            else
                null
        }catch (e: Exception){
            println("Error en login:  ${e.localizedMessage}")
            null
        }
    }

}