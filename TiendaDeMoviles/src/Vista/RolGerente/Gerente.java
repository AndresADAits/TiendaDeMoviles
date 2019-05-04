package Vista.RolGerente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Gerente extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gerente frame = new Gerente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Gerente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 684, 531);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGerenteEsVendedor = new JLabel("Gerente; es vendedor y supervisor. ycambia precio m\u00F3viles");
		lblGerenteEsVendedor.setBounds(12, 96, 344, 43);
		contentPane.add(lblGerenteEsVendedor);
		
		JLabel lblNewLabel = new JLabel("inserta y delete  usuarios vendedores y supervisores");
		lblNewLabel.setBounds(26, 174, 322, 62);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("muestra last session de usuario from BBDD tiendademoviles");
		lblNewLabel_1.setBounds(12, 327, 418, 56);
		contentPane.add(lblNewLabel_1);
		
		JButton btnCambiarPrecioMoviles = new JButton("Cambiar precio moviles");
		btnCambiarPrecioMoviles.setBounds(12, 44, 176, 43);
		contentPane.add(btnCambiarPrecioMoviles);
		
		JLabel lblGestinDeEmpleados = new JLabel("Gesti\u00F3n de Empleados");
		lblGestinDeEmpleados.setBounds(343, 13, 176, 16);
		contentPane.add(lblGestinDeEmpleados);
		
		JButton btnNewButton = new JButton("Agregar");
		btnNewButton.setBounds(234, 34, 114, 62);
		contentPane.add(btnNewButton);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(368, 34, 114, 62);
		contentPane.add(btnEliminar);
		
		JButton btnVerltimoAcceso = new JButton("\u00DAltimo Acceso");
		btnVerltimoAcceso.setBounds(504, 34, 114, 62);
		contentPane.add(btnVerltimoAcceso);
		
		JLabel lblQueElLast = new JLabel("que el last sesion se escriba en archivo y acceda desde aqui");
		lblQueElLast.setBounds(300, 127, 354, 62);
		contentPane.add(lblQueElLast);
		
		JLabel lblAlgoParaEl = new JLabel("Algo para el tema de  BIGDATA");
		lblAlgoParaEl.setBounds(234, 249, 372, 74);
		contentPane.add(lblAlgoParaEl);
	}
}
