package f1.visualizer.controller;

import f1.visualizer.view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelSwitcherController {
    private MainFrame mainFrame;

    public PanelSwitcherController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListners();
    }

    private void attachListners(){
        mainFrame.getMenuPanel().getWatchRaceReplayButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.remove(mainFrame.getMenuPanel());
                mainFrame.add(mainFrame.getDrawingPanel());
                mainFrame.add(mainFrame.getDebugPanel());
                mainFrame.repaint();
                mainFrame.revalidate();
            }
        });
    }
}
