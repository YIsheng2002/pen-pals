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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.penpals.model.Customer;
import com.penpals.model.Order;

import com.penpals.controller.OrderController;
import java.awt.Font;

public class OrderHistoryGui extends JFrame  implements ActionListener, MouseListener{

	private static final long serialVersionUID = 1L;


	
	private JPanel northPanel;
		private JPanel northLeftPanel;
			private JButton backButton;
				private ImageIcon backIcon;
		private JPanel northCenterPanel;
			private JLabel orderHisLabel;
	
	private JPanel ordersPanel;
		private JPanel orderPanel;
			private JLabel orderIDLabel;
			private JLabel statusLabel;
			private JPanel southPanel;
				private JPanel southLeftPanel;
					private JLabel dateLabel;
				private JPanel southRightPanel;
					private JLabel totalPriceLabel;

private OrderController orderController = new OrderController();
private List<Order> orders;

private Customer cus;
	/**
	 * Create the frame.
	 */
	public OrderHistoryGui(Customer cus) {
		this.cus = cus;
		init(cus);
	}
	
	public void init(Customer cus)
	{
		orders = orderController.getAllOrdersbyCustomerId(cus.getCustomerId());
		cus.setCustomerOrders(orders);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Penpals Gift Shop");
		setResizable(false);
		
		
			northPanel = new JPanel(new BorderLayout());
				northLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
					backButton = new JButton();
		     			backIcon = createResizedIcon("/resources/uiSymbol/back.png",20,20);
				    backButton.setIcon(backIcon);
				    backButton.addMouseListener(this);
				    backButton.addActionListener(this);
			    northLeftPanel.add(backButton);
		    northPanel.add(northLeftPanel,BorderLayout.LINE_START);
			     
				northCenterPanel =new JPanel(new FlowLayout(FlowLayout.CENTER));
					orderHisLabel = new JLabel("Order History");
					orderHisLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
					orderHisLabel.setHorizontalAlignment(JLabel.CENTER);
				northCenterPanel.add(orderHisLabel);
			northPanel.add(northCenterPanel,BorderLayout.CENTER);
		getContentPane().add(northPanel,BorderLayout.PAGE_START);
				
			ordersPanel = new JPanel(new GridLayout(0,1,0,0));
			ordersPanel.setBorder(new EmptyBorder(5, 10, 20, 10));
			for(Order order :orders)
			{
				orderPanel = createOrderPanel(order);
				orderPanel.setPreferredSize(new Dimension(450,100));
				ordersPanel.add(orderPanel);
			}
			
		getContentPane().add(ordersPanel,BorderLayout.CENTER);
		pack();
			
		
	}	

	public JPanel createOrderPanel(Order order)

	{
		JPanel orderPanel = new JPanel(new BorderLayout(0,5));
		
		orderPanel.addMouseListener(new MouseAdapter() { 
		     public void mousePressed(MouseEvent m) { 
		    	 dispose();
		    	 OrderTrackingGui frame = new OrderTrackingGui(cus,order);
		    	 frame.setVisible(true);
		     } 
		     
		     
		     @Override
		 	public void mouseEntered(MouseEvent e) {
		 		// TODO Auto-generated method stub
		    	 setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		 	}

		 	@Override
		 	public void mouseExited(MouseEvent e) {
		 		// TODO Auto-generated method stub
		 		setCursor(Cursor.getDefaultCursor());
		 	}
		    }); 

			orderIDLabel = new JLabel("Order ID: "+ String.valueOf(order.getOrderId()));
			orderIDLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
		orderPanel.add(orderIDLabel,BorderLayout.PAGE_START);
		
		
			statusLabel = new JLabel();
				String completeStatus;
				String deliveryStatus;
				if(order.getOrderIsDelivered())
				{
					deliveryStatus = "Delivered";
				}
				else
				{
					deliveryStatus =  "In Delivery";
				}
				
				if(order.getIsCompleted())
				{
					completeStatus = "Completed";
				}
				else
				{
					completeStatus = "Waiting for Confirmation";
				}
			statusLabel.setText("Status : " + completeStatus + ", " + deliveryStatus);
			statusLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
		orderPanel.add(statusLabel,BorderLayout.CENTER);
			
		
			southPanel = new JPanel(new BorderLayout());
				
				southLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
						//dummy data
						String date = order.getOrderDate();
					dateLabel = new JLabel(date);
				southLeftPanel.add(dateLabel);
			southPanel.add(southLeftPanel,BorderLayout.LINE_START);
				
				
				southRightPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
				
					totalPriceLabel = new JLabel("RM " + String.valueOf(order.getOrderTotal()));
				southRightPanel.add(totalPriceLabel);
				
			southPanel.add(southRightPanel,BorderLayout.LINE_END);
		orderPanel.add(southPanel,BorderLayout.PAGE_END);
		orderPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		orderPanel.setPreferredSize(new Dimension(700,150));
			
		return orderPanel;
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
			UserPageGui frame = new UserPageGui(cus);
			frame.setVisible(true);
		}
		
	}

}
