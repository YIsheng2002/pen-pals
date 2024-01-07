package com.penpals.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
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
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class UserPage extends JFrame implements ActionListener, MouseListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel userImageLabel;
	private JLabel usernameLabel;
	private JButton orderHistoryButton;
	private JButton cartButton;

	/**
	 * Create the frame.
	 */
	public UserPage ()
	{
		initializeCompoenent();
	}
	
	public void initializeCompoenent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		
		setContentPane(contentPane);
		
			//user profile image
			userImageLabel = new JLabel("");
	        ImageIcon userImage = createResizedIcon("/resources/uiSymbol/user_profile.png", 50,50); 
	        userImageLabel.setIcon(userImage);
	        userImageLabel.setVerticalAlignment(JLabel.CENTER);
	        userImageLabel.addMouseListener(this);
	        
	        //username label
	        usernameLabel = new JLabel();
	        usernameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	        usernameLabel.setText("Username");
	        usernameLabel.addMouseListener(this);
	        
	    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    northPanel.add(userImageLabel);
	    northPanel.add(usernameLabel);
	    
	        //order history label
	    	JPanel orderHistoryPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	        ImageIcon orderHistoryIcon = createResizedIcon("/resources/uiSymbol/orderHistory.png", 50,50); 
	        orderHistoryButton = new JButton("Order History",orderHistoryIcon);
	        orderHistoryButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
	        orderHistoryButton.setPreferredSize(new Dimension(450,100));
	        orderHistoryButton.addActionListener(this);
	        orderHistoryButton.addMouseListener(this);
	        orderHistoryPanel.add(orderHistoryButton);
	     
	        
	        //shopping cart label
	        JPanel cartPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	        ImageIcon cartIcon = createResizedIcon("/resources/uiSymbol/Cart.jpg", 55,55); 
	        cartButton = new JButton("Shopping Cart",cartIcon);
	        cartButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
	        cartButton.setPreferredSize(new Dimension(450,100));
	        cartButton.addActionListener(this);
	        cartButton.addMouseListener(this);
	        cartPanel.add(cartButton);
	        
	        
        contentPane.add(northPanel,BorderLayout.PAGE_START);
        contentPane.add(orderHistoryPanel,BorderLayout.CENTER);
        contentPane.add(cartPanel,BorderLayout.PAGE_END);
        
        pack();
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
		if(e.getSource()==userImageLabel || e.getSource()==usernameLabel)
		{
			dispose();
			//jump to user profile page
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
		if(e.getSource()==orderHistoryButton || e.getSource()==cartButton||e.getSource()==userImageLabel || e.getSource()==usernameLabel)
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==orderHistoryButton || e.getSource()==cartButton||e.getSource()==userImageLabel || e.getSource()==usernameLabel)
		{
			setCursor(Cursor.getDefaultCursor());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==orderHistoryButton)
		{
			dispose();
			//jump to orderlist page
		}
		else if(e.getSource()==cartButton)
		{
			dispose();
			//jump to cart page
		}
	}
}