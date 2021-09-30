package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.NBA;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NBAteamTest {
    private NBAteam testTeam;

    @BeforeEach
    public void runBefore() {
        testTeam = new NBAteam("Golden State Warriors");
    }

    @Test
    public void testConstructor() {
        assertEquals("Golden State Warriors",testTeam.getTeamName());
        assertEquals(0,testTeam.teamRoster());
        assertEquals(0,testTeam.teamChampionships());
        assertEquals(0,testTeam.getGamesWon());
        assertEquals(0,testTeam.getGamesLost());
    }

    @Test
    public void testAddPlayerAddingSamePlayers() {
        NBAplayer testPlayer1 =
                new NBAplayer("Steph Curry",20.8,5.2,6.6,40.2,100,true);
        testTeam.addPlayer(testPlayer1);
        testTeam.addPlayer(testPlayer1);
        assertEquals(1,testTeam.teamRoster());
    }

    @Test
    public void testAddPlayerAddingDifferentPlayers() {
        NBAplayer testPlayer1 =
                new NBAplayer("Steph Curry",20.8,5.2,6.6,40.2,100,true);
        NBAplayer testPlayer2 =
                new NBAplayer("Klay Thompson",0,0,0,0,0,false);
        testTeam.addPlayer(testPlayer1);
        testTeam.addPlayer(testPlayer2);
        assertEquals(2,testTeam.teamRoster());
    }

    @Test
    public void testGetPlayerWhenWantedPlayerInTeam() {
        NBAplayer testPlayer1 =
                new NBAplayer("Steph Curry",20.8,5.2,6.6,40.2,100,true);
        testTeam.addPlayer(testPlayer1);
        assertEquals(testPlayer1,testTeam.getPlayer("Steph Curry"));
    }

    @Test
    public void testGetPlayerWhenWantedPlayerNotInTeam() {
        NBAplayer testPlayer1 =
                new NBAplayer("Steph Curry",20.8,5.2,6.6,40.2,100,true);
        testTeam.addPlayer(testPlayer1);
        assertNull(testTeam.getPlayer("Klay Thompson"));
    }

    @Test
    public void testGetPlayerWhenTeamIsEmpty() {
        assertNull(testTeam.getPlayer("Steph Curry"));
    }

    @Test
    public void testGetListOfPlayersWhenTeamIsEmpty() {
        assertEquals(testTeam.getListOfPlayers(),testTeam.getListOfPlayers());
    }

    @Test
    public void testGetListOfPlayersWhenTeamNotEmpty() {
        NBAplayer testPlayer1 =
                new NBAplayer("Steph Curry",20.8,5.2,6.6,40.2,100,true);
        NBAplayer testPlayer2 =
                new NBAplayer("Klay Thompson",0,0,0,0,0,false);
        testTeam.addPlayer(testPlayer1);
        testTeam.addPlayer(testPlayer2);
        assertEquals(testTeam.getListOfPlayers(),testTeam.getListOfPlayers());
    }

    @Test
    public void testGetPlayerInPositionWhenPlayerInPosition() {
        NBAplayer testPlayer1 =
                new NBAplayer("Steph Curry",20.8,5.2,6.6,40.2,100,true);
        NBAplayer testPlayer2 =
                new NBAplayer("Klay Thompson",0,0,0,0,0,false);
        testTeam.addPlayer(testPlayer1);
        testTeam.addPlayer(testPlayer2);
        assertEquals(testPlayer1,testTeam.getPlayerInPosition(0));
        assertEquals(testPlayer2,testTeam.getPlayerInPosition(1));
    }

    @Test
    public void testGetPlayerInPositionWhenPlayerNotInPosition() {
        assertNull(testTeam.getPlayerInPosition(0));
    }

    @Test
    public void testAddChampionshipAddingSameChampionships() {
        NBAchampionship testChampionship1 = new NBAchampionship(2015);
        testTeam.addChampionship(testChampionship1);
        testTeam.addChampionship(testChampionship1);
        assertEquals(1,testTeam.teamChampionships());
    }

    @Test
    public void testAddChampionshipAddingDifferentChampionships() {
        NBAchampionship testChampionship1 = new NBAchampionship(2015);
        NBAchampionship testChampionship2 = new NBAchampionship(2017);
        NBAchampionship testChampionship3 = new NBAchampionship(2018);
        testTeam.addChampionship(testChampionship1);
        testTeam.addChampionship(testChampionship2);
        testTeam.addChampionship(testChampionship3);
        assertEquals(3,testTeam.teamChampionships());
    }

    @Test
    public void testGetChampionshipWhenChampionshipInTeam() {
        NBAchampionship testChampionship1 = new NBAchampionship(2015);
        testTeam.addChampionship(testChampionship1);
        assertEquals(testChampionship1,testTeam.getChampionship(2015));
    }

    @Test
    public void testGetChampionshipWhenWantedChampionshipNotInTeam() {
        NBAchampionship testChampionship1 = new NBAchampionship(2015);
        testTeam.addChampionship(testChampionship1);
        assertNull(testTeam.getChampionship(2010));
    }

    @Test
    public void testGetChampionshipTeamHaveNoChampionships() {
        assertNull(testTeam.getChampionship(2015));
    }

    @Test
    public void TestGetListOfChampionships() {
        assertEquals(testTeam.getListOfChampionships(),testTeam.getListOfChampionships());
    }

    @Test
    public void testGetChampionshipInPositionWhenChampionInPosition() {
        NBAchampionship testChampionship1 = new NBAchampionship(2015);
        NBAchampionship testChampionship2 = new NBAchampionship(2017);
        NBAchampionship testChampionship3 = new NBAchampionship(2018);
        testTeam.addChampionship(testChampionship1);
        testTeam.addChampionship(testChampionship2);
        testTeam.addChampionship(testChampionship3);
        assertEquals(testChampionship1,testTeam.getChampionshipInPosition(0));
        assertEquals(testChampionship2,testTeam.getChampionshipInPosition(1));
        assertEquals(testChampionship3,testTeam.getChampionshipInPosition(2));
    }

    @Test
    public void testGetChampionshipInPositionWhenChampionshipNotInPosition() {
        assertNull(testTeam.getChampionshipInPosition(0));
    }

    @Test
    public void testSetGamesWon() {
        assertEquals(0,testTeam.getGamesWon());
        assertEquals(0,testTeam.getGamesLost());
        testTeam.setGamesWon(4);
        assertEquals(4,testTeam.getGamesWon());
        assertEquals(0,testTeam.getGamesLost());
    }

    @Test
    public void testSetGamesLost() {
        assertEquals(0,testTeam.getGamesWon());
        assertEquals(0,testTeam.getGamesLost());
        testTeam.setGamesLost(5);
        assertEquals(0,testTeam.getGamesWon());
        assertEquals(5,testTeam.getGamesLost());
    }

    @Test
    public void testAddGamesWon() {
        assertEquals(0,testTeam.getGamesWon());
        assertEquals(0,testTeam.getGamesLost());
        testTeam.addGamesWon();
        assertEquals(1,testTeam.getGamesWon());
        assertEquals(0,testTeam.getGamesLost());
    }

    @Test
    public void testAddGamesLost() {
        assertEquals(0,testTeam.getGamesWon());
        assertEquals(0,testTeam.getGamesLost());
        testTeam.addGamesLost();
        assertEquals(0,testTeam.getGamesWon());
        assertEquals(1,testTeam.getGamesLost());
    }
}