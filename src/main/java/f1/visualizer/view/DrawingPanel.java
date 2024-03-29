package f1.visualizer.view;

import f1.visualizer.controller.debug.PlacementController;
import f1.visualizer.utils.CoordinateReader;
import f1.visualizer.utils.Converter;
import f1.visualizer.utils.DataFetcher;
import f1.visualizer.response_model.DriverArbitraryPosition;
import f1.visualizer.response_model.GPSCircuitPosition;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Data
public class DrawingPanel extends JPanel {
    private List<GPSCircuitPosition> GPSCircuitPositions;
    private double scaleFactor; // Adjusted scaleFactor
    private boolean initialized = false; // Flag to check if initialization is done
    private Shape circuitShape;
    private boolean debug = true;
    private double rotationAngle = 0;
    private List<DriverArbitraryPosition> oneDriversPositions = DataFetcher.fetchDriverLocation(1);
    private int scale = 1;
    private int currentIndex = 1;
    private int delay = 200; // Delay in milliseconds between each frame
    private Timer timer; // Total number of frames for the animation
    private DriverArbitraryPosition startDriverArbitraryPosition; // Initial position of the driver for animation
    private DriverArbitraryPosition endDriverArbitraryPosition; // Final position of the driver for animation
    private boolean animationInProgress = false;
    private Point screenCenter = new Point();
    private PositionalPanel positionalPanel = new PositionalPanel();
    private boolean animation = false;
    private int currIndex = 0;

    public DrawingPanel(String circuit) {
        extracted();
        try {
            GPSCircuitPositions = CoordinateReader.readCoordinatesFromFile("C:\\Users\\jonci\\Desktop\\front\\F1 Visualizer Client\\src\\main\\java\\f1\\visualizer\\race_tracks\\"+circuit+".json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void extracted() {
        add(positionalPanel, BorderLayout.NORTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!initialized) {
            initialize();
        }
        paintCircuit(g);
        paintDriver(g);
        positionalPanel.setVisible(debug);
        if(debug){
            paintGrid(g);
        }
        if(animation){
            animate();
        }
    }

    private void animate() {
        // Check if animation reached the end
        if (currIndex + 1 >= oneDriversPositions.size()) {
            animation = false;
            return;
        }
        DriverArbitraryPosition currentPos = oneDriversPositions.get(currIndex);
        DriverArbitraryPosition nextPos = oneDriversPositions.get(currIndex + 1);
        long aMili = Converter.stringToDate(currentPos.getDate()).getTime();
        long bMili = Converter.stringToDate(nextPos.getDate()).getTime();
        long timeDiff = bMili - aMili;
        int currentX = (int) (currentPos.getX() + (nextPos.getX() - currentPos.getX()) * timeDiff*10000);
        int currentY = (int) (currentPos.getY() + (nextPos.getY() - currentPos.getY()) * timeDiff*10000);

        Graphics2D g2d = (Graphics2D) getGraphics();
        g2d.setColor(Color.BLUE);
        g2d.fillOval(currentX - 5, currentY - 5, 10, 10);

        g2d.setColor(Color.RED);
        g2d.fillOval(nextPos.getX() - 5, nextPos.getY() - 5, 10, 10);

        int frameTime = 1000 / 60;
        int timeUntilNextFrame = Math.max(0, frameTime);
        Timer timer = new Timer(timeUntilNextFrame, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animate();
            }
        });
        timer.setRepeats(false);
        timer.start();
        currIndex++;
    }








    private void fixPos(){
        for (GPSCircuitPosition GPSCircuitPosition : GPSCircuitPositions) {
            int x = (int)(GPSCircuitPosition.getX()*100000 - (double)((int) GPSCircuitPosition.getX()*100000));
            int y = (int)(GPSCircuitPosition.getY()*100000 - (double)((int) GPSCircuitPosition.getY()*100000));
            GPSCircuitPosition.setX(x);
            GPSCircuitPosition.setY(y);
        }
    }
    private void scaleDriverPositions() {
        Rectangle2D circuitBounds = circuitShape.getBounds2D();
        Rectangle2D driversBounds = getDriversBoundingBox();

        double scaleX = circuitBounds.getWidth() / driversBounds.getWidth();
        double scaleY = circuitBounds.getHeight() / driversBounds.getHeight();
        scaleFactor = Math.min(scaleX, scaleY);
        for (DriverArbitraryPosition position : oneDriversPositions) {
            double scaledX = circuitBounds.getX() + (position.getX() - driversBounds.getX()) * scaleFactor;
            double scaledY = circuitBounds.getY() + (position.getY() - driversBounds.getY()) * scaleFactor;
            position.setX((int) scaledX);
            position.setY((int) scaledY);
        }
    }

