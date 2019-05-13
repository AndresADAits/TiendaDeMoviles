package Vista.RolVendedor;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

public class Capacidad extends JFrame {
	private JPanel contentPane;

	private JTable jtPrecio;

	/**
	 * SE MUESTRA EL JFRAME EN EL QUE INTRODUCIMOS EL RANGO DE PRECIO Y AL PULSAR
	 * BUSCAR APARECEN LOS MOVILES CON LAS CONDICIONES QUE ESTAMOS BUSCANDO
	 */
	public Capacidad() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 919, 653);
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
		JButton btnCargar = new JButton("Ordenado por Capacidad");
		btnCargar.setIcon(new ImageIcon(Capacidad.class.getResource("/imagenes/CAPACIDAD.png")));
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
					 * 
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
					String sql = "SELECT * FROM stock  ORDER BY capacidad";

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

		btnCargar.setBounds(275, 13, 315, 126);
		panel.add(btnCargar);
	}
}
