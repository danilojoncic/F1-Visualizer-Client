package f1.visualizer.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import f1.visualizer.response_objects.DriverArbitraryPosition;
import f1.visualizer.response_objects.Meetings;
import f1.visualizer.response_objects.Sessions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DataFetcher {
    //use this for real time until i come up with some sort of buffer to animate
    public static DriverArbitraryPosition fetchLiveDriverPosition(int driver_number){
        DriverArbitraryPosition driverArbitraryPosition = new DriverArbitraryPosition();
        try {
            String apiUrl = "https://api.openf1.org/v1/location?session_key=latest&driver_number="+driver_number+"";
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.toString());
            driverArbitraryPosition = objectMapper.readValue(response.toString(), new TypeReference<DriverArbitraryPosition>() {});
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return driverArbitraryPosition;
    }

    public static List<DriverArbitraryPosition> fetchDriverLocation(int driver_number){
        List<DriverArbitraryPosition> positions = new ArrayList<>();
        try {
            String apiUrl = "https://api.openf1.org/v1/location?session_key=9161&driver_number=" + driver_number + "&date>2023-09-16T13:03:35.200&date<2023-09-16T13:08:35.800";
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.toString());
            List<DriverArbitraryPosition> positionList = objectMapper.readValue(response.toString(), new TypeReference<List<DriverArbitraryPosition>>() {});
            positions.addAll(positionList);
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (positions.isEmpty()){
            System.out.println("Positions is empty");
            return null;
        } else {
            return positions;
        }
    }
    public static List<Sessions> fetchSessionByKey(int meeting_key) {
        List<Sessions> sessions = new ArrayList<>();
        try {
            String apiUrl = "https://api.openf1.org/v1/sessions?session_name=Race&meeting_key="+meeting_key+"";
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.toString());
            List<Sessions> sessionList = objectMapper.readValue(response.toString(), new TypeReference<List<Sessions>>() {});
            sessions.addAll(sessionList);
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (sessions.isEmpty()) {
            System.out.println("Sessions is empty");
            return null;
        } else {
            return sessions;
        }
    }

    public static List<Meetings> fetchAllMeetings() {
        List<Meetings> meetings = new ArrayList<>();
        try {
            String apiUrl = "https://api.openf1.org/v1/meetings?year=2023";
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.toString());
            List<Meetings> meetingList = objectMapper.readValue(response.toString(), new TypeReference<List<Meetings>>() {});
            meetings.addAll(meetingList);
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (meetings.isEmpty()) {
            System.out.println("Meetings is empty");
            return null;
        } else {
            return meetings;
        }
    }
}
