import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Asig1 {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Asig1 window = new Asig1();
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
	public Asig1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 83);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblTypeYourZip = new JLabel("Type your Zip Code:");
		frame.getContentPane().add(lblTypeYourZip, BorderLayout.WEST);
		
		textField = new JTextField();
		frame.getContentPane().add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		frame.getContentPane().add(btnSubmit, BorderLayout.EAST);
	}

}
