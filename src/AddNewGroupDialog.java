import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class AddNewGroupDialog extends JDialog {

    private final JLabel jLabelGroupName = new JLabel("Group name: ");
    private final JLabel jLabelDescription = new JLabel("Description: ");

    private final JTextField groupTextField = new JTextField(20);
    private final JTextArea jTextArea = new JTextArea(8,8);

    private final JButton jButtonCreate = new JButton("Create");
    private final JButton jButtonCancel = new JButton("Cancel");

    private final JLabel jLabelStatus = new JLabel(" ");
    private Font font;

    AddNewGroupDialog(final JFrame owner, boolean modal) {
        super(owner, "New Group", modal);
        setResizable(false);
        font = new Font("Serif", Font.PLAIN, 20);
        jTextArea.setLineWrap(true);
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        GridBagHelper helperButtons = new GridBagHelper();
        buttonsPanel.add(jButtonCreate, helperButtons.nextCell().alignRight().setInsets(0,0,5,0).get());
        buttonsPanel.add(jButtonCancel, helperButtons.nextCell().alignLeft().setInsets(5,0,0,0).get());

        JPanel main = new JPanel(new GridBagLayout());
        GridBagHelper helper = new GridBagHelper();
        helper.insertEmptyRow(main, 5);
        main.add(jLabelGroupName, helper.nextCell().alignLeft().setInsets(5,0,0,0).get());
        main.add(groupTextField, helper.nextCell().setInsets(0,0,5,0).get());
        helper.insertEmptyRow(main, 5);
        main.add(jLabelDescription, helper. nextRow().nextCell().alignLeft().setInsets(5,0,0,0).get());
        main.add(jScrollPane, helper.nextCell().fillBoth().setInsets(0,0,5,0).get());
        main.add(jLabelStatus, helper.nextRow().nextCell().span().get());
        main.add(buttonsPanel, helper.nextRow().nextCell().span().get());
        helper.insertEmptyRow(main, 5);
        add(main);
        pack();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        jButtonCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (groupTextField != null && jTextArea != null && !groupTextField.getText().equals("")) {
                    Path path = Paths.get("StorageData\\" + groupTextField.getText());
                    try {
                        Files.createDirectory(path);
                    } catch (IOException ex) {
                        /*jLabelStatus.setText("Incorrect input.");
                        jLabelStatus.revalidate();
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException exc) {
                        }*/
                    }
                    File description = new File("StorageData\\" + groupTextField.getText() + "\\description.txt");
                    try {
                        PrintWriter writer = new PrintWriter(description);
                        writer.write(jTextArea.getText());
                        writer.close();
                    } catch (FileNotFoundException ex) {
                        /*jLabelStatus.setText("Incorrect input.");
                        jLabelStatus.revalidate();
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException exc) {
                        }*/
                    }
                    Storage.revalidateGroupsTableData();
                    setVisible(false);
                }
            }
        });
        jButtonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

}
