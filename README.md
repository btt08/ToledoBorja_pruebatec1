# ToledoBorja_pruebatec1

## Introducción
En este proyecto se realiza la prueba técnica del módiglo Java Básico como parte de la realización del bootcamp Java Backend - HackABoss - Softtek.

## Descripción
La aplicación trata de imitar una gestión de empleados de una empresa con un sistema CRUD (alta, lectura, actualización y eliminación).
A través de la consola y con ayuda de un menú se puede navegar por las diferentes opciones. Tal como se indica más abajo en los requisitos técnicos, además de las opraciones básicas CRUD, también se incluye una búsqueda de empleados por su cargo en la empresa.

## Requisitos técnicos

1. Utiliza Java para el desarrollo de la aplicación.

2. Debes utilizar estructuras de control para la validación de datos de entrada, como asegurarte de que los campos obligatorios no estén vacíos (obligatorio) y que los valores sean válidos (opcional).

3. La aplicación debe utilizar JPA para acceder a la base de datos. Asegúrate de configurar una fuente de datos y una entidad "Empleado" con las propiedades necesarias.

4. Utiliza colecciones para gestionar los datos antes de interactuar con la base de datos.

#### Requisitos extra

1. En caso de que sepas manejar excepciones te proponemos el siguiente punto extra:

2. La aplicación debe ser capaz de manejar errores y excepciones de manera apropiada, como la gestión de registros duplicados o la búsqueda de empleados que no existen, etc.

## Instalación y uso del proyecto
Para lanzar el proyecto se deben seguir los siguientes pasos:
1. Clonar o descargar este repositorio
2. Importarlo en el IDE que se quiera utilizar. El proyecto fue realizado con Netbeans 17
3. Realizar un build del proyecto para asegurarse de que el JDK 17 y las demás dependencias Maven se instalen correctamente
4. Crear la base de datos "empleados" en MySQL
5. Ejecutar [el script SQL](ToledoBorja_pruebatec1/src/main/resources)
6. Lanzar el proyecto con el botón correspondiente del IDE (F6 por defecto en Netbeans)

Para maniobrar entra las opciones se ha de hacer foco en la consola del IDE para evitar problemas.


## Documentación
Se ha generado un JavaDoc con la documentación del proyecto.
Se puede encontrar [aquí](ToledoBorja_pruebatec1/target/site/apidocs).

En [esta carpeta](ToledoBorja_pruebatec1/target/site/apidocs/resources) se ha añadido un archivo con capturas de pantalla de la aplicación.