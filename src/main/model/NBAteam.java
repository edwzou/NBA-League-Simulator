package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class NBAteam implements Writable {
    private final String name;
    private List<NBAplayer> team;
    private List<NBAchampionship> championship;
    private int gamesWon;
    private int gamesLost;

    // EFFECTS: create a new NBA team object with a team name, empty roster(ArrayList), empty championships(ArrayList)
    //          zero games won and zero games lost
    public NBAteam(String teamName) {
        name = teamName;
        team = new ArrayList<>();
        gamesWon = 0;
        gamesLost = 0;
        championship = new ArrayList<>();
    }

    // EFFECTS: returns the name of NBA team
    public String getTeamName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: add new player to the team if player is not already in the team
    public void addPlayer(NBAplayer player) {
        if (!team.contains(player)) {
            team.add(player);
        }
    }

    // EFFECTS: takes the name (a string) as an argument and returns the player if he's in the team
    //          returns null if player is not in the team
    public NBAplayer getPlayer(String name) {
        for (NBAplayer p : team) {
            if (p.getPlayerName().toLowerCase().equals(name.toLowerCase())) {
                return p;
            }
            break;
        }
        return null;
    }

    // EFFECTS: return the list of players on team
    public List<NBAplayer> getListOfPlayers() {
        return team;
    }

    // EFFECTS: takes a position (an int) as an argument and returns the player in that position of the team.
    //          Return null if there is no player in desired position
    public NBAplayer getPlayerInPosition(int i) {
        for (int j = 0; j < team.size(); j++) {
            if (j == i) {
                return team.get(j);
            }
        }
        return null;
    }

    // EFFECTS: takes no arguments and returns an int that represents the number of players on a team
    public int teamRoster() {
        return team.size();
    }

    // MODIFIES: this
    // EFFECTS: add championship to the list of championships
    public void addChampionship(NBAchampionship c) {
        if (!championship.contains(c)) {
            championship.add(c);
        }
    }

    // EFFECTS: takes year (an int) as an argument and returns the championships of that year if the
    //          team has won a championship that year
    public NBAchampionship getChampionship(int year) {
        for (NBAchampionship c : championship) {
            if (c.getYearWon() == year) {
                return c;
            }
            break;
        }
        return null;
    }

    // EFFECTS: takes no arguments and returns an int that represents the number of championships that
    //          the team won
    public int teamChampionships() {
        return championship.size();
    }

    // EFFECTS: return the list of championships on the team
    public List<NBAchampionship> getListOfChampionships() {
        return championship;
    }

    // EFFECTS: takes a position (an int) as an argument and returns the championship in that position of the team.
    //          Return null if there is no championship in desired position
    public NBAchampionship getChampionshipInPosition(int i) {
        for (int j = 0; j < teamChampionships(); j++) {
            if (j == i) {
                return championship.get(j);
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: set the amount of games won by the team
    public void setGamesWon(int wins) {
        gamesWon = wins;
    }

    // MODIFIES: this
    // EFFECTS: set the amount of games lost by the team
    public void setGamesLost(int losts) {
        gamesLost = losts;
    }

    // MODIFIES: this
    // EFFECTS: add 1 win to the amount of games won by the team
    public void addGamesWon() {
        gamesWon++;
    }

    // MODIFIES: this
    // EFFECTS: add 1 lost to the amount og games lost by the team
    public void addGamesLost() {
        gamesLost++;
    }

    // EFFECTS: takes no arguments and returns an int that represents the number of games won
    public int getGamesWon() {
        return gamesWon;
    }

    // EFFECTS: takes no arguments and returns an int that represents the number of games lost
    public int getGamesLost() {
        return gamesLost;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("team name",getTeamName());
        json.put("team size",teamRoster());
        json.put("players on team",playersToJson());
        json.put("games won",getGamesWon());
        json.put("games lost",getGamesLost());
        json.put("championship size",teamChampionships());
        json.put("championships on team",championshipsToJson());
        return json;
    }

    // EFFECTS: returns NBA players in this NBA team as a JSON array
    private JSONArray playersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (NBAplayer p : team) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns NBA championships in this NBA team as a JSON array
    private JSONArray championshipsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (NBAchampionship c : championship) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}
