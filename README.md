# Parcial-1Concurrente (Roberto.Quilez y Marcos.García)

Este proyecto es una aplicación desarrollada en Java utilizando Spring Boot, con una base de datos MySQL y una interfaz de usuario en React. A continuación, se describe brevemente el funcionamiento del programa y las referencias que componen el directorio `src` (Backend).

## Descripción General

La aplicación simula un sistema de producción concurrente que maneja componentes y bolas, asignando valores de distribución a las bolas, permitiendo la visualización de estos valores en tiempo real a través de un streaming de eventos del lado del servidor (SSE) para formar la maquina de Galton que con las bolas formará una campana de Gaussiana.

## Estructura del Proyecto

### `src/main/java/org/example/parcial1concurrente`

#### `Parcial1ConcurrenteApplication.java`
Este es el punto de entrada de la aplicación Spring Boot. Configura la aplicación para que soporte operaciones asíncronas y lanza los servicios de producción, ensamblaje y asignación de valores de distribución al iniciar.

#### `domain/Bola.java`
Define la entidad `Bola` con sus atributos y anotaciones JPA para mapearla a la tabla `bolas` en la base de datos.

#### `domain/Componente.java`
Define la entidad `Componente` con sus atributos y anotaciones JPA para mapearla a la tabla `componentes` en la base de datos.

#### `repos/BolaRepository.java`
Interfaz que extiende `JpaRepository` para proporcionar métodos CRUD para la entidad `Bola`.

#### `repos/ComponenteRepository.java`
Interfaz que extiende `JpaRepository` para proporcionar métodos CRUD para la entidad `Componente`, incluyendo un método personalizado para buscar componentes por tipo.

#### `rest/ComponenteController.java`
Controlador REST que expone endpoints para obtener componentes de tipo "Clavo", "Contenedor" y todas las bolas.

#### `rest/ProduccionController.java`
Controlador REST que expone endpoints para iniciar la producción de componentes, ensamblar la máquina y asignar valores de distribución a las bolas.

#### `rest/VisualizacionController.java`
Controlador REST que expone un endpoint para iniciar el streaming de valores de distribución de las bolas utilizando SSE.

#### `service/ProduccionService.java`
Servicio que maneja la lógica de negocio para producir componentes, ensamblar la máquina y asignar valores de distribución a las bolas. Utiliza colas de bloqueo para manejar la concurrencia.

### `src/main/resources`

#### `application.properties`
Archivo de configuración principal de Spring Boot.

#### `application-mysql.properties`
Archivo de configuración específico para la base de datos MySQL.

#### `DatosDistribucionNormal.CSV`
Archivo CSV que contiene los valores de distribución que se asignarán a las bolas.

### `docker-compose.yml`
Archivo de configuración para Docker Compose que define un servicio MySQL para la base de datos.

## Ejecución

Para ejecutar la aplicación, asegúrate de tener Docker y Docker Compose instalados. Luego, ejecuta el siguiente comando en el directorio raíz del proyecto:

```sh
docker-compose up
```

Esto levantará un contenedor MySQL. Luego, puedes iniciar la aplicación Spring Boot desde tu IDE o utilizando Maven:

```sh
mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`.

## Endpoints

- **GET** `/api/componentes/clavos`: Obtiene componentes de tipo "Clavo".
- **GET** `/api/componentes/contenedores`: Obtiene componentes de tipo "Contenedor".
- **GET** `/api/componentes/bolas`: Obtiene todas las bolas.
- **GET** `/api/produccion/producir`: Inicia la producción de componentes.
- **GET** `/api/produccion/ensamblar`: Inicia el ensamblaje de la máquina.
- **GET** `/api/produccion/asignar-valores`: Asigna valores de distribución a las bolas.
- **GET** `/api/visualizacion/stream`: Inicia el streaming de valores de distribución de las bolas.

