package com.penpals.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
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
import com.penpals.model.Customer;
import com.penpals.model.Order;
import com.penpals.controller.OrderController;  

public class OrderTrackingGui extends JFrame implements MouseListener, ActionListener {

	private static final long serialVersionUID = 1L;
	
	

	//south panel
	private JPanel southPanel;
		private JLabel completeLabel;
		private JButton completeOrderButton;
		private JButton printReceiptBtn;
	
	//north panel
	private JPanel northPanel;
	
		private JButton backButton;
			private ImageIcon backIcon;
			
		
	private JScrollPane scrollableBrowseArea;		
	
		//center panel
		private JPanel contentPane;
		
			//container consist of status and address
			private JPanel container;
			
				private JPanel statusPanel;
					private JLabel statusLabel;
				
				private JPanel addressPanel;
					private JLabel addressLabel;
				
					private JPanel productPanels;
						private JPanel productPanel ;
							private JLabel imageLabel;
								private ImageIcon productImage; 
								
							private JLabel nameLabel;
							private JPanel qtyPricePanel;
								private JLabel quantityLabel;
								private JLabel priceLabel;
												
			private JPanel totalPanel ;
				private JLabel totalPriceLabel;
				private JLabel totalLabel;
			
			private JPanel datePanel;
				private JLabel dateLabel;
				private JLabel orderDateLabel;
						
	

private Customer cus;
private Order order;
private OrderController orderController = new OrderController();


	/**
	 * Create the frame.
	 */
	public OrderTrackingGui(Customer cus,Order order) {   
		this.cus = cus;
		this.order = order;
		init(order);
	}
	


	private void init(Order order) 
	{
		order.setOrderCartItems(orderController.getAllItembyOrderId(order.getOrderId()));
		//frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLayout(new BorderLayout());
	    setMinimumSize(new Dimension(900,700));
	    setTitle("Penpals Gift Shop");
		     
		   
		    contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
		     
			scrollableBrowseArea = new JScrollPane(contentPane);
		    scrollableBrowseArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		    
	    getContentPane().add(scrollableBrowseArea, BorderLayout.CENTER);  
	    
			    container = new JPanel();
				container.setBorder(new EmptyBorder(10, 10, 10, 10));  
				BoxLayout contentBox = new BoxLayout(container, BoxLayout.Y_AXIS); 
				// to set the box layout 
				container.setLayout(contentBox);
				
					//status panel
					statusPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
					
						statusLabel = new JLabel();
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
						statusLabel.setText(statusString);
						statusLabel.setHorizontalAlignment(JLabel.LEFT);
					
					statusPanel.add(statusLabel);
					statusPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
					
					
					//address panel
					addressPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
					
						addressLabel = new JLabel();
						
							int number = cus.getCustomerAddress().getNumber();
							int postcode = cus.getCustomerAddress().getPostcode();
							String state = cus.getCustomerAddress().getState();
							String road = cus.getCustomerAddress().getRoad();
							String address = String.valueOf(number) + ", " + road + ", " + String.valueOf(postcode)+ ", "+ state;
							address = "Address : " + address;
				
						addressLabel.setText(address);
					
					addressPanel.add(addressLabel);
					addressPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
					
				
				 
					//Product List panel
				 	//iterate through cartitem
					 productPanels = new JPanel(new GridLayout(0,1,0,0));
					 
					 List<CartItem> cartItems = order.getOrderCartItems();
				 
					for(CartItem cartItem : cartItems) 
					{
							createProductPanel(cartItem);
					     
					}
					 container.add(statusPanel);
					 container.add(Box.createRigidArea(new Dimension(0,5)));
					 container.add(addressPanel);
					 container.add(Box.createRigidArea(new Dimension(0,5)));
					 container.add(productPanels);
					
				    //total price Panel
					totalPanel = new JPanel(new BorderLayout());
					
						//total price JLabel 
					     totalPriceLabel = new JLabel();
						   
						     double price = order.getOrderTotal();
						     String totalPriceString = String.valueOf(price);
						     totalPriceString  = "RM " + totalPriceString;
						     
					     totalPriceLabel.setText(totalPriceString);
					     totalPriceLabel.setVerticalAlignment(JLabel.BOTTOM);
					
						 totalLabel = new JLabel("Amount Paid");
						 totalLabel.setVerticalAlignment(JLabel.BOTTOM);
			
					totalPanel.add(totalLabel,BorderLayout.LINE_START);
					totalPanel.add(totalPriceLabel,BorderLayout.LINE_END);
					
					//date panel
					datePanel = new JPanel(new BorderLayout());
					
						dateLabel = new JLabel("Order Date");
						dateLabel.setVerticalAlignment(JLabel.TOP);
						
					    //Order date JLabel
					     String date = order.getOrderDate();
					     String orderDate = date.toString();
					     orderDateLabel = new JLabel();
					     orderDateLabel.setVerticalAlignment(JLabel.TOP);
					     orderDateLabel.setText(orderDate);
					     
					     
					datePanel.add(dateLabel,BorderLayout.LINE_START);
					datePanel.add(orderDateLabel,BorderLayout.LINE_END);
	
			contentPane.add(container,BorderLayout.NORTH);
		    contentPane.add(totalPanel,BorderLayout.CENTER);
		    contentPane.add(datePanel,BorderLayout.SOUTH);
			   
			 //add back button
		     northPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		     	backButton = new JButton();
		     		backIcon = createResizedIcon("/resources/uiSymbol/back.png",20,20);
			     backButton.setIcon(backIcon);
			     backButton.addMouseListener(this);
			     backButton.addActionListener(this);
		     northPanel.add(backButton);
	     add(northPanel,BorderLayout.PAGE_START);
	    
	     
	     	//southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	     	southPanel = new JPanel(new BorderLayout());
     	
	     		
			//complete order button add to frame (exist only if order haven't completed)
			if(!order.getIsCompleted())
			{
						completeOrderButton = new JButton();
						completeOrderButton.setText("Complete Order");	
						completeOrderButton.addActionListener(this);
						completeOrderButton.addMouseListener(this);
						
			southPanel.add(completeOrderButton,BorderLayout.PAGE_START);
			}
			else
			{
						completeLabel = new JLabel();
						completeLabel.setText("The order is completed.");	
						completeLabel.setHorizontalAlignment(JLabel.CENTER);
						
			southPanel.add(completeLabel,BorderLayout.PAGE_START);
			}
			
			 printReceiptBtn = new JButton("Print Receipt");
			southPanel.add(printReceiptBtn,BorderLayout.CENTER);
			
			
		add(southPanel,BorderLayout.SOUTH);
		    
	    setTitle("Penpals Gift Shop");
	    setVisible(true);
    
	}

	

