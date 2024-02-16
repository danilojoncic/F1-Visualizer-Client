package f1.visualizer.wrappers;

import lombok.Data;

import java.util.Date;


@Data
public class Position {
    private int x;
    private int session_key;
    private int meeting_key;
    private int driver_number;
    private int y;
    private Date date;
    private int lap;
    private int z;
}
