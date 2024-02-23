package f1.visualizer.view;

import f1.visualizer.controller.ControllManager;
import f1.visualizer.controller.debug.PlacementController;
import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class MainFrame extends JFrame {

    private DrawingPanel drawingPanel;
    private DebugPanel debugPanel;
    private MenuPanel menuPanel;
    ControllManager controllManager;

    public MainFrame() {
        init();
    }


    private void init(){
        setTitle("App");
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        menuPanel = new MenuPanel();
        drawingPanel = new DrawingPanel("Bahrain_Grand_Prix");
        debugPanel = new DebugPanel();
        this.add(menuPanel);
        controllManager = new ControllManager(this);
        PlacementController placementController = new PlacementController(this);
    }
}
