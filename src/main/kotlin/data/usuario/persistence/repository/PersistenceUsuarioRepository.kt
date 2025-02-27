package data.usuario.persistence.repository

import data.usuario.persistence.models.UsuarioDao
import data.usuario.persistence.models.UsuarioTable
import data.usuario.persistence.models.suspendTransaction
import data.usuario.security.PasswordHash
import domain.usuario.mapper.toUsuario
import domain.usuario.models.UpdateUsuario
import domain.usuario.models.Usuario
import domain.usuario.repository.UsuarioInterface
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.update

class PersistenceUsuarioRepository : UsuarioInterface {
    override suspend fun getAllUsuarios(): List<Usuario> {
        return suspendTransaction{
            UsuarioDao.all().map { it.toUsuario() }
        }
    }

    override suspend fun getUsuarioByEmail(email: String): Usuario? {
       return suspendTransaction {
            UsuarioDao.find{
                UsuarioTable.email eq email.toString()
            }.limit(1).map { it.toUsuario() }.firstOrNull()
       }
    }

    override suspend fun getUsuarioByDni(dni: String): Usuario? {
        try {
            return suspendTransaction {

                    UsuarioDao.find {
                        UsuarioTable.dni eq dni
                    }.limit(1).map { it.toUsuario() }.firstOrNull()

            }
        }catch (e: Exception){
            e.printStackTrace()
        }
        return null
    }

    override suspend fun postUsuario(usuario: Usuario): Boolean {
        val user = getUsuarioByDni(usuario.dni)
        return if (user == null){
            suspendTransaction {
                UsuarioDao.new {
                    this.dni = usuario.dni
                    this.email = usuario.email
                    this.password = usuario.password
                    this.nombre = usuario.nombre
                    this.phone = usuario.phone
                    this.token = usuario.token

                }

            }
            true
        }
        else{
            false
        }
    }

    override suspend fun updateUsuario(usuario: UpdateUsuario, dni: String): Boolean {
        var num = 0
        try {
            suspendTransaction {
                num = UsuarioTable
                    .update({ UsuarioTable.dni eq dni }) { stm ->
                        usuario.email?.let { stm[email] = it }
                        usuario.password?.let { stm[password] = it }
                        usuario.nombre?.let { stm[nombre] = it }
                        usuario.phone?.let { stm[phone] = it }
                        usuario.token?.let { stm[token] = it }
                    }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
        return num == 1
    }

    override suspend fun deleteUsuario(dni: String): Boolean = suspendTransaction {
        val num = UsuarioTable
            .deleteWhere { UsuarioTable.dni eq dni }
        num == 1
    }

    override suspend fun login(dni: String, pass: String): Usuario? {
        val usuario : Usuario = getUsuarioByDni(dni) ?: return null

        return try {
            val posibleHash = PasswordHash.hash(pass)
            if (posibleHash == usuario.password)
                usuario
            else
                null
        } catch (e: Exception) {
            println("Error en la autenticación: ${e.localizedMessage}")
            null
        }
    }

    override suspend fun register(usuario: UpdateUsuario): Usuario? {
        return try {
            suspendTransaction {
                UsuarioDao.new {
                    this.dni = usuario.dni!! //es seguro.
                    this.email = usuario.email!!   //es seguro.
                    this.password = PasswordHash.hash(usuario.password!!) //hasheo la password.
                    this.nombre = usuario.nombre!!
                    this.phone = usuario.phone!!
                    this.token = usuario.token!!
                }
            }.let {
                it.toUsuario() //hago directamente el mapping.
            }
        } catch (e: Exception) {
            println("Error en el registro de empleado: ${e.localizedMessage}")
            null
        }
    }

}