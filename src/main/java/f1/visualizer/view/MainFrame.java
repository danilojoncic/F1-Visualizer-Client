package f1.visualizer.view;

import f1.visualizer.wrappers.Position;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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

    public MainFrame() {
        setTitle("F1 Visualizer 0.0.3");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        drawingPanel = new DrawingPanel();

        rotatePlus = new JButton("Rotate +");
        rotateMinus = new JButton("Rotate -");

        getContentPane().add(drawingPanel);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 3));
        buttonPanel.add(increaseScaleButton);
        buttonPanel.add(pushYOffsetButton);
        buttonPanel.add(pushXOffsetButton);
        buttonPanel.add(decreaseScaleButton);
        buttonPanel.add(pullYOffsetButton);
        buttonPanel.add(pullXOffsetButton);
        buttonPanel.add(rotatePlus);
        buttonPanel.add(rotateMinus);
        buttonPanel.add(debug);

        // Add the button panel to the frame
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

    }



    public JButton getRotatePlus() {
        return rotatePlus;
    }

    public void setRotatePlus(JButton rotatePlus) {
        this.rotatePlus = rotatePlus;
    }

    public JButton getRotateMinus() {
        return rotateMinus;
    }

    public void setRotateMinus(JButton rotateMinus) {
        this.rotateMinus = rotateMinus;
    }


    public DrawingPanel getDrawingPanel() {
        return drawingPanel;
    }

    public void setDrawingPanel(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }

    public JButton getIncreaseScaleButton() {
        return increaseScaleButton;
    }

    public void setIncreaseScaleButton(JButton increaseScaleButton) {
        this.increaseScaleButton = increaseScaleButton;
    }

    public JButton getDecreaseScaleButton() {
        return decreaseScaleButton;
    }

    public void setDecreaseScaleButton(JButton decreaseScaleButton) {
        this.decreaseScaleButton = decreaseScaleButton;
    }

    public JButton getPushXOffsetButton() {
        return pushXOffsetButton;
    }

    public void setPushXOffsetButton(JButton pushXOffsetButton) {
        this.pushXOffsetButton = pushXOffsetButton;
    }

    public JButton getPushYOffsetButton() {
        return pushYOffsetButton;
    }

    public void setPushYOffsetButton(JButton pushYOffsetButton) {
        this.pushYOffsetButton = pushYOffsetButton;
    }

    public JButton getPullXOffsetButton() {
        return pullXOffsetButton;
    }

    public void setPullXOffsetButton(JButton pullXOffsetButton) {
        this.pullXOffsetButton = pullXOffsetButton;
    }

    public JButton getPullYOffsetButton() {
        return pullYOffsetButton;
    }

    public void setPullYOffsetButton(JButton pullYOffsetButton) {
        this.pullYOffsetButton = pullYOffsetButton;
    }

    public JButton getDebug() {
        return debug;
    }

    public void setDebug(JButton debug) {
        this.debug = debug;
    }
}
