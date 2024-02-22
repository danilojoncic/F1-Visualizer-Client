package f1.visualizer.response_model;

import lombok.Data;

@Data
public class DriverResponseWrapper {
    private int driver_number;
    private String full_name;
    private int session_involved_key;
}
//i will use this in combination with meetings to find out which driver was at what race
