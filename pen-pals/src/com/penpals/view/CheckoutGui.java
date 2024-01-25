package com.penpals.view;

import java.awt.BorderLayout;
import java.awt.Color;
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
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.penpals.model.Address;
import com.penpals.model.CartItem;
import com.penpals.model.Customer;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;

import com.penpals.model.Coupon;

import com.penpals.controller.CouponController;
import com.penpals.controller.OrderController;
import com.penpals.controller.PaymentController;

public class CheckoutGui extends JFrame  implements MouseListener, ActionListener{

	private CouponController couponController = new CouponController();

	private static final long serialVersionUID = 1L;
	private String paymentType = ""; 
	
	private JPanel northPanel;
		private JPanel northLeftPanel;
			private JButton backButton;
				private ImageIcon backIcon;
		private JPanel northCenterPanel;
			private JLabel checkOutLabel;
	
	
	private JPanel contentPane;
	
		//north panel in contentPane
		private JPanel addressPanel;
			private JLabel addressLabel;
			private JLabel address;
					
		//center panel in contentPane
		private JPanel itemsPanels;
			private JPanel itemPanel;
				private JLabel imageLabel;
					private ImageIcon productImage; 
					
				private JLabel nameLabel;
				private JPanel qtyPricePanel;
					private JLabel quantityLabel;
					private JLabel priceLabel;
		
		//south panel in contentPane
		private JPanel contentSouthPanel;
					
			//north panel in contentSouthPanel
			private JPanel shippingPanel;
			
				private JPanel shippingFeeLabelPanel;
					private JLabel shippingFeeLabel;
				private JPanel shippingFeePanel;
					private JLabel shippingFee;
				
			//center panel in contentSouthPanel
			private JPanel couponPanel;
				private JPanel couponLabelPanel;
					private JLabel couponLabel;
				private JPanel couponSelectPanel;
					private JLabel couponSelectLabel;
				
			//south panel in contentSouthPanel
			private JPanel paymentPanel;
				
				//north panel in paymentPanel
				private JPanel paymentOption;
					private JPanel paymentOptionPanel;
						private JLabel paymentOptionLabel;
					private JPanel paymentSelectPanel;
						private JLabel paymentSelectLabel;
						
				//center panel in paymentPanel		
				private JPanel detailsAndDiscountPanel;
					
					
					private JPanel paymentDetailsPanel;
					
					
						private JLabel paymentDetailsLabel;
				
						private JPanel subTotalPanel;
		
							private JLabel subTotalLabel;
							private JLabel subTotal;
							
						private JPanel shippingSubtotalPanel;
							private JLabel shippingSubtotalLabel;
							private JLabel shippingSubtotal;
							
							private JPanel discountPanel;
								private JLabel discountlLabel;
								private JLabel discount;
					
					private JPanel totalPanel;
						private JLabel totalLabel;
						private JLabel total;
					
		
	private JPanel southPanel;
		private JButton orderButton;
	
	
	private JFrame callingFrame;
	private double grandTotal = 0;
	private double discountRate = 0;
	private  String priceString;
	private Customer cus;
	private OrderController orderController = new OrderController();
	private List<CartItem> checkOutItems = new ArrayList<CartItem>();
	private int couponQuantity;
	private String couponCode;
	/**
	 * Create the frame.
	 */
	public CheckoutGui(Customer cus,List<CartItem> checkOutItems,JFrame callingFrame) {
		this.callingFrame = callingFrame;
		this.cus = cus;
		this.checkOutItems = checkOutItems;
		this.couponQuantity = 0;
		init(cus,checkOutItems);
	}
	
	public void init(Customer cus,List<CartItem> checkOutItems)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		setTitle("Penpals Gift Shop");
		
			northPanel = new JPanel(new BorderLayout());
			
				northLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
						backIcon = createResizedIcon("/resources/uiSymbol/back.png",20,20);
					backButton = new JButton(backIcon);
					backButton.addMouseListener(this);
					backButton.addActionListener(this);
				northLeftPanel.add(backButton);
					
				northCenterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
					checkOutLabel = new JLabel("Checkout");
					checkOutLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
					checkOutLabel.setHorizontalAlignment(SwingConstants.CENTER);
				northCenterPanel.add(checkOutLabel);
			
			northPanel.add(northLeftPanel,BorderLayout.LINE_START);
			northPanel.add(northCenterPanel,BorderLayout.CENTER);
			
