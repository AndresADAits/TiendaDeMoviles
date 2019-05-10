package Vista.RolGerente;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


import Modelo.Conexion;






import javax.swing.ImageIcon;
import java.awt.Toolkit;



public class BorrarEmpleado extends JFrame {

	/**
	 * TODO REFRESH LA TABLA DE ARRIBA CUANDO SE REALIZA COMPRA, PODRIAS LLAMAR
	 * DESDE DONDE SE LANZA EN MENSAJE COMPRA CORRECTA
	 */

	
	private JPanel contentPane;

	private JTextField txtId;
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

	public BorrarEmpleado() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\usuario\\Desktop\\PDF.png"));
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
			String[] datos = { "ID", "USUARIO", "PASSWORD", "NOMBRE", "CORREO", "LAST SESSION", "TIPO DE USUARIO" };
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
			String sql = "SELECT * FROM usuario";
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
			JOptionPane.showMessageDialog(null, "No se puede mostrar la tabla USUARIOS");
		}

		JLabel lblCantidad = new JLabel("ID EMPLEADO A BORRAR");
		lblCantidad.setBounds(10, 24, 153, 16);
		panel.add(lblCantidad);

		JButton btnInsertar = new JButton("BORRAR");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/**
				 * EN ESTA PARTE COMPROBAMOS COMPARANDO EL SELECT DEL STOCK CON LO QUE VAMOS A
				 * VENDER CONTROLANDO STOCK
				 */

				Connection con = null;

				try {
					if (txtId.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "HAY QUE RELLENAR TODOS LOS CAMPOS");

					} else {
						if (Integer.parseInt(txtId.getText()) == 1) {
							JOptionPane.showMessageDialog(null,
									"NO PUEDE BORRARSE A USTED MISMO, SEÑOR GERENTE: ¿QUÉ HARIAMOS SIN USTED?");
						} else {
							con = getConexion();
							ps = con.prepareStatement("DELETE FROM `usuario` WHERE `usuario`.`id` = ?");

							ps.setInt(1, Integer.parseInt(txtId.getText()));

							int res = ps.executeUpdate();

							if (res > 0) {
								JOptionPane.showMessageDialog(null, "USUARIO BORRADO CORRECTAMENTE");

							} else {
								JOptionPane.showMessageDialog(null, "ERROR, NO BORRADO");

							}
							con.close();
						}
					}
				} catch (Exception err) {
					System.err.println(err);
				}

			}

		});
		btnInsertar.setBounds(288, 4, 139, 56);
		panel.add(btnInsertar);

		txtId = new JTextField();
		txtId.addKeyListener(new KeyAdapter() {
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
		txtId.setColumns(10);
		txtId.setBounds(207, 21, 30, 22);
		panel.add(txtId);
		
		JButton btnPdf = new JButton("PDF");
	
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * FUNCION QUE IMPRIME UNA TABLA
				 */
				MessageFormat header =new MessageFormat("Lista de Empleados");
				MessageFormat pie =new MessageFormat("Página 1");
				try {
					jtPrecio.print(JTable.PrintMode.NORMAL, header, pie);
					
				}catch(java.awt.print.PrinterException f) {
					System.err.format("Error de impresion", f.getMessage());
					
				}
				
							
	
			}
		});
		btnPdf.setBounds(473, 4, 180, 56);
		panel.add(btnPdf);

	}
	
	
}
