# Plataforma de Gestión de Música - Proyecto Java

## Descripción del Proyecto

Nos han encargado desarrollar una aplicación para la **gestión de una plataforma de streaming de música**.

## Datos del Sistema

### Usuario

De cada usuario se registran los siguientes datos personales:

- **Nombre**
- **Apellidos** (cada uno por separado)
- **Fecha de nacimiento**
- **Fecha de alta** en la plataforma
- **Email** (único)
- **Contraseña** (mínimo 8 caracteres, 1 mayúscula, 1 minúscula y 1 dígito)
- **Tarjeta bancaria**:
  - Número
  - Fecha de caducidad (mes y año)
  - Nombre del titular
  - CVV

### Canción

Cada canción contiene:

- **Código** (número entero, único)
- **Título**
- **Artista/s**:
  - Código (número entero, único)
  - Nombre
  - Fecha de nacimiento
  - País de origen
- **Álbum** (nombre)
- **Género/s** (enumeración: rock, pop, electrónica, disco, trap, k-pop, …)
- **Duración** (en segundos)
- **Fecha de lanzamiento**

### Historial y Playlists

- Los usuarios tienen un **historial de reproducción** asociado con las canciones (en orden), guardando:
  - Fecha y hora de reproducción
  - Segundo en el que se detuvo la canción
- Los usuarios pueden crear **playlists** (conjuntos ordenados de canciones) con un **título** (ej: "Canciones para viajar", "Canciones tristes", etc.).

## Funcionamiento de la Aplicación

1. Al arrancar la aplicación, el usuario podrá **darse de alta** o **iniciar sesión**.
2. Si se registra un nuevo usuario, se guardan sus datos y se vuelve al menú principal.
3. Para iniciar sesión se solicita **email** y **contraseña**.
4. Si la autenticación falla, se vuelve al menú principal.
5. Una vez autenticado, el usuario puede realizar las siguientes operaciones:

### Menú Principal (Usuario Logueado)

- **a.** Buscar canciones por cadena (búsqueda case-insensitive en el título).
- **b.** Reproducir una canción por código (indicar segundo donde parar o duración total).
- **c.** Reproducir una playlist completa (introducir `-1` para terminar).
- **d.** Crear una playlist (título + códigos de canciones, terminar con `-1`).
- **e.** Borrar una playlist por título.
- **f.** Ver la **canción más escuchada** (y en caso de empate, todas).
- **g.** Ver el **artista más escuchado** (y en caso de empate, todos).
- **h.** Ver el **género más escuchado** (y en caso de empate, todos).
- **i.** Cerrar sesión (guardar datos y salir).

## Requisitos Técnicos Importantes

- Los **datos personales de los usuarios** se guardarán en un archivo con **acceso directo o aleatorio**.
- Los **datos de las canciones** y **artistas** se guardarán en archivos con la estructura que consideres más adecuada.
- El **historial de reproducción** de cada usuario se guardará en el formato que elijas.
- Las **playlists** de cada usuario se guardarán en un archivo con estructura **XML**.
- Se debe utilizar **al menos una clase con tipo de dato genérico**.
- Uso recomendado de **Streams** para funciones agregadas (conteos, máximos, filtros, etc.).
- Uso adecuado de **excepciones propias**.
- **Buen formateo** y experiencia de usuario en consola (claridad y estructura).
- Correcto uso de las colecciones de Java (`List`, `Set`, `Map`, etc.).

---
## Tecnologías y Buenas Prácticas

- Java (JDK 17 o superior recomendado)
- Programación Orientada a Objetos
- Persistencia en ficheros
- XML para playlists
- Excepciones personalizadas
- Streams y lambdas
- Código limpio y comentado

---

**Proyecto desarrollado como práctica final de Programación / Acceso a Datos.**