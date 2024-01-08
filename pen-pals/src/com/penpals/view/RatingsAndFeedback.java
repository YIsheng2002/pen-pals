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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.penpals.db.MyDatabase;
import com.penpals.model.CartItem;
import com.penpals.model.Order;


public class RatingsAndFeedback extends JFrame  implements ActionListener, MouseListener{

	private static final long serialVersionUID = 1L;
	
	
	private JScrollPane scrollableBrowseArea;
	//center panel
	private JPanel contentPane;
		private JPanel commentPanels;
			private JPanel commentPanel;
				private  JPanel productPanel;
					private JLabel imageLabel;
						private ImageIcon productImage;
					private JLabel nameLabel;
					private JPanel qtyPricePanel;
						private JLabel quantityLabel;
						private JLabel priceLabel;
						
				private JPanel ratingPanel;
					private JLabel ratingLabel;
					private JTextField ratingTextField ;
					private JLabel totalRatingLabel;
					
				private JPanel feedbackPanel;
					private JLabel feedbackLabel;
					private JTextField feedbackTextField;
		
		private  List<CartItem> cartItems;
		private Order order;
	
	//North label
	private JLabel label;
	
	//South Button
	private JButton submitButton;

	/**
	 * Create the frame.
	 */
	//public RatingsAndFeedback(Order order) {
	public RatingsAndFeedback(Order order) {

		this.order = order;
		initializeComponents(order);
	}
	
