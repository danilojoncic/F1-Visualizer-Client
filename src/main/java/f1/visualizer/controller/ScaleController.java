package f1.visualizer.controller;

import f1.visualizer.view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScaleController {
    MainFrame mainFrame;

    public ScaleController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListners();
    }

    private void attachListners(){
        mainFrame.getIncreaseScaleButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getDrawingPanel().setScaleFactor(mainFrame.getDrawingPanel().getScaleFactor()*2);
                mainFrame.repaint();
                mainFrame.revalidate();
                System.out.println("Scale +");
            }
        });

        mainFrame.getDecreaseScaleButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getDrawingPanel().setScaleFactor(mainFrame.getDrawingPanel().getScaleFactor()/2);
                mainFrame.repaint();
                mainFrame.revalidate();
                System.out.println("Scale -");
            }
        });
    }




}
