package Vista.RolVendedor;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Modelo.Conexion;
import javax.swing.JTable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.Hash;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;



public class VenderMovil extends JFrame {

	private JPanel contentPane;
	
	private JTextField txtCantidad;
	private final String base = "usuariostiendademoviles";
	private final String user = "root";
	private final String password = "manolo";
	private final String timeZone = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	/**
	 * en casa tengo de localhost 3306 en ada 3309
	 */
	private final String url = "jdbc:mysql://localhost:3309/" + base + timeZone;
	private Connection con = null;
	private JTable jtPrecio;
	private JTextField txtId;

	PreparedStatement ps;
	ResultSet rs;
	
	/**
	 * AL NO SERVIRME UTILIZANDO LA OTRA CONEXIÓN PARA HACER UPDATE EN LA BBDD
	 * COPIO AQUI LA CLASE QUE ES EXACTAMENTE LA MISMA QUE EN CONEXIÓN
	 * 
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
		

	
	public VenderMovil() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1241, 861);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		setLocationRelativeTo(null);

		/**
		 * AL PULSAR EL JBUTTON NOS APARECE UNA JTABLE CON LOS MOVILES CON LAS
		 * CARACTERISTICAS QUE BUSCAMOS
		 */
		JButton btnCargar = new JButton("PRECIO");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jtPrecio = new JTable();
				jtPrecio.setBounds(22, 22, 561, 338);
				panel.add(jtPrecio);
				try {
					/**
					 * OBJETO CON LAS COLUMNAS QUE VAMOS A MOSTAR Y COINCIDEN CON LA DE NUESTRA
					 * TABLA STOCK EN BBDD USUARIOSTIENDAMOVILES
					 */
					Object[][] data = new Object[0][0];
					String[] datos = { "ID", "STOCK", "MARCA", "MODELO", "PRECIO", "GB", "PANTALA INCH", "BATERIA",
							"CÁMARA" };
					DefaultTableModel modelo = new DefaultTableModel(data, datos);
					jtPrecio.setModel(modelo);
					JScrollPane scroll = new JScrollPane(jtPrecio);
					getContentPane().add(scroll, BorderLayout.NORTH);

					/**
					 * HACEMOS CONEXIÓN CON LA BBDD USUARIOSTIENDAMOVILES
					 */
					PreparedStatement ps = null;
					ResultSet rs = null;
					Conexion conn = new Conexion();
					Connection con = conn.getConexion();
					/**
					 * VAMOS A PASARLE LA SIGUIENTE SELECT, METIENDOLE EL TEXTO QUE SE HA
					 * INTRODUCIDO EN EL JTEXT, QUE LLAMAMOS TXTMIN Y TXT MAX RESPECTIVAMENTE PARA
					 * RANGO DE PRECIO
					 */
					String sql = "SELECT * FROM stock ORDER BY precio";

					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();

					ResultSetMetaData rsMd = rs.getMetaData();
					int cantidadColumnas = rsMd.getColumnCount();
					/**
					 * MIENTRAS EXISTA UN SIGUIENTE SE SEGUIRA INSERTARNDO EN LA TABLA QUE SE VA A
					 * MOSTRAR
					 */
					while (rs.next()) {

						Object[] filas = new Object[cantidadColumnas];

						for (int i = 0; i < cantidadColumnas; i++) {
							filas[i] = rs.getObject(i + 1);

						}

						modelo.addRow(filas);
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "No se puede mostrar la tabla stock");
				}

			}

		});

		btnCargar.setBounds(2, 108, 108, 23);
		panel.add(btnCargar);

		JButton btnMostrarAscendente = new JButton("MARCA");
		btnMostrarAscendente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				jtPrecio = new JTable();
				jtPrecio.setBounds(22, 22, 561, 338);
				panel.add(jtPrecio);
				try {
					/**
					 * OBJETO CON LAS COLUMNAS QUE VAMOS A MOSTAR Y COINCIDEN CON LA DE NUESTRA
					 * TABLA STOCK EN BBDD USUARIOSTIENDAMOVILES
					 */
					Object[][] data = new Object[0][0];
					String[] datos = { "ID", "STOCK", "MARCA", "MODELO", "PRECIO", "GB", "PANTALA INCH", "BATERIA",
							"CÁMARA" };
					DefaultTableModel modelo = new DefaultTableModel(data, datos);
					jtPrecio.setModel(modelo);
					JScrollPane scroll = new JScrollPane(jtPrecio);
					getContentPane().add(scroll, BorderLayout.NORTH);

					/**
					 * HACEMOS CONEXIÓN CON LA BBDD USUARIOSTIENDAMOVILES
					 */
					PreparedStatement ps = null;
					ResultSet rs = null;
					Conexion conn = new Conexion();
					Connection con = conn.getConexion();
					/**
					 * VAMOS A PASARLE LA SIGUIENTE SELECT, METIENDOLE EL TEXTO QUE SE HA
					 * INTRODUCIDO EN EL JTEXT, QUE LLAMAMOS TXTMIN Y TXT MAX RESPECTIVAMENTE PARA
					 * RANGO DE PRECIO
					 */
					String sql = "SELECT * FROM stock ORDER BY marca";

					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();

					ResultSetMetaData rsMd = rs.getMetaData();
					int cantidadColumnas = rsMd.getColumnCount();
					/**
					 * MIENTRAS EXISTA UN SIGUIENTE SE SEGUIRA INSERTARNDO EN LA TABLA QUE SE VA A
					 * MOSTRAR
					 */
					while (rs.next()) {

						Object[] filas = new Object[cantidadColumnas];

						for (int i = 0; i < cantidadColumnas; i++) {
							filas[i] = rs.getObject(i + 1);

						}

						modelo.addRow(filas);
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "No se puede mostrar la tabla stock");
				}

			}
		});
		btnMostrarAscendente.setBounds(215, 108, 108, 23);
		panel.add(btnMostrarAscendente);

		JButton btnBateria = new JButton("BATERIA");
		btnBateria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtPrecio = new JTable();
				jtPrecio.setBounds(22, 22, 561, 338);
				panel.add(jtPrecio);
				try {
					/**
					 * OBJETO CON LAS COLUMNAS QUE VAMOS A MOSTAR Y COINCIDEN CON LA DE NUESTRA
					 * TABLA STOCK EN BBDD USUARIOSTIENDAMOVILES
					 */
					Object[][] data = new Object[0][0];
					String[] datos = { "ID", "STOCK", "MARCA", "MODELO", "PRECIO", "GB", "PANTALA INCH", "BATERIA",
							"CÁMARA" };
					DefaultTableModel modelo = new DefaultTableModel(data, datos);
					jtPrecio.setModel(modelo);
					JScrollPane scroll = new JScrollPane(jtPrecio);
					getContentPane().add(scroll, BorderLayout.NORTH);

					/**
					 * HACEMOS CONEXIÓN CON LA BBDD USUARIOSTIENDAMOVILES
					 */
					PreparedStatement ps = null;
					ResultSet rs = null;
					Conexion conn = new Conexion();
					Connection con = conn.getConexion();
					/**
					 * VAMOS A PASARLE LA SIGUIENTE SELECT, METIENDOLE EL TEXTO QUE SE HA
					 * INTRODUCIDO EN EL JTEXT, QUE LLAMAMOS TXTMIN Y TXT MAX RESPECTIVAMENTE PARA
					 * RANGO DE PRECIO
					 */
					String sql = "SELECT * FROM stock ORDER BY bateria";

					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();

					ResultSetMetaData rsMd = rs.getMetaData();
					int cantidadColumnas = rsMd.getColumnCount();
					/**
					 * MIENTRAS EXISTA UN SIGUIENTE SE SEGUIRA INSERTARNDO EN LA TABLA QUE SE VA A
					 * MOSTRAR
					 */
					while (rs.next()) {

						Object[] filas = new Object[cantidadColumnas];

						for (int i = 0; i < cantidadColumnas; i++) {
							filas[i] = rs.getObject(i + 1);

						}

						modelo.addRow(filas);
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "No se puede mostrar la tabla stock");
				}

			}
		});
		btnBateria.setBounds(406, 108, 108, 23);
		panel.add(btnBateria);

		JButton btnCapacidad = new JButton("CAPACIDAD");
		btnCapacidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtPrecio = new JTable();
				jtPrecio.setBounds(22, 22, 561, 338);
				panel.add(jtPrecio);
				try {
					/**
					 * OBJETO CON LAS COLUMNAS QUE VAMOS A MOSTAR Y COINCIDEN CON LA DE NUESTRA
					 * TABLA STOCK EN BBDD USUARIOSTIENDAMOVILES
					 */
					Object[][] data = new Object[0][0];
					String[] datos = { "ID", "STOCK", "MARCA", "MODELO", "PRECIO", "GB", "PANTALA INCH", "BATERIA",
							"CÁMARA" };
					DefaultTableModel modelo = new DefaultTableModel(data, datos);
					jtPrecio.setModel(modelo);
					JScrollPane scroll = new JScrollPane(jtPrecio);
					getContentPane().add(scroll, BorderLayout.NORTH);

					/**
					 * HACEMOS CONEXIÓN CON LA BBDD USUARIOSTIENDAMOVILES
					 */
					PreparedStatement ps = null;
					ResultSet rs = null;
					Conexion conn = new Conexion();
					Connection con = conn.getConexion();
					/**
					 * VAMOS A PASARLE LA SIGUIENTE SELECT, METIENDOLE EL TEXTO QUE SE HA
					 * INTRODUCIDO EN EL JTEXT, QUE LLAMAMOS TXTMIN Y TXT MAX RESPECTIVAMENTE PARA
					 * RANGO DE PRECIO
					 */
					String sql = "SELECT * FROM stock ORDER BY capacidad";

					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();

					ResultSetMetaData rsMd = rs.getMetaData();
					int cantidadColumnas = rsMd.getColumnCount();
					/**
					 * MIENTRAS EXISTA UN SIGUIENTE SE SEGUIRA INSERTARNDO EN LA TABLA QUE SE VA A
					 * MOSTRAR
					 */
					while (rs.next()) {

						Object[] filas = new Object[cantidadColumnas];

						for (int i = 0; i < cantidadColumnas; i++) {
							filas[i] = rs.getObject(i + 1);

						}

						modelo.addRow(filas);
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "No se puede mostrar la tabla stock");
				}
			}
		});
		btnCapacidad.setBounds(618, 108, 108, 23);
		panel.add(btnCapacidad);

		JButton btnPantalla = new JButton("PANTALLA");
		btnPantalla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtPrecio = new JTable();
				jtPrecio.setBounds(22, 22, 561, 338);
				panel.add(jtPrecio);
				try {
					/**
					 * OBJETO CON LAS COLUMNAS QUE VAMOS A MOSTAR Y COINCIDEN CON LA DE NUESTRA
					 * TABLA STOCK EN BBDD USUARIOSTIENDAMOVILES
					 */
					Object[][] data = new Object[0][0];
					String[] datos = { "ID", "STOCK", "MARCA", "MODELO", "PRECIO", "GB", "PANTALA INCH", "BATERIA",
							"CÁMARA" };
					DefaultTableModel modelo = new DefaultTableModel(data, datos);
					jtPrecio.setModel(modelo);
					JScrollPane scroll = new JScrollPane(jtPrecio);
					getContentPane().add(scroll, BorderLayout.NORTH);

					/**
					 * HACEMOS CONEXIÓN CON LA BBDD USUARIOSTIENDAMOVILES
					 */
					PreparedStatement ps = null;
					ResultSet rs = null;
					Conexion conn = new Conexion();
					Connection con = conn.getConexion();
					/**
					 * VAMOS A PASARLE LA SIGUIENTE SELECT, METIENDOLE EL TEXTO QUE SE HA
					 * INTRODUCIDO EN EL JTEXT, QUE LLAMAMOS TXTMIN Y TXT MAX RESPECTIVAMENTE PARA
					 * RANGO DE PRECIO
					 */
					String sql = "SELECT * FROM stock ORDER BY pantalla";

					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();

					ResultSetMetaData rsMd = rs.getMetaData();
					int cantidadColumnas = rsMd.getColumnCount();
					/**
					 * MIENTRAS EXISTA UN SIGUIENTE SE SEGUIRA INSERTARNDO EN LA TABLA QUE SE VA A
					 * MOSTRAR
					 */
					while (rs.next()) {

						Object[] filas = new Object[cantidadColumnas];

						for (int i = 0; i < cantidadColumnas; i++) {
							filas[i] = rs.getObject(i + 1);

						}

						modelo.addRow(filas);
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "No se puede mostrar la tabla stock");
				}
			}
		});
		btnPantalla.setBounds(833, 108, 108, 23);
		panel.add(btnPantalla);

		JButton btnCamara = new JButton("C\u00C1MARA");
		btnCamara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtPrecio = new JTable();
				jtPrecio.setBounds(22, 22, 561, 338);
				panel.add(jtPrecio);
				try {
					/**
					 * OBJETO CON LAS COLUMNAS QUE VAMOS A MOSTAR Y COINCIDEN CON LA DE NUESTRA
					 * TABLA STOCK EN BBDD USUARIOSTIENDAMOVILES
					 */
					Object[][] data = new Object[0][0];
					String[] datos = { "ID", "STOCK", "MARCA", "MODELO", "PRECIO", "GB", "PANTALA INCH", "BATERIA",
							"CÁMARA" };
					DefaultTableModel modelo = new DefaultTableModel(data, datos);
					jtPrecio.setModel(modelo);
					JScrollPane scroll = new JScrollPane(jtPrecio);
					getContentPane().add(scroll, BorderLayout.NORTH);

					/**
					 * HACEMOS CONEXIÓN CON LA BBDD USUARIOSTIENDAMOVILES
					 */
					PreparedStatement ps = null;
					ResultSet rs = null;
					Conexion conn = new Conexion();
					Connection con = conn.getConexion();
					/**
					 * VAMOS A PASARLE LA SIGUIENTE SELECT, METIENDOLE EL TEXTO QUE SE HA
					 * INTRODUCIDO EN EL JTEXT, QUE LLAMAMOS TXTMIN Y TXT MAX RESPECTIVAMENTE PARA
					 * RANGO DE PRECIO
					 */
					String sql = "SELECT * FROM stock ORDER BY camara";

					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();

					ResultSetMetaData rsMd = rs.getMetaData();
					int cantidadColumnas = rsMd.getColumnCount();
					/**
					 * MIENTRAS EXISTA UN SIGUIENTE SE SEGUIRA INSERTARNDO EN LA TABLA QUE SE VA A
					 * MOSTRAR
					 */
					while (rs.next()) {

						Object[] filas = new Object[cantidadColumnas];

						for (int i = 0; i < cantidadColumnas; i++) {
							filas[i] = rs.getObject(i + 1);

						}

						modelo.addRow(filas);
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "No se puede mostrar la tabla stock");
				}
			}
		});
		btnCamara.setBounds(1033, 108, 108, 23);
		panel.add(btnCamara);

		JLabel lblIdMovil = new JLabel("ID MOVIL");
		lblIdMovil.setBounds(12, 24, 56, 16);
		panel.add(lblIdMovil);

		txtId = new JTextField();
		txtId.setBounds(80, 21, 30, 22);
		panel.add(txtId);
		txtId.setColumns(10);

		JLabel lblCantidad = new JLabel("CANTIDAD");
		lblCantidad.setBounds(161, 24, 65, 16);
		panel.add(lblCantidad);

		txtCantidad = new JTextField();
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(227, 21, 30, 22);
		panel.add(txtCantidad);

		JButton btnVender = new JButton("VENDER");
		btnVender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection con = null;

				try {				
					con = getConexion();
					ps = con.prepareStatement("UPDATE stock SET cantidad=(cantidad -?) WHERE idmovil=?");
					
					
					ps.setInt(1, Integer.parseInt(txtCantidad.getText()));
					ps.setInt(2, Integer.parseInt(txtId.getText()));

					int res = ps.executeUpdate();

					if (res > 0) {
						JOptionPane.showMessageDialog(null, "VENTA CORRECTA");
						
					} else {
						JOptionPane.showMessageDialog(null, "ERROR EN VENTA");
						
					}
					con.close();
				} catch (Exception err) {
					System.err.println(err);
				}
			}

		});
		btnVender.setBounds(288, 4, 139, 56);
		panel.add(btnVender);

		JButton btnEnviarCorreo = new JButton("ENVIAR  FACTURA");
		btnEnviarCorreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEnviarCorreo.setBounds(465, 20, 161, 25);
		panel.add(btnEnviarCorreo);

		JButton btnBigdata = new JButton("BIGDATA");
		btnBigdata.setBounds(663, 20, 97, 25);
		panel.add(btnBigdata);

	}
}