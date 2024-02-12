package f1.visualizer;

import com.fasterxml.jackson.databind.ObjectMapper;
import f1.visualizer.view.MainFrame;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientMain {

    public static void main(String[] args) {
        //fetchRaceStartAndEnd();
        //fetchDriverLocation();
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }



    public void fetchRaceStartAndEnd(){
        https://api.openf1.org/v1/sessions?country_name=Belgium&session_name=Sprint&year=2023

        try {
            // Specify the URL with parameters
            String apiUrl = "https://api.openf1.org/v1/sessions?country_name=Belgium&session_name=Sprint&year=2023";
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
            Object json = objectMapper.readValue(response.toString(), Object.class);
            String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            System.out.println("Response:\n" + prettyJson);
            // Close the connection
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
