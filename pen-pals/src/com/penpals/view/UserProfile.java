package com.penpals.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
import java.awt.Font;
import java.awt.Color;

public class UserProfile extends JFrame implements ActionListener, MouseListener{

	private static final long serialVersionUID = 1L;
	
	//center Panel
	private JPanel contentPane;
		//page start panel
		private JLabel userImage;
		//line start panel
		private JPanel textPanel;
			private JLabel usernameLabel;
			private JLabel passwordLabel;
			private JLabel nameLabel;
			private JLabel telNumberLabel;
			private JLabel emailLabel;
			private JLabel addressLabel;
			
		private JPanel fieldLabelPanel;
			private JLabel usernameFieldLabel;
			private JLabel passwordFieldLabel;
			private JLabel nameFieldLabel;
			private JLabel telNumberFieldLabel;
			private JLabel emailFieldLabel;
			private JLabel addressNumberFieldLabel;
			private JLabel addressRoadFieldLabel;
			private JLabel addressPostcodeFieldLabel;
			private JLabel addressStateFieldLabel;
	
	//north Panel
	private JPanel northPanel;
		private JButton backButton;
			private ImageIcon backIcon;
	/**
	 * Create the frame.
	 */
	public UserProfile () {
		init();
	}
		
		
	public void init() {
		 //Frame
		setBounds(100, 100, 450, 550);
		
		setTitle("Penpal Gift Shop");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(450, 550));
        setLayout(new BorderLayout());
        
