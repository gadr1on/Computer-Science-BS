import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;

//import EachRowEditorExample.EachRowEditor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JSlider;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.event.CellEditorListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JRadioButton;

public class Asig4 {
	
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Asig4 window = new Asig4();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Asig4() {
		initialize();
	}

	
	private void initialize() {
		// Asig4 frame
		frame = new JFrame("WORK HOURS");  
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(500,100,208,350); 
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSeleccioneTipoDe = new JLabel("Seleccione tipo de distancia");
		lblSeleccioneTipoDe.setBounds(12, 13, 183, 16);
		frame.getContentPane().add(lblSeleccioneTipoDe);
		
		
		JRadioButton rdbtnPitagorico = new JRadioButton("Pitagorico");
		JRadioButton rdbtnPlanar = new JRadioButton("Planar");
		JRadioButton rdbtnRectilineo = new JRadioButton("Rectilineo");
		
		
		rdbtnRectilineo.setBounds(12, 38, 83, 25);
		frame.getContentPane().add(rdbtnRectilineo);
		rdbtnRectilineo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				rdbtnPitagorico.setSelected(false);
				rdbtnPlanar.setSelected(false);
			}
		});
		

		rdbtnPitagorico.setBounds(12, 68, 97, 25);
		frame.getContentPane().add(rdbtnPitagorico);
		rdbtnPitagorico.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				rdbtnRectilineo.setSelected(false);
				rdbtnPlanar.setSelected(false);
			}
		});
		
		
		rdbtnPlanar.setBounds(98, 38, 78, 25);
		frame.getContentPane().add(rdbtnPlanar);
		rdbtnPlanar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				rdbtnRectilineo.setSelected(false);
				rdbtnPitagorico.setSelected(false);
			}
		});
		
		JLabel lblIngreseValoreX = new JLabel("Ingrese valore x, y");
		lblIngreseValoreX.setBounds(29, 102, 116, 16);
		frame.getContentPane().add(lblIngreseValoreX);
		
		JLabel lblX = new JLabel("x:");
		lblX.setBounds(12, 131, 17, 16);
		frame.getContentPane().add(lblX);
		
		textField = new JTextField();
		textField.setBounds(29, 128, 123, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblY = new JLabel("y:");
		lblY.setBounds(12, 160, 17, 16);
		frame.getContentPane().add(lblY);
		
		textField_1 = new JTextField();
		textField_1.setBounds(29, 157, 123, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnCalcularDistancia = new JButton("Calcular Distancia");
		btnCalcularDistancia.setBounds(12, 195, 164, 25);
		frame.getContentPane().add(btnCalcularDistancia);
		btnCalcularDistancia.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				double x = Double.parseDouble(textField.getText());
				double y = Double.parseDouble(textField_1.getText());
				double d;
				int formula;
				
				if(rdbtnRectilineo.isSelected()) {
					formula = 1;
				} else if (rdbtnPitagorico.isSelected()) {
					formula = 2;
				} else {
					formula = 3;
				}
				
				switch (formula) {
				// Rectilineo
				case 1:
					d = 10-x+(0.5*y*x*x);
					textField_2.setText(String.valueOf(d));
					break;
				// Pitagorico
				case 2:
					d = Math.sqrt((x*x)+(y*y));
					textField_2.setText(String.valueOf(d));
					break;
				// Planar
				case 3:
					d = (3*x+8*y+5)/(Math.sqrt((x*x)+(y*y)));
					textField_2.setText(String.valueOf(d));
					break;
				default:
					break;
				}
				
			}
		});
		
		JLabel lblD = new JLabel("d = ");
		lblD.setBounds(12, 233, 30, 16);
		frame.getContentPane().add(lblD);
		
		textField_2 = new JTextField();
		textField_2.setBounds(39, 230, 137, 22);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnClear = new JButton("clear");
		btnClear.setBounds(12, 262, 68, 25);
		frame.getContentPane().add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				rdbtnRectilineo.setSelected(false);
				rdbtnPitagorico.setSelected(false);
				rdbtnPlanar.setSelected(false);
				
			}
		});
		
		JButton btnClose = new JButton("close");
		btnClose.setBounds(98, 265, 78, 25);
		frame.getContentPane().add(btnClose);
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
			}
		});
		
		frame.setVisible(true);
		
	}
}
