import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class EditProductDialog extends JDialog {

    private final JLabel jLabelProductName = new JLabel("Product name: ");
    private final JLabel jLabelDescription = new JLabel("Description: ");
    private final JLabel jLabelGroup = new JLabel("Group: ");
    private final JLabel jLabelManufacturer = new JLabel("Manufacturer: ");
    private final JLabel jLabelPrice = new JLabel("Price: ");

    private final JTextField productTextField = new JTextField(20);
    private final JTextArea descriptionTextArea = new JTextArea(4,4);
    private final JTextField manufacturerTextField = new JTextField(20);

    private final JButton jButtonCreate = new JButton("Apply Changes");
    private final JButton jButtonCancel = new JButton("Cancel");

    private final JLabel jLabelStatus = new JLabel(" ");
    private Font font;

    EditProductDialog(final JFrame owner, boolean modal, String name, String description, final String group, String manufacturer, Double price) {
        super(owner, "Edit Product", modal);
        setResizable(false);
        font = new Font("Serif", Font.PLAIN, 20);
        productTextField.setText(name);
        descriptionTextArea.setText(description);
        manufacturerTextField.setText(manufacturer);
        String[] groups = getGroups();
        String comboGroup = "";
        for (int i=0; i<groups.length; i++) if (groups[i].equals(group)) comboGroup=groups[i];
        JComboBox<String> groupComboBox = new JComboBox<>(groups);
        groupComboBox.setSelectedItem(comboGroup);
        SpinnerModel spinnerModel = new SpinnerNumberModel(0.01,0.01,9999,1);
        JSpinner priceSpinner = new JSpinner(spinnerModel);
        priceSpinner.setValue(price);

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
        main.add(productTextField, helper.nextCell().setInsets(0,0,5,0).get());
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
            String prevPath = "StorageData\\" + group + "\\" + name + ".txt";
            if (!productTextField.getText().equals("") && !jLabelManufacturer.getText().equals("")) {
                try {
                    Files.delete(Path.of(prevPath));
                } catch (IOException ex) {
                }
                File productFile = new File("StorageData\\" + groupComboBox.getSelectedItem() + "\\" + productTextField.getText() + ".txt");
                try {
                    PrintWriter writer = new PrintWriter(productFile);
                    writer.write("$" + productTextField.getText() + "$" + descriptionTextArea.getText() + "$" + groupComboBox.getSelectedItem() + "$" + manufacturerTextField.getText() + "$" + priceSpinner.getValue() + "$" + "0$");
                    writer.close();
                } catch (IOException ex) {
                }
            }
            Storage.revalidateProductsTableData();
            setVisible(false);
        });
        jButtonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    private static String[] getGroups(){
        File storage = new File("StorageData\\");
        String[] groups = new String[storage.list().length];
        for (int i = 0; i < storage.list().length; i++) {
            groups[i] = storage.listFiles()[i].getName();
        }
        return groups;
    }
}
