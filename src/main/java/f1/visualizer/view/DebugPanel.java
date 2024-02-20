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
    JButton rotatePlus;
    JButton rotateMinus;
    JButton backToMenuButton = new JButton("Go back");

    public DebugPanel() {
        this.setLayout(new GridLayout(4,4));
        init();
    }


    private void init(){
        rotatePlus = new JButton("Rotate +");
        rotateMinus = new JButton("Rotate -");
        this.add(pushYOffsetButton);
        this.add(pushXOffsetButton);
        this.add(pullYOffsetButton);
        this.add(pullXOffsetButton);
        this.add(rotatePlus);
        this.add(rotateMinus);
        this.add(debug);
        this.add(startButton);
        this.add(pauseButton);
        this.add(backToMenuButton);
    }
}
