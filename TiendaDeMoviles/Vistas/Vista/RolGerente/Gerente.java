package Vista.RolGerente;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.Conexion;
import Modelo.Usuarios;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Gerente extends JFrame {
	Usuarios mod;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Gerente(Usuarios mod) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 880, 257);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setIconImage(new ImageIcon(getClass().getResource("/imagenes/ICON.png")).getImage());
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JButton btnCambiarPrecioMoviles = new JButton("Cambiar precio");
		btnCambiarPrecioMoviles.setIcon(new ImageIcon(Gerente.class.getResource("/imagenes/VENDER.png")));
		btnCambiarPrecioMoviles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CambiarPrecio CambiarPrecio = new CambiarPrecio();
				CambiarPrecio.setVisible(true);
			}
		});
		btnCambiarPrecioMoviles.setBounds(12, 47, 176, 62);
		contentPane.add(btnCambiarPrecioMoviles);

		JLabel lblGestinDeEmpleados = new JLabel("Gesti\u00F3n de Empleados");
		lblGestinDeEmpleados.setBounds(631, 13, 176, 16);
		contentPane.add(lblGestinDeEmpleados);

		JButton btnEliminar = new JButton("Dar de Baja");
		btnEliminar.setIcon(new ImageIcon(Gerente.class.getResource("/imagenes/ALERTA.png")));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BorrarEmpleado BorrarEmpleado = new BorrarEmpleado();
				BorrarEmpleado.setVisible(true);
			}
		});
		btnEliminar.setBounds(478, 47, 157, 62);
		contentPane.add(btnEliminar);

		JButton btnVerltimoAcceso = new JButton("\u00DAltimo Acceso");
		btnVerltimoAcceso.setIcon(
				new ImageIcon(Gerente.class.getResource("/imagenes/icons8-hombre-men\u00FA-de-usuario-32.png")));
		btnVerltimoAcceso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ControlAcceso ControlAcceso = new ControlAcceso();
				ControlAcceso.setVisible(true);
			}
		});
		btnVerltimoAcceso.setBounds(663, 47, 166, 62);
		contentPane.add(btnVerltimoAcceso);

		JLabel lblRol = new JLabel("ROL:");
		lblRol.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblRol.setBounds(190, 122, 110, 43);
		contentPane.add(lblRol);

		JLabel lblNombre = new JLabel(mod.getNombre());
		lblNombre.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNombre.setBounds(12, 122, 166, 43);
		contentPane.add(lblNombre);

		JLabel lblTipo = new JLabel(mod.getNombre_tipo());
		lblTipo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblTipo.setBounds(312, 122, 170, 43);
		contentPane.add(lblTipo);

		JLabel lblUltAcceso = new JLabel("ULT ACCESO:");
		lblUltAcceso.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblUltAcceso.setBounds(504, 122, 170, 43);
		contentPane.add(lblUltAcceso);

		JLabel lblAcceso = new JLabel(mod.getLast_session());
		lblAcceso.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblAcceso.setBounds(686, 122, 264, 43);
		contentPane.add(lblAcceso);
		/**
		 * BOTON QUE GUARDA BASE DE DATOS EN CSV
		 */

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conexion conn = new Conexion();
				Connection con = conn.getConexion();
				String sql2 = "SELECT * FROM usuario";

				try {
					/**
					 * GUARDA BASE DE DATOS USUARIO EN VISTAS/CSV/USUARIOSBBDD
					 */
					PreparedStatement ps = con.prepareStatement(sql2);
					;
					ResultSet rs2 = ps.executeQuery();

					String extension = ".csv";
					String ruta = "Vistas/csv/usuariosBBDD" + extension;
					FileWriter writer = new FileWriter(ruta);
					writer.write("Id;Usuario;Password;Nombre;Correo;Ultima Sesion;Tipo de ID\n");
					/* Siguiente linea escribe bbdd en fichero */
					while (rs2.next()) {

						writer.write(rs2.getInt("id") + ";" + rs2.getString("usuario") + ";" + rs2.getString("password")
								+ ";" + rs2.getString("nombre") + ";" + rs2.getString("correo") + ";"
								+ rs2.getString("last_session") + ";" + rs2.getString("id_tipo") + "\n");
					}
					writer.close();
					/**
					 * GUARDA BASE DE DATOS STOCK EN VISTAS/CSV/STOCKBBDD
					 */
					String sql3 = "SELECT * FROM stock";
						PreparedStatement ps2 = con.prepareStatement(sql3);
						;
						ResultSet rs3 = ps2.executeQuery();
					

						String extension2 = ".csv";
						String ruta2 = "Vistas/csv/stockBBDD" + extension2;
						FileWriter writer2 = new FileWriter(ruta2);
						writer2.write("idmovil;cantidad;marca;modelo;precio;capacidad;pantalla;bateria;camara\n");
						/* Siguiente linea escribe bbdd en fichero */
						while (rs3.next()) {

							writer2.write(rs3.getInt("idmovil") + ";" + rs3.getInt("cantidad") + ";" + rs3.getString("marca")
									+ ";" + rs3.getString("modelo") + ";" + rs3.getInt("precio") + ";"
									+ rs3.getInt("capacidad") + ";" + rs3.getInt("pantalla") +";" + rs3.getInt("bateria") +";" + rs3.getInt("camara") + "\n");

					}
					writer2.close();
					
					
					JOptionPane.showMessageDialog(null, "Bases de datos guardadas con éxito");
						} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error");
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(Gerente.class.getResource("/imagenes/CSV2.png")));
		btnNewButton.setBounds(312, 40, 65, 69);
		contentPane.add(btnNewButton);

		JLabel lblGuardarBbdd = new JLabel("Guardar BBDD");
		lblGuardarBbdd.setBounds(312, 14, 83, 14);
		contentPane.add(lblGuardarBbdd);
	}
}