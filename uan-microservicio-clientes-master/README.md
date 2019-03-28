# uan-microservicio-clientes
Microservicio para gestión de clientes.

## Importar
* Clonar e importar en SpringToolSuite como proyecto Maven
* Click derecho sobre el proyecto
* Maven/Update project
* Cambiar en pom.xml credenciales de sonar por las correspondientes
```
<properties>
	<sonar.host.url>xxxxxxxx</sonar.host.url>
	<sonar.login>xxxxxxxx</sonar.login>
</properties>
```
* Cambiar credenciales de MySQL en src/main/resources/application.properties
```
spring.datasource.url=jdbc:mysql://<ENDPOINT>:3306/<NOMBRE-BD>?useSSL=false
spring.datasource.username=<USUARIO>
spring.datasource.password=<CONTRASEÑA>
```

## Ejecutar
* Click derecho sobre el proyecto
* Run As/Spring Boot App
* Acceder a localhost:8080

## API Endpoints
* /health GET
* /create POST
* /findAll GET
* /findById GET
* /update PUT
* /deleteById DELETE
* /delete DELETE