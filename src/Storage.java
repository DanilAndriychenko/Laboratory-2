import javax.swing.*;
import java.awt.*;

public class Storage{

    private JFrame frame;
    private JScrollPane contentJScrollPane;
    private JPanel controlPanel, contentPanel;

    Storage(String nameOfStorage){
        frame = createFrame(nameOfStorage);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        startInterface();
    }

    private void startInterface() {
        contentPanel = new JPanel(new GridBagLayout());
        contentJScrollPane = new JScrollPane(contentPanel);
        controlPanel = new JPanel(new GridBagLayout());
        GridBagHelper controlHelper = new GridBagHelper();

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

}
