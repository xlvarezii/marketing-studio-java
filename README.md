# Pregunta 2 — StudioFlow Marketing Lab (Java)

## Alcance real del prototipo

Aplicación web Java Spring Boot + Thymeleaf + PostgreSQL. Permite subir una foto, generar **3 guiones de plantilla**, seleccionar uno, mostrar **3 estilos visuales aplicando CSS a la misma foto**, consultar un **storyboard de video** y **simular una publicación** en Instagram o TikTok. Las imágenes no se generan mediante IA, no se renderiza un video real y no se publica realmente en redes sociales. Estos pasos requieren servicios externos, credenciales y APIs que no están integrados.

## Ejecutar en Windows con Docker Desktop

1. Extraer el ZIP y abrir la carpeta `marketing-studio-java` en VS Code.
2. Abrir la terminal **en la carpeta que contiene `docker-compose.yml`**.
3. Ejecutar `docker compose config` para comprobar la configuración.
4. Ejecutar `docker compose up --build -d`.
5. Abrir http://localhost:8081
6. Logs: `docker compose logs -f app`.
7. Detener: `docker compose down` (conserva la BD). **No usar `down -v`** salvo que quieras borrar los datos.

Se usa puerto **8081** para no interferir con la Pregunta 1 (8080).

## Arquitectura

- Presentación: `templates/` y `static/` (Thymeleaf y CSS).
- Controlador: `WebController.java` (rutas HTTP).
- Negocio: `CampaignService.java` (generación de guiones de demostración y validaciones).
- Datos: `CampaignRepository.java` (Spring Data JPA) + entidad `Campaign.java` + PostgreSQL.

Flujo: formulario multipart -> controlador -> servicio -> repositorio -> PostgreSQL -> vista Thymeleaf.

## Docker / nube

- `Dockerfile`: compilación Maven y ejecución con JRE 17.
- `docker-compose.yml`: contenedores app y PostgreSQL; volumen persistente `marketing_pgdata`.
- `.env.example`: variables configurables; copiar a `.env` y **cambiar la contraseña antes de exponer servicios**.
- Imagen: `docker build -t USUARIO/marketing-studio:1.0 .` y `docker push USUARIO/marketing-studio:1.0` después de iniciar sesión en Docker Hub.
- Para AWS: desplegar contenedor Java y una base PostgreSQL persistente, configurar secretos/variables y HTTPS. **No sirve subir este ZIP directamente a Amplify Hosting estático**.

## Límites de seguridad

Prototipo educativo sin autenticación. No publicar públicamente con contraseñas de ejemplo ni almacenar fotografías sensibles. La publicación real en redes sociales exige OAuth, permisos y cumplimiento de APIs oficiales. Para generar imágenes y video reales se requiere integrar un proveedor de generación multimedia.
