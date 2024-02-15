package f1.visualizer.requests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import f1.visualizer.wrappers.Position;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Requests {
    private List<Position> positions;

    public List<Position> fetchDriverLocation() {
        positions = new ArrayList<>();

        try {
            // Specify the URL with parameters
            String apiUrl = "http://localhost:8000/location?session_key=7953&driver_number=1&date%3E2023-03-05T15:00:00&date%3C2023-03-05T15:05:00";

            // Create a URL object
            URL url = new URL(apiUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read the response from the API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Format and print the JSON response
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.toString());

            // Extract positions from the array
            List<Position> positionList = objectMapper.readValue(response.toString(), new TypeReference<List<Position>>() {});

            // Add all positions to the existing list
            positions.addAll(positionList);
            // Close the connection
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
    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
}
