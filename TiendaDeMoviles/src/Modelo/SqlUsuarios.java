package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlUsuarios extends Conexion {

	public boolean registrar(Usuarios usr) {

		/**
		 * FUNCION QUE MANIPULA LA BASE DE DATOS CON DIVERSAR CONSULTAS
		 */
		PreparedStatement ps = null;
		Connection con = getConexion();

		/**
		 * CONSULTA PARA REGISTRAR USUARIOS CON UN INSERT
		 */

		String sql = "INSERT INTO usuario (usuario,password,nombre,correo,id_tipo) VALUES(?,?,?,?,?)";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usr.getUsuario());
			ps.setString(2, usr.getPassword());
			ps.setString(3, usr.getNombre());
			ps.setString(4, usr.getCorreo());
			ps.setInt(5, usr.getId_tipo());
			ps.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean login(Usuarios usr) {

		/**
		 * Creamos el select sql para poder comprobar si existe el usuario Devuelve 1 si
		 * el usuario existe Devuelve 0 si el usuario no existe
		 */
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();
// Consulta multitabla para que muestra id, usuario, password, etc de nuestra base de datos usuariostiendademoviles
		String sql = "SELECT u.id,u.usuario, u.password, u.nombre, u.id_tipo, c.nombre FROM usuario AS u INNER JOIN clasesdeusuario As C ON u.id_tipo=c.id WHERE usuario = ?";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usr.getUsuario());
			rs = ps.executeQuery();

			if (rs.next()) {
				if (usr.getPassword().equals(rs.getString(3))) {

					String sqlUpdate = "UPDATE usuario SET last_session = ? WHERE id=?";
					ps = con.prepareStatement(sqlUpdate);
					ps.setString(1, usr.getLast_session());
					ps.setInt(2, rs.getInt(1));
					ps.execute();

					usr.setId(rs.getInt(1));
					usr.setNombre(rs.getString(4));
					usr.setId_tipo(rs.getInt(5));
					usr.setNombre_tipo(rs.getString(6));
					return true;
				} else {
					return false;
				}
			}

			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public int existeUsuario(String usuario) {

		/**
		 * CREAMOS EL SELECT SQL PARA PODER COMPROBAR SI EXISTE EL USUARIO EN LA BBDD
		 * DEVUELVE 1 SI EL USUARIO EXISTE, Y 0 SI NO
		 * 
		 */
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();

		String sql = "SELECT count(id) FROM usuario WHERE usuario = ?";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usuario);
			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}

			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1;
		}
	}

	public boolean esEmail(String correo) {
		/**
		 * VALIDACION DE CORREO, QUE OBLIGA A QUE TENGA UNA SERIE DE CARACTERES EN UN
		 * ORDEN QUE COINCIDE CON FORMATO DE CORREO
		 */
		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

		Matcher mather = pattern.matcher(correo);

		return mather.find();
	}
}