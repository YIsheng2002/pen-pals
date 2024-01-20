package com.penpals.view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.penpals.model.Product;
import com.penpals.model.ProductCategory;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Cursor;

public class FilterProductPanel extends JPanel implements MouseListener, ActionListener{
	

	private static final long serialVersionUID = 1L;
	
	private JPanel northPanel;
		 private JLabel titleLabel;
			 
		private JPanel categoryPanel;
			private JLabel categoryLabel;
			private JComboBox categoryBox;
			
		private JPanel priceRangePanel;
			private JLabel priceRangeLabel;
		
		private JPanel minMaxPanel;
			private JTextField minPriceField;
			private JLabel toSymbolLabel;
			private JTextField maxPriceField;
			
		private JPanel southPanel;
			private JPanel ratingPanel;
			private JLabel ratingsLabel;
				private JComboBox ratingsBox;
				
			private JCheckBox checkBox;
				
			private JPanel buttonPanel;
				private JButton resetButton;
				private JButton applyButton;
				

		
		private Integer[] ratingsArray = {1,2,3,4,5};
		private String[] categoriesArray;
		
		private int ratings ;
		private String category = "";
		private String minPriceString="";
		private String maxPriceString="";
		private double minPrice ;
		private double maxPrice ;
		
		private BrowseProductGui frame;
		

	/**
	 * Create the panel.
	 */
	public FilterProductPanel(BrowseProductGui frame) {
		this.frame = frame;
		init();
	}

