# Twitter
Para la resolución del [challenge de Ualá](./docs/challenge.md) se desarrolló un API Rest en java con los siguientes métodos:
* /time-line/{userId} | GET : para obtención del TimeLine de un usuario (paginado).
* /tweet/{userId} | POST: para crear un nuevo comentario de un usuario.
* /user/{userId}/start-following/{userIdToFollow} | PUT : para actualizar la información de segimiento de un usuario.

## Instancia para pruebas en la nube
* URL : http://trilla.com.ar/q/swagger-ui/

## Diagrama de Casos de uso

![Diagrama](http://yuml.me/diagram/scruffy/usecase/%5BUser%5D-(Create%20Tweet),%20%5BUser%5D-(Get%20TimeLine),%20%5BUser%5D-(Start%20Following).svg)


## Estructura del proyecto
EL proyecto se divide en tres módulos (Maven): 
  * twitter-domain : clases de dominio y puertos
  * twitter-application: servicios de aplicación
  * twitter-infrastructure: adapdatores (implementación utilizando Quarkus)  

### Clases de dominio y puertos
  * Tweet: representa un comentario (tweet) de un usuario.
  * Page: representa una página de comentarios que se mostrarán en el TimeLine.
  * CreateTweetPort: creación de un nuevo tweet.
  * GetTimeLineForUserPort: obtención del Timeline de un usuario.
  * StartFollowingPort: inicio de seguimiento de un usuario por otro usuario.
  * TimeLineRepository: repositorio para obtención de TimeLine (lectura).
  * TimeLineSyncRepository: para sincronizar los comentarios (CQRS) en el TimeLine (escritura).
  * TweetRepository: repositorio de Tweets (escritura).
  * UserRepository: repositorio de usuarios (información de seguidores).

### Servicios de aplicación
  * CreateTweetService: creación de un nuevo comentario.
  * GetTimeLineForUserService: obtención del TimeLine de un usuario.
  * StartFollowingService: inicio de seguimiento de un usuario por otro usuario.

### Adaptadores
  * SynchronizableTimeLineRepositoryInMemoryImpl: implementación de repositorio de TimeLine tanto para lectura como para sincronización. 
  * TweetProjector: clase auxiliar para la proyección de los comentarios en el repositorio de lectura (sincronización de repositorios).
  * TweetRepositoryInMemoryImpl: implementación de repositorio de comentarios (en memoria).
  * UserRepositoryInMemoryImpl: implementación de repositorio de usuarios (en memoria).


## Tecnologías utilizadas

* Arquitectura : Hexagonal
* Herramientas de gestión y construcción :  Apache Maven (3.9.4)
* Lenguaje de programación : Java (21)
* Framework: Quarkus (3.14.4)

# Ejecución local (perfil dev)

#### Prerequisitos: tener instalado y configurado Java 21 y Apache Maven 3.9.4

* Descargar el proyecto.
* Abrir una terminal o consola en el directorio directorio raiz del proyecto descargado.
* Ejecuetar:
```sh
./mvnw clean install
mvn -pl twitter-infrastructure quarkus:dev
```
* Una vez iniciada la aplicación, navegar a la URL  http://localhost:8080/q/dev-ui/io.quarkus.quarkus-smallrye-openapi/swagger-ui


# Ejecución local (Docker)
#### Prerequisitos: tener instalado y configurado Java 21, Apache Maven 3.9.4 y Docker Cli 26.1.2 
* Descargar el proyecto.
* Abrir una terminal o consola en el directorio directorio raiz del proyecto descargado.
* Ejecuetar:
```sh
./mvnw clean install
mvn -pl twitter-infrastructure quarkus:image-build
docker run -p 8080:8080 alejandrotrilla/twitter-infrastructure:1.0.0-SNAPSHOT
```
* Una vez iniciada la aplicación, navegar a la URL  http://localhost:8080/q/swagger-ui/

# Despliegue en Kubernetes local (Kind, Minikube, Microk8s etc)
#### Prerequisitos: tener instalado y configurado Java 21, Apache Maven 3.9.4, Docker Cli 26.1.2 y kuberctl 1.31.0. Además, debe estar logeado a DockerHub. En el cluster de k8s que esté utilizando, debe tener configurado [Ingress (Nginx)](https://kubernetes.io/docs/concepts/services-networking/ingress/) y [cert-manager](https://cert-manager.io/docs/installation).
* Descargar el proyecto.
* Abrir una terminal o consola en el directorio directorio raiz del proyecto descargado.
* Ejecuetar:
```sh
./mvnw clean install
mvn -pl twitter-infrastructure quarkus:image-build
mvn -pl twitter-infrastructure quarkus:image-push
kubectl create ns uala
kubectl apply -f twitter-infrastructure/target/kubernetes/kubernetes.yml
```
* Agregar al archivo /etc/hosts la siguiente entrada:
```
127.0.0.1       twitter.uala.test
```
* Una vez iniciada la aplicación, navegar a la URL  http://twitter.uala.test/q/swagger-ui


# Despliegue en Kubernetes cloud
#### Prerequisitos: tener instalado y configurado Java 21, Apache Maven 3.9.4, Docker Cli 26.1.2 y kuberctl 1.31.0. Además, debe estar logeado a DockerHub. En el cluster de k8s que esté utilizando, debe tener configurado [Ingress (Nginx)](https://kubernetes.io/docs/concepts/services-networking/ingress/) y [cert-manager](https://cert-manager.io/docs/installation).
* Descargar el proyecto.
* Abrir una terminal o consola en el directorio directorio raiz del proyecto descargado.
* Ejecuetar (reemplazando <DOMINIO> por el nombre de domino que corresponda):
```sh
./mvnw clean install -DHOST=DOMINIO
mvn -pl twitter-infrastructure quarkus:image-build
mvn -pl twitter-infrastructure quarkus:image-push
kubectl create ns uala
kubectl apply -f twitter-infrastructure/target/kubernetes/kubernetes.yml
```
* Una vez iniciada la aplicación, navegar a la URL  http://DOMINIO/q/swagger-ui

