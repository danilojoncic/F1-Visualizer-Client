package f1.visualizer.wrappers;

import lombok.Data;

@Data
public class Meetings {
    private String meeting_name;
    private String meeting_name_official;
    private String location;
    private int country_key;
    private String country_name;
    private int circuit_key;
    private String circuit_short_name;
    private String date_start;
    private String gmt_offset;
    private int meeting_key;
    private int year;
}