	private ImageIcon createResizedIcon(String imagePath, int width, int height) {
	    
	    ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));
	    Image originalImage = originalIcon.getImage();
	    Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	    return new ImageIcon(resizedImage);
	}
	
	
	public void createProductPanel(CartItem cartItem)
	{
		productPanel = new JPanel(new BorderLayout(5,0));
		//product panel for each Product
		//image JLabel
		imageLabel = new JLabel("");
		
        	productImage = createResizedIcon(cartItem.getCartItemProduct().getProductImageURL(), 100,100); 
        	
        imageLabel.setIcon(productImage);
        imageLabel.setPreferredSize(new Dimension(120,120));
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
   
        
		//product name JLabel
        nameLabel = new JLabel();
        nameLabel.setText(cartItem.getCartItemProduct().getProductName());
        nameLabel.setVerticalAlignment(JLabel.CENTER);
  
    
        qtyPricePanel = new JPanel(new BorderLayout());
			//quantity JLabel
	        quantityLabel = new JLabel();
	        
		        int quantity = cartItem.getCartItemQuantity();
		        String quantityString = String.valueOf(quantity);
		        quantityString = " x " + quantityString ;
		        
		    quantityLabel.setText(quantityString);
	        
			//total Price for each product JLabel
	        priceLabel = new JLabel();
	        
		        double productPrice = cartItem.getCartItemProduct().getProductPrice();
		        double cartItemPrice = productPrice * quantity;
		        // Format the number to 2 decimal price
		        DecimalFormat df = new DecimalFormat("#.00");
		        String cartItemPriceString = df.format(cartItemPrice);

		        cartItemPriceString = "RM " + cartItemPriceString;
	        
	        priceLabel.setText(cartItemPriceString);
        
     	qtyPricePanel.add(quantityLabel,BorderLayout.CENTER);
     	qtyPricePanel.add(priceLabel,BorderLayout.SOUTH);
     	
     
     productPanel.add(imageLabel,BorderLayout.LINE_START);
     productPanel.add(nameLabel,BorderLayout.CENTER);
     productPanel.add(qtyPricePanel,BorderLayout.LINE_END);
     productPanel.setPreferredSize(new Dimension(220,120));
     productPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

 productPanels.add(productPanel);
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==completeOrderButton)
		{
		
			int result = JOptionPane.showConfirmDialog(null,"Are you sure to complete order? Please ensure you have received the product(s) and are satisfied with their condition.","Complete Order",JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION)
			{
				JOptionPane.showMessageDialog(null,"The order is completed.");
				//set status as completed
				orderController.updateOrderIsCompleted(order.getOrderId());
				order.setIsCompleted(true);
				
				dispose();
				//open a new frame with completed order
				//OrderTrackingGui frame = new OrderTrackingGui(cus ,order);
				//PLEASE CHECK to confirm the complete order button disappear when order is completed
				for (CartItem cartItem : order.getOrderCartItems()) {
					System.out.println(cartItem.getCartItemProduct().getProductName());
					System.out.println(cartItem.getCartItemQuantity());
				}
				RatingsAndFeedbackGui frame = new RatingsAndFeedbackGui(cus,order); //delete it later
				frame.setVisible(true);
			}
		}
		else if(e.getSource()==backButton)
		{
			dispose();
			OrderHistoryGui frame = new OrderHistoryGui(cus);
			frame.setVisible(true);
		}
	}

}
