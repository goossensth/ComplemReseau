import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mail_client {
    private JPanel mainPanel;
    private JCheckBox piècesJointesCheckBox;
    private JButton sendButton;
    private JTextField fromField;
    private JTextField toField;
    private JTextField objectField;
    private JTextArea textArea1;

    public Mail_client() {
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SMTPSend mail = new SMTPSend(fromField.getText(), toField.getText(), objectField.getText(), textArea1.getText());
                mail.send();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mail_client");
        frame.setContentPane(new Mail_client().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(600,500);
    }
}
