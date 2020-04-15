package login;

import java.awt.EventQueue;

import javax.swing.*;

import view.LoginData;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class LoginFrame{

	private JFrame frame;
	private JPasswordField passwordField;
	private JTextField textField;
	public boolean succeeded;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("User Login");
		frame.setBounds(100, 100, 503, 429);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(95, 117, 84, 19);
		lblUsername.setFont(new Font("Arial", Font.BOLD, 16));
		frame.getContentPane().add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(183, 117, 147, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(95, 173, 83, 19);
		lblPassword.setFont(new Font("Arial", Font.BOLD, 16));
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(183, 172, 147, 22);
		frame.getContentPane().add(passwordField);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(250, 227, 71, 25);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(183, 227, 63, 25);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		    //JOptionPane.showMessageDialog(null, "login button has been pressed");
				if (LoginData.authenticate(getUsername(),getPassword())){
					 JOptionPane.showMessageDialog(null,
	                            "Hi " + getUsername() + "! You have successfully logged in.",
	                            "Login",
	                            JOptionPane.INFORMATION_MESSAGE);
					  succeeded = true;
				}else{
					JOptionPane.showMessageDialog(null,
                            "Invalid username or password",
                            "Login",
                            JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		frame.getContentPane().add(btnLogin);
		frame.getContentPane().add(btnCancel);
	
		JButton btnForgottenPassword = new JButton("Forgotten Password");
		btnForgottenPassword.setBounds(183, 285, 147, 25);
		btnForgottenPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,
						"Contact System Administrator",
						"Login",
						JOptionPane.ERROR_MESSAGE);
			}
		});
		
		frame.getContentPane().add(btnForgottenPassword);
	}
	
	public void loginSystem(){
		if (succeeded = true){
			
		}
	}
	
	public String getUsername() {
		String Username = textField.getText();
		return Username;
	}
	
	public String getPassword() {
		String passText = new String(passwordField.getPassword());
		return passText;
	}

}
