package f1.visualizer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import f1.visualizer.wrappers.Position;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
public class CoordinateReader {

    public static List<Position> readCoordinatesFromFile(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Position[] positions = objectMapper.readValue(new File(filePath), Position[].class);
        List<Position> positionList = new ArrayList<>();
        for (Position position : positions) {
            positionList.add(position);
        }
        return positionList;
    }
}
