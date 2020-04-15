package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import rmi.client.Client;

import view.GlobalVariables;
public class LoginFrame{

	private JFrame frame;
	private JPasswordField passwordField;
	private JTextField textField;
	public boolean succeeded;
	 public BufferedWriter fileMovements =  creatingFile();//CREATING THE FILE (Call the Function to create the file) Store all the logins details
	public  StringWriter writemike = null;
	boolean reverse = false;
	boolean stop=true;
	int x=0;
	int times=1;
	Timer t;
    Client c;
    //int userID;
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
	 * @throws MalformedURLException 
	 * @throws InterruptedException 
	 */
	public LoginFrame() throws MalformedURLException, InterruptedException {
		
		c = new Client();
		initialize();
		
	}
		

	/**
	 * Initialize the contents of the frame.
	 * @throws MalformedURLException 
	 * @throws InterruptedException 
	 */
	private void initialize() throws MalformedURLException, InterruptedException {
		
		frame = new JFrame("User Login");
	
		frame.setBounds(100, 100, 580, 429);
		frame.setSize(330, 350);
		frame.setLocation(570, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		final JLabel welcome = new JLabel("Welcome");
		welcome.setBounds(150,30,200,100);
		frame.getContentPane().add(welcome);
		welcome.setFont(new Font("Arial",Font.BOLD,25));
	
	
				 
				   welcome.setLocation(108,15);
				
		
		
		
		
	
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(30, 117, 84, 19);
		lblUsername.setFont(new Font("Arial", Font.BOLD, 16));
		frame.getContentPane().add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(120, 117, 147, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(30, 173, 83, 19);
		lblPassword.setFont(new Font("Arial", Font.BOLD, 16));
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(120, 172, 147, 22);
		frame.getContentPane().add(passwordField);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(160, 227, 71, 25);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(85, 227, 63, 25);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		    
				GlobalVariables.userID = c.login(getUsername(), getPassword());
				System.out.println(GlobalVariables.userID);
				if (GlobalVariables.userID !=-1){
					  GlobalVariables.user = getUsername();
					 JOptionPane.showMessageDialog(null,
	                            "Hi " + GlobalVariables.user + "! You have successfully logged in.",
	                            "Login",
	                            JOptionPane.INFORMATION_MESSAGE);
					  succeeded = true;
					  MonthlyView j = new MonthlyView();
					  System.out.println("Perfect!");
					  frame.dispose();
				
					  try {
						fileMovements.append(getUsername()+" got into the system");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					  try {
						fileMovements.newLine();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					  
					  try {
						fileMovements.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					  
				}else{
					try {
						fileMovements.append(getUsername() + "     'trying to access to the system' with that password :  " +getPassword());
						fileMovements.newLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
		btnForgottenPassword.setBounds(74, 260, 170, 25);
		btnForgottenPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,
						"Contact System Administrator",
						"Login",
						JOptionPane.ERROR_MESSAGE);
			}
		});
		
		frame.getContentPane().add(btnForgottenPassword);
		
		Icon icon = new ImageIcon("res/11195954_10206295769055154_1179239354_n.gif");
	        JLabel label = new JLabel(icon);
	        label.setBounds(-10, -110, 250, 330);
	        
			frame.getContentPane().add(label);
			
		
	
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

	

	public BufferedWriter creatingFile() {

		try {
			 BufferedWriter tofile = new BufferedWriter(new FileWriter(
					"LoginInformation.txt"));
			
			 return tofile;
			
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
	
}
