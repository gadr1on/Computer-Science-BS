import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/*******************************************************
 * PROGRAMMERS: Kevin Hernandez & Kimberly Pereyra
 * UNIVERSITY: Inter-american of Puerto Rico, Bayamon
 * CLASS: Advanced Programming
 * PROFESSOR: Jaime Yeckle Sanchez
 * DATE: MAY 8, 2018
 *******************************************************/


public class Sales {
	private static JTable table = new JTable();
	private static Object[] columns = {"Id","Car Name","Part Name","Price"};
    private static DefaultTableModel model = new DefaultTableModel();
    private static JTextField textField;
    
	public static void showSales() {
		// MAIN FRAME
		JFrame frame = new JFrame("KK AUTO PARTS"); 
		frame.getContentPane().setBackground(new Color(0, 0, 0));  
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:/Users/Kevin/Documents/CAR_PROJECTS/Images/kk_icon.jpg"));
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(500,100,897,480); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		// BACK BUTTON
		JButton back = new JButton("BACK");
		back.setBackground(Color.WHITE);
		back.setBounds(8, 10, 83, 21);
		frame.getContentPane().add(back);
		back.addActionListener(new ActionListener() {
  			@Override
  			public void actionPerformed(ActionEvent e) {
  				Reports rep = new Reports();
  				rep.showReports();
  				frame.dispose();
  			    }});
  		frame.validate();
  		
  	    // Create a table model and set a Column Identifiers to this model 
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
        pane.setBounds(8, 41, 867, 330);
        frame.getContentPane().add(pane);
        
        // TOTAL TEXT BOX: Show total price $$$
        textField = new JTextField();
        textField.setForeground(Color.BLACK);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 35));
		textField.setBounds(639, 381, 236, 49);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		// "TOTAL" TEXT On Window
		JLabel total = new JLabel("TOTAL SALES:");
		total.setForeground(Color.WHITE);
		total.setFont(new Font("Tahoma", Font.BOLD, 51));
		total.setBounds(274, 374, 357, 56);
		frame.getContentPane().add(total);
		total.setVisible(true);
		
		// SALES TEXT (TOP)
		JLabel lblSell = new JLabel("SALES");
		lblSell.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblSell.setForeground(Color.WHITE);
		lblSell.setBounds(405, 0, 89, 31);
		frame.getContentPane().add(lblSell);
		
		// TOTAL CALCULATION
		double tot=0;
		for (int i = 0; i < table.getRowCount(); i++){
	        tot += Double.parseDouble((String) table.getValueAt(i, 3));}
		
		textField.setText(String.format("$%2.2f", tot));
	    frame.revalidate();
  		frame.setVisible(true);
	}
	
	// ADD DATA TO TABLE FUNCTION
	public static void addData(JTable tabla) {
		Object[] row = new Object[4];
        
        for (int i=0;i<tabla.getRowCount();i++) {
        	row[0]=tabla.getValueAt(i, 0);
        	row[1]=tabla.getValueAt(i, 1);
        	row[2]=tabla.getValueAt(i, 2);
        	row[3]=tabla.getValueAt(i, 3);
        	model.addRow(row);
        }
	}
}
