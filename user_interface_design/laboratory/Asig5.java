import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Asig5 {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Asig5 window = new Asig5();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Asig5() {
		initialize();
	}

	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 209, 330);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSelectOption = new JLabel("Select option:");
		lblSelectOption.setBounds(12, 13, 86, 16);
		frame.getContentPane().add(lblSelectOption);
		
		JRadioButton rdbtnUnion = new JRadioButton("Union");
		JRadioButton rdbtnIntersection = new JRadioButton("Intersection");
		JRadioButton rdbtnDifference = new JRadioButton("Difference");
		
		
		rdbtnUnion.setBounds(12, 38, 66, 25);
		frame.getContentPane().add(rdbtnUnion);
		rdbtnUnion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				rdbtnIntersection.setSelected(false);
				rdbtnDifference.setSelected(false);
			}
		});
		
	
		rdbtnIntersection.setBounds(82, 38, 100, 25);
		frame.getContentPane().add(rdbtnIntersection);
		rdbtnIntersection.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				rdbtnUnion.setSelected(false);
				rdbtnDifference.setSelected(false);
			}
		});
		
		
		rdbtnDifference.setBounds(12, 68, 100, 25);
		frame.getContentPane().add(rdbtnDifference);
		rdbtnDifference.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				rdbtnUnion.setSelected(false);
				rdbtnIntersection.setSelected(false);
			}
		});
		
		JLabel lblX = new JLabel("X:");
		lblX.setBounds(12, 102, 18, 16);
		frame.getContentPane().add(lblX);
		
		textField = new JTextField();
		textField.setBounds(33, 99, 116, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblY = new JLabel("Y:");
		lblY.setBounds(12, 131, 18, 16);
		frame.getContentPane().add(lblY);
		
		textField_1 = new JTextField();
		textField_1.setBounds(33, 128, 116, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.setBounds(43, 159, 97, 25);
		frame.getContentPane().add(btnCalcular);
		btnCalcular.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Set set = new HashSet();
				String[] x = textField.getText().split(",");
				String[] y = textField_1.getText().split(",");
				String result;
				int option;
				int count;
				
				if(rdbtnUnion.isSelected()) {
					option = 1;
				} else if (rdbtnIntersection.isSelected()) {
					option = 2;
				} else {
					option = 3;
				}
				
				switch (option) {
				case 1:
					count = 0;
					result = "{";
					for(String num1:x) {set.add(num1);}
					for(String num2:y) {set.add(num2);}
					for(Object r:set) {
						count++;
						if(count<set.size()) {
							result+=r+", ";
						} else {
							result+=r+"}";
						}
					}
					textField_2.setText(result);
					break;
				case 2:
					count = 0;
					result = "{";
					for(String num1:x) {
						count++;
						for(String num2:y) {
							if(Integer.valueOf(num1.trim())==Integer.valueOf(num2.trim())) {
								if(count<x.length) {
									result+=num2+", ";
								} else {
									result+=num2+"}";
								}
							}
						}
					}
					textField_2.setText(result);
					break;
				case 3:
					count = 0;
					result = "";
					for(String num1:x) {
						count++;
						for(String num2:y) {
							if(Integer.valueOf(num1.trim())==Integer.valueOf(num2.trim())) {
								if(count<x.length) {
									result+=num2+", ";
								} else {
									result+=num2;
								}
							}
						}
					}
					String result2 = "";
					boolean in;
					count = 0;
					for(String num1:x) {set.add(num1);}
					for(String num2:y) {set.add(num2);}
					for(Object s:set) {
						in = false;
						count++;
						for(String num2:result.split(",")) {
							if(Integer.valueOf(s.toString().trim())==Integer.valueOf(num2.trim())) {
								in = true;
								break;
							}
						}
						if(!in) {
							if(count<set.size()) {
								result2+=s+", ";
							} else {
								result2+=s+"}";
							}
						}
					}
					textField_2.setText(result2);
					break;
				default:
					break;
				}
				
			}
		});
		
		textField_2 = new JTextField();
		textField_2.setBounds(33, 197, 116, 22);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(12, 232, 66, 25);
		frame.getContentPane().add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				rdbtnUnion.setSelected(false);
				rdbtnIntersection.setSelected(false);
				rdbtnDifference.setSelected(false);
				
			}
		});
		
		JButton btnDone = new JButton("Done");
		btnDone.setBounds(90, 232, 66, 25);
		frame.getContentPane().add(btnDone);
		btnDone.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
			}
		});
		
		
	}
}
