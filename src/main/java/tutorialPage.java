package src.main.java;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import javax.sound.sampled.*;
import java.net.URL;

/**
 * This class is like the subpage for the game, to display the tutorial
 * This page is to be accessed from the menu page by clicking the button "tutorial" in menu
 * @author Regina Liang
 * @author Viktoriya Li
 * @author Zaid.Sattar
 *
 */
public class tutorialPage extends JFrame {

	/** These are the panels to display the content*/
	private JPanel contentPanel, navigationPanel, bottomPanel;
	/** These are the buttons for navigation, next, back, and a button back to the menu*/
	private JButton nextButton, backButton, menuButton;
	/** This is the indicators*/
	private JLabel[] indicators;
	/** This is the content label*/
	private JLabel contentLabel;
	/** This is the current slide number*/
	private int currentSlide = 0;
	/** This is the identifier for the tutorial page for keyboard shortcuts.*/
	private String page = "t";
	private User user;
	/** This is the username who login */

	/**
	 * tutorialPage constructor. Constructs the tutorial page for the game that display instructions of the game
	 * 
	 */
	
	public tutorialPage(User user) {
		this.user = user;
		setTitle("Tutorial Page");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BackgroundPanel backgroundPanel = new BackgroundPanel();
		setContentPane(backgroundPanel);
		backgroundPanel.setLayout(new GridBagLayout());

		OutlinedLabel headingLabel2 = new OutlinedLabel("HOW TO PLAY  ");
		headingLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		headingLabel2.setFont(new Font("Trebuchet MS", Font.BOLD, 48));

		GridBagConstraints gbcHeading = new GridBagConstraints();
		gbcHeading.gridwidth = GridBagConstraints.REMAINDER;
		gbcHeading.anchor = GridBagConstraints.NORTH;
		gbcHeading.insets = new Insets(-120, 0, 0, 0);
		backgroundPanel.add(headingLabel2, gbcHeading);

		contentPanel = new JPanel();
		contentPanel.setOpaque(false);
		contentPanel.setLayout(new BorderLayout());
		contentPanel.setPreferredSize(new Dimension(800, 300));
		contentLabel = new JLabel("The game will start with showing a few ingredients with some instructions.", SwingConstants.CENTER);
		contentPanel.add(contentLabel, BorderLayout.CENTER);

		bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setOpaque(false);
		contentPanel.add(bottomPanel, BorderLayout.SOUTH);

		navigationPanel = new JPanel();
		navigationPanel.setOpaque(false);
		navigationPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		bottomPanel.add(navigationPanel, BorderLayout.CENTER);

		backButton = new JButton("Back");
		navigationPanel.add(backButton);
		backButton.addActionListener(e -> {
			playSound("click sound.wav");
			if (currentSlide > 0) {
				currentSlide--;
				updateSlideContent();
				updateIndicators();
			}
		});
		
        

		indicators = new JLabel[4];
		for (int i = 0; i < indicators.length; i++) {
			indicators[i] = new JLabel("O");
			indicators[i].setFont(new Font("Serif", Font.BOLD, 24));
			navigationPanel.add(indicators[i]);
		}

		nextButton = new JButton("Next");
		navigationPanel.add(nextButton);
		nextButton.addActionListener(e -> {
			playSound("click sound.wav");
			navigateSlides();
		});

		menuButton = new JButton("Main Menu");
		bottomPanel.add(menuButton, BorderLayout.WEST);
		menuButton.addActionListener(e -> {
			setVisible(false);
			new menu(user, user.getLevel());
		});
		
		// Add keyboard shortcuts
        keyboard kb = new keyboard(page);
        bottomPanel.addKeyListener(kb); // add keyboard listener
        bottomPanel.setFocusable(true); // allow to panel to receive keyboard key events
        bottomPanel.requestFocusInWindow(); // Request focus for the  panel

		GridBagConstraints gbcContent = new GridBagConstraints();
		gbcContent.gridwidth = GridBagConstraints.REMAINDER;
		gbcContent.anchor = GridBagConstraints.CENTER;
		backgroundPanel.add(contentPanel, gbcContent);

		updateIndicators();
		setVisible(true);
	}

	/**
	 * Navigate the slides
	 */
	private void navigateSlides() {
		if (currentSlide < indicators.length - 1) {
			currentSlide++;
			updateSlideContent();
			updateIndicators();
		} else {
			setVisible(false);
			String username = user.getUsername();
			new gameplay(username,"recipe0.csv");
		}
	}

	/**
	 * This is to update the slide contents based on the current slide number
	 */
	private void updateSlideContent() {
	    String[] texts = {
	        "<html>The game will start by showing a recipe with ingredients and some additional cooking instructions.</html>",
	        "<html>The timer will start,<br>and you will need to memorize the ingredients.<br>Cooking instructions are provided for general information and education,</html>",
	        "<html>Once the timer for memorizing the ingredients runs out,<br>you will be directed to the next page with all possible ingredients for the recipe.<br>A new timer will begin, and you will need to select the ingredients that were specified in the recipe shown to you.</html>",
	        "<html>Select all the necessary ingredients before the new timer runs out.<br>If you succeed - the level is finished, and you can progress further! Otherwise, you will need to restart the level. :( </html>"
	    };
	    contentLabel.setText(texts[currentSlide]);
	}


	/**
	 * This is to indicate the update after some navigation or buttons clicked
	 */
	private void updateIndicators() {
		for (int i = 0; i < indicators.length; i++) {
			indicators[i].setText(i == currentSlide ? "●" : "○");
		}
		backButton.setEnabled(currentSlide > 0);
		nextButton.setText(currentSlide == indicators.length - 1 ? "Play Now" : "Next");
	}

	/**
	 * This is the playsound method for sound playing
	 * @param soundFileName
	 */
	private void playSound(String soundFileName) {
		try {
			URL url = getClass().getResource(soundFileName);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
			ex.printStackTrace();
		}
	}

	
	class BackgroundPanel extends JPanel {
		private BufferedImage image;

		
		/**
		 * This is to display the background for this page
		 */
		public BackgroundPanel() {
			try {
				image = ImageIO.read(getClass().getResource("game.png"));
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		
		/**
		 * * This method paints the component's background image, if available.\
		 * @param g the graphics context to use for painting
		 */
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (image != null) {
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		}
	}

	
	/**
	 * main method
	 * @param args
	 */
	public static void main(String[] args) {
//		SwingUtilities.invokeLater(tutorialPage::new);
	}
}
