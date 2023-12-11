# ShopSwiftly

An ecommerce with microservices architecutre for the backend.
Un ecommerce con una arquitectura de microservicios para el backend. 

### Info:

Este proyecto contiene una estructura modular, esta diseñado orignialmente para ser desplegado usando Kubernetes en AWS, AZURE, o en tu propio servidor, con tu propio "iron".
Fue adaptado para ser levantado en Render.

# HOW TO RUN

Ir al directorio root, donde se ubica el docker-compose y el pom.xml
Setear tu .env (con las variables de entorno que vas a utilizar)

1. Arrancar
```bash
 mvn clean package 
```
  Una vez que se compilen los paquetes

2. Para crear la imagen y levantar:
```bash
docker-compose up --build
```

Con esto (Si tenes todo instalado) debería funcionar localmente.

## HOW TO RUN PROFILES

Dentro del proyecto, en el pom padre, estan seteados dos perfiles.
Para poder hacerlos funcionar es necesario.
1- Crear los .env file que correspondan al numero de perfiles.
En este caso, como tenemos dos perfiles, serán dos .env files.
.env.dev y .env.prod (es lo mas normal)
En uno de ellos vamos a poner las variables de entorno locales, y en el otro las variables remotas.
Para ejecutar una o la otra utilizamos el CLI:

```bash
 mvn clean package 
```
```bash
docker-compose --env-file .env.dev up (para el perfil de dev)
```
```bash
docker-compose --env-file .env.prod up (para el perfil de prod)
```

Con eso ya deberían tener las diferentes configuraciónes disponibles en su entorno de trabajo.

## CI/CD

Utilizamos GitHub Actions para automatizar.
Actualmente controla que este todo correcto con el deploy a DockerHub. Entonces, cuando suban algo a la rama que tengan, va a hacer los chequeos de manera automatica y va a permitir o no los merges al main, donde ahí se actualiza la imagen en DockerHub.
Desde DockerHub se despliega (manualmente) a Render.
¿Que comprueba especificamente?

1. Que Java Maven compile todo y construya correctamente los paquetes.
2. Que se generen las imagenes correctamente con docker-compose.
3. Que se apliquen los secrets.
4. Que se desplieguen en el orden correcto.

# Manejo de errores

La pipeline tiene un manejo de errores. Se suspende automaticamente cuando encuentre el primer error y tiene los logs listos, para que chequear donde esta fallando.
