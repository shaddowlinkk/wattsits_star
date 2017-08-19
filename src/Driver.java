import java.sql.SQLException;

/**
 * Created by nathan on 8/4/2017.
 *
 */
public class Driver {
    public Driver() throws ClassNotFoundException, SQLException {
        run();
    }

    private LogIn logIn;
    DBC connection=new DBC();
    public void run() throws ClassNotFoundException, SQLException {
        logIn=new LogIn(connection);
        logIn.setVisible(true);

    }
}
