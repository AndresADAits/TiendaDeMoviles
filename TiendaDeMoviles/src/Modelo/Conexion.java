package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private final String base = "usuariostiendademoviles";
	private final String user = "root";
	private final String password = "manolo";
	//NECASARIO PARA SOLUCIONAR ERROR TIMEZONE ROMANCE
	private final String timeZone = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private final String url = "jdbc:mysql://localhost:3306/"+base+ timeZone;
	private Connection con = null;
	
	/**
	 * CONEXIÓN CON LA BASE DE DATOS "USUARIOSTIENDADEMOVILES" EN LA QUE TENEMOS DOS TABLAS
	 * 
	 * 1.-CLASESDEUSUARIO: EN LA QUE SE ALMACENAN LAS ID Y NOMBRES DE USUARIO
	 * 2.-USUARIO: EN EL QUE SE ALMACENAN LA ID(INCREMENTAL), USUARIO, PASSWORD
	 * (ENCRIPTADO POR HASH), NOMBRE, CORREO(CON UNA BUENA VALIDACIÓN), LAST_SESSION
	 * E ID_TIPO QUE CONECTA CON LA ID DE LA TABLA CLASESDEUSUARIO.
	 */
	public Connection getConexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

}
