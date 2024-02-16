package f1.visualizer.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import f1.visualizer.wrappers.Position;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DataFetcher {
    public static List<Position> fetchDriverLocation(int driver_number){
        List<Position> positions = new ArrayList<>();

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
            List<Position> positionList = objectMapper.readValue(response.toString(), new TypeReference<List<Position>>() {});
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
}