		getContentPane().add(northPanel,BorderLayout.NORTH);
		
		
			contentPane = new JPanel(new BorderLayout());
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			
				//north panel in contentPane
				addressPanel = new JPanel(new BorderLayout());
					
					addressLabel =  new JLabel("Delivery Address");
				addressPanel.add(addressLabel,BorderLayout.NORTH);
					
					
					address = new JLabel();
						Address cusAddress = cus.getCustomerAddress();
						String addressString = String.valueOf(cusAddress.getNumber()) + ", " + cusAddress.getRoad() + ", " +  String.valueOf(cusAddress.getPostcode()) + ", " + cusAddress.getState();					
					address.setText(addressString);
				addressPanel.add(address,BorderLayout.CENTER);
				addressPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.add(addressPanel,BorderLayout.NORTH);
			
				//center panel in contentPane
				itemsPanels = new JPanel(new GridLayout(0,1,0,0));
				double totalPrice = 0;
				for(CartItem checkOutItem:checkOutItems)
				{
					itemPanel = createItemPanel(checkOutItem);
					itemsPanels.add(itemPanel);
					totalPrice = totalPrice + ((checkOutItem.getCartItemQuantity()) * checkOutItem.getCartItemProduct().getProductPrice());
				}
				
			contentPane.add(itemsPanels,BorderLayout.CENTER);
				
				
			//south panel in contentPane
			contentSouthPanel = new JPanel(new BorderLayout());			
				//north panel in contentSouthPanel
				shippingPanel = new JPanel(new BorderLayout());
				
					shippingFeeLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
						shippingFeeLabel = new JLabel("Shipping Fee");
						shippingFeeLabel.setHorizontalAlignment(JLabel.LEFT);
					shippingFeeLabelPanel.add(shippingFeeLabel);
				shippingPanel.add(shippingFeeLabelPanel,BorderLayout.LINE_START);
				
					shippingFeePanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
						shippingFee = new JLabel();
						  	DecimalFormat df = new DecimalFormat("#.00");
						  	double shipFee = 4.9;
					        String shipFeeString = df.format(shipFee);
						shippingFee.setText("RM "+String.valueOf(shipFeeString));
					shippingFeePanel.add(shippingFee);
				shippingPanel.add(shippingFeePanel,BorderLayout.LINE_END);
						
			contentSouthPanel.add(shippingPanel,BorderLayout.NORTH);
					
					
				//center panel in contentSouthPanel
				couponPanel = new JPanel(new BorderLayout());
					couponLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
						couponLabel = new JLabel("Coupon");
					couponLabelPanel.add(couponLabel);
				couponPanel.add(couponLabelPanel,BorderLayout.LINE_START);
				
				couponSelectPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
					couponSelectLabel = new JLabel("Enter code >");
						couponSelectLabel.addMouseListener(this);
						couponSelectLabel.setForeground(new Color(137, 137, 137));
					couponSelectPanel.add(couponSelectLabel);
				couponPanel.add(couponSelectPanel,BorderLayout.LINE_END);
			contentSouthPanel.add(couponPanel,BorderLayout.CENTER);
				
					
				//south panel in contentSouthPanel
				paymentPanel = new JPanel(new BorderLayout());
					
					//north panel in paymentPanel
					paymentOption = new JPanel(new BorderLayout());
					
							paymentOptionPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
								paymentOptionLabel = new JLabel("Payment Option");
							paymentOptionPanel.add(paymentOptionLabel);
					paymentOption.add(paymentOptionPanel,BorderLayout.LINE_START);
					
						paymentSelectPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
							paymentSelectLabel = new JLabel("Select payment method >");
							paymentSelectLabel.addMouseListener(this);
							paymentSelectLabel.setForeground(new Color(137, 137, 137));
						paymentSelectPanel.add(paymentSelectLabel);
					paymentOption.add(paymentSelectPanel,BorderLayout.LINE_END);
				paymentPanel.add(paymentOption,BorderLayout.NORTH);
					
				
				detailsAndDiscountPanel = new JPanel(new BorderLayout());
					
							//center panel in paymentPanel
							paymentDetailsPanel = new JPanel(new BorderLayout());
							paymentDetailsPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
								paymentDetailsLabel = new JLabel("Payment Details");
							paymentDetailsPanel.add(paymentDetailsLabel,BorderLayout.PAGE_START);
								
