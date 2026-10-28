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

1. New → PostgreSQL. Copiar la *Internal Database URL*: `postgresql://USUARIO:CLAVE@HOST/BASE`.
2. New → Web Service desde este repo, runtime **Docker**.
3. Variables de entorno: `DB_URL` = `jdbc:postgresql://HOST/BASE`, `DB_USER` = `USUARIO`, `DB_PASSWORD` = `CLAVE`.
