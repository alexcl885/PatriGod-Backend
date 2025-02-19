package data.usuario.inmemory.models

import domain.usuario.models.Usuario

object UsuarioData {
    val listUsuarios = mutableListOf<Usuario>(
        Usuario(
            dni = "26531235L",
            email = "alexcopado@gmail.com",
            password = "copado",
            nombre = "Alejandro",
            phone = "610864403",
            token = ""
        ),
        Usuario(
            dni = "12345678A",
            email = "maria.gomez@example.com",
            password = "maria123",
            nombre = "María",
            phone = "611223344",
            token = ""
        ),
        Usuario(
            dni = "87654321B",
            email = "juan.perez@example.com",
            password = "juan456",
            nombre = "Juan",
            phone = "622334455",
            token = ""
        ),
        Usuario(
            dni = "23456789C",
            email = "laura.martin@example.com",
            password = "laura789",
            nombre = "Laura",
            phone = "633445566",
            token = ""
        ),
        Usuario(
            dni = "34567890D",
            email = "carlos.rodriguez@example.com",
            password = "carlos159",
            nombre = "Carlos",
            phone = "644556677",
            token = ""
        ),
        Usuario(
            dni = "45678901E",
            email = "ana.lopez@example.com",
            password = "ana753",
            nombre = "Ana",
            phone = "655667788",
            token = ""
        ),
        Usuario(
            dni = "56789012F",
            email = "pedro.garcia@example.com",
            password = "pedro852",
            nombre = "Pedro",
            phone = "666778899",
            token = ""
        ),
        Usuario(
            dni = "67890123G",
            email = "elena.sanchez@example.com",
            password = "elena369",
            nombre = "Elena",
            phone = "677889900",
            token = ""
        )
    )

}