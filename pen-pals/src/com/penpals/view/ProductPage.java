package com.penpals.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.penpals.model.Product;

public class ProductPage extends JFrame implements MouseListener, ActionListener{

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollableBrowseArea;
	private JPanel contentPane;
		private JLabel imageLabel;
			private ImageIcon resizedIcon;
			
		private	JPanel centerContainer;
			private JPanel centerPanel;
				private JPanel pricePanel;
					private JLabel productPriceLabel;
					private JLabel discountLabel;
			
				private JLabel productNameLabel;
				private JLabel descriptionLabel;
				private JLabel productDescriptionLabel;
				private JLabel stockLabel;
				
			private JLabel feedbackString;
			private JPanel feedbackPanel;
				private JLabel feedbackLabel;
				private JPanel ratingPanel;
					private JLabel ratingsLabel;
					private JLabel starLabel;
						private ImageIcon starIcon;
		
	private JPanel northPanel;
		private JPanel northLeftPanel;
			private JButton backButton;
				private ImageIcon backIcon;
			
		private JPanel northRightPanel;
			private JButton cartButton;
				private ImageIcon cartIcon;
				
	private JPanel southPanel;
		private JButton addToCartButton;
		private JButton buyNowButton;
	/**
	 * Create the frame.
	 */
	
	public ProductPage(Product product) { 
		
		init(product);
		 
	}
	
