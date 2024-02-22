package f1.visualizer.view;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class DebugPanel extends JPanel {
    JButton increaseScaleButton = new JButton("Scale +");
    JButton decreaseScaleButton = new JButton("Scale -");
    JButton pushXOffsetButton = new JButton("X +");
    JButton pushYOffsetButton = new JButton("Y +");
    JButton pullXOffsetButton = new JButton("X -");
    JButton pullYOffsetButton = new JButton("Y -");
    JButton debug = new JButton("Debug");
    JButton startButton = new JButton("Play");
    JButton pauseButton = new JButton("Pause");
    JButton rotatePlus = new JButton("Rotate +");;
    JButton rotateMinus = new JButton("Rotate -");;
    JButton backToMenuButton = new JButton("Go back");
    JSlider xSlider = new JSlider(SwingConstants.HORIZONTAL,-100,100,0);
    JSlider ySlider = new JSlider(SwingConstants.HORIZONTAL,-100,100,0);
    JSlider rotationSlider = new JSlider(SwingConstants.HORIZONTAL,0,360,0);

    public DebugPanel() {
        this.setLayout(new GridLayout(5,4));
        init();
    }


    private void init(){
        xSlider.setMajorTickSpacing(10);
        xSlider.setPaintTicks(true);
        xSlider.setPaintLabels(true);


        ySlider.setMajorTickSpacing(10);
        ySlider.setPaintTicks(true);
        ySlider.setPaintLabels(true);

        rotationSlider.setMajorTickSpacing(60);
        rotationSlider.setPaintTicks(true);
        rotationSlider.setPaintLabels(true);
        this.add(debug);
        this.add(startButton);
        this.add(pauseButton);
        this.add(backToMenuButton);
        this.add(xSlider);
        this.add(ySlider);
        this.add(rotationSlider);
    }
}