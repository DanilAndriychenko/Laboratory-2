import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class AddNewProductDialog extends JDialog {

    private final JLabel jLabelProductName = new JLabel("Product name: ");
    private final JLabel jLabelDescription = new JLabel("Description: ");
    private final JLabel jLabelGroup = new JLabel("Group: ");
    private final JLabel jLabelManufacturer = new JLabel("Manufacturer: ");
    private final JLabel jLabelPrice = new JLabel("Price: ");

    private final JTextField groupTextField = new JTextField(20);
    private final JTextArea descriptionTextArea = new JTextArea(4,4);
    private final JTextField manufacturerTextField = new JTextField(20);

    private final JButton jButtonCreate = new JButton("Create");
    private final JButton jButtonCancel = new JButton("Cancel");

    private final JLabel jLabelStatus = new JLabel(" ");
    private Font font;

    AddNewProductDialog(final JFrame owner, boolean modal) {
        super(owner, "New Product", modal);
        setResizable(false);
        font = new Font("Serif", Font.PLAIN, 20);
        String[] groups = getGroups();
        JComboBox<String> groupComboBox = new JComboBox<>(groups);
        SpinnerModel spinnerModel = new SpinnerNumberModel(0.01,0.01,9999,1);
        JSpinner priceSpinner = new JSpinner(spinnerModel);

        descriptionTextArea.setLineWrap(true);
        JScrollPane jScrollPane = new JScrollPane(descriptionTextArea);
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        GridBagHelper helperButtons = new GridBagHelper();
        buttonsPanel.add(jButtonCreate, helperButtons.nextCell().alignRight().setInsets(0,0,5,0).get());
        buttonsPanel.add(jButtonCancel, helperButtons.nextCell().alignLeft().setInsets(5,0,0,0).get());

        JPanel main = new JPanel(new GridBagLayout());
        GridBagHelper helper = new GridBagHelper();
        helper.insertEmptyRow(main, 5);
        main.add(jLabelProductName, helper.nextCell().alignLeft().setInsets(5,0,0,0).get());
        main.add(groupTextField, helper.nextCell().setInsets(0,0,5,0).get());
        helper.insertEmptyRow(main, 5);
        main.add(jLabelDescription, helper. nextRow().nextCell().alignLeft().setInsets(5,0,0,0).get());
        main.add(jScrollPane, helper.nextCell().fillHorizontally().setInsets(0,0,5,0).get());
        helper.insertEmptyRow(main, 5);

        main.add(jLabelGroup, helper.nextRow().nextCell().alignLeft().setInsets(5,0,0,0).get());
        main.add(groupComboBox, helper.nextCell().fillHorizontally().setInsets(0,0,5,0).get());
        helper.insertEmptyRow(main, 5);
        main.add(jLabelManufacturer, helper.nextRow().nextCell().alignLeft().setInsets(5,0,0,0).get());
        main.add(manufacturerTextField, helper.nextCell().setInsets(0,0,5,0).get());
        helper.insertEmptyRow(main, 5);
        main.add(jLabelPrice, helper.nextRow().nextCell().alignLeft().setInsets(5,0,0,0).get());
        main.add(priceSpinner, helper.nextCell().setInsets(0,0,5,0).get());
        helper.insertEmptyRow(main, 5);

        main.add(jLabelStatus, helper.nextRow().nextCell().span().get());
        main.add(buttonsPanel, helper.nextRow().nextCell().span().get());
        helper.insertEmptyRow(main, 5);
        add(main);
        pack();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        jButtonCreate.addActionListener(e -> {
            if (groupTextField != null && descriptionTextArea != null && !groupTextField.getText().equals("") && !jLabelManufacturer.getText().equals("")) {
                File productFile = new File("D:\\Java IntelliJ IDEA\\Laboratory 2\\StorageData\\" + groupComboBox.getSelectedItem() + "\\" + groupTextField.getText() + ".txt");
                try {
                    PrintWriter writer = new PrintWriter(productFile);
                    writer.write("$" + groupTextField.getText() + "$" + descriptionTextArea.getText() + "$" + groupComboBox.getSelectedItem() + "$" + manufacturerTextField.getText() + "$" + priceSpinner.getValue() + "$" + "0$");
                    writer.close();
                } catch (IOException ex) {
                    /*jLabelStatus.setText("Incorrect input.");
                    jLabelStatus.setVisible(true);*/
                }
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

    private static String[] getGroups(){
        File storage = new File("D:\\Java IntelliJ IDEA\\Laboratory 2\\StorageData\\");
        String[] groups = new String[storage.list().length];
        for (int i = 0; i < storage.list().length; i++) {
            groups[i] = storage.listFiles()[i].getName();
        }
        return groups;
    }
}
