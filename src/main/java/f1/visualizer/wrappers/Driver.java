package f1.visualizer.wrappers;

import java.util.List;

public class Driver {
    private String name;
    private int raceNumber;
    private String team;
    private List<Position> positionsInTime;
    private int racePos;
    private float gapFront;
    private float gapBehind;

    public Driver(String name, int raceNumber, List<Position> positionsInTime) {
        this.name = name;
        this.raceNumber = raceNumber;
        this.positionsInTime = positionsInTime;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRaceNumber() {
        return raceNumber;
    }

    public void setRaceNumber(int raceNumber) {
        this.raceNumber = raceNumber;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public List<Position> getPositionsInTime() {
        return positionsInTime;
    }

    public void setPositionsInTime(List<Position> positionsInTime) {
        this.positionsInTime = positionsInTime;
    }
}
