import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

/*******************************************************
 * PROGRAMMERS: Kevin Hernandez & Kimberly Pereyra
 * UNIVERSITY: Inter-american of Puerto Rico, Bayamon
 * CLASS: Advanced Programming
 * PROFESSOR: Jaime Yeckle Sanchez
 * DATE: MAY 8, 2018
 *******************************************************/

/*******************
 *   INHERITANCE  *
 *******************/

public class Sell extends DataMang {
	private JTextField textField;
	private double tax = 0.213;  // Price tax
	
	// Launch the application.
	public static void sell() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {}});}

	// Create the application.
	public Sell() {
		initialize();
	}

	// Initialize the contents of the frame.
	JTable table = new JTable();
	private void initialize() {
				// Get Data
				try {
					setData();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				// MAIN FRAME
				JFrame frame = new JFrame("KK AUTO PARTS"); 
				frame.getContentPane().setBackground(new Color(0, 0, 0));  
				frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:/Users/Kevin/Documents/CAR_PROJECTS/Images/kk_icon.jpg"));
				frame.getContentPane().setForeground(Color.WHITE);
				frame.setBounds(500,100,891,609); 
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setLocationRelativeTo(null);
				frame.getContentPane().setLayout(null);
				
				// COMBO_BOX_1 (PARA SELECCIONAR MODELO DE CARRO)
				JComboBox box_1 = new JComboBox(cars.toArray());
				box_1.setBackground(Color.WHITE);
				box_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
				box_1.addItemListener(new ItemListener() {
							public void itemStateChanged(ItemEvent event) {
								if(event.getStateChange()==ItemEvent.SELECTED) {
									if(box_1.getSelectedItem().toString()!="Select Car/Model")
										car = box_1.getSelectedItem().toString();
									else car = null;}}});
				box_1.setBounds(8, 41, 867, 34);
				frame.getContentPane().add(box_1);
				
				// SELL TEXT (TOP)
				JLabel lblSell = new JLabel("SELL");
				lblSell.setFont(new Font("Tahoma", Font.PLAIN, 32));
				lblSell.setForeground(Color.WHITE);
				lblSell.setBounds(405, 7, 68, 34);
				frame.getContentPane().add(lblSell);
				
				// COMBO_BOX_2 (PARA SELECCIONAR PARTE DEL CARRO)
				JComboBox box_2 = new JComboBox(parts.toArray());
				box_2.setBackground(Color.WHITE);
				box_2.setForeground(Color.BLACK);
				box_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
				box_2.addItemListener(new ItemListener(){
							public void itemStateChanged(ItemEvent event) {
								if(event.getStateChange()==ItemEvent.SELECTED) {
									if(box_2.getSelectedItem().toString()!="Select Part")
										part = box_2.getSelectedItem().toString();
									else part = null;}}});
				frame.getContentPane().add(box_2);
				box_2.setBounds(8, 85, 867, 34);
				
				// TOTAL TEXT BOX: Muestra el total a pagar de las piezas
		        textField = new JTextField();
		        textField.setForeground(Color.BLACK);
				textField.setFont(new Font("Tahoma", Font.PLAIN, 35));
				textField.setBounds(639, 515, 236, 49);
				frame.getContentPane().add(textField);
				textField.setColumns(10);
	
				// "TOTAL" TEXT On Window
				JLabel total = new JLabel("TOTAL:");
				total.setForeground(Color.WHITE);
				total.setFont(new Font("Tahoma", Font.BOLD, 51));
				total.setBounds(448, 515, 183, 49);
				frame.getContentPane().add(total);
				total.setVisible(true);
				
				// Create a table model and set a Column Identifiers to this model 
		        Object[] columns = {"Id","Car Name","Part Name","Price", "Quantity"};
		        DefaultTableModel model = new DefaultTableModel();
		        model.setColumnIdentifiers(columns);
		        table.setModel(model); // set the model to the table
		        
		        // Change A JTable Background Color, Font Size, Font Color, Row Height
		        table.setBackground(Color.white);
		        table.setForeground(Color.black);
		        table.setSelectionBackground(Color.red);
		        table.setGridColor(Color.red);;
		        table.setSelectionForeground(Color.white);
		        table.setFont(new Font("Tahoma",Font.PLAIN,17));
		        table.setRowHeight(30);
		        
		        // create JScrollPane (To scroll the table)
		        JScrollPane pane = new JScrollPane(table);
		        pane.setForeground(Color.RED);
		        pane.setBackground(Color.WHITE);
		        pane.setBounds(8, 175, 867, 330);
		        frame.getContentPane().add(pane);
		    
		        // create an array of objects to set the row data
		        Object[] row = new Object[5];
		        
		        
		        /**************************************************************************************
		         *                                    BUTTONS										  *
		        /**************************************************************************************/
		        
		        // ADD BUTTON
		 		JButton add = new JButton("ADD");
		 		add.setForeground(Color.BLACK);
		 		add.setBackground(Color.WHITE);
		 		add.setFont(new Font("Tahoma", Font.PLAIN, 17));
		 		add.setBounds(8, 129, 427, 36);
		 		frame.getContentPane().add(add);
		        add.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Inventory inventory = new Inventory();
						Update update = new Update();
						if(car!=null && part!=null) {
							row[0] = getCarId()+getPartId();
							row[1] = car;
							row[2] = part;
							row[3] = String.format("%.2f", (getCarPrice()+getPartPrice())+(getCarPrice()+getPartPrice())*tax);
							try {
								int data=inventory.getQuantity(part)-1;
								if (data>0) {
								row[4]=data;
								update.modifyFile(inventory.getAutopart(part), part+","+data);} else {row[4]=data+1;update.modifyFile(inventory.getAutopart(part), part+","+data+1);}
								// If quantity is zero, show message
								if (data==0) {
									JOptionPane.showMessageDialog(frame, "Item NOT Available");
									BackOrders backOrder = new BackOrders();
									BackOrders.addData(table);
								} else {model.addRow(row);}
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}				
							// add row to the model
				    	    frame.revalidate();
			    		   }else {System.out.println("Please choose option.");}
							double total = 0;
						    for (int i = 0; i < table.getRowCount(); i++){
						        double amount = Double.parseDouble((String) table.getValueAt(i, 3));
						        total += amount;						        						    
						    }
						    textField.setText(String.format("$%2.2f", total));}});
		        
		        // DELETE BUTTON
		        JButton btnDelete = new JButton("DELETE");
		        btnDelete.setBackground(Color.WHITE);
		        btnDelete.setForeground(Color.BLACK);
		        btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 17));
		        btnDelete.setBounds(448, 129, 427, 36);
		        frame.getContentPane().add(btnDelete);
		        btnDelete.addActionListener(new ActionListener(){
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	Inventory inventory2 = new Inventory();
						Update update2 = new Update();
		                // i = the index of the selected row
		            	int i = table.getSelectedRow();
		                if(i >= 0){
		                    // remove a row from jtable
		                	try {
								int data=inventory2.getQuantity(part)+1;
								update2.modifyFile(inventory2.getAutopart(part), part+","+data);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}           
		                    model.removeRow(i);
		                }else{System.out.println("Delete Error");}}});
		        
		        // DONE BUTTON
		        JButton done = new JButton("DONE");
		        done.setBackground(Color.WHITE);
		        done.setForeground(new Color(0, 0, 0));
		 		done.setFont(new Font("Tahoma", Font.PLAIN, 17));
		 		done.setBounds(8, 515, 432, 49);
		 		frame.getContentPane().add(done);
		        done.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Sales sales = new Sales();
					    Sales.addData(table);
					    if (table.getRowCount()>0) {JOptionPane.showMessageDialog(frame, "SELL SUCCESSFUL!");}
					    frame.revalidate();
					    }});
		  		frame.validate();
		  		
		  	    // BACK BUTTON
				JButton back = new JButton("BACK");
				back.setBackground(Color.WHITE);
				back.setBounds(8, 10, 83, 21);
				frame.getContentPane().add(back);
				//image2.setVisible(true);
				back.addActionListener(new ActionListener() {
		  			@Override
		  			public void actionPerformed(ActionEvent e) {
		  				Menu y = new Menu();
		  				y.showMenu();
		  				frame.dispose();
		  			    }});
		  		frame.validate();
		  		
		  		frame.setVisible(true);
	}
	
	public JTable getTable() {
		return table;
	}
}
