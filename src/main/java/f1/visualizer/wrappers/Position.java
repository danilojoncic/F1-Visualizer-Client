package f1.visualizer.wrappers;

import java.util.Date;

public class Position {
    private int x;
    private int session_key;
    private int meeting_key;
    private int driver_number;
    private int y;
    private Date date;
    private int lap;
    private int z;


    public Position() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLap() {
        return lap;
    }

    public void setLap(int lap) {
        this.lap = lap;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getDriver_number() {
        return driver_number;
    }

    public void setDriver_number(int driver_number) {
        this.driver_number = driver_number;
    }

    public int getSession_key() {
        return session_key;
    }

    public void setSession_key(int session_key) {
        this.session_key = session_key;
    }

    public int getMeeting_key() {
        return meeting_key;
    }

    public void setMeeting_key(int meeting_key) {
        this.meeting_key = meeting_key;
    }
}
