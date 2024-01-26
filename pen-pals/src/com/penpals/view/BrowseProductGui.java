package com.penpals.view;
import com.penpals.model.Customer;
import com.penpals.model.Product;
import com.penpals.controller.ProductController;
import java.util.ArrayList;
import java.util.List;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

public class BrowseProductGui extends JFrame implements MouseListener, ActionListener, KeyListener{

	ProductController productController = new ProductController();
	private static final long serialVersionUID = 1L;
	private List<Product> products = productController.getAllProduct();
	
    private FilterProductPanel filterPanel;

	private JPanel contentPane;
	
		//north panel
		private JPanel northPanel;
		
			//west panel
			private JPanel northLeftPanel;
				private JLabel searchLabel;
				private ImageIcon searchIcon;
				private JTextField textField;
				private JButton filterButton;
		
			//east panel
			private JPanel northRightPanel;
				private JButton shoppingCart;
					private ImageIcon cartIcon;
				private JButton userProfile;
				private ImageIcon profileIcon;
			
		//center panel
		private JScrollPane scrollableBrowseArea;
			//grid layout panel
			private JPanel productPanel;
				private JPanel productPanelRow;
					private JPanel productPanelItem;
//					private JLabel productImageLabel;
//						private ImageIcon productImage;
//				private JLabel productNameLabel;
//				private JLabel productPriceLabel;
						

	private Customer cus;
	/**
	 * Create the frame.
	 */
	public BrowseProductGui(Customer cus) {
		this.cus = cus;
		init(cus);
	}
	
	private void init(Customer cus) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setLayout(new BorderLayout());
		//setBounds(200, 800, 900, 700);
		//set frame min size
        setMinimumSize(new Dimension(900,700));
        setTitle("Penpal Gift Shop");

        	contentPane = createContentPane();
   
            filterPanel = new FilterProductPanel(this);
            filterPanel.setBorder(new EmptyBorder(15, 5, 400, 5));

            filterPanel.setPreferredSize(new Dimension(200,700));

            filterPanel.setVisible(false);

