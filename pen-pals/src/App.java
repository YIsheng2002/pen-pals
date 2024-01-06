import com.penpals.db.MyDatabase;
import com.penpals.view.Login;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        MyDatabase.doConnection();
        Login login = new Login();
        login.setVisible(true);
    }
}
