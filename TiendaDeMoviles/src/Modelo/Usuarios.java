package Modelo;

public class Usuarios {
	
	/**FUNCION PARA TOMAR Y ESCRIBIR LOS DATOS QUE LE PEDIMOS 
	 * EN EL REGISTRO A EL USUARIO. LAS VARIABLES DEBEN 
	 * COINCIDIR CON LAS COLUMNAS DE LA TABLA USUARIO
	 */
	private int id;
	private String usuario;
	private String password;
	private String nombre;
	private String correo;
	private String last_session;
	private int id_tipo;
	private String nombre_tipo;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getLast_session() {
		return last_session;
	}
	public void setLast_session(String last_session) {
		this.last_session = last_session;
	}
	public int getId_tipo() {
		return id_tipo;
	}
	public void setId_tipo(int id_tipo) {
		this.id_tipo = id_tipo;
	}
	public String getNombre_tipo() {
		return nombre_tipo;
	}
	public void setNombre_tipo(String nombre_tipo) {
		this.nombre_tipo = nombre_tipo;
	}
	
	

}
