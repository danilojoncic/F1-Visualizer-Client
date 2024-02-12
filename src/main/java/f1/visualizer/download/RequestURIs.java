package f1.visualizer.download;

public class RequestURIs {
    //to get json data instead of json but in csv you can add the  &csv=true tag
    public static final String getSessions = "https://api.openf1.org/v1/sessions?year=2023&session_name=Race";
    public static final String getMeetings = "https://api.openf1.org/v1/meetings?year=2023";
    public static final String getDrivers = "https://api.openf1.org/v1/drivers";
    public static final String getCarData = "https://api.openf1.org/v1/car_data?";
    public static final String getLocationData = "https://api.openf1.org/v1/location?";
    public static final String getLaps = "https://api.openf1.org/v1/laps?";
    public static final String getIntervals = "https://api.openf1.org/v1/intervals?";
    public static final String getWeather = "https://api.openf1.org/v1/weather?";
    public static final String getPostions= "https://api.openf1.org/v1/position?";
    public static final String getStints = "https://api.openf1.org/v1/stints?";
    public static final String getRaceControlUpdates = "https://api.openf1.org/v1/race_control?";
}