								subTotalPanel = new JPanel(new BorderLayout());
								
									subTotalLabel = new JLabel("Merchandise Subtotal");
									subTotalLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
									subTotalLabel.setForeground(new Color(137, 137, 137));
									subTotalLabel.setHorizontalAlignment(JLabel.LEADING);
								subTotalPanel.add(subTotalLabel,BorderLayout.LINE_START);
							
								
								 	String totalPriceString = df.format(totalPrice);
									subTotal = new JLabel("RM "+String.valueOf(totalPriceString));
									subTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
									subTotal.setForeground(new Color(137, 137, 137));
									subTotal.setHorizontalAlignment(JLabel.TRAILING);
								subTotalPanel.add(subTotal,BorderLayout.LINE_END);
						
						paymentDetailsPanel.add(subTotalPanel,BorderLayout.CENTER);
								
								
							
							shippingSubtotalPanel = new JPanel(new BorderLayout());
							
								shippingSubtotalLabel = new JLabel("Shipping Subtotal");
								shippingSubtotalLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
								shippingSubtotalLabel.setForeground(new Color(137, 137, 137));
								shippingSubtotalLabel.setHorizontalAlignment(JLabel.LEADING);
							shippingSubtotalPanel.add(shippingSubtotalLabel,BorderLayout.LINE_START);
							
								shippingSubtotal = new JLabel("RM "+shipFeeString);
								shippingSubtotal.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
								shippingSubtotal.setForeground(new Color(137, 137, 137));
								shippingSubtotal.setHorizontalAlignment(JLabel.TRAILING);
							shippingSubtotalPanel.add(shippingSubtotal,BorderLayout.LINE_END);
					
						paymentDetailsPanel.add(shippingSubtotalPanel,BorderLayout.PAGE_END);
						
					detailsAndDiscountPanel.add(paymentDetailsPanel,BorderLayout.CENTER);
					
				paymentPanel.add(detailsAndDiscountPanel,BorderLayout.CENTER);
				
				
						totalPanel = new JPanel(new BorderLayout());
						
							totalLabel = new JLabel("Total Payment");
							totalLabel.setHorizontalAlignment(JLabel.LEADING);
						totalPanel.add(totalLabel,BorderLayout.LINE_START);
						
						grandTotal = totalPrice+shipFee;
				        String totalString = df.format(grandTotal);
							total = new JLabel("RM "+totalString);
							total.setHorizontalAlignment(JLabel.TRAILING);
						totalPanel.add(total,BorderLayout.LINE_END);
						totalPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
						
				paymentPanel.add(totalPanel,BorderLayout.SOUTH);
				
			contentSouthPanel.add(paymentPanel,BorderLayout.SOUTH);

		contentPane.add(contentSouthPanel,BorderLayout.SOUTH);
	getContentPane().add(contentPane,BorderLayout.CENTER);
	
		southPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
			orderButton = new JButton("Place Order");
			orderButton.addMouseListener(this);
			orderButton.addActionListener(this);
		southPanel.add(orderButton);
			
