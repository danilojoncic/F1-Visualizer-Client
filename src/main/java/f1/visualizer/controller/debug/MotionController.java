package f1.visualizer.controller.debug;

import f1.visualizer.view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MotionController {
    private MainFrame mainFrame;

    public MotionController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListner();
    }


    private void attachListner(){
        mainFrame.getDebugPanel().getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getDrawingPanel().startAnimation();
                mainFrame.repaint();
                mainFrame.revalidate();
            }
        });

        mainFrame.getDebugPanel().getPauseButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getDrawingPanel().startAnimation();
                mainFrame.repaint();
                mainFrame.revalidate();
            }
        });
    }

}
