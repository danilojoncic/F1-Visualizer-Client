package f1.visualizer.view;

import f1.visualizer.controller.DataFetcher;
import f1.visualizer.wrappers.Position;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

@Data
public class DrawingPanel extends JPanel {
    private List<Position> positions;
    private int xOffset;
    private int yOffset;
    private int screenCenter;
    private int raceTrackCenter;
    private int scaleFactor;

    public DrawingPanel() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }
}
