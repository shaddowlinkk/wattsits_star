import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
/*
 * Created by JFormDesigner on Thu Aug 03 22:22:36 EDT 2017
 */




public class LogIn extends JFrame {
    private DBC conn;
    private String user;
    public LogIn(DBC connector) {
        conn=connector;
        initComponents();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void loginActionPerformed() {
    }

    private void loginActionPerformed(ActionEvent e) throws ClassNotFoundException, SQLException {
        user=formattedTextField1.getText();
        conn.tryConnect(user,passwordField1.getText());
        if(!conn.getIsConnected()){
            incorrectLogIn();
        }else{
            //todo admin vs user recognition via database
            UserGUI n = new UserGUI(conn,user);
            close();
        }
    }
    public void incorrectLogIn(){
        JOptionPane.showMessageDialog(null,
                "You have entered a invalid username or password",
                "Failed login",
                JOptionPane.WARNING_MESSAGE);
    }
     public void close(){
        this.setVisible(false);
        this.dispose();
     }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - mac van pelt
        formattedTextField1 = new JFormattedTextField();
        Username = new JLabel();
        password = new JLabel();
        login = new JButton();
        passwordField1 = new JPasswordField();

        //======== this ========
        setTitle("Star Citizen Manager");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(formattedTextField1);
        formattedTextField1.setBounds(95, 30, 200, formattedTextField1.getPreferredSize().height);

        //---- Username ----
        Username.setText("Username");
        contentPane.add(Username);
        Username.setBounds(new Rectangle(new Point(25, 30), Username.getPreferredSize()));

        //---- password ----
        password.setText("Passord");
        contentPane.add(password);
        password.setBounds(new Rectangle(new Point(25, 65), password.getPreferredSize()));

        //---- login ----
        login.setText("Login");
        login.addActionListener(e -> {
            loginActionPerformed();
            try {
                loginActionPerformed(e);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        contentPane.add(login);
        login.setBounds(115, 105, 140, login.getPreferredSize().height);
        contentPane.add(passwordField1);
        passwordField1.setBounds(95, 65, 200, passwordField1.getPreferredSize().height);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - mac van pelt
    private JFormattedTextField formattedTextField1;
    private JLabel Username;
    private JLabel password;
    private JButton login;
    private JPasswordField passwordField1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
