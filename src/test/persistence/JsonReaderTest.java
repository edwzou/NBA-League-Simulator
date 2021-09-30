package persistence;

import model.NBAleague;
import model.NBAteam;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            NBAleague l = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyEverything() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyEverything");
        try {
            NBAleague l = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyNBAleague() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyNBAleague.json");
        try {
            NBAleague l = reader.read();
            assertEquals("Edward's League",l.getLeagueName());
            assertEquals(0,l.leagueSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderEmptyNBATeam() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyNBAteam.json");
        try {
            NBAleague l = reader.read();
            NBAteam t = l.getTeam(0);
            assertEquals("Lakers",t.getTeamName());
            assertEquals(0,t.teamRoster());
            assertEquals(0,t.getGamesWon());
            assertEquals(0,t.getGamesLost());
            assertEquals(0,t.teamChampionships());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralNBAleague() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralNBAleague.json");
        try {
            NBAleague l = reader.read();
            List<NBAteam> NBAteams = l.getListOfTeams();
            NBAteam lakers = NBAteams.get(0);
            checkNBAleague("Edward's League",1,l);
            checkNBATeam("Lakers",1,0,0,1,lakers);
            checkNBAplayer("Lebron James",30.5,8.9,10.3,55.4,80.4,
                    true,lakers.getListOfPlayers().get(0));
            checkNBAchampionship(2020,lakers.getListOfChampionships().get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}