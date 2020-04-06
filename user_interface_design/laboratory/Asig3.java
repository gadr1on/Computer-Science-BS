import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Asig3 extends JFrame {

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public static void main(String[] args) {
		Asig3 window = new Asig3();
		window.setVisible(true);
	}

	public Asig3() {
		initialize();
	}


	private void initialize() {
		setBounds(100, 100, 202, 228);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblPitagorasAbc = new JLabel("Pitagoras:   a^2+b^2=c^2");
		lblPitagorasAbc.setBounds(12, 13, 167, 16);
		getContentPane().add(lblPitagorasAbc);
		
		JLabel lblA = new JLabel("A: ");
		lblA.setBounds(12, 42, 17, 16);
		getContentPane().add(lblA);
		
		JLabel lblC = new JLabel("C: ");
		lblC.setBounds(12, 71, 17, 16);
		getContentPane().add(lblC);
		
		textField = new JTextField();
		textField.setBounds(33, 39, 139, 22);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(33, 68, 139, 22);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.setBounds(43, 103, 97, 25);
		getContentPane().add(btnCalculate);
		btnCalculate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				double a = Double.parseDouble(textField.getText()), 
						c = Double.parseDouble(textField_1.getText()), b;
				b = Math.sqrt((a*a)-(c*c));
				
				textField_2.setText(String.valueOf(b));
			}
		});
		
		JLabel lblB = new JLabel("B = ");
		lblB.setBounds(12, 141, 34, 16);
		getContentPane().add(lblB);
		
		textField_2 = new JTextField();
		textField_2.setBounds(40, 141, 132, 22);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		
	}

}
