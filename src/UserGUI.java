import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.*;
/*
 * Created by JFormDesigner on Thu Aug 03 22:22:43 EDT 2017
 */

public class UserGUI extends JFrame {
    private DBC conn;
    private int shipSelect;
    private String handle;
    private DefaultListModel names = new DefaultListModel();
    public UserGUI(DBC connector,String _handle) throws SQLException {
        handle=_handle;
        conn=connector;
        initComponents();
    }
    private void list1ValueChanged(ListSelectionEvent e) {
        // TODO add your code here
        shipSelect=list1.getSelectedIndex();
    }

    private void button1ActionPerformed(ActionEvent e) throws SQLException {
        // TODO add your code here
        addShip ship= new addShip(conn,handle,this);

    }
    private void button2ActionPerformed(ActionEvent e) throws SQLException {
        System.out.println(Integer.parseInt(conn.getIDList()[shipSelect]));
        conn.removeShip(Integer.parseInt(conn.getIDList()[shipSelect]));
        names.remove(shipSelect);
    }

    private void initComponents() throws SQLException {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - mac van pelt
        label1 = new JLabel();
        label2 = new JLabel();
        scrollPane1 = new JScrollPane();
        setJlist();
        list1 = new JList(names);
        button1 = new JButton();
        button2 = new JButton();
        scrollPane2 = new JScrollPane();
        textArea1 = new JTextArea();
        label3 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Star Citizen Manager");
        setVisible(true);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("name handel ");
        label1.setFont(new Font("Tahoma", Font.PLAIN, 36));
        contentPane.add(label1);
        label1.setBounds(45, 15, 500, 60);

        //---- label2 ----
        label2.setText("Active Ship(s)");
        label2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        contentPane.add(label2);
        label2.setBounds(40, 100, 190, label2.getPreferredSize().height);

        //======== scrollPane1 ========
        {

            //---- list1 ----
            list1.setFont(new Font("Tahoma", Font.PLAIN, 14));
            list1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            list1.setVisibleRowCount(3);
            list1.addListSelectionListener(e -> list1ValueChanged(e));
            scrollPane1.setViewportView(list1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(40, 135, 300, 60);

        //---- button1 ----
        button1.setText("Add New Ship");
        button1.addActionListener(e -> {
            try {
                button1ActionPerformed(e);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        contentPane.add(button1);
        button1.setBounds(350, 165, 175, button1.getPreferredSize().height);
        //---- button2 ----
        button2.setText("Remove Ship");
        button2.addActionListener(e -> {
            try {
                button2ActionPerformed(e);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        contentPane.add(button2);
        button2.setBounds(350, 135, 175, button2.getPreferredSize().height);


        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(textArea1);
        }
        contentPane.add(scrollPane2);
        scrollPane2.setBounds(35, 215, 185, 180);

        //---- label3 ----
        label3.setText("#13241234");
        label3.setFont(new Font("Tahoma", Font.PLAIN, 12));
        contentPane.add(label3);
        label3.setBounds(45, 65, 115, label3.getPreferredSize().height);

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
        label3.setText(""+conn.getUserID(handle));
        label1.setText(conn.getName(handle)+":@"+handle);
        pack();
        setLocationRelativeTo(getOwner());

        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - mac van pelt
    public void setJlist() throws SQLException {
        names.clear();
        String[] ships = conn.getShipsName(conn.getShipInvID(handle));
        for(int i =0;i<ships.length;i++){
            names.addElement(ships[i]);
        }
    }
    private JLabel label1;
    private JLabel label2;
    private JScrollPane scrollPane1;
    private JList list1;
    private JButton button2;
    private JButton button1;
    private JScrollPane scrollPane2;
    private JTextArea textArea1;
    private JLabel label3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
