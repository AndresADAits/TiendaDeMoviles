package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private final String base = "usuariostiendademoviles";
	private final String user = "root";
	private final String password = "manolo";
	private final String timeZone = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private final String url = "jdbc:mysql://localhost:3306/"+base+ timeZone;
	private Connection con = null;
	
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

/**
 * package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
	public static void main(String[]args)
	throws InstantiationException,IllegalAccessError,ClassNotFoundException,IllegalAccessException,SQLException{
	 String timeZone = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

 String base = "consumoelectrico";
	
	 String url = "jdbc:mysql://localhost:3306/"+base+ timeZone;

	
	String user = "root";
	
	String password = "manolo";
	
	 String driver="com.mysql.cj.jdbc.Driver";
	
	Class.forName(driver).newInstance();
	
	Connection conexion=DriverManager.getConnection(url, user, password);
	
	if(conexion!=null)
		System.out.println("Conexion establecida");
	
	Statement consulta=conexion.createStatement();
	ResultSet resultado = consulta.executeQuery("SELECT count(*),sum(kw), avg(kw) from mediciones");
	
	while(resultado.next());
	System.out.println(resultado.getString("sum(kw)"));
	
	
	
	
}
}
	
	
 */
