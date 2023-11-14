
import javax.mail.*;
import java.util.Properties;

public class JMailSimplePart {
    public JMailSimplePart(){}
    static String host = "u2.tech.hepl.local";
    public static Session sess;

    public static void main(String args[]) {
        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", host);
        sess = Session.getDefaultInstance(prop, null);
    }
}
