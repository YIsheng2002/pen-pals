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
		
		//north panel
		private JPanel userPanel;
			private JLabel userImageLabel;
				private ImageIcon userImage;
			private JLabel usernameLabel;
		
		//center
		private JPanel centerPanel;
			private JPanel voucherPanel;
				private JButton voucherButton;
				private ImageIcon voucherIcon;
			private JPanel orderHistoryPanel;
				private JButton orderHistoryButton;
				private ImageIcon orderHistoryIcon;
			private JPanel cartPanel;
				private ImageIcon cartIcon;
				private JButton cartButton;
			
		
		

	/**
	 * Create the frame.
	 */
	public UserPage ()
	{
		init();
	}
	
	public void init() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		setTitle("Penpals Gift Shop");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(5,10));
		setContentPane(contentPane);
		setResizable(false);
		
			userPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			
				//user profile image
				userImageLabel = new JLabel("");
		        	userImage = createResizedIcon("/resources/uiSymbol/user_profile.png", 50,50); 
		        userImageLabel.setIcon(userImage);
		        userImageLabel.setVerticalAlignment(JLabel.CENTER);
		        userImageLabel.addMouseListener(this);
	        userPanel.add(userImageLabel);
	        
		        //username label
		        usernameLabel = new JLabel();
		        usernameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		        usernameLabel.setText("Username");
		        usernameLabel.addMouseListener(this);
	        userPanel.add(usernameLabel);
	    
	        centerPanel = new JPanel(new BorderLayout());
	        
	        	//voucher Panel
		        voucherPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		        	voucherIcon = createResizedIcon("/resources/uiSymbol/voucher.png", 50,50); 
					 voucherButton = new JButton("Vouchers						",voucherIcon);
					 voucherButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
					 voucherButton.setPreferredSize(new Dimension(450,100));
					 voucherButton.addActionListener(this);
					 voucherButton.addMouseListener(this);
				 voucherPanel.add(voucherButton);
			 centerPanel.add(voucherPanel,BorderLayout.PAGE_START);
					 
					 
					 
		        //order history label
		    	orderHistoryPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		    		orderHistoryIcon = createResizedIcon("/resources/uiSymbol/orderHistory.png", 50,50); 
			        orderHistoryButton = new JButton("Order History",orderHistoryIcon);
			        orderHistoryButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
			        orderHistoryButton.setPreferredSize(new Dimension(450,100));
			        orderHistoryButton.addActionListener(this);
			        orderHistoryButton.addMouseListener(this);
		        orderHistoryPanel.add(orderHistoryButton);
		    centerPanel.add(orderHistoryPanel,BorderLayout.CENTER);
		     
		        
		        //shopping cart label
		        cartPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		        	cartIcon = createResizedIcon("/resources/uiSymbol/Cart.jpg", 55,55); 
			        cartButton = new JButton("Shopping Cart",cartIcon);
			        cartButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
			        cartButton.setPreferredSize(new Dimension(450,100));
			        cartButton.addActionListener(this);
			        cartButton.addMouseListener(this);
			        
		        cartPanel.add(cartButton);
	        centerPanel.add(cartPanel,BorderLayout.PAGE_END);
	        
        contentPane.add(userPanel,BorderLayout.PAGE_START);
        contentPane.add(centerPanel,BorderLayout.CENTER);
        pack();
        
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
			UserProfile frame = new UserProfile();
			frame.setVisible(true);
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