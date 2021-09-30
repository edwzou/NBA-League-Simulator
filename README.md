# My Personal Project

For my personal project I'm going to make an NBA team tracker that will store all the information about the players of 
the 30 teams in NBA including each of their game performance (i.e. points per game, rebounds per game, assists per game, 
field goal percentage, free throw percentage), their health status (healthy or injured). The tracker will also store 
information about the team such as the name of the team, the number of players on the team, the amount of games that 
the team won/lost, and the amount of championships the team has won. The user will be able to search a player's name, 
the stats of that player (game performance and health status) will be returned. If the user searches a team, then the 
team stats will be returned (team name, number of players in team, games won and lost, amount of championships won). 

This application can be used for anyone who's interested to know how his or her favourite NBA player is performing in 
games and how his or her favourite NBA team is performing in the league. 

This project interests me as I'm a big fan of NBA and I've always wanted to be able 
to keep track of the statues of players and their game schedule.

## User Stories

- As a user, I want to be able to create players and set their game performance and health status
- As a user, I want to be able to create teams and set the team's stats and championships wins
- As a user, I want to be able to add players into a team
- As a user, I want to be able to see all the teams I've added and the stats of each team
- As a user, I want to be able to select a team and view a list of players on that team
- As a user, I want to be able to select a team and view all the player's stats of that team
- As a user, I want to be able to add teams to the NBA
- As a user, I want to be able to create championships
- As a user, I want to be able to add championships to a team
- As a user, I want to be able to view the stats of the championship (i.e. year won)
- As a user, I want to be able to remove teams from the NBA
- As a user, I want to be able to remove players from a team
- As a user, I want to be able to remove championships from a team
- As a user, I want to be able to save my NBA league and all the information in it (i.e. players on a team)
- As a user, when I open the program, I want to be able to load my NBA league from where I left of last time
- As a user, when I open the program, I want to be given a choice of either loading previous NBA or start a new one
- As a user, I want to be able to add multiple Xs to a Y
- As a user, I want to be able to load and save the state of the application

## Phase 4: Task 2

I've chosen to make the NBAplayer class more robust by throwing a NegativeStats exception for setPointsPerGame(), 
setReboundsPerGame(), setAssistsPerGame(), setFieldGoalPercentage(), and setFreeThrowPercentage() and then having the
createAndAddPlayer() method in NBA class to catch those exceptions. This makes my program more robust because now 
I no longer need a requires clause for all the set methods listed above to ensure that the value passed in is not
negative.

## Phase 4: Task 3

I don't think there's any refactorings for me to do as all of my classes obey to the single responsibility principle