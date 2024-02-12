package f1.visualizer.controller;
import f1.visualizer.view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CordinatesController {
    private MainFrame mainFrame;

    public CordinatesController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListeners();
    }


    private void attachListeners(){
        mainFrame.getDecreaseScaleButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.decreaseScale();
                mainFrame.repaint();
                mainFrame.revalidate();
            }
        });

        mainFrame.getIncreaseScaleButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.increaseScale();
                mainFrame.repaint();
                mainFrame.revalidate();
            }
        });


        mainFrame.getPushXOffsetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.pushXOffset();
                mainFrame.repaint();
                mainFrame.revalidate();
            }
        });


        mainFrame.getPushYOffsetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.pushYOffset();
                mainFrame.repaint();
                mainFrame.revalidate();
            }
        });



        mainFrame.getPullYOffsetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.pullYOffset();
                mainFrame.repaint();
                mainFrame.revalidate();
            }
        });


        mainFrame.getPullXOffsetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.pullXOffset();
                mainFrame.repaint();
                mainFrame.revalidate();
            }
        });


    }


}
