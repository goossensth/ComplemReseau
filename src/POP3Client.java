import javax.mail.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class POP3Client extends Thread
{
    static String host ="u2.tech.hepl.local";

    private Properties prop;
    private Session sess;
    private Store store;
    private int message_count = 0;

    private static POP3_Client clientGui;
    private  Folder folder;


    POP3Client(POP3_Client gui) throws MessagingException {
        clientGui = gui;
        prop = System.getProperties();
        prop.put("mail.pop3.host", host);
        prop.put("mail.disable.top", true);
        sess = Session.getDefaultInstance(prop, null);
        store = sess.getStore("pop3");
        clientGui.GetButtonConnect().addActionListener(new ConnectListener(){});
    }
    public void connect(String user, String pass) throws MessagingException {
        store.connect(host, user, pass);
        folder = store.getFolder("INBOX");
        folder.open(Folder.READ_ONLY);
        clientGui.GetButtonConnect().setEnabled(false);
        start();
    }
    public  void run(){
        try{
            while(true)
            {
                System.out.println("Checking for update");
                folder = store.getFolder("INBOX");
                folder.open(Folder.READ_ONLY);
                int new_message_count = folder.getMessageCount();
                if(new_message_count > message_count) {
                    System.out.println("Message count" + message_count);
                    if(message_count!=0){
                        //Runtime.getRuntime().exec(new String[] { "osascript", "-e", "display notification \"Vous avez des nouveaux messages\" with title \"POP3 Client\"" });
                        JOptionPane.showMessageDialog(null, "Vous avez recu un mail");
                    }
                    message_count = new_message_count;
                    new POP3Rcv(clientGui).receive(folder);
                }

                Thread.sleep(5000);
                folder.close();
            }

        }
        catch (MessagingException | InterruptedException  e) {
            throw new RuntimeException(e);
        }
    }
    public  class ConnectListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try {
                connect(clientGui.GetLogin(), clientGui.Getpass());
            } catch (MessagingException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}


