package f1.visualizer.data_keeper;

import f1.visualizer.response_model.DriverArbitraryPosition;
import f1.visualizer.response_model.DriverResponseWrapper;
import f1.visualizer.response_model.Meetings;
import lombok.Data;

import java.util.List;

@Data
public class Keeper {
    public static List<Meetings> allMettings;
    public static Meetings currentMeeting;
    public static List<DriverArbitraryPosition> driversPositions;
    public static List<DriverResponseWrapper> allDrivers;
    public static DriverResponseWrapper pickedDriver;
}
