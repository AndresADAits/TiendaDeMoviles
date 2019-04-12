package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.SqlUsuarios;
import Modelo.hash;
import Modelo.usuarios;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class registro extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private JPasswordField txtConfirmaPassword;
	private JTextField txtNombre;
	private JTextField txtCorreo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registro frame = new registro();
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
	public registro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(105, 24, 46, 14);
		contentPane.add(lblNewLabel);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(161, 22, 138, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblContrasea.setBounds(86, 55, 65, 14);
		contentPane.add(lblContrasea);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(161, 53, 138, 20);
		contentPane.add(txtPassword);

		JLabel lblConfirmarContrasea = new JLabel("Confirmar  Contrase\u00F1a:");
		lblConfirmarContrasea.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblConfirmarContrasea.setBounds(26, 86, 124, 14);
		contentPane.add(lblConfirmarContrasea);

		txtConfirmaPassword = new JPasswordField();
		txtConfirmaPassword.setBounds(161, 84, 138, 20);
		contentPane.add(txtConfirmaPassword);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(105, 117, 57, 14);
		contentPane.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(161, 115, 138, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCorreo.setBounds(105, 148, 46, 14);
		contentPane.add(lblCorreo);

		txtCorreo = new JTextField();
		txtCorreo.setBounds(161, 146, 138, 20);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);

		JButton btnRegistrar = new JButton("Registrar en sistema");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SqlUsuarios modSql = new SqlUsuarios();
				usuarios mod = new usuarios();

				String pass = new String(txtPassword.getPassword());
				String passCon = new String(txtConfirmaPassword.getPassword());
//Comprueba que todos los campos estan siendo utilizados, y si alguno  no lo esta nos manda un mensaje con aviso
				if (txtUsuario.getText().equals("") || pass.equals("") || passCon.equals("")
						|| txtNombre.getText().equals("") || txtCorreo.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "No se pueden dejar vacios ninguno de los campos");
				} else {

					if (pass.equals(passCon)) {
						// comprobar si ya esta registrado un usuario
						if (modSql.existeUsuario(txtUsuario.getText()) == 0) {
							
							if (modSql.esEmail(txtCorreo.getText())) {
								String nuevoPass = hash.sha1(pass);

								mod.setUsuario(txtUsuario.getText());
								mod.setPassword(nuevoPass);
								mod.setNombre(txtNombre.getText());
								mod.setCorreo(txtCorreo.getText());
								mod.setId_tipo(1);

								if (modSql.registrar(mod)) {
									JOptionPane.showMessageDialog(null, "Se ha guardadado correctamente");
									limpiar();
								} else {
									JOptionPane.showMessageDialog(null, "Error: no se ha podido guardar registro");
								}
							}else {
								JOptionPane.showMessageDialog(null, "El formato de correo no es correcto");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Error: Ese usuario ya esta registrado");
						}
//Si las contraseñas no coinciden nos salta ventana con aviso
					} else {
						JOptionPane.showMessageDialog(null, "Error: Las contraseñas introducidas no son iguales");
					}
				}
			}

//limpia registro
			private void limpiar() {
				txtUsuario.setText("");
				txtPassword.setText("");
				txtConfirmaPassword.setText("");
				txtNombre.setText("");
				txtCorreo.setText("");

			}
		});
		btnRegistrar.setBounds(161, 198, 118, 23);
		contentPane.add(btnRegistrar);
	}
}
