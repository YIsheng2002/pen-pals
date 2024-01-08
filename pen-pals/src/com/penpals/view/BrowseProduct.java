package com.penpals.view;
import com.penpals.model.Product;

import com.penpals.db.MyDatabase;


import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BrowseProduct extends JFrame implements MouseListener, ActionListener{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Product> products = new ArrayList<>();
	
	private JPanel contentPane;
	
		//north panel
		private JPanel northPanel;
		
			//west panel
			private JPanel northLeftPanel;
				private JLabel searchLabel;
				private ImageIcon searchIcon;
				private JTextField textField;
		
			//east panel
			private JPanel northRightPanel;
				private JButton shoppingCart;
					private ImageIcon cartIcon;
				private JButton userProfile;
				private ImageIcon profileIcon;
			
		//center panel
		private JScrollPane scrollableBrowseArea;
			private JPanel productPanel;
				private JPanel productPanelItem;
					private JLabel productImageLabel;
						private ImageIcon productImage;
				private JLabel productNameLabel;
				private JLabel productPriceLabel;
	
	/**
	 * Create the frame.
	 */
	public BrowseProduct() {

		init();
	}
	
	private void init() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 1000, 900, 700);
		//set frame min size
        setMinimumSize(new Dimension(900,700));
        
	        contentPane = new JPanel(new BorderLayout());
	        
		        //northPanel
				northPanel = new JPanel(new BorderLayout(0,0));
				northPanel.setPreferredSize(new Dimension(450,30));
				northPanel.setOpaque(false);
				
					//north left corner
					northLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
					northLeftPanel.setOpaque(false);
				        //Search icon
				        searchLabel = new JLabel("");
				        	searchIcon = createResizedIcon("/resources/uiSymbol/searchSymbol.jpg", 15, 15);
				        searchLabel.setIcon(searchIcon);
			        northLeftPanel.add(searchLabel);
		        
				        //Search text area
						textField = new JTextField();
						textField.setColumns(40);
						
					northLeftPanel.add(textField);
				northPanel.add(northLeftPanel,BorderLayout.WEST);
		
					//north right corner
					northRightPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 5, 0));
					northRightPanel.setOpaque(false);
			
						//shopping cart button
						shoppingCart = new JButton("");
						 	cartIcon = createResizedIcon("/resources/uiSymbol/Cart.jpg", 22, 22);
						shoppingCart.setIcon(cartIcon);
						shoppingCart.addMouseListener(this);
			
						//user profile button
						userProfile = new JButton("");
						 	profileIcon = createResizedIcon("/resources/uiSymbol/user_profile.png", 22, 22);
						userProfile.setIcon(profileIcon);
						userProfile.addMouseListener(this);	
	
			
					northRightPanel.add(shoppingCart);
					northRightPanel.add(userProfile);
		
				northPanel.add(northRightPanel,BorderLayout.EAST);

			contentPane.add(northPanel,BorderLayout.NORTH);
		
       
		       try {
				products = loadProducts();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	       //JPanel for products
	        productPanel = new JPanel(new GridLayout(0, 3, 20, 50));
	
	       
	        for (Product product : products) {
	        	
	           // Create a panel for each product with a BorderLayout
		           productPanelItem = new JPanel(new BorderLayout());
		           // Create a JLabel for the image
		            	productImageLabel = new JLabel("");
		            		productImage = createResizedIcon(product.getProductImageURL(), 300, 300);
			           productImageLabel.setIcon(productImage);
			           productImageLabel.setHorizontalAlignment(JLabel.CENTER);
			           productImageLabel.setVerticalAlignment(JLabel.BOTTOM);
			           productImageLabel.setPreferredSize(new Dimension(350,350));
		
			           // Create a JLabel for the name
			           productNameLabel = new JLabel(product.getProductName());
			           productNameLabel.setHorizontalAlignment(JLabel.CENTER);
			           productNameLabel.setVerticalAlignment(JLabel.BOTTOM);
			           productNameLabel.setPreferredSize(new Dimension(20,20));
			           productNameLabel.addMouseListener(new MouseAdapter() {
		        	    @Override
		        	    public void mouseClicked(MouseEvent e) {
		        	        // Handle the click event, e.g., open a product page
		        	        // openProductPage(product);
		        	    }
		
		        	    @Override
		        	    public void mouseEntered(MouseEvent e) {
		        	        // Change the cursor to indicate it's clickable
		        	        productNameLabel.setForeground(Color.BLUE);
		        	        productNameLabel.setText("<html><u>" + product.getProductName() + "</u></html>");
		        	        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        	    }
		
		        	    @Override
		        	    public void mouseExited(MouseEvent e) {
		        	        // Change the cursor back to the default
		        	        productNameLabel.setForeground(Color.BLACK);
		        	        productNameLabel.setText(product.getProductName());
		        	        setCursor(Cursor.getDefaultCursor());
		        	    }
			           	});
		     
				           // Create a JLabel for the price
				           double price = product.getProductPrice();
				           // Convert double to String
				           String priceString = String.valueOf(price);
			           productPriceLabel = new JLabel(priceString);
			           productPriceLabel.setHorizontalAlignment(JLabel.CENTER);
		
		
		           // Add components to the productPanelItem
		           productPanelItem.add(productImageLabel, BorderLayout.NORTH);
		           productPanelItem.add(productNameLabel, BorderLayout.CENTER);
		           productPanelItem.add(productPriceLabel, BorderLayout.SOUTH);
		
		          // Add the productPanelItem to the main productPanel
		       productPanel.add(productPanelItem);
		           
		           
	       }
	
	       scrollableBrowseArea = new JScrollPane(productPanel);
	       scrollableBrowseArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	       scrollableBrowseArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	       
	       
	       contentPane.add(scrollableBrowseArea, BorderLayout.CENTER); 
	       contentPane.setOpaque(false);
	       
       setBackground(new Color(235, 217, 209));
       setContentPane(contentPane);
       pack();
       setVisible(true);
		
	}

	private ImageIcon createResizedIcon(String imagePath, int width, int height) {
	    
	    ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));

	    Image originalImage = originalIcon.getImage();
	    Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	    return new ImageIcon(resizedImage);
	}


	private ArrayList<Product> loadProducts() throws ClassNotFoundException, SQLException
	{
		//retrieve all products in database
		//call function from controller
		
		//Connection
		
//		
//		Connection con = MyDatabase.doConnection();
//		Statement ps = con.createStatement();
//		
//		
//		String sql = "SELECT * FROM PRODUCT";
//		ResultSet rs = ps.executeQuery(sql);
//		while(rs.next())
//		{
//			Product product = new Product();
//			product.setProductName(rs.getString("Name"));
//			product.setProductId(rs.getInt("Id"));
//			product.setProductDescription(rs.getString("Description"));
//			product.setProductImageURL(rs.getString("URL"));
//			product.setProductPrice(rs.getDouble("Price"));
//			product.setProductStockQuantity(rs.getInt("Quantity"));
//			//product get Categorys
//			
//		}
		
		return products;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==shoppingCart)
		{
			//jump to shopping cart page
		}
		else if(e.getSource()==userProfile)
		{
			dispose();
			UserProfile frame = new UserProfile();
			frame.setVisible(true);
		}
		
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
		if(e.getSource()==shoppingCart||e.getSource()==userProfile)
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==shoppingCart||e.getSource()==userProfile)
		{
			setCursor(Cursor.getDefaultCursor());
		}

	}
}
