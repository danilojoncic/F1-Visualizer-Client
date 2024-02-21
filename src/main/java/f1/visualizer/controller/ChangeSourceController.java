package f1.visualizer.controller;

import f1.visualizer.utils.Constants;
import f1.visualizer.view.MainFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeSourceController {
    private MainFrame mainFrame;

    public ChangeSourceController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListener();
    }
    private void attachListener(){
        mainFrame.getMenuPanel().getConfirmOriginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Constants.url = mainFrame.getMenuPanel().getChooseOriginComboBox().getSelectedItem().toString();
                mainFrame.getMenuPanel().getChooseOriginComboBox().setBackground(Color.green);
            }
        });
    }
}
