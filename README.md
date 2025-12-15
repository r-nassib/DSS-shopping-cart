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
# Con Maven instalado
mvn clean spring-boot:run

# O usando el wrapper incluido
./mvnw clean spring-boot:run
```

## Rutas principales
- `/products` listado y búsqueda de productos
- `/products/add` crear producto
- `/products/edit/{id}` editar producto
- `/products/delete/{id}` eliminar producto
- `/cart` ver carrito
- `/admin/export` exportar productos (requiere rol ADMIN)
- `/login` pantalla de inicio de sesión

## 🧑‍💻 Desarrollado por [Raúl Hidalgo](mailto:rhidalgou2@gmail.com)
GitHub: [r-nassib](https://github.com/r-nassib)

