# Java Coach API
Java Coach es un proyecto API REST con Spring Boot, Hibernate-JPA y MySQL.

Su propósito es generar una base de datos con contenido de estudio para el exámen de certificación Java de Oracle.
## Acerca de
Esta API es el Trabajo Practico Final de la cursada de Java Backend en ADA ITW (Comisión 8).

Hace uso de los siguientes recursos aprendidos a lo largo del curso:
- [x]  Spring Boot
- [x]  MySQL
- [x]  JPA (Java Persistence API)
- [x]  Patrones de Diseño: DTO, Entity, Service, Repository
- [x]  Patron de Arquitectura: MVC
- [x]  Documentación con Swagger/Open API
- [x]  Logger
- [x]  ModelMapper
- [x]  Excepciones
- [x]  Lombok
- [x]  Java Mail Sender
- [x]  Creación de PDF (itextpdf)
## Configuración
- Crear Mysql database
```sql
  create database java-coach-api
```
- Crear el `src/main/resources/application.properties`como el siguiente ejemplo
```.properties
server.error.whitelabel.enabled=true
# Configuración DB
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/java_coach_api?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval\=true
spring.datasource.username=[MYSQL_USER]
spring.datasource.password=[MYSQL_PASSWORD]
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
# Configuración Java Mail (gmail)
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.username=[GMAIL_USER]
spring.mail.password=[GMAIL_APP_PASSWORD]
```
- La app comenzará en http://localhost:8080.

## Documentación API
Esta API fue documentada con Open API. Para verla, ir a http://localhost:8080/swagger-ui/index.html/.
## Entregables
- Script `.entregables/data.sql`
- Colección de Postman `.entregables/Java Coach API.postman_collection.json`

