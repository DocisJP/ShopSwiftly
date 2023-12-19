# Plataforma SaaS ShopSwiftly

Para leer este documento en inglés, haga clic [aquí](README.EN.BackEnd.md).

## Introducción y Visión General

ShopSwiftly ofrece una plataforma de comercio electrónico personalizable como servicio, diseñada para empoderar a los clientes con sus propias soluciones de venta a medida. La plataforma aprovecha una arquitectura de microservicios para permitir un desarrollo rápido, escalabilidad a demanda y eficiencia en costos.

## Puntos de Venta Únicos

### Monorepo Escalable: Simplifica el escalado a través de un monorepo administrado por Docker-compose.

### Ágil y Económico: Facilita las prácticas de desarrollo ágil y minimiza los costos a largo plazo.

### Terminación SSL y Agregación de Solicitudes: Mejora la seguridad y el rendimiento a través de la API Gateway.

## Descripción de Microservicios

### API Gateway

La API Gateway es el punto de entrada principal para todas las solicitudes de los clientes, dirigiéndolas al servicio adecuado y gestionando la terminación SSL, la agregación de solicitudes, la interrupción de circuitos y las reversiones.

### Auth Service

Este servicio maneja la autenticación en toda la plataforma, emitiendo y validando tokens JWT para asegurar el acceso seguro a los servicios de lógica empresarial.

### Config Server

Un gestor centralizado de configuración, el Config Server proporciona a los microservicios las configuraciones necesarias sin configuraciones codificadas.

### Eureka Server

Actuando como el registro de descubrimiento de servicios, permite que los microservicios localicen y se comuniquen entre sí de manera dinámica.

### Servicios de Lógica Empresarial Core

- Product Service: Gestiona la información y el inventario de productos.
- User Service: Maneja los perfiles de usuario y los detalles de autenticación.
- Transaction Service: Procesa y registra las transacciones.

## Diseño de Bases de Datos

Cada microservicio opera con su propio esquema en PostgreSQL, asegurando la encapsulación de datos y la independencia.

## Contenedorización y Configuración del Entorno

Los contenedores Docker encapsulan los microservicios, simplificando la implementación, el escalado y la gestión del entorno. Diferentes archivos .env (dev y prod) permiten configuraciones flexibles específicas del entorno.

## Estructura del Repositorio

El repositorio adopta una estrategia de monorepo, con todos los microservicios alojados dentro de un único repositorio. Este enfoque simplifica el control de versiones y la coordinación entre servicios.

## Primeros Pasos

### Configuración Local

Navega al directorio raíz que contiene los archivos Docker-compose y el POM padre.
Establece los archivos .env.dev y .env.prod de acuerdo con el entorno requerido.
Compila el proyecto utilizando Maven con el comando:

```bash
 mvn clean package
```

Construye e inicia los servicios con Docker-compose:

```bash
docker-compose up --build
```

Para microservicios individuales, usa:

```bash
docker-compose build <service-name>
```

```bash
docker-compose up <service-name>
```

## Prerrequisitos

Maven
Entorno Linux con Docker o Docker sobre WSL

## Uso

Aunque la plataforma no está abierta para colaboración externa, ofrece una API completa documentada dentro de cada microservicio.

## CI/CD

Utilizamos GitHub Actions para automatizar.
Actualmente controla que este todo correcto con el deploy a DockerHub. Entonces, cuando suban algo a la rama que tengan, va a hacer los chequeos de manera automatica y va a permitir o no los merges al main, donde ahí se actualiza la imagen en DockerHub.
Desde DockerHub se despliega (manualmente) a Render.
¿Que comprueba especificamente?

1. Que Java Maven compile todo y construya correctamente los paquetes.
2. Que se generen las imagenes correctamente con docker-compose.
3. Que se apliquen los secrets.
4. Que se desplieguen en el orden correcto.

### Manejo de errores

La pipeline tiene un manejo de errores. Se suspende automaticamente cuando encuentre el primer error y tiene los logs listos, para que chequear donde esta fallando.