	private void init(Product product) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 1000, 900, 700);
		setMinimumSize(new Dimension(900,700));
		setTitle("Penpals Gift Shop");
		
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
		
			//Image Label
	        //resizedIcon = createResizedIcon(product.getImagePath(), 700, 500); //uncomment it later
				resizedIcon = createResizedIcon("/resources/productImage/Key Chain.jpg", 700, 500); //delete it later 
	        imageLabel = new JLabel(resizedIcon);
	        imageLabel.setHorizontalAlignment(JLabel.CENTER);
	        imageLabel.setVerticalAlignment(JLabel.CENTER);
	        imageLabel.setPreferredSize(new Dimension(700,550));
	
	        
	        centerPanel = new JPanel();
	        centerPanel.setBackground(new Color(235, 217, 209));
	        
	        BoxLayout boxlayout = new BoxLayout(centerPanel, BoxLayout.Y_AXIS); 
	        // to set the box layout 
	        centerPanel.setLayout(boxlayout); 
	        centerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	        
		        //Price Panel
		        pricePanel = new JPanel(new FlowLayout(FlowLayout.LEADING,5,0));
		        pricePanel.setBackground(new Color(235, 217, 209));
		        
			        //double price = product.getPrice(); //uncomment it later
			        double price = 123.479; 					//delete it later
			        
			        // Format the number to 2 decimal price
			        DecimalFormat df = new DecimalFormat("#.00");
			        String priceString = df.format(price);
			        priceString = "RM " + priceString;
			        
			        productPriceLabel = new JLabel(priceString);
			        productPriceLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
			        productPriceLabel.setHorizontalAlignment(JLabel.LEADING);
			        
		        pricePanel.add(productPriceLabel);
	
	        
		        //add Discount Percentage beside price (if any) 
		//        if(has discount)
		//        {
				            double discount = 9.80; //delete it later, retrieve discount from database
				            String discountString = String.valueOf(discount);
				            discountString = " - " + discountString + "%";
			            discountLabel = new JLabel(discountString);
			            discountLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
			            discountLabel.setVerticalAlignment(JLabel.CENTER);
		            pricePanel.add(discountLabel);        	
		//        }
		
		        pricePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		        
	
			        //Name label
			        String name = product.getProductName(); 
		        productNameLabel = new JLabel(name);
		        productNameLabel.setFont(new Font("Arial", Font.PLAIN, 21));
	        
	        
		        //Description label
		        descriptionLabel = new JLabel("Description:");
		        descriptionLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
	        
	        
		        //String label
		       // String description = product.getDescription();  //uncommment it later
		        String description = "This is description blahhhhhhhhhhhhhhhhh"; //delete it later
		        productDescriptionLabel = new JLabel(description);
		        productDescriptionLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
	        
	
		        //add stock number
		        //int stock = product.getStock(); //uncomment it later
		        int stock = 100;//delete it later
		        String stockString =  String.valueOf(stock);
		        stockString = "Stock: " + stockString;
		        stockLabel = new JLabel(stockString);
		        stockLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        
	        centerPanel.add(pricePanel);
	        centerPanel.add(productNameLabel);
	        centerPanel.add(descriptionLabel);
	        centerPanel.add(productDescriptionLabel);
	        centerPanel.add(stockLabel);
               
        centerContainer = new JPanel();
        BoxLayout centerBox = new BoxLayout(centerContainer, BoxLayout.Y_AXIS); 
        // to set the box layout 
        centerContainer.setLayout(centerBox);

	        //to separate the comments with upper elements
	        JPanel emptySpace = new JPanel();
	        
	        //add comment string to indicate ratings and reviews section
	        feedbackString = new JLabel("Comments :");
	        feedbackString.setVerticalAlignment(JLabel.BOTTOM);
	        feedbackString.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
        
        
        centerContainer.add(centerPanel);
        centerContainer.add(emptySpace);
        centerContainer.add(feedbackString);
        
        

	        //display feedback
	       //retrieve feedback data form database
	        //dummy feedback data
	        ArrayList<String> feedbacks = new ArrayList<>();
	        feedbacks.add("Good");
	        feedbacks.add("Nice");
	        feedbacks.add("Bad");
	        feedbacks.add("Excellent");
	        feedbacks.add("Rubbish");
	        
	        // For Each Loop for iterating ArrayList
	        for (String feedback : feedbacks)
	        {
	        	feedbackPanel = new JPanel(new GridLayout(0,1,0,0));
	        	
		        	feedbackLabel = new JLabel(feedback);
		        	feedbackLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		        	
		        	ratingPanel = new JPanel(new FlowLayout(FlowLayout.LEADING,0,0));
		        	
		            //int ratings = feedback.getRatings(); //uncomment it later
			            int ratings = 3; //delete it later
			            ratingsLabel = new JLabel("Ratings: ");
			            ratingsLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 17 ));
		            ratingPanel.add(ratingsLabel);
		            
		            //display star based on ratings 
		            for(int i = 1 ; i <= ratings ; i++)
		            {
			            	starLabel = new JLabel();
			            		starIcon = createResizedIcon("/resources/uiSymbol/ratingStar.png",18,12);
			            	starLabel.setIcon(starIcon);
		            	ratingPanel.add(starLabel);
		            }
	            
	            
	            feedbackPanel.add(ratingPanel);
	            feedbackPanel.add(feedbackLabel,BorderLayout.CENTER);
	            feedbackLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	            feedbackPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	            feedbackPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	            
            centerContainer.add(feedbackPanel);
	      
	        }
	        centerContainer.setVisible(true);
        
	        //north Panel
	        northPanel = new JPanel(new BorderLayout());
	        
		        //add back button
		        northLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
			        backButton = new JButton();
			        	backIcon = createResizedIcon("/resources/uiSymbol/back.png",20,20);
			        backButton.setIcon(backIcon);
			        backButton.addMouseListener(this);
			        backButton.addActionListener(this);
		        northLeftPanel.add(backButton);
	        northPanel.add(northLeftPanel,BorderLayout.LINE_START);
	       
	        
	        //add cart button
	        northRightPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
	        
		        cartButton = new JButton();
		        	cartIcon = createResizedIcon("/resources/uiSymbol/Cart.jpg",20,20);
		        cartButton.setIcon(cartIcon);
		        cartButton.addMouseListener(this);
		        cartButton.addActionListener(this);
		        
	        northRightPanel.add(cartButton);
	        northPanel.add(northRightPanel,BorderLayout.LINE_END);
	        
        getContentPane().add(northPanel,BorderLayout.NORTH);
        
	        //southpanel that contains add to cart button and buy now button
	        southPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING,0,0));
	        
		        addToCartButton = new JButton();
		        addToCartButton.setText("Add To Cart");
		        addToCartButton.addMouseListener(this);
		        addToCartButton.addActionListener(this);
		        
		        buyNowButton = new JButton();
		        buyNowButton.setText("Buy Now");
		        buyNowButton.addMouseListener(this);
	        
	        southPanel.add(addToCartButton);
	        southPanel.add(buyNowButton);
	        
        getContentPane().add(southPanel,BorderLayout.SOUTH);
        
	        scrollableBrowseArea = new JScrollPane(contentPane);
	        scrollableBrowseArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	        scrollableBrowseArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        
	        contentPane.add(imageLabel,BorderLayout.NORTH);
	        contentPane.add(centerContainer,BorderLayout.CENTER);
	        
        getContentPane().add(scrollableBrowseArea, BorderLayout.CENTER);  
        pack();
        setVisible(true);
    }

	private ImageIcon createResizedIcon(String imagePath, int width, int height) {
	    
	    ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));

	    Image originalImage = originalIcon.getImage();
	    Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	    return new ImageIcon(resizedImage);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==addToCartButton)
		{
			//add to cart
			JOptionPane.showMessageDialog(null, "Product is added to cart successfully.","Add To Cart", JOptionPane.INFORMATION_MESSAGE);
		}
		else if(e.getSource()==buyNowButton)
		{
			//jump to buy now page
		}
	
		else if(e.getSource()==backButton)
		{
			dispose();
			BrowseProduct frame = new BrowseProduct();
			frame.setVisible(true);
		}
		else if(e.getSource()==cartButton)
		{
			//jump to cart
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
		if(e.getSource()==addToCartButton||e.getSource()==buyNowButton||e.getSource()==backButton||e.getSource()==cartButton)
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
	
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==addToCartButton||e.getSource()==buyNowButton||e.getSource()==backButton||e.getSource()==cartButton)
		{
			setCursor(Cursor.getDefaultCursor());
		}

	}
}
