package com.penpals.view;

import java.awt.Color;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import com.penpals.model.Customer;
import com.penpals.model.Order;
import com.penpals.model.CartItem;
import com.penpals.model.Payment;

import com.penpals.controller.ReceiptTable;
import com.penpals.controller.ReceiptText;
import com.penpals.controller.PaymentController;
  
public class GenerateReceipt {
    
    private PDDocument document;
    private PDPage firstPage;
    private Customer cus;
    private Order order;
    private Payment payment;

    public GenerateReceipt(Customer cus, Order order) throws IOException{
        this.document = new PDDocument();
        this.firstPage = new PDPage(PDRectangle.A4);
        this.cus = cus;
        this.order = order;
        this.payment = new PaymentController().getPayment(order.getOrderId());

        document.addPage(firstPage);
    
        String name = cus.getCustomerName();
        String callNo = cus.getCustomerTelNumber();

        int pageWidth = (int) firstPage.getMediaBox().getWidth();
        int pageHeight = (int) firstPage.getMediaBox().getHeight();

        PDPageContentStream contentStream = new PDPageContentStream(document, firstPage);        
        ReceiptText text = new ReceiptText(document, contentStream);

        PDFont font=  new PDType1Font(Standard14Fonts.getMappedFontName("HELVETIC_BOLD").HELVETICA_BOLD);
        PDFont italicFont = new PDType1Font(Standard14Fonts.getMappedFontName("HELVETIC_OBLIQUE").HELVETICA_OBLIQUE);

        //change to ur own path
       PDImageXObject headImage = PDImageXObject.createFromFile("C:\\Users\\User\\Desktop\\oop_final\\pen-pals\\pen-pals\\src\\resources\\uiSymbol\\loginPage.png", document);
        contentStream.drawImage(headImage, 0, pageHeight-235, pageWidth, 239);

        String[] contactDetails = new String[]{"012-234-4871", "03-5167-8192"};
        text.addMultipleLineText(contactDetails, 18, (int)(pageWidth - font.getStringWidth("012-234-4871")/1000*15-10), pageHeight-25, font, 15, Color.BLACK);

        text.addSingleLineText("Penpals Gift Shop", 25, pageHeight-150, font, 40, Color.BLACK);

        text.addSingleLineText("Customer Name: "+ name, 25, pageHeight-250, font, 16, Color.BLACK);
        text.addSingleLineText("Contact Number: "+ callNo, 25, pageHeight-274, font, 16, Color.BLACK);

        String invoiceNo = "Invoice# "+ String.valueOf(order.getOrderId());
        float textWidth = text.getTextWidth(invoiceNo, font, 16);
        text.addSingleLineText(invoiceNo, pageWidth-(int)textWidth-25, pageHeight-250, font, 16, Color.BLACK);

        float dateTextWidth = text.getTextWidth("Date: "+ order.getOrderDate(), font, 16);
        text.addSingleLineText("Date: "+ order.getOrderDate(), pageWidth-(int)dateTextWidth-25, pageHeight-274, font, 16, Color.BLACK);

        ReceiptTable table = new ReceiptTable(document, contentStream);

        int[] cellWidths = {70, 160, 120, 90, 100};
        table.setTable(cellWidths, 30, 25, pageHeight-350);
        table.setTableFont(font, 16, Color.BLACK);

        Color tableHeadColor = new Color(240, 93, 11);
        Color tableBodyColor = new Color(219, 218, 198);

        table.addCell("Si. No.", tableHeadColor);
        table.addCell("Items", tableHeadColor);
        table.addCell("Price", tableHeadColor);
        table.addCell("Qty", tableHeadColor);
        table.addCell("Total", tableHeadColor);

        int i = 1;
        int total = 0;
        for (CartItem item : order.getOrderCartItems()) {
            table.addCell(String.valueOf(i), tableBodyColor);
            table.addCell(item.getCartItemProduct().getProductName(), tableBodyColor);
            table.addCell(String.valueOf(item.getCartItemProduct().getProductPrice()), tableBodyColor);
            table.addCell(String.valueOf(item.getCartItemQuantity()), tableBodyColor);
            table.addCell(String.valueOf(item.getCartItemQuantity()*item.getCartItemProduct().getProductPrice()), tableBodyColor);
            total += item.getCartItemQuantity()*item.getCartItemProduct().getProductPrice();
        }

        table.addCell("", null);
        table.addCell("", null);
        table.addCell("Total", tableHeadColor);
        table.addCell("", tableHeadColor);
        table.addCell(String.valueOf(total), tableHeadColor);


        //Methods of Payment
        int[] cellWidths2 = {70, 20, 180, 90, 180};
        ReceiptTable table1 = new ReceiptTable(document, contentStream);

        table1.setTable(cellWidths2, 30, 25, pageHeight-680);
        table1.setTableFont(font, 16, Color.BLACK);

        table1.addCell("", null);
        table1.addCell("", null);
        table1.addCell("Payement Method", tableHeadColor);
        table1.addCell("", tableHeadColor);
        table1.addCell(this.payment.getPaymentMethod(), tableHeadColor);

        contentStream.close();

        document.save("C:\\Users\\User\\Desktop\\receipt.pdf");
        document.close();
    }
   
} 