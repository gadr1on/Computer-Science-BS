import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.TextArea;
import java.awt.Font;

public class About {
	public static void main(String[] args) throws IOException {
		showAbout();
	}
	
	public static void showAbout() throws IOException {
		JFrame frame = new JFrame("KK AUTO PARTS"); 
		frame.getContentPane().setBackground(new Color(0, 0, 0));  
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:/Users/Kevin/Documents/CAR_PROJECTS/Images/kk_icon.jpg"));
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(500,100,456,294); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblProgramCreatedBy = new JLabel("ABOUT");
		lblProgramCreatedBy.setForeground(Color.WHITE);
		lblProgramCreatedBy.setFont(new Font("Tahoma", Font.BOLD, 65));
		lblProgramCreatedBy.setBounds(122, 10, 235, 52);
		frame.getContentPane().add(lblProgramCreatedBy);
		
		File file = new File("C:\\Users\\Kevin\\Documents\\CAR_PROJECTS\\Readme.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String st, readme="";
		while ((st = br.readLine())!=null) {System.out.println(readme+=st+"\n");;}
		
		// BACK BUTTON
		JButton back = new JButton("BACK");
		back.setBackground(Color.WHITE);
		back.setBounds(8, 10, 83, 21);
		frame.getContentPane().add(back);
		back.addActionListener(new ActionListener() {
  			@Override
  			public void actionPerformed(ActionEvent e) {
  				Menu y = new Menu();
  				y.showMenu();
  				frame.dispose();
  			    }});
  		frame.validate();
		
		TextArea textArea = new TextArea();
		textArea.setColumns(1);
		textArea.setForeground(Color.BLACK);
		textArea.setText(readme);
		textArea.setBounds(0, 68, 440, 183);
		frame.getContentPane().add(textArea);
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		
	}
}
