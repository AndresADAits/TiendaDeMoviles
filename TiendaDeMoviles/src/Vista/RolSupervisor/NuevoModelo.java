package Vista.RolSupervisor;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Modelo.Conexion;

public class NuevoModelo extends JFrame {

	/**
	 * TODO REFRESH LA TABLA DE ARRIBA CUANDO SE REALIZA COMPRA, PODRIAS LLAMAR
	 * DESDE DONDE SE LANZA EN MENSAJE COMPRA CORRECTA
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

	PreparedStatement ps;
	ResultSet rs;
	private JTextField txtMarca;
	private JTextField txtModelo;
	private JTextField txtPrecio;
	private JTextField txtCapacidad;
	private JTextField txtPantalla;
	private JTextField txtBateria;
	private JTextField txtCamara;

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

	public NuevoModelo() {
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

		jtPrecio = new JTable();
		jtPrecio.setBounds(22, 22, 561, 338);
		panel.add(jtPrecio);
		try {
			/**
			 * AQUI MOSTRAMOS LAS UNIDADES QUE TENIAMOS EN STOCK AL HACER LA VENTA
			 */
			Object[][] data = new Object[0][0];
			String[] datos = { "ID", "STOCK", "MARCA", "MODELO", "PRECIO", "GB", "PANTALA INCH", "BATERIA", "CÁMARA" };
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
			String sql = "SELECT * FROM stock ";
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

		JLabel lblCantidad = new JLabel("CANTIDAD");
		lblCantidad.setBounds(0, 94, 65, 16);
		panel.add(lblCantidad);

		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/**
				 * EN ESTA PARTE COMPROBAMOS COMPARANDO EL SELECT DEL STOCK CON LO QUE VAMOS A
				 * VENDER CONTROLANDO STOCK
				 */

				Connection con = null;

				try {
					con = getConexion();

					ps = con.prepareStatement("INSERT INTO stock (cantidad,marca,modelo,precio,capacidad,pantalla,bateria,camara) VALUES(?,?,?,?,?,?,?,?)");

					ps.setInt(1, Integer.parseInt(txtCantidad.getText()));
					ps.setString(2, (txtMarca.getText()));
					ps.setString(3, (txtModelo.getText()));
					ps.setInt(4, Integer.parseInt(txtPrecio.getText()));
					ps.setInt(5, Integer.parseInt(txtCapacidad.getText()));
					ps.setString(6, (txtPantalla.getText()));
					ps.setInt(7, Integer.parseInt(txtBateria.getText()));
					ps.setInt(8, Integer.parseInt(txtCamara.getText()));

					int res = ps.executeUpdate();

					if (res > 0) {
						JOptionPane.showMessageDialog(null, "INSERTADO CORRECTAMENTE");

					} else {
						JOptionPane.showMessageDialog(null, "ERROR, NO INSERTADO");

					}
					con.close();
				} catch (Exception err) {
					System.err.println(err);
				}

			}

		});
		btnInsertar.setBounds(288, 4, 139, 56);
		panel.add(btnInsertar);

		JButton btnEnviarCorreo = new JButton("IMPRIMIR   ALBARAN");
		btnEnviarCorreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEnviarCorreo.setBounds(465, 20, 161, 25);
		panel.add(btnEnviarCorreo);
		
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
		txtCantidad.setBounds(65, 91, 30, 22);
		panel.add(txtCantidad);

		
		txtMarca = new JTextField();
		txtMarca.setColumns(10);
		txtMarca.setBounds(155, 91, 123, 22);
		panel.add(txtMarca);
		
		JLabel lblMarca = new JLabel("MARCA");
		lblMarca.setBounds(107, 94, 56, 16);
		panel.add(lblMarca);
		
		JLabel lblModelo = new JLabel("MODELO");
		lblModelo.setBounds(288, 94, 56, 16);
		panel.add(lblModelo);
		
		txtModelo = new JTextField();
		txtModelo.setColumns(10);
		txtModelo.setBounds(341, 91, 123, 22);
		panel.add(txtModelo);
		
		txtPrecio = new JTextField();
		txtPrecio.addKeyListener(new KeyAdapter() {
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
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(538, 91, 30, 22);
		panel.add(txtPrecio);
		
		JLabel lblPrecio = new JLabel("PRECIO");
		lblPrecio.setBounds(476, 94, 56, 16);
		panel.add(lblPrecio);
		
		JLabel lblCapacidad = new JLabel("CAPACIDAD");
		lblCapacidad.setBounds(582, 94, 80, 16);
		panel.add(lblCapacidad);
		
		txtCapacidad = new JTextField();
		txtCapacidad.addKeyListener(new KeyAdapter() {
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
		txtCapacidad.setColumns(10);
		txtCapacidad.setBounds(661, 91, 30, 22);
		panel.add(txtCapacidad);
		
		JLabel lblPantallaDecimal = new JLabel("PANTALLA (DECIMAL)");
		lblPantallaDecimal.setBounds(703, 94, 133, 16);
		panel.add(lblPantallaDecimal);
		
		txtPantalla = new JTextField();
		txtPantalla.setColumns(10);
		txtPantalla.setBounds(838, 91, 30, 22);
		panel.add(txtPantalla);
		
		JLabel lblBateria = new JLabel("BATERIA");
		lblBateria.setBounds(894, 94, 80, 16);
		panel.add(lblBateria);
		
		txtBateria = new JTextField();
		txtBateria.addKeyListener(new KeyAdapter() {
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
		txtBateria.setColumns(10);
		txtBateria.setBounds(986, 91, 30, 22);
		panel.add(txtBateria);
		
		JLabel lblCmara = new JLabel("C\u00C1MARA");
		lblCmara.setBounds(1028, 94, 80, 16);
		panel.add(lblCmara);
		
		txtCamara = new JTextField();
		txtCamara.addKeyListener(new KeyAdapter() {
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
		txtCamara.setColumns(10);
		txtCamara.setBounds(1105, 91, 30, 22);
		panel.add(txtCamara);

	}
}