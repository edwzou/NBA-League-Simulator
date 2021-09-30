package model;

import exceptions.NegativeStats;
import org.json.JSONObject;
import persistence.Writable;

public class NBAplayer implements Writable {
    public String playerName;
    public double pointsPerGame;
    public double reboundsPerGame;
    public double assistPerGame;
    public double fieldGoalPercentage;
    public double freeThrowPercentage;
    public boolean isHealthy;

    // EFFECTS: constructs a new NBA player with its name (string), points per game (double), rebounds per game
    //          (double), assists per game (double), field goal percentage (double), free throw percentage
    //          (double), and health status (boolean, true for healthy, false for injured/not healthy)
    public NBAplayer(String name, double ppg, double rpg, double apg, double fgp, double ftp, boolean healthy) {
        playerName = name;
        pointsPerGame = ppg;
        reboundsPerGame = rpg;
        assistPerGame = apg;
        fieldGoalPercentage = fgp;
        freeThrowPercentage = ftp;
        isHealthy = healthy;
    }

    // MODIFIES: this
    // EFFECTS: set the point per game value of the player
    public void setPointsPerGame(double ppg) throws NegativeStats {
        if (ppg < 0) {
            throw new NegativeStats();
        }
        pointsPerGame = ppg;
    }

    // MODIFIES: this
    // EFFECTS: set the rebound per game value of the player
    public void setReboundsPerGame(double rpg) throws NegativeStats {
        if (rpg < 0) {
            throw new NegativeStats();
        }
        reboundsPerGame = rpg;
    }

    // MODIFIES: this
    // EFFECTS: set the assist per game value of the player
    public void setAssistPerGame(double apg) throws NegativeStats {
        if (apg < 0) {
            throw new NegativeStats();
        }
        assistPerGame = apg;
    }

    // MODIFIES: this
    // EFFECTS: set the field goal percentage value of the player
    public void setFieldGoalPercentage(double fgp) throws NegativeStats {
        if (fgp < 0) {
            throw new NegativeStats();
        }
        fieldGoalPercentage = fgp;
    }

    // MODIFIES: this
    // EFFECTS: set the free throw percentage value of the player
    public void setFreeThrowPercentage(double ftp) throws NegativeStats {
        if (ftp < 0) {
            throw new NegativeStats();
        }
        freeThrowPercentage = ftp;
    }

    // MODIFIES: this
    // EFFECTS: set the health state of the player
    public void setHealthy(boolean healthy) {
        isHealthy = healthy;
    }

    // EFFECTS: takes no arguments and returns the player's name (a string)
    public String getPlayerName() {
        return playerName;
    }

    // EFFECTS: takes no arguments and returns the player's points per game (a double)
    public double getPointsPerGame() {
        return pointsPerGame;
    }

    // EFFECTS: takes no arguments and returns the player's rebound per game (a double)
    public double getReboundsPerGame() {
        return reboundsPerGame;
    }

    // EFFECTS: takes no arguments and returns the player's assists per game (a double)
    public double getAssistPerGame() {
        return assistPerGame;
    }

    // EFFECTS: takes no arguments and returns the player's field goal percentage (a double)
    public double getFieldGoalPercentage() {
        return fieldGoalPercentage;
    }

    // EFFECTS: takes no arguments and returns the player's free throw percentage (a double)
    public double getFreeThrowPercentage() {
        return freeThrowPercentage;
    }

    // EFFECTS: takes no arguments and returns the player's health status. Returns true if player is health, false if
    //          player is injured (a boolean)
    public boolean getIsHealthy() {
        return isHealthy;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("player name", getPlayerName());
        json.put("points per game", getPointsPerGame());
        json.put("rebounds per game", getReboundsPerGame());
        json.put("assists per game", getAssistPerGame());
        json.put("field goal percentage", getFieldGoalPercentage());
        json.put("free throw percentage", getFreeThrowPercentage());
        json.put("is player healthy", getIsHealthy());
        return json;
    }
}
