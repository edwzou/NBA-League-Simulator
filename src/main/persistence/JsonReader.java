package persistence;

import model.NBAchampionship;
import model.NBAleague;
import model.NBAplayer;
import model.NBAteam;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads NBA league from file and returns it;
    // throws IOException if an error occurs reading data from file
    public NBAleague read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseNBAleague(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses NBA league from JSON object and returns it
    private NBAleague parseNBAleague(JSONObject jsonObject) {
        String name = jsonObject.getString("league name");
        NBAleague nbaLeague = new NBAleague(name);
        addNBAteams(nbaLeague,jsonObject);
        return nbaLeague;
    }

    // MODIFIES: nbaLeague
    // EFFECTS: parses teams from JSON object and adds them to NBA league
    private void addNBAteams(NBAleague nbaLeague, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("teams");
        for (Object json : jsonArray) {
            JSONObject nextTeam = (JSONObject) json;
            addNBAteam(nbaLeague,nextTeam);
        }
    }

    // MODIFIES: nbaLeague
    // EFFECTS: parses teams from JSON object and adds them to list of NBA teams
    private void addNBAteam(NBAleague nbaLeague, JSONObject jsonObject) {
        String name = jsonObject.getString("team name");
        NBAteam t = new NBAteam(name);
        addNBAplayers(t,jsonObject);
        addNBAchampionships(t,jsonObject);
        nbaLeague.addTeam(t);
    }

    // MODIFIES: t
    // EFFECTS: parses players from JSON object and adds it to team
    private void addNBAplayers(NBAteam t, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("players on team");
        for (Object json : jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            addNBAplayer(t,nextPlayer);
        }
    }

    // MODIFIES: t
    // EFFECTS: parses players from JSON object and adds it to list of NBA players in team
    private void addNBAplayer(NBAteam t, JSONObject jsonObject) {
        String playerName = jsonObject.getString("player name");
        double ppg = jsonObject.getDouble("points per game");
        double rpg = jsonObject.getDouble("rebounds per game");
        double apg = jsonObject.getDouble("assists per game");
        double fgp = jsonObject.getDouble("field goal percentage");
        double ftp = jsonObject.getDouble("free throw percentage");
        boolean isHealthy = jsonObject.getBoolean("is player healthy");
        NBAplayer p = new NBAplayer(playerName,ppg,rpg,apg,fgp,ftp,isHealthy);
        t.addPlayer(p);
    }

    // MODIFIES: t
    // EFFECTS: parses championships from JSON object and adds it to team
    private void addNBAchampionships(NBAteam t, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("championships on team");
        for (Object json : jsonArray) {
            JSONObject nextChampionship = (JSONObject) json;
            addNBAchampionship(t,nextChampionship);
        }
    }

    // MODIFIES: t
    // EFFECTS: parses championships from JSON object and adds it to list of NBA championships in team
    private void addNBAchampionship(NBAteam t, JSONObject jsonObject) {
        int yearWon = jsonObject.getInt("championship year won");
        NBAchampionship c = new NBAchampionship(yearWon);
        t.addChampionship(c);
    }
}