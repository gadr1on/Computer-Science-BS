import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.HeadlessException;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/*
 * PROGRAMMERS: Kevin Hernandez & Kimberly Pereyra
 * UNIVERSITY: Inter-american of Puerto Rico, Bayamon
 * CLASS: Advanced Programming
 * PROFESSOR: Jaime Yeckle Sanchez
 * DATE: MAY 8, 2018
 */

public class Login {
	
	public static void main(String [] args) {
		
		loginUser();
	}
	
	private static String user_pass;
	private static JTextField username;
	private static JPasswordField password;
	private static JPasswordField new_password;
	
	public static void loginUser() {
		// MAIN FRAME
		JFrame frame = new JFrame("KK AUTO PARTS");
		frame.setBackground(Color.WHITE);
		frame.getContentPane().setBackground(new Color(0, 0, 0)); 
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:/Users/Kevin/Documents/CAR_PROJECTS/Images/kk_icon.jpg"));
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(500,100,407,396); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// USERNAME (TEXT)
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsername.setBounds(70, 186, 90, 26);
		frame.getContentPane().add(lblUsername);
		frame.revalidate();
		
		// USERNAME (TEXT BOX)
		username = new JTextField();
		username.setBounds(168, 186, 160, 26);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		// PASSWORD (TEXT)
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(77, 222, 83, 26);
		frame.getContentPane().add(lblPassword);
		
		// PASSWORD (TEXT BOX)
		password = new JPasswordField();
		password.setBounds(168, 222, 160, 26);
		frame.getContentPane().add(password);
		
		// LOGIN BUTTON
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setBounds(70, 269, 260, 35);
		frame.getContentPane().add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String pass= password.getText();
	            
	            File file = new File("C:\\Users\\Kevin\\Documents\\CAR_PROJECTS\\Data\\Users.txt");
	    		
	    		String st;
	    		try {
	    			BufferedReader br = new BufferedReader(new FileReader(file));
					while ((st = br.readLine())!=null)
						if(st.contains(username.getText())) {
							String[] userData = st.split(",");
							user_pass = userData[2];
							if(pass.equals(user_pass)) {
					        	JOptionPane.showMessageDialog(frame, "Welcome "+userData[0]+"!");
					        	frame.setVisible(false);
					        	Menu menu = new Menu();
					        	menu.showMenu();
					        } else {
					        	JOptionPane.showMessageDialog(frame, "Invalid password or username");
					    }
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	        }
	    });
		
		// CHANGE PASSWORD BUTTON
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.setBackground(Color.BLACK);
		btnChangePassword.setForeground(new Color(255, 255, 255));
		btnChangePassword.setBounds(70, 314, 258, 21);
		frame.getContentPane().add(btnChangePassword);
		btnChangePassword.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	File file = new File("C:\\Users\\Kevin\\Documents\\CAR_PROJECTS\\Data\\Users.txt");
	        	String user= username.getText();
	        	
	        	String st;
	            try {
	            	BufferedReader br = new BufferedReader(new FileReader(file));
					while ((st = br.readLine())!=null) {
						String[] userD = st.split(",");
						if (user.equals(userD[1])) {
							frame.setVisible(false);
							setPassword(user);
							break;
						} else{JOptionPane.showMessageDialog(frame, "Wrong Username"); break;}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	            
	        }
	    });
		
		// ICON IMAGE
		JLabel image1 = new JLabel();
		image1.setIcon(new ImageIcon("C://Users//Kevin//Documents//CAR_PROJECTS//Images//kk_icon.jpg"));
		frame.getContentPane().add(image1);
		image1.setBounds(111, 10, 184, 166);
		frame.getContentPane().add(image1);
		image1.setVisible(true);
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void setPassword(String username) throws IOException {
		// CHANGE PASSWORD FRAME
		JFrame frame = new JFrame("KK AUTO PARTS");
		frame.setBackground(Color.WHITE);
		frame.getContentPane().setBackground(new Color(0, 0, 0)); 
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:/Users/Kevin/Documents/CAR_PROJECTS/Images/kk_icon.jpg"));
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(500,100,407,396); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// ICON IMAGE
		JLabel image1 = new JLabel();
		image1.setIcon(new ImageIcon("C://Users//Kevin//Documents//CAR_PROJECTS//Images//kk_icon.jpg"));
		frame.getContentPane().add(image1);
		image1.setBounds(111, 10, 184, 166);
		frame.getContentPane().add(image1);
		
		// OLD PASSWORD (TEXT)
		JLabel lblOldPassword = new JLabel("Password");
		lblOldPassword.setForeground(Color.WHITE);
		lblOldPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOldPassword.setBounds(70, 186, 90, 26);
		frame.getContentPane().add(lblOldPassword);
		frame.revalidate();
		
		// OLD PASSWORD (TEXT BOX)
		password = new JPasswordField();
		password.setBounds(168, 186, 160, 26);
		frame.getContentPane().add(password);
		image1.setVisible(true);
		
		// NEW PASS WORD (TEXT)
		JLabel lblNewPassword = new JLabel("NewPass");
		lblNewPassword.setForeground(Color.WHITE);
		lblNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewPassword.setBounds(77, 222, 83, 26);
		frame.getContentPane().add(lblNewPassword);
		frame.revalidate();
		
		// NEW PASSWORD (TEXT BOX)
		new_password = new JPasswordField();
		new_password.setBounds(168, 222, 160, 26);
		frame.getContentPane().add(new_password);
		
		// CHANGE PASSWORD BUTTON
		JButton btnChangePass = new JButton("CHANGE PASSWORD");
		btnChangePass.setBackground(Color.WHITE);
		btnChangePass.setBounds(70, 269, 260, 35);
		frame.getContentPane().add(btnChangePass);
		btnChangePass.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String pass= password.getText();
	            String newpass = new_password.getText();
	            
	            File file = new File("C:\\Users\\Kevin\\Documents\\CAR_PROJECTS\\Data\\Users.txt");
	    		
	    		String st;
	    		
	    		if (newpass!=null && pass!=null) {
	    			try {
		    			BufferedReader br = new BufferedReader(new FileReader(file));
		    			Update up = new Update();
						while ((st = br.readLine())!=null)
							if(st.contains(username)) {
								String[] userData = st.split(",");
								user_pass = userData[2];
								if(pass.equals(user_pass)) {
						        	up.changePass(st, userData[0]+","+userData[1]+","+newpass);
						        	JOptionPane.showMessageDialog(frame, "Password change successful");
						        	frame.setVisible(false);
						        	loginUser();
						        	break;
						        } else {
						        	JOptionPane.showMessageDialog(frame, "Something went wrong");
						        	break;
						    }
						}
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	    		} else {JOptionPane.showMessageDialog(frame, "One or both text boxes are empty!");}
	        }
	    });
				
		// CANCEL BUTTON
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(Color.BLACK);
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setBounds(70, 314, 258, 21);
		frame.getContentPane().add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	frame.setVisible(false);
	        	loginUser();
	        }
	    });
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);	
	}
}
