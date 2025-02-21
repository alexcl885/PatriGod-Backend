

# 🏛️ Patrigod - Backend

---
Bienvenido a **Patrigod**, el backend desarrollado para la asignatura **Programación Multimedia y Dispositivos Móviles**. Este proyecto ha sido diseñado para proporcionar una solución robusta y escalable que gestiona de forma segura la información de usuarios y monumentos.

## ¿Qué es Patrigod?

**Patrigod** es una API REST que ofrece funcionalidades clave para:
- **Gestión de Usuarios:** Registro, autenticación y administración de la información de los usuarios.
- **Gestión de Monumentos:** Operaciones CRUD (Crear, Leer, Actualizar y Eliminar) para la administración de monumentos.

## Características Principales

- **Autenticación Segura:**  
  Utiliza **JSON Web Tokens (JWT)** para garantizar que solo los usuarios autenticados puedan acceder a los endpoints protegidos.

- **Operaciones CRUD Completo:**  
  Permite la creación, consulta, actualización y eliminación de monumentos, facilitando una gestión dinámica y eficaz de la información.

- **Arquitectura Sólida y Escalable:**  
  Construido en **Kotlin** utilizando el framework **Ktor**, el backend sigue los principios de **Clean Architecture**. Esto se traduce en:
  - **Modularidad:** Cada componente tiene una responsabilidad claramente definida.
  - **Escalabilidad:** La estructura del código permite la integración de nuevas funcionalidades sin afectar la estabilidad del sistema.
  - **Mantenibilidad:** Una separación clara de responsabilidades facilita la actualización y mejora continua del sistema.

Patrigod es un ejemplo práctico de la aplicación de buenas prácticas en el desarrollo de sistemas backend, combinando tecnología moderna y una arquitectura robusta para satisfacer las necesidades de aplicaciones complejas y en constante crecimiento.


---

## 🚀 Tecnologías Usadas

- **Kotlin** - Lenguaje principal del backend.
- **Ktor** - Framework para construir aplicaciones backend en Kotlin.
- **JWT (JSON Web Tokens)** - Para autenticación segura.
- **Clean Architecture** - Para una organización clara y mantenible del código.
- **MariaDB** (usando **Docker**) - Base de datos para almacenar usuarios y monumentos.
- **Serialización JSON** - Para gestionar las respuestas de la API.


---

## 📂 Estructura del Proyecto
```plaintext
main
│── kotlin
│   ├── data                 # Capa de datos (repositorios, DAOs, acceso a BD)
│   │   ├── monumento
│   │   ├── usuario
│   ├── domain               # Capa de dominio (casos de uso, lógica de negocio)
│   │   ├── monumento
│   │   ├── usuario
│   ├── ktor                 # Configuración del servidor
│   │   ├── routing          # Rutas de la API
│   │   ├── Application.kt   # Configuración principal del servidor
│   │   ├── Database.kt      # Configuración de la base de datos
│   │   ├── Routing.kt       # Definición de rutas
│   │   ├── Security.kt      # Configuración de seguridad y autenticación
│   │   ├── Serialization.kt # Configuración de serialización
│── resources                 # Archivos de configuración
```

## 📌 Endpoints Implementados

### 🔐 Autenticación

| Método  | Ruta              | Descripción                         | Autenticación |
|---------|------------------|-------------------------------------|--------------|
| `POST`  | `/auth`          | Iniciar sesión y obtener token     | ❌ No        |
| `POST`  | `/register`      | Registrar un nuevo usuario         | ❌ No        |
| `GET`   | `/usuario/{dni}` | Obtener datos del usuario por DNI  | ✅ Sí (JWT)  |

### 🏛️ Monumentos

| Método  | Ruta                | Descripción                     | Autenticación |
|---------|--------------------|---------------------------------|--------------|
| `GET`   | `/monumentos`       | Obtener todos los monumentos   | ✅ Sí (JWT)  |
| `GET`   | `/monumentos/{id}`  | Obtener un monumento por ID    | ✅ Sí (JWT)  |
| `POST`  | `/monumentos`       | Agregar un nuevo monumento     | ✅ Sí (JWT)  |
| `PATCH` | `/monumentos/{id}`  | Actualizar un monumento        | ✅ Sí (JWT)  |
| `DELETE`| `/monumentos/{id}`  | Eliminar un monumento          | ✅ Sí (JWT)  |

## 🔑 Autenticación y Seguridad

La API utiliza **JSON Web Tokens (JWT)** para autenticar a los usuarios.

Para acceder a los endpoints protegidos, debes incluir el token en el **header** de la solicitud:

```http
Authorization: Bearer <TOKEN>
```

# Documentación sobre los  Casos de Uso

Este documento describe los casos de uso que gestionan la lógica de negocio para **usuarios** y **monumentos** dentro del sistema.

---

## 👥 Provider de Usuarios (`ProviderUseCase`)

El objeto `ProviderUseCase` es el encargado de gestionar todas las operaciones relacionadas con los **usuarios** en el sistema. Se apoya en un repositorio de persistencia para acceder y manipular los datos de los usuarios en la base de datos, encapsulando toda la lógica de negocio necesaria para cada operación.

### 📚 Estructura y Dependencias

- **Repositorio:**  
  Utiliza `PersistenceUsuarioRepository` para interactuar con la capa de datos.

- **Modelos:**  
  - **`Usuario`**: Representa la entidad usuario en el sistema.  
  - **`UpdateUsuario`**: Modelo utilizado para actualizar o crear usuarios.

- **Logger:**  
  Se utiliza `LoggerFactory.getLogger("UsuarioUseCaseLogger")` para registrar mensajes de advertencia e información relevante durante la ejecución de los casos de uso.

- **Casos de Uso Internos:**  
  Los casos de uso encapsulados en este proveedor incluyen:
  - **Obtener todos los usuarios**
  - **Obtener un usuario por DNI**
  - **Realizar login**
  - **Registrar un nuevo usuario**
  - **Actualizar la información de un usuario**
  - **Buscar usuarios por email**
  - **Eliminar un usuario**

---

## 🏛️ Monumento Provider (`MonumentoProviderUseCase`)

El objeto `MonumentoProviderUseCase` se encarga de gestionar las operaciones relacionadas con los **monumentos** en el sistema. Hace uso del repositorio `PersistenceMonumentoRepository` para interactuar con la capa de persistencia y ejecutar la lógica de negocio necesaria para las operaciones de actualización, obtención, eliminación e inserción de monumentos.

### 📚 Dependencias y Estructura

- **Repositorio:**  
  - `PersistenceMonumentoRepository` para el acceso a los datos de los monumentos.

- **Modelos:**  
  - **`Monumento`**: Representa la entidad monumento en el sistema.  
  - **`UpdateMonumento`**: Modelo utilizado para actualizar los datos de un monumento.

- **Logger:**  
  Se utiliza el logger definido en `ProviderUseCase.logger` para registrar advertencias y mensajes relevantes durante la ejecución de los casos de uso.

- **Casos de Uso Internos:**  
  Este proveedor agrupa varios casos de uso, tales como:
  - **`UpdateMonumentoUseCase`**: Actualiza un monumento existente.
  - **`GetMonumentoByIdMonuUseCase`**: Obtiene un monumento a partir de su ID.
  - **`DeleteMonumentoUseCase`**: Elimina un monumento.
  - **`GetAllMonumentosUseCase`**: Recupera todos los monumentos registrados.
  - **`PostMonumentoUseCase`**: Inserta un nuevo monumento.

