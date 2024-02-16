package f1.visualizer.view;

import f1.visualizer.controller.DataFetcher;
import f1.visualizer.wrappers.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.List;

public class DrawingPanel extends JPanel {
    private List<Position> positions;
    private int xOffset;
    private int yOffset;
    private Point screenCenter = new Point();
    private int scaleFactor = 8;
    private boolean initialized = false; // Flag to check if initialization is done

    public DrawingPanel() {
        positions = DataFetcher.fetchDriverLocation(81);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!initialized) {
            // Initialize screen center here, after the component has been sized
            screenCenter.x = getWidth() / 2;
            screenCenter.y = getHeight() / 2;
            setUpValues();
            initialized = true; // Mark initialization as done
        }
        paintGrid(g);
        paintCircuit(g);
    }

    private void setUpValues() {
        int totalX = 0;
        int totalY = 0;
        for (Position position : positions) {
            position.setX(position.getX() / scaleFactor);
            position.setY(position.getY() / scaleFactor);
            totalX += position.getX();
            totalY += position.getY();
        }
        int raceTrackCenterX = totalX / positions.size();
        int raceTrackCenterY = totalY / positions.size();
        xOffset = screenCenter.x - raceTrackCenterX;
        yOffset = screenCenter.y - raceTrackCenterY;

        for (Position position : positions) {
            position.setX(position.getX() + xOffset);
            position.setY(position.getY() + yOffset);
        }
    }

    private void paintCircuit(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setColor(Color.BLUE);
        for (int i = 0; i < positions.size() - 1; i++) {
            Position p1 = positions.get(i);
            Position p2 = positions.get(i + 1);
            g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        }
    }

    private void paintGrid(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setColor(Color.LIGHT_GRAY);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        int numLinesX = panelWidth / 50 + 1;
        int numLinesY = panelHeight / 50 + 1;

        int startX = (panelWidth % 50) / 2;
        int startY = (panelHeight % 50) / 2;

        for (int i = 0; i < numLinesX; i++) {
            int x = startX + i * 50;
            g2d.draw(new Line2D.Double(x, 0, x, panelHeight));
        }

        for (int i = 0; i < numLinesY; i++) {
            int y = startY + i * 50;
            g2d.draw(new Line2D.Double(0, y, panelWidth, y));
        }
    }
}
