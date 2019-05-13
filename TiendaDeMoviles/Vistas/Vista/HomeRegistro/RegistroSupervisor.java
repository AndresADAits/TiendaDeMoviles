package Vista.HomeRegistro;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.Hash;
import Modelo.SqlUsuarios;
import Modelo.Usuarios;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * REGISTRO PARA SUPERVISORES (SE PODRAN REGISTRAR VENDEDORES Y SUPERVISORES,
 * PERO NO UN USUARIO CON ROL GERENTE, YA QUE ESTE CONTROLARA A LOS OTROS, Y
 * SERÁ ÚNICO)
 * 
 * EL SIGUIENTE JFRAME ES UN FORMULARIO EN EL QUE PEDIMOS AL USUARIO QUE
 * INTRODUZCALOS DATOS EN EL FORMATO Y ORDEN QUE NECESITAMOS PARA QUE SE ADAPTE
 * A LA BASE DE DATOS QUE HEMOS CREADO Y MÁS CONCRETAMENTE A LA TABLA USUARIO
 * QUE SERÁ DÓNDE SE GUARDEN ESTOS DATOS.
 * 
 * ESTE REGISTRO TIENE VARIAS VALIDACIONES PARA QUE NO SE DEJEN VACIOS LOS
 * CAMPOS, QUE LA CONTRASEÑA SERA INTRODUCIDA DOS VECES DE FORMA CORRECTA, EL
 * FORMATO DE CORREO SEA CORRECTO, ETC.
 *
 * @author J.Andrés Fernández
 *
 */
public class RegistroSupervisor extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private JPasswordField txtConfirmaPassword;
	private JTextField txtNombre;
	private JTextField txtCorreo;

	/**
	 * Launch the application.
	 */

	public RegistroSupervisor() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setIconImage(new ImageIcon(getClass().getResource("/imagenes/ICON.png")).getImage());
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setFont(new Font("Sitka Display", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(105, 24, 57, 14);
		contentPane.add(lblNewLabel);

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Sitka Display", Font.BOLD | Font.ITALIC, 15));
		txtUsuario.setBounds(186, 21, 138, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Sitka Display", Font.BOLD | Font.ITALIC, 15));
		lblContrasea.setBounds(86, 55, 88, 14);
		contentPane.add(lblContrasea);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Sitka Display", Font.BOLD | Font.ITALIC, 15));
		txtPassword.setBounds(186, 54, 138, 20);
		contentPane.add(txtPassword);

		JLabel lblConfirmarContrasea = new JLabel("Confirmar  Contrase\u00F1a:");
		lblConfirmarContrasea.setFont(new Font("Sitka Display", Font.BOLD | Font.ITALIC, 15));
		lblConfirmarContrasea.setBounds(14, 90, 148, 14);
		contentPane.add(lblConfirmarContrasea);

		txtConfirmaPassword = new JPasswordField();
		txtConfirmaPassword.setFont(new Font("Sitka Display", Font.BOLD | Font.ITALIC, 15));
		txtConfirmaPassword.setBounds(186, 87, 138, 20);
		contentPane.add(txtConfirmaPassword);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Sitka Display", Font.BOLD | Font.ITALIC, 15));
		lblNombre.setBounds(105, 117, 57, 14);
		contentPane.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Sitka Display", Font.BOLD | Font.ITALIC, 15));
		txtNombre.setBounds(186, 114, 138, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setFont(new Font("Sitka Display", Font.BOLD | Font.ITALIC, 15));
		lblCorreo.setBounds(105, 148, 46, 14);
		contentPane.add(lblCorreo);

		txtCorreo = new JTextField();
		txtCorreo.setFont(new Font("Sitka Display", Font.BOLD | Font.ITALIC, 15));
		txtCorreo.setBounds(186, 145, 138, 20);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);

		JButton btnRegistrar = new JButton("Dar de alta");
		btnRegistrar.setFont(new Font("Sitka Display", Font.BOLD | Font.ITALIC, 15));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SqlUsuarios modSql = new SqlUsuarios();
				Usuarios mod = new Usuarios();

				String pass = new String(txtPassword.getPassword());
				String passCon = new String(txtConfirmaPassword.getPassword());

				// VALIDACIÓN PARA QUE NO SE INTRODUZCAN CAMPOS VACIOS
				if (txtUsuario.getText().equals("") || pass.equals("") || passCon.equals("")
						|| txtNombre.getText().equals("") || txtCorreo.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Hay campos vacíos, debe rellenar todos los campos");
				} else {

					// QUE LAS CLAVES SEAN IGUALES

					if (pass.equals(passCon)) {

						// COMPROBACION DE SI USUARIO YA EXISTE

						if (modSql.existeUsuario(txtUsuario.getText()) == 0) {
							// VALIDACIÓN DE CORREO COMENTADA EN SQLUSUARIOS

							if (modSql.esEmail(txtCorreo.getText())) {

								String nuevoPass = Hash.sha1(pass);

								mod.setUsuario(txtUsuario.getText());
								mod.setPassword(nuevoPass);
								mod.setNombre(txtNombre.getText());
								mod.setCorreo(txtCorreo.getText());
								// ESTO HACE QUE POR DEFECTO EL TIPO DE USUARIO SEA 2, O SEA SUPERVISOR
								mod.setId_tipo(2);
								// MENSAJES PARA LAS DISTINTAS POSIBILIDAD

								if (modSql.registrar(mod)) {
									JOptionPane.showMessageDialog(null, "Registro Guardado");
									limpiar();
								} else {
									JOptionPane.showMessageDialog(null, "Error al guardar el registro");
								}
							} else {
								JOptionPane.showMessageDialog(null, "El correo electrónico no es válido");
							}
						} else {
							JOptionPane.showMessageDialog(null, "El usuario ya existe");
						}

					} else {
						JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
					}
				}
			}

			// VACIAMOS EL REGISTRO AL TERMINAR
			public void limpiar() {
				txtUsuario.setText("");
				txtPassword.setText("");
				txtConfirmaPassword.setText("");
				txtNombre.setText("");
				txtCorreo.setText("");
			}
		});
		btnRegistrar.setBounds(161, 198, 128, 42);
		contentPane.add(btnRegistrar);
	}
}
