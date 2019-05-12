package Vista.HomeRegistro;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.Usuarios;
import Vista.RolGerente.Gerente;
import Vista.RolSupervisor.Supervisor;
import Vista.RolVendedor.Vendedor;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;
import java.awt.Color;

/**
 * ESTA VISTA ES A LA QUE PODRAN ACCEDER LOS USUARIOS QUE YA ESTEN REGISTRADOS
 * ANTERIORMENTE Y QUE TENDRAN QUE PASAR ANTES POR INICIO, REGISTRO Y LOGIN, O
 * DIRECTAMENTE LOGIN DESDE INICIO SI YA ESTAN REGISTRADOS
 * 
 * @author J.Andrés Fernández
 *
 */
public class Home extends JFrame {

	private JPanel contentPane;
	// variable para llamar a los datos del usuario y que no resetee
	Usuarios mod;

	/**
	 * SOBRECARGA DE METODOS Aqui hago que segun los privilegios del usuario se
	 * muesten determinados botones que nos daran acceso a otra funcionalidades
	 */

	public Home(Usuarios mod) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 881, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setIconImage(new ImageIcon(getClass().getResource("/imagenes/ICON.png")).getImage());

		JButton btnVendedor = new JButton("Vendedor");
		btnVendedor.setFont(new Font("Sitka Display", Font.BOLD | Font.ITALIC, 27));
		btnVendedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * ACCESO AL FRAME DE VENDEDOR, EN ESTE Y LOS DEMAS ROLES RECIBE MOD PARA PODER
				 * IMPRIMIR EN UN JLABEL DE FORMA DINAMICA EL USUARIO, ROL Y ULTIMO ACCESO
				 */
				Vendedor Vendedor = new Vendedor(mod);
				Vendedor.setVisible(true);
			}
		});
		contentPane.setLayout(null);
		btnVendedor.setBounds(40, 209, 178, 179);
		contentPane.add(btnVendedor);

		JButton btnSupervisor = new JButton("Supervisor");
		btnSupervisor.setFont(new Font("Sitka Display", Font.BOLD | Font.ITALIC, 27));
		btnSupervisor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ACCESO A JFRAME SUPERVISOR
				Supervisor Supervisor = new Supervisor(mod);
				Supervisor.setVisible(true);
			}
		});
		btnSupervisor.setBounds(348, 209, 178, 179);
		contentPane.add(btnSupervisor);

		JButton btnGerente = new JButton("Gerente");
		btnGerente.setFont(new Font("Sitka Display", Font.BOLD | Font.ITALIC, 27));
		btnGerente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ACCESO A JFRAME GERENTE
				Gerente Gerente = new Gerente(mod);
				Gerente.setVisible(true);
			}
		});
		btnGerente.setBounds(648, 209, 178, 179);
		contentPane.add(btnGerente);

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
		// Y EL ULTIMO ACCESO
		JLabel lblTipo = new JLabel(mod.getNombre_tipo());
		lblTipo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblTipo.setBounds(153, 57, 86, 19);
		contentPane.add(lblTipo);

		JLabel lblUltAcceso = new JLabel("ULT ACCESO:");
		lblUltAcceso.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblUltAcceso.setBounds(25, 89, 99, 19);
		contentPane.add(lblUltAcceso);
		/**
		 * ESTO HABRIA QUE PULIRLO PORQUE AL ACTUALIZAR EL ULTIMO ACCESO CADA VEZ QUE
		 * ENTRA SIEMPRE APARECE EL MISMO MOMENTO EN EL QUE SE LOGEA. ESTO TB SERA
		 * INTERESANTE PARA UN REGISTRO QUE DE LA ACTIVIDAD DEL USUARIO EN LA APLICACION
		 */
		JLabel lblAcceso = new JLabel(mod.getLast_session());
		lblAcceso.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblAcceso.setBounds(153, 89, 332, 19);
		contentPane.add(lblAcceso);
		// inicia en el centro de la pantalla
		setLocationRelativeTo(null);
		this.mod = mod;

		if (mod.getId_tipo() == 3) {

		} else if (mod.getId_tipo() == 2) {

			btnGerente.setVisible(false);

		} else {

			if (mod.getId_tipo() == 1) {

				btnSupervisor.setVisible(false);
				btnGerente.setVisible(false);

			} else {
				// por si se encuentra un atajo inesperado.
				JOptionPane.showMessageDialog(null, "Algo esta fallando....");
			}

		}

	}

}
