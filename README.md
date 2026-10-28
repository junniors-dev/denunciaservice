# denunciaservice

API REST para que los ciudadanos denuncien hechos de inseguridad ante el serenazgo.
Spring Boot 4.1 · Java 21 · Spring Data JPA · PostgreSQL (Render) o MySQL/MariaDB (local).

Capas: `entity` → `repository` → `service` → `controller`.

## Endpoints

| Método | Ruta | Descripción |
|---|---|---|
| POST | `/denuncias/` | Registra una denuncia (estado = 1) |
| GET | `/denuncias/?offset=0&limit=5` | Lista paginada, de la más reciente a la más antigua |
| GET | `/denuncias/{id}` | Una denuncia (404 si no existe) |
| GET | `/denuncias/ciudadano/{dni}` | Denuncias de un ciudadano |
| PUT | `/denuncias/{id}` | Marca la denuncia como atendida (estado = 0) |

Cuerpo del POST (la fecha es opcional; si no llega, se usa la hora de Lima):

```json
{
  "dni": "71234561",
  "tipo": "ROBO",
  "direccion": "Av. Balta 500, Chiclayo",
  "descripcion": "Me arrebataron el celular"
}
```

La colección `postman/denunciaservice.postman_collection.json` trae los 5 endpoints. Cambia la variable `baseUrl` para pasar de local a Render.

## Ejecución local

Con la MariaDB de XAMPP encendida (crea sola la base `denuncias_db`):

```
mvnw.cmd spring-boot:run
```

## Variables de entorno

| Variable | Ejemplo |
|---|---|
| `DB_URL` | `jdbc:postgresql://dpg-xxxx-a/denuncias_db` |
| `DB_USER` | usuario de la base |
| `DB_PASSWORD` | contraseña de la base |

Render pone `PORT` por su cuenta.

## Despliegue en Render

1. **New → PostgreSQL** (plan Free). Copiar la *Internal Database URL*:
   `postgresql://USUARIO:CLAVE@HOST/BASE`.
2. **New → Web Service** desde este repositorio, con runtime **Docker** (usa el `Dockerfile`).
3. En *Environment*:
   - `DB_URL` = `jdbc:postgresql://HOST/BASE`, es decir, la URL interna sin usuario ni clave y con `jdbc:` delante.
   - `DB_USER` = `USUARIO`
   - `DB_PASSWORD` = `CLAVE`
4. Al terminar el despliegue, probar `https://<servicio>.onrender.com/denuncias/`.
