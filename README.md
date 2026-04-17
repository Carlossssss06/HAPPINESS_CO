# HAPPINESS&Co: Ecosistema de Gestión y Difusión Cultural

## 📝 Descripción del Proyecto
**HAPPINESS&Co** es una solución integral diseñada para la promoción y gestión de eventos en el Principado de Asturias. El proyecto no solo ofrece una plataforma visual para el usuario final (Frontend), sino que incluye un robusto sistema de backend para la administración de datos, permitiendo gestionar usuarios, eventos, galerías de imágenes y preferencias personales.

Este ecosistema se divide en tres pilares fundamentales:
1.  **Frontend Web:** Interfaz de usuario elegante y moderna.
2.  **Backend Administrativo (Java):** Aplicación de consola para la gestión de la lógica de negocio.
3.  **Persistencia de Datos (SQL):** Base de datos relacional para el almacenamiento seguro de la información.

---

## Arquitectura del Sistema

### 1. Plataforma Web (Frontend)
Diseñada bajo una estética minimalista y profesional, centrada en la experiencia del usuario (UX).
* **Secciones:** Inicio, Próximos Eventos, Historial, Sobre Nosotros, Contacto y Fuentes.
* **Tecnologías:** HTML5, CSS3 (Flexbox/Grid), Tipografía corporativa *Asenine*.
* **Diseño:** Adaptativo (Responsive) y con efectos visuales de transición.

### 2. Sistema de Gestión (Java OO)
Una aplicación orientada a objetos que permite administrar el ciclo de vida de la información.
* **Clases Principales:**
    * `Usuarios.java`: Gestión de credenciales (nombre, email, password).
    * `Eventos.java`: Atributos de fecha, ubicación y descripción técnica.
    * `Galerias.java`: Estructura para organizar recursos visuales vinculados a eventos.
    * `Favoritos.java`: Lógica para la vinculación personalizada entre usuarios y eventos.
* **Funcionalidades del `Main.java`:** Operaciones CRUD (Crear, Leer, Actualizar, Eliminar) mediante menús interactivos y manejo de estructuras de datos como `HashMap` y `ArrayList`.

### 3. Base de Datos (MySQL)
Modelo relacional diseñado para garantizar la integridad de los datos.
* **Tablas:** `usuarios`, `eventos`, `galerias`, `imagenes` y `favoritos`.
* **Relaciones:** Implementación de claves foráneas (FK) para asegurar que, por ejemplo, una galería no exista sin un evento asociado, o que los favoritos se eliminen si el usuario desaparece.
