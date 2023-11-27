import javax.mail.*;
import javax.swing.table.DefaultTableModel;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class POP3Rcv {
    private POP3_Client gui;
    public POP3Rcv(POP3_Client gui){
        this.gui = gui;
    }
    void receive(Folder folder) {
        try {
            Message msg[] = folder.getMessages();



            DefaultTableModel tbm = new DefaultTableModel(new String[]{"From", "Object", "Text","image"}, 0);

            for (Message value : msg)
            {
                Object content = value.getContent();
                gui.getMsgList().add(value);
                if (content instanceof String)
                {
                    String[] message = {Arrays.toString(value.getFrom()), value.getSubject(), (String) value.getContent()};
                    tbm.addRow(message);
                    gui.GetCombo().addItem(value.getSubject());
                }
                else if (content instanceof Multipart){
                    String text = "";
                    Multipart msgMp = (Multipart) value.getContent();
                    gui.GetCombo().addItem(value.getSubject());
                    int partNb = msgMp.getCount();
                    for(int j = 0; j <partNb; j++){
                        Part p = msgMp.getBodyPart(j);
                        String d = p.getDisposition();
                        if(p.isMimeType("text/plain"))
                        {
                            System.out.println("salut");
                            System.out.println(p.getContent());
                            text = (String) p.getContent();
                        }

                        if(d!=null && d.equalsIgnoreCase(Part.ATTACHMENT))
                        {
                            InputStream is = p.getInputStream();
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            int c;
                            while((c=is.read()) != -1) baos.write(c);
                            baos.flush();
                            String nf =  p.getFileName();
                            FileOutputStream fos = new FileOutputStream(nf);
                            baos.writeTo(fos);
                            fos.close();
                            String[] message = {Arrays.toString(value.getFrom()), value.getSubject(), text,nf};
                            tbm.addRow(message);
                            System.out.println("Attached part " + nf);

                        }
                    }
                }
            }
            gui.setModel(tbm);

        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
