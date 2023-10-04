
## Autor

- [@dcaceres](https://github.com/davidacaceres)


## Organización de codigo:

Estructura de paquetes:

![Imagen](https://github.com/davidacaceres/ey-desafio/blob/main/doc/organizacion_paquetes.png)



# Desafio EY

Implementacion de microservicio restfull que esta compuesto por los paquetes:


## API referencia

#### Obtiene lista de usuarios

```http
  GET /api/v1/usuario
```
Retorno:
```json

[
    {
        "uuid": "3ff1d8d2-3d45-4315-946f-7790316c7767",
        "name": "David Caceres",
        "email": "dcaceres@gmai1l.com",
        "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkY2FjZXJlc0BnbWFpMWwuY29tIiwibmJmIjoxNjk2Mzg1OTQxLCJleHAiOjE2OTY0NzIzNDF9.1xRyQGR0qjqGgpov_NA1f5QiSKUoTjzjHtQVgZwDn74"
    }
]
```

#### Obtiene un usuario por uuid

```http
  GET /api/v1/usuario/${uuid}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `uuid`      | `string` | **Requerido**. Id de usuario a buscar |

Retorna Json con el siguiente formato:

```json
{
    "uuid": "3ff1d8d2-3d45-4315-946f-7790316c7767",
    "name": "David Caceres",
    "email": "dcaceres@gmai1l.com",
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkY2FjZXJlc0BnbWFpMWwuY29tIiwibmJmIjoxNjk2Mzg1OTQxLCJleHAiOjE2OTY0NzIzNDF9.1xRyQGR0qjqGgpov_NA1f5QiSKUoTjzjHtQVgZwDn74",
    "password": "XXXXXXXX",
    "phones": [
        {
            "uuid": "8dc1a50d-be32-4480-834a-e14fa5ea3e92",
            "number": "1234567",
            "citycode": "1",
            "countrycode": "57"
        }
    ]
}
```

#### Crea un usuario 

```http
  POST /api/v1/usuario
```
El cuerpo del metodo post debe contener el json con el siguiente formato:

```json
{
    "name": "David Caceres",
    "email": "dcaceres@gmai1l.com",
    "password": "huntAr21",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "countrycode": "57"
        }
    ]
}
```
#### Actualiza un usuario 

El metodo put permite actualizar los datos de un usuario, adicionalmente agrega, actualiza o elimina telefonos dependiendo de la existencia del uuid del elemento y  en cada actualizacion regenera el token.

```http
  PUT /api/v1/usuario
```
El cuerpo del metodo post debe contener el json con el siguiente formato:

```json
{
    "uuid": "3ff1d8d2-3d45-4315-946f-7790316c7767",
    "name": "David Caceres",
    "email": "dcaceres@gmai1l.com",
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkY2FjZXJlc0BnbWFpMWwuY29tIiwibmJmIjoxNjk2Mzg1OTQxLCJleHAiOjE2OTY0NzIzNDF9.1xRyQGR0qjqGgpov_NA1f5QiSKUoTjzjHtQVgZwDn74",
    "password": "XXXXXXXX",
    "phones": [
        {
            "uuid": "8dc1a50d-be32-4480-834a-e14fa5ea3e92",
            "number": "1234567",
            "citycode": "1",
            "countrycode": "57"
        }
    ]
}
```

#### Eliminar un usuario por uuid

```http
  DELETE /api/v1/usuario/${uuid}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `uuid`      | `string` | **Requerido**. Id de usuario a eliminar |



