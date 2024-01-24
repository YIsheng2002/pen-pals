package com.penpals.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.penpals.controller.CustomerController;
import com.penpals.model.Address;
import com.penpals.model.Customer;

public class RegisterGui extends JFrame implements ActionListener,MouseListener{

	private static final long serialVersionUID = 1L;
	
	
	private JPanel bgPanel;
		private JLabel bgLabel;
		
		
	private JPanel panel;
		private JLabel title;
		private JPanel textPanel;
			private JLabel usernameLabel;
			private JLabel passwordLabel;
			private JLabel retypePasswordLabel;
			private JLabel nameLabel;
			private JLabel telNumberLabel;
			private JLabel emailLabel;
			private JLabel addressLabel;
		private JPanel fieldPanel;
			private JTextField usernameField;
			private JPasswordField passwordField;
			private JPasswordField retypePasswordField;
			private JTextField nameField;
			private JTextField telNumberField;
			private JTextField emailField;
			private JTextField addressNumberField;
			private JTextField addressRoadField;
			private JTextField addressPostcodeField;
			private JTextField addressStateField;
	
	private JPanel southPanel;
		private JButton registerButton;
		private JButton cancelButton;
		
		
		private JPanel successRegisterPanel;
			private JPanel loginButtonLabel;
					private JButton loginButton;
			private JLabel successMessageLabel;

	
	private Dimension frameSize;
	
	private CustomerController customerController = new CustomerController();

	/**
	 * Create the frame.
	 */
	public RegisterGui() {
		
		initializeComponents();
	}

