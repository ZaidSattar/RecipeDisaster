package src.main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Represents a clickable label with specific properties for click game item.
 * The label displays an image and responds to mouse clicks by showing messages and updating scores.
 * Extends JLabel.
 * @author Molick Hou
 * 
 */
public class ClickableLabel extends JLabel {
	private String name;
	private int score;
    private ImageIcon icon;
    private boolean inRecipe;
    private boolean untouched;
    private soundPlayer playSound = new soundPlayer();
    
    /**
     * Constructs a ClickableLabel for an item in the recipe.
     * The default score is set to 5 for items in the recipe.
     * 
     * @param name the name of the item (corresponding to the image filename without extension)
     * @param user the User object associated with the label
     * @param x the x-coordinate of the label's position
     * @param y the y-coordinate of the label's position
     */
    public ClickableLabel(String name, User user, int x, int y) {
        super(new ImageIcon(name+".png"));
        this.name = name;
        this.score = 5;
        this.icon = (ImageIcon) getIcon();
        this.inRecipe = true;
        this.untouched = true;
        setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
        addMouseListener(new MouseAdapter() {
            //mouse click
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (untouched) {
                	showFirstMessage(name);
                	untouched = false;
                    if (inRecipe) user.addScore(score);
                    user.updateUser();
                }else {
                	showSecondMessage(name);
                }
            }
        });
    }
    
    /**
     * Constructs a ClickableLabel for an item in the recipe with custom dimensions.
     * The default score is set to 5 for items in the recipe.
     * 
     * @param name the name of the item (corresponding to the image filename without extension)
     * @param user the User object associated with the label
     * @param x the x-coordinate of the label's position
     * @param y the y-coordinate of the label's position
     * @param itemWidth the width of the label
     * @param itemHeight the height of the label
     */
    public ClickableLabel(String name, User user, int x, int y, int itemWidth, int itemHeight) {
        super(new ImageIcon(name+".png"));
        this.name = name;
        this.score = 5;
        this.icon = (ImageIcon) getIcon();
        this.inRecipe = true;
        this.untouched = true;
        setPreferredSize(new Dimension(itemWidth, itemHeight));
        setBounds(x, y, itemWidth, itemHeight);
        addMouseListener(new MouseAdapter() {
            //mouse click
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (untouched) {
                	showFirstMessage(name);
                	untouched = false;
                    if (inRecipe) user.addScore(score);
                    user.updateUser();
                }else {
                	showSecondMessage(name);
                }
            }
        });
    }
    
    /**
     * Constructs a ClickableLabel for an item not in the recipe.
     * The score is set to 0 for items not in the recipe.
     * 
     * @param name the name of the item (corresponding to the image filename without extension)
     * @param x the x-coordinate of the label's position
     * @param y the y-coordinate of the label's position
     */
    public ClickableLabel(String name, int x, int y) {
    	super(new ImageIcon(name+".png"));
        this.name = name;
        this.score = 0;
        this.icon = (ImageIcon) getIcon();
        this.inRecipe = false;
        this.untouched = true;
        setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
        addMouseListener(new MouseAdapter() {
            //mouse click
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (untouched) {
                	showFirstMessage(name);
                	untouched = false;
                } else {
                	showSecondMessage(name);
                }
            }
        });
    }
    
    /**
     * Constructs a ClickableLabel with custom properties.
     * 
     * @param name the name of the item (corresponding to the image filename without extension)
     * @param score the score associated with the item
     * @param inRecipe true if the item is in the recipe, false otherwise
     * @param user the User object associated with the label
     * @param x the x-coordinate of the label's position
     * @param y the y-coordinate of the label's position
     */
    public ClickableLabel(String name, int score, boolean inRecipe, User user, int x, int y) {
    	super(new ImageIcon(name+".png"));
        this.name = name;
        this.score = score;
        this.icon = (ImageIcon) getIcon();
        this.inRecipe = inRecipe;
        this.untouched = true;
        setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
        addMouseListener(new MouseAdapter() {
            //mouse click
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (untouched) {
                	showFirstMessage(name);
                	untouched = false;
                    if (inRecipe) user.addScore(score);
                    user.updateUser();
                }else {
                	showSecondMessage(name);
                }
            }
        });
    }
    
    /**
     * Sets the location of the label.
     * 
     * @param x the new x-coordinate of the label's position
     * @param y the new y-coordinate of the label's position
     */
    public void setlocation(int x, int y) {
    	setBounds(x, y, 100, 100);
    }
    
    /**
     * Sets the image icon for the label.
     * 
     * @param icon the new image icon to set
     */
    public void setImage(ImageIcon icon) {
        this.icon = icon;
        setIcon(icon);
    }
    
    /**
     * Sets the score for the item.
     * 
     * @param score the new score to set
     */
    public void setScore(int score) {
    	this.score = score;
    }
    
    /**
     * Checks if the item is in the recipe.
     * 
     * @return true if the item is in the recipe, false otherwise
     */
    public boolean isInRecipe() {
    	return inRecipe;
    }
    
    /**
     * Displays the first message when the item is selected for the first time.
     * 
     * @param name the name of the item
     */
    private void showFirstMessage(String name) {
        JOptionPane.showMessageDialog(null, "You selected "+ name +".");
        playSound.playSoundOnce("click sound.wav");
    }
    
    /**
     * Displays the second message when the item is already selected.
     * 
     * @param name the name of the item
     */
    private void showSecondMessage(String name) {
        JOptionPane.showMessageDialog(null, "You already selected "+ name +". Careeful - too many ingredients can make you sick!");
        playSound.playSoundOnce("click sound.wav");
    }
    
    /**
     * Gets the score associated with the item.
     * 
     * @return the score of the item
     */
    public int getScore() {
    	return this.score;
    }

}
