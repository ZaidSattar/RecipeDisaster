package src.main.java;

import src.main.java.soundPlayer;
import src.main.java.OutlinedLabel;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * A JFrame subclass to display credits for a game.
 * 
 * @author zaid.sattar
 * @author viktoriya.li
 */
public class Credit extends JFrame {
	private JButton menu; // Button to return to the main menu
	private soundPlayer playSound; // Object to play sound effects
	private OutlinedLabel headingLabel; // Label for displaying the heading
	private JLabel backgroundLabel; // Label for displaying background image
	private JTextPane creditText; // Text pane for displaying credits
	private String page = "c"; // String indicating the current page

	/**
	 * Constructs the Credit frame.
	 */
	public Credit(User user) {
		setTitle("Credits"); // Set title of the frame
		setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to maximize the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation when the frame is closed

		// Display game title and menu in the center
		headingLabel = new OutlinedLabel("CREDITS: "); // Title text
		headingLabel.setHorizontalAlignment(SwingConstants.CENTER); // Title text is centered
		headingLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 48)); // Font style and size for the title

		// Create a label with an empty icon for background image
		backgroundLabel = new JLabel();
		add(backgroundLabel);

		// Create a panel to center the table and title within the window
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setOpaque(false);

		// Add keyboard shortcuts
		keyboard kb = new keyboard(page);
		panel.addKeyListener(kb); // Add keyboard listener
		panel.setFocusable(true); // Allow the panel to receive keyboard key events
		panel.requestFocusInWindow(); // Request focus for the panel

		// Set the layout for the panel and add it in the center
		backgroundLabel.setLayout(new BorderLayout());
		backgroundLabel.add(panel, BorderLayout.CENTER);

		// Spacing on the panel
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.NONE;

		gbc.gridy = 0;
		gbc.insets = new Insets(5, 0, 20, 0);
		gbc.anchor = GridBagConstraints.NORTH;

		// Add the title on the panel
		panel.add(headingLabel, gbc);

		creditText = new JTextPane();
		StyledDocument doc = creditText.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);

		// Set the text for the credits
		creditText.setText("TEAM 10:" + "\nMolick Hou, Viktoriya Li, Regina Liang\nZaid Sattar, Collin Zhong"
				+ "\nCreated: Winter 2024\nThis game project was created as part of the CompSci 2212 Course at Western University."
				+ "\nAll code was produced by the team members listed above." + "\n"
				+ "\nArt: all images used for background were AI generated with the help of the following website: gencraft.com."
				+ "\nThe arts for ingredients are drawn by Molick Hou and Regina Liang"
				+ "\nMusic: all background music and soundeffects were sourced from the following media repository: OpenGameArt.org."
				+ "\n List of music tracks and soundeffects:"
				+ "\n'Level Complete Splash' by GreyFrogGames licensed CC0: https://opengameart.org/content/level-complete-splash."
				+ "\n'UI Sound Effects (Button Clicks, User Feedback, Notifications)' by Robin Lamb licensed CC0: "
				+ "\nhttps://opengameart.org/content/ui-sound-effects-button-clicks-user-feedback-notifications."
				+ "\n'Menu Loop' by Akikazer licenced CC0: https://opengameart.org/content/menu-loop."
				+ "\n'Hi Score' by soled licenced CC-BY-SA 4.0: https://opengameart.org/content/hi-score.");

		creditText.setFont(new Font("Trebuchet MS", Font.BOLD, 16)); // Set the font for the credits label
		creditText.setEditable(false); // Make the JTextPane non-editable
		creditText.setOpaque(false); // Make the JTextPane transparent
		creditText.setAlignmentX(Component.CENTER_ALIGNMENT);

		playSound = new soundPlayer(); // Initialize sound player

		menu = new JButton("Main Menu"); // Button to return to the main menu
		menu.setFont(new Font("Trebuchet MS", Font.BOLD, 20)); // Set font for the button
		menu.setPreferredSize(new Dimension(200, 50)); // Set preferred size for the button

		// Action listener for the menu button
		menu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); // Hide the credits window
				new menu(user, user.getLevel()); // Assuming 'menu' is a class that extends JFrame or similar

				try {
					playSound.playSoundOnce("button sound.wav"); // Play button click sound
				} catch (Exception ex) {
					System.out.println("Sound not found");
				}
			}
		});

		gbc.gridy = 1;
		gbc.insets = new Insets(0, 0, 50, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(creditText, gbc);

		gbc.gridy = 2;
		gbc.insets = new Insets(0, 0, 50, 0);
		gbc.fill = GridBagConstraints.NONE;
		panel.add(menu, gbc);

		backgroundLabel.add(panel, BorderLayout.CENTER);

		setVisible(true); // Set frame visible

		// Add a ComponentListener to the JFrame to resize background image
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				try {
					// Scale the image icon every time the JFrame is resized
					// Use the relative path to the file
					ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("credits.png")).getImage()
							.getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT));
					backgroundLabel.setIcon(imageIcon);
				} catch (NullPointerException exception) {
					System.out.println("Image not found");
				}
			}
		});
	}

	/**
	 * Main method to create and display the Credit frame.
	 * 
	 * @param args Command-line arguments (not used).
	 */
	public static void main(String[] args) {
//		SwingUtilities.invokeLater(() -> new Credit());
	}
}
