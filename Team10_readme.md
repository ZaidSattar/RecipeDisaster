# CS2212 Group Project by Team 10

# Game name: "Recipe Disaster"

## Description
The purpose of this project is to create an educational game that would teach players some basics of cooking, as well as some recipes for simple dishes.

## Requirements
Java IDE (preferably latest version), and the computer device to run the program on. The game can run on both Windows and MacOS.

## Installation
The game requires an installation of any IDE that supports Java language (e.g. Eclipse, NetBeans etc). No external libraries are required, however, most of the files require additional imports. Some of the imports include: swing, BorderLayout, Font, GridBagConstraints, GridBagLayout, ActionEvent, ActionListener, ComponentAdapter, ComponentEvent, ComponentAdapter, ComponentEvent etc. All imports are already included within the files, no additional installations are required.

## Controls
Player uses the touch panel or mouse to navigate on the screen and click buttons. Keyboard shortcuts were added for additional control:
-esc: escape button to quit the game by closing the window at any point.
-enter/return: hit enter button when logging in.

## How it Works
Once all the files are placed in correct location, the user starts the game by running the "InitialPage.java" class. From there, the user is presented with the user type drop down menu, registration, and login option. The user must select the user type first:
-Player(default user type): players must register by putting in a unique username and an instructor key. If a player does not have the instructor ID, they must type "-1" (message displayed on the screen for clarity). Once registered, they can log in with their username.

-Instructor: instructors must register by putting in a unique username and an instructor key. When the instructor logs in, they must put their instructor ID in the appropriate field box, and it will lead them to the "Instructor Mode" page immediately. Instructors cannot play the game, they can only observe the progres of the players.

-Developer: developers must register by by putting in a unique username and password. Password feature is implemented for information hiding and debug purposes. Developers have the same access type as players with one exception: developers are able to select and play any level they choose from the "Previous Game" page for debug purposes.

If registration or log in process fails, a message is displayed explaining that and asking the user to try again.

Once logged in, players (or developers) are directed to the main menu page, where they are presented with the following button options:
-Start new game: to start playing the first level.
-Previous game: to view all the levels that the player has played previously.
-Tutorial: to view the tutorial for the game.
-Highscore table: to view the top 10 players with highest scores.
-Credits: to view the credits for the code, artwork, music, sounds.
-Quit: to quit the game and close the window.

Each level is a recipe that is shown to a player for a limited amount of time. The player must memorize all the ingredients. Once the timer runs out, the player is directed to the next page, where they must choose all the ingredients required for the recipe by clicking on the appropriate icons within the limited time. If the player fails to pick all the ingredients, the level is restarted, and the player is shown the recipe again. Once the level is finished, player is given certain number of scores.

## Additional files
Along with the source code files (.java), there are 3 types of additional files that are required for running the game:
-CSV: all the CSV files must be located outside of the src folder but within the same project folder.
-WAV: all the WAV files must be located inside the src folder.
-PNG: the following PNG files must be located inside the src folder: "main menu.png", "game.png", "credits.png", "highscore.png". The rest of the PNG files must be outside of the src folder.


## Arguments
No arguments are required to pass in the terminal in order to run the program and play the game.

# Authors
Viktoriya Li - vli233@uwo.ca

Regina Liang - rliang59@uwo.ca

Molick Hou - xhou54@uwo.ca

Collin Zhong - czhong33@uwo.ca

Zaid Muhammad Sattar - zsattar2@uwo.ca