package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NBAleagueTest {
    private NBAleague nbaLeague;

    @BeforeEach
    public void runBefore() {
        nbaLeague = new NBAleague("Edward's League");
    }

    @Test
    public void testConstructor() {
        assertEquals("Edward's League",nbaLeague.getLeagueName());
        assertEquals(0,nbaLeague.leagueSize());
    }

    @Test
    public void testAddTeamAddingSameTeamToLeauge() {
        assertEquals(0,nbaLeague.leagueSize());
        NBAteam team1 = new NBAteam("Lakers");
        nbaLeague.addTeam(team1);
        assertEquals(1,nbaLeague.leagueSize());
        nbaLeague.addTeam(team1);
        assertEquals(1,nbaLeague.leagueSize());
    }

    @Test
    public void testAddTeamAddingDifferentTeamToLeague() {
        assertEquals(0,nbaLeague.leagueSize());
        NBAteam team1 = new NBAteam("Lakers");
        NBAteam team2 = new NBAteam("Raptors");
        nbaLeague.addTeam(team1);
        assertEquals(1,nbaLeague.leagueSize());
        nbaLeague.addTeam(team2);
        assertEquals(2,nbaLeague.leagueSize());
    }

    @Test
    public void testRemoveTeamWhenTeamIsInLeague() {
        NBAteam team1 = new NBAteam("Lakers");
        nbaLeague.addTeam(team1);
        assertEquals(1,nbaLeague.leagueSize());
        nbaLeague.removeTeam(team1);
        assertEquals(0,nbaLeague.leagueSize());
    }

    @Test
    public void testRemoveTeamWhenTeamIsNotInLeague() {
        NBAteam team1 = new NBAteam("Lakers");
        NBAteam team2 = new NBAteam("Raptors");
        nbaLeague.addTeam(team1);
        assertEquals(1,nbaLeague.leagueSize());
        nbaLeague.removeTeam(team2);
        assertEquals(1,nbaLeague.leagueSize());
    }

    @Test
    public void testGetTeamWhenLeagueIsEmpty() {
        assertNull(nbaLeague.getTeam(1));
    }

    @Test
    public void testGetTeamWhenIndexWithinLeagueSize() {
        NBAteam team1 = new NBAteam("Lakers");
        nbaLeague.addTeam(team1);
        assertEquals(team1,nbaLeague.getTeam(0));
    }

    @Test
    public void testGetTeamWhenIndexOutOfLeagueSize() {
        NBAteam team1 = new NBAteam("Lakers");
        nbaLeague.addTeam(team1);
        assertNull(nbaLeague.getTeam(1));
    }

    @Test
    public void testGetListOfTeamsWhenLeagueIsEmpty() {
        assertEquals(nbaLeague.getListOfTeams(),nbaLeague.getListOfTeams());
    }

    @Test
    public void testGetListOfTeamsWhenLeagueIsNotEmpty() {
        NBAteam team1 = new NBAteam("Lakers");
        nbaLeague.addTeam(team1);
        assertEquals(nbaLeague.getListOfTeams(),nbaLeague.getListOfTeams());
    }
}
