package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
/**
 * ES EL MAIN DE LA APLICACION Y ES EL PRIMER JFRAME QUE SE MUESTRA.
 * SOLO MUESTRA UNA BIENVENIDA Y TIENE DOS BOTONES, UNO PARA REGISTRAR 
 * A UN USUARIO NUEVO Y OTRO PARA QUE SE LOGEE EL QUE YA LO ESTA
 * @author J.Andrés Fernández
 *
 */
public class Inicio extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creamos el frame de inicio
	 */
	public Inicio() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		  setLocationRelativeTo(null);
		
		/**
		 * Creamos el boton de registrar el cual inicia el formulario de registro
		 */
		JButton btnRegistrar = new JButton("Registrar Vendedor");
		btnRegistrar.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Registro Registro = new Registro();
				Registro.setVisible(true);
				
			}
		});
		btnRegistrar.setBounds(12, 109, 193, 62);
		contentPane.add(btnRegistrar);
		
		/**
		 * Creamos el boton de iniciar sesion el cual inicia el formulario de inicio de sesion
		 */
		JButton btnIngresar = new JButton("Iniciar Sesi\u00F3n");
		btnIngresar.setFont(new Font("Sitka Subheading", Font.BOLD | Font.ITALIC, 21));
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login Login = new Login();
				Login.setVisible(true);
			}
		});
		btnIngresar.setBounds(254, 109, 166, 131);
		contentPane.add(btnIngresar);
		
		JLabel lblBienvenido = new JLabel("BIENVENIDO");
		lblBienvenido.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		lblBienvenido.setBounds(163, 13, 163, 62);
		contentPane.add(lblBienvenido);
		
		JButton btnRegistrarSupervisor = new JButton("Registrar Supervisor");
		btnRegistrarSupervisor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				RegistroSupervisor RegistroSupervisor = new RegistroSupervisor();
				RegistroSupervisor.setVisible(true);
			}
		});
		btnRegistrarSupervisor.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		btnRegistrarSupervisor.setBounds(12, 182, 193, 62);
		contentPane.add(btnRegistrarSupervisor);
	}
}
