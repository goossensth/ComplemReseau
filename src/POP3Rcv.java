import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class POP3Rcv {
    static String host ="u2.tech.hepl.local";
    public static void main(String[] args) {
        try {
            Properties prop = System.getProperties();
            prop.put("mail.pop3.host", host);
            prop.put("mail.disable.top", true);
            Session sess = Session.getDefaultInstance(prop, null);

            String user ="goossensth";
            String pass = "thomas123";

            Store st = sess.getStore("pop3");
            st.connect(host, user, pass);
            Folder f = st.getFolder("INBOX");
            f.open(Folder.READ_ONLY);

            Message msg[] = f.getMessages();
            System.out.println(f.getMessageCount() + "  mails.");
            System.out.println(f.getNewMessageCount() + " nouveaux mails.");

            System.out.println("Liste des messages ");

            for(int m = 0; m< msg.length; m++){
                if(msg[m].isMimeType("text/plain")){
                    System.out.println("Message de :" + msg[m].getFrom()[0]);
                    System.out.println(msg[m].getContent());
                }
            }


        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
