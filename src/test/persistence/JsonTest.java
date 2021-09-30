package persistence;

import model.NBAchampionship;
import model.NBAleague;
import model.NBAplayer;
import model.NBAteam;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkNBAleague(String leagueName,int leagueSize, NBAleague testLeague) {
        assertEquals(leagueName,testLeague.getLeagueName());
        assertEquals(leagueSize,testLeague.leagueSize());
    }

    protected void checkNBATeam(String teamName, int teamSize, int gamesWon, int gamesLost,
                                int championshipSize, NBAteam testTeam) {
        assertEquals(teamName,testTeam.getTeamName());
        assertEquals(teamSize,testTeam.teamRoster());
        assertEquals(gamesWon,testTeam.getGamesWon());
        assertEquals(gamesLost,testTeam.getGamesLost());
        assertEquals(championshipSize,testTeam.teamChampionships());
    }

    protected void checkNBAplayer(String playerName, double ppg, double rpg, double apg, double fgp, double ftp,
                                  boolean isHealthy, NBAplayer testPlayer) {
        assertEquals(playerName,testPlayer.getPlayerName());
        assertEquals(ppg,testPlayer.getPointsPerGame());
        assertEquals(rpg,testPlayer.getReboundsPerGame());
        assertEquals(apg,testPlayer.getAssistPerGame());
        assertEquals(fgp,testPlayer.getFieldGoalPercentage());
        assertEquals(ftp,testPlayer.getFreeThrowPercentage());
        assertEquals(isHealthy,testPlayer.getIsHealthy());
    }

    protected void checkNBAchampionship(int yearWon, NBAchampionship testChampionship) {
        assertEquals(yearWon,testChampionship.getYearWon());
    }
}
