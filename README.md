# 📚 Literalura - Catálogo de Libros

<p align="left">
   <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white">
   <img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white">
   <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white">
   <img src="https://img.shields.io/badge/JPA%20Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white">
</p>

## 📝 Descripción

Literalura es una aplicación de consola desarrollada en Java utilizando **Spring Boot** y **JPA**. Su objetivo principal es permitir a los usuarios buscar libros, consultar información sobre autores y filtrar contenidos mediante el consumo de la API externa [Gutendex](https://gutendex.com/), persistiendo los datos de interés en una base de datos relacional **PostgreSQL**.

Este proyecto forma parte del desafío de backend de Alura Latam, implementando buenas prácticas de programación, manejo de excepciones y construcción de consultas JPQL.

## ⚙️ Funcionalidades

El sistema cuenta con un menú interactivo que permite:

1.  **🔍 Buscar libros por título:** Conecta con la API de Gutendex, busca el libro y muestra sus datos (título, autor, idioma, descargas).
2.  **💾 Registrar libros:** Permite guardar los libros buscados en la base de datos local para futuras consultas sin necesidad de internet.
3.  **📋 Listar libros registrados:** Muestra todos los libros que han sido guardados en la base de datos.
4.  **👥 Buscar libros por autor:** Filtra los libros guardados coincidiendo con el nombre del autor.
5.  **📅 Listar autores vivos en un año determinado:** Algoritmo que consulta la base de datos filtrando autores que estaban vivos en el año ingresado (calculando nacimiento y fallecimiento).
6.  **🗣️ Listar libros por idioma:** Búsqueda inteligente que soporta códigos ISO (es, en) y nombres comunes (Español, Inglés), filtrando los resultados de la base de datos.

## 🛠️ Tecnologías Utilizadas

* **Java 17** - Lenguaje principal.
* **Spring Boot 3** - Framework para el desarrollo de la aplicación.
* **Spring Data JPA** - Para la persistencia y consultas a la base de datos.
* **PostgreSQL** - Motor de base de datos relacional.
* **Maven** - Gestor de dependencias.
* **Jackson** - Para la deserialización de datos JSON provenientes de la API.
* **Gutendex API** - Fuente de datos externa.

## 🚀 Cómo ejecutar el proyecto

### Prerrequisitos
* Java JDK 17 o superior.
* Maven.
* PostgreSQL instalado y ejecutándose.

### Pasos

1.  **Clonar el repositorio:**
    ```bash
    git clone [https://github.com/TU_USUARIO/literalura.git](https://github.com/TU_USUARIO/literalura.git)
    ```

2.  **Configurar la Base de Datos:**
    En tu gestor de PostgreSQL (pgAdmin o terminal), crea una base de datos llamada `literalura` (o el nombre que prefieras).

3.  **Variables de Entorno:**
    Configura el archivo `src/main/resources/application.properties` con tus credenciales de base de datos:
    
    ```properties
    spring.application.name=literalura
    spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
    spring.datasource.username=TU_USUARIO_POSTGRES
    spring.datasource.password=TU_CONTRASEÑA
    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true
    ```

4.  **Ejecutar la aplicación:**
    Desde tu IDE (IntelliJ IDEA / Eclipse) o mediante terminal:
    ```bash
    mvn spring-boot:run
    ```

## 📸 Ejemplos de Uso

### Búsqueda por Idioma
El sistema es capaz de interpretar diferentes entradas para el idioma:
* Entrada: `español` -> Busca código `es`
* Entrada: `ingles` -> Busca código `en`

### Autores Vivos
Al ingresar un año (ej. 1600), el sistema ejecuta una consulta JPQL compleja para determinar qué autores estaban vivos en ese periodo específico, considerando fechas de nacimiento y defunción.

## 👤 Autor

Desarrollado por Alan Salazar como parte de la formación en desarrollo Backend Java para Alura Latam y Oracle G9.

---
