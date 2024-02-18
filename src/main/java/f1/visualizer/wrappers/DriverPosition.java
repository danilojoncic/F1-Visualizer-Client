package f1.visualizer.wrappers;


import lombok.Data;

@Data
public class DriverPosition{
    private int x;
    private int y;
    private int z;
    private int session_key;
    private int meeting_key;
    private String date;
    private int driver_number;

}
