import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
/*
 * Created by JFormDesigner on Sat Aug 05 19:39:22 EDT 2017
 */




public class addShip extends JFrame {
    private DBC conn;
    private String handle;
    private int index;
    private String[] names;
    private UserGUI  Gui;
    public addShip(DBC connention,String _handle, UserGUI gui) throws SQLException {
        Gui=gui;
        handle=_handle;
        conn= connention;
        names= conn.getShipsName();
        initComponents();
    }

    private void button1ActionPerformed(ActionEvent e) throws  SQLException {
        conn.addShip(handle,names[index]);
       Gui.setJlist();
        close();
    }

    private void comboBox1ItemStateChanged(ItemEvent e) {
        index= comboBox1.getSelectedIndex();
    }
    public void close(){
        this.setVisible(false);
        this.dispose();
    }

    private void initComponents() throws SQLException {
        comboBox1 = new JComboBox(names);
        button1 = new JButton();


        //======== this ========
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- comboBox1 ----
        comboBox1.addItemListener(e -> comboBox1ItemStateChanged(e));
        contentPane.add(comboBox1);
        comboBox1.setBounds(5, 5, 210, comboBox1.getPreferredSize().height);

        //---- button1 ----
        button1.setText("Add ship");
        button1.addActionListener(e -> {
            try {
                button1ActionPerformed(e);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(220, 5), button1.getPreferredSize()));

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
    private JComboBox comboBox1;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
