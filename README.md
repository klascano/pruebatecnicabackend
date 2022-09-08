# Prueba tecnica api
Proyecto apis para prueba tecnica.

> Tildaciones y acentos son explicitamente excluidos para una mejor visualizacion de esta documentacion.

> No se incluye en esta documentacion instalacion y configuracion de herramientas, servicios e IDES para desarrollo.

## Software

 1. [Openjdk 8](https://openjdk.org/projects/jdk8/)
 2. [Springboot](https://spring.io/projects/spring-boot/)
 3. [Gradle 7](https://gradle.org/releases/)			
 4. [Docker](https://www.docker.com/)
 5. [Mongodb Atlas](https://www.mongodb.com/es/cloud/atlas/efficiency)
 6. [Eclipse 2021 o superior](https://www.eclipse.org/downloads/)


## Estructura de directorios

 - app: Proyecto api

## Repositorio de datos
**No se requiere MongoDb instalado localmente.** Se utiliza MongoDb Atlas. Servicio auto	gestionado para almacenamiento de documentos en version gratis con limite de 2Gb.
La cadena de conexion esta establecida directamente en el application.properties
Por defecto se utiliza usuario/contraseña para cluster mongo atlas: klascano/klascano para las operaciones de datos.

## Apis
El proyecto api se encuentra en app/vacunaapi.

Para compliar

    cd app/api
	gradle build

Para ejecutar con gradle

    cd app/vacunaapi
    gradle bootRun
	
Si todo esta correcto debemos tener una salida que se muestra a continuacion:

    2022-09-08 10:41:32.791  INFO 47580 --- [  restartedMain] c.f.p.pruebatecnica.api.ApiApplication   : Starting ApiApplication using Java 1.8.0_202 on klopc with PID 47580 (/home/klascano/BESIX/GIT/proamerica/pruebatecnicabackend/app/api/build/classes/java/main started by klascano in /home/klascano/BESIX/GIT/proamerica/pruebatecnicabackend/app/api)
    2022-09-08 10:41:32.791  INFO 47580 --- [  restartedMain] c.f.p.pruebatecnica.api.ApiApplication   : No active profile set, falling back to 1 default profile: "default"
    2022-09-08 10:41:32.811  INFO 47580 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
    2022-09-08 10:41:32.811  INFO 47580 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
    2022-09-08 10:41:33.032  INFO 47580 --- [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data MongoDB repositories in DEFAULT mode.
    2022-09-08 10:41:33.052  INFO 47580 --- [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 18 ms. Found 2 MongoDB repository interfaces.
    2022-09-08 10:41:33.292  INFO 47580 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 2090 (http)
    2022-09-08 10:41:33.297  INFO 47580 --- [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
    2022-09-08 10:41:33.297  INFO 47580 --- [  restartedMain] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.65]
    2022-09-08 10:41:33.322  INFO 47580 --- [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
    2022-09-08 10:41:33.322  INFO 47580 --- [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 511 ms
 
## Docker

Para construir una imagen y que se registre localmente

    gradle docker

Otros comandos utiles para administracion

    dockerRun             #crea contenedor y ejecuta
    dockerRunStatus       #verifica estado de contenedor
    dockerStop            #detiene el contenedor
    dockerRemoveContainer #remueve contenedor

Para reiniciar contenedor

	#con gradle
    gradle dockerRemoveContainer
    gradle dockerRun

	#con docker
	docker stop proamerica-api
	docker start proamerica-api

Para verificar logs del contenedor

    docker logs -f proamerica-api

### Ejecutar sin gradle
En caso que no se tenga instalado ni configurado gradle. Se requiere instalado docker.
	
	#descomprimir imagen
	unzip imagen/cr.fi.proamerica.pruebatecnica.api.zip
	
	#cargar imagen al registro local docker
	docker load -i cr.fi.proamerica.pruebatecnica.api.tar

	#crear contenedor
	docker run --name proamerica-api -p 2090:2090 -d cr.fi.proamerica.pruebatecnica.api:0.0.1

## Documentacion apis
Las apis disponibles son:

 1. Registrar 
	 * http://{{vServer}}:2090/register
	 * POST / No requiere rol ni token
	 * Genera un usuario con rol admin para acceso a los servicios
 2. Autenticar 
	 * http://{{vServer}}:2090/authenticate
	 * GET / No requiere rol ni token
	 * Genera un token valido para las otras llamadas. Dentro del token se encuentra el claim "rol"
 3. Todos 
	 * http://{{vServer}}:2090/idocs
	 * GET / Requiere token con rol admin
 4.  Leer
	 * http://{{vServer}}:2090/idoc/{{number}}
	 * GET / Requiere token con rol admin
 5. Crear
	 * http://{{vServer}}:2090/idocs
	 * POST / Requiere token con rol admin
 6. Editar 
	* http://{{vServer}}:2090/idocs
	* PUT / Requiere token con rol admin
 7. Borrar
	* http://{{vServer}}:2090/idoc/{{number}}
	* PUT / Requiere token con rol admin


Para mayor informacion y detalle consultar la documentacion postman:

[Documentacion api](https://documenter.getpostman.com/view/10331412/VVBXw5mY)

## Soporte tecnico
kleverlascano@gmail.com / 5930983739079