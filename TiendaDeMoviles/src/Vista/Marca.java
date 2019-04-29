package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Modelo.Conexion;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Marca extends JFrame {

	private JPanel contentPane;
	private JTextField txtMarca;
	private JButton btnBuscar;
	private JTable jtMarca;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Marca() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 917, 562);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		setLocationRelativeTo(null);

		/**
		 * AL PULSAR EL JBUTTON NOS APARECE UNA JTABLE CON LOS MOVILES CON 
		 * LAS CARACTERISTICAS QUE BUSCAMOS
		 */
		JButton btnMarca = new JButton("Buscar");
		btnMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				jtMarca = new JTable();
				jtMarca.setBounds(22, 22, 561, 338);
				panel.add(jtMarca);
				try {
					/**
					 * OBJETO CON LAS COLUMNAS QUE VAMOS A MOSTAR Y COINCIDEN CON LA DE NUESTRA TABLA STOCK
					 * EN BBDD USUARIOSTIENDAMOVILES
					 */
					Object[][] data = new Object[0][0];
					String[] datos = { "ID", "STOCK", "MARCA", "MODELO", "PRECIO", "GB", "PANTALA INCH", "BATERIA","CÁMARA"};
					DefaultTableModel modelo = new DefaultTableModel(data, datos);
					jtMarca.setModel(modelo);
					JScrollPane scroll2 = new JScrollPane(jtMarca);
					getContentPane().add(scroll2, BorderLayout.NORTH);

					/**
					 * HACEMOS CONEXIÓN CON LA BBDD USUARIOSTIENDAMOVILES
					 */
					PreparedStatement ps = null;
					ResultSet rs = null;
					Conexion conn = new Conexion();
					Connection con = conn.getConexion();
					/**
					 * VAMOS A PASARLE LA SIGUIENTE SELECT, METIENDOLE EL TEXTO QUE SE HA INTRODUCIDO EN EL 
					 * JTEXT, QUE LLAMAMOS TXTMIN Y TXT MAX RESPECTIVAMENTE PARA RANGO DE PRECIO
					 */
					String sql = "SELECT * FROM stock WHERE marca="+"'"+ textField.getText()+"'";
					
					
					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();

					ResultSetMetaData rsMd = rs.getMetaData();
					int cantidadColumnas = rsMd.getColumnCount();
					/**
					 * MIENTRAS EXISTA UN SIGUIENTE SE SEGUIRA INSERTARNDO EN LA TABLA QUE SE 
					 * VA A MOSTRAR
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

		btnMarca.setBounds(452, 11, 164, 23);
		panel.add(btnMarca);

		;

		JLabel lblNewLabel = new JLabel("Introduce una marca para mostrar todos los modelos ");
		lblNewLabel.setBounds(10, 15, 265, 14);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(309, 12, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
	}
}
