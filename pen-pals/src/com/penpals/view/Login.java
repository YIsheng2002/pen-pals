package com.penpals.view;

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
import com.penpals.db.*;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;


public class Login extends JFrame implements MouseListener, ActionListener{

	
	private static final long serialVersionUID = 1L;
	//north panel
	private JLabel title;
	
	//west panel
	private JPanel textPanel;
		private JLabel usernameLabel;
		private JLabel passwordLabel;
	
	//content Pane, set background picture
	private JLabel contentPane;
	
	//center panel
	private JPanel fieldPanel;
		private JTextField usernameField;
		private JPasswordField passwordField;

	//south panel
	private JPanel southPanel;
	private JButton loginButton;
	private JButton registerButton;
	private Image scaledImage;
	private ImageIcon icon;



	/**
	 * Create the frame.
	 */
	public Login() {
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(10, 10));
		setTitle("Penpals Gift Shop");
        setMinimumSize(new Dimension(450, 250));
        
        
        try {
			icon = new ImageIcon(Login.class.getResource("/resources/uiSymbol/loginPage.png"));
			scaledImage = icon.getImage().getScaledInstance(450,250, Image.SCALE_SMOOTH);
		} catch (Exception e) {
		    e.printStackTrace();
		}
        
        //set background picture
	    contentPane = new JLabel("",new ImageIcon(scaledImage),JLabel.CENTER);
		contentPane.setSize(450,250);
		contentPane.setOpaque(false);
		setContentPane(contentPane);
		

	
			title = new JLabel("Login",SwingConstants.CENTER);
			title.setFont(new Font("Lucida Grande", Font.BOLD, 16));
			title.setPreferredSize(new Dimension(450,30));
	
		add(title,BorderLayout.NORTH);
		
			//creation of a panel to contain Jlabels
			textPanel = new JPanel();
			textPanel.setPreferredSize(new Dimension(70,250));
				//username label
				usernameLabel = new JLabel();
				usernameLabel.setText("Username");
				usernameLabel.setPreferredSize(new Dimension(70,30));
				usernameLabel.setHorizontalAlignment(4);
				
				//password label
				passwordLabel = new JLabel();
				passwordLabel.setText("Password ");
				passwordLabel.setPreferredSize(new Dimension(70,30));
				passwordLabel.setHorizontalAlignment(4);
			
			textPanel.add(usernameLabel);
			textPanel.add(passwordLabel);
			textPanel.setOpaque(false);
			
		add(textPanel,BorderLayout.WEST);
		

			//TextField Panel Container
			fieldPanel = new JPanel();
			fieldPanel.setOpaque(false);
			fieldPanel.setPreferredSize(new Dimension(100,300));
				//username textfield
				usernameField = new JTextField();
				usernameField.setPreferredSize(new Dimension(200,30));
				
				//password field
				passwordField = new JPasswordField();
				passwordField.setPreferredSize(new Dimension(200,30));
				
			fieldPanel.add(usernameField);
			fieldPanel.add(passwordField);
			
		add(fieldPanel,BorderLayout.CENTER);
		
				

			//South panel that consist buttons
			southPanel = new JPanel();
			
				//log in button
				loginButton = new JButton();
				loginButton.setText("Login");
				loginButton.addActionListener(this);
				loginButton.addMouseListener(this);
				
				registerButton = new JButton();
				registerButton.setText("Register");
				registerButton.addActionListener(this);
				registerButton.addMouseListener(this);
				
			southPanel.add(loginButton);
			southPanel.add(registerButton);
			southPanel.setOpaque(false);
		
		add(southPanel,BorderLayout.PAGE_END);
	
		contentPane.setOpaque(false);
		
		setResizable(false);
		setVisible(true);
	}


	@Override
	public void mouseClicked(MouseEvent e) {

	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == loginButton || e.getSource() == registerButton  )
		{
			setCursor(Cursor.getDefaultCursor());
		}
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == loginButton || e.getSource() == registerButton  )
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == loginButton || e.getSource() == registerButton)
		{
			setCursor(Cursor.getDefaultCursor());
		}
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == loginButton)
		{
			login();
		}
	}
	
	public void login()
	{
		if(usernameField.getText().isEmpty()||passwordField.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null,"Please complete the form");
		}
		else
		{
		
//			try {
				String username = usernameField.getText();
				String password = passwordField.getText();
				
				//call login function
				
//				//backend??
//				Connection con = MyDatabase.doConnection();
//				Statement stm = con.createStatement();
//				String sql = "SELECT * FROM account WHERE username = '" + username + "' AND password = '" + password +"'";
//				
//				ResultSet rs = stm.executeQuery(sql);
//				if(rs.next())
//				{
//					dispose();
//					//jump to browse product
//					BrowseProduct frame = new BrowseProduct();
//					frame.setVisible(true);
//				}
//				else
//				{
//
//					usernameField.setText("");
//					passwordField.setText("");
//				}
//				con.close();
////				
//			}catch(SQLException err){
//				JOptionPane.showMessageDialog(null,err.getMessage());
//				
//			} catch (ClassNotFoundException e1) {
//				// TODO Auto-generated catch block
//				JOptionPane.showMessageDialog(null,e1.getMessage());
//			}
		}
	}
	
	
	public void exit()
	{
		int result = JOptionPane.showConfirmDialog(null, "Are you sure to exit? ","Exit",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		if(result == JOptionPane.YES_OPTION)
		{
			JOptionPane.showMessageDialog(null,"Thanks for using!");
			System.exit(0);
		}
	}
	

}
