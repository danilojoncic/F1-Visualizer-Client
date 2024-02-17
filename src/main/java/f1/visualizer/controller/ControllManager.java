package f1.visualizer.controller;

import f1.visualizer.view.MainFrame;

public class ControllManager {
    MainFrame mainFrame;

    ScaleController scaleController;
    PlacementController placementController;
    RotationController rotationController;
    public ControllManager(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachController();
    }

    private void attachController(){
        placementController = new PlacementController(mainFrame);
        scaleController = new ScaleController(mainFrame);
        rotationController = new RotationController(mainFrame);
    }

}
