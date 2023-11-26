import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class POP3_Client {
    private JTextField passField;
    private JTable tableMails;
    private JTextField userField;
    private JButton getMails;
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JComboBox<String> mailChoose;
    private JTree mailTree;
    public List<Message> msgList;

    private POP3Rcv receiver;

    public static void main(String[] args) throws MessagingException {
        POP3_Client gui = new POP3_Client();
        JFrame frame = new JFrame("Pop3_Client");
        frame.setContentPane(gui.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600,500);
        new POP3Client(gui);
    }
    public POP3_Client() {
        DefaultTableModel tbm = new DefaultTableModel(new String[]{"From", "Object", "Text"}, 0);
        tableMails.setModel(tbm);
        mailChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                            }
        });
    }


    public void showPane(){
        JOptionPane.showMessageDialog(null, "New message !");
        try {
            wait(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    public JButton GetButtonConnect(){return getMails;}

    public void setModel (DefaultTableModel tbm){
        tableMails.setModel(tbm);
    }
    public String GetLogin(){return userField.getText();}
    public String Getpass(){return passField.getText();}

    public JComboBox<String> GetCombo(){return mailChoose;}
}
