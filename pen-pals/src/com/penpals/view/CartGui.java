package com.penpals.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.penpals.model.CartItem;
import com.penpals.model.Customer;
import com.penpals.model.Order;
import com.penpals.model.Product;
import com.penpals.model.ProductCategory;

public class CartGui extends JFrame implements  MouseListener, ActionListener{

	private static final long serialVersionUID = 1L;
	List<CartItem> selectedItems=new ArrayList<>();
	List<CartItem> cartItems=new ArrayList<>();
	Order order;
	double totalPrice = 0;
	DecimalFormat df ;
	//north
	private JPanel northPanel;
		private JPanel northLeftPanel;
			private JButton backButton;
				private ImageIcon backIcon;
		private JPanel northCenterPanel;
			private JLabel cartLabel;
		private JPanel northRightPanel;
			private JButton manageButton;
	
	
	//center
	private JScrollPane scrollableBrowseArea;
	
	private JPanel cartItemsPanel;
		private JPanel cartItemPanel;
			//east
			 //private JCheckBox checkBox;
		
			//center
			private JPanel itemPanel;
				private JLabel imageLabel;
					private ImageIcon itemImage;
				private JLabel nameLabel;
			
			//west
			private JPanel qtyPricePanel;
			
				private JPanel qtyPanel;
					private JButton addButton;
//					private JLabel qtyLabel;
					private JButton minusButton;
				
//				private JLabel priceLabel;
			
	//south 
	private JPanel southPanel;
		private JPanel southLeftPanel;
			private JLabel totalPanel;
			private JLabel totalPriceLabel;
			
		private JPanel southCenterPanel;
			private JButton deleteButton;
				private boolean isVisible = false;
		
		private JPanel southRightPanel;
			private JButton checkOutButton;
			
	private Customer cus;
private JFrame callingFrame;
	/**
	 * Create the frame.
	 */
	public CartGui(Customer cus,JFrame callingFrame) {
		this.cus = cus;
		this.callingFrame = callingFrame;
		init(cus);
		
	}
	
	public void init(Customer cus) {
		//cartItems = loadCartItems();
		cartItems = cus.getCustomerShoppingCart().getShoppingCartItems();
		setBounds(100, 100, 500, 300);
		setTitle("Penpals Gift Shop");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
			//north Panel
			northPanel = new JPanel(new BorderLayout());
			
				northLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
						backIcon = createResizedIcon("/resources/uiSymbol/back.png",20,20);
					backButton = new JButton(backIcon);
					backButton.addMouseListener(this);
					backButton.addActionListener(this);
				northLeftPanel.add(backButton);
					
				northCenterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
					cartLabel = new JLabel("Shopping Cart");
					cartLabel.setHorizontalAlignment(JLabel.LEFT);
					cartLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
				northCenterPanel.add(cartLabel);
				
				
				northRightPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
					manageButton = new JButton("Manage Cart");
					manageButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
					manageButton.addActionListener(this);
					manageButton.addMouseListener(this);
				northRightPanel.add(manageButton);
			
				
			northPanel.add(northLeftPanel,BorderLayout.LINE_START);
			northPanel.add(northCenterPanel,BorderLayout.CENTER);
			northPanel.add(northRightPanel,BorderLayout.LINE_END);
		add(northPanel,BorderLayout.PAGE_START);
		
		
		
			cartItemsPanel = new JPanel(new GridLayout(0,1,0,0));
			
		 
			for(CartItem cartItem : cartItems) 
			{
				cartItemPanel = createProductPanel(cartItem);
				cartItemsPanel.add(cartItemPanel);
			}
			
			
			 scrollableBrowseArea = new JScrollPane(cartItemsPanel);
		     scrollableBrowseArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		       
	       
	     add(scrollableBrowseArea,BorderLayout.CENTER); 
	       
		     southPanel = new JPanel(new BorderLayout());
		     
			     southLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
			     
			    
			     	totalPanel = new JLabel("Total Price : RM ");
			     	totalPanel.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
			     	
			     	 df = new DecimalFormat("#.00");
				     String priceString;

				     if (totalPrice == 0) {
				         priceString = "0.00";
				     } else {
				         priceString = df.format(totalPrice);
				     }
		     		totalPriceLabel = new JLabel(String.valueOf(priceString));
		     		totalPriceLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		     		totalPriceLabel.setVerticalAlignment(JLabel.CENTER);
		     	southLeftPanel.add(totalPanel);
	     		southLeftPanel.add(totalPriceLabel);
	     		
	     		
	     		
	     		southCenterPanel = new JPanel();
	     			deleteButton = new JButton("Delete");
	     			deleteButton.setPreferredSize(new Dimension(100,35));
	     			deleteButton.setForeground(Color.RED);
	     			deleteButton.setOpaque(true);  
	     			deleteButton.setVisible(isVisible);
	     			deleteButton.setEnabled(isVisible);
	     			deleteButton.addActionListener(this);
     			southCenterPanel.add(deleteButton);
	  		
		     	southRightPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		     		checkOutButton = new JButton("Check Out");
		     		checkOutButton.setPreferredSize(new Dimension(100,40));
		     		checkOutButton.addActionListener(this);
	     		southRightPanel.add(checkOutButton);
	     		
     		southPanel.add(southLeftPanel,BorderLayout.LINE_START);
     		southPanel.add(southCenterPanel,BorderLayout.CENTER);
     		southPanel.add(southRightPanel,BorderLayout.LINE_END);
     	
     		
     	add(southPanel,BorderLayout.PAGE_END);
	     
		setVisible(true);
		pack();
	}
	
