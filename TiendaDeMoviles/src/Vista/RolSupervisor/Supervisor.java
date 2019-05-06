package Vista.RolSupervisor;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.Usuarios;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setBounds(100, 100, 1002, 350);
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
		btnComprarStock.setBounds(12, 220, 117, 70);
		contentPane.add(btnComprarStock);

		JButton btnAadirModelo = new JButton("A\u00F1adir Modelo");
		btnAadirModelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NuevoModelo NuevoModelo = new NuevoModelo();
				NuevoModelo.setVisible(true);
			}
		});
		btnAadirModelo.setBounds(140, 220, 117, 70);
		contentPane.add(btnAadirModelo);

		JButton btnQuitarModelo = new JButton("Quitar Modelo");
		btnQuitarModelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			QuitarModelo QuitarModelo=new QuitarModelo();
			QuitarModelo.setVisible(true);
			}
		});
		btnQuitarModelo.setBounds(269, 220, 117, 70);
		contentPane.add(btnQuitarModelo);

		JButton btnAvisos = new JButton("Avisos");
		btnAvisos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAvisos.setBounds(398, 220, 117, 70);
		contentPane.add(btnAvisos);

		JLabel lblLosAvisosDe = new JLabel("Los avisos de que no hay stock se esciben en fichero");
		lblLosAvisosDe.setBounds(552, 230, 337, 50);
		contentPane.add(lblLosAvisosDe);
		
		
		
		JLabel lblNombre = new JLabel(mod.getNombre());
		lblNombre.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNombre.setBounds(12, 13, 188, 50);
		contentPane.add(lblNombre);
		
		JLabel lblRol = new JLabel("ROL:");
		lblRol.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblRol.setBounds(212, 13, 82, 50);
		contentPane.add(lblRol);
		
		JLabel lblTipo = new JLabel(mod.getNombre_tipo());
		lblTipo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblTipo.setBounds(306, 13, 188, 50);
		contentPane.add(lblTipo);
		
		JLabel lblUltAcceso = new JLabel("ULT ACCESO:");
		lblUltAcceso.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblUltAcceso.setBounds(506, 13, 188, 50);
		contentPane.add(lblUltAcceso);
		
		JLabel lblAcceso = new JLabel(mod.getLast_session());
		lblAcceso.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblAcceso.setBounds(706, 13, 266, 50);
		contentPane.add(lblAcceso);
	}
}
