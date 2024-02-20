package f1.visualizer.controller;

import f1.visualizer.view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RotationController {
    private MainFrame mainFrame;

    public RotationController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListners();
    }

    private void attachListners(){
        mainFrame.getDebugPanel().getRotatePlus().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getDrawingPanel().setRotationAngle(mainFrame.getDrawingPanel().getRotationAngle() + Math.toRadians(50));
                mainFrame.repaint();
                mainFrame.revalidate();
            }
        });


        mainFrame.getDebugPanel().getRotateMinus().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getDrawingPanel().setRotationAngle(mainFrame.getDrawingPanel().getRotationAngle() - Math.toRadians(50));
                mainFrame.repaint();
                mainFrame.revalidate();
            }
        });
    }
}
