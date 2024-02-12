package f1.visualizer.controller;
import f1.visualizer.view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RotationController {
    private MainFrame mainFrame;

    public RotationController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListeners();
    }

    private void attachListeners() {
        mainFrame.getRotatePlus().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rotate(10); // Rotate by 10 degrees
            }
        });

        mainFrame.getRotateMinus().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rotate(-10); // Rotate by -10 degrees
            }
        });
    }

    private void rotate(int degrees) {
        double newRotationAngle = mainFrame.getRotationAngle() + Math.toRadians(degrees);
        mainFrame.setRotationAngle(newRotationAngle);
        mainFrame.repaint();
        mainFrame.revalidate();
    }
}
