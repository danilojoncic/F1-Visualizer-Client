package f1.visualizer.controller.debug;

import f1.visualizer.view.MainFrame;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlacementController {
    MainFrame mainFrame;

    public PlacementController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListner();
    }

    private void attachListner(){
        mainFrame.getDebugPanel().getXSlider().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println("Changed x");

                mainFrame.getDrawingPanel().pushX(mainFrame.getDebugPanel().getXSlider().getValue());
                mainFrame.repaint();
                mainFrame.revalidate();
            }
        });

        mainFrame.getDebugPanel().getYSlider().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println("Changed y");
                mainFrame.getDrawingPanel().pushY(mainFrame.getDebugPanel().getYSlider().getValue());
                mainFrame.repaint();
                mainFrame.revalidate();
            }
        });
    }
}
