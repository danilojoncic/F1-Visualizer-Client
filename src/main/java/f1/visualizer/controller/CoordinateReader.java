package f1.visualizer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import f1.visualizer.response_objects.GPSCircuitPosition;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
public class CoordinateReader {

    public static List<GPSCircuitPosition> readCoordinatesFromFile(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        GPSCircuitPosition[] GPSCircuitPositions = objectMapper.readValue(new File(filePath), GPSCircuitPosition[].class);
        List<GPSCircuitPosition> GPSCircuitPositionList = new ArrayList<>();
        for (GPSCircuitPosition GPSCircuitPosition : GPSCircuitPositions) {
            GPSCircuitPositionList.add(GPSCircuitPosition);
        }
        return GPSCircuitPositionList;
    }
}
