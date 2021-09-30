package ui;

import model.NBAleague;
import model.NBAteam;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

/**
 * Represents user interface for NBA league.
 */
public class NBAleagueGUI extends JInternalFrame {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 100;
    private static final int LOC = 100;
    private NBAleague nbaLeague;
    private Container cp = getContentPane();
    private JTextField homePanel;

    /**
     * Constructor sets up user interface for a given NBA league
     * @param l   the NBA league
     * @param parent  the parent component
     */
    public NBAleagueGUI(NBAleague l, Component parent) {
        super(l.getLeagueName(), true, true, true, true);
        nbaLeague = l;
        homePanel = new JTextField("Teams:");
        homePanel.setEditable(false);
        homePanel.setAlignmentX(CENTER_ALIGNMENT);

        //JButton lookIntoTeam = new JButton(new LookIntoTeamAction());
        //lookIntoTeam.setAlignmentX(CENTER_ALIGNMENT);
        cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
        cp.add(homePanel);
        displayTeams(nbaLeague);
        //cp.add(lookIntoTeam);
        setSize(WIDTH, HEIGHT);
        setPosition(parent);
        setVisible(true);
    }

    /**
     * Helper method to display the teams
     */
    private void displayTeams(NBAleague l) {
        for (int i = 0; i < l.leagueSize(); i++) {
            NBAteam team = l.getTeam(i);
            String display = team.getTeamName()
                    + "\tNumber of players on team: " + team.teamRoster()
                    + "\tNumber of games won: " + team.getGamesWon()
                    + "\tNumber of games lost: " + team.getGamesLost()
                    + "\tNumber of championships won: " + team.teamChampionships();
            cp.add(new JTextField(display));
        }
    }

    /**
     * Sets the position of this NBAleague UI relative to parent component
     * @param parent  the parent component
     */
    private void setPosition(Component parent) {
        setLocation(LOC, parent.getHeight() / 2 + LOC / 5);
    }

//    /**
//     * Represents the action to be taken when the user wants to look into a particular team
//     */
//    private class LookIntoTeamAction extends AbstractAction {
//        LookIntoTeamAction() {
//            super("Manage team");
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            String manageTeam = JOptionPane.showInputDialog(null,
//                    "Which team would you like to manage?",
//                    "Enter team name",
//                    JOptionPane.QUESTION_MESSAGE);
//            for (NBAteam t : nbaLeague.getListOfTeams()) {
//                if (t.getTeamName().toLowerCase().equals(manageTeam.toLowerCase())) {
//                    manageTeam(t);
//                } else {
//                    JOptionPane.showMessageDialog(null,
//                            "The team you've entered is not in the NBA");
//                }
//            }
//        }
//    }
//
//    private void manageTeam(NBAteam t) {
//        teamPanel = new JInternalFrame(t.getTeamName(),false,true,false,true);
//        teamPanel.setLayout(new BorderLayout());
//
//        addTeamManageButtonPanel();
//
//        teamPanel.pack();
//        teamPanel.setVisible(true);
//        desktop.add(teamPanel);
//    }
//
//    private void addTeamManageButtonPanel() {
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new GridLayout(6, 1));
//    }
}