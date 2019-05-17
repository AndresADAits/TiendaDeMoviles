# TiendaDeMoviles

> Autor: José Andrés Fernández Francisco


Aplicación  JAVA  en arquitectura MVC para  tres diferentes roles registrados en una base de datos mysql. Se pueden hacer diferentes tipos de consultas sobre otra base de datos (de móviles),  información de la aplicación y otros usuarios (según sus  privilegios).Exporta a CSV la base de datos para copia de seguridad (Persistencia en memoria) También incluye  impresión de facturas, pedidos y lista de empleados en activo.
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
