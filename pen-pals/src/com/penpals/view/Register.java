package view;

import java.awt.BorderLayout;
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

import database.MyDatabase;

public class Register extends JFrame implements ActionListener,MouseListener{

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel textPanel;
	private JPanel fieldPanel;
	private JLabel title;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JLabel retypePasswordLabel;
	private JLabel nameLabel;
	private JLabel telNumberLabel;
	private JLabel emailLabel;
	private JLabel addressLabel;
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
	private Dimension frameSize;
	private JPanel southPanel;
	private JPanel successRegisterPanel;
	private JButton registerButton;
	private JButton cancelButton;
	private JButton loginButton;
	private JLabel successMessageLabel;


	/**
	 * Create the frame.
	 */
	public Register() {
		
		initializeComponents();
	}

	private void initializeComponents()
	{
		// Frame
		setTitle("Gift Shop - Registration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(10, 10));
		setLayout(new BorderLayout(0,0));
		frameSize = new Dimension(850, 650); // Adjust the dimensions as needed
        setSize(frameSize);
        
        // Background image
        ImageIcon bg = new ImageIcon(Login.class.getResource("/resources/uiSymbol/loginPage.png"));
        Image img = bg.getImage();
        Image temp = img.getScaledInstance(850, 750, Image.SCALE_SMOOTH);
        bg = new ImageIcon(temp);
        JLabel bgImg = new JLabel(bg);
        bgImg.setLayout(new BorderLayout());
        bgImg.setBounds(0, 0, 850, 750);
        
        // Title
		title = new JLabel("Register",SwingConstants.CENTER);
		title.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		title.setPreferredSize(new Dimension(450,100));	

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
		
		//creation of a panel to contain Jlabels
		textPanel = new JPanel();
		textPanel.setPreferredSize(new Dimension(200,250));
		textPanel.setOpaque(false);
		textPanel.add(usernameLabel);
		textPanel.add(passwordLabel);
		textPanel.add(retypePasswordLabel);
		textPanel.add(nameLabel);
		textPanel.add(telNumberLabel);
		textPanel.add(emailLabel);
		textPanel.add(addressLabel);
		
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
		addressNumberField.setPreferredSize(new Dimension(450,30));
		
		addressRoadField = new JTextField("Road");
		addressRoadField.setPreferredSize(new Dimension(450,30));
		
		addressPostcodeField = new JTextField("Postcode");
		addressPostcodeField.setPreferredSize(new Dimension(450,30));
		
		addressStateField = new JTextField("State");
		addressStateField.setPreferredSize(new Dimension(450,30));
		
		//TextField Panel Container
		fieldPanel = new JPanel();
		fieldPanel.setOpaque(false);
		fieldPanel.setPreferredSize(new Dimension(5,300));
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
		
		//button
		registerButton = new JButton();
		registerButton.setText("Register");
		registerButton.addActionListener(this);
		registerButton.addMouseListener(this);
		
		cancelButton = new JButton();
		cancelButton.setText("Cancel");
		cancelButton.addActionListener(this);
		cancelButton.addMouseListener(this);
		
		loginButton = new JButton();
		loginButton.setText("Login");
		loginButton.addActionListener(this);
		loginButton.addMouseListener(this);
		
		// success text label
		successMessageLabel = new JLabel();
		successMessageLabel.setText("Account created! You may login now.");
		successMessageLabel.setHorizontalAlignment(JLabel.CENTER);
		
		//South panel that consist buttons
		southPanel = new JPanel();
		southPanel.add(registerButton);
		southPanel.add(cancelButton);
		
		JPanel loginButtonLabel = new JPanel();
		loginButtonLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
		loginButtonLabel.add(loginButton);


		successRegisterPanel = new JPanel(new BorderLayout());
		successRegisterPanel.add(successMessageLabel,BorderLayout.NORTH);
		
		successRegisterPanel.add(loginButtonLabel,BorderLayout.SOUTH);
        
        //set background picture
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(bgImg);
		panel.add(title,BorderLayout.NORTH);
		panel.add(textPanel,BorderLayout.WEST);
		panel.add(fieldPanel,BorderLayout.CENTER);
		panel.add(southPanel,BorderLayout.SOUTH);
		panel.setOpaque(false);
		
		
		setVisible(true);
		setContentPane(panel);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
			Login frame = new Login();
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
		else
		{
			southPanel.setVisible(false);;
			panel.add(successRegisterPanel,BorderLayout.PAGE_END);
			successRegisterPanel.setVisible(true);
			setContentPane(panel);
		
			/*
			 * try { String username = usernameField.getText(); String password =
			 * passwordField.getText();
			 * 
			 * 
			 * //controller function: Check whether username is already exist
			 * 
			 * Connection con = MyDatabase.doConnection(); Statement stm =
			 * con.createStatement(); String checkSQL =
			 * "SELECT * FROM account WHERE username = '" + username + "' AND '" + password
			 * + "'"; ResultSet rs = stm.executeQuery(checkSQL); if(rs.next()) {
			 * JOptionPane.showMessageDialog(null,
			 * "The username is alreay exist.","Error",JOptionPane.WARNING_MESSAGE);
			 * usernameField.setText(""); usernameField.setText(""); } else { String sql =
			 * "INSERT INTO account (username, password) VALUES (" + username + "," +
			 * password + ")"; stm.executeUpdate(sql);
			 * JOptionPane.showMessageDialog(null,"Registration successful!");
			 * 
			 * dispose(); Login frame = new Login(); frame.setVisible(true); } con.close();
			 * 
			 * }catch(SQLException err){
			 * JOptionPane.showMessageDialog(null,err.getMessage());
			 * 
			 * } catch (ClassNotFoundException e1) { // TODO Auto-generated catch block
			 * JOptionPane.showMessageDialog(null,e1.getMessage()); }
			 */
		
		// Add data to db + error handling
		}
	}
	
}
