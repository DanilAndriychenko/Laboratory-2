import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class    Storage{

    private JFrame frame;
    private JScrollPane contentJScrollPane;
    private JPanel controlPanel, contentPanel;

    Storage(String nameOfStorage){
        frame = createFrame(nameOfStorage);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());
        startInterface();
        storageContent();
        frame.setVisible(true);
    }

    private void startInterface() {
        contentPanel = new JPanel(new GridBagLayout());
        contentJScrollPane = new JScrollPane(contentPanel);
        controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));
        frame.add(contentPanel, BorderLayout.CENTER);
        controlPanel.setBackground(Color.DARK_GRAY);
        frame.add(controlPanel, BorderLayout.WEST);
        controlPanelContent("Sasha", new String[]{"Storage", "Admission", "Write-off", "Settings"});
        frame.revalidate();
    }

    private void controlPanelContent(String ownerName, String[] points){
        JLabel jlOwnerName = new JLabel(ownerName);
        jlOwnerName.setBackground(Color.DARK_GRAY);
        jlOwnerName.setForeground(Color.WHITE);
        jlOwnerName.setFont(new Font("Serif", Font.PLAIN, 27));
        controlPanel.add(jlOwnerName);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        for(int i = 0; i < points.length; i++){
            JLabel jlPoints = new JLabel(points[i]);
            jlPoints.setFont(new Font("Serif", Font.PLAIN, 27));
            jlPoints.setForeground(Color.WHITE);
            controlPanel.add(jlPoints);
        }
    }

    private JFrame createFrame(String nameOfStorage){
        return new JFrame(nameOfStorage);
    }

    private JFrame createFrame(){
        return new JFrame("Default Storage");
    }

    public static void main(String[] args) {
        Storage storage = new Storage("Storage");
    }

    private void storageContent(){
        JButton jbAddNewGroup = new JButton();
        controlPanel.add(jbAddNewGroup);
        jbAddNewGroup.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
        Object[] titles = {"Group of Products", "Description", "Overall Price"};
        Object[][] data = {{"Vegetables", "Contains different vegetables", 5000}};
        JTable table = new JTable(data, titles);
        contentPanel.add(table);
        frame.revalidate();
    }

}
