package Vista.RolSupervisor;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Modelo.Conexion;
import Vista.RolVendedor.RangoMarca;

import javax.swing.JTable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Compra extends JFrame {
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
	private JTextField txtId;

	PreparedStatement ps;
	ResultSet rs;

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

	public Compra() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 1241, 861);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setIconImage(new ImageIcon(getClass().getResource("/imagenes/ICON.png")).getImage());
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
			String[] datos = { "ID", "STOCK", "MARCA", "MODELO", "PRECIO", "GB", "PANTALA INCH", "BATERIA", "C�MARA" };
			DefaultTableModel modelo = new DefaultTableModel(data, datos);
			jtPrecio.setModel(modelo);
			JScrollPane scroll = new JScrollPane(jtPrecio);
			getContentPane().add(scroll, BorderLayout.NORTH);

			/**
			 * HACEMOS CONEXI�N CON LA BBDD USUARIOSTIENDAMOVILES
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

		/**
		 * AL PULSAR EL JBUTTON NOS APARECE UNA JTABLE CON LOS MOVILES CON LAS
		 * CARACTERISTICAS QUE BUSCAMOS
		 */

		JLabel lblIdMovil = new JLabel("ID MOVIL");
		lblIdMovil.setBounds(10, 47, 56, 16);
		panel.add(lblIdMovil);

		txtId = new JTextField();
		txtId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if (c < '0' || c > '9')
					arg0.consume();
			}
		});
		txtId.setBounds(76, 44, 30, 22);
		panel.add(txtId);
		txtId.setColumns(10);

		JLabel lblCantidad = new JLabel("CANTIDAD");
		lblCantidad.setBounds(156, 47, 65, 16);
		panel.add(lblCantidad);

		txtCantidad = new JTextField();
		txtCantidad.addKeyListener(new KeyAdapter() {
			@Override
			/**
			 * ESTA PEQUE�A FUNCION NOS IMPIDE METER LETRAS, SIMBOLOS, ETC LO QUE HACE QUE
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
		txtCantidad.setBounds(231, 44, 30, 22);
		panel.add(txtCantidad);

		JButton btnVender = new JButton("COMPRAR");
		btnVender.setIcon(new ImageIcon(Compra.class.getResource("/imagenes/icons8-carrito-de-compras-filled-50.png")));
		btnVender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/**
				 * EN ESTA PARTE COMPROBAMOS COMPARANDO EL SELECT DEL STOCK CON LO QUE VAMOS A
				 * VENDER CONTROLANDO STOCK
				 */

				Connection con = null;

				try {
					if (txtCantidad.getText().equals("") || txtId.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "HAY QUE RELLENAR TODOS LOS CAMPOS");

					} else {

						con = getConexion();
						ps = con.prepareStatement("UPDATE stock SET cantidad=(cantidad +?) WHERE idmovil=?");

						ps.setInt(1, Integer.parseInt(txtCantidad.getText()));
						ps.setInt(2, Integer.parseInt(txtId.getText()));

						int res = ps.executeUpdate();

						if (res > 0) {
							JOptionPane.showMessageDialog(null, "COMPRA CORRECTA");

						} else {
							JOptionPane.showMessageDialog(null, "ERROR EN COMPRA");

						}
						con.close();
					}
				} catch (Exception err) {
					System.err.println(err);
				}

			}

		});
		btnVender.setBounds(305, 24, 144, 63);
		panel.add(btnVender);

		JButton btnEnviarCorreo = new JButton("");
		btnEnviarCorreo.setIcon(new ImageIcon(Compra.class.getResource("/imagenes/IMPRIMIR.png")));
		btnEnviarCorreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * FUNCION QUE IMPRIME UNA TABLA
				 */
				MessageFormat header =new MessageFormat("STOCK");
				MessageFormat pie =new MessageFormat("P�gina 1");
				try {
					jtPrecio.print(JTable.PrintMode.FIT_WIDTH, header, pie);
					
				}catch(java.awt.print.PrinterException f) {
					System.err.format("Error de impresion", f.getMessage());
					
				}
				
			}
		});
		btnEnviarCorreo.setBounds(470, 24, 155, 63);
		panel.add(btnEnviarCorreo);

	}
}