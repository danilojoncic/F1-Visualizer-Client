package f1.visualizer.view;
import f1.visualizer.controller.CordinatesController;
import f1.visualizer.controller.DebugController;
import f1.visualizer.controller.RotationController;
import f1.visualizer.requests.Requests;
import f1.visualizer.wrappers.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

    private DebugController debugController;
    private RotationController rotationController;

    private JButton rotatePlus;
    private JButton rotateMinus;
    private double currentAngle;
    private double rotationAngle = 0;
    private boolean debugMode = true;
    private List<Point> circuit = new ArrayList<>();

    private JButton debug = new JButton("Debug OFF");
    private int gridOriginX = 0;
    private int arbiterX = 0;
    private int arbiterY = 0;
    private int gridOriginY = 0;
    private Point startPoint;
    private boolean started = false;
    private List<Position> positionList;
    private Point screenCenter = new Point(750, 400);
    private Point raceTrackCenter = new Point();
    private int offsetX;
    private int offsetY;
    private int scaleFactor = 8;
    private JPanel drawingPanel;
    JButton increaseScaleButton = new JButton("Scale +");
    JButton decreaseScaleButton = new JButton("Scale -");
    JButton pushXOffsetButton = new JButton("X +");
    JButton pushYOffsetButton = new JButton("Y +");
    JButton pullXOffsetButton = new JButton("X -");
    JButton pullYOffsetButton = new JButton("Y -");

    private CordinatesController cordinatesController;

    public MainFrame() {
        setTitle("F1 Visualizer 0.0.2");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (!started) {
                    fetchData();
                }
                drawTheCenterOfTheCircuit(g);
                drawGrid(g);
                drawPositionPoint(g);
                started = true;
            }
        };
        rotatePlus = new JButton("Rotate +");
        rotateMinus = new JButton("Rotate -");

        getContentPane().add(drawingPanel);
        JPanel buttonPanel = new JPanel(new GridLayout(3, 3));
        buttonPanel.add(increaseScaleButton);
        buttonPanel.add(pushYOffsetButton);
        buttonPanel.add(pushXOffsetButton);
        buttonPanel.add(decreaseScaleButton);
        buttonPanel.add(pullYOffsetButton);
        buttonPanel.add(pullXOffsetButton);
        buttonPanel.add(debug);
        buttonPanel.add(rotatePlus);
        buttonPanel.add(rotateMinus);

        // Add the button panel to the frame
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        cordinatesController = new CordinatesController(this);
        debugController = new DebugController(this);
        rotationController = new RotationController(this);
    }

    private void drawTheCenterOfTheCircuit(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        int positionCount = 0;
        int positionSumX = 0;
        int positionSumY = 0;

        for (Position position : positionList) {
            int xPos = position.getX() / scaleFactor;
            int yPos = position.getY() / scaleFactor;
            positionSumX += xPos;
            positionSumY += yPos;
            positionCount++;
        }

        int trueX = positionSumX / positionCount;
        int trueY = positionSumY / positionCount;

        raceTrackCenter.x = trueX;
        raceTrackCenter.y = trueY;
        offsetX = screenCenter.x - raceTrackCenter.x;
        offsetY = screenCenter.y - raceTrackCenter.y;

        raceTrackCenter.x = screenCenter.x + arbiterX;
        raceTrackCenter.y = screenCenter.y + arbiterY;

        g2d.drawRect(raceTrackCenter.x, raceTrackCenter.y, 10, 10);
        g2d.drawString("Central point of circuit X: " + raceTrackCenter.x + 5  + " Y: " + raceTrackCenter.y + 5 , raceTrackCenter.x + 10, raceTrackCenter.y - 10);
    }

    private void fetchData() {
        Requests requests = new Requests();
        positionList = requests.fetchDriverLocation();
        System.out.println("Fetched data: " + positionList);
        for (Position position : positionList) {
            System.out.println(position.getX() + " " + position.getY());
        }
    }

    private void drawGrid(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.CYAN);
        g2d.drawString("GLOBAL TIME : " + "REPLACE",750,100);
        g2d.setColor(Color.LIGHT_GRAY);



        for (int i = -2000 + gridOriginX; i <= getWidth() + gridOriginX + 1000; i += 50) {
            g2d.draw(new Line2D.Double(i, 0, i, getHeight()));
        }

        for (int i = -2000 + gridOriginY; i <= getHeight() + gridOriginY + 1000; i += 50) {
            g2d.draw(new Line2D.Double(0, i, getWidth(), i));
        }

        g2d.setColor(Color.MAGENTA);
        g2d.drawOval(screenCenter.x + gridOriginX - 10, screenCenter.y + gridOriginY - 10, 20, 20);
        g2d.drawString("X: " + screenCenter.x + " Y: " + screenCenter.y, screenCenter.x + gridOriginX + 10, screenCenter.y + gridOriginY);
    }

    private int giveActualX(Position position) {
        return (position.getX() / scaleFactor) + offsetX + arbiterX;
    }

    private int giveActualY(Position position) {
        return (position.getY() / scaleFactor) +  offsetY + arbiterY;
    }

    private int calculateDistanceFromRaceTrackCenter(int x, int y){
        if(x == raceTrackCenter.x){
            return (int)Math.abs(y - raceTrackCenter.y);
        }
        if(y == raceTrackCenter.y){
            return (int)Math.abs(x - raceTrackCenter.x);
        }
        int distance =(int) Math.sqrt(Math.pow(Math.abs(x - raceTrackCenter.x),2) + Math.pow(Math.abs(y - raceTrackCenter.y),2));
        return distance;
    }

    private void drawPositionPoint(Graphics g) {
        circuit.clear();
        if (positionList != null && !positionList.isEmpty()) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.BLUE);
            int radius = 8;
            int miniSectorCount = 1;

            // Save the current graphics transform before any modifications
            AffineTransform originalTransform = g2d.getTransform();
            for (Position position : positionList) {
                g2d.setColor(Color.BLUE);
                int actualPosX = giveActualX(position);
                int actualPosY = giveActualY(position);
                int distance = calculateDistanceFromRaceTrackCenter(actualPosX, actualPosY);

                double currentAngle = Math.atan2(actualPosY - raceTrackCenter.y, actualPosX - raceTrackCenter.x);
                double newAngle = currentAngle + rotationAngle;

                int newX = (int) (raceTrackCenter.x + distance * Math.cos(newAngle));
                int newY = (int) (raceTrackCenter.y + distance * Math.sin(newAngle));
                actualPosX = newX;
                actualPosY = newY;
                circuit.add(new Point(actualPosX,actualPosY));
                Polygon polygon = new Polygon();
                polygon.npoints = circuit.size();
                polygon.addPoint(actualPosX,actualPosY);
                g2d.setColor(Color.PINK);
                //polygon.translate(200,200);
                //g2d.drawPolygon(polygon);
                // debug drawing - adjust coordinates based on arbiter values
                if (debugMode && miniSectorCount % 75 == 0) {
                    g2d.setColor(Color.BLUE);
                    g2d.drawOval(actualPosX - radius, actualPosY - radius, 2 * radius, 2 * radius);
                    g2d.drawString(" " + miniSectorCount, actualPosX, actualPosY);
                    g2d.setColor(Color.GREEN);
                    g2d.drawLine(actualPosX, actualPosY, raceTrackCenter.x, raceTrackCenter.y);
                    g2d.setColor(Color.DARK_GRAY);
                    g2d.drawString("Distance : " + distance, actualPosX + 15, actualPosY + 15);
                    g2d.drawOval(raceTrackCenter.x - distance, raceTrackCenter.y - distance, distance * 2, distance * 2);
                }

                miniSectorCount++;
                if (positionList.indexOf(position) == 0) {
                    continue;
                }

                // previous position after applying arbiter values
                int previuosX = giveActualX(positionList.get(positionList.indexOf(position) - 1));
                int previousY = giveActualY(positionList.get(positionList.indexOf(position) - 1));
                int distanceForOld = calculateDistanceFromRaceTrackCenter(previuosX, previousY);
                double prevoiusAngle = Math.atan2(previousY - raceTrackCenter.y, previuosX - raceTrackCenter.x);
                double newForOldAngle = prevoiusAngle + rotationAngle;

                previuosX = (int) (raceTrackCenter.x + distanceForOld * Math.cos(newForOldAngle));
                previousY = (int) (raceTrackCenter.y + distanceForOld * Math.sin(newForOldAngle));

                // part where you draw the track - adjust coordinates based on arbiter values
                double angle = Math.atan2(
                        actualPosY - previousY,
                        actualPosX - previuosX
                );

                int radiusOffset = 2; // Adjust this value if necessary
                int lineLength = radius + radiusOffset;
                int destinationUpperX = actualPosX + (int) (lineLength * Math.cos(angle + Math.toRadians(90)));
                int destinationUpperY = actualPosY + (int) (lineLength * Math.sin(angle + Math.toRadians(90)));
                int destinationLowerX = actualPosX + (int) (lineLength * Math.cos(angle - Math.toRadians(90)));
                int destinationLowerY = actualPosY + (int) (lineLength * Math.sin(angle - Math.toRadians(90)));
                int originUpperX = previuosX + (int) (lineLength * Math.cos(angle + Math.toRadians(90)));
                int originUpperY = previousY + (int) (lineLength * Math.sin(angle + Math.toRadians(90)));
                int originLowerX = previuosX + (int) (lineLength * Math.cos(angle - Math.toRadians(90)));
                int originLowerY = previousY + (int) (lineLength * Math.sin(angle - Math.toRadians(90)));

                g2d.setColor(Color.BLACK);
                g2d.drawLine(originUpperX, originUpperY, destinationUpperX, destinationUpperY);
                g2d.setColor(Color.RED);
                g2d.drawLine(originLowerX, originLowerY, destinationLowerX, destinationLowerY);
            }

            // Reset the graphics transform to its original state
            g2d.setTransform(originalTransform);
        } else {
            System.out.println("No positions to draw.");
        }
    }



    public void increaseScale(){
        scaleFactor++;
    }

    public void decreaseScale(){
        scaleFactor--;
    }

    public void pushXOffset(){
        arbiterX += 100;
    }

    public void pushYOffset(){
        arbiterY += 100;
    }

    public void pullXOffset(){
        arbiterX -= 100;
    }
    public void pullYOffset(){
        arbiterY -= 100;
    }

    public int getGridOriginX() {
        return gridOriginX;
    }

    public void setGridOriginX(int gridOriginX) {
        this.gridOriginX = gridOriginX;
    }

    public int getGridOriginY() {
        return gridOriginY;
    }

    public void setGridOriginY(int gridOriginY) {
        this.gridOriginY = gridOriginY;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }

    public Point getScreenCenter() {
        return screenCenter;
    }

    public void setScreenCenter(Point screenCenter) {
        this.screenCenter = screenCenter;
    }

    public Point getRaceTrackCenter() {
        return raceTrackCenter;
    }

    public void setRaceTrackCenter(Point raceTrackCenter) {
        this.raceTrackCenter = raceTrackCenter;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public int getScaleFactor() {
        return scaleFactor;
    }

    public void setScaleFactor(int scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    public JPanel getDrawingPanel() {
        return drawingPanel;
    }

    public void setDrawingPanel(JPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }

    public JButton getIncreaseScaleButton() {
        return increaseScaleButton;
    }

    public void setIncreaseScaleButton(JButton increaseScaleButton) {
        this.increaseScaleButton = increaseScaleButton;
    }

    public JButton getDecreaseScaleButton() {
        return decreaseScaleButton;
    }

    public void setDecreaseScaleButton(JButton decreaseScaleButton) {
        this.decreaseScaleButton = decreaseScaleButton;
    }

    public JButton getPushXOffsetButton() {
        return pushXOffsetButton;
    }

    public void setPushXOffsetButton(JButton pushXOffsetButton) {
        this.pushXOffsetButton = pushXOffsetButton;
    }

    public JButton getPushYOffsetButton() {
        return pushYOffsetButton;
    }

    public void setPushYOffsetButton(JButton pushYOffsetButton) {
        this.pushYOffsetButton = pushYOffsetButton;
    }

    public JButton getPullXOffsetButton() {
        return pullXOffsetButton;
    }

    public void setPullXOffsetButton(JButton pullXOffsetButton) {
        this.pullXOffsetButton = pullXOffsetButton;
    }

    public JButton getPullYOffsetButton() {
        return pullYOffsetButton;
    }

    public void setPullYOffsetButton(JButton pullYOffsetButton) {
        this.pullYOffsetButton = pullYOffsetButton;
    }

    public CordinatesController getCordinatesController() {
        return cordinatesController;
    }

    public void setCordinatesController(CordinatesController cordinatesController) {
        this.cordinatesController = cordinatesController;
    }


    public int getArbiterX() {
        return arbiterX;
    }

    public void setArbiterX(int arbiterX) {
        this.arbiterX = arbiterX;
    }

    public int getArbiterY() {
        return arbiterY;
    }

    public void setArbiterY(int arbiterY) {
        this.arbiterY = arbiterY;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public JButton getDebug() {
        return debug;
    }

    public void setDebug(JButton debug) {
        this.debug = debug;
    }

    public DebugController getDebugController() {
        return debugController;
    }

    public void setDebugController(DebugController debugController) {
        this.debugController = debugController;
    }

    public RotationController getRotationController() {
        return rotationController;
    }

    public void setRotationController(RotationController rotationController) {
        this.rotationController = rotationController;
    }

    public JButton getRotatePlus() {
        return rotatePlus;
    }

    public void setRotatePlus(JButton rotatePlus) {
        this.rotatePlus = rotatePlus;
    }

    public JButton getRotateMinus() {
        return rotateMinus;
    }

    public void setRotateMinus(JButton rotateMinus) {
        this.rotateMinus = rotateMinus;
    }

    public double getCurrentAngle() {
        return currentAngle;
    }

    public void setCurrentAngle(double currentAngle) {
        this.currentAngle = currentAngle;
    }

    public double getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(double rotationAngle) {
        this.rotationAngle = rotationAngle;
    }
}
