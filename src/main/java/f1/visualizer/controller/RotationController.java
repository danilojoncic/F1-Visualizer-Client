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
        mainFrame.getRotatePlus().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getDrawingPanel().setRotationAngle(mainFrame.getDrawingPanel().getRotationAngle() + Math.toRadians(10));
                mainFrame.repaint();
                mainFrame.revalidate();
                System.out.println("Rotation +");
            }
        });


        mainFrame.getRotateMinus().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getDrawingPanel().setRotationAngle(mainFrame.getDrawingPanel().getRotationAngle() - Math.toRadians(10));
                mainFrame.repaint();
                mainFrame.revalidate();
                System.out.println("Rotation -");

            }
        });
    }
}
