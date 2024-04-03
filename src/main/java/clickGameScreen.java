package src.main.java;

import java.awt.Container;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Represents a game screen for a clicking game.
 * Manages the display of clickable labels and tracks user score and success conditions.
 * 
 * @author Molick Hou
 * 
 */
public class clickGameScreen extends JFrame {
   	public Timer timer; // Timer for the game
    private JLabel timeLabel; //Label for timer
    private ArrayList<ClickableLabel> items; // List of clickable labels
    public int PageScore; // Total score for the page
    private int UserInitialScore; // Initial score of the user
    private int UserSuccessScore; // Success score threshold
    private static final int ITEM_WIDTH = 100; // Width of the clickable item
    private static final int ITEM_HEIGHT = 100; // Height of the clickable item
    private static final int MIN_GAP = 100; // Minimum gap between items

    /**
     * Constructs a clickGameScreen object.
     *
     * @param username      The username of the current user.
     * @param time          The time duration for the game timer.
     * @param filename      The filename of the recipe file.
     * @param backgroundLabel   The JLabel for the background of the game screen.
     */
    public clickGameScreen(String username, int time, String filename, JLabel backgroundLabel) {
//        JFrame frame = new JFrame("Select Ingredient");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	
        //get the user from csv file
        Users u = new Users(true);
        User user = u.getUser(username);
        
        Recipe recipe = new Recipe(new File(filename));
        List<String> items = new ArrayList<>();
        ArrayList<String> food = CSVReader.readCSV("ingredients.csv");
        ArrayList<Integer> ingredientInt = recipe.getIngredients();
        ArrayList<String> ingredients = new ArrayList();
        for (int i = 0; i < ingredientInt.size(); i++) {
        	ingredients.add(food.get(ingredientInt.get(i)));
        }
        
        
        for (int i = 0; i < ingredients.size(); i++) {
        	items.add(ingredients.get(i));
        }
        
        for (int i = 0; items.size() < 14; i++) {
            if (!ingredients.contains(food.get(i))) items.add(food.get(i));
        }
        Collections.shuffle(items); // Shuffle the list to get a random order
        backgroundLabel.setLayout(null);
        
        PageScore = ingredients.size() * 5; //the score gained by click the right ingredient
        System.out.println(PageScore);
        UserInitialScore = user.getScore();
        this.UserSuccessScore = PageScore + this.UserInitialScore;
                
        //Get the screen dimensions
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        int screenWidth = gs[0].getDisplayMode().getWidth();
        int screenHeight = gs[0].getDisplayMode().getHeight();

        // Calculate the maximum number of items per row and column based on item size and gap
        int maxItemsPerRow = 5;
        int maxItemsPerColumn = 3;

        // Calculate the starting position for items
        int startX = 2*MIN_GAP;
        int startY = 2*MIN_GAP;
        
        int maxX = screenWidth - ITEM_HEIGHT - MIN_GAP;
        int maxY = screenHeight - ITEM_HEIGHT - MIN_GAP;
        
        //timer = new Timer(time, backgroundLabel, screenWidth-150, 10); // Create and start the timer
        timeLabel = new JLabel("0:"+ time);
        timeLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
        timeLabel.setHorizontalAlignment(JLabel.RIGHT);
        timeLabel.setBounds(screenWidth-150, 10, 100, 20);
        backgroundLabel.add(timeLabel);

        timer = new Timer(1000, new ActionListener() {
            int timeLeft = time; 

            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                if (timeLeft >= 0) {
                    int minutes = timeLeft / 60;
                    int seconds = timeLeft % 60;
                    timeLabel.setText(String.format("%d:%02d", minutes, seconds));
                } else {
                    timer.stop();
                    //showTimeUpWindow();
                    if (!isPass(user)) {
                    	showMessage();
                    	user.setScore(UserInitialScore);;
                        user.updateUser();
                    	switchToClickGameScreen(username,time,filename,backgroundLabel);
                    } else {
                    	System.out.println("To finish screen");
                    	switchToFinishGameScreen(username,filename,backgroundLabel);
                    }
                    
                }
            }
        });
        timer.start();
        
        
        
        int itemIndex = 0;
        int x,y;
        for (int row = 0; itemIndex < items.size() && row < maxItemsPerColumn && row < maxX; row++) {
            for (int col = 0; itemIndex < items.size() && col < maxItemsPerRow && col < maxX; col++) {
                String itemName = items.get(itemIndex);
                x = startX + col * (ITEM_WIDTH + MIN_GAP);
                y = startY + row * (ITEM_HEIGHT + MIN_GAP);

                ClickableLabel label;
				if (ingredients.contains(itemName)) label = new ClickableLabel(itemName, user, x, y);
                else label = new ClickableLabel(itemName, x, y);
				backgroundLabel.add(label);
                itemIndex++;
            }
        }
        
        backgroundLabel.setSize(screenWidth, screenHeight);
        backgroundLabel.setVisible(true);

    }
    
  	/**
     * Gets the success score threshold for the game.
     *
     * @return The success score threshold.
     */
    public int getSuccessScore() {
    	return this.UserSuccessScore;
    }
    
  	/**
     * Checks if the user has passed the game (scored enough points).
     *
     * @return True if the user has passed, false otherwise.
     */
    public boolean isPass(User username) {
    	return (username.getScore() >= this.UserSuccessScore);
    }
    
    /**
     * Switches to the memory game screen if yuser didn't pass
     *
     * @param username       The username of the user.
     * @param timeleft       The time left in the game.
     * @param filename       The filename of the CSV file.
     * @param backgroundLabel The background label component.
     */
    private void switchToClickGameScreen(String username, int timeleft, String filename, JLabel backgroundLabel) {
    	try {
    		backgroundLabel.removeAll();
        	memoryGameScreen Screen = new memoryGameScreen(username,timeleft,filename,backgroundLabel);
            backgroundLabel.add(Screen);
            this.dispose(); // Close the clickGameScreen
    	}catch(Exception e) {
    		System.out.print(e);
    	}
    }
    
    /**
     * Switches to the finish game screen.
     *
     * @param username       The username of the user.
     * @param filename       The filename of the CSV file.
     * @param backgroundLabel The background label component.
     */
    public void switchToFinishGameScreen(String username, String filename, JLabel backgroundLabel) {	
    	try {
    		backgroundLabel.removeAll();
        	finishGameScreen screen = new finishGameScreen(username,filename,backgroundLabel);
        	backgroundLabel.add(screen);
        	this.dispose();
    	}catch(Exception e) {
    		System.out.print(e);
    	}
    }
    

    /**
     * Displays a message dialog.
     */
    private void showMessage() {
        JOptionPane.showMessageDialog(null, "You didn't finish the game, try again.");
    }
}

