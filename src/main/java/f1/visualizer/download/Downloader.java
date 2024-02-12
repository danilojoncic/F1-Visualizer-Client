package f1.visualizer.download;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Downloader {
    public static int totalRequestNumber = 0;
    private Set<Integer> session_ids = new HashSet<>();
    private Set<Integer> meeting_ids = new HashSet<>();
    private Set<Integer> driver_numbers = new HashSet<>();
    private HashMap<Integer,DataFields> sessionStartAndEnd = new HashMap<>();
    private String startDate= "";
    private String endDate = "";
    private String sessionName = "";
    public void fetchRaceControll(){
        for (Integer sessionId : session_ids) {
            startDate = sessionStartAndEnd.get(sessionId).getStartDate();
            endDate = sessionStartAndEnd.get(sessionId).getEndDate();
            sessionName = sessionStartAndEnd.get(sessionId).getName();
            String apiURL = new String(RequestURIs.getRaceControlUpdates+"session_key="+sessionId+"&date>"+startDate+"&date<"+endDate);
            System.out.println(apiURL);
            totalRequestNumber++;
            try {
                URL url = new URL(apiURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
                JsonNode jsonNode = objectMapper.readTree(response.toString());
                objectMapper.writeValue(new FileWriter(sessionName.toLowerCase()+"_race_control_updates.json",true), jsonNode);
                connection.disconnect();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void fetchStints(){
        for (Integer sessionId : session_ids) {
            sessionName = sessionStartAndEnd.get(sessionId).getName();
            for (Integer driverNumber : driver_numbers) {
                String apiURL = new String(RequestURIs.getStints+"session_key="+sessionId+"&driver_number="+driverNumber);
                totalRequestNumber++;
                System.out.println(apiURL);
                try {
                    URL url = new URL(apiURL);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
                    JsonNode jsonNode = objectMapper.readTree(response.toString());
                    objectMapper.writeValue(new FileWriter(sessionName.toLowerCase()+"stints.json",true), jsonNode);
                    connection.disconnect();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public void fetchWeather(){
        for (Integer sessionId : session_ids) {
            startDate = sessionStartAndEnd.get(sessionId).getStartDate();
            endDate = sessionStartAndEnd.get(sessionId).getEndDate();
            sessionName = sessionStartAndEnd.get(sessionId).getName();
            String apiURL = new String(RequestURIs.getWeather+"session_key="+sessionId+"&date>"+startDate+"&date<"+endDate);
            System.out.println(apiURL);
            totalRequestNumber++;
            try {
                URL url = new URL(apiURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
                JsonNode jsonNode = objectMapper.readTree(response.toString());
                objectMapper.writeValue(new FileWriter(sessionName.toLowerCase()+"_weather.json",true), jsonNode);
                connection.disconnect();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void fetchIntervals(){
        for (Integer sessionId : session_ids) {
            startDate = sessionStartAndEnd.get(sessionId).getStartDate();
            endDate = sessionStartAndEnd.get(sessionId).getEndDate();
            sessionName = sessionStartAndEnd.get(sessionId).getName();
            for (Integer driverNumber : driver_numbers) {
                String apiURL = new String(RequestURIs.getIntervals+"session_key="+sessionId+"&driver_number="+driverNumber+"&date>"+startDate+"&date<"+endDate);
                System.out.println(apiURL);
                totalRequestNumber++;
                try {
                    URL url = new URL(apiURL);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
                    JsonNode jsonNode = objectMapper.readTree(response.toString());
                    objectMapper.writeValue(new FileWriter(sessionName.toLowerCase()+"_intervals.json",true), jsonNode);
                    connection.disconnect();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void fetchPostions(){
        for (Integer sessionId : session_ids) {
            startDate = sessionStartAndEnd.get(sessionId).getStartDate();
            endDate = sessionStartAndEnd.get(sessionId).getEndDate();
            sessionName = sessionStartAndEnd.get(sessionId).getName();
            for (Integer driverNumber : driver_numbers) {
                String apiURL = new String(RequestURIs.getPostions+"session_key="+sessionId+"&driver_number="+driverNumber+"&date>"+startDate+"&date<"+endDate);
                System.out.println(apiURL);
                totalRequestNumber++;
                try {
                    URL url = new URL(apiURL);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
                    JsonNode jsonNode = objectMapper.readTree(response.toString());
                    objectMapper.writeValue(new FileWriter(sessionName.toLowerCase()+"_positions.json",true), jsonNode);
                    connection.disconnect();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void fetchLaps(){
        for (Integer sessionId : session_ids) {
            sessionName = sessionStartAndEnd.get(sessionId).getName();
            for (Integer driverNumber : driver_numbers) {
                String apiURL = new String(RequestURIs.getLaps+"session_key="+sessionId+"&driver_number="+driverNumber);
                System.out.println(apiURL);
                totalRequestNumber++;
                try {
                    URL url = new URL(apiURL);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
                    JsonNode jsonNode = objectMapper.readTree(response.toString());
                    objectMapper.writeValue(new FileWriter(sessionName.toLowerCase()+"_laps.json",true), jsonNode);
                    connection.disconnect();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void fetchLocations(){
        for (Integer sessionId : session_ids) {
            startDate = sessionStartAndEnd.get(sessionId).getStartDate();
            endDate = sessionStartAndEnd.get(sessionId).getEndDate();
            sessionName = sessionStartAndEnd.get(sessionId).getName();
            for (Integer driverNumber : driver_numbers) {
                String apiURL = new String(RequestURIs.getLocationData+"session_key="+sessionId+"&driver_number="+driverNumber+"&date>"+startDate+"&date<"+endDate);
                totalRequestNumber++;
                System.out.println(apiURL);
                try {
                    URL url = new URL(apiURL);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
                    JsonNode jsonNode = objectMapper.readTree(response.toString());
                    objectMapper.writeValue(new FileWriter(sessionName.toLowerCase()+"_location_data.json",true), jsonNode);
                    connection.disconnect();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void fetchCarData(){
        for (Integer sessionId : session_ids) {
            startDate = sessionStartAndEnd.get(sessionId).getStartDate();
            endDate = sessionStartAndEnd.get(sessionId).getEndDate();
            sessionName = sessionStartAndEnd.get(sessionId).getName();
            for (Integer driverNumber : driver_numbers) {
                String apiURL = new String(RequestURIs.getCarData+"session_key="+sessionId+"&driver_number="+driverNumber+"&date>"+startDate+"&date<"+endDate);
                totalRequestNumber++;
                System.out.println(apiURL);
                try {
                    URL url = new URL(apiURL);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
                    JsonNode jsonNode = objectMapper.readTree(response.toString());
                    objectMapper.writeValue(new FileWriter(sessionName.toLowerCase()+"_car_data.json",true), jsonNode);
                    connection.disconnect();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void fetchDrivers(){
        try {
            String apiUrl = RequestURIs.getDrivers;
            System.out.println(apiUrl);
            totalRequestNumber++;
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // Get the response code
            // Read the response from the API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            JsonNode jsonNode = objectMapper.readTree(response.toString());
            for (JsonNode sessionNode : jsonNode) {
                int driver_number = Integer.parseInt(sessionNode.get("driver_number").asText());
                driver_numbers.add(driver_number);
            }
            objectMapper.writeValue(new FileWriter("drivers.json"), jsonNode);
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fetchMeetings(){
        try {
            String apiUrl = RequestURIs.getMeetings;
            URL url = new URL(apiUrl);
            totalRequestNumber++;
            System.out.println(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // Get the response code
            // Read the response from the API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            JsonNode jsonNode = objectMapper.readTree(response.toString());
            for (JsonNode sessionNode : jsonNode) {
                int meetingId = Integer.parseInt(sessionNode.get("meeting_key").asText());
                meeting_ids.add(meetingId);
            }
            objectMapper.writeValue(new FileWriter("meetings.json"), jsonNode);
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fetchSessions() {
        try {
            String apiUrl = RequestURIs.getSessions;
            URL url = new URL(apiUrl);
            System.out.println(apiUrl);
            totalRequestNumber++;
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // Get the response code
            // Read the response from the API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            JsonNode jsonNode = objectMapper.readTree(response.toString());
            for (JsonNode sessionNode : jsonNode) {
                int sessionKey = Integer.parseInt(sessionNode.get("session_key").asText());
                session_ids.add(sessionKey);
                String startDate = sessionNode.get("date_start").asText();
                String endDate = sessionNode.get("date_end").asText();
                String country = sessionNode.get("country_name").asText();
                String circuit = sessionNode.get("circuit_short_name").asText();
                sessionStartAndEnd.put(sessionKey,new DataFields(startDate,endDate,country+"-"+circuit));
            }
            objectMapper.writeValue(new FileWriter("sessions.json"), jsonNode);
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Downloader downloader = new Downloader();
        Thread.sleep(60000);
        System.out.println("Started fetching data!");
        downloader.fetchSessions();
        downloader.fetchMeetings();
        downloader.fetchDrivers();
        System.out.println("Essentials fetched!");


        downloader.fetchCarData();
        System.out.println("Finished getting car data!");
        Thread.sleep(60000);

        downloader.fetchLocations();
        System.out.println("Finished getting locations!");
        Thread.sleep(60000);

        downloader.fetchLaps();
        System.out.println("Finished getting laps!");
        Thread.sleep(60000);

        downloader.fetchPostions();
        System.out.println("Finished getting positions!");
        Thread.sleep(60000);

        downloader.fetchIntervals();
        System.out.println("Finished getting intervals!");
        Thread.sleep(60000);

        downloader.fetchWeather();
        System.out.println("Finished getting weather!");
        Thread.sleep(60000);

        downloader.fetchStints();
        System.out.println("Finished getting stints!");
        Thread.sleep(60000);

        downloader.fetchRaceControll();
        System.out.println("Finished getting race control data!");
        Thread.sleep(60000);


        System.out.println("Finished fetching data");
        System.out.println("Total number of requests: " + totalRequestNumber);
    }

}
