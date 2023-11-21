import javax.mail.MessagingException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Client_GUI {
    private JTextField passField;
    private JTable tableMails;
    private JTextField userField;
    private JButton getMails;
    private JPanel mainPanel;

    private POP3Rcv receiver;

    public static void main(String[] args) throws MessagingException {
        Client_GUI gui = new Client_GUI();
        JFrame frame = new JFrame("Pop3_Client");
        frame.setContentPane(gui.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600,500);
        new POP3Client(gui);
    }
    public Client_GUI() {
        DefaultTableModel tbm = new DefaultTableModel(new String[]{"From", "Object", "Text"}, 0);
        tableMails.setModel(tbm);
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
}
