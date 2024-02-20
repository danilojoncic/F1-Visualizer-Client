package f1.visualizer.view;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class MenuPanel extends JPanel {
    private JLabel title = new JLabel("F1 Visualizer 0.0.3");
    private JButton liveTimingButton = new JButton("Live Data");
    private JButton watchRaceReplayButton = new JButton("Race Replay");
    private JButton exitButton = new JButton("Exit");
    private JButton aboutButton = new JButton("About");
    private JComboBox<String> chooseOriginComboBox= new JComboBox<>();
    private JButton confirmOriginButton = new JButton("Confirm origin");
    private JButton resetButton = new JButton("Reset");
    private JComboBox<String> raceSelectionRace = new JComboBox<>();

    public MenuPanel() {
        init();
    }

    private void init() {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(title, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        addComponent(liveTimingButton, gbc);
        addComponent(raceSelectionRace, gbc);
        addComponent(watchRaceReplayButton, gbc);
        addComponent(aboutButton, gbc);
        addComponent(chooseOriginComboBox, gbc);
        addComponent(confirmOriginButton, gbc);
        addComponent(resetButton, gbc);
        addComponent(exitButton, gbc);
    }

    private void addComponent(Component component, GridBagConstraints gbc) {
        gbc.gridy++;
        add(component, gbc);
    }
}
