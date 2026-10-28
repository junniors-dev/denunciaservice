# denunciaservice

API REST de denuncias de inseguridad ciudadana. Spring Boot · JPA · PostgreSQL (Render) o MariaDB (local).

| Método | Ruta | Descripción |
|---|---|---|
| POST | `/denuncias/` | Registra una denuncia (estado = 1) |
| GET | `/denuncias/?offset=0&limit=5` | Lista paginada |
| GET | `/denuncias/{id}` | Una denuncia |
| GET | `/denuncias/ciudadano/{dni}` | Denuncias de un ciudadano |
| PUT | `/denuncias/{id}` | Marca la denuncia como atendida (estado = 0) |

En `postman/` está la colección con los 5 endpoints.

## Local

Con la MariaDB de XAMPP encendida: `mvnw.cmd spring-boot:run`

## Render

`render.yaml` crea la base PostgreSQL y el Web Service (Docker) y les pasa los datos de conexión
como variables de entorno, sin credenciales en el repo. En Render: New → Blueprint → este repositorio.