	private JPanel createProductPanel(CartItem cartItem) {
		
		JPanel cartItemPanel = new JPanel(new BorderLayout(5,0));
			
			//center
			itemPanel = new JPanel(new BorderLayout(5,0));
				//image JLabel
				imageLabel = new JLabel("");
		        	itemImage = createResizedIcon(cartItem.getCartItemProduct().getProductImageURL(), 100,100); 
					//itemImage = createResizedIcon("/resources/productImage/White Bear.jpg", 100,100); 
		        imageLabel.setIcon(itemImage);
		        imageLabel.setVerticalAlignment(JLabel.CENTER);
		   
		    
		      	//product name JLabel
		        nameLabel = new JLabel();
		        nameLabel.setText(cartItem.getCartItemProduct().getProductName());
//		        nameLabel.setText("Product");
		        nameLabel.setVerticalAlignment(JLabel.CENTER);
		        
		    itemPanel.add(imageLabel,BorderLayout.WEST);
		    itemPanel.add(nameLabel,BorderLayout.CENTER);
		cartItemPanel.add(itemPanel,BorderLayout.CENTER);
		    
			//east
			qtyPricePanel = new JPanel(new BorderLayout());
			qtyPricePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
				qtyPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
			
				int qty  = cartItem.getCartItemQuantity();
//				int qty = 2;
				JLabel qtyLabel = new JLabel(String.valueOf(qty));
				qtyLabel.setPreferredSize(new Dimension(20,20));
				qtyLabel.setHorizontalAlignment(JLabel.CENTER);
				qtyLabel.setVerticalAlignment(JLabel.CENTER);
				
//				double price = 100;
				double price = cartItem.getCartItemProduct().getProductPrice();
				 String priceString;
				 df = new DecimalFormat("#.00");
			     if (price == 0) {
			         priceString = "0.00";
			     } else {
			    	 priceString = "RM " + df.format(price * qty);
			     }
			     
				JLabel priceLabel = new JLabel(priceString);
				priceLabel.setHorizontalAlignment(JLabel.CENTER);
				priceLabel.setVerticalAlignment(JLabel.TOP);
				

		     	 
				//west
				JCheckBox checkBox = new JCheckBox();
				checkBox.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent event) {
				    	String changePriceString;
				        JCheckBox cb = (JCheckBox) event.getSource();
				        
				        if (cb.isSelected()) {
				            totalPrice += Double.parseDouble(priceLabel.getText().replace("RM ", ""));
				            selectedItems.add(cartItem);
				            
				        } else {
				            totalPrice -= Double.parseDouble(priceLabel.getText().replace("RM ", ""));
				            selectedItems.remove(cartItem);
				        }
				        
				        df = new DecimalFormat("#.00");
				        if (totalPrice == 0) {
			            	changePriceString = "0.00";
					     } else {
					    	 changePriceString = df.format(totalPrice);
					     }
				        
				        totalPriceLabel.setText(changePriceString);
				    }
				});

			cartItemPanel.add(checkBox,BorderLayout.LINE_START);
			
				minusButton = new JButton("-");
			    minusButton.setPreferredSize(new Dimension(20, 20));
			    minusButton.addMouseListener(this);
			    minusButton.addActionListener(e -> updateQty(-1, qtyLabel, priceLabel,price,checkBox,cartItem));

				addButton = new JButton("+");
			    addButton.setPreferredSize(new Dimension(20, 20));
			    addButton.addMouseListener(this);
			    addButton.addActionListener(e -> updateQty(1, qtyLabel,priceLabel,price,checkBox,cartItem));
					
				qtyPanel.add(minusButton);
				qtyPanel.add(qtyLabel);
				qtyPanel.add(addButton);
				
			qtyPricePanel.add(qtyPanel,BorderLayout.CENTER);
			qtyPricePanel.add(priceLabel,BorderLayout.SOUTH);
			
			
		
		cartItemPanel.add(qtyPricePanel,BorderLayout.LINE_END);
		cartItemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		cartItemPanel.setPreferredSize(new Dimension(700,150));
	
		return cartItemPanel;
	}

	private ImageIcon createResizedIcon(String imagePath, int width, int height) {
		    
		    ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));
	
		    Image originalImage = originalIcon.getImage();
		    Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		    return new ImageIcon(resizedImage);
		}
	
	
	public void checkOut(List<CartItem> checkOutItem)
	{
		dispose();
		CheckoutGui frame = new CheckoutGui(cus,checkOutItem,this);
		frame.setVisible(true);
	}
	
	public List<CartItem> loadCartItems()  //dummy data , call controller function later
	{
		ProductCategory category = new ProductCategory(1, "Sample Category");
		
		
        // Sample products
        Product product1 = new Product(1, "Product 1", "Description 1", 19.99, 10, category, "/resources/productImage/White Bear.jpg");
        Product product2 = new Product(2, "Product 2", "Description 2", 29.99, 15, category, "/resources/productImage/White Bear.jpg");
        Product product3 = new Product(3, "Product 3", "Description 3", 39.99, 20, category, "/resources/productImage/White Bear.jpg");

        // Create CartItem instances with quantities
        CartItem cartItem1 = new CartItem(2, product1);
        CartItem cartItem2 = new CartItem(1, product2);
        CartItem cartItem3 = new CartItem(3, product3);

        // Add CartItem instances to the list
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);
        cartItems.add(cartItem3);

        return cartItems;
		   
		
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
		
		if(e.getSource()==checkOutButton||e.getSource()==manageButton||e.getSource()==deleteButton||e.getSource()==minusButton||e.getSource()==addButton||e.getSource()==backButton)
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==checkOutButton||e.getSource()==manageButton||e.getSource()==deleteButton||e.getSource()==minusButton||e.getSource()==addButton||e.getSource()==backButton)
		{
			setCursor(Cursor.getDefaultCursor());
		}
	}
	
	private void updateQty(int change, JLabel qtyLabel, JLabel priceLabel, double price, JCheckBox checkBox,CartItem cartItem) {
		df = new DecimalFormat("#.00");
	    String qtyString = String.valueOf(cartItem.getCartItemQuantity());
	    int qty = Integer.parseInt(qtyString) + change;
	    qty = Math.max(1, qty);
	    cartItem.setCartItemQuantity(qty);
	    if(checkBox.isSelected() && qty > 0)
	    {
	    	String updatedPrice;
	    	totalPrice = totalPrice - (Double.parseDouble(priceLabel.getText().replace("RM ", ""))) + ( price * qty);
	    	 
	    	 if (totalPrice == 0) {
	    		 updatedPrice = "0.00";
		     } else {
		    	 updatedPrice = df.format(totalPrice);
		     }
	    	totalPriceLabel.setText(updatedPrice);
	    }
	    
	    qtyLabel.setText(String.valueOf(qty));
	     if (price * qty == 0) {
	    	 priceLabel.setText("RM 0.00");
	     } else {
	    	 priceLabel.setText("RM " + df.format(price * qty));
	     }
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==checkOutButton)
		{
			if(selectedItems.isEmpty())
			{
				JOptionPane.showMessageDialog(null, "No item is selected.","Delete Item(s)",JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				//pass to checkout page
				checkOut(selectedItems);
			}
			
		}
		else if(e.getSource()==manageButton)
		{
			isVisible = !isVisible;
			deleteButton.setVisible(isVisible);
			deleteButton.setEnabled(isVisible);
		}
		
		else if(e.getSource()==deleteButton)
		{
			if(selectedItems.isEmpty())
			{
				JOptionPane.showMessageDialog(null, "No item is selected.","Delete Item(s)",JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				
				int choice = JOptionPane.showConfirmDialog(null, "Are you sure to delete this/these item(s) from shopping cart?","Delete Item(s)", JOptionPane.YES_NO_OPTION);
				{
					if(choice == JOptionPane.YES_OPTION)
					{
						//delete selected product(s) from cart
						for(CartItem cartItem : selectedItems)
						{
							//remove cartItem from database, cartItem as parameter
						}
						dispose();
						CartGui frame = new CartGui(cus,callingFrame);
						frame.setVisible(true);
					}
					
				}
		
			}
		}
		else if(e.getSource()==backButton)
		{
			dispose();
			callingFrame.setVisible(true);
		}
	}
}
