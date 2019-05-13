package Vista.RolGerente;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.Usuarios;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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
		btnCambiarPrecioMoviles.setBounds(46, 25, 196, 62);
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
		btnVerltimoAcceso.setIcon(new ImageIcon(Gerente.class.getResource("/imagenes/icons8-hombre-men\u00FA-de-usuario-32.png")));
		btnVerltimoAcceso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ControlAcceso ControlAcceso=new ControlAcceso();
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
	}
}
