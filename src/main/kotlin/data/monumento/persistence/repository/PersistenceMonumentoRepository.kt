package data.monumento.persistence.repository

import data.monumento.persistence.models.MonumentoDao
import data.monumento.persistence.models.MonumentoTable
import data.usuario.persistence.models.UsuarioTable

import data.usuario.persistence.models.suspendTransaction

import domain.monumento.mapper.toMonumento
import domain.monumento.models.Monumento
import domain.monumento.models.UpdateMonumento
import domain.monumento.repository.MonumentoInterface
import domain.monumento.usecase.MonumentoProviderUseCase
import domain.monumento.usecase.MonumentoProviderUseCase.getMonumentoByIdMonu
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere

import org.jetbrains.exposed.sql.update


class PersistenceMonumentoRepository : MonumentoInterface {
    override suspend fun getAllMonumentos(): List<Monumento> {
        return suspendTransaction{
            MonumentoDao.all().map { it.toMonumento() }
        }
    }

    override suspend fun getMonumentoByIdMonu(idMonu: String): Monumento? {
        return suspendTransaction {
            MonumentoDao.find{
                MonumentoTable.idMonu eq idMonu
            }.limit(1).map { it.toMonumento() }.firstOrNull()
        }
    }

    override suspend fun postMonumento(monumento: Monumento): Monumento? = suspendTransaction {
            MonumentoDao.new {
                this.idMonu = monumento.idMonu
                this.nombre = monumento.nombre
                this.ciudad = monumento.ciudad
                this.fecha = monumento.fecha
                this.descripcion = monumento.descripcion
                this.imagen = monumento.imagen
                this.descripcionPlus = monumento.descripcionPlus
                this.idUsuario=monumento.idUsuario
            }
        }.toMonumento()

    override suspend fun updateMonumento(monumento: UpdateMonumento, idMonumento: String): Monumento? {
        var num = 0
        try {
            suspendTransaction {
                num = MonumentoTable
                    .update({ MonumentoTable.idMonu eq idMonumento }) { stm ->
                        monumento.nombre?.let { stm[nombre] = it }
                        monumento.ciudad?.let { stm[ciudad] = it }
                        monumento.fecha?.let { stm[fecha] = it }
                        monumento.descripcion?.let { stm[descripcion] = it }
                        monumento.imagen?.let { stm[imagen] = it }
                        monumento.descripcionPlus?.let { stm[descripcionPlus] = it }
                        monumento.idUsuario?.let { stm[idUsuario] = it }
                    }
            }
            return MonumentoProviderUseCase.getMonumentoByIdMonu(idMonumento)  //devuelvo todos los datos de ese empleado. Esto puede cambiarse.
            //   return updateEmployee.toEmployee()  //devolvemos el empleado modificado

        } catch (e: Exception) {
            e.printStackTrace()
            return null //ha pasado algo y no se ha modificado.
        }
    }

    override suspend fun deleteMonumento(idMonu: String): Boolean = suspendTransaction  {
        val num = MonumentoTable
            .deleteWhere { MonumentoTable.idMonu eq idMonu }
        num == 1
    }

    override suspend fun getMonumentosByUsuario(idUsuario: String): List<Monumento>? {
        return suspendTransaction {
            MonumentoDao.find{
                MonumentoTable.idUsuario eq idUsuario
            }.map { it.toMonumento() }
        }
    }


}