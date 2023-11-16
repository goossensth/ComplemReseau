import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pop3_Client {
    private JTextField passField;
    private JTable tableMails;
    private JTextField userField;
    private JButton getMails;
    private JPanel mainPanel;

    public Pop3_Client() {
        getMails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                POP3Rcv received = new POP3Rcv(userField.getText(), passField.getText());
                DefaultTableModel tbm = received.receive();
                tableMails.setModel(tbm);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pop3_Client");
        frame.setContentPane(new Pop3_Client().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600,500);
    }
}
