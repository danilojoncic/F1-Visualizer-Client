package f1.visualizer.view;

import f1.visualizer.response_model.Meetings;
import f1.visualizer.utils.DataFetcher;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Data
public class MenuPanel extends JPanel {
    private JLabel title = new JLabel("F1 Visualizer 0.0.4");
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
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        title.setFont(new Font(Font.MONOSPACED,Font.BOLD,32));
        gbc.gridwidth = 3;
        add(title, gbc);

        // Live Timing Button
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy++;
        liveTimingButton.setMaximumSize(new Dimension(150, 30)); // Set maximum size
        add(liveTimingButton, gbc);

        // Race Selection ComboBox
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        raceSelectionRace.setPreferredSize(new Dimension(150, 30)); // Set preferred size
        add(raceSelectionRace, gbc);

        // Race Replay Button
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        watchRaceReplayButton.setMaximumSize(new Dimension(150, 30)); // Set maximum size
        add(watchRaceReplayButton, gbc);

        // About Button
        gbc.gridy++;
        aboutButton.setMaximumSize(new Dimension(150, 30)); // Set maximum size
        add(aboutButton, gbc);

        // Choose Origin ComboBox
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        chooseOriginComboBox.setMaximumSize(new Dimension(150, 30)); // Set maximum size
        add(chooseOriginComboBox, gbc);

        // Confirm Origin Button
        gbc.gridy++;
        confirmOriginButton.setMaximumSize(new Dimension(150, 30)); // Set maximum size
        add(confirmOriginButton, gbc);

        // Reset Button
        gbc.gridy++;
        resetButton.setMaximumSize(new Dimension(150, 30)); // Set maximum size
        add(resetButton, gbc);

        // Exit Button
        gbc.gridy++;
        exitButton.setMaximumSize(new Dimension(150, 30)); // Set maximum size
        add(exitButton, gbc);

        // Initialize components
        initRaceSelection();
        initOriginSelection();
    }


    private void addComponent(Component component, GridBagConstraints gbc) {
        gbc.gridy++;
        add(component, gbc);
    }


    private void initRaceSelection(){
        List<Meetings> allWeekends = DataFetcher.fetchAllMeetings();
        for(Meetings meetings: allWeekends){
            if(meetings.getMeeting_name().contains("Testing"))continue;
            raceSelectionRace.addItem(meetings.getMeeting_name());
        }
        //need to hardcode, missing in Open F1 api
        raceSelectionRace.addItem("Austrian Grand Prix");
    }


    private void initOriginSelection(){
        chooseOriginComboBox.addItem("https://api.openf1.org/v1");
        chooseOriginComboBox.addItem("Custom");
    }
}
