# CS2212 Group Project by Team 10

## Game Name: "Recipe Disaster"

### Description
The purpose of this project is to create an educational game that teaches players some basics of cooking, as well as some recipes for simple dishes.

### Requirements
- **Java IDE** (preferably latest version)
- **Computer device** to run the program on (The game can run on both Windows and MacOS).

### Installation
The game requires an installation of any IDE that supports the Java language (e.g., Eclipse, NetBeans, etc.). No external libraries are required. However, most of the files require additional imports, such as `swing`, `BorderLayout`, `Font`, `GridBagConstraints`, `GridBagLayout`, `ActionEvent`, `ActionListener`, `ComponentAdapter`, `ComponentEvent`, etc. All imports are already included within the files; no additional installations are required.

### Controls
The player uses the touch panel or mouse to navigate on the screen and click buttons. Keyboard shortcuts were added for additional control:
- **esc**: Escape button to quit the game by closing the window at any point.
- **enter/return**: Hit enter button when logging in.

### How it Works
Once all the files are placed in the correct location, the user starts the game by running the `InitialPage.java` class. From there, the user is presented with the user type dropdown menu, registration, and login option. The user must select the user type first:
- **Player** (default user type): Players must register by putting in a unique username and an instructor key. If a player does not have the instructor ID, they must type "-1" (message displayed on the screen for clarity). Once registered, they can log in with their username.
- **Instructor**: Instructors must register by putting in a unique username and an instructor key. When the instructor logs in, they must put their instructor ID in the appropriate field box, and it will lead them to the "Instructor Mode" page immediately. Instructors cannot play the game; they can only observe the progress of the players.
- **Developer**: Developers must register by putting in a unique username and password. The password feature is implemented for information hiding and debug purposes. Developers have the same access type as players with one exception: developers are able to select and play any level they choose from the "Previous Game" page for debug purposes.

If registration or login process fails, a message is displayed explaining that and asking the user to try again.

Once logged in, players (or developers) are directed to the main menu page, where they are presented with the following button options:
- **Start new game**: To start playing the first level.
- **Previous game**: To view all the levels that the player has played previously.
- **Tutorial**: To view the tutorial for the game.
- **Highscore table**: To view the top 10 players with the highest scores.
- **Credits**: To view the credits for the code, artwork, music, sounds.
- **Quit**: To quit the game and close the window.

Each level is a recipe shown to a player for a limited amount of time. The player must memorize all the ingredients. Once the timer runs out, the player is directed to the next page, where they must choose all the ingredients required for the recipe by clicking on the appropriate icons within the limited time. If the player fails to pick all the ingredients, the level is restarted, and the player is shown the recipe again. Once the level is finished, the player is given a certain number of scores.

### Additional Files
Along with the source code files (`.java`), there are 3 types of additional files that are required for running the game:
- **CSV**: All the CSV files must be located outside of the src folder but within the same project folder.
- **WAV**: All the WAV files must be located inside the src folder.
- **PNG**: The following PNG files must be located inside the src folder: "main menu.png", "game.png", "credits.png", "highscore.png". The rest of the PNG files must be outside of the src folder.

### Arguments
No arguments are required to pass in the terminal in order to run the program and play the game.

## Authors
- Viktoriya Li
- Regina Liang
- Molick Hou 
- Collin Zhong 
- Zaid Muhammad Sattar 
