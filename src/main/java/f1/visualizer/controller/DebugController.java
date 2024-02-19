package f1.visualizer.controller;

import f1.visualizer.view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DebugController {
    private MainFrame mainFrame;

    public DebugController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListners();
    }

    private void attachListners(){
        mainFrame.getDebug().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.getDrawingPanel().setDebug(!mainFrame.getDrawingPanel().isDebug());
                mainFrame.repaint();
                mainFrame.revalidate();
            }
        });
    }
}
