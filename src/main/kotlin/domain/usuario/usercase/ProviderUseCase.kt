package domain.usuario.usercase

import data.usuario.persistence.repository.PersistenceUsuarioRepository
import domain.usuario.models.UpdateUsuario
import domain.usuario.models.Usuario
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object ProviderUseCase {
    private val repository  = PersistenceUsuarioRepository()

    val logger: Logger = LoggerFactory.getLogger("UsuarioUseCaseLogger")

    private val getAllUsearioUseCase = GetAllUsuariosUseCase(repository)
    private val getUsuarioByDniUseCase = GetUsuaioByDniUseCase(repository)
    private val loginUseCase = LoginUseCase(repository)
    private val registerUseCase = RegisterUseCase(repository)
    private val updateUsuarioUseCase = UpdateUsuarioUseCase(repository)
    private val getUsuarioByEmailUseCase = GetUsuarioByEmailUseCase(repository)
    private val deleteEmployeUseCase = DeleteUsuarioUseCaseç(repository)

    suspend fun getAllUsuarios() = getAllUsearioUseCase

    suspend fun getUsuarioByDni(dni : String) : Usuario?{
        if (dni.isNullOrBlank()){
            logger.warn("El dni está vacío. No podemos buscar un empleado")
            return null
        }
        getUsuarioByDniUseCase.dni = dni
        val emp = getUsuarioByDniUseCase()
        return if (emp == null) {
            logger.warn("No se ha encontrado un empleado con ese $dni.")
            null
        }else{
            emp
        }
    }
    suspend fun updateEmployee(updateEmployee: UpdateUsuario?, dni : String) : Boolean{
        if (updateEmployee == null){
            logger.warn("No existen datos del empleado a actualizar")
            return false
        }

        updateUsuarioUseCase.updateEmployee = updateEmployee
        updateUsuarioUseCase.dni = dni
        return updateUsuarioUseCase()
    }

    suspend fun getEmployeeByEmail(email: String) : List<Usuario> {
        getUsuarioByEmailUseCase.filter = email
        return getUsuarioByEmailUseCase()
    }



    suspend fun deleteEmployee(dni : String) : Boolean{
        deleteEmployeUseCase.dni = dni
        return deleteEmployeUseCase()
    }

    suspend fun login(dni: String?, pass: String?) : Usuario? = loginUseCase(dni, pass)

    suspend fun register(employee : UpdateUsuario): Usuario? {

        return if(
            employee.dni.isNullOrBlank() ||
            employee.email.isNullOrBlank() ||
            employee.password.isNullOrBlank()
        )
            null
        else
            registerUseCase(employee)

    }

}