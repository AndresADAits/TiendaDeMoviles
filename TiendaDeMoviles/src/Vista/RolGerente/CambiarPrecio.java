package Vista.RolGerente;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Modelo.Conexion;
import Vista.RolVendedor.Marca;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CambiarPrecio extends JFrame {
	/**
	 * TODO REFRESH LA TABLA DE ARRIBA CUANDO SE REALIZA COMPRA, PODRIAS LLAMAR
	 * DESDE DONDE SE LANZA EN MENSAJE COMPRA CORRECTA
	 */

	private JPanel contentPane;

	private JTextField txtPrecio;
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

	public CambiarPrecio() {
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

			/**
			 * AL PULSAR EL JBUTTON NOS APARECE UNA JTABLE CON LOS MOVILES CON LAS
			 * CARACTERISTICAS QUE BUSCAMOS
			 */

			JLabel lblIdMovil = new JLabel("ID MOVIL");
			lblIdMovil.setBounds(12, 24, 56, 16);
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
			txtId.setBounds(80, 21, 30, 22);
			panel.add(txtId);
			txtId.setColumns(10);

			JLabel lblCantidad = new JLabel("CANTIDAD");
			lblCantidad.setBounds(161, 24, 65, 16);
			panel.add(lblCantidad);

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
			txtPrecio.setBounds(227, 21, 30, 22);
			panel.add(txtPrecio);

			JButton btnCambiar = new JButton("Cambiar");
			btnCambiar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					/**
					 * EN ESTA PARTE COMPROBAMOS COMPARANDO EL SELECT DEL STOCK CON LO QUE VAMOS A
					 * VENDER CONTROLANDO STOCK
					 */

					Connection con = null;

					try {
						con = getConexion();
						ps = con.prepareStatement("UPDATE stock SET precio=? WHERE idmovil=?");

						ps.setInt(1, Integer.parseInt(txtPrecio.getText()));
						ps.setInt(2, Integer.parseInt(txtId.getText()));

						int res = ps.executeUpdate();

						if (res > 0) {
							JOptionPane.showMessageDialog(null, "CAMBIO CORRECTO");

						} else {
							JOptionPane.showMessageDialog(null, "ERROR AL CAMBIAR PRECIO");

						}
						con.close();
					} catch (Exception err) {
						System.err.println(err);
					}

				}

			});
			btnCambiar.setBounds(288, 4, 139, 56);
			panel.add(btnCambiar);

		}
}
