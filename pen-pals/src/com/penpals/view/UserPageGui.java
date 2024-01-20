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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.penpals.model.Address;
import com.penpals.model.CartItem;
import com.penpals.model.Coupon;
import com.penpals.model.Customer;
import com.penpals.model.Order;
import com.penpals.model.Product;
import com.penpals.model.ProductCategory;
import com.penpals.model.ShoppingCart;

import java.awt.Font;

public class UserPageGui extends JFrame implements ActionListener, MouseListener{

	private static final long serialVersionUID = 1L;

		
	
	private JPanel northPanel;
		private JButton backButton;
			private ImageIcon backIcon;
	
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
			
	private Customer cus;
		

	/**
	 * Create the frame.
	 */
	public UserPageGui (Customer cus)
	{
		this.cus = cus;
		init(cus);
	}
	
	public void init(Customer cus) {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		setTitle("Penpals Gift Shop");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(5,10));
		setContentPane(contentPane);
		setResizable(false);
		
			northPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
				backIcon = createResizedIcon("/resources/uiSymbol/back.png",20,20);
				backButton = new JButton(backIcon);
				backButton.addMouseListener(this);
				backButton.addActionListener(this);
			northPanel.add(backButton);
		add(northPanel,BorderLayout.PAGE_START);
		
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
	        
        contentPane.add(userPanel,BorderLayout.CENTER);
        contentPane.add(centerPanel,BorderLayout.PAGE_END);
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
			UserProfileGui frame = new UserProfileGui(cus);
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
		if(e.getSource()==orderHistoryButton || e.getSource()==cartButton||e.getSource()==userImageLabel || e.getSource()==usernameLabel||e.getSource()==backButton)
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==orderHistoryButton || e.getSource()==cartButton||e.getSource()==userImageLabel || e.getSource()==usernameLabel||e.getSource()==backButton)
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
			OrderHistoryGui frame = new OrderHistoryGui(cus);
			frame.setVisible(true);
		}
		else if(e.getSource()==cartButton)
		{
			dispose();
			CartGui frame = new CartGui(cus,this);
			frame.setVisible(true);
		}
		else if(e.getSource()==backButton)
		{
			dispose();
			BrowseProductGui frame = new BrowseProductGui(cus);
			frame.setVisible(true);
		}
	}
}
	
