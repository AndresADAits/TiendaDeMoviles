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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class gb extends JFrame {

	private JPanel contentPane;
	private JTable jtPrecio;
	private JTextField txtMin;
	private JTextField txtMax;
	private JLabel lblY;
	private JLabel lblNewLabel_1;

	/**
	 * SE MUESTRA EL JFRAME EN EL QUE INTRODUCIMOS EL RANGO DE CAPACIDAD EN GB Y AL
	 * PULSAR BUSCAR APARECEN LOS MOVILES CON LAS CONDICIONES QUE ESTAMOS BUSCANDO
	 */
	public gb() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 917, 562);
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
		JButton btnCargar = new JButton("Mostrar Ascendente");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jtPrecio = new JTable();
				jtPrecio.setBounds(22, 22, 561, 338);
				panel.add(jtPrecio);
				try {
					if (txtMin.getText().equals("") || txtMax.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "HAY QUE RELLENAR TODOS LOS CAMPOS");

					} else {
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
						 * RANGO DE GB
						 */
						String sql = "SELECT * FROM stock WHERE capacidad BETWEEN " + txtMin.getText() + " AND "
								+ txtMax.getText() + " ORDER BY capacidad";

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

			}

		});

		btnCargar.setBounds(452, 11, 164, 23);
		panel.add(btnCargar);

		txtMin = new JTextField();
		txtMin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				char c = e.getKeyChar();
				if (c < '0' || c > '9')
					e.consume();

			}
		});
		txtMin.setBounds(214, 12, 75, 22);
		panel.add(txtMin);
		txtMin.setColumns(10);

		JLabel lblNewLabel = new JLabel("Introduce un rango de GB entre");
		lblNewLabel.setBounds(10, 15, 185, 14);
		panel.add(lblNewLabel);

		txtMax = new JTextField();
		txtMax.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				char c = e.getKeyChar();
				if (c < '0' || c > '9')
					e.consume();

			}
		});
		txtMax.setColumns(10);
		txtMax.setBounds(345, 11, 75, 22);
		panel.add(txtMax);

		lblY = new JLabel("GB       y ");
		lblY.setBounds(299, 15, 46, 14);
		panel.add(lblY);

		lblNewLabel_1 = new JLabel("GB");
		lblNewLabel_1.setBounds(430, 15, 46, 14);
		panel.add(lblNewLabel_1);

		JButton btnMostrarAscendente = new JButton("Mostrar Descendente");
		btnMostrarAscendente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				jtPrecio = new JTable();
				jtPrecio.setBounds(22, 22, 561, 338);
				panel.add(jtPrecio);
				try {
					if (txtMin.getText().equals("") || txtMax.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "HAY QUE RELLENAR TODOS LOS CAMPOS");

					} else {
						/**
						 * OBJETO CON LAS COLUMNAS QUE VAMOS A MOSTAR Y COINCIDEN CON LA DE NUESTRA
						 * TABLA STOCK EN BBDD USUARIOSTIENDAMOVILES
						 */
						Object[][] data = new Object[0][0];
						String[] datos = { "ID", "STOCK", "MARCA", "MODELO", "PRECIO", "GB", "PANTALA INCH", "BATERIA",
								"CÁMARA" };
						DefaultTableModel modelo = new DefaultTableModel(data, datos);
						jtPrecio.setModel(modelo);
						JScrollPane scroll2 = new JScrollPane(jtPrecio);
						getContentPane().add(scroll2, BorderLayout.NORTH);

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
						String sql = "SELECT * FROM stock WHERE capacidad BETWEEN " + txtMin.getText() + " AND "
								+ txtMax.getText() + " ORDER BY capacidad DESC";

						ps = con.prepareStatement(sql);
						rs = ps.executeQuery();

						ResultSetMetaData rsMd = rs.getMetaData();
						int cantidadColumnas = rsMd.getColumnCount();
						/**
						 * MIENTRAS EXISTA UN SIGUIENTE SE SEGUIRA INSERTANDO EN LA TABLA QUE SE VA A
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

			}
		});
		btnMostrarAscendente.setBounds(645, 10, 164, 23);
		panel.add(btnMostrarAscendente);

	}
}
