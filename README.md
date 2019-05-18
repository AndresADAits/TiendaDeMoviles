# TiendaDeMoviles

> Autor: José Andrés Fernández Francisco

### Descripción:


Aplicación  JAVA con interfaz gráfica en arquitectura MVC que gestiona tres roles en una base de datos mysql, que tambien contiene un registro de usuarios, tipos de usuarios, stock de móviles y registro de acceso a la aplicación. Se pueden realizar multitud de consultas, compras, ventas, altas y bajas, tanto de móviles como de usuarios, y dependiendo del rol del usuario.Exporta a CSV tabla stock y tabla de usuarios de la base de datos para copia de seguridad. También incluye  impresión de facturas, pedidos y lista de empleados en activo.

### Instalación:
Esta aplicación  se podria ejecutar tanto desde eclipse, netbeans, o desde un ejecutable a través de un simbolo de sistema de Windows. Solo habira que añadir el conector al build path ( desde add external jar ) e importar la base de datos a mysql y hacer coincidir el localhost, usuario y contraseña con el vuestro.


### Estructura:
#### Vistas

|RolVendedor| RolSupervisor|RolGerente|HomeRegistro|Imágenes|
| ------------- | ------------- |------------- |------------- |------------- |
| Vendedor.  | Supervisor.  | Gerente.| Home. | Iconos. |
| Ordenar.  | NuevoModelo. | CambiarPrecio.| Login. |
| Cámara.  |  QuitarModelo.  | ControlAcceso.| Registro. |
| Capacidad.  | Compra.  | BorrarEmpleado.| RegistroSupervisor. |
| Marca.  |
| Bateria.  |
| Pantalla. |
| Precio.  |
| Busqueda por rango de anteriores  |

 #### Modelos.
| Modelo |
| ------------- |
| Conexion.|
| Hash.|
| SqlUsuarios.|
| Usuarios.|

 #### Ejecutable

| Main|
| ------------- |
| Ejecutable.|


[Video de Presentación](https://youtu.be/G8FBvsOOXwQ)
