package ui;

import model.NBAleague;
import model.NBAteam;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

/**
 * Represents application's main window frame.
 */
public class GUI extends JFrame {
    private static final int WIDTH = 1250;
    private static final int HEIGHT = 850;
    private static final String JSON_STORE = "./data/nba.json";
    private JDesktopPane desktop;
    private JInternalFrame nbaPanel;
    private JLabel background;

    private NBAleague nbaLeague;
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);

    /**
     * Constructor sets up button panel.
     */
    public GUI() {
        nbaLeague = new NBAleague("Edward's NBA");
        desktop = new JDesktopPane();
        desktop.addMouseListener(new DesktopFocusAction());
        nbaPanel = new JInternalFrame("NBA", false, false, false, false);
        nbaPanel.setLayout(new BorderLayout());

        setContentPane(desktop);
        setTitle("NBA Simulator");
        setSize(WIDTH, HEIGHT);

        addHomeButtonPanel();

        nbaPanel.pack();
        nbaPanel.setVisible(true);
        desktop.add(nbaPanel);

        ImageIcon logo = new ImageIcon("NBA.png");
        setIconImage(logo.getImage());

        ImageIcon img = new ImageIcon("NBA background.png");
        background = new JLabel("",img,JLabel.CENTER);
        background.setBounds(0,0,1250,850);
        add(background);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);
    }

    /**
     * Helper to add buttons on home panel.
     */
    private void addHomeButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1));
        buttonPanel.add(new JButton(new CreateAndAddTeamAction()));
        buttonPanel.add(new JButton(new RemoveTeamAction()));
        buttonPanel.add(new JButton(new ViewTeamsAction()));
        buttonPanel.add(new JButton(new SaveTeamsAction()));
        buttonPanel.add(new JButton(new LoadTeamsAction()));
        buttonPanel.add(new JButton(new QuitAction()));

        nbaPanel.add(buttonPanel, BorderLayout.WEST);
    }

    /**
     * Represents action to be taken when user wants to create and add a new team
     * to the NBA.
     */
    private class CreateAndAddTeamAction extends AbstractAction {
        CreateAndAddTeamAction() {
            super("Create and add team");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean alreadyExists = false;
            playSound("buzzer.wav");
            String teamName = JOptionPane.showInputDialog(null,
                    "Please enter the name of the team",
                    "Enter team name",
                    JOptionPane.QUESTION_MESSAGE);
            for (NBAteam t : nbaLeague.getListOfTeams()) {
                if (t.getTeamName().toLowerCase().equals(teamName.toLowerCase())) {
                    alreadyExists = true;
                    break;
                }
            }
            if (teamName != null) {
                if (!alreadyExists) {
                    nbaLeague.addTeam(new NBAteam(teamName));
                    JOptionPane.showMessageDialog(null,
                            teamName + " has been created and added");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "The team you've entered is already in the NBA");
                }
            }
        }
    }

    /**
     * Represents action to be taken when user wants to remove a team from the NBA.
     */
    private class RemoveTeamAction extends AbstractAction {
        RemoveTeamAction() {
            super("Remove a team from NBA");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            playSound("buzzer.wav");
            if (nbaLeague.leagueSize() == 0) {
                JOptionPane.showMessageDialog(null,
                        "There are no teams to be removed");
            } else {
                String removeTeam = JOptionPane.showInputDialog(null,
                        "Which team would you like to remove?",
                        "Enter team name",
                        JOptionPane.QUESTION_MESSAGE);
                for (NBAteam t : nbaLeague.getListOfTeams()) {
                    if (t.getTeamName().toLowerCase().equals(removeTeam.toLowerCase())) {
                        nbaLeague.removeTeam(t);
                        JOptionPane.showMessageDialog(null,
                                t.getTeamName() + " has been removed from NBA");
                        break;
                    }
                }
            }
        }
    }

    /**
     * Represents action to be taken when user wants to view all the teams on the NBA
     */
    private class ViewTeamsAction extends AbstractAction {
        ViewTeamsAction() {
            super("View the teams");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            playSound("buzzer.wav");
            desktop.add(new NBAleagueGUI(nbaLeague, GUI.this));
        }
    }

    /**
     * Represents action to be taken when user wants to save all the actions
     * (teams or players) made on the system.
     */
    private class SaveTeamsAction extends AbstractAction {
        SaveTeamsAction() {
            super("Save the teams to file");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            playSound("buzzer.wav");
            try {
                jsonWriter.open();
                jsonWriter.write(nbaLeague);
                jsonWriter.close();
                JOptionPane.showMessageDialog(null,
                        "Saved " + nbaLeague.getLeagueName() + " to " + JSON_STORE);
            } catch (FileNotFoundException f) {
                JOptionPane.showMessageDialog(null,
                        "Unable to write to file: " + JSON_STORE);
            }
        }
    }

    /**
     * Represents action to be taken when user wants to load the teams, players, and championships
     * from file onto the system.
     */
    private class LoadTeamsAction extends AbstractAction {
        LoadTeamsAction() {
            super("Load the teams from file");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            playSound("buzzer.wav");
            try {
                nbaLeague = jsonReader.read();
                JOptionPane.showMessageDialog(null,
                        "Loaded " + nbaLeague.getLeagueName() + " from " + JSON_STORE);
            } catch (IOException i) {
                JOptionPane.showMessageDialog(null,
                        "Unable to read from file: " + JSON_STORE);
            }
        }
    }

    /**
     * Represents action to be taken when user wants to quit the system.
     */
    private class QuitAction extends AbstractAction {
        QuitAction() {
            super("Quit");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            playSound("buzzer.wav");
            System.exit(0);
        }
    }

    /**
     * Helper to centre main application window on desktop
     */
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    /**
     * Helper to play a sound (audio feature)
     */
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
        }
    }

    /**
     * Represents action to be taken when user clicks desktop
     * to switch focus. (Needed for key handling.)
     */
    private class DesktopFocusAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            GUI.this.requestFocusInWindow();
        }
    }
}