		//main content panel
        getContentPane().add(contentPane, BorderLayout.CENTER);
        //side filter panel
		getContentPane().add(filterPanel, BorderLayout.LINE_START);

      
       pack();
       setVisible(true);
	}

	//contentPane
	private JPanel createContentPane()
	{
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
					textField.addKeyListener(this);
					
				northLeftPanel.add(textField);
				filterButton = new JButton("Filter");
				filterButton.addActionListener(e -> showFilterPanel());
				northLeftPanel.add(filterButton);
			northPanel.add(northLeftPanel,BorderLayout.WEST);
	
				//north right corner
				northRightPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 5, 0));
				northRightPanel.setOpaque(false);
		
					//shopping cart button
					shoppingCart = new JButton("");
					 	cartIcon = createResizedIcon("/resources/uiSymbol/Cart.jpg", 22, 22);
					shoppingCart.setIcon(cartIcon);
					shoppingCart.addMouseListener(this);
					shoppingCart.addActionListener(this);
		
					//user profile button
					userProfile = new JButton("");
					 	profileIcon = createResizedIcon("/resources/uiSymbol/user_profile.png", 22, 22);
					userProfile.setIcon(profileIcon);
					userProfile.addMouseListener(this);	
					userProfile.addActionListener(this);
	
		
				northRightPanel.add(shoppingCart);
				northRightPanel.add(userProfile);
	
			northPanel.add(northRightPanel,BorderLayout.EAST);
	
		contentPane.add(northPanel,BorderLayout.NORTH);
	
	
	
	   //JPanel for products
	    productPanel = new JPanel(new GridLayout(0, 1, 20, 50));
	
	   //load product list 
		for (int i = 0; i < products.size(); i++) {
			if (i % 3 == 0) {
				productPanelRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
				productPanelRow.setOpaque(false);
				productPanel.add(productPanelRow);
			}
			Product product = products.get(i);
			productPanelItem = createProductPanel(product);
			productPanelRow.add(productPanelItem);
		}
	
	   scrollableBrowseArea = new JScrollPane(productPanel);
	   scrollableBrowseArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	   scrollableBrowseArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	   
	   
	   contentPane.add(scrollableBrowseArea, BorderLayout.CENTER); 
	   contentPane.setOpaque(false);
	   return contentPane;
	}
	
	private void showFilterPanel() {
        // Toggle the visibility of the filter panel
        filterPanel.setVisible(!filterPanel.isVisible());
        revalidate();
        repaint();
    }
	
	
	private ImageIcon createResizedIcon(String imagePath, int width, int height) {
	    
	    ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));

	    Image originalImage = originalIcon.getImage();
	    Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	    return new ImageIcon(resizedImage);
	}


	private JPanel createProductPanel(Product product)
	{
		 // Create a panel for each product with a BorderLayout
        JPanel productPanelItem = new JPanel(new BorderLayout());
		productPanelItem.setPreferredSize(new Dimension(360,420));
		productPanelItem.setMaximumSize(new Dimension(360,420));
		productPanelItem.setMinimumSize(new Dimension(360,420));
        // Create a JLabel for the image
         	JLabel productImageLabel = new JLabel("");
         			ImageIcon productImage = createResizedIcon(product.getProductImageURL(), 300, 300);
	           productImageLabel.setIcon(productImage);
	           productImageLabel.setHorizontalAlignment(JLabel.CENTER);
	           productImageLabel.setVerticalAlignment(JLabel.BOTTOM);
	           productImageLabel.setPreferredSize(new Dimension(350,350));

	           // Create a JLabel for the name
	           JLabel productNameLabel = new JLabel(product.getProductName());
	           productNameLabel.setHorizontalAlignment(JLabel.CENTER);
	           productNameLabel.setVerticalAlignment(JLabel.BOTTOM);
	           productNameLabel.setPreferredSize(new Dimension(20,20));
	           productNameLabel.addMouseListener(new MouseAdapter() {
	        	   
     	    @Override
     	    public void mouseClicked(MouseEvent e) {
     	    	dispose();
     	        ProductPageGui frame = new ProductPageGui(BrowseProductGui.this ,cus,product);
     	        frame.setVisible(true);
     	    }

     	    @Override
     	    public void mouseEntered(MouseEvent e) {
     	        // Change the cursor to indicate it's clickable
     	    	productNameLabel.setForeground(new Color(57, 142, 255));
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
  			
				JLabel productPriceLabel = new JLabel();
				DecimalFormat df = new DecimalFormat("#.00");		       
			
			if (product.getProductHasPromotion()) {
				// Create a JLabel for the price
				double price = product.getProductPrice();
				double discountPercentage = productController.getProductPromotionPercentage(product.getProductId());
				double discountPrice = price * (100 - discountPercentage) / 100;
				// Convert double to String
				 String priceString = df.format(price);
				String discountPriceString = df.format(discountPrice);
			
				productPriceLabel.setText("<html><strike> RM " + priceString + "</strike> <br><b style = \"color:red;\"> NOW ONLY RM " + discountPriceString + " <></b></html>");
			productPriceLabel.setHorizontalAlignment(JLabel.CENTER);
			} else {
		           // Create a JLabel for the price
		           double price = product.getProductPrice();
		           // Convert double to String
		           String priceString = df.format(price);
					productPriceLabel.setText("RM " + priceString);
	           productPriceLabel.setHorizontalAlignment(JLabel.CENTER);
			}
			   


        // Add components to the productPanelItem
        productPanelItem.add(productImageLabel, BorderLayout.NORTH);
        productPanelItem.add(productNameLabel, BorderLayout.CENTER);
        productPanelItem.add(productPriceLabel, BorderLayout.SOUTH);

       return productPanelItem;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==shoppingCart)
		{
			dispose();
			CartGui frame = new CartGui(cus,this);
			frame.setVisible(true);
		}
		else if(e.getSource()==userProfile)
		{
			dispose();
			UserPageGui frame = new UserPageGui(cus);
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
	
	public void repaintProductPanel(List<Product> products)
	{
		productPanel.removeAll();
		//dummy data , has to call controller function
		
		for (int i = 0; i < products.size(); i++) {
			if (i % 3 == 0) {
				productPanelRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 50));
				productPanelRow.setOpaque(false);
				productPanel.add(productPanelRow);
			}
			Product product = products.get(i);
			productPanelItem = createProductPanel(product);
			productPanelRow.add(productPanelItem);
		}

		productPanel.revalidate();
		productPanel.repaint();
	}
	
	public void setProducts(List<Product> products)
	{
		this.products = products;
	}
	
	public List<Product> getProducts()
	{
		return products;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			if(textField.getText() != "")
			{
				// temperary product list
				List <Product> tempProductList = new ArrayList<>();
				String findKey = textField.getText();
				textField.setText("");
				for (Product product : products) {
					if(product.getProductName().toLowerCase().contains(findKey.toLowerCase()))
					{
						if (!tempProductList.contains(product)) {
							tempProductList.add(product);
						}
					}
				}
				if(tempProductList.isEmpty())
				{
					JOptionPane.showMessageDialog(this, "No product found");
				}
				else
				{
					repaintProductPanel(tempProductList);
				}
			}

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
