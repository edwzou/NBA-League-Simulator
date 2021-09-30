package ui;

import exceptions.NegativeStats;
import model.*;

import persistence.JsonReader;
import persistence.JsonWriter;


import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;

// NBA application
public class NBA {
    private static final String JSON_STORE = "./data/nba.json";
    private NBAleague nbaLeague;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the NBA application
    public NBA() {
        input = new Scanner(System.in);
        nbaLeague = new NBAleague("Edward's League");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runNBA();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runNBA() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye!\n");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("c")) {
            createAndAddTeam();
        } else if (command.equals("r")) {
            removeTeam();
        } else if (command.equals("v")) {
            viewTeams();
        } else if (command.equals("s")) {
            saveTeams();
        } else if (command.equals("l")) {
            loadTeams();
        } else {
            System.out.println("\nSelection not valid...\n");
        }
    }

    // EFFECTS: displays menu of options to users
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> create and add a team");
        System.out.println("\tr -> remove a team from NBA");
        System.out.println("\tv -> view teams");
        System.out.println("\ts -> save teams to file");
        System.out.println("\tl -> load teams from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: create a new team and add it to NBA. If team already exists in NBA then it won't be added into NBA
    public void createAndAddTeam() {
        boolean alreadyExists = false;
        System.out.println("\nPlease enter the name of the team:\n");
        String teamName = input.next();
        for (NBAteam t : nbaLeague.getListOfTeams()) {
            if (t.getTeamName().toLowerCase().equals(teamName.toLowerCase())) {
                alreadyExists = true;
                break;
            }
        }
        if (!alreadyExists) {
            nbaLeague.addTeam(new NBAteam(teamName));
            System.out.println("\n" + teamName + " has been created and added.\n");
        } else {
            System.out.println("\nThe team you've entered is already in the NBA.\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a team from NBA if the team is already in NBA. If the team is not in NBA then it does nothing
    private void removeTeam() {
        if (nbaLeague.leagueSize() == 0) {
            System.out.println("\nThere are no teams to be removed.\n");
        } else {
            System.out.println("\nWhich team would you like to remove?\n");
            String removeTeam = input.next();
            removeTeam = removeTeam.toLowerCase();
            for (NBAteam t : nbaLeague.getListOfTeams()) {
                if (t.getTeamName().toLowerCase().equals(removeTeam)) {
                    nbaLeague.removeTeam(t);
                    System.out.println("\n" + t.getTeamName() + " has been removed from NBA.\n");
                    break;
                }
                //break;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: prints out the information of all teams in NBA
    private void viewTeams() {
        if (nbaLeague.leagueSize() == 0) {
            System.out.println("\nThere are no teams. Please go create and add some teams\n");
        } else {
            System.out.println("\nTeams:\n");
            for (int i = 0; i < nbaLeague.leagueSize(); i++) {
                NBAteam team = nbaLeague.getTeam(i);
                System.out.println(team.getTeamName());
                System.out.println("\tNumber of players on team: " + team.teamRoster());
                System.out.println("\tNumber of games won: " + team.getGamesWon());
                System.out.println("\tNumber of games lost: " + team.getGamesLost());
                System.out.println("\tNumber of championships won: " + team.teamChampionships());
                System.out.println();
            }
            lookIntoTeams();
        }
    }

    // EFFECTS: if the desired team is in NBA, then it looks into the team and manages it
    //          if the desired team is not in NBA, then nothing happens
    private void lookIntoTeams() {
        System.out.println("\nWould you like to manage any of the teams?\nPlease enter: y or n\n");
        String answer = input.next();
        answer = answer.toLowerCase();
        if (answer.equals("y")) {
            System.out.println("\nPlease enter which team you'd like to manage:\n");
            String manageTeam = input.next();
            for (NBAteam t : nbaLeague.getListOfTeams()) {
                if (t.getTeamName().toLowerCase().equals(manageTeam.toLowerCase())) {
                    runManageTeam(t);
                }
            }
        } else if (answer.equals("n")) {
            System.out.println("\nTaking you back to the menu.\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input relating to team management
    private void runManageTeam(NBAteam team) {
        boolean keepGoing = true;
        String teamCommand = null;
        NBAteam t = team;

        while (keepGoing) {
            displayTeamMenu();
            teamCommand = input.next();
            teamCommand = teamCommand.toLowerCase();

            if (teamCommand.equals("b")) {
                keepGoing = false;
            } else {
                manageTeam(teamCommand,t);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: manages the team with user input
    private void manageTeam(String teamCommand,NBAteam t) {
        if (teamCommand.equals("c")) {
            createAndAddPlayer(t);
        } else if (teamCommand.equals("r")) {
            removePlayers(t);
        } else if (teamCommand.equals("cc")) {
            createAndAddChampionship(t);
        } else if (teamCommand.equals("rc")) {
            removeChampionship(t);
        } else if (teamCommand.equals("v")) {
            viewPlayers(t);
        } else if (teamCommand.equals("vc")) {
            viewChampionships(t);
        } else {
            System.out.println("\nSelection not valid...\n");
        }
    }

    // EFFECTS: displays menu of options to users relating to team management
    private void displayTeamMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> create and add a player");
        System.out.println("\tr -> remove a player");
        System.out.println("\tcc -> create and add a championship");
        System.out.println("\trc -> remove a championship");
        System.out.println("\tv -> view all players on team");
        System.out.println("\tvc -> view all championships on team");
        System.out.println("\tb -> go back");
    }

    // MODIFIES: this
    // EFFECTS: create a new player and add it to an NBA team. If player already exists then he won't be added
    private void createAndAddPlayer(NBAteam t) {
        System.out.println("\nPlease enter the first name of the player:\n");
        String playerFirstName = input.next();
        System.out.println("\nPlease enter the last name of the player:\n");
        String playerLastName = input.next();
        String playerName = playerFirstName + " " + playerLastName;
        System.out.println("\nPlease enter the points per game of " + playerName + ":\n");
        double ppg = input.nextDouble();
        System.out.println("\nPlease enter the rebounds per game of " + playerName + ":\n");
        double rpg = input.nextDouble();
        System.out.println("\nPlease enter the assists per game of " + playerName + ":\n");
        double apg = input.nextDouble();
        System.out.println("\nPlease enter the field goal percentage of " + playerName + ":\n");
        double fgp = input.nextDouble();
        System.out.println("\nPlease enter the free throw percentage of " + playerName + ":\n");
        double ftp = input.nextDouble();
        System.out.println("\nIs " + playerName + " healthy?\nPlease enter: y or n\n");
        String answer = input.next();
        checkPlayer(t,playerName,answer,ppg,rpg,apg,fgp,ftp);
    }

    // MODIFIES: this
    // EFFECTS: helper method for createAndAddPlayer. Check if player is already in team, if true, then the player is
    //          not added into the team. If false, then the player will be added into the team.
    private void checkPlayer(NBAteam t,String s,String a,double ppg,double rpg,double apg,double fgp,double ftp) {
        boolean alreadyExists = false;
        for (NBAplayer p : t.getListOfPlayers()) {
            if (p.getPlayerName().toLowerCase().equals(s.toLowerCase())) {
                alreadyExists = true;
                break;
            }
        }
        if (!alreadyExists) {
            NBAplayer nbaPlayer = new NBAplayer(s,ppg,rpg,apg,fgp,ftp,createAndAddPlayerHelperHealth(a));
            try {
                nbaPlayer.setPointsPerGame(ppg);
                nbaPlayer.setReboundsPerGame(rpg);
                nbaPlayer.setAssistPerGame(apg);
                nbaPlayer.setFieldGoalPercentage(fgp);
                nbaPlayer.setFreeThrowPercentage(ftp);
            } catch (NegativeStats negativeStats) {
                System.out.println("NegativeStats exception thrown");
            }
            t.addPlayer(nbaPlayer);
            System.out.println("\n" + s + " has been added to the team.\n");
        } else {
            System.out.println("\nThe player you've entered is already in the team.\n");
        }
    }

    // EFFECTS: helper method for createAndAddPlayer. Return boolean for the health status of player. True if
    //          healthy, false if not healthy
    private boolean createAndAddPlayerHelperHealth(String answer) {
        boolean healthy = true;
        answer = answer.toLowerCase();
        if (answer.equals("y")) {
            healthy = true;
        } else if (answer.equals("n")) {
            healthy = false;
        }
        return healthy;
    }

    // MODIFIES: this
    // EFFECTS: removes a player from team if the player is already in the team.
    //          If the player is not in the team then it does nothing
    private void removePlayers(NBAteam t) {
        if (t.teamRoster() == 0) {
            System.out.println("\nThere are no players to be removed.\n");
        } else {
            System.out.println("\nWhich player would you like to remove? Enter his first name:\n");
            String removePlayerFirstName = input.next();
            System.out.println("\nWhat is his last name? Enter his last name:\n");
            String removePlayerLastName = input.next();
            String removePlayer = removePlayerFirstName + " " + removePlayerLastName;
            for (NBAplayer p : t.getListOfPlayers()) {
                if (p.getPlayerName().toLowerCase().equals(removePlayer.toLowerCase())) {
                    t.getListOfPlayers().remove(p);
                    System.out.println("\n" + p.getPlayerName() + " has been removed from the team.\n");
                    break;
                }
                //break;
            }
        }
    }

    // EFFECTS: prints out the information of all players in team
    private void viewPlayers(NBAteam t) {
        if (t.teamRoster() == 0) {
            System.out.println("\nThere are no players on the team. Please go create and add some players\n");
        } else {
            System.out.println("\nPlayers:\n");
            for (int i = 0; i < t.teamRoster(); i++) {
                NBAplayer player = t.getPlayerInPosition(i);
                System.out.println(player.getPlayerName());
                System.out.println("\tPlayer's points per game: " + player.getPointsPerGame());
                System.out.println("\tPlayer's rebounds per game: " + player.getReboundsPerGame());
                System.out.println("\tPlayer's assists per game: " + player.getAssistPerGame());
                System.out.println("\tPlayer's field goal percentage: " + player.getFieldGoalPercentage());
                System.out.println("\tPlayer's free throw percentage: " + player.getFreeThrowPercentage());
                System.out.println("\tIs player healthy: " + player.getIsHealthy());
                System.out.println();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: create a new championship and add it to an NBA team. If the championship already exists in team then
    //          it won't be added into team
    private void createAndAddChampionship(NBAteam t) {
        boolean alreadyExists = false;
        System.out.println("\nPlease enter the year of the championship\n");
        int year = input.nextInt();
        for (NBAchampionship c : t.getListOfChampionships()) {
            if (c.getYearWon() == year) {
                alreadyExists = true;
                break;
            }
        }
        if (!alreadyExists) {
            t.addChampionship(new NBAchampionship(year));
            System.out.println("\nThe " + year + " championship has been added to the team.\n");
        } else {
            System.out.println("\nThe championship you've entered is already in the team.\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a championship from team if the championship is already in the team.
    //          If the championship is not in the team then it does nothing
    private void removeChampionship(NBAteam t) {
        if (t.teamChampionships() == 0) {
            System.out.println("\nThere are no championships to be removed.\n");
        } else {
            System.out.println("\nWhich championship would you like to remove? Please enter the year below: \n");
            int removeYear = input.nextInt();
            for (NBAchampionship c : t.getListOfChampionships()) {
                if (c.getYearWon() == removeYear) {
                    t.getListOfChampionships().remove(c);
                    break;
                }
                //break;
            }
        }
    }

    // EFFECTS: prints out the information of all championships in team
    private void viewChampionships(NBAteam t) {
        if (t.teamChampionships() == 0) {
            System.out.println("\nThere are no championships on the team.\n");
            System.out.println("\nPlease go create and add some championships\n");
        } else {
            System.out.println("\nChampionships:\n");
            for (int i = 0; i < t.teamChampionships(); i++) {
                NBAchampionship c = t.getChampionshipInPosition(i);
                System.out.println("\tChampionship " + (i + 1) + ": " + c.getYearWon() + " championship\n");
            }
        }
    }

    // EFFECTS: saves NBA teams to file
    public void saveTeams() {
        try {
            jsonWriter.open();
            jsonWriter.write(nbaLeague);
            jsonWriter.close();
            System.out.println("Saved " + nbaLeague.getLeagueName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads NBA teams from file
    public void loadTeams() {
        try {
            nbaLeague = jsonReader.read();
            System.out.println("Loaded " + nbaLeague.getLeagueName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}