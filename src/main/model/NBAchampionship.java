package model;

import org.json.JSONObject;
import persistence.Writable;

public class NBAchampionship implements Writable {
    private final int yearWon;

    // EFFECTS: create a new NBA championship with the year that the championship happened
    public NBAchampionship(int year) {
        yearWon = year;
    }

    // EFFECTS: takes no arguments and returns the year that the championships was won (an int)
    public int getYearWon() {
        return yearWon;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("championship year won",getYearWon());
        return json;
    }
}
