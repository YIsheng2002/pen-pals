package com.penpals.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.penpals.model.CartItem;
import com.penpals.model.Order;

public class OrderTracking extends JFrame implements MouseListener, ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton completeOrderButton;
	private JButton backButton;
	private Order order;




	/**
	 * Create the frame.
	 */
	public OrderTracking(Order order) {   
		this.order = order;
		initializeComponent(order);
	}
	


	private void initializeComponent(Order order) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLayout(new BorderLayout());
		 Dimension minimumSize = new Dimension(900,700); 
			//set frame min size
	     setMinimumSize(minimumSize);
	     
			//status JLabel
			String statusString;
			if(order.getOrderIsDelivered())
			{
				statusString = "Status: In Delivery";
			}
			else
			{
				statusString = "Status: Delivered";
			}
			JLabel statusLabel = new JLabel();
			statusLabel.setText(statusString);
			statusLabel.setHorizontalAlignment(JLabel.LEFT);
			JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
			statusPanel.add(statusLabel);
			statusPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
			
			
			//address JLanel
			int number = order.getOrderCustomer().getCustomerAddress().getNumber();
			int postcode = order.getOrderCustomer().getCustomerAddress().getPostcode();
			String state = order.getOrderCustomer().getCustomerAddress().getState();
			String road = order.getOrderCustomer().getCustomerAddress().getRoad();
			
			String address = String.valueOf(number) + ", " + road + ", " + String.valueOf(postcode)+ state;
			address = "Address : " + address;
			JLabel addressLabel = new JLabel();
			addressLabel.setText(address);
			JPanel addressPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
			addressPanel.add(addressLabel);
			addressPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
			
			
			JPanel container = new JPanel();
			container.setBorder(new EmptyBorder(10, 10, 10, 10));  
		
		 BoxLayout contentBox = new BoxLayout(container, BoxLayout.Y_AXIS); 
		 // to set the box layout 
		 container.setLayout(contentBox);
		 container.add(statusPanel);
		 container.add(Box.createRigidArea(new Dimension(0,5)));
		 container.add(addressPanel);
		 container.add(Box.createRigidArea(new Dimension(0,5)));
		 
			//Product List panel
		 	//iterate through cartitem
		 JPanel productPanels = new JPanel(new GridLayout(0,1,0,0));
		 List<CartItem> cartItems = order.getOrderCartItems();
		 
			for(CartItem cartItem : cartItems) // delete this and change to iteration of list
			{
				
//					//product panel for each Product
					//image JLabel
					JLabel imageLabel = new JLabel("");
			        ImageIcon productImage = createResizedIcon(cartItem.getCartItemProduct().getProductImageURL(), 100,100); 
			        imageLabel.setIcon(productImage);
			        imageLabel.setVerticalAlignment(JLabel.CENTER);
			        
			   
			        
					//product name JLabel
			        JLabel nameLabel = new JLabel();
			        nameLabel.setText(cartItem.getCartItemProduct().getProductName());
			        nameLabel.setVerticalAlignment(JLabel.CENTER);
			  
		        
					//quantity JLabel
			        //int quantity = cartItem.getQuantity();
			        int quantity = cartItem.getCartItemQuantity();
			        String quantityString = String.valueOf(quantity);
			        quantityString = " x " + quantityString ;
			        JLabel quantityLabel = new JLabel();
			        quantityLabel.setText(quantityString);
			        
					//total Price JLabel
			        double productPrice = cartItem.getCartItemProduct().getProductPrice();
			        double cartItemPrice = productPrice * quantity;
			        // Format the number to 2 decimal price
			        DecimalFormat df = new DecimalFormat("#.00");
			        String cartItemPriceString = df.format(cartItemPrice);

			        cartItemPriceString = "RM " + cartItemPriceString;
			        
			        
			        JLabel priceLabel = new JLabel();
			        priceLabel.setText(cartItemPriceString);
			        
			     JPanel productPanel = new JPanel(new BorderLayout(5,0));
			     productPanel.add(imageLabel,BorderLayout.LINE_START);
			     productPanel.add(nameLabel,BorderLayout.CENTER);
			     
			     	JPanel qtyPricePanel = new JPanel(new BorderLayout());
			     	qtyPricePanel.add(quantityLabel,BorderLayout.CENTER);
			     	qtyPricePanel.add(priceLabel,BorderLayout.SOUTH);
			     	
	
			     productPanel.add(qtyPricePanel,BorderLayout.LINE_END);
			     productPanel.setPreferredSize(new Dimension(200,100));
			     productPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

			     productPanels.add(productPanel);
			     
			}
			container.add(productPanels);
			
		     //total price JLabel 
		     //double price = order.getOrderTotal();
		     double price = order.getOrderTotal();
		     String totalPriceString = String.valueOf(price);
		     totalPriceString  = "RM " + totalPriceString;
		     JLabel totalPriceLabel = new JLabel();
		     totalPriceLabel.setText(totalPriceString);
		     totalPriceLabel.setVerticalAlignment(JLabel.BOTTOM);
		     
		     //Order date JLabel
		     Date date = order.getOrderDate();
		     String orderDate = date.toString();
		     JLabel orderDateLabel = new JLabel();
		     orderDateLabel.setVerticalAlignment(JLabel.TOP);
		     orderDateLabel.setText(orderDate);
		     
		    //total price Panel
			JPanel totalPanel = new JPanel(new BorderLayout());
			
				JLabel totalLabel = new JLabel("Amount Paid");
				totalLabel.setVerticalAlignment(JLabel.BOTTOM);
	
			totalPanel.add(totalLabel,BorderLayout.LINE_START);
			totalPanel.add(totalPriceLabel,BorderLayout.LINE_END);
			
			//date panel
			JPanel datePanel = new JPanel(new BorderLayout());
			
				JLabel dateLabel = new JLabel("Order Date");
				dateLabel.setVerticalAlignment(JLabel.TOP);
				
			datePanel.add(dateLabel,BorderLayout.LINE_START);
			datePanel.add(orderDateLabel,BorderLayout.LINE_END);


	 //add back button
     JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
     backButton = new JButton();
     ImageIcon backIcon = createResizedIcon("/resources/uiSymbol/back.png",20,20);
     backButton.setIcon(backIcon);
     backButton.addMouseListener(this);
     backButton.addActionListener(this);
     northPanel.add(backButton);
     add(northPanel,BorderLayout.PAGE_START);
    
		//complete order button add to frame (exist only if order haven't completed)
		if(!order.getIsCompleted())
		{
			JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			completeOrderButton = new JButton();
			completeOrderButton.setText("Complete Order");	
			completeOrderButton.addActionListener(this);
			completeOrderButton.addMouseListener(this);
			buttonPanel.add(completeOrderButton);
			add(buttonPanel,BorderLayout.SOUTH);
		}
		else
		{
			JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			JLabel completeLabel = new JLabel();
			completeLabel.setText("The order is completed.");	
			completeLabel.setHorizontalAlignment(JLabel.CENTER);
			southPanel.add(completeLabel);
			add(southPanel,BorderLayout.SOUTH);
		}
	
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	contentPane.setLayout(new BorderLayout(0, 0));
     
	JScrollPane scrollableBrowseArea = new JScrollPane(contentPane);
    scrollableBrowseArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    contentPane.add(container,BorderLayout.NORTH);
    contentPane.add(totalPanel,BorderLayout.CENTER);
    contentPane.add(datePanel,BorderLayout.SOUTH);
    getContentPane().add(scrollableBrowseArea, BorderLayout.CENTER);  
    setTitle("Order Details");
    setVisible(true);
    
	
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==completeOrderButton)
		{
		
			int result = JOptionPane.showConfirmDialog(null,"Are you sure to complete order? Please ensure you have received the product(s) and are satisfied with their condition.","Complete Order",JOptionPane.YES_NO_OPTION);
			if(result == 0)
			{
				JOptionPane.showMessageDialog(null,"The order is completed.");
				//set status as completed
				order.setIsCompleted(true);
				
				dispose();
				//open a new frame with completed order
				//OrderTracking frame = new OrderTracking(order); //uncomment it 
				//PLEASE CHECK to confirm the complete order button disappear when order is completed
				RatingsAndFeedback frame = new RatingsAndFeedback(order); //delete it later
				frame.setVisible(true);
			}
		}
		else if(e.getSource()==backButton)
		{
			//jump to user profile
		}
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
		if(e.getSource()==completeOrderButton||e.getSource()==backButton)
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==completeOrderButton ||e.getSource()==backButton)
		{
			setCursor(Cursor.getDefaultCursor());
		}
		
	}

}
