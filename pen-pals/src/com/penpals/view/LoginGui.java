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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.penpals.controller.CustomerController;
import com.penpals.db.*;
import com.penpals.model.Address;
import com.penpals.model.CartItem;
import com.penpals.model.Coupon;
import com.penpals.model.Customer;
import com.penpals.model.Order;
import com.penpals.model.Product;
import com.penpals.model.ProductCategory;
import com.penpals.model.ShoppingCart;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Color;


public class LoginGui extends JFrame implements MouseListener, ActionListener{

	
	private static final long serialVersionUID = 1L;
	
	//content Pane, set background picture
	private JPanel bgPanel;
		private JLabel bgLabel;
	private JPanel contentPane;
//		private JLabel bgLabel;
		//north panel
		private JLabel title;
		
		//west panel
		private JPanel textPanel;
			private JLabel usernameLabel;
			private JLabel passwordLabel;
			
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
	public LoginGui() {
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450,250);
		getContentPane().setLayout(new BorderLayout(10, 10));
		setTitle("Penpals Gift Shop");
        setMinimumSize(new Dimension(450, 250));
        
        try {
			icon = new ImageIcon(LoginGui.class.getResource("/resources/uiSymbol/loginPage.png"));
			scaledImage = icon.getImage().getScaledInstance(450,250, Image.SCALE_SMOOTH);
		} catch (Exception e) {
		    e.printStackTrace();
		}
        
        	//set background picture
	        contentPane = new JPanel(new BorderLayout());
			contentPane.setSize(450,220);
			contentPane.setOpaque(false);
		getContentPane().add(contentPane);
		
			 bgPanel = new JPanel();
			 bgPanel.setSize(450,250);
	
			 	bgLabel = new JLabel("",new ImageIcon(scaledImage),JLabel.CENTER);
			 	bgLabel.setSize(450,250);
		 	bgPanel.add(bgLabel);
		
		getContentPane().add(bgPanel);
		
	
			title = new JLabel("Login",SwingConstants.CENTER);
			title.setFont(new Font("Lucida Grande", Font.BOLD, 16));
			title.setPreferredSize(new Dimension(450,30));
	
		contentPane.add(title,BorderLayout.NORTH);
		
			//creation of a panel to contain Jlabels
			textPanel = new JPanel();
			textPanel.setPreferredSize(new Dimension(70,250));
			textPanel.setOpaque(false);
				//username label
				usernameLabel = new JLabel();
				usernameLabel.setText("Username");
				usernameLabel.setPreferredSize(new Dimension(70,30));
				usernameLabel.setHorizontalAlignment(JLabel.RIGHT);
				
				//password label
				passwordLabel = new JLabel();
				passwordLabel.setText("Password ");
				passwordLabel.setPreferredSize(new Dimension(70,30));
				passwordLabel.setHorizontalAlignment(JLabel.RIGHT);
			
			textPanel.add(usernameLabel);
			textPanel.add(passwordLabel);
			
		contentPane.add(textPanel,BorderLayout.WEST);
		

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
			
		contentPane.add(fieldPanel,BorderLayout.CENTER);
		
				

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
			
			contentPane.add(southPanel,BorderLayout.PAGE_END);
		//setContentPane(contentPane);
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
		else if(e.getSource()== registerButton)
		{
			RegisterGui frame = new RegisterGui();
			frame.setVisible(true);
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
		
//			CustomerController cusCtrl = new CustomerController();
//				String username = usernameField.getText();
//				String password = passwordField.getText();
				Customer cus = loadData();
//				c
//				if(cus != null)
//				{
					dispose();
					BrowseProductGui frame = new BrowseProductGui(cus);
					frame.setVisible(true);
//				}
//				else
//				{
//					JOptionPane.showMessageDialog(null,"Incorrect username or password.");
//					usernameField.setText("");
//					passwordField.setText("");
//				}
		}
	}
//	
//	
//	
//	public void exit()
//	{
//		int result = JOptionPane.showConfirmDialog(null, "Are you sure to exit? ","Exit",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
//		if(result == JOptionPane.YES_OPTION)
//		{
//			JOptionPane.showMessageDialog(null,"Thanks for using!");
//			System.exit(0);
//		}
//	}
	
	//dummy data just for gui testing, delete it later
	public Customer loadData()
	{
		
        Address address = new Address(1,"Jln Penpal",12345,"Melaka");
      

        // Create a sample product category
        ProductCategory category = new ProductCategory(1, "Electronics");

        // Create a sample product
        Product product1 = new Product(1, "Product 1", "Description 1", 19.99, 10, category, "/resources/productImage/White Bear.jpg");
        Product product2 = new Product(2, "Product 2", "Description 2", 29.99, 15, category, "/resources/productImage/White Bear.jpg");
        Product product3 = new Product(3, "Product 3", "Description 3", 39.99, 20, category, "/resources/productImage/White Bear.jpg");


        // Create a sample cart item
     // Create CartItem instances with quantities
        CartItem cartItem1 = new CartItem(2, product1);
        CartItem cartItem2 = new CartItem(1, product2);
        CartItem cartItem3 = new CartItem(3, product3);

        List<CartItem> cartItems = new ArrayList<>();
        // Add CartItem instances to the list
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);
        cartItems.add(cartItem3);
        //ShoppingCart shoppingCart = new ShoppingCart(cartItems);

        // Create a sample order with the cart item
        List<Order> orders = new ArrayList<>();
        List<CartItem> orderCartItems1 = new ArrayList<>();
        orderCartItems1.add(cartItem1);
        orderCartItems1.add(cartItem2);
        orderCartItems1.add(cartItem3);
        
        List<CartItem> orderCartItems2 = new ArrayList<>();
        orderCartItems2.add(cartItem1);
        orderCartItems2.add(cartItem2);
        
        Order order1 = new Order(1, new Date(), false, false, 999.99, null, orderCartItems1);
      
        Order order2 = new Order(2, new Date(), true, true, 939.99, null, orderCartItems2);
        Order order3 = new Order(3, new Date(), false, true, 123.99, null, orderCartItems1);
        Order order4 = new Order(4, new Date(), true, false, 45.99, null, orderCartItems2);
        
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);
        // Create a sample coupon
        Coupon coupon = new Coupon(1, 10.0, "Discount on Electronics", "ELECTRO10", "2024-12-31", 1);

        // Create a sample customer with the address, shopping cart, order, and coupon
        List<Coupon> coupons = new ArrayList<>();
        coupons.add(coupon);

        Customer customer = new Customer(
                "John Doe",
                "1234567890",
                "john.doe@example.com",
                "password123",
                address,
                1,
                null,
                orders,
                coupons
        );
        
        order1.setOrderCustomer(customer);
        order2.setOrderCustomer(customer);
        order3.setOrderCustomer(customer);
        order4.setOrderCustomer(customer);
        return customer;}
}
