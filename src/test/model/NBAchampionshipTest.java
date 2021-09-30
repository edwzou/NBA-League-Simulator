package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NBAchampionshipTest {
    private NBAchampionship testChampionship;

    @BeforeEach
    public void runBefore() {
        testChampionship = new NBAchampionship(2015);
    }

    @Test
    public void testConstructor() {
        assertEquals(2015,testChampionship.getYearWon());
    }
}
