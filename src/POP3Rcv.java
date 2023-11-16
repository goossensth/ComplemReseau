import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class POP3Rcv {
    static String host ="u2.tech.hepl.local";
    private  String user;
    private String pass ;

    public POP3Rcv(String user, String pass){
        this.user = user;
        this.pass = pass;
    }
    DefaultTableModel receive() {
        try {
            Properties prop = System.getProperties();
            prop.put("mail.pop3.host", host);
            prop.put("mail.disable.top", true);
            Session sess = Session.getDefaultInstance(prop, null);


            Store st = sess.getStore("pop3");
            st.connect(host, user, pass);
            Folder f = st.getFolder("INBOX");
            f.open(Folder.READ_ONLY);

            Message msg[] = f.getMessages();
            System.out.println(f.getMessageCount() + "  mails.");
            System.out.println(f.getNewMessageCount() + " nouveaux mails.");

            DefaultTableModel tbm = new DefaultTableModel(new String[]{"From", "Object", "Text"}, 0);

            for(int m = 0; m< msg.length; m++){
                if(msg[m].isMimeType("text/plain")){
                    String[] message = {Arrays.toString(msg[m].getFrom()), msg[m].getSubject(), (String) msg[m].getContent()};
                    tbm.addRow(message);
                }
            }
            return tbm;


        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
