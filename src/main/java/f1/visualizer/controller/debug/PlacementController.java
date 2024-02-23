package f1.visualizer.controller.debug;

import f1.visualizer.response_model.DriverArbitraryPosition;
import f1.visualizer.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlacementController {
    MainFrame mainFrame;

    public PlacementController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListener();
    }

    private void attachListener() {
        mainFrame.getDrawingPanel().getPositionalPanel().getBtnUp().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveDrivers(0, -5);
            }
        });
        mainFrame.getDrawingPanel().getPositionalPanel().getBtnDown().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveDrivers(0, 5);
            }
        });

        mainFrame.getDrawingPanel().getPositionalPanel().getBtnLeft().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveDrivers(-5, 0);
            }
        });

        mainFrame.getDrawingPanel().getPositionalPanel().getBtnRight().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveDrivers(5, 0);
            }
        });
    }

    private void moveDrivers(int deltaX, int deltaY) {
        System.out.println("TRIGGERED");
        mainFrame.getDrawingPanel().changeCordinates(deltaX,deltaY);
        mainFrame.getDrawingPanel().repaint();
        mainFrame.getDrawingPanel().revalidate();
    }
}
