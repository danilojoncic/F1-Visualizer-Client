package f1.visualizer.view;

import f1.visualizer.controller.ControllManager;
import f1.visualizer.wrappers.Position;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.util.List;
@Data
public class MainFrame extends JFrame {
    private JButton rotatePlus;
    private JButton rotateMinus;
    private DrawingPanel drawingPanel;
    JButton increaseScaleButton = new JButton("Scale +");
    JButton decreaseScaleButton = new JButton("Scale -");
    JButton pushXOffsetButton = new JButton("X +");
    JButton pushYOffsetButton = new JButton("Y +");
    JButton pullXOffsetButton = new JButton("X -");
    JButton pullYOffsetButton = new JButton("Y -");
    JButton debug = new JButton("Debug");
    JButton startButton = new JButton("Play");
    JButton pauseButton = new JButton("Pause");
    ControllManager controllManager;

    public MainFrame() {
        setTitle("F1 Visualizer 0.0.3");
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        drawingPanel = new DrawingPanel();

        rotatePlus = new JButton("Rotate +");
        rotateMinus = new JButton("Rotate -");

        getContentPane().add(drawingPanel);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4));
        buttonPanel.add(increaseScaleButton);
        buttonPanel.add(pushYOffsetButton);
        buttonPanel.add(pushXOffsetButton);
        buttonPanel.add(decreaseScaleButton);
        buttonPanel.add(pullYOffsetButton);
        buttonPanel.add(pullXOffsetButton);
        buttonPanel.add(rotatePlus);
        buttonPanel.add(rotateMinus);
        buttonPanel.add(debug);
        buttonPanel.add(startButton);
        buttonPanel.add(pauseButton);

        // Add the button panel to the frame
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        controllManager = new ControllManager(this);
    }
}
