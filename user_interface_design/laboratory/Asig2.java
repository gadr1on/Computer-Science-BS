import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Asig2 extends JFrame {

	JTextField textField;
	JTextField textField_1;
	
	JRadioButton rdbtnGrams = new JRadioButton("Grams");
	JRadioButton rdbtnOunces = new JRadioButton("Ounces");
	JRadioButton rdbtnMilliliters = new JRadioButton("Milliliters");
	
	JRadioButton radioButton_2 = new JRadioButton("Grams");
	JRadioButton radioButton_1 = new JRadioButton("Ounces");
	JRadioButton radioButton = new JRadioButton("Milliliters");

	public static void main(String[] args) {
		Asig2 window = new Asig2();
		window.setVisible(true);
	}

	public Asig2() {
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 258, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblConvertFrom = new JLabel("Convert from:");
		lblConvertFrom.setBounds(12, 13, 93, 16);
		getContentPane().add(lblConvertFrom);
		
		rdbtnMilliliters.setBounds(8, 34, 85, 25);
		getContentPane().add(rdbtnMilliliters);
		rdbtnMilliliters.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				rdbtnOunces.setSelected(false);
				rdbtnGrams.setSelected(false);
			}
		});
		
		rdbtnOunces.setBounds(97, 34, 79, 25);
		getContentPane().add(rdbtnOunces);
		rdbtnOunces.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				rdbtnMilliliters.setSelected(false);
				rdbtnGrams.setSelected(false);
			}
		});
		
		
		rdbtnGrams.setBounds(175, 34, 79, 25);
		getContentPane().add(rdbtnGrams);
		rdbtnGrams.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				rdbtnOunces.setSelected(false);
				rdbtnMilliliters.setSelected(false);
			}
		});
		
		
		JLabel lblEnterValue = new JLabel("Enter Value:");
		lblEnterValue.setBounds(12, 68, 93, 16);
		getContentPane().add(lblEnterValue);
		
		textField = new JTextField();
		textField.setBounds(0, 90, 240, 22);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblConvertTo = new JLabel("Convert to:");
		lblConvertTo.setBounds(12, 125, 81, 16);
		getContentPane().add(lblConvertTo);
		
		
		radioButton.setBounds(8, 143, 85, 25);
		getContentPane().add(radioButton);
		radioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				radioButton_1.setSelected(false);
				radioButton_2.setSelected(false);
				result();
			}
		});
		
	
		radioButton_1.setBounds(97, 143, 79, 25);
		getContentPane().add(radioButton_1);
		radioButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				radioButton.setSelected(false);
				radioButton_2.setSelected(false);
				result();
			}
		});
		
		
		radioButton_2.setBounds(175, 143, 79, 25);
		getContentPane().add(radioButton_2);
		radioButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				radioButton_1.setSelected(false);
				radioButton.setSelected(false);
				result();
			}
		});
		
		JLabel lblResultIs = new JLabel("Result is = ");
		lblResultIs.setBounds(8, 183, 65, 16);
		getContentPane().add(lblResultIs);
		
		textField_1 = new JTextField();
		textField_1.setBounds(73, 177, 167, 22);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
	}
	
	private void result() {
		double x = Double.parseDouble(textField.getText());
		double result;
		int option=0;
		
		if(rdbtnMilliliters.isSelected()) {
			option = 1;
		} else if (rdbtnOunces.isSelected()) {
			option = 2;
		} else {
			option = 3;
		}
		
		switch (option) {
		// from Millilimeters
		case 1:
			if(radioButton.isSelected()) {
				result = x;
				textField_1.setText(String.valueOf(result)+"ml");
			} else if (radioButton_1.isSelected()) {
				result = x/29573.53;
				textField_1.setText(String.valueOf(result)+"oz");
			} else {
				result = x;
				textField_1.setText(String.valueOf(result)+"g");
			}
			break;
		// From Ounces
		case 2:
			if(radioButton.isSelected()) {
				result = x*29.574;
				textField_1.setText(String.valueOf(result)+"ml");
			} else if (radioButton_1.isSelected()) {
				result = x;
				textField_1.setText(String.valueOf(result)+"oz");
			} else {
				result = x*28.35;
				textField_1.setText(String.valueOf(result)+"g");
			}
			break;
		// From Grams
		case 3:
			if(radioButton.isSelected()) {
				result = x;
				textField_1.setText(String.valueOf(result)+"ml");
			} else if (radioButton_1.isSelected()) {
				result = x/28.35;
				textField_1.setText(String.valueOf(result)+"oz");
			} else {
				result = x;
				textField_1.setText(String.valueOf(result)+"g");
			}
			break;
		default:
			break;
		}
		
	}
}
