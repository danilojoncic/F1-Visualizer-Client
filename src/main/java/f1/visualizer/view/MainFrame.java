package f1.visualizer.view;

import f1.visualizer.controller.ControllManager;
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
        setTitle("F1 Visualizer 0.0.3");
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //menuPanel = new MenuPanel();
        //this.add(menuPanel);
        drawingPanel = new DrawingPanel();
        getContentPane().add(drawingPanel);
        debugPanel = new DebugPanel();
        getContentPane().add(debugPanel, BorderLayout.SOUTH);
        controllManager = new ControllManager(this);
    }
}
