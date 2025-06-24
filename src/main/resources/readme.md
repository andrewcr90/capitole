# Capitole Pricing Service

Este es un microservicio REST desarrollado en Spring Boot que permite consultar el precio final (PVP) aplicable a un producto, dada una fecha de aplicación, un identificador de producto y un identificador de cadena (brand).

##  Descripción

La base de datos contiene una tabla `PRICES` con información de tarifas aplicables a productos dentro de un rango de fechas. Si existen múltiples tarifas aplicables, se selecciona la de mayor prioridad.

### Entrada:
- `applicationDate`: Fecha y hora en formato ISO (e.g. `2020-06-14T16:00:00`)
- `productId`: Identificador del producto
- `brandId`: Identificador de la cadena (1 = ZARA)

### Salida:
- `productId`: ID del producto
- `brandId`: ID de la cadena
- `priceList`: ID de la tarifa aplicable
- `startDate`: Fecha de inicio de la tarifa
- `endDate`: Fecha de fin de la tarifa
- `price`: Precio final a aplicar

---

## Tecnologías utilizadas

- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 Database (en memoria)
- Swagger/OpenAPI 3
- JUnit 5 + Spring Test
- Gradle

---

## Cómo ejecutar el proyecto

1. Clona el repositorio:
    
   git clone https://github.com/andrewcr90/capitole.git
   cd capitole

2. Ejecuta el proyecto:
   ./gradlew bootRun

3. Accede a Swagger UI:
   http://localhost:8080/swagger-ui/index.html

## Endpoints 
GET /prices/getPrice
Consulta el precio aplicable:
- `applicationDate`: Fecha y hora en formato ISO (e.g. `2020-06-14T16:00:00`)
- `productId`: Identificador del producto
- `brandId`: Identificador de la cadena (1 = ZARA)

- Respuesta exitosa (200 OK)
  {
  "productId": 35455,
  "brandId": 1,
  "priceList": 2,
  "startDate": "2020-06-14T15:00:00",
  "endDate": "2020-06-14T18:30:00",
  "price": 25.45
  }

- Si no hay resultados (404 Not Found)
  json
  Copiar
  Editar
  {
  "status": 404,
  "message": "Price not found for given parameters"
  }
## Autor
Javier Andrés Ceballos Rodríguez
 Desarrollador Java Senior