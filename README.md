# TiendaDeMoviles

> Autor: José Andrés Fernández Francisco


Aplicación  JAVA con interfaz gráfica en arquitectura MVC que gestiona tres roles en una base de datos mysql, que tambien contiene un registro de usuarios, tipos de usuarios, stock de moviles y registro de acceso a la aplicación. Se pueden realizar multitud de consultas, compras, ventas, altas y bajas, tanto de moviles como de usuarios, y dependiendo del rol del usuario.Exporta a CSV tabla stock y tabla de usuarios de la base de datos para copia de seguridad. También incluye  impresión de facturas, pedidos y lista de empleados en activo.


#### Estructura:

 1. Vistas.
 + Imágenes.
   + Iconos para botones y ventanas.
  
 + HomeRegistro.
   + Home.
   + Login.
   + Registro.
   + RegistroSupervisor.
  
 + RolGerente.
   + BorrarEmpleado.
   + CambiarPrecio.
   + ControlAcceso.
   + Gerente.
 + RolSupervisor.
   + Compra.
   + NuevoModelo.
   + QuitarModelo.
  
 + RolVendedor.
   + Bateria.
   + Cámara.
   + Capacidad.
   + Marca.
   + Ordenar.
   + Pantalla.
   + Precio.
   + RangoBateria.
   + RangoCámara.
   + RangoCapacidad.
   + RangoMarca.
   + RangoOrdenar.
   + RangoPantalla.
   + RangoPrecio.
   + Vendedor.
  
  2. Modelos.

   + Conexion.
   + Hash.
   + SqlUsuarios.
   + Usuarios.
 
 3. Ejecutable
  * Ejecutable.
 
 4. Librerías.
  * Conector mysqlJava.
  * Base de datos que vamos a usar.

    

[Repositorio](https://github.com/AndresADAits/TiendaDeMoviles)
