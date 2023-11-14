import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SMTPSend {
    public static void main(String[] args) {
        try {
            String exp = "goossensth@u2.tech.hepl.local";
            String destination = "goossensth@u2.tech.hepl.local";
            String subject = "Essai mail";
            String text = "Bonjour, j'essaie d'envoyer un mail";
            Properties prop = System.getProperties();
            prop.put("mail.smtp.host", "u2.tech.hepl.local");
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
