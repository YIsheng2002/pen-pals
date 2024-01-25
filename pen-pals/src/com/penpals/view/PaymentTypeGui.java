package com.penpals.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;


public class PaymentTypeGui extends JDialog implements ActionListener, MouseListener {

    private static final long serialVersionUID = 1L;

    HashMap<String, String> types = new HashMap<String, String>();
    private boolean isDisposed = false;

    // North panel
    private JPanel northPanel;

    	private JLabel titleLabel;

    // Center panel
    private JPanel typePanels;

    	private ImageIcon imageIcon;

    // South Panel
    private JPanel southPanel;

	    private JButton applyButton;

    private String choice = "";
    private CheckoutGui frame;

    /**
     * Create the dialog.
     */
    public PaymentTypeGui(CheckoutGui frame) {
        super(frame, "Payment Type", true);
        this.frame = frame;
        init();
    }

    public void init() {
        
    	setBounds(200, 150, 450, 300);
    	setMinimumSize(new Dimension(450,300));
	        types = getTypeAndURL();
	
	        northPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
	        titleLabel = new JLabel("Payment Type");
	        titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
	        titleLabel.setHorizontalAlignment(JLabel.CENTER);
	        northPanel.add(titleLabel);
	        getContentPane().add(titleLabel, BorderLayout.PAGE_START);
    

	        typePanels = new JPanel(new GridLayout(0, 1, 0, 0));
	        typePanels.setBorder(new EmptyBorder(5, 10, 20, 10));
	
	        for (String type : types.keySet()) {
	            imageIcon = createResizedIcon(types.get(type), 50, 50);
	            JButton btn = new JButton();
	
	            btn.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                    choice = btn.getText();
	                }
	            });
	
	        
			 	
			 	btn.addMouseListener((MouseListener) new MouseListener() {
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
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					}
	
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						setCursor(Cursor.getDefaultCursor());
					}
		 		 });
			 	
	            btn.setIcon(imageIcon);
	            btn.setText(type);
	
	            typePanels.add(btn);
	        }
	    getContentPane().add(typePanels, BorderLayout.CENTER);
	
	        southPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
	        applyButton = new JButton("Apply");
	        applyButton.addActionListener(this);
	        applyButton.addMouseListener(this);
	        southPanel.add(applyButton);
	
	    add(southPanel, BorderLayout.PAGE_END);
	    pack();
    
	}
	
	private HashMap<String,String> getTypeAndURL()
	{
	    HashMap<String, String> types = new HashMap<String, String>();
	    
	    types.put("Credit Card", "/resources/uiSymbol/card.jpg");
	    types.put("Debit Card", "/resources/uiSymbol/debitCard.png");
		types.put("Online Banking", "/resources/uiSymbol/fpx.png");
		types.put("Cash on Delivery", "/resources/uiSymbol/cod.png");
		
		
	    return types;

	}
	
	private ImageIcon createResizedIcon(String imagePath, int width, int height) {
	    
	    ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));
	    Image originalImage = originalIcon.getImage();
	    Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	    return new ImageIcon(resizedImage);
	}


	public boolean isDisposed()
	{	
		return isDisposed;
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
		if(e.getSource()==applyButton)
		{
			
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==applyButton)
		{
			setCursor(Cursor.getDefaultCursor());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==applyButton)
		{
			if(choice=="")
			{
				JOptionPane.showMessageDialog(null, "Please select a payment type");
			}
			else
			{
				isDisposed = true;
				frame.setPaymentType(choice);
				dispose();
			}
		}
	}

}
