import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class POP3Rcv {
    private Client_GUI gui;
    public POP3Rcv(Client_GUI gui){
        this.gui = gui;
    }
    DefaultTableModel receive(Folder folder) {
        try {
            Message msg[] = folder.getMessages();

            DefaultTableModel tbm = new DefaultTableModel(new String[]{"From", "Object", "Text"}, 0);

            for (Message value : msg) {
                if (value.isMimeType("text/plain")) {
                    String[] message = {Arrays.toString(value.getFrom()), value.getSubject(), (String) value.getContent()};
                    tbm.addRow(message);
                }
            }
            gui.setModel(tbm);
            return tbm;
        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
