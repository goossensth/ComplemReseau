import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class SMTPSend {
    public String exp ;
    public String destination ;
    public String subject;
    public String text ;
    public Properties prop;

    public SMTPSend(String exp, String destination, String object, String text) {
        this.exp = exp;
        this.destination = destination;
        this.subject = object;
        this.text = text;
        this.prop = System.getProperties();
        String host = "u2.tech.hepl.local";
        //String host = "smtp-relay.gmail.com:25";
        prop.put("mail.smtp.host", host);
    }

    public  void send() {
        try {
            Session sess = Session.getDefaultInstance(prop, null);

            MimeMessage msg = new MimeMessage(sess);

            msg.setFrom(new InternetAddress(exp));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(destination));
            msg.setSubject(subject);
            msg.setText(text);

            System.out.println("Envoi du mail");
            Transport.send(msg);
            System.out.println("Message envoyé à " + destination);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
