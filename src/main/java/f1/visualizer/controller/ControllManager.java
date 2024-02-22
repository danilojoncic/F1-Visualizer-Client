package f1.visualizer.controller;

import f1.visualizer.controller.debug.DebugController;
import f1.visualizer.controller.debug.MotionController;
import f1.visualizer.controller.debug.PlacementController;
import f1.visualizer.controller.debug.RotationController;
import f1.visualizer.controller.menu.BackController;
import f1.visualizer.controller.menu.ChangeSourceController;
import f1.visualizer.controller.menu.ExitController;
import f1.visualizer.controller.menu.PanelSwitcherController;
import f1.visualizer.view.MainFrame;

public class ControllManager {
    MainFrame mainFrame;

    ChangeSourceController changeSourceController;
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
        changeSourceController = new ChangeSourceController(mainFrame);
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
