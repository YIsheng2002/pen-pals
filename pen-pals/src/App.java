//import com.penpals.db.MyDatabase;
import java.util.Scanner;
import com.penpals.controller.CustomerController;
import com.penpals.db.MyDatabase;
import com.penpals.model.Customer;
import com.penpals.view.Login;
import com.penpals.db.MyDatabase;
import com.penpals.view.CartGui;
import com.penpals.view.LoginGui;
import com.penpals.view.OrderTrackingGui;
import com.penpals.view.PaymentTypeGui;
import com.penpals.view.ProductPageGui;
import com.penpals.view.UserPageGui;
import com.penpals.controller.*;
import com.penpals.view.*;
import com.penpals.model.*;

import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
    	//LoginGui frame = new LoginGui();
        //frame.setVisible(true);
        
        /**MyDatabase.doConnection();
        Scanner scanner = new Scanner(System.in);
        String username;
        String password;

        System.out.println("Pen Pals");
        System.out.println("========");

        System.out.println("Login Page");
        System.out.println("==========");
        System.out.println("Username: ");
        username = scanner.nextLine();
        System.out.println("Password: ");
        password = scanner.nextLine();
        System.out.println("==========");

        CustomerController customerController = new CustomerController();
        customerController.connectToDatabase();
        Customer customer = customerController.getCustomerDetailbyUsernamePassword(username, password);

        if (customer.getCustomerId() != 0)
        {
            System.out.println("Welcome " + customer.getCustomerName());
            System.out.println("==========");
            System.out.println("Customer Details");
            System.out.println("==========");
            System.out.println("Name: " + customer.getCustomerName());
            System.out.println("Email: " + customer.getCustomerEmail());
            System.out.println("Tel Number: " + customer.getCustomerTelNumber());
            System.out.println("Address: " + customer.getCustomerAddress().getNumber() + " " + customer.getCustomerAddress().getRoad() + ", " + customer.getCustomerAddress().getPostcode() + ", " + customer.getCustomerAddress().getState());
            System.out.println("==========");
        }
        else
        {
            System.out.println("Invalid username or password");
        }

        scanner.close();**/
        //Login login = new Login();
        //login.setVisible(true);
        //System.out.println("Hello, World!");
        //MyDatabase.doConnection();


        CustomerController customerController = new CustomerController();
        customerController.connectToDatabase();
        Customer customer = customerController.getCustomerDetailbyUsernamePassword("ahmad1234", "ahmad1234");
        System.out.println(customer.getCustomerName());

        //BrowseProductGui browseProductGui = new BrowseProductGui(customer);
        //browseProductGui.setVisible(true);
        ProductController productController = new ProductController();
        productController.connectToDatabase();
        Product product = productController.getProductDetailbyId(1);

        ProductPageGui productPageGui = new ProductPageGui(customer, product);
        productPageGui.setVisible(true);
    }
}
