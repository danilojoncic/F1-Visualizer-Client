package f1.visualizer.controller.debug;

import f1.visualizer.view.MainFrame;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RotationController {
    private MainFrame mainFrame;

    public RotationController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListners();
    }

    private void attachListners() {
        mainFrame.getDebugPanel().getRotationSlider().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                mainFrame.getDrawingPanel().setRotationAngle(mainFrame.getDebugPanel().getRotationSlider().getValue());
                mainFrame.repaint();
                mainFrame.revalidate();
            }
        });
    }
}
