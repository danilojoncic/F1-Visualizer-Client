package f1.visualizer.view;

import f1.visualizer.controller.CoordinateReader;
import f1.visualizer.wrappers.Position;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.util.List;

@Data
public class DrawingPanel extends JPanel {
    private List<Position> positions;
    private int xOffset = 0;
    private int yOffset = 0;
    private double rotationAngle = 0;
    private Point screenCenter = new Point();
    private double scaleFactor; // Adjusted scaleFactor
    private boolean initialized = false; // Flag to check if initialization is done

    public DrawingPanel() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!initialized) {
            // Initialize screen center here, after the component has been sized
            screenCenter.x = getWidth() / 2;
            screenCenter.y = getHeight() / 2;
            initialized = true; // Mark initialization as done
            try {
                positions = CoordinateReader.readCoordinatesFromFile("C:\\Users\\jonci\\Desktop\\front\\F1 Visualizer Client\\src\\main\\java\\f1\\visualizer\\race_tracks\\sg-2008.json");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            scaleFactor = calculateScaleFactor(); // Calculate scaleFactor
        }
        paintGrid(g);
        paintCircuit(g);
    }

    private double calculateScaleFactor() {
        // Calculate the scale factor based on the panel size and the range of coordinates
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;
        for (Position position : positions) {
            maxX = Math.max(maxX, position.getX());
            maxY = Math.max(maxY, position.getY());
        }
        double maxDimension = Math.max(maxX, maxY);
        return Math.min(getWidth(), getHeight()) / maxDimension;
    }

    private Shape createCircuitShape() {
        GeneralPath path = new GeneralPath();
        Position firstPosition = positions.get(0);
        path.moveTo(scaleCoordinate(firstPosition.getX()), scaleCoordinate(firstPosition.getY()));
        for (int i = 1; i < positions.size(); i++) {
            Position position = positions.get(i);
            path.lineTo(scaleCoordinate(position.getX()), scaleCoordinate(position.getY()));
        }
        path.closePath();
        return path;
    }

    private double scaleCoordinate(double coordinate) {
        return coordinate * scaleFactor;
    }

    private void paintCircuit(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setColor(Color.BLUE);
        Shape circuitShape = createCircuitShape();

        // Create a transformation to rotate the shape around the screen center
        AffineTransform transform = new AffineTransform();
        transform.rotate(rotationAngle, screenCenter.x, screenCenter.y); // Rotate around the screen center

        // Apply the transformation
        Shape transformedShape = transform.createTransformedShape(circuitShape);

        // Debugging statements
        System.out.println("Transformed shape bounds: " + transformedShape.getBounds());

        // Draw the transformed shape
        g2d.fill(transformedShape);
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
