package persistence;

import model.NBAchampionship;
import model.NBAleague;
import model.NBAplayer;
import model.NBAteam;
import org.junit.jupiter.api.Test;
import ui.NBA;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            NBAleague l = new NBAleague("Edward's League");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyNBAleague() {
        try {
            NBAleague l = new NBAleague("Edward's League");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyNBAleague.json");
            writer.open();
            writer.write(l);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyNBAleague.json");
            l = reader.read();
            assertEquals("Edward's League",l.getLeagueName());
            assertEquals(0,l.leagueSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterEmptyNBAteam() {
        try {
            NBAleague l = new NBAleague("Edward's League");
            l.addTeam(new NBAteam("Nuggets"));
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyNBAteam.json");
            writer.open();
            writer.write(l);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyNBAteam.json");
            l = reader.read();
            checkNBAleague("Edward's League",1,l);
            List<NBAteam> NBAteams = l.getListOfTeams();
            NBAteam n = NBAteams.get(0);
            checkNBATeam("Nuggets",0,0,0,0,n);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterGeneralNBAleague() {
        try {
            NBAleague l = new NBAleague("Edward's League");
            l.addTeam(new NBAteam("Raptors"));
            l.addTeam(new NBAteam("Celtics"));
            List<NBAteam> NBAteams = l.getListOfTeams();
            NBAteam r = NBAteams.get(0);
            NBAteam c = NBAteams.get(1);

            r.addPlayer(new NBAplayer("Kyle Lowry",30.4,10.3,6.6,56.5,70.6,true));
            r.addPlayer(new NBAplayer("Marc Gasol",25.4,7.7,8.9,54.5,70.6,false));
            r.addChampionship(new NBAchampionship(2019));
            r.addGamesWon();
            r.addGamesLost();

            c.addPlayer(new NBAplayer("Kemba Walker",20.3,5.5,9.6,66.4,80.0,true));
            c.addChampionship(new NBAchampionship(1986));
            c.addChampionship(new NBAchampionship(2008));
            c.addGamesWon();
            c.addGamesLost();

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralNBAleague.json");
            writer.open();
            writer.write(l);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralNBAleague.json");
            l = reader.read();
            checkNBAleague("Edward's League",2,l);

            checkNBATeam("Raptors",2,1,1,1,r);
            checkNBAplayer("Kyle Lowry",30.4,10.3,6.6,56.5,70.6,true,
                    r.getListOfPlayers().get(0));
            checkNBAplayer("Marc Gasol",25.4,7.7,8.9,54.5,70.6,false,
                    r.getListOfPlayers().get(1));
            checkNBAchampionship(2019,r.getListOfChampionships().get(0));

            checkNBATeam("Celtics",1,1,1,2,c);
            checkNBAplayer("Kemba Walker",20.3,5.5,9.6,66.4,80.0,true,
                    c.getPlayer("Kemba Walker"));
            checkNBAchampionship(1986,c.getListOfChampionships().get(0));
            checkNBAchampionship(2008,c.getListOfChampionships().get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}