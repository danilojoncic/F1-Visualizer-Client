package f1.visualizer.view;

import f1.visualizer.controller.CoordinateReader;
import f1.visualizer.controller.DataFetcher;
import f1.visualizer.wrappers.DriverPosition;
import f1.visualizer.wrappers.Position;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.util.List;

@Data
public class DrawingPanel extends JPanel {
    private List<Position> positions;
    private double scaleFactor; // Adjusted scaleFactor
    private boolean initialized = false; // Flag to check if initialization is done
    private Shape circuitShape;
    private boolean debug = true;
    private List<DriverPosition> oneDriversPositions = DataFetcher.fetchDriverLocation(81);
    private int scale = 1;
    private int currentFrame = 0;
    private final int totalFrames = 100; // Total number of frames for the animation
    private DriverPosition startDriverPosition; // Initial position of the driver for animation
    private DriverPosition endDriverPosition; // Final position of the driver for animation


    private Point screenCenter = new Point();

    public DrawingPanel() {
        // Load positions from file
        try {
            positions = CoordinateReader.readCoordinatesFromFile("C:\\Users\\jonci\\Desktop\\front\\F1 Visualizer Client\\src\\main\\java\\f1\\visualizer\\race_tracks\\sg-2008.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void fixPos(){
        for (Position position : positions) {
            int x = (int)(position.getX()*100000 - (double)((int)position.getX()*100000));
            int y = (int)(position.getY()*100000 - (double)((int)position.getY()*100000));
            position.setX(x);
            position.setY(y);
        }
    }

    public void pushX(){
        for (DriverPosition position : oneDriversPositions) {
            position.setX(position.getX()+10);
        }
    }
    public void pushY(){
        for (DriverPosition position : oneDriversPositions) {
            position.setY(position.getY()+10);
        }
    }
    public void pullX(){
        for (DriverPosition position : oneDriversPositions) {
            position.setX(position.getX()-10);
        }
    }
    public void pullY(){
        for (DriverPosition position : oneDriversPositions) {
            position.setY(position.getY()-10);
        }
    }


    private void setUpDriverPos() {
        // Step 1: Scale all driver positions by a factor of 10
        for (DriverPosition position : oneDriversPositions) {
            position.setX((int)(((float)position.getX()) / 12.5));
            position.setY((int)(((float)position.getY()) / 11.89));
        }

        // Step 2: Calculate the center point of all scaled driver positions
        int totalX = 0;
        int totalY = 0;
        for (DriverPosition position : oneDriversPositions) {
            totalX += position.getX();
            totalY += position.getY();
        }
        int centerX = totalX / oneDriversPositions.size();
        int centerY = totalY / oneDriversPositions.size();

        // Print the cumulative center of the drivers' positions
        // Step 3: Calculate the offset needed to center the driver positions relative to the screen center
        int offsetX = (int) screenCenter.getX() - centerX;
        int offsetY = (int) screenCenter.getY() - centerY;

        // Step 4: Apply the offset to each driver position
        for (DriverPosition position : oneDriversPositions) {
            position.setX(position.getX() + offsetX+25);
            position.setY(position.getY() + offsetY+30);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!initialized) {
            initialize();
        }
        paintCircuit(g);
        paintDriver(g);
        if(debug){
            paintGrid(g);
        }
    }

    private void initialize() {
        screenCenter.x = getWidth() / 2;
        screenCenter.y = getHeight() / 2;
        initialized = true; // Mark initialization as done
        fixPos();
        circuitShape = createCircuit();
        transformCircuit();
        setBackground(Color.WHITE);
        setUpDriverPos();
    }


    private void transformCircuit(){
        Rectangle bounds = circuitShape.getBounds();
        double scaleX = (double) getWidth() / bounds.getWidth();
        double scaleY = (double) getHeight() / bounds.getHeight();
        double scale = Math.min(scaleX, scaleY);
        double translateX = (getWidth() - scale * bounds.getWidth()) / 2 - scale * bounds.getX();
        double translateY = (getHeight() - scale * bounds.getHeight()) / 2 - scale * bounds.getY();
        AffineTransform transform = new AffineTransform();
        transform.translate(translateX, translateY);
        transform.scale(scale, scale);
        circuitShape = transform.createTransformedShape(circuitShape);
    }
    private Shape createCircuit(){
        GeneralPath generalPath = new GeneralPath();
        generalPath.moveTo(positions.get(0).getX(),positions.get(0).getY());
        for (int i = 1; i < positions.size(); i++) {
            generalPath.lineTo(positions.get(i).getX(), positions.get(i).getY());
        }
        generalPath.closePath();
        return generalPath;
    }

    private void paintCircuit(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        Stroke outsideStroke = new BasicStroke(
                27, // Adjust the width of the outside line
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER,
                10, // Adjust the dash length to control the size of red and white stripes
                new float[] {15, 15}, // Adjust the dash pattern to control the size of red and white stripes
                0); // Adjust the dash phase if necessary
        g2d.setStroke(outsideStroke);
        g2d.draw(circuitShape);

        // Set the track color and draw it
        g2d.setColor(Color.BLACK);
        Stroke trackStroke = new BasicStroke(22); // Adjust the width of the track line
        g2d.setStroke(trackStroke);
        g2d.draw(circuitShape);
    }


    private void paintGrid(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.setStroke(new BasicStroke(2));
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

    private void paintDriver(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GREEN);
            // Animation finished or not started, draw the driver at its final position
            for (DriverPosition position : oneDriversPositions) {
                if (oneDriversPositions.indexOf(position) % 10 == 0) {
                    g2d.setStroke(new BasicStroke(5));
                    g2d.drawOval(position.getX()-5, position.getY()-5, 5, 5);
                }
            }
        int totalx = 0;
        int totaly = 0;
        for (DriverPosition position : oneDriversPositions) {
            totalx+=position.getX();
            totaly+=position.getY();
        }
        g2d.setColor(Color.RED);
        g2d.drawOval(totalx/oneDriversPositions.size(),totaly/oneDriversPositions.size(),5,5);
    }

    // Method to start the animation
    // Method to start the animation




}

