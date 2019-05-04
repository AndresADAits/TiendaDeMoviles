package Vista.RolSupervisor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * JFRAME DE ACCESO  PARA USUARIO GERENTE Y SUPERVISOR
 *  @author J.Andrés Fernández
 */

public class Supervisor extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Supervisor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1002, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSupervisirEsVendedor = new JLabel("Supervisi\u00F3r:  es vendedor y:inserts y delete en BBDD m\u00F3viles");
		lblSupervisirEsVendedor.setBounds(12, 0, 347, 86);
		contentPane.add(lblSupervisirEsVendedor);
		
		JButton btnComprarStock = new JButton("Comprar Stock");
		btnComprarStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Compra Compra= new Compra();
				Compra.setVisible(true);
				
			}
		});
		btnComprarStock.setBounds(12, 220, 117, 70);
		contentPane.add(btnComprarStock);
		
		JButton btnAadirModelo = new JButton("A\u00F1adir Modelo");
		btnAadirModelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAadirModelo.setBounds(140, 220, 117, 70);
		contentPane.add(btnAadirModelo);
		
		JButton btnQuitarModelo = new JButton("Quitar Modelo");
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
		lblLosAvisosDe.setBounds(308, 75, 337, 50);
		contentPane.add(lblLosAvisosDe);
	}
}
