package f1.visualizer.controller;

import f1.visualizer.view.MainFrame;

public class ControllManager {
    MainFrame mainFrame;

    ExitController exitController;
    PlacementController placementController;
    MotionController motionController;
    RotationController rotationController;
    DebugController debugController;
    BackController backController;
    PanelSwitcherController panelSwitcherController;
    public ControllManager(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachController();
    }
    private void attachController(){
        backController = new BackController(mainFrame);
        panelSwitcherController = new PanelSwitcherController(mainFrame);
        exitController = new ExitController(mainFrame);
        placementController = new PlacementController(mainFrame);
        debugController = new DebugController(mainFrame);
        placementController = new PlacementController(mainFrame);
        rotationController = new RotationController(mainFrame);
        motionController = new MotionController(mainFrame);
    }
}
