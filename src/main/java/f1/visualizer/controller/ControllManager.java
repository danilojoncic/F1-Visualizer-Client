package f1.visualizer.controller;

import f1.visualizer.view.MainFrame;

public class ControllManager {
    MainFrame mainFrame;

    //ScaleController scaleController;
    PlacementController placementController;
    MotionController motionController;
    //RotationController rotationController;
    DebugController debugController;
    PanelSwitcherController panelSwitcherController;
    public ControllManager(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachController();
    }

    private void attachController(){
        placementController = new PlacementController(mainFrame);
        debugController = new DebugController(mainFrame);
        placementController = new PlacementController(mainFrame);
        //scaleController = new ScaleController(mainFrame);
        //rotationController = new RotationController(mainFrame);
        motionController = new MotionController(mainFrame);
    }

}
