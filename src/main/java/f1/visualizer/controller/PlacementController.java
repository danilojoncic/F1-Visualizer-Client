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
                mainFrame.getDrawingPanel().setXOffset(mainFrame.getDrawingPanel().getXOffset()-50);
                mainFrame.repaint();
                mainFrame.revalidate();
                System.out.println("X offset -");

            }
        });

        mainFrame.getPullYOffsetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getDrawingPanel().setYOffset(mainFrame.getDrawingPanel().getYOffset()-50);
                mainFrame.repaint();
                mainFrame.revalidate();
                System.out.println("Y offset -");

            }
        });

        mainFrame.getPushXOffsetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getDrawingPanel().setXOffset(mainFrame.getDrawingPanel().getXOffset()+50);
                mainFrame.repaint();
                mainFrame.revalidate();
                System.out.println("X offset +");
            }
        });

        mainFrame.getPushYOffsetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getDrawingPanel().setYOffset(mainFrame.getDrawingPanel().getYOffset()+50);
                mainFrame.repaint();
                mainFrame.revalidate();
                System.out.println("Y offset +");

            }
        });

    }
}
