package f1.visualizer.controller;

import f1.visualizer.view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlacementController {
    MainFrame mainFrame;

    public PlacementController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListner();
    }

    private void attachListner(){
        mainFrame.getPullXOffsetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getDrawingPanel().pullX();
                mainFrame.repaint();
                mainFrame.revalidate();
            }
        });

        mainFrame.getPullYOffsetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getDrawingPanel().pullY();
                mainFrame.repaint();
                mainFrame.revalidate();
            }
        });

        mainFrame.getPushXOffsetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getDrawingPanel().pushX();
                mainFrame.repaint();
                mainFrame.revalidate();
            }
        });

        mainFrame.getPushYOffsetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getDrawingPanel().pushY();
                mainFrame.repaint();
                mainFrame.revalidate();
            }
        });

    }
}
