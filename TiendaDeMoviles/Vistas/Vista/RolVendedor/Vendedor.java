package Vista.RolVendedor;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Modelo.Conexion;
import Modelo.Usuarios;

import javax.swing.JTable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.MessageFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Vendedor extends JFrame {
	Usuarios mod;
	/**
	 * EL SIGUIENTE BOOLEANO ES PARA USAR EL BOTON DE AVISO DEL ROL SUPERVISOR, QUE
	 * AVISE CUANDO EL STOCK DE UN TIPO DE MOVIL ES IGUAL A CERO Y TIENE QUE COMPRAR
	 * MÁS
	 */

	private JPanel contentPane;

	private JTextField txtCantidad;
	private final String base = "usuariostiendademoviles";
	private final String user = "root";
	private final String password = "manolo";
	private final String timeZone = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	/**
	 * en casa tengo de localhost 3306 en ada 3309
	 */
	private final String url = "jdbc:mysql://localhost:3306/" + base + timeZone;
	private Connection con = null;
	private JTable jtPrecio;
	private JTextField txtId;

	PreparedStatement ps;
	ResultSet rs;

	/**
	 * AL NO SERVIRME UTILIZANDO LA OTRA CONEXIÓN PARA HACER UPDATE EN LA BBDD COPIO
	 * AQUI LA CLASE QUE ES EXACTAMENTE LA MISMA QUE EN CONEXIÓN
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

	public Vendedor(Usuarios mod) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 991, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setIconImage(new ImageIcon(getClass().getResource("/imagenes/ICON.png")).getImage());
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
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		txtId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c < '0' || c > '9')
					e.consume();
			}
		});
		txtId.setBounds(80, 21, 30, 22);
		panel.add(txtId);
		txtId.setColumns(10);

		JLabel lblCantidad = new JLabel("CANTIDAD");
		lblCantidad.setBounds(161, 24, 65, 16);
		panel.add(lblCantidad);

		txtCantidad = new JTextField();
		txtCantidad.addKeyListener(new KeyAdapter() {
			@Override
			/**
			 * ESTA PEQUEÑA FUNCION NOS IMPIDE METER LETRAS, SIMBOLOS, ETC LO QUE HACE QUE
			 * NO SE PUEDA ESTROPEAR LA VENTA, NI HACER UNA "VENTA NEGATIVA" QUE AUMENTE
			 * STOCK
			 */

			public void keyTyped(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if (c < '0' || c > '9')
					arg0.consume();
			}
		});
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(227, 21, 30, 22);
		panel.add(txtCantidad);

		JButton btnVender = new JButton("VENDER");
		btnVender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int numeroStock = 0;

				jtPrecio = new JTable();
				jtPrecio.setBounds(22, 22, 561, 338);
				panel.add(jtPrecio);
				try {
					if (txtId.getText().equals("") || txtCantidad.getText().equals("")) {

						JOptionPane.showMessageDialog(null, "HAY QUE RELLENAR  LOS DOS CAMPOS");
					} else {

						/**
						 * AQUI MOSTRAMOS LAS UNIDADES QUE TENIAMOS EN STOCK AL HACER LA VENTA
						 */
						Object[][] data = new Object[0][0];
						String[] datos = { "STOCK" };
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
						String sql = "SELECT cantidad FROM stock WHERE idmovil= " + txtId.getText();
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

								numeroStock = (int) rs.getObject(i + 1);
							}

							modelo.addRow(filas);
						}
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "No se puede mostrar la tabla stock");
				}

				/**
				 * EN ESTA PARTE COMPROBAMOS COMPARANDO EL SELECT DEL STOCK CON LO QUE VAMOS A
				 * VENDER CONTROLANDO STOCK
				 */
				int compruebaStock = Integer.parseInt(txtCantidad.getText());

				Connection con = null;
				/**
				 * SI VENDEMOS TODAS LAS EXISTENCIAS, LA VENTA SE REALIZA, PERO SE MANDA AVISO A
				 * SUPERVISOR
				 */

				if (numeroStock > compruebaStock || numeroStock == compruebaStock) {
					try {
						/**
						 * CON ESTO VAMOS A OBLIGAR A QUE SE RELLENEN TODOS LOS CAMPOS Y ASI TENER
						 * CONTROLADOS TODAS LAS POSIBILIDADES DE ACCIÓN DEL USUARIO, YA QUE TAMBIEN
						 * ESTA OBLIGADO A INTRODUCIR EXCLUSIVAMENTE NUMEROS
						 */
						if (txtId.getText().equals("") || txtCantidad.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "HAY QUE RELLENAR TODOS LOS CAMPOS");

						} else {
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
						}
					} catch (Exception err) {
						System.err.println(err);
					}
					if (numeroStock == compruebaStock) {
						JOptionPane.showMessageDialog(null, "CON ESTA VENTA NOS QUEDAMOS SIN STOCK");
					}
				} else {

					JOptionPane.showMessageDialog(null, "NO HAY STOCK SUFICIENTE PARA ESA VENTA");

				}

			}

		});
		btnVender.setBounds(288, 4, 139, 56);
		panel.add(btnVender);

		JButton btnEnviarCorreo = new JButton("FACTURA");
		btnEnviarCorreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * CREO UNA TABLA DEL MOVIL QUE SE HA VENDIDO
				 */
				jtPrecio = new JTable();
				jtPrecio.setBounds(22, 22, 561, 338);
				panel.add(jtPrecio);
				try {
					/**
					 * OBJETO CON LAS COLUMNAS QUE VAMOS A MOSTAR Y COINCIDEN CON LA DE NUESTRA
					 * TABLA STOCK EN BBDD USUARIOSTIENDAMOVILES
					 */
					if (txtId.getText().equals("") || txtCantidad.getText().equals("")) {

						JOptionPane.showMessageDialog(null, "HAY QUE RELLENAR  LOS DOS CAMPOS");
					} else {
						

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
						String sql = "SELECT * FROM stock WHERE idmovil= " + txtId.getText();

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
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "No se puede mostrar la tabla stock");

				}

				/**
				 * FUNCION QUE IMPRIME UNA TABLA
				 */
				MessageFormat header =new MessageFormat("FACTURA");
		
				MessageFormat pie =new MessageFormat(" X "+txtCantidad.getText());
				try {
					if (txtId.getText().equals("") || txtCantidad.getText().equals("")) {

						JOptionPane.showMessageDialog(null, "HAY QUE RELLENAR  LOS DOS CAMPOS");
					} else {
						
					jtPrecio.print(JTable.PrintMode.FIT_WIDTH, header, pie);
					}
				}catch(java.awt.print.PrinterException f) {
					System.err.format("Error de impresion", f.getMessage());
					
				}
				

			}
		});
		btnEnviarCorreo.setBounds(509, 4, 217, 56);
		panel.add(btnEnviarCorreo);

		JLabel lblListasOrdenadasPor = new JLabel("BUSQUEDA POR  CARACTERISTICA");
		lblListasOrdenadasPor.setBounds(386, 154, 240, 16);
		panel.add(lblListasOrdenadasPor);

		JLabel lblBusquedaPorRango = new JLabel("ORDENAR POR :");
		lblBusquedaPorRango.setBounds(406, 73, 207, 16);
		panel.add(lblBusquedaPorRango);

		JButton button = new JButton("PRECIO");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RPrecio Precio = new RPrecio();
				Precio.setVisible(true);
			}
		});
		button.setBounds(2, 207, 108, 23);
		panel.add(button);

		JButton button_1 = new JButton("MARCA");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Marca Marca = new Marca();
				Marca.setVisible(true);
			}
		});
		button_1.setBounds(215, 206, 108, 23);
		panel.add(button_1);

		JButton button_2 = new JButton("BATERIA");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mAmperios mAmperios = new mAmperios();
				mAmperios.setVisible(true);

			}
		});
		button_2.setBounds(406, 206, 108, 23);
		panel.add(button_2);

		JButton button_3 = new JButton("CAPACIDAD");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gb gb = new gb();
				gb.setVisible(true);
			}
		});
		button_3.setBounds(618, 207, 108, 23);
		panel.add(button_3);

		JButton button_4 = new JButton("PANTALLA");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inches inches = new inches();
				inches.setVisible(true);
			}
		});
		button_4.setBounds(833, 206, 108, 23);
		panel.add(button_4);

		JButton button_5 = new JButton("C\u00C1MARA");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				camara camara = new camara();
				camara.setVisible(true);
			}
		});
		button_5.setBounds(1033, 206, 108, 23);
		panel.add(button_5);
		/**
		 * AQUI VOY A ESCRIBIR EN LOS JLABEL EL NOMBRE DEL USUARIO, EL ROL QUE TIENE Y
		 * EL ULTIMO ACCESO DE FORMA DINAMICA
		 */

		JLabel lblNombre = new JLabel(mod.getNombre());
		lblNombre.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNombre.setBounds(2, 241, 172, 64);
		panel.add(lblNombre);

		JLabel lblRol = new JLabel("ROL:");
		lblRol.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblRol.setBounds(215, 240, 116, 64);
		panel.add(lblRol);

		JLabel lblTipo = new JLabel(mod.getNombre_tipo());
		lblTipo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblTipo.setBounds(272, 240, 217, 64);
		panel.add(lblTipo);

		JLabel lblUltAcceso = new JLabel("ULT ACCESO:");
		lblUltAcceso.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblUltAcceso.setBounds(398, 240, 116, 64);
		panel.add(lblUltAcceso);

		JLabel lblAcceso = new JLabel(mod.getLast_session());
		lblAcceso.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblAcceso.setBounds(513, 240, 313, 64);
		panel.add(lblAcceso);

		JButton btnOrdenar = new JButton("ORDENAR");
		btnOrdenar.addActionListener(new ActionListener() {
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
					String sql = "SELECT * FROM stock ORDER BY idmovil";

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
		btnOrdenar.setBounds(802, 4, 139, 56);
		panel.add(btnOrdenar);

	}
}