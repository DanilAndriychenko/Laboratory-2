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

public class EditGroupDialog extends JDialog {

    private final JLabel jLabelGroupName = new JLabel("Group name: ");
    private final JLabel jLabelDescription = new JLabel("Description: ");

    private final JTextField groupTextField = new JTextField(20);
    private final JTextArea descriptionTextArea = new JTextArea(8, 8);

    private final JButton jButtonCreate = new JButton("Apply changes");
    private final JButton jButtonCancel = new JButton("Cancel");

    private final JLabel jLabelStatus = new JLabel(" ");
    private Font font;
    private String group, description;

    EditGroupDialog(final JFrame owner, boolean modal, String group, String description) {
        super(owner, "Edit Group", modal);
        setResizable(false);
        font = new Font("Serif", Font.PLAIN, 20);
        this.group=group;
        this.description=description;
        descriptionTextArea.setLineWrap(true);
        groupTextField.setText(group);
        descriptionTextArea.setText(description);
        JScrollPane jScrollPane = new JScrollPane(descriptionTextArea);
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        GridBagHelper helperButtons = new GridBagHelper();
        buttonsPanel.add(jButtonCreate, helperButtons.nextCell().alignRight().setInsets(0, 0, 5, 0).get());
        buttonsPanel.add(jButtonCancel, helperButtons.nextCell().alignLeft().setInsets(5, 0, 0, 0).get());

        JPanel main = new JPanel(new GridBagLayout());
        GridBagHelper helper = new GridBagHelper();
        helper.insertEmptyRow(main, 5);
        main.add(jLabelGroupName, helper.nextCell().alignLeft().setInsets(5, 0, 0, 0).get());
        main.add(groupTextField, helper.nextCell().setInsets(0, 0, 5, 0).get());
        helper.insertEmptyRow(main, 5);
        main.add(jLabelDescription, helper.nextRow().nextCell().alignLeft().setInsets(5, 0, 0, 0).get());
        main.add(jScrollPane, helper.nextCell().fillBoth().setInsets(0, 0, 5, 0).get());
        main.add(jLabelStatus, helper.nextRow().nextCell().span().get());
        main.add(buttonsPanel, helper.nextRow().nextCell().span().get());
        helper.insertEmptyRow(main, 5);
        add(main);
        pack();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        jButtonCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File directoryBefore = new File("StorageData\\" + group);
                File groupDirectory = new File("StorageData\\" + groupTextField.getText());
                if (groupTextField.getText().equals(group)) writeDescription();
                else if (directoryBefore.exists()){
                    writeDescription();
                }
                Storage.revalidateGroupsTableData();
                Storage.revalidateProductsTableData();
                setVisible(false);
            }
        });
        jButtonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    private void writeDescription(){
        File description = new File("StorageData\\" + groupTextField.getText() + "\\description.txt");
        try {
            PrintWriter writer = new PrintWriter(description);
            writer.write(descriptionTextArea.getText());
            writer.close();
        } catch (FileNotFoundException ex) {
        }
    }
}