        contentPane = new JPanel(new BorderLayout(5,5));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));  
        	
			//set user image
	        userImage = new JLabel();
	        ImageIcon image = createResizedIcon("/resources/uiSymbol/user_profile.png",50,50);
	        userImage.setIcon(image);
	        userImage.setHorizontalAlignment(JLabel.CENTER);
	        userImage.setVerticalAlignment(JLabel.BOTTOM);
	        
	    contentPane.add(userImage,BorderLayout.PAGE_START);
		        
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
				textPanel.add(nameLabel);
				textPanel.add(telNumberLabel);
				textPanel.add(emailLabel);
				textPanel.add(addressLabel);
				
			contentPane.add(textPanel,BorderLayout.LINE_START);
			
			
				//TextLabel Panel Container
				fieldLabelPanel = new JPanel();
				fieldLabelPanel.setPreferredSize(new Dimension(5,300));
				
					usernameFieldLabel = new JLabel("old username");
					usernameFieldLabel.setForeground(new Color(96, 104, 97));
					usernameFieldLabel.setPreferredSize(new Dimension(200,30));
					usernameFieldLabel.addMouseListener(this);
					
					passwordFieldLabel = new JLabel("******");
					passwordFieldLabel.setForeground(new Color(96, 104, 97));
					passwordFieldLabel.setPreferredSize(new Dimension(200,30));
					passwordFieldLabel.addMouseListener(this);
	
					nameFieldLabel = new JLabel("Name");
					nameFieldLabel.setForeground(new Color(96, 104, 97));
					nameFieldLabel.setPreferredSize(new Dimension(200,30));
					nameFieldLabel.addMouseListener(this);
					
					telNumberFieldLabel = new JLabel("telNo");
					telNumberFieldLabel.setForeground(new Color(96, 104, 97));
					telNumberFieldLabel.setPreferredSize(new Dimension(200,30));
					telNumberFieldLabel.addMouseListener(this);
					
					emailFieldLabel = new JLabel("Email");
					emailFieldLabel.setForeground(new Color(96, 104, 97));
					emailFieldLabel.setPreferredSize(new Dimension(200,30));
					emailFieldLabel.addMouseListener(this);
					
					addressNumberFieldLabel = new JLabel("Unit No");
					addressNumberFieldLabel.setForeground(new Color(96, 104, 97));
					addressNumberFieldLabel.setPreferredSize(new Dimension(200,30));
					addressNumberFieldLabel.addMouseListener(this);
					
					addressRoadFieldLabel = new JLabel("Road");
					addressRoadFieldLabel.setForeground(new Color(96, 104, 97));
					addressRoadFieldLabel.setPreferredSize(new Dimension(200,30));
					addressRoadFieldLabel.addMouseListener(this);
					
					addressPostcodeFieldLabel = new JLabel("Postcode");
					addressPostcodeFieldLabel.setForeground(new Color(96, 104, 97));
					addressPostcodeFieldLabel.setPreferredSize(new Dimension(200,30));
					addressPostcodeFieldLabel.addMouseListener(this);
					
					addressStateFieldLabel = new JLabel("State");
					addressStateFieldLabel.setForeground(new Color(96, 104, 97));
					addressStateFieldLabel.setPreferredSize(new Dimension(200,30));
					addressStateFieldLabel.addMouseListener(this);
				
				
				fieldLabelPanel.add(usernameFieldLabel);
				fieldLabelPanel.add(passwordFieldLabel);
				fieldLabelPanel.add(nameFieldLabel);
				fieldLabelPanel.add(telNumberFieldLabel);
				fieldLabelPanel.add(emailFieldLabel);
				fieldLabelPanel.add(addressNumberFieldLabel);
				fieldLabelPanel.add(addressRoadFieldLabel);
				fieldLabelPanel.add(addressPostcodeFieldLabel);
				fieldLabelPanel.add(addressStateFieldLabel);
				
			
			
			contentPane.add(fieldLabelPanel,BorderLayout.CENTER);
			
		add(contentPane,BorderLayout.CENTER);
		
			 //add back button
		     northPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		     
			     backButton = new JButton();
			     	backIcon = createResizedIcon("/resources/uiSymbol/back.png",20,20);
			     backButton.setIcon(backIcon);
			     backButton.addMouseListener(this);
			     backButton.addActionListener(this);
			     
			 northPanel.add(backButton);
			 
	     add(northPanel,BorderLayout.PAGE_START);
		pack();
		setVisible(true);
		setResizable(false);

	}

	private ImageIcon createResizedIcon(String imagePath, int width, int height) {
	    
	    ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));

	    Image originalImage = originalIcon.getImage();
	    Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	    return new ImageIcon(resizedImage);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == usernameFieldLabel)
		{
			String username = JOptionPane.showInputDialog("Enter new username");
			//update username 
			
			if(username!=null)
			{
				if(username.length()<6)
				{
					JOptionPane.showMessageDialog(null, "Username should have at least 6 characters.");
				}
				else if(username.length()>50)
				{
					JOptionPane.showMessageDialog(null, "Username should not exceed 50 characters.");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Username is updated successfully");
					usernameFieldLabel.setText(username);
				}
				
			}
			
		}
		else if(e.getSource() == passwordFieldLabel )
		{
			String oldPasswordInput = JOptionPane.showInputDialog("Enter old password");
			//compare old password
			if(oldPasswordInput!=null)
			{
				String newPassword = JOptionPane.showInputDialog("Enter new password");
				//update password 
				
				if(newPassword!=null)
				{
					if(newPassword.length()<8)
					{
						JOptionPane.showMessageDialog(null, "Password should have at least 8 characters.");
					}
					else if(newPassword.length()>50)
					{
						JOptionPane.showMessageDialog(null, "Password should not exceed 50 characters.");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Password is updated successfully");
					
					}
				}
			}
			
			
		}
		
		else if(e.getSource() == nameFieldLabel)
		{
			String name = JOptionPane.showInputDialog("Enter new name");
			//update name 
			if(name!=null)
			{
				if(name.length()>100)
				{
					JOptionPane.showMessageDialog(null, "Name should not exceed 100 characters.");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Name is updated successfully");
					nameFieldLabel.setText(name);
				}
			}
		
			
		}
		else if(e.getSource() == telNumberFieldLabel)
		{
			String telNo = JOptionPane.showInputDialog("Enter new telephone number");
			//update telno 
			if(telNo!=null)
			{
				if(telNo.length()>20)
				{
					JOptionPane.showMessageDialog(null, "Telephone number should not exceed 20 characters.");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Telephone number is updated successfully");
					telNumberFieldLabel.setText(telNo);
				}
			}
		}
		else if(e.getSource() == emailFieldLabel )
		{
			String email = JOptionPane.showInputDialog("Enter new email");
			//update email  
			if(email!=null)
			{
				if(email.length()>100)
				{
					JOptionPane.showMessageDialog(null, "Email should not exceed 100 characters.");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Email is updated successfully");
					emailFieldLabel.setText(email);
				}
			}
		}
		else if(e.getSource() == addressNumberFieldLabel)
		{
			String addressNoString = JOptionPane.showInputDialog("Enter address number");
			
			//update addressNo 
			if(addressNoString!=null)
			{
				if(addressNoString.length()>11)
				{
					JOptionPane.showMessageDialog(null, "Address number should not exceed 11 numbers.");
				}
				else
				{
					try
					{
						int addressNo = Integer.parseInt(addressNoString);
						JOptionPane.showMessageDialog(null, "Address number is updated successfully");
						addressNumberFieldLabel.setText(addressNoString);
					}catch(NumberFormatException nfe)
					{
						JOptionPane.showMessageDialog(null, "Please enter integer for address number.");
					}
					
				}
			}
			
		}
		else if(e.getSource() == addressRoadFieldLabel)
		{
			String roadName = JOptionPane.showInputDialog("Enter new road name");
			//update roadname  
			if(roadName!=null)
			{
				if(roadName.length()>100)
				{
					JOptionPane.showMessageDialog(null, "Road name should not exceed 100 characters.");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Road name is updated successfully");
					addressRoadFieldLabel.setText(roadName);
				}
			}
			
		}
		else if(e.getSource() == addressPostcodeFieldLabel)
		{
			String postcodeString = JOptionPane.showInputDialog("Enter new postcode");
			
			//update postcode 
			if(postcodeString!=null)
			{
				if(postcodeString.length()>11)
				{
					JOptionPane.showMessageDialog(null, "Postcode should not exceed 11 numbers.");
				}
				else
				{
					try {
						int postcode = Integer.parseInt(postcodeString);
						JOptionPane.showMessageDialog(null, "Postcode is updated successfully");
						usernameFieldLabel.setText(postcodeString);
						
					}catch(NumberFormatException nfe)
					{
						JOptionPane.showMessageDialog(null, "Please enter integer for postcode.");
					}
				}
			}
		}
		else if(e.getSource() == addressStateFieldLabel)
		{
			String state = JOptionPane.showInputDialog("Enter new state");
			//update state 
			if(state!=null)
			{
				if(state.length()>100)
				{
					JOptionPane.showMessageDialog(null, "State should not exceed 100 characters.");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "State is updated successfully");
					addressStateFieldLabel.setText(state);
				}
			}
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == usernameFieldLabel || e.getSource() == passwordFieldLabel ||e.getSource() == nameFieldLabel ||e.getSource() == telNumberFieldLabel ||e.getSource() == emailFieldLabel ||e.getSource() == addressNumberFieldLabel  ||e.getSource() == addressRoadFieldLabel ||e.getSource() == addressPostcodeFieldLabel ||e.getSource() == addressStateFieldLabel)
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));	
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == usernameFieldLabel || e.getSource() == passwordFieldLabel ||e.getSource() == nameFieldLabel ||e.getSource() == telNumberFieldLabel ||e.getSource() == emailFieldLabel ||e.getSource() == addressNumberFieldLabel  ||e.getSource() == addressRoadFieldLabel ||e.getSource() == addressPostcodeFieldLabel ||e.getSource() == addressStateFieldLabel)
		{
			setCursor(Cursor.getDefaultCursor());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==backButton)
		{
			dispose();
			UserPage frame = new UserPage();
			frame.setVisible(true);
		}
	}
}
