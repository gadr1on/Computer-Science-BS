import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*******************************************************
 * PROGRAMMERS: Kevin Hernandez & Kimberly Pereyra
 * UNIVERSITY: Inter-american of Puerto Rico, Bayamon
 * CLASS: Advanced Programming
 * PROFESSOR: Jaime Yeckle Sanchez
 * DATE: MAY 8, 2018
 *******************************************************/

/*******************
 *  INTERFACE  *
 *******************/

public class Menu implements MenuInterface {

	public void showMenu() {
				// MAIN FRAME
				JFrame frame = new JFrame("K&K AUTO PARTS"); 
				frame.getContentPane().setBackground(new Color(0, 0, 0));  
				frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:/Users/Kevin/Documents/CAR_PROJECTS/Images/kk_icon.jpg"));
				frame.getContentPane().setForeground(Color.WHITE);
				frame.setBounds(500,100,447,467); 
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().setLayout(null);
				frame.setLocationRelativeTo(null);
				
				
				// SELL BUTTON
		  		JButton sell = new JButton("Sell");
		  		sell.setBackground(Color.WHITE);
		  		sell.setBounds(8, 195, 420, 49);
		  		frame.getContentPane().add(sell);
		  		sell.addActionListener(new ActionListener() {
		  			@Override
		  			public void actionPerformed(ActionEvent e) {
		  				frame.setVisible(false);
		  				Sell sales = new Sell();
		 				sales.sell();
		  			    }});
		  		frame.validate();
		  		
		  		// REPORTS BUTTON
		  		JButton btnReports = new JButton("Reports");
		  		btnReports.setBackground(Color.WHITE);
		  		btnReports.setBounds(8, 254, 420, 49);
		  		frame.getContentPane().add(btnReports);
		  		btnReports.addActionListener(new ActionListener() {
		  			@Override
		  			public void actionPerformed(ActionEvent e) {
		  				frame.setVisible(false);
		  				Reports rep = new Reports();
		 				rep.showReports();
		  			    }});
		  		frame.validate();
		  		
		  		// BACK ORDERS BUTTON
		  		JButton btnBackOrders = new JButton("Back Orders");
		  		btnBackOrders.setBackground(Color.WHITE);
		  		btnBackOrders.setBounds(8, 313, 420, 49);
		  		frame.getContentPane().add(btnBackOrders);
		  		btnBackOrders.addActionListener(new ActionListener() {
		  			@Override
		  			public void actionPerformed(ActionEvent e) {
		  				frame.setVisible(false);
		  				BackOrders backord = new BackOrders();
		  				backord.showBackOrders();
		  			    }});
		  		frame.validate();
		  		
		  		// ABOUT BUTTON
		  		JButton btnAbout = new JButton("About");
		  		btnAbout.setBackground(Color.WHITE);
		  		btnAbout.setBounds(8, 372, 420, 49);
		  		frame.getContentPane().add(btnAbout);
		  		btnAbout.addActionListener(new ActionListener() {
		  			@Override
		  			public void actionPerformed(ActionEvent e) {
		  				frame.setVisible(false);
		  				About about = new About();
		 				try {
							about.showAbout();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		  			    }});
		  		frame.validate();
		  		
		  		// LOGOUT BUTTON
		  		JButton btnLogOut = new JButton("Log out");
		  		btnLogOut.setBackground(Color.WHITE);
		  		btnLogOut.setBounds(349, 10, 83, 21);
		  		frame.getContentPane().add(btnLogOut);
		  		
		  		// LOGO IMAGE
		  		JLabel icon = new JLabel();
		  		icon.setIcon(new ImageIcon("C:/Users/Kevin/Documents/CAR_PROJECTS/Images/kk_icon2.png"));
		  		frame.getContentPane().add(icon);
		  		icon.setBounds(131,-75,184,350);
		  		icon.setVisible(true);
		  		
		  		// GIF IMAGE
		  		JLabel label = new JLabel(new ImageIcon("C:/Users/Kevin/Documents/CAR_PROJECTS/Images/giphy.gif"));
		  		label.setBounds(0, 0, 437, 244);
		  		frame.getContentPane().add(label);
		  		btnLogOut.addActionListener(new ActionListener() {
		  			@Override
		  			public void actionPerformed(ActionEvent e) {
		  				frame.setVisible(false);
		  				Login log = new Login();
		 				log.loginUser();
		  			    }});
		  		frame.validate();
				frame.revalidate();

		  		frame.setVisible(true);
	}
}
