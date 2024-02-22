package f1.visualizer.response_model;


import lombok.Data;

@Data
public class DriverArbitraryPosition {
    private int x;
    private int y;
    private int z;
    private int session_key;
    private int meeting_key;
    private String date;
    private int driver_number;

}
