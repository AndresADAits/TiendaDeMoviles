package Vista.RolSupervisor;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.Usuarios;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Vista.RolVendedor.Vendedor;

/**
 * JFRAME DE ACCESO PARA USUARIO GERENTE Y SUPERVISOR
 * 
 * @author J.Andrés Fernández
 */

public class Supervisor extends JFrame {
	Usuarios mod;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Supervisor(Usuarios mod) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 880, 257);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setLocationRelativeTo(null);

		JButton btnComprarStock = new JButton("Comprar Stock");
		btnComprarStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Compra Compra = new Compra();
				Compra.setVisible(true);

			}
		});
		btnComprarStock.setBounds(81, 25, 117, 70);
		contentPane.add(btnComprarStock);

		JButton btnAadirModelo = new JButton("A\u00F1adir Modelo");
		btnAadirModelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NuevoModelo NuevoModelo = new NuevoModelo();
				NuevoModelo.setVisible(true);
			}
		});
		btnAadirModelo.setBounds(303, 25, 117, 70);
		contentPane.add(btnAadirModelo);

		JButton btnQuitarModelo = new JButton("Quitar Modelo");
		btnQuitarModelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				QuitarModelo QuitarModelo = new QuitarModelo();
				QuitarModelo.setVisible(true);
			}
		});
		btnQuitarModelo.setBounds(527, 25, 117, 70);
		contentPane.add(btnQuitarModelo);

		JButton btnAvisos = new JButton("Avisos");
		btnAvisos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SinStock SinStock = new SinStock();
				SinStock.setVisible(true);

			}
		});
		btnAvisos.setBounds(733, 25, 117, 70);
		contentPane.add(btnAvisos);

		JLabel lblNombre = new JLabel(mod.getNombre());
		lblNombre.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNombre.setBounds(12, 108, 188, 50);
		contentPane.add(lblNombre);

		JLabel lblRol = new JLabel("ROL:");
		lblRol.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblRol.setBounds(191, 108, 82, 50);
		contentPane.add(lblRol);

		JLabel lblTipo = new JLabel(mod.getNombre_tipo());
		lblTipo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblTipo.setBounds(252, 108, 188, 50);
		contentPane.add(lblTipo);

		JLabel lblUltAcceso = new JLabel("ULT ACCESO:");
		lblUltAcceso.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblUltAcceso.setBounds(390, 108, 188, 50);
		contentPane.add(lblUltAcceso);

		JLabel lblAcceso = new JLabel(mod.getLast_session());
		lblAcceso.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblAcceso.setBounds(584, 108, 266, 50);
		contentPane.add(lblAcceso);
	}
}
