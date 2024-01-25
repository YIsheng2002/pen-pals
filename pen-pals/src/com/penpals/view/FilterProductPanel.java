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

import com.penpals.controller.*;

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
				

		
		private Integer[] ratingsArray = {0,1,2,3,4,5};
		private String[] categoriesArray;
		
		private int ratings ;
		private String category = "";
		private String minPriceString="";
		private String maxPriceString="";
		private double minPrice ;
		private double maxPrice ;
		private boolean isPromote;
		
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
		List<ProductCategory> categories = new ProductCategoryController().getAllProductCategory();
		for(ProductCategory category : categories)
		{
			categoriesList.add(category.getProductCategoryName());
		}
		
		categoriesArray = categoriesList.toArray(new String[categoriesList.size()]);
		return categoriesArray;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==categoryBox)
		{
			category = (String) categoryBox.getSelectedItem();
		}
		else if(e.getSource()==ratingsBox)
		{
			ratings = (int) ratingsBox.getSelectedItem();
		}
		else if(e.getSource()==applyButton)
		{
			category = (String) categoryBox.getSelectedItem();
			ratings = (int) ratingsBox.getSelectedItem();
			isPromote = checkBox.isSelected();
			
			if(!maxPriceField.getText().equals("Max Price")&&!minPriceField.getText().equals("Min Price")&&!maxPriceField.getText().isEmpty()&&!minPriceField.getText().isEmpty())
			{
				maxPriceString = maxPriceField.getText();
				minPriceString = minPriceField.getText();
				System.out.println(maxPriceString);
				
				try {
					maxPrice = Double.parseDouble(maxPriceString);
					minPrice = Double.parseDouble(minPriceString);
					if(maxPrice < minPrice)
					{
						JOptionPane.showMessageDialog(null, "Maximum Price cannot be less than minimun price","Price Range",JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						filterPrice();
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
			// reset all filter field
			categoryBox.setSelectedIndex(0);
			ratingsBox.setSelectedIndex(0);
			minPriceField.setText("Min Price");
			minPriceField.setForeground(new Color(146, 146, 146));
			maxPriceField.setText("Max Price");
			maxPriceField.setForeground(new Color(146, 146, 146));
			checkBox.setSelected(false);
			List<Product> products = new ArrayList<>();
			// LOAD ALL PRODUCT
			products = new ProductController().getAllProduct();
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
		if (category.equals("All") && !isPromote)
		{
			List<Product> products = new ArrayList<>();
			products = new ProductController().getAllProductbyRating(ratings);
			frame.setProducts(products);
			frame.repaintProductPanel(frame.getProducts());
		}  else if (category.equals("All") && isPromote)
		{
			List<Product> products = new ArrayList<>();
			products = new ProductController().getAllPromotionProduct(ratings);
			frame.setProducts(products);
			frame.repaintProductPanel(frame.getProducts());
		} else if (!category.equals("All") && !isPromote)
		{
			List<Product> products = new ArrayList<>();
			products = new ProductController().getAllProductByCategory(category,ratings);
			frame.setProducts(products);
			frame.repaintProductPanel(frame.getProducts());
		} else if (!category.equals("All") && isPromote)
		{
			List<Product> products = new ArrayList<>();
			products = new ProductController().getAllPromotionProductByCategory(category, ratings);
			frame.setProducts(products);
			frame.repaintProductPanel(frame.getProducts());
		}  else {
			System.out.println("Error");
			System.out.println("More error");
		}
	}
	
	public void filterPrice(){
		//filter in database
		if (category.equals("All") && !isPromote)
		{
			List<Product> products = new ArrayList<>();
			products = new ProductController().getAllProductbyRatingAndPrice(ratings, minPriceString, maxPriceString);
			frame.setProducts(products);
			frame.repaintProductPanel(frame.getProducts());
		}  else if (category.equals("All") && isPromote)
		{
			List<Product> products = new ArrayList<>();
			products = new ProductController().getAllPromotionProductbyPrice(ratings, minPriceString, maxPriceString);
			frame.setProducts(products);
			frame.repaintProductPanel(frame.getProducts());
		} else if (!category.equals("All") && !isPromote)
		{
			List<Product> products = new ArrayList<>();
			products = new ProductController().getAllProductByCategoryAndPrice(category,ratings, minPriceString, maxPriceString);
			frame.setProducts(products);
			frame.repaintProductPanel(frame.getProducts());
		} else if (!category.equals("All") && isPromote)
		{
			List<Product> products = new ArrayList<>();
			products = new ProductController().getAllPromotionProductByCategoryAndPrice(category, ratings, minPriceString, maxPriceString);
			frame.setProducts(products);
			frame.repaintProductPanel(frame.getProducts());
		}  else {
			System.out.println("Error");
		}
	}

}

	