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

import com.penpals.model.Coupon;
import com.penpals.model.Customer;
import com.penpals.model.Order;
import com.penpals.model.Product;
import com.penpals.model.ProductCategory;

import java.util.Arrays;

public class CouponGui extends JFrame implements  MouseListener, ActionListener{

	private static final long serialVersionUID = 1L;

	//north
	private JPanel northPanel;
		private JPanel northLeftPanel;
			private JButton backButton;
				private ImageIcon backIcon;
		private JPanel northCenterPanel;
			private JLabel couponsLabel;
	
	
	//center
	private JScrollPane scrollableBrowseArea;
	
	private JPanel couponsPanel;
		private JPanel couponPanel;
			//east
			 private JLabel couponCodeLabel;
		
			//center
			private JPanel couponDetailPanel;
				private JLabel minSpentLabel;
			
			//west
			private JPanel qtyPanel;
			
				private JLabel qtyLabel;
				
//				private JLabel priceLabel;
			
	//south 
			
	private Customer cus;
private JFrame callingFrame;
	/**
	 * Create the frame.
	 */
	public CouponGui(Customer cus,JFrame callingFrame) {
		this.cus = cus;
		this.callingFrame = callingFrame;
		init(cus);
		
	}
	
	public void init(Customer cus) {
		Coupon coupon1 = new Coupon(1, 10, "10% off", "10OFF", "2021-12-31", 100);
		Coupon coupon2 = new Coupon(2, 20, "20% off", "20OFF", "2021-12-31", 200);
		Coupon coupon3 = new Coupon(3, 30, "30% off", "30OFF", "2021-12-31", 300);
		Coupon [] couponList = {coupon1, coupon2, coupon3};
		List<Coupon> coupons = Arrays.asList(couponList);

		System.out.println("CouponGui: init: coupons: " + coupons);
		//cartItems = loadCartItems();
		//cartItems = cus.getCustomerShoppingCart().getShoppingCartItems();
		setBounds(100, 100, 500, 300);
		setTitle("Penpals Gift Shop - Coupon");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(500,300));
		
			//north Panel
			northPanel = new JPanel(new BorderLayout());
			
				northLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
						backIcon = createResizedIcon("/resources/uiSymbol/back.png",20,20);
					backButton = new JButton(backIcon);
					backButton.addMouseListener(this);
					backButton.addActionListener(this);
				northLeftPanel.add(backButton);
					
				northCenterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
					couponsLabel = new JLabel("Coupons");
					couponsLabel.setHorizontalAlignment(JLabel.LEFT);
					couponsLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
				northCenterPanel.add(couponsLabel);
				
				
				/**northRightPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
					manageButton = new JButton("Manage Cart");
					manageButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
					manageButton.addActionListener(this);
					manageButton.addMouseListener(this);
				northRightPanel.add(manageButton);**/
			
				
			northPanel.add(northLeftPanel,BorderLayout.LINE_START);
			northPanel.add(northCenterPanel,BorderLayout.CENTER);
			//northPanel.add(northRightPanel,BorderLayout.LINE_END);
		add(northPanel,BorderLayout.PAGE_START);
		
		
		
			couponsPanel = new JPanel(new GridLayout(0,1,0,0));
			
		 
			for(Coupon coupon : coupons) 
			{
				couponPanel = createCouponPanel(coupon);
				couponsPanel.add(couponPanel);
			}
			
			
			 scrollableBrowseArea = new JScrollPane(couponsPanel);
		     scrollableBrowseArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		       
	       
	     add(scrollableBrowseArea,BorderLayout.CENTER); 
	       
	     
		setVisible(true);
		//pack();
	}
	
	private JPanel createCouponPanel(Coupon coupon) {
		
		couponPanel = new JPanel(new BorderLayout(5,0));
			
			//center
			couponDetailPanel = new JPanel(new BorderLayout(5,0));
		      	//product name JLabel
		        minSpentLabel = new JLabel();
		        minSpentLabel.setText("<html>Min Spent : RM "
				 + coupon.getCouponMinSpent()+ 
				 "<br> Expiration Date : "
				 + coupon.getCouponExpirationDate() + "</html>");
//		        nameLabel.setText("Product");
		        minSpentLabel.setVerticalAlignment(JLabel.CENTER);
		        
		    couponDetailPanel.add(minSpentLabel,BorderLayout.CENTER);
			couponDetailPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		couponPanel.add(couponDetailPanel,BorderLayout.CENTER);
		    
			//east
			
				int qty = 2;
				qtyLabel = new JLabel(String.valueOf(qty));
				qtyLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
				qtyLabel.setHorizontalAlignment(JLabel.CENTER);
				qtyLabel.setVerticalAlignment(JLabel.CENTER);
				qtyLabel.setBorder(new EmptyBorder(5, 5, 5, 60));
				
				couponCodeLabel = new JLabel();
				couponCodeLabel.setText(coupon.getCouponCode());
				couponCodeLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
				couponCodeLabel.setVerticalAlignment(JLabel.CENTER);
				couponCodeLabel.setBorder(new EmptyBorder(5, 5, 5, 5));

			couponPanel.add(couponCodeLabel,BorderLayout.LINE_START);
			
		
		couponPanel.add(qtyLabel,BorderLayout.LINE_END);
		couponPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		couponPanel.setPreferredSize(new Dimension(300,80));
		setVisible(true);

		return couponPanel;
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
		
		if(e.getSource()==backButton)
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==backButton)
		{
			setCursor(Cursor.getDefaultCursor());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==backButton)
		{
			dispose();
			callingFrame.setVisible(true);
		}
	}
}

