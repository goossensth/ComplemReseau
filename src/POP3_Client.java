import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

    public List<Message> getMsgList()
    {

        return msgList;
    }

    public void setMsgList(List<Message> msgList) {
        this.msgList = msgList;
    }

    public JTree getMailTree(){return mailTree;}

    private List<Message> msgList;

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
    public POP3_Client()
    {

        DefaultTableModel tbm = new DefaultTableModel(new String[]{"From", "Object", "Text"}, 0);
        tableMails.setModel(tbm);

        msgList = new ArrayList<>();

        mailChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                TreeExample();
            }
        });
    }


    public void TreeExample()
    {
        //create the root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        //create the child nodes
        DefaultMutableTreeNode vegetableNode = new DefaultMutableTreeNode("test1");
        DefaultMutableTreeNode fruitNode = new DefaultMutableTreeNode("Test2");
        //add the child nodes to the root node
        root.add(vegetableNode);
        root.add(fruitNode);

        //create the tree by passing in the root node
        mailTree = new JTree(root);
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