    private Rectangle2D getDriversBoundingBox() {
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;

        for (DriverArbitraryPosition position : oneDriversPositions) {
            minX = Math.min(minX, position.getX());
            minY = Math.min(minY, position.getY());
            maxX = Math.max(maxX, position.getX());
            maxY = Math.max(maxY, position.getY());
        }

        return new Rectangle2D.Double(minX, minY, maxX - minX, maxY - minY);
    }

    private void setUpDriverPos() {
        scaleDriverPositions();
        int totalX = 0;
        int totalY = 0;
        for (DriverArbitraryPosition position : oneDriversPositions) {
            totalX += position.getX();
            totalY += position.getY();
        }
        int centerX = totalX / oneDriversPositions.size();
        int centerY = totalY / oneDriversPositions.size();
        int offsetX = (int) (screenCenter.getX() - centerX);
        int offsetY = (int) (screenCenter.getY() - centerY);
        for (DriverArbitraryPosition position : oneDriversPositions) {
            position.setX((int) (position.getX() + offsetX));
            position.setY((int) (position.getY() + offsetY));
        }
    }

    private void initialize() {
        screenCenter.x = getWidth() / 2;
        screenCenter.y = getHeight() / 2;
        initialized = true;
        fixPos();
        circuitShape = createCircuit();
        transformCircuit();
        setBackground(Color.WHITE);
        setUpDriverPos();
    }

    private void transformCircuit() {
        Rectangle bounds = circuitShape.getBounds();
        double scaleX = ((double) getWidth() / bounds.getWidth()) * 0.9; // Scale factor reduced by 10%
        double scaleY = ((double) getHeight() / bounds.getHeight()) * 0.9; // Scale factor reduced by 10%
        double scale = Math.min(scaleX, scaleY);
        double translateX = (getWidth() - scale * bounds.getWidth()) / 2 - scale * bounds.getX();
        double translateY = (getHeight() - scale * bounds.getHeight()) / 2 - scale * bounds.getY();
        AffineTransform transform = new AffineTransform();
        transform.translate(translateX, translateY);
        transform.rotate(Math.toRadians(rotationAngle), screenCenter.getX(), screenCenter.getY()); // Rotate around screen center
        transform.scale(scale, scale);
        circuitShape = transform.createTransformedShape(circuitShape);
    }

    private Shape createCircuit(){
        GeneralPath generalPath = new GeneralPath();
        generalPath.moveTo(GPSCircuitPositions.get(0).getX(), GPSCircuitPositions.get(0).getY());
        for (int i = 1; i < GPSCircuitPositions.size(); i++) {
            generalPath.lineTo(GPSCircuitPositions.get(i).getX(), GPSCircuitPositions.get(i).getY());
        }
        generalPath.closePath();
        return generalPath;
    }

    private void paintCircuit(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        AffineTransform oldTransform = g2d.getTransform(); // Save the original transform
        g2d.rotate(Math.toRadians(rotationAngle), screenCenter.getX(), screenCenter.getY());
        Stroke outsideStroke = new BasicStroke(
                27, // Adjust the width of the outside line
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER,
                10, // Adjust the dash length to control the size of red and white stripes
                new float[] {15, 15}, // Adjust the dash pattern to control the size of red and white stripes
                0); // Adjust the dash phase if necessary
        g2d.setStroke(outsideStroke);
        g2d.draw(circuitShape);
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
        AffineTransform oldTransform = g2d.getTransform(); // Save the original transform
        g2d.rotate(Math.toRadians(rotationAngle), screenCenter.getX(), screenCenter.getY()); // Rotate around screen center
        for (DriverArbitraryPosition position : oneDriversPositions) {
            if (debug) {
                g2d.setStroke(new BasicStroke(5));
                g2d.drawOval(position.getX()-5, position.getY()-5, 5, 5);
            }
        }
        int totalx = 0;
        int totaly = 0;
        for (DriverArbitraryPosition position : oneDriversPositions) {
            totalx+=position.getX();
            totaly+=position.getY();
        }
        g2d.setColor(Color.RED);
        g2d.drawOval(totalx/oneDriversPositions.size()-5,totaly/oneDriversPositions.size()-5,5,5);
        g2d.setTransform(oldTransform); // Restore the original transform
    }
    public void changeCordinates(int x,int y){
        for (DriverArbitraryPosition position : oneDriversPositions) {
            position.setX(position.getX()+x);
            position.setY(position.getY()+y);
        }
    }
}
