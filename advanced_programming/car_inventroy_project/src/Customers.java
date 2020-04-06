import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Label;

/*******************************************************
 * PROGRAMMERS: Kevin Hernandez & Kimberly Pereyra
 * UNIVERSITY: Inter-american of Puerto Rico, Bayamon
 * CLASS: Advanced Programming
 * PROFESSOR: Jaime Yeckle Sanchez
 * DATE: MAY 8, 2018
 *******************************************************/


/*******************
 *   OVERLOADING  *          <<< DO OVERLOADING WITH FUNCTIONS FROM DataMang
 *******************/

public class Customers extends Reports {
	private static JTable table = new JTable();
	private static Object[] columns = {"First Name","Last Name","Email"};
    private static DefaultTableModel model = new DefaultTableModel();
    private static JTextField txtAutoPart;
    private static JTextField txtCarModel;
    private static JTextField txtItemQuantity;
    
	public void showCustomers() throws IOException {
		File file = new File("C:\\Users\\Kevin\\Documents\\CAR_PROJECTS\\Data\\Customers.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		DataMang x = new DataMang();
		try {
			x.setData();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// MAIN FRAME
		JFrame frame = new JFrame("KK AUTO PARTS"); 
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 9));
		frame.getContentPane().setBackground(new Color(0, 0, 0));  
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:/Users/Kevin/Documents/CAR_PROJECTS/Images/kk_icon.jpg"));
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(500,100,897,514); 
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
        table.setAutoCreateRowSorter(true);
        
        // create JScrollPane (To scroll the table)
        JScrollPane pane = new JScrollPane(table);
        pane.setForeground(Color.RED);
        pane.setBackground(Color.WHITE);
        pane.setBounds(8, 41, 867, 330);
        frame.getContentPane().add(pane);
        
        Object[] row = new Object[3];
        
        String st, st2;
        while ((st = br.readLine())!=null) {
        	String[] data = st.split(",");
			row[0] = data[0];
			row[1] = data[1];
			row[2] = data[2];
			// add row to the model
			model.addRow(row);
        }
        frame.revalidate();
        

        JTextField filterField = RowFilterUtil.createRowFilter(table);
        filterField.setBounds(484, 7, 391, 28);
        frame.getContentPane().add(filterField);

        
        Label label = new Label("SEARCH:");
        label.setFont(new Font("Dialog", Font.PLAIN, 17));
        label.setBounds(402, 10, 106, 21);
        frame.getContentPane().add(label);
        
        txtAutoPart = new JTextField();
        txtAutoPart.setText("Last Name");
        txtAutoPart.setForeground(Color.BLACK);
        txtAutoPart.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtAutoPart.setBounds(442, 381, 218, 40);
        frame.getContentPane().add(txtAutoPart);
        txtAutoPart.setColumns(10);
        
        txtCarModel = new JTextField();
        txtCarModel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtCarModel.setForeground(Color.BLACK);
        txtCarModel.setText("First Name");
        txtCarModel.setBounds(234, 381, 200, 40);
        frame.getContentPane().add(txtCarModel);
        txtCarModel.setColumns(10);
        
        txtItemQuantity = new JTextField();
        txtItemQuantity.setText("Email");
        txtItemQuantity.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtItemQuantity.setForeground(Color.BLACK);
        txtItemQuantity.setBounds(668, 381, 207, 40);
        frame.getContentPane().add(txtItemQuantity);
        txtItemQuantity.setColumns(10);
        
        // get selected row data From table to textfields 
        table.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mouseClicked(MouseEvent e){
            
            // i = the index of the selected row
            int i = table.getSelectedRow();
            
            txtCarModel.setText(model.getValueAt(i, 0).toString());
            txtAutoPart.setText(model.getValueAt(i, 1).toString());
            txtItemQuantity.setText(model.getValueAt(i, 2).toString());
        }
        });
        
        // ADD BUTTON
        JButton btnAdd = new JButton("ADD");
        btnAdd.setBounds(8, 381, 218, 40);
        btnAdd.setBackground(Color.WHITE);
        frame.getContentPane().add(btnAdd);
        // button add row
        btnAdd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {          
                row[0] = txtCarModel.getText();
                row[1] = txtAutoPart.getText();
                row[2] = txtItemQuantity.getText();                
                // add row to the model
                model.addRow(row);
            }
        });
        
        // UPDATE BUTTON
        JButton btnUpdate = new JButton("UPDATE");
        btnUpdate.setBounds(442, 431, 433, 40);
        btnUpdate.setBackground(Color.WHITE);
        frame.getContentPane().add(btnUpdate);
        btnUpdate.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {             
                
            	
            	/////////////////////////////
            	
            }
        });
        
        
        // DELETE BUTTON
        JButton btnDelete = new JButton("DELETE");
        btnDelete.setBackground(Color.WHITE);
        btnDelete.setBounds(8, 431, 426, 40);
        frame.getContentPane().add(btnDelete);
        // button remove row
        btnDelete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {            
                // i = the index of the selected row
                int i = table.getSelectedRow();
                if(i >= 0){
                    // remove a row from jtable
                    model.removeRow(i);
                }
                else{
                    System.out.println("Delete Error");}
            }
        });
        
 
        frame.revalidate();
  		frame.setVisible(true);
	}
}
