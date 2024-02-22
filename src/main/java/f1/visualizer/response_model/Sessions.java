package f1.visualizer.response_model;

import lombok.Data;

@Data
public class Sessions {
    private String location;
    private int country_key;
    private String country_name;
    private String country_code;
    private int circuit_key;
    private String date_start;
    private String circuit_short_name;
    private String date_end;
    private String gmt_offset;
    private int meeting_key;
    private int year;
    private int session_key;
    private String session_name;
    private String session_type;
}

