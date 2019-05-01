package Vista.RolVendedor;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.Usuarios;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * JFRAME DE ACCESO  TODOS LOS USUARIOS
 * 
 * VENDEDOR, SUPERVISOR Y GERENTE
 * 
 *  @author J.Andrés Fernández
 */
public class Vendedor extends JFrame {

	private JPanel contentPane;
	Usuarios mod;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Vendedor(Usuarios mod) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 626, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		  setLocationRelativeTo(null);
		
		
		
		JLabel lblUsuario = new JLabel("USUARIO:");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblUsuario.setBounds(50, 13, 77, 19);
		contentPane.add(lblUsuario);

		JLabel lblNombre = new JLabel(mod.getNombre());
		lblNombre.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNombre.setBounds(153, 13, 86, 19);
		contentPane.add(lblNombre);

		JLabel lblRol = new JLabel("ROL:");
		lblRol.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblRol.setBounds(86, 57, 37, 19);
		contentPane.add(lblRol);
// AQUI VOY A ESCRIBIR EN LOS JLABEL EL NOMBRE DEL USUARIO, EL ROL QUE TIENE
		//Y EL ULTIMO ACCESO
		JLabel lblTipo = new JLabel(mod.getNombre_tipo());
		lblTipo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblTipo.setBounds(153, 57, 86, 19);
		contentPane.add(lblTipo);

		JLabel lblUltAcceso = new JLabel("ULT ACCESO:");
		lblUltAcceso.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblUltAcceso.setBounds(25, 89, 99, 19);
		contentPane.add(lblUltAcceso);
		
		JLabel lblAcceso = new JLabel(mod.getLast_session());
		lblAcceso.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblAcceso.setBounds(153, 89, 332, 19);
		contentPane.add(lblAcceso);
		
		
		JButton btnPrecio = new JButton("PRECIO");
		btnPrecio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Precio Precio = new Precio();
				Precio.setVisible(true);
			}
		});
		btnPrecio.setBounds(0, 158, 89, 23);
		contentPane.add(btnPrecio);
		
		JButton btnMarca = new JButton("MARCA");
		btnMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Marca Marca = new Marca();
				Marca.setVisible(true);
			}
		});
		btnMarca.setBounds(101, 158, 89, 23);
		contentPane.add(btnMarca);
		
		JButton btnGb = new JButton("GB");
		btnGb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gb gb = new gb();
				gb.setVisible(true);
				
			}
		});
		btnGb.setBounds(202, 158, 89, 23);
		contentPane.add(btnGb);
		
		JButton btnInches = new JButton("INCHES");
		btnInches.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inches inches = new inches();
				inches.setVisible(true);
			}
		});
		btnInches.setBounds(303, 158, 89, 23);
		contentPane.add(btnInches);
		
		JButton btnMamperios = new JButton("mAMPERIOS");
		btnMamperios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mAmperios mAmperios = new mAmperios();
				mAmperios.setVisible(true);
			}
		});
		btnMamperios.setBounds(404, 158, 104, 23);
		contentPane.add(btnMamperios);
		
		JButton btnVender = new JButton("VENDER");
		btnVender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VenderMovil VenderMovil=new VenderMovil();
				VenderMovil.setVisible(true);}
		});
		btnVender.setBounds(198, 256, 201, 126);
		contentPane.add(btnVender);
		
		JLabel lblBusquedaPor = new JLabel("BUSQUEDA POR :");
		lblBusquedaPor.setBounds(252, 122, 135, 14);
		contentPane.add(lblBusquedaPor);
		
		JButton btnCamara = new JButton("CAMARA");
		btnCamara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				camara camara= new camara();
				camara.setVisible(true);
			}
		});
		btnCamara.setBounds(511, 157, 97, 25);
		contentPane.add(btnCamara);
	}
}
