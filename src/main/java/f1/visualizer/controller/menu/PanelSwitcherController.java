package f1.visualizer.controller.menu;

import f1.visualizer.view.DrawingPanel;
import f1.visualizer.view.MainFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelSwitcherController {
    private MainFrame mainFrame;

    public PanelSwitcherController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListeners();
    }

    private void attachListeners(){
        mainFrame.getMenuPanel().getWatchRaceReplayButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String circuit = mainFrame.getMenuPanel().getRaceSelectionRace().getSelectedItem().toString();
                circuit = circuit.replaceAll(" ","_");
                System.out.println("Pressed watch replay button!");
                mainFrame.remove(mainFrame.getMenuPanel());
                mainFrame.setDrawingPanel(new DrawingPanel(circuit));
                mainFrame.add(mainFrame.getDrawingPanel());
                mainFrame.add(mainFrame.getDebugPanel(), BorderLayout.SOUTH);
                mainFrame.repaint();
                mainFrame.revalidate();
            }
        });
    }
}
