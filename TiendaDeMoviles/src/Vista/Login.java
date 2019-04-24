package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.SqlUsuarios;
import Modelo.Hash;
import Modelo.Usuarios;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
/**
 * MUESTRA UN JFRAME PARA LOGEARSE QUE COMPRAR EL USUARIO Y CONTRASEÑA 
 * QUE INTRODUCIRMOS CON LA QUE TENEMOS EN LA BASE DE DATOS. EN CASO DE QUE 
 * COINCIDA NOS PERMITE PASAR A EL HOME, Y EN CASO CONTRARIO NOS MUESTRA UN
 * MENSAJE DICIENDO QUE LA CONTRASEÑA ES INCORRECTA
 * TAMBIEN ACTUALIZA EL DATETIME DE LA COLUMNA LAST_SESSION DE LA TABLA USUARIO,
 * NUESTRO ULTIMO ACCESO
 *  
 * @author J.Andrés Fernández
 *
 */
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 * 
	 */
	

	/**
	 * Creamos la ventana de login
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		  setLocationRelativeTo(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Sitka Heading", Font.BOLD | Font.ITALIC, 15));
		lblUsuario.setBounds(127, 80, 103, 17);
		contentPane.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Sitka Heading", Font.BOLD | Font.ITALIC, 15));
		txtUsuario.setBounds(198, 80, 129, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Sitka Heading", Font.BOLD | Font.ITALIC, 15));
		lblContrasea.setBounds(101, 126, 103, 17);
		contentPane.add(lblContrasea);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Sitka Heading", Font.BOLD | Font.ITALIC, 15));
		txtPassword.setBounds(198, 126, 129, 20);
		contentPane.add(txtPassword);
		
		JButton btnIngresar = new JButton("Iniciar Sesi\u00F3n");
		btnIngresar.setFont(new Font("Sitka Heading", Font.BOLD | Font.ITALIC, 15));
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SqlUsuarios modSql = new SqlUsuarios();
				Usuarios mod = new Usuarios();
				
				Date date = new Date();
				DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				String pass = new String(txtPassword.getPassword());
				
				if(!txtUsuario.getText().equals("") && !pass.equals("")) {
					String nuevoPass =Hash.sha1(pass);
					
					mod.setUsuario(txtUsuario.getText());
					mod.setPassword(nuevoPass);
					mod.setLast_session(fechaHora.format(date).toString());
					
					/**
					 * SI COINCIDEN DAMOS PASO A JFRAME HOME
					 */
					if(modSql.login(mod)) {
						//metodo creado en Home
						Home Home = new Home(mod);
						Home.setVisible(true);
						
					}else {
						JOptionPane.showMessageDialog(null, "Datos incorrectos");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Debe ingresar sus datos");
				}
			}
		});
		btnIngresar.setBounds(165, 196, 162, 44);
		contentPane.add(btnIngresar);
	}
}
