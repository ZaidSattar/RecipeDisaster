package src.main.java;
import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * 
 * This is the class to display the main manu for the game.
 * It consist selection of : Start new game, previous game, tutorial, credits, and highscore table
 * 
 * @author Regina.Liang
 * @author Viktoriya.Li
 * @author Zaid.Sattar
 *
 */
public class menu extends JFrame {

	/** This is the panel to display the menu*/
	private JPanel panel;
	/** This is the button for user to click to play new game*/
	private JButton newGame;
	/** This is the button for user to load previous game*/
	private JButton previous;
	/** This is the button for user to see the tutorial*/
	private JButton tutorial;
	/** This is the button for user to see the highscore table*/
	private JButton table;
	/** This is the button for user to quit*/
	private JButton quit;
	/** This is the button to display the credits of this game*/
	private JButton credits;
	/** This is the background label*/
	private JLabel backgroundLabel;
	/** This is the label for the heading*/
	private OutlinedLabel headingLabel;
	/** This is the sound player*/
	private soundPlayer playSound;
	/** This is an identifier for the keyboard shortcuts, "m" is to idenfity menu*/
	private String page = "m";
	
	/**
	 * menu constructor. Construct a new menu object. 
	 * Construct the main menu for the game that allow user to navigate through various buttons
	 */
	public menu(User user, int level)  {
		
		// create a title of the window, set the window to max size and exit on close
		setTitle("Menu Page");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        
        // display game title and menu in the centre
        headingLabel = new OutlinedLabel("RECIPE DISASTER ");	// title text
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);			// title text is in the centre
        headingLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 58));		// font style and size for the title
                
        // same principle for the second title of the page
        OutlinedLabel headingLabel2 = new OutlinedLabel("Main Menu ");
        headingLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        headingLabel2.setFont(new Font("Trebuchet MS", Font.BOLD, 57));
        
        // create a label with an empty icon initially
        backgroundLabel = new JLabel();
        add(backgroundLabel);
        
        // create a panel
        panel = new JPanel(new GridBagLayout());
       
        // Add keyboard shortcuts
        keyboard kb = new keyboard(page);
        panel.addKeyListener(kb); // add keyboard listener
		panel.setFocusable(true); // allow to panel to receive keyboard key events
		panel.requestFocusInWindow(); // Request focus for the  panel
        
        
        // make the panel transparent so the image is visible
     	panel.setOpaque(false);
     	
     	// set the layout for the panel and add it in the centre
     	backgroundLabel.setLayout(new BorderLayout());
		backgroundLabel.add(panel, BorderLayout.CENTER);
		
		// spacing on the panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(15,15,15,15);
        gbc.anchor = GridBagConstraints.CENTER;

        // add the headingLabel, headingLabel2 to the panel
        panel.add(headingLabel, gbc);
        panel.add(headingLabel2, gbc);
        
        
        // create a soundPlayer instance
     	playSound = new soundPlayer();
     	
     	// loop play the background music
     	playSound.playSoundLoop("menu sound.wav");

        
        // display the menu buttons
        newGame = new JButton("   Start New Game   ");
        newGame.setFont(new Font("Trebuchet MS", Font.BOLD, 20));	// set font style and size for button text
        newGame.setPreferredSize(new Dimension(200, 50));			// set button size
        newGame.addActionListener(new ActionListener() {			// set the button function
            /**
             * actionPerformed method for the button: newGame
             * It will perform an action after the button being clicked
             * @param e
             */
            public void actionPerformed(ActionEvent e) {
                setVisible(false);		// hide the menu window
                new gameplay(user.getUsername(), "recipe0.csv");			// create and display the gameplay window
                
                try {
                	// play the button music
                	playSound.playSoundOnce("button sound.wav");
                } catch (Exception ex) {
                	System.out.print("Sound not found");
                }
            }
        });
        panel.add(newGame, gbc);		// add the button to the panel
        
        
        // same principle for the rest of the buttons

        previous = new JButton("   Previous  Game   ");
        previous.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        previous.setPreferredSize(new Dimension(200, 50));
        previous.addActionListener(new ActionListener() {
        	/**
             * actionPerformed method for the button: previous
             * It will perform an action after the button being clicked
             * @param e
             */
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Hide the current window
                new LevelSelect(user); // Open the Level Select window

                try {
                    // play the button music
                    playSound.playSoundOnce("button sound.wav");
                } catch (Exception ex) {
                    System.out.print("Sound not found");
                }
            }
        });
        panel.add(previous, gbc);



        tutorial = new JButton("         Tutorial         ");
        tutorial.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        tutorial.setPreferredSize(new Dimension(200, 47));
        
        tutorial.addActionListener(new ActionListener() {
        	/**
             * actionPerformed method for the button: tutorial
             * It will perform an action after the button being clicked
             * @param e
             */
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);
        		new tutorialPage(user);
        		
        		try {
        			// play the button music
        			playSound.playSoundOnce("button sound.wav");
                } catch (Exception ex) {
                	System.out.print("Sound not found");
                }
        	}
        	
        });
        panel.add(tutorial, gbc);
        
        
        table = new JButton(" Highscore  Table ");
        table.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        table.setPreferredSize(new Dimension(200, 50));
        table.addActionListener(new ActionListener() {
        	/**
             * actionPerformed method for the button: table
             * It will perform an action after the button being clicked
             * @param e
             */
            public void actionPerformed(ActionEvent e) {
                setVisible(false); 
                new HighscorePage(user); 
                
                try {       	
                	// play the button + congrats sounds
                	playSound.playSoundOnce("congrats sound.wav");
                	playSound.playSoundOnce("button sound.wav");
                	
                	
                	
                } catch (Exception ex) {
                	System.out.print("Sound not found");
                }
            }
        });
        panel.add(table, gbc);
        
        /**
         * actionPerformed method for the button: credits
         * It will perform an action after the button being clicked
         * @param e
         */
        credits = new JButton("Credits");
        credits.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        credits.setPreferredSize(new Dimension(195, 46));
        credits.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		new Credit(user);{
        		try {
        			// play the button music
        			playSound.playSoundOnce("button sound.wav");
                } catch (Exception ex) {
                	System.out.print("Sound not found");
                }
        	}
        	}
        	
        });
        panel.add(credits, gbc);
        
        quit = new JButton("Quit");
        quit.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        quit.setPreferredSize(new Dimension(195, 46));
        quit.addActionListener(new ActionListener() {
        	/**
             * actionPerformed method for the button: quit
             * It will perform an action after the button being clicked
             * @param e
             */
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        	
        });
        panel.add(quit, gbc);
        
        
        addComponentListener(new ComponentAdapter() {
        	/**
             * method for accessing and stretching the image to fit full screen
             * Add a ComponentListener to the JFrame
             * @param e
             */
            public void componentResized(ComponentEvent e) {
            	
            	// try-catch block in case there's no picture
				try {
					// scale the image icon every time the JFrame is resized
	            	// use the relative path to the file
	            	ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("main menu.png")).getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT));
	            	backgroundLabel.setIcon(imageIcon);
				} catch (NullPointerException exception) {
					System.out.println("Image not found");
				}
             
            }
        });
        
        
        setVisible(true);
        
	}
	
	/**
	 * main method
	 * @param args
	 */
	public static void main(String[] args) {
//		Users u = new Users(true);
//		User user = u.getUser("pinkiepie");
//		menu menu = new menu(user,u.getUser("pinkiepie").getLevel() );
//		new menu(user, 3);
	}

}