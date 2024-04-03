package src.main.java;

import java.awt.Color;
import java.awt.Component;
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
 * Represents the memory game screen that displays ingredients, steps, and a timer.
 * 
 * @author Molick Hou
 * 
 */
public class memoryGameScreen extends JFrame {
   	public Timer timer; // Timer for the game
   	private JLabel timeLabel; //Label for timer
    private static final int ITEM_WIDTH = 100; // Width of the clickable item
    private static final int ITEM_HEIGHT = 100; // Height of the clickable item
    private static final int MIN_GAP = 20; // Minimum gap between items
    
    /**
     * Constructs a new memory game screen.
     *
     * @param username       The username of the user.
     * @param time           The time limit for the game.
     * @param filename       The filename of the CSV file.
     * @param backgroundLabel The background label component.
     */
    public memoryGameScreen(String username, int time, String filename, JLabel backgroundLabel) {
    	
        Recipe recipe = new Recipe(new File(filename));
        ArrayList<String> food = CSVReader.readCSV("ingredients.csv");
        ArrayList<Integer> ingredientInt = recipe.getIngredients();
        ArrayList<String> ingredients = new ArrayList();
        for (int i = 0; i < ingredientInt.size(); i++) {
        	ingredients.add(food.get(ingredientInt.get(i)));
        }
        
        ArrayList<Integer> stepsInt = recipe.getSteps();
        ArrayList<String> process = CSVReader.readCSV("steps.csv");
        ArrayList<String> steps = new ArrayList();
        for (int i = 0; i < stepsInt.size();i++) {
        	steps.add(process.get(stepsInt.get(i)));
        }
        backgroundLabel.setLayout(null);
                
        //Get the screen dimensions
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        int screenWidth = gs[0].getDisplayMode().getWidth();
        int screenHeight = gs[0].getDisplayMode().getHeight();

        // Calculate the maximum number of items per row and column based on item size and gap
        int maxItemsPerRow = 3;
        int maxItemsPerColumn = 3;

        // Calculate the starting position for items
        int startX = 8*MIN_GAP;
        int startY = 12*MIN_GAP;
        
        int midX = screenWidth/2;
        int midY = screenHeight/2;
        
        JLabel titleIngredients = new JLabel("Ingredients");
        titleIngredients.setFont(new Font("Arial", Font.BOLD, 24));
        titleIngredients.setForeground(Color.DARK_GRAY);
        titleIngredients.setBounds(startX + 4*MIN_GAP, startY - 4*MIN_GAP, 300, 100);
        backgroundLabel.add(titleIngredients);
        
        //timer = new Timer(time, backgroundLabel, screenWidth-150, 10, filename,username,1); // Create and start the timer
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
                    switchToClickGameScreen(username,time,filename,backgroundLabel);
                }
            }
        });
        timer.start();
        
        int itemIndex = 0;
        int x,y;
        for (int row = 0; itemIndex < ingredients.size() && row < maxItemsPerColumn; row++) {
            for (int col = 0; itemIndex < ingredients.size() && col < maxItemsPerRow; col++) {
                String itemName = ingredients.get(itemIndex);
                x = startX + col * (ITEM_WIDTH + MIN_GAP);
                y = startY + row * (ITEM_HEIGHT + MIN_GAP + 20);
                
                ImageIcon icon = new ImageIcon(itemName+".png");
                JLabel label = new JLabel(icon);
                label.setBounds(x, y, 100, 100);
				backgroundLabel.add(label);
				JLabel name = new JLabel(itemName);
				name.setFont(new Font("Arial", Font.ITALIC, 15));
				name.setBounds(x+30, y+100, 70, 20);
				backgroundLabel.add(name);
                itemIndex++;
            }
        }
        
        JLabel titleSteps = new JLabel("Steps - Double Click each of these");
        titleSteps.setFont(new Font("Arial", Font.BOLD, 24));
        titleSteps.setForeground(Color.darkGray);
        titleSteps.setBounds(midX + 4*MIN_GAP, startY - 4*MIN_GAP, 500, 100);
        backgroundLabel.add(titleSteps);
        
        int locx,locy;
        for (int i = 0; i < steps.size(); i++) {
        	String stepName = steps.get(i);
            System.out.println(stepName);
            locx = midX - 4* 20;
            locy = startY + i * 50;
            
            JLabel label = new JLabel(stepName);
            label.setFont(new Font("Arial", Font.ITALIC, 20));
            label.setBounds(locx, locy, 850, 100);
			backgroundLabel.add(label);
        }
        
        
        backgroundLabel.setSize(screenWidth, screenHeight);
        backgroundLabel.setVisible(true);

    }
    
    /**
     * Switches to the click game screen.
     *
     * @param username       The username of the user.
     * @param timeleft       The time left in the game.
     * @param filename       The filename of the CSV file.
     * @param backgroundLabel The background label component.
     */
    private void switchToClickGameScreen(String username, int timeleft, String filename, JLabel backgroundLabel) {
    	backgroundLabel.removeAll();
    	clickGameScreen Screen = new clickGameScreen(username,timeleft,filename,backgroundLabel);
    	try {
            backgroundLabel.add(Screen);
            this.dispose(); // Close the clickGameScreen
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