	//private void initializeComponents(Order orders)
	private void initializeComponents(Order order)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900,700);
			//set frame min size
	    setMinimumSize(new Dimension(900,700));

			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 10, 5, 10));
			contentPane.setLayout(new BorderLayout(0, 0));
		     
			scrollableBrowseArea = new JScrollPane(contentPane);
		    scrollableBrowseArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				//loop 
			 commentPanels = new JPanel(new GridLayout(0,1,0,0));
			 cartItems = order.getOrderCartItems();
			 for(CartItem cartItem: cartItems)
				{
				 		//comment panel for each Product
						commentPanel = new JPanel(new BorderLayout(0,5));
						
							productPanel = new JPanel(new BorderLayout(5,0));
								//image JLabel
								imageLabel = new JLabel("");
						        	productImage = createResizedIcon(cartItem.getCartItemProduct().getProductImageURL(), 100,100); 
						        imageLabel.setIcon(productImage);
						        imageLabel.setVerticalAlignment(JLabel.CENTER);
						        
					        productPanel.add(imageLabel,BorderLayout.LINE_START);
						        
						        //product name JLabel
						        nameLabel = new JLabel();
						        nameLabel.setText(cartItem.getCartItemProduct().getProductName());
						        nameLabel.setVerticalAlignment(JLabel.CENTER);
						    productPanel.add(nameLabel,BorderLayout.CENTER);
					   
						    
							    qtyPricePanel = new JPanel(new BorderLayout());
							    
									//quantity JLabel
							        quantityLabel = new JLabel();
								        int quantity = cartItem.getCartItemQuantity();
								        String quantityString = String.valueOf(quantity);
								        quantityString = " x " + quantityString;
							        quantityLabel.setText(quantityString);
					        
							        //price label
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
				     		
						     productPanel.add(qtyPricePanel,BorderLayout.LINE_END);
						     productPanel.setPreferredSize(new Dimension(200,200));
				   
	
				     
					     	//Ratings panel
					     	ratingPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
					     		
					     		//Ratings label
					     		ratingLabel = new JLabel();
					     		ratingLabel.setText("Ratings :");
					     		
					     		//Ratings text field
					     		ratingTextField = new JTextField();
					     		ratingTextField.setColumns(1);
					     
					     		
					     		//Total Ratings label
					     		totalRatingLabel = new JLabel();
					     		totalRatingLabel.setText(" / 5");
					     		
					     	ratingPanel.add(ratingLabel);
					     	ratingPanel.add(ratingTextField);
					     	ratingPanel.add(totalRatingLabel);	
					     
					     	//Feedback panel
					     	feedbackPanel = new JPanel(new BorderLayout(0,0));
					     	
					     		//Feedback label
						     	feedbackLabel = new JLabel();
						     	feedbackLabel.setText("Feedback (Maximum 100 characters) :");
					     		
					     		//Ratings text field
					     		 feedbackTextField = new JTextField();
					     		feedbackTextField.setColumns(70);
					     		feedbackTextField.addActionListener(this);
					     		
				     		feedbackPanel.add(feedbackLabel,BorderLayout.NORTH);
				     		feedbackPanel.add(feedbackTextField,BorderLayout.CENTER);
			     	
			     		commentPanel.add(productPanel,BorderLayout.NORTH);
			     		commentPanel.add(ratingPanel,BorderLayout.CENTER);
			     		commentPanel.add(feedbackPanel,BorderLayout.SOUTH);
			     		commentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				     	
			     	commentPanels.add(commentPanel);
				     
				}	 
			
			    contentPane.add(commentPanels,BorderLayout.CENTER);
		    getContentPane().add(scrollableBrowseArea, BorderLayout.CENTER);  
		    
			//title label
			label = new JLabel();
			label.setText("		Ratings and Feedbacks");
		getContentPane().add(label,BorderLayout.NORTH);
		
			//submit button
			submitButton = new JButton();
			submitButton.setText("Submit");
			submitButton.addActionListener(this);
			submitButton.addMouseListener(this);
		getContentPane().add(submitButton,BorderLayout.SOUTH);
		
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
		if(e.getSource()==submitButton)
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));	
		}	
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==submitButton)
		{
			setCursor(Cursor.getDefaultCursor());
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==submitButton)
		{
			submit();
		}
	}
	
	public void submit() {
	    for (int i = 0; i < commentPanels.getComponentCount(); i++) {
	        Component component = commentPanels.getComponent(i);
	        if (component instanceof JPanel) {
	            JPanel commentPanel = (JPanel) component;

	            // Retrieve ratingTextField and feedbackTextField for each product
	            JTextField ratingTextField = getRatingTextField(commentPanel);
	            JTextField feedbackTextField = getFeedbackTextField(commentPanel);

	            // Validate feedback
	            if (feedbackTextField.getText().length() > 100) {
	                JOptionPane.showMessageDialog(null, "Maximum input for feedback is 100 characters");
	                return; // Exit the method if validation fails
	            }

	            // Validate rating
	            try {
	                int rating = Integer.parseInt(ratingTextField.getText());
	                if (rating < 1 || rating > 5) {
	                    JOptionPane.showMessageDialog(null, "Please enter an integer (1-5) for ratings");
	                    return; // Exit the method if validation fails
	                }
	            } catch (NumberFormatException nfe) {
	                JOptionPane.showMessageDialog(null, "Please enter an integer (1-5) for ratings");
	                return; // Exit the method if validation fails
	            }
	        }
	    }

	    // If all validations pass, insert into database
	    try {
            Connection connection = null;
			
            try {
				connection = MyDatabase.doConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            int j = 0;
            for (int i = 0; i < commentPanels.getComponentCount(); i++) {
                Component component = commentPanels.getComponent(i);
                if (component instanceof JPanel) {
                    JPanel commentPanel = (JPanel) component;

                    // Retrieve ratingTextField and feedbackTextField for each product
                    JTextField ratingTextField = getRatingTextField(commentPanel);
                    JTextField feedbackTextField = getFeedbackTextField(commentPanel);
                    
                    LocalDate currentDate = java.time.LocalDate.now();
                    String date = currentDate.toString();
                    
                    // Validate feedback and rating (same as before)

                    // If validation passes, add SQL statement to batch
                    //write in controller
//                    String sql = "INSERT INTO feedback (review,reviewDate,rating, ProductID) VALUES (?, ?,?,?)";
//                    try (PreparedStatement ps = connection.prepareStatement(sql)) {
//                        
//                        ps.setString(1, feedbackTextField.getText());
//                        ps.setString(2,date);
//                        ps.setInt(3, Integer.parseInt(ratingTextField.getText()));
//                        ps.setInt(4,cartItems.get(j).getCartItemProduct().getProductId());
//                        ps.executeUpdate();
//                        j++;
//                    } catch (SQLException e) {
//                        e.printStackTrace(); // Handle the exception as needed
//                    }
                }
            }
            connection.close();
            JOptionPane.showMessageDialog(null, "Ratings and feedbacks are submitted.");
            
            dispose();
            OrderTracking frame = new OrderTracking(order);
            frame.setVisible(true);
            
            
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception as needed
        }
	    

	}

	private JTextField getRatingTextField(JPanel commentPanel) {
	    Component[] components = commentPanel.getComponents();
	    for (Component component : components) {
	        if (component instanceof JPanel) {
	            JPanel ratingPanel = (JPanel) component;
	            Component[] ratingComponents = ratingPanel.getComponents();
	            for (Component ratingComponent : ratingComponents) {
	                if (ratingComponent instanceof JTextField) {
	                    return (JTextField) ratingComponent;
	                }
	            }
	        }
	    }
	    return null; // Return null if not found
	}

	private JTextField getFeedbackTextField(JPanel commentPanel) {
	    int textFieldCount = 0;
	    Component[] components = commentPanel.getComponents();
	    for (Component component : components) {
	        if (component instanceof JPanel) {
	            JPanel feedbackPanel = (JPanel) component;
	            Component[] feedbackComponents = feedbackPanel.getComponents();
	            for (Component feedbackComponent : feedbackComponents) {
	                if (feedbackComponent instanceof JTextField) {
	                    textFieldCount++;
	                    if (textFieldCount == 2) {
	                        return (JTextField) feedbackComponent;
	                    }
	                }
	            }
	        }
	    }
	    return null; // Return null if not found
	}


}

