//import com.penpals.db.MyDatabase;
import java.util.Scanner;
import com.penpals.controller.CustomerController;
import com.penpals.db.MyDatabase;
import com.penpals.model.Customer;
import com.penpals.db.MyDatabase;
import com.penpals.controller.*;
import com.penpals.view.*;
import com.penpals.model.*;

import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
    	//LoginGui frame = new LoginGui();
        //frame.setVisible(true);


        CustomerController customerController = new CustomerController();
        customerController.connectToDatabase();
        Customer customer = customerController.getCustomerDetailbyUsernamePassword("ahmad1234", "ahmad1234");
        System.out.println(customer.getCustomerName());

        BrowseProductGui browseProductGui = new BrowseProductGui(customer);
        browseProductGui.setVisible(true);
        //ProductController productController = new ProductController();
        //productController.connectToDatabase();
        //Product product = productController.getProductDetailbyId(1);

        //ProductPageGui productPageGui = new ProductPageGui(customer, product);
        //productPageGui.setVisible(true);
    }
}
