import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

public class MultiPartSNMP {
    public String exp ;
    public String destination ;
    public String subject;
    public String text ;
    public Properties prop;

    private File fileToSend;
    public MultiPartSNMP(String exp, String destination, String object, String text, File fts) {
        this.exp = exp;
        this.destination = destination;
        this.subject = object;
        this.text = text;
        this.prop = System.getProperties();
        prop.put("mail.smtp.host", "u2.tech.hepl.local");
        fileToSend = fts;
    }

    public  void send() {
        try {
            Session sess = Session.getDefaultInstance(prop, null);

            MimeMessage msg = new MimeMessage(sess);

            //Définis les paramètres généraux du message
            msg.setFrom(new InternetAddress(exp));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(destination));
            msg.setSubject(subject);

            //Crée le multipart
            Multipart multiPt = new MimeMultipart();

            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.setText(text);
            multiPt.addBodyPart(bodyPart);

            //String linkToPart = "C:/Users/benja/ProjetCompReseau/ComplemReseau/8687v0.jpg";
            DataSource source = new FileDataSource(fileToSend);
            bodyPart.setDataHandler(new DataHandler(source));
            bodyPart.setFileName(fileToSend.getName());

            msg.setContent(multiPt);
            System.out.println("Envoi du mail");
            Transport.send(msg);
            System.out.println("Message envoyé à " + destination);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
