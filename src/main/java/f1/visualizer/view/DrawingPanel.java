package f1.visualizer.view;

import f1.visualizer.controller.DataFetcher;
import f1.visualizer.wrappers.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.List;

public class DrawingPanel extends JPanel {
    private List<Position> positions = DataFetcher.fetchDriverLocation(81);
    private int xOffset;
    private int yOffset;
    private Point screenCenter = new Point();
    private Point raceTrackCenter;
    private int scaleFactor = 8;

    public DrawingPanel() {
        applyScaleFactor();
        applyOffset();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintGrid(g);
        paintCircuit(g);
    }

    private void applyScaleFactor() {
        for (Position position : positions) {
            position.setX(position.getX() / scaleFactor);
            position.setY(position.getY() / scaleFactor);
        }
    }

    private void applyOffset() {
        int xTotal = 0;
        int yTotal = 0;
        for (Position p : positions) {
            xTotal += p.getX();
            yTotal += p.getY();
        }
        raceTrackCenter = new Point(xTotal / positions.size(), yTotal / positions.size());
        Dimension panelSize = getSize();
        screenCenter = new Point(panelSize.width / 2, panelSize.height / 2);
        xOffset = screenCenter.x - raceTrackCenter.x;
        yOffset = screenCenter.y - raceTrackCenter.y;
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Drawing Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            DrawingPanel drawingPanel = new DrawingPanel();
            frame.add(drawingPanel);

            frame.setVisible(true);
        });
    }
}