	private void init()
	{
		
   		setVisible(true);
   		setPreferredSize(new Dimension(450, 400));
   		setLayout(new BorderLayout());
   		setBounds(200, 1000, 450, 400);
   		
	   		 northPanel = new JPanel(new BorderLayout());
		   		  titleLabel = new JLabel("Filter");
		   		  titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		   		  titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
	   		northPanel.add(titleLabel,BorderLayout.PAGE_START);
	   		
		   		
	   		categoriesArray = loadCategoriesName();
	   		
	   			categoryPanel = new JPanel(new BorderLayout());
	   				categoryLabel = new JLabel("Category");
	   				categoryLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
	   				
   				categoryPanel.add(categoryLabel,BorderLayout.CENTER);
	   				
	   				categoryBox = new JComboBox(categoriesArray);
   				categoryPanel.add(categoryBox,BorderLayout.PAGE_END);
			northPanel.add(categoryPanel,BorderLayout.PAGE_END);
	   			
		add(northPanel,BorderLayout.PAGE_START);
				priceRangePanel = new JPanel(new BorderLayout());
				priceRangePanel.setBorder(new EmptyBorder(50, 5, 5, 5));
   				//north 
   				 priceRangeLabel = new JLabel("Price Range (RM)");
   				 priceRangeLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
   				//center (flowlayout)
   				 minMaxPanel =new JPanel(new FlowLayout(FlowLayout.CENTER));

   					minPriceField = new JTextField("Min Price");
   					minPriceField.addMouseListener(this);
   					minPriceField.setForeground(new Color(146, 146, 146));
   					minPriceField.setPreferredSize(new Dimension(70,30));

   					toSymbolLabel = new JLabel(" - ");
   					maxPriceField = new JTextField("Max Price");
   					maxPriceField.addMouseListener(this);
   					maxPriceField.setForeground(new Color(146, 146, 146));
   					maxPriceField.setPreferredSize(new Dimension(70,30));

					minMaxPanel.add(minPriceField);
					minMaxPanel.add(toSymbolLabel);
					minMaxPanel.add(maxPriceField);
					
				priceRangePanel.add(priceRangeLabel,BorderLayout.NORTH);
				priceRangePanel.add(minMaxPanel,BorderLayout.CENTER);
				
		add(priceRangePanel,BorderLayout.CENTER);
   			
   					
   			southPanel = new JPanel(new BorderLayout());
   				JPanel ratingPanel = new JPanel(new BorderLayout());
   				
	   				ratingsLabel = new JLabel("Ratings Above");
	   				ratingsLabel.setBorder(new EmptyBorder(0, 5, 0, 5));
   				ratingPanel.add(ratingsLabel,BorderLayout.PAGE_START);
	   				
				
	   				ratingsBox = new JComboBox(ratingsArray);
	   				ratingsBox.setBorder(new EmptyBorder(5, 35, 20, 35));
	   				ratingsBox.addActionListener(this);
   				ratingPanel.add(ratingsBox,BorderLayout.CENTER);

			southPanel.add(ratingPanel,BorderLayout.PAGE_START);
			
			
				checkBox = new JCheckBox("In promotion");
				checkBox.addMouseListener(this);
				checkBox.addActionListener(this);
				checkBox.setBorder(new EmptyBorder(5, 35, 20, 35));
				
			southPanel.add(checkBox,BorderLayout.CENTER);
			
				buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
				
					resetButton = new JButton("Reset");
					resetButton.addActionListener(this);
					resetButton.addMouseListener(this);
					
					applyButton = new JButton("Apply");
					applyButton.addActionListener(this);
					applyButton.addMouseListener(this);
					
				buttonPanel.add(applyButton);
				buttonPanel.add(resetButton);
					
					
			southPanel.add(buttonPanel,BorderLayout.PAGE_END);
				
			add(southPanel,BorderLayout.PAGE_END);
		setOpaque(true);
	}

	 
	public String[] loadCategoriesName()
	{
		List<String> categoriesList = new ArrayList<>();
		categoriesList.add("All");
		categoriesList.add("category1");
		categoriesList.add("category2");
		categoriesList.add("category3");
		categoriesList.add("category4");
		categoriesList.add("category5");
		categoriesList.add("category6");
		categoriesArray = categoriesList.toArray(new String[categoriesList.size()]);
		return categoriesArray;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==categoryBox)
		{
			category = (String) categoryBox.getSelectedItem();
			System.out.println(category);
		}
		else if(e.getSource()==ratingsBox)
		{
			ratings = (int) ratingsBox.getSelectedItem();
			System.out.println(ratings);
		}
		else if(e.getSource()==applyButton)
		{
			category = (String) categoryBox.getSelectedItem();
			ratings = (int) ratingsBox.getSelectedItem();
			
			if(!maxPriceField.getText().equals("Max Price")&&!minPriceField.getText().equals("Min Price")&&!maxPriceField.getText().isEmpty()&&!minPriceField.getText().isEmpty())
			{
				maxPriceString = maxPriceField.getText();
				minPriceString = minPriceField.getText();
				
				try {
					maxPrice = Double.parseDouble(maxPriceString);
					minPrice = Double.parseDouble(minPriceString);
					if(maxPrice < minPrice)
					{
						JOptionPane.showMessageDialog(null, "Invalid input for price range","Price Range",JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						filter();
					}
				}catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(null, "Invalid input for price range","Price Range",JOptionPane.ERROR_MESSAGE);
				}
				
			}
			else
			{
				//filter without price
				filter();
			}
			
		}
		else if(e.getSource()==resetButton)
		{
			
			List<Product> products = new ArrayList<>();
			products = frame.loadData();
			//product = loadAllProducts();
			frame.setProducts(products);
			frame.repaintProductPanel(frame.getProducts());
		}
		else if(e.getSource()==checkBox)
		{
			//filter by promotion 
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==maxPriceField)
		{
			maxPriceField.setText("");
			maxPriceField.setForeground(Color.black);
		}
		else if(e.getSource()==minPriceField)
		{
			minPriceField.setText("");
			minPriceField.setForeground(Color.black);
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
		if(e.getSource()==maxPriceField||e.getSource()==minPriceField||e.getSource()==applyButton||e.getSource()==resetButton||e.getSource()==checkBox)
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==maxPriceField||e.getSource()==minPriceField||e.getSource()==applyButton||e.getSource()==resetButton||e.getSource()==checkBox)
		{
			setCursor(Cursor.getDefaultCursor());
		}
	}
	
	public void filter()
	{
		//filter in database
		List<Product> products = new ArrayList<>();
		products = filterProduct();
		//product = loadAllProducts();
		frame.setProducts(products);
		frame.repaintProductPanel(frame.getProducts());
		
	}
	//dummy data
	public List<Product> filterProduct()
	{
		List<Product> products = new ArrayList<>();
		
		ProductCategory category = new ProductCategory(1, "Electronics");
		// Create a sample product
        Product product1 = new Product(1, "Producdwqeet 1", "Description 1", 19.99, 10, category, "/resources/productImage/Wooden Key Chain.jpg");
        Product product2 = new Product(2, "Produwqeqct 2", "Description 2", 29.99, 15, category, "/resources/productImage/White Bear.jpg");
        Product product3 = new Product(3, "Prodeqweuct 3", "Description 3", 39.99, 20, category, "/resources/productImage/Key Chain.jpg");

        Product product4 = new Product(1, "Prodqweqweuct 1", "Description 1", 19.99, 10, category, "/resources/productImage/White Bear.jpg");
        Product product5 = new Product(2, "Prodqweeuct 2", "Description 2", 29.99, 15, category, "/resources/productImage/Wooden Key Chain.jpg");
        Product product6 = new Product(3, "Produqweect 3", "Description 3", 39.39, 20, category, "/resources/productImage/White Bear.jpg");
        Product product7 = new Product(1, "Prodqweqeuct 1", "Description 1", 79.99, 10, category, "/resources/productImage/White Bear.jpg");
        Product product8 = new Product(2, "Produqwect 2", "Description 2", 212.99, 15, category, "/resources/productImage/White Bear.jpg");
        Product product9 = new Product(3, "Produqwect 3", "Description 3", 329.99, 20, category, "/resources/productImage/White Bear.jpg");

        Product product10 = new Product(3, "Prodqwesuct 3", "Description 3", 3359.99, 20, category, "/resources/productImage/White Bear.jpg");
        
        products.add(product3);
        products.add(product1);
        products.add(product2);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);
        products.add(product8);
        products.add(product9);
        products.add(product10);
        
        return products;
	}
	

}

	