	getContentPane().add(southPanel,BorderLayout.PAGE_END);
	pack();
		
	}
	
	private JPanel createItemPanel(CartItem item)
	{

		JPanel itemPanel = new JPanel(new BorderLayout(5,0));
		//product panel for each Product
		//image JLabel
		imageLabel = new JLabel("");
		
        	productImage = createResizedIcon(item.getCartItemProduct().getProductImageURL(), 100,100); 
        	
        imageLabel.setIcon(productImage);
        imageLabel.setPreferredSize(new Dimension(120,120));
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
   
        
		//product name JLabel
        nameLabel = new JLabel();
        nameLabel.setText(item.getCartItemProduct().getProductName());
        nameLabel.setVerticalAlignment(JLabel.CENTER);
  
    
        qtyPricePanel = new JPanel(new BorderLayout());
        qtyPricePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        
			//quantity JLabel
	        quantityLabel = new JLabel();
	        
		        int quantity = item.getCartItemQuantity();
		        String quantityString = String.valueOf(quantity);
		        quantityString = " x " + quantityString ;
		        
		    quantityLabel.setText(quantityString);
	        
			//total Price for each product JLabel
	        priceLabel = new JLabel();
	        
		        double productPrice = item.getCartItemProduct().getProductPrice();
		        double cartItemPrice = productPrice * quantity;
		        // Format the number to 2 decimal price
		        DecimalFormat df = new DecimalFormat("#.00");
		        String cartItemPriceString = df.format(cartItemPrice);

		        cartItemPriceString = "RM " + cartItemPriceString;
	        
	        priceLabel.setText(cartItemPriceString);
        
     	qtyPricePanel.add(quantityLabel,BorderLayout.CENTER);
     	qtyPricePanel.add(priceLabel,BorderLayout.SOUTH);
     	
     
     	itemPanel.add(imageLabel,BorderLayout.LINE_START);
     	itemPanel.add(nameLabel,BorderLayout.CENTER);
     	itemPanel.add(qtyPricePanel,BorderLayout.LINE_END);
     	itemPanel.setPreferredSize(new Dimension(700,150));
     	
     	itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
     	return itemPanel;
	}
	
	private ImageIcon createResizedIcon(String imagePath, int width, int height) {
	    
	    ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));
	    Image originalImage = originalIcon.getImage();
	    Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	    return new ImageIcon(resizedImage);
	}
	
	public void setPaymentType(String type)
	{
        paymentType = type;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==backButton)
		{
			dispose();
			callingFrame.setVisible(true);
		}
		else if(e.getSource()==orderButton)
		{
			if(paymentType=="")
			{
				JOptionPane.showMessageDialog(null,"Please choose a payment type");
			}
			else
			{
				//add to order list
				int orderId = orderController.insertOrder((int)grandTotal, cus.getCustomerId());
				System.out.println(orderId);
				//add to payment
				new PaymentController().insertPayment(orderId, paymentType, grandTotal);
				//deduct coupon
				if(couponQuantity>0)
				{
					couponController.deductCoupon(cus.getCustomerId(), couponCode, couponQuantity);
				}

				//add to order detail
				for(CartItem item:checkOutItems)
				{
					orderController.insertOrderDetail(orderId, item.getCartItemProduct().getProductId(), item.getCartItemQuantity());
				}
				JOptionPane.showMessageDialog(null,"The order is placed successfully.");
			}
			
		}
	}

	//select payment type
	@Override
	public void mouseClicked(MouseEvent e) {
	    if (e.getSource() == paymentSelectLabel) {
	    	
	    	PaymentTypeGui dialog = new PaymentTypeGui(this);
	    	dialog.setVisible(true);
	    	dialog.setSize(450,300);
	    	dialog.setMinimumSize(new Dimension(450,300));
	    	if(paymentType!=null)
	    	{
	    		paymentSelectLabel.setText(paymentType);
	    		
	    	}
	    	
	    	
	    }
	    
	}

	//select coupon
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==couponSelectLabel)
		{
			couponCode = JOptionPane.showInputDialog("Enter coupon code");
			//search whether the code valid
			couponQuantity = couponController.checkCouponCode(cus.getCustomerId(), couponCode, grandTotal);
			if (couponQuantity > 0){
					Coupon coupon = couponController.getCouponDetailbyCode(couponCode);
					
					discountRate = coupon.getDiscountPercentage()/100;
					DecimalFormat df = new DecimalFormat("#.00");
					couponSelectLabel.setText(couponCode);
					grandTotal *= (1-discountRate);
					
					priceString = df.format(grandTotal);
					total.setText("RM " + priceString);
					
					discountPanel = new JPanel(new BorderLayout());
					discountPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
					
					discountlLabel = new JLabel("Discount");
					discountlLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
					discountlLabel.setForeground(new Color(137, 137, 137));
					discountlLabel.setHorizontalAlignment(JLabel.LEADING);
					discountPanel.add(discountlLabel,BorderLayout.LINE_START);
					
					priceString = df.format(grandTotal*discountRate);
					discount = new JLabel("- RM "+ priceString);
					discount.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
					discount.setForeground(new Color(183,80,39));
					discount.setHorizontalAlignment(JLabel.TRAILING);
					discountPanel.add(discount,BorderLayout.LINE_END);
				detailsAndDiscountPanel.add(discountPanel,BorderLayout.PAGE_END);
			}
			
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==backButton||e.getSource()==orderButton||e.getSource()==paymentSelectLabel)
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==backButton||e.getSource()==orderButton|e.getSource()==paymentSelectLabel)
		{
			setCursor(Cursor.getDefaultCursor());
		}
	}

}
