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

import com.penpals.model.Order;

public class OrderTracking extends JFrame implements MouseListener, ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton completeOrderButton;
	private JButton backButton;




	/**
	 * Create the frame.
	 */
//	public OrderTracking(Order order) {    //uncomment it
//		initializeComponent(order);
//	}
	
	public OrderTracking() { //delete it later
		initializeComponent();
	}
	
	
	//private void initializeComponent(Order order)  // uncomment it, pass Order as parameter
	private void initializeComponent() //delete this later
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLayout(new BorderLayout());
	
			//status JLabel
			//status = order.getOrderIsDelivered();
			int status = 0;
			String statusString;
			if(status == 0)
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
			//String address = order.getCustomer().getAddress();
			String address = "123,Jalan Melaka";
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
			for(int i = 0; i<13;i++) // delete this and change to iteration of list
			{
				
//					//product panel for each Product
					//image JLabel
					JLabel imageLabel = new JLabel("");
					//ImageIcon productImage = createResizedIcon(product.getURL(), 100,100); //uncomment this
			        ImageIcon productImage = createResizedIcon("/resources/uiSymbol/Cart.jpg", 100,100); //delete this later
			        imageLabel.setIcon(productImage);
			        imageLabel.setVerticalAlignment(JLabel.CENTER);
			        
			   
			        
					//product name JLabel
			        JLabel nameLabel = new JLabel();
			        //nameLabel.setText(cartItem.getProduct().getName());
			        nameLabel.setText("Product Name");
			        nameLabel.setVerticalAlignment(JLabel.CENTER);
			  
		        
					//quantity JLabel
			        //int quantity = cartItem.getQuantity();
			        int quantity = 3;
			        String quantityString = String.valueOf(quantity);
			        quantityString = " x " + quantityString;
			        JLabel quantityLabel = new JLabel();
			        quantityLabel.setText(quantityString);
			        
					//total Price JLabel
			        //double productPrice = cartItem.getProduct().getPrice();
			        double productPrice = 100;
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
	//		     	BoxLayout qtyPriceBox = new BoxLayout(qtyPricePanel, BoxLayout.Y_AXIS); 
	//		     	qtyPricePanel.setLayout(qtyPriceBox);
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
		     double price = 234.55;
		     String totalPriceString = String.valueOf(price);
		     totalPriceString  = "RM " + totalPriceString;
		     JLabel totalPriceLabel = new JLabel();
		     totalPriceLabel.setText(totalPriceString);
		     totalPriceLabel.setVerticalAlignment(JLabel.BOTTOM);
		     
		     //Order date JLabel
		     //Date orderDate = order.getOrderDate(); //have to convert to String
		     String orderDate = "12-12-2023";
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
    
		//complete order button add to frame (exist only if order haven't completed
		if(status==0)
		{
			JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			completeOrderButton = new JButton();
			completeOrderButton.setText("Complete Order");	
			completeOrderButton.addActionListener(this);
			completeOrderButton.addMouseListener(this);
			buttonPanel.add(completeOrderButton);
			add(buttonPanel,BorderLayout.SOUTH);
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
    
	 Dimension minimumSize = new Dimension(900,700); // Adjust the dimensions as needed
		//set frame min size
     setMinimumSize(minimumSize);
	
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
				//order.setStatus(1); //uncommment it later
				
				dispose();
				//open a new frame with completed order
				//OrderTracking frame = new OrderTracking(order); //uncomment it 
				//PLEASE CHECK to confirm the complete order button disappear when order is completed
				OrderTracking frame = new OrderTracking(); //delete it later
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
