//import com.penpals.db.MyDatabase;
import java.util.Scanner;
import com.penpals.controller.CustomerController;
import com.penpals.model.Customer;
import com.penpals.view.Login;
import com.penpals.db.MyDatabase;
import com.penpals.view.CartGui;
import com.penpals.view.LoginGui;
import com.penpals.view.OrderTrackingGui;
import com.penpals.view.PaymentTypeGui;
import com.penpals.view.ProductPageGui;
import com.penpals.view.UserPageGui;

public class App {
    public static void main(String[] args) throws Exception {
    	LoginGui frame = new LoginGui();
        frame.setVisible(true);
        
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
        Login login = new Login();
        login.setVisible(true);
    }
}
