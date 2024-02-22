package f1.visualizer.controller.menu;

import f1.visualizer.view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackController {
    private MainFrame mainFrame;

    public BackController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListener();
    }
    private void attachListener(){
        mainFrame.getDebugPanel().getBackToMenuButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.remove(mainFrame.getDrawingPanel());
                mainFrame.remove(mainFrame.getDebugPanel());
                mainFrame.add(mainFrame.getMenuPanel());
                mainFrame.repaint();
                mainFrame.revalidate();
            }
        });
    }
}
