package model;

import exceptions.NegativeStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NBAplayerTest {
    private NBAplayer testPlayer;

    @BeforeEach
    public void runBefore() {
        testPlayer = new NBAplayer("Steph Curry",20.8,5.2,6.6,40.2,100,true);
    }

    @Test
    public void testConstructor() {
        assertEquals("Steph Curry",testPlayer.getPlayerName());
        assertEquals(20.8,testPlayer.getPointsPerGame());
        assertEquals(5.2,testPlayer.getReboundsPerGame());
        assertEquals(6.6,testPlayer.getAssistPerGame());
        assertEquals(40.2,testPlayer.getFieldGoalPercentage());
        assertEquals(100,testPlayer.getFreeThrowPercentage());
        assertTrue(testPlayer.getIsHealthy());
    }

    @Test
    public void testSetPositivePointsPerGame() {
        assertEquals(20.8,testPlayer.getPointsPerGame());
        try {
            testPlayer.setPointsPerGame(30.5);
        } catch (NegativeStats negativeStats) {
        }
        assertEquals(30.5,testPlayer.getPointsPerGame());
    }

    @Test
    public void testSetPositiveReboundsPerGame() {
        assertEquals(5.2,testPlayer.getReboundsPerGame());
        try {
            testPlayer.setReboundsPerGame(3.3);
        } catch (NegativeStats negativeStats) {
            fail("Caught exception when not supposed to.");
        }
        assertEquals(3.3,testPlayer.getReboundsPerGame());
    }

    @Test
    public void testSetPositiveAssistsPerGame() {
        assertEquals(6.6,testPlayer.getAssistPerGame());
        try {
            testPlayer.setAssistPerGame(4.5);
        } catch (NegativeStats negativeStats) {
            fail("Caught exception when not supposed to.");
        }
        assertEquals(4.5,testPlayer.getAssistPerGame());
    }

    @Test
    public void testSetPositiveFieldGoalPercentage() {
        assertEquals(40.2,testPlayer.getFieldGoalPercentage());
        try {
            testPlayer.setFieldGoalPercentage(45.6);
        } catch (NegativeStats negativeStats) {
            fail("Caught exception when not supposed to.");
        }
        assertEquals(45.6,testPlayer.getFieldGoalPercentage());
    }

    @Test
    public void testSetPositiveFreeThrowPercentage() {
        assertEquals(100,testPlayer.getFreeThrowPercentage());
        try {
            testPlayer.setFreeThrowPercentage(98.8);
        } catch (NegativeStats negativeStats) {
            fail("Caught exception when not supposed to.");
        }
        assertEquals(98.8,testPlayer.getFreeThrowPercentage());
    }

    @Test
    public void testSetNegativePointsPerGame() {
        assertEquals(20.8,testPlayer.getPointsPerGame());
        try {
            testPlayer.setPointsPerGame(-10.0);
            fail("Suppose to catch exception.");
        } catch (NegativeStats negativeStats) {
            // do nothing here
        }
        assertEquals(20.8,testPlayer.getPointsPerGame());
    }

    @Test
    public void testSetNegativeReboundsPerGame() {
        assertEquals(5.2,testPlayer.getReboundsPerGame());
        try {
            testPlayer.setReboundsPerGame(-10.0);
            fail("Suppose to catch exception.");
        } catch (NegativeStats negativeStats) {
            // do nothing here
        }
        assertEquals(5.2,testPlayer.getReboundsPerGame());
    }

    @Test
    public void testSetNegativeAssistsPerGame() {
        assertEquals(6.6,testPlayer.getAssistPerGame());
        try {
            testPlayer.setAssistPerGame(-10.0);
            fail("Suppose to catch exception.");
        } catch (NegativeStats negativeStats) {
            // do nothing here
        }
        assertEquals(6.6,testPlayer.getAssistPerGame());
    }

    @Test
    public void testSetNegativeFieldGoalPercentage() {
        assertEquals(40.2,testPlayer.getFieldGoalPercentage());
        try {
            testPlayer.setFieldGoalPercentage(-10.0);
            fail("Suppose to catch exception.");
        } catch (NegativeStats negativeStats) {
            // do nothing here
        }
        assertEquals(40.2,testPlayer.getFieldGoalPercentage());
    }

    @Test
    public void testSetNegativeFreeThrowPercentage() {
        assertEquals(100,testPlayer.getFreeThrowPercentage());
        try {
            testPlayer.setFreeThrowPercentage(-10.0);
            fail("Suppose to catch exception.");
        } catch (NegativeStats negativeStats) {
            // do nothing here
        }
        assertEquals(100,testPlayer.getFreeThrowPercentage());
    }

    @Test
    public void testSetHealthy() {
        assertTrue(testPlayer.getIsHealthy());
        testPlayer.setHealthy(false);
        assertFalse(testPlayer.getIsHealthy());
    }

    @Test
    public void testGetPlayerName() {
        assertEquals("Steph Curry",testPlayer.getPlayerName());
    }

    @Test
    public void testGetPointsPerGame() {
        assertEquals(20.8,testPlayer.getPointsPerGame());
    }

    @Test
    public void testGetReboundsPerGame() {
        assertEquals(5.2,testPlayer.getReboundsPerGame());
    }

    @Test
    public void testGetAssistsPerGame() {
        assertEquals(6.6,testPlayer.getAssistPerGame());
    }

    @Test
    public void testGetFieldGoalPercentage() {
        assertEquals(40.2,testPlayer.getFieldGoalPercentage());
    }

    @Test
    public void testGetFreeThrowPercentage() {
        assertEquals(100,testPlayer.getFreeThrowPercentage());
    }

    @Test
    public void testGetIsHealthy() {
        assertTrue(testPlayer.getIsHealthy());
    }
}
