package f1.visualizer.wrappers;

import java.util.Date;
import java.util.List;

public class Race {
    private Date startDate;
    private Date endDate;
    private String raceName;
    private int numberOfLaps;

    private String country;
    private List<Driver> drivers;

    public Race(Date startDate, Date endDate, String raceName, int numberOfLaps, String country, List<Driver> drivers) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.raceName = raceName;
        this.numberOfLaps = numberOfLaps;
        this.country = country;
        this.drivers = drivers;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }
}
