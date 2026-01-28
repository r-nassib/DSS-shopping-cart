# CompraGrana - Tienda online para Granada

CompraGrana es una aplicación web de carrito de compras para la ciudad de Granada, España. Permite listar productos, buscarlos por nombre o rango de precio, añadirlos al carrito, gestionarlos (CRUD) y exportar el catálogo de productos. Incluye autenticación básica para las rutas de administración.

## Prerrequisitos
- Java 17 o superior
- Maven 3.8+ (o el wrapper `mvnw` incluido)
- Navegador web moderno

## Tecnologías usadas
- Spring Boot (Web, Security)
- Thymeleaf
- Spring Data JPA con H2 en memoria
- Lombok

## Configuración por defecto
- Base de datos H2 en memoria: `jdbc:h2:mem:testdb`
- Credenciales por defecto (propósito demo): usuario `admin`, contraseña `admin`
- Puerto HTTP: `8080`

## Comandos de ejecución
Desde el directorio raíz del proyecto:
```bash
mvn clean package
mvn clean install

#Debugging Maven Build Failure ejecutar el comando para revisar los errores de compilación
mvn clean package 

#Restarting application to verify fix
mvn spring-boot:run

# Con Maven instalado
mvn clean spring-boot:run

# O usando el wrapper incluido
./mvnw clean spring-boot:run
```

## Cómo ejecutar y probar la API

### 1. Ejecutar la aplicación
Para iniciar el servidor, abre una terminal en la raíz del proyecto y ejecuta:

```bash
./mvnw spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`.

### 2. Usuarios de Prueba
El sistema incluye los siguientes usuarios preconfigurados:

| Usuario | Contraseña | Rol | Permisos |
| :--- | :--- | :--- | :--- |
| `admin` | `admin` | ADMIN, MANAGER | Acceso total (Gestión de usuarios, productos, exportación) |
| `manager` | `manager` | MANAGER | Gestión de productos (Crear, Editar, Eliminar) |
| `cliente` | `cliente` | CLIENT | Ver productos, comprar, usar carrito |

### 3. Escenarios de Prueba (Manual)

#### A. Acceso Público y Catálogo
1. Abre un navegador (o modo incógnito) y entra a `http://localhost:8080/products`.
2. Debes poder ver la lista de productos sin iniciar sesión.
3. Intenta añadir un producto al carrito; esto debería funcionar (se crea una sesión anónima o te redirige si está protegido, en este caso el carrito es público pero persistente por sesión).

#### B. Gestión de Productos (Seguridad)
1. **Intento no autorizado:** Sin loguearte, intenta acceder a `http://localhost:8080/products/add`. Deberías ser redirigido al login.
2. **Login Admin/Manager:** Inicia sesión con `manager` / `manager`.
3. Ve a `Productos > Añadir` y crea un producto nuevo.
4. Verifica que aparece en el listado.
5. **Borrado:** Elimina el producto creado.

#### C. Carrito de Compras (Lógica y Sesión)
1. Añade un producto al carrito. Verifica que la cantidad es 1.
2. Añade el **mismo** producto otra vez. La cantidad debe subir a 2.
3. Abre **otro navegador** (o ventana de incógnito).
4. Verifica que el carrito en la nueva ventana está vacío (aislamiento de sesión).

#### D. Verificación de Seguridad API (Avanzado)
Si intentas enviar un POST directo para crear un producto sin ser administrador, debe fallar.
*Nota:* Debido a la protección CSRF activada, las pruebas con herramientas externas como Postman requieren configuración de cookies y tokens. Se recomienda probar los flujos de seguridad directamente en el navegador.

### 4. Consola H2
Para inspeccionar la base de datos en memoria:
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- User: `sa`
- Password: `password`

## 📄 Documentación API
Se ha generado un archivo de especificación OpenAPI con todos los endpoints.
Archivo: [`src/main/resources/openapi.yaml`](src/main/resources/openapi.yaml)
Puedes visualizar este archivo en cualquier editor de Swagger o importarlo en Postman.


## 🧑‍💻 Desarrollado por [Raúl Hidalgo](mailto:rhidalgou2@gmail.com)
GitHub: [r-nassib](https://github.com/r-nassib)

