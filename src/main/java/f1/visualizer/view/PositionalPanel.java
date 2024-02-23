package f1.visualizer.view;

import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Data
public class PositionalPanel extends JPanel {

    JButton btnUp;
    JButton btnDown;
    JButton btnLeft;
    JButton btnRight;
    JButton btnX = new JButton("X");
    public PositionalPanel() {
        init();
    }
    private void init(){
         btnUp = new JButton("+");
         btnDown = new JButton("-");
         btnLeft = new JButton("-");
         btnRight = new JButton("+");
         btnX = new JButton("X");
        setLayout(new GridLayout(3,3));
        setBackground(Color.WHITE);
        add(new JLabel());
        add(btnUp);
        add(new JLabel());
        add(btnLeft);
        add(btnX);
        add(btnRight);
        add(new JLabel());
        add(btnDown);
        add(new JLabel());
    }
}
