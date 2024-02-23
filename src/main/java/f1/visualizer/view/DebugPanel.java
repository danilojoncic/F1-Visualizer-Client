package f1.visualizer.view;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class DebugPanel extends JPanel {
    JButton debug = new JButton("Debug");
    JButton startButton = new JButton("Play");
    JButton pauseButton = new JButton("Pause");
    JButton backToMenuButton = new JButton("Go back");
    JSlider rotationSlider = new JSlider(SwingConstants.HORIZONTAL,0,360,0);

    public DebugPanel() {
        this.setLayout(new GridLayout(2,3));
        init();
    }


    private void init(){
        rotationSlider.setMajorTickSpacing(60);
        this.add(debug);
        this.add(startButton);
        this.add(pauseButton);
        this.add(backToMenuButton);
        this.add(rotationSlider);
    }
}