	private void initializeComponents()
	{
		// Frame
		setTitle("Penpals Gift Shop - Registration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(10, 10));
		getContentPane().setLayout(new BorderLayout(0,0));
		frameSize = new Dimension(850, 650); // Adjust the dimensions as needed
        setSize(frameSize);

	    

	    panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setSize(850, 650);
        panel.setOpaque(false);
        getContentPane().add(panel);
		
	        // Background image
	        bgPanel = new JPanel(); 
	        bgPanel.setSize(850, 650);
	       
			        ImageIcon bg = new ImageIcon(LoginGui.class.getResource("/resources/uiSymbol/loginPage.png"));
			        Image img = bg.getImage();
			        Image temp = img.getScaledInstance(850, 650, Image.SCALE_SMOOTH);
			        bg = new ImageIcon(temp);
	        	bgLabel = new JLabel(bg);
		    	bgLabel.setLayout(new BorderLayout());
		    	bgLabel.setSize(850, 650);
	        bgPanel.add(bgLabel);
	        bgPanel.setOpaque(false);
	        
        getContentPane().add(bgPanel);
        
	 
		
		        // Title
				title = new JLabel("Register",SwingConstants.CENTER);
				title.setFont(new Font("Lucida Grande", Font.BOLD, 20));
				title.setPreferredSize(new Dimension(450,100));	
			panel.add(title,BorderLayout.NORTH);
		
			
				//creation of a panel to contain Jlabels
				textPanel = new JPanel();
				textPanel.setPreferredSize(new Dimension(200,250));
					// label
					usernameLabel = new JLabel();
					usernameLabel.setText("Username");
					usernameLabel.setPreferredSize(new Dimension(200,30));
					usernameLabel.setHorizontalAlignment(4);
					
					passwordLabel = new JLabel();
					passwordLabel.setText("Password");
					passwordLabel.setPreferredSize(new Dimension(200,30));
					passwordLabel.setHorizontalAlignment(4);
					
					retypePasswordLabel = new JLabel();
					retypePasswordLabel.setText("Retype Password");
					retypePasswordLabel.setPreferredSize(new Dimension(200,30));
					retypePasswordLabel.setHorizontalAlignment(4);
					
					nameLabel = new JLabel();
					nameLabel.setText("Full Name");
					nameLabel.setPreferredSize(new Dimension(200,30));
					nameLabel.setHorizontalAlignment(4);
					
					telNumberLabel = new JLabel();
					telNumberLabel.setText("Telephone Number");
					telNumberLabel.setPreferredSize(new Dimension(200,30));
					telNumberLabel.setHorizontalAlignment(4);
					
					emailLabel = new JLabel();
					emailLabel.setText("Email");
					emailLabel.setPreferredSize(new Dimension(200,30));
					emailLabel.setHorizontalAlignment(4);
					
					addressLabel = new JLabel();
					addressLabel.setText("Address");
					addressLabel.setPreferredSize(new Dimension(200,30));
					addressLabel.setHorizontalAlignment(4);
					
				
				textPanel.add(usernameLabel);
				textPanel.add(passwordLabel);
				textPanel.add(retypePasswordLabel);
				textPanel.add(nameLabel);
				textPanel.add(telNumberLabel);
				textPanel.add(emailLabel);
				textPanel.add(addressLabel);
				textPanel.setOpaque(false);
			panel.add(textPanel,BorderLayout.LINE_START);
			
			
				//TextField Panel Container
				fieldPanel = new JPanel();
				fieldPanel.setOpaque(false);
				fieldPanel.setPreferredSize(new Dimension(5,300));
				
					//textfield
					usernameField = new JTextField();
					usernameField.setPreferredSize(new Dimension(450,30));
					
					passwordField = new JPasswordField();
					passwordField.setPreferredSize(new Dimension(450,30));
					
					retypePasswordField = new JPasswordField();
					retypePasswordField.setPreferredSize(new Dimension(450,30));
					
					nameField = new JTextField();
					nameField.setPreferredSize(new Dimension(450,30));
					
					telNumberField = new JTextField();
					telNumberField.setPreferredSize(new Dimension(450,30));
					
					emailField = new JTextField();
					emailField.setPreferredSize(new Dimension(450,30));
					
					addressNumberField = new JTextField("Unit No");
					addressNumberField.setForeground(new Color(172, 172, 172));
					addressNumberField.setPreferredSize(new Dimension(450,30));
					addressNumberField.addMouseListener(this);
					
					addressRoadField = new JTextField("Road");
					addressRoadField.setForeground(new Color(172, 172, 172));
					addressRoadField.setPreferredSize(new Dimension(450,30));
					addressRoadField.addMouseListener(this);
					
					addressPostcodeField = new JTextField("Postcode");
					addressPostcodeField.setForeground(new Color(172, 172, 172));
					addressPostcodeField.setPreferredSize(new Dimension(450,30));
					addressPostcodeField.addMouseListener(this);
					
					addressStateField = new JTextField("State");
					addressStateField.setForeground(new Color(172, 172, 172));
					addressStateField.setPreferredSize(new Dimension(450,30));
					addressStateField.addMouseListener(this);
			
			
				fieldPanel.add(usernameField);
				fieldPanel.add(passwordField);
				fieldPanel.add(retypePasswordField);
				fieldPanel.add(nameField);
				fieldPanel.add(telNumberField);
				fieldPanel.add(emailField);
				fieldPanel.add(addressNumberField);
				fieldPanel.add(addressRoadField);
				fieldPanel.add(addressPostcodeField);
				fieldPanel.add(addressStateField);
			panel.add(fieldPanel,BorderLayout.CENTER);
			
			
				//South panel that consist buttons
				southPanel = new JPanel();
					//button
					registerButton = new JButton();
					registerButton.setText("Register");
					registerButton.addActionListener(this);
					registerButton.addMouseListener(this);
					
					cancelButton = new JButton();
					cancelButton.setText("Cancel");
					cancelButton.addActionListener(this);
					cancelButton.addMouseListener(this);
				
			
				southPanel.add(registerButton);
				southPanel.add(cancelButton);
				southPanel.setOpaque(false);
				
			panel.add(southPanel,BorderLayout.SOUTH);
			
	
	
			successRegisterPanel = new JPanel(new BorderLayout());
			
			// success text label
					successMessageLabel = new JLabel();
					successMessageLabel.setText("Account created! You may login now.");
					successMessageLabel.setHorizontalAlignment(JLabel.CENTER);
					
					loginButtonLabel = new JPanel();
	
						loginButton = new JButton();
						loginButton.setText("Login");
						loginButton.addActionListener(this);
						loginButton.addMouseListener(this);
					
					loginButtonLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
					loginButtonLabel.add(loginButton);
				
			successRegisterPanel.add(successMessageLabel,BorderLayout.NORTH);
			successRegisterPanel.add(loginButtonLabel,BorderLayout.SOUTH);
			successRegisterPanel.setOpaque(false);
	        
	       
		pack();
		setVisible(true);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()== addressNumberField)
		{
			addressNumberField.setText("");
			addressNumberField.setForeground(Color.BLACK);
		}
		else if(e.getSource()== addressRoadField)
		{
			addressRoadField.setText("");
			addressRoadField.setForeground(Color.BLACK);
		}
		else if(e.getSource()== addressPostcodeField)
		{
			addressPostcodeField.setText("");
			addressPostcodeField.setForeground(Color.BLACK);
		}
		else if(e.getSource()== addressStateField)
		{
			addressStateField.setText("");
			addressStateField.setForeground(Color.BLACK);
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {

	}


	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == registerButton || e.getSource()== cancelButton)
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == registerButton || e.getSource()== cancelButton )
		{
			setCursor(Cursor.getDefaultCursor());
		}
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == registerButton)
		{
			register();
			//retrive information and save into database
		
		}
		else if(e.getSource() == cancelButton || e.getSource() == loginButton)
		{
			
			dispose();
			LoginGui frame = new LoginGui();
			frame.setVisible(true);
		}
	}
	
	public void register()
	{
		
		if(usernameField.getText().isEmpty()||passwordField.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Please complete the form.");
			
		}
		else if(usernameField.getText().length() < 6)
		{
			JOptionPane.showMessageDialog(null, "Username must at least 6 character!","Error",JOptionPane.WARNING_MESSAGE);
			
		}
		else if(passwordField.getText().length() < 6)
		{
			JOptionPane.showMessageDialog(null, "Password must at least 6 character!","Error",JOptionPane.WARNING_MESSAGE);
		}
		else if(!passwordField.getText().equals(retypePasswordField.getText()))
		{
			JOptionPane.showMessageDialog(null, "Password mismatch","Error",JOptionPane.WARNING_MESSAGE);
		}
		
		else
		{
			Customer customer = new Customer();
			customer.setCustomerUsername(usernameField.getText());
			customer.setCustomerPassword(passwordField.getText());
			customer.setCustomerName(nameField.getText());
			customer.setCustomerEmail(emailField.getText());
			customer.setCustomerTelNumber(telNumberField.getText());
			Address address = new Address();
			address.setNumber(Integer.parseInt(addressNumberField.getText()));
			address.setRoad(addressRoadField.getText());
			address.setPostcode(Integer.parseInt(addressPostcodeField.getText()));
			address.setState(addressStateField.getText());
			customer.setCustomerAddress(address);
			customerController.addCustomer(customer);

			southPanel.setVisible(false);;
			panel.add(successRegisterPanel,BorderLayout.PAGE_END);
			successRegisterPanel.setVisible(true);
		
		}
	}
	
}
