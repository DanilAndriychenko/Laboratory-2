import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Storage {

    private static JFrame frame;
    private static JPanel groupPanel, firstPartContent, secondPartContent, titleWithPlusGroups, titleWithPlusProducts, productsPanel;
    private static JTable tableGroups, tableProducts;
    private static Object[] titlesGroups = {"Group of Products", "Description", "Overall Price"};
    private static Object[] titlesProducts = {"Product", "Description", "Group of Product", "Manufacturer", "Price per one", "Overall Price"};
    private JPanel controlPanel, contentPanel;
    private Font font;

    Storage(String nameOfStorage) {
        frame = createFrame(nameOfStorage);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());
        startInterface();
        storageContent();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Storage storage = new Storage("Storage");
    }

    private void startInterface() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(2,1));
        controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));
        frame.add(contentPanel, BorderLayout.CENTER);
        controlPanel.setBackground(Color.DARK_GRAY);
        frame.add(controlPanel, BorderLayout.WEST);
        controlPanelContent("Sasha", new String[]{"Storage", "Admission", "Write-off", "Settings"});
        frame.revalidate();
        font = new Font("Serif", Font.BOLD, 22);
    }

    private void controlPanelContent(String ownerName, String[] points) {
        JLabel jlOwnerName = new JLabel("<html><div style='text-align: center;'>" + ownerName + "</div></html>", SwingConstants.CENTER);
        jlOwnerName.setBackground(Color.DARK_GRAY);
        jlOwnerName.setForeground(Color.WHITE);
        jlOwnerName.setBorder(new EmptyBorder(15, 5, 5, 5));
        jlOwnerName.setFont(new Font("Serif", Font.PLAIN, 37));
        controlPanel.add(jlOwnerName);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        for (int i = 0; i < points.length; i++) {
            JLabel jlPoints = new JLabel("<html><div style='text-align: center;'>" + points[i] + "</div></html>", SwingConstants.CENTER);
            jlPoints.setBorder(new EmptyBorder(20, 20, 20, 20));
            jlPoints.setFont(new Font("Serif", Font.PLAIN, 27));
            jlPoints.setForeground(Color.WHITE);
            jlPoints.setBackground(Color.YELLOW);
            controlPanel.add(jlPoints);
        }
    }

    private JFrame createFrame(String nameOfStorage) {
        return new JFrame(nameOfStorage);
    }

    private JFrame createFrame() {
        return new JFrame("Default Storage");
    }

    private void storageContent() {
        firstPartContent = new JPanel(new BorderLayout());
        groupPanel = new JPanel(new BorderLayout());
        JLabel groupsLabel = new JLabel("   Group of products");
        groupsLabel.setFont(font);
        JButton addNewGroupButton = new JButton("+");
        JTextField searchGroupTextField = new JTextField(20);


        titleWithPlusGroups = new JPanel();
        titleWithPlusGroups.setLayout(new GridLayout(1,2));
        JPanel labelWithRigidAndPlus = new JPanel();
        labelWithRigidAndPlus.setLayout(new BoxLayout(labelWithRigidAndPlus, BoxLayout.X_AXIS));
        labelWithRigidAndPlus.add(groupsLabel);
        labelWithRigidAndPlus.add(Box.createRigidArea(new Dimension(8, 0)));
        labelWithRigidAndPlus.add(addNewGroupButton);
        titleWithPlusGroups.add(labelWithRigidAndPlus);
        titleWithPlusGroups.add(searchGroupTextField);

        JLabel topBarGroups = new JLabel("    Brief info");
        topBarGroups.setHorizontalAlignment(JLabel.CENTER);
        topBarGroups.setFont(new Font("Serif", Font.PLAIN, 25));
        topBarGroups.setOpaque(true);
        topBarGroups.setForeground(Color.WHITE);
        topBarGroups.setBackground(Color.BLACK);
        topBarGroups.setHorizontalAlignment(SwingConstants.LEFT);
        firstPartContent.add(topBarGroups, BorderLayout.PAGE_START);

        firstPartContent.add(groupPanel, BorderLayout.CENTER);
        contentPanel.add(firstPartContent);
        revalidateGroupsTableData();

        addNewGroupButton.addActionListener(actionEvent -> {
            JDialog addNewGroupDialog = new AddNewGroupDialog(frame, true);
            addNewGroupDialog.setLocationRelativeTo(null);
            addNewGroupDialog.setVisible(true);
        });


        secondPartContent = new JPanel(new BorderLayout());
        productsPanel = new JPanel(new BorderLayout());
        JLabel productsLabel = new JLabel("   Products");
        productsLabel.setFont(font);
        JButton addNewProductButton = new JButton("+");
        JTextField searchProductTextField = new JTextField(20);

        titleWithPlusProducts = new JPanel();
        titleWithPlusProducts.setLayout(new GridLayout(1,2));
        JPanel labelWithRigidAndPlus2 = new JPanel();
        labelWithRigidAndPlus2.setLayout(new BoxLayout(labelWithRigidAndPlus2, BoxLayout.X_AXIS));
        labelWithRigidAndPlus2.add(productsLabel);
        labelWithRigidAndPlus2.add(Box.createRigidArea(new Dimension(8, 0)));
        labelWithRigidAndPlus2.add(addNewProductButton);
        titleWithPlusProducts.add(labelWithRigidAndPlus2);
        titleWithPlusProducts.add(searchProductTextField);

        JLabel topBarProducts = new JLabel("    Extended info");
        topBarProducts.setHorizontalAlignment(JLabel.CENTER);
        topBarProducts.setFont(new Font("Serif", Font.PLAIN, 25));
        topBarProducts.setOpaque(true);
        topBarProducts.setForeground(Color.WHITE);
        topBarProducts.setBackground(Color.BLACK);
        topBarProducts.setHorizontalAlignment(SwingConstants.LEFT);
        secondPartContent.add(topBarProducts, BorderLayout.PAGE_START);

        secondPartContent.add(productsPanel, BorderLayout.CENTER);
        contentPanel.add(secondPartContent);
        revalidateProductsTableData();

        addNewProductButton.addActionListener(actionEvent -> {
            //TODO
            JDialog addNewProductDialog = new AddNewProductDialog(frame, true);
            addNewProductDialog.setLocationRelativeTo(null);
            addNewProductDialog.setVisible(true);
        });
    }

    public static void revalidateGroupsTableData() {
        File storage = new File("D:\\Java IntelliJ IDEA\\Laboratory 2\\StorageData\\");
        DefaultTableModel defaultTableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 2;
            }
        };
        defaultTableModel.setColumnIdentifiers(titlesGroups);
        for (int i = 0; i < storage.list().length; i++) {
            String description = "";
            try {
                FileReader reader = new FileReader("D:\\Java IntelliJ IDEA\\Laboratory 2\\StorageData\\" + storage.listFiles()[i].getName() + "\\description.txt");
                Scanner scan = new Scanner(reader);
                do{
                    if(scan.hasNext())
                    description += (scan.nextLine()+";");
                }while (scan.hasNext());
            } catch (Exception e) {
                //TODO
                e.printStackTrace();
            }
            Object[] objects = {storage.list()[i], description, 0};
            defaultTableModel.addRow(objects);
        }
        tableGroups = new JTable(defaultTableModel);
        tableGroups.getTableHeader().setReorderingAllowed(false);
        groupPanel.removeAll();
        groupPanel.add(titleWithPlusGroups, BorderLayout.NORTH);
        JPanel rigidWithFullTable = new JPanel();
        rigidWithFullTable.setLayout(new BoxLayout(rigidWithFullTable, BoxLayout.Y_AXIS));
        rigidWithFullTable.add(Box.createRigidArea(new Dimension(0,15)));
        rigidWithFullTable.add(tableGroups.getTableHeader());
        rigidWithFullTable.add(tableGroups);
        groupPanel.add(rigidWithFullTable, BorderLayout.CENTER);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }

    public static void revalidateProductsTableData() {
        File storage = new File("D:\\Java IntelliJ IDEA\\Laboratory 2\\StorageData\\");
        DefaultTableModel defaultTableModel = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 2 || column == 5) return false;
                return true;
            }
        };
        defaultTableModel.setColumnIdentifiers(titlesProducts);
        for (int i = 0; i < storage.list().length; i++) {
            String productContent = "";
                File group = new File("D:\\Java IntelliJ IDEA\\Laboratory 2\\StorageData\\" + storage.listFiles()[i].getName());
                for(int j = 0; j < group.list().length; j++){
                    try {
                        if(group.listFiles()[j].getName().equals("description.txt"))
                            continue;
                        FileReader reader = new FileReader("D:\\Java IntelliJ IDEA\\Laboratory 2\\StorageData\\" + storage.listFiles()[i].getName() + "\\" + group.listFiles()[j].getName());
                        Scanner scan = new Scanner(reader);
                        do{
                            if(scan.hasNext())
                            productContent += (scan.nextLine()+" ");
                        }while (scan.hasNext());
                    } catch (Exception e) {
                        //TODO
                        e.printStackTrace();
                }
                    String[]content = productContent.split("\\$");
                    Object[] objects = {content[1], content[2], content[3], content[4], content[5], content[6]};
                    defaultTableModel.addRow(objects);
            }

        }
        tableProducts = new JTable(defaultTableModel);
        tableProducts.getTableHeader().setReorderingAllowed(false);
        productsPanel.removeAll();
        productsPanel.add(titleWithPlusProducts, BorderLayout.NORTH);
        JPanel rigidWithFullTable = new JPanel();
        rigidWithFullTable.setLayout(new BoxLayout(rigidWithFullTable, BoxLayout.Y_AXIS));
        rigidWithFullTable.add(Box.createRigidArea(new Dimension(0,15)));
        rigidWithFullTable.add(tableProducts.getTableHeader());
        rigidWithFullTable.add(tableProducts);
        productsPanel.add(rigidWithFullTable, BorderLayout.CENTER);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }
}
