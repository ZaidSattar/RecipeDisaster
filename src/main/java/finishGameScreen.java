package src.main.java;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.*;

/**
 * Represents the finish game screen that displays a congratulatory message
 * and updates the user's level.
 * This screen appears when the user completes a level in the game.
 * It congratulates the user and updates their level in the system.
 * 
 * @author Molick Hou
 * 
 */
public class finishGameScreen extends JFrame {

    /**
     * Constructs a new finish game screen.
     * 
     * @param username       The username of the user.
     * @param filename       The filename of the CSV file.
     * @param backgroundLabel The background label component.
     */
    public finishGameScreen(String username, String filename, JLabel backgroundLabel) {

        // Get the user from CSV file
        Users userHandler = new Users(true);
        User user = userHandler.getUser(username);

        // Increment user's level and update user data
        int newLevel = user.getLevel() + 1;
        user.setLevel(newLevel);
        user.updateUser();
        int completedLevel = user.getLevel();
        new LevelFinished("Level" + completedLevel);


        // Get screen dimensions
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        int screenWidth = gs[0].getDisplayMode().getWidth();
        int screenHeight = gs[0].getDisplayMode().getHeight();

        // Create and format congratulatory message label
        JLabel finish = new JLabel("<html><div style='text-align: center;'>Congratulations! <br/> You are a smart cookie <br/> and you have completed this level!</div></html>");
        finish.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
        finish.setForeground(Color.DARK_GRAY);
        finish.setHorizontalAlignment(SwingConstants.CENTER);
        finish.setBounds(300, 400, 700, 100);

        // Add the message label to the background label
        backgroundLabel.add(finish);
        
        JButton menuButton = new JButton("back to Main Menu");
        menuButton.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        menuButton.setBounds(600, 600, 200, 50);
        menuButton.addActionListener(e -> new menu(user, user.getLevel()));
        backgroundLabel.add(menuButton);

        // Set background label size and visibility
        backgroundLabel.setSize(screenWidth, screenHeight);
        backgroundLabel.setVisible(true);
        
        

    }
}
