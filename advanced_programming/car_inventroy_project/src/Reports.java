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
 *   INHERITANCE  *
 *******************/

public class Reports extends Menu {
	
	public void showReports() {
		// MAIN FRAME
		JFrame frame = new JFrame("KK AUTO PARTS"); 
		frame.getContentPane().setBackground(new Color(0, 0, 0));  
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:/Users/Kevin/Documents/CAR_PROJECTS/Images/kk_icon.jpg"));
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(500,100,447,467); 
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
  				showMenu();
  				frame.dispose();
  			    }});
  		frame.validate();
  		
  		// INVENTORY BUTTON
  		JButton inv = new JButton("Inventory");
  		inv.setBackground(Color.WHITE);
  		inv.setBounds(8, 254, 420, 49);
  		frame.getContentPane().add(inv);
  		inv.addActionListener(new ActionListener() {
  			@Override
  			public void actionPerformed(ActionEvent e) {
  				Inventory inventory = new Inventory();
  				try {
					inventory.showInventory();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
  				frame.dispose();
  			    }});
  		frame.validate();
  		
  	    // Sales BUTTON
  		JButton sales = new JButton("Sales");
  		sales.setBackground(Color.WHITE);
  		sales.setBounds(8, 195, 420, 49);
  		frame.getContentPane().add(sales);
  		sales.addActionListener(new ActionListener() {
  			@Override
  			public void actionPerformed(ActionEvent e) {
  				Sales sales1 = new Sales();
  				sales1.showSales();
  				frame.dispose();
  			    }});
  		frame.validate();
  		
  		// LOGOUT BUTTON
  		JButton btnLogOut = new JButton("Log out");
  		btnLogOut.setBackground(Color.WHITE);
  		btnLogOut.setBounds(349, 10, 83, 21);
  		frame.getContentPane().add(btnLogOut);
  		btnLogOut.addActionListener(new ActionListener() {
  			@Override
  			public void actionPerformed(ActionEvent e) {
  				frame.setVisible(false);
  				Login log = new Login();
 				log.loginUser();}});
  		
  		JButton btnCustomers = new JButton("Customers");
  		btnCustomers.setBackground(Color.WHITE);
  		btnCustomers.setBounds(8, 313, 420, 49);
  		frame.getContentPane().add(btnCustomers);
  		btnCustomers.addActionListener(new ActionListener() {
  			@Override
  			public void actionPerformed(ActionEvent e) {
  				frame.setVisible(false);
  				Customers customers = new Customers();
 				try {
					customers.showCustomers();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
  			    }});
  		frame.validate();
  		
  		
  		JButton btnEmployees = new JButton("Employees");
  		btnEmployees.setBackground(Color.WHITE);
  		btnEmployees.setBounds(8, 372, 420, 49);
  		frame.getContentPane().add(btnEmployees);
  		btnEmployees.addActionListener(new ActionListener() {
  			@Override
  			public void actionPerformed(ActionEvent e) {
  				frame.setVisible(false);
  				Employees employees = new Employees();
 				try {
					employees.showEmployees();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
  			    }});
  		frame.validate();
  		
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
  		
		
  		
		frame.setVisible(true);
	}
}
