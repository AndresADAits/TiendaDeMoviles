package Vista.RolGerente;

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
		setBounds(100, 100, 1033, 683);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblGerenteEsVendedor = new JLabel("Gerente; es vendedor y supervisor. ycambia precio m\u00F3viles");
		lblGerenteEsVendedor.setBounds(-10, 239, 344, 43);
		contentPane.add(lblGerenteEsVendedor);

		JLabel lblNewLabel = new JLabel("inserta y delete  usuarios vendedores y supervisores");
		lblNewLabel.setBounds(12, 295, 322, 62);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("muestra last session de usuario from BBDD tiendademoviles");
		lblNewLabel_1.setBounds(12, 415, 418, 56);
		contentPane.add(lblNewLabel_1);

		JButton btnCambiarPrecioMoviles = new JButton("Cambiar precio moviles");
		btnCambiarPrecioMoviles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CambiarPrecio CambiarPrecio = new CambiarPrecio();
				CambiarPrecio.setVisible(true);
			}
		});
		btnCambiarPrecioMoviles.setBounds(12, 44, 176, 43);
		contentPane.add(btnCambiarPrecioMoviles);

		JLabel lblGestinDeEmpleados = new JLabel("Gesti\u00F3n de Empleados");
		lblGestinDeEmpleados.setBounds(408, 13, 176, 16);
		contentPane.add(lblGestinDeEmpleados);

		JButton btnEliminar = new JButton("Dar de Baja");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BorrarEmpleado BorrarEmpleado = new BorrarEmpleado();
				BorrarEmpleado.setVisible(true);
			}
		});
		btnEliminar.setBounds(368, 34, 114, 62);
		contentPane.add(btnEliminar);

		JButton btnVerltimoAcceso = new JButton("\u00DAltimo Acceso");
		btnVerltimoAcceso.setBounds(504, 34, 114, 62);
		contentPane.add(btnVerltimoAcceso);

		JLabel lblQueElLast = new JLabel("que el last sesion se escriba en archivo y acceda desde aqui");
		lblQueElLast.setBounds(312, 253, 354, 62);
		contentPane.add(lblQueElLast);

		JLabel lblAlgoParaEl = new JLabel("Algo para el tema de  BIGDATA");
		lblAlgoParaEl.setBounds(246, 328, 372, 74);
		contentPane.add(lblAlgoParaEl);

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
