import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Mail_sender {
    private JPanel mainPanel;
    private JCheckBox checkBox;
    private JButton sendButton;
    private JTextField fromField;
    private JTextField toField;
    private JTextField objectField;
    private JTextArea textArea1;
    private JButton button1;

    private File fileToSend;

    public Mail_sender() {
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(checkBox.isSelected()){
                    MultiPartSNMP mail = new MultiPartSNMP(fromField.getText(), toField.getText(), objectField.getText(), textArea1.getText(), fileToSend);
                    mail.send();
                    JOptionPane.showMessageDialog(null, "E-mail multipart envoyé avec succès");
                }
                else{
                    SMTPSend mail = new SMTPSend(fromField.getText(), toField.getText(), objectField.getText(), textArea1.getText());
                    mail.send();
                    JOptionPane.showMessageDialog(null, "E-mail simple envoyé avec succès");
                }

            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                    JFileChooser fc = new JFileChooser();
                    int returnVal = fc.showOpenDialog(null);

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        fileToSend = fc.getSelectedFile();
                        button1.setText(String.valueOf(fileToSend));
                        //This is where a real application would open the file.

                    } else {
                    }

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mail_client");
        frame.setContentPane(new Mail_sender().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600,500);
    }
}
