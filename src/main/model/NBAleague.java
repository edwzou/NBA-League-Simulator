package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class NBAleague implements Writable {
    private List<NBAteam> teams;
    private String leagueName;

    // EFFECTS: creates NBA league with no teams
    public NBAleague(String s) {
        teams = new ArrayList<>();
        leagueName = s;
    }

    // EFFECTS: returns the name of NBA league
    public String getLeagueName() {
        return leagueName;
    }

    // EFFECTS: return the list of teams in the NBA
    public List<NBAteam> getListOfTeams() {
        return teams;
    }

    // MODIFIES: this
    // EFFECTS: add new NBA teams to the NBA league
    public void addTeam(NBAteam t) {
        if (!teams.contains(t)) {
            teams.add(t);
        }
    }

    // MODIFIES: this
    // EFFECTS: remove NBA teams from the NBA league
    public void removeTeam(NBAteam t) {
        teams.remove(t);
    }

    // EFFECTS: takes in an int as a parameter, returns the NBA team in that index if team exists,
    //          null
    public NBAteam getTeam(int i) {
        for (int j = 0; j < teams.size(); j++) {
            if (j == i) {
                return teams.get(j);
            }
        }
        return null;
    }

    // EFFECTS: get the size of the league
    public int leagueSize() {
        return teams.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("league name","Edward's League");
        json.put("teams",teamsToJson());
        return json;
    }

    // EFFECTS: returns NBA teams in this NBA as a JSON array
    private JSONArray teamsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (NBAteam t : teams) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}
