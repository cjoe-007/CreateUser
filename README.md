# CreateUser

Revisar CreateUser.docx se encuentra con imágenes.

En esta primera versión se tiene la creación de un usuario. Consta de 1 parte 

1. micoroservicios_create_user= es la parte back end desarrollado con Java 8, IDE Eclipse, jpa, base de datos en memoria hdbsql, Spring Boot, Servidor tomcat.

Para ejecutar bajar el proyecto y tener libre el puerto 9000. 

Empaquetado y ejecución del microservicio;

 1. Bajamos el proyecto "micoroservice_create_user " en el IDE eclipse dar clic derecho sobre el proyecto, ir hacia “Run As”, escoger la 4 opción maven build.

 

2. En la opción Goal poner deploy y dar clic en Run

 

3. Se ejecutará construirá  el jar.

4. En la carpeta target se creará el jar  micoroservicios_create_user.jar.
 


5. Este jar lo podemos ubicar en cualquier carpeta y con una terminal de comando ejecutamos 
java -jar micoroservicios_create_user-0.0.1-SNAPSHOT.jar


 

6. Una vez que se encuentre arriba el microservicio nos dirigimos postman e importamos en raw el siguiente curl

curl --location --request POST 'http://localhost:9000/createUser' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=16478E54F319F8B20B288B9912D1EE7B' \
--data-raw '{
    "name":"Juan Rodriguez",
    "email":"juan@rodriguez2.org",
    "password":"HuntEr#/22",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "countrycode": "57"
        }
    ]
}
'

 

6. Los datos de base se encuentra en data.sql.
/microservice_create_user/src/main/resources/data.sql

 

7. Para configurar el password ubicarse en el archivo ValidatePassword.java y modificar los valores MIN_Uppercase validara cuantas letras mayúsculas debe tener clave.
/microservice_create_user/src/main/java/com/create/user/interceptor/ValidatePassword.java

 


 
