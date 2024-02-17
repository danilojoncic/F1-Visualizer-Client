package f1.visualizer.wrappers;

import lombok.Data;

import java.util.Date;


@Data
public class Position {
    private double x;
    private double y;






    /*
    45.1234567
    45
    451234567
    450000000
    ->1234567
     */
    public static int giveInInt(double db){
        double decimalPart = (db * 1000000) - ((int) db) * 1000000;
        return (int) decimalPart;
    }
}



