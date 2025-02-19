package domain.monumento.mapper

import data.monumento.persistence.models.MonumentoDao
import domain.monumento.models.Monumento
import domain.monumento.models.UpdateMonumento

fun Monumento.toUpdateMonumento() : UpdateMonumento {
    return UpdateMonumento(
        idMonu=idMonu,
        nombre = nombre,
        ciudad = ciudad ,
        fecha = fecha,
        descripcion=descripcion ,
        imagen = imagen ,
        descripcionPlus =descripcionPlus
    )
}
fun UpdateMonumento.toMonumento(): Monumento {
    return Monumento(
        idMonu = idMonu!!,
        nombre = nombre!!,
        ciudad = ciudad!!,
        fecha = fecha!!,
        descripcion = descripcion!!,
        imagen = imagen!!,
        descripcionPlus = descripcionPlus!!,

    )
}
fun MonumentoDao.toMonumento() : Monumento {
    val e = Monumento(
        idMonu = idMonu,
        nombre = nombre,
        ciudad = ciudad ,
        fecha = fecha,
        descripcion =descripcion ,
        imagen = imagen ?: "",
        descripcionPlus =descripcionPlus

        )
    return e
}
