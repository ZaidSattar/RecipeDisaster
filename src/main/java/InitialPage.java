package src.main.java;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
/**
 * The InitialPage class represents the main login and registration page for the Recipe Disaster game.
 * It provides functionality for users to log in based on their user type (Player, Instructor, Developer)
 * and navigate to registration if needed.
 * 
 * @author Regina.Liang
 * @author Viktoriya.Li
 * @author Zaid.Sattar
 */
public class InitialPage extends JFrame implements KeyListener {

	
//	private static final long serialVersionUID = 1L;
	/**This is the mainPanel for the page, as well as the userDetail panel for things like user inputs*/
	private JPanel mainPanel, userDetailPanel;
	/** This is the userTypeComboBox for user types*/
	private JComboBox<String> userTypeComboBox;
	/**This is the outlined label for the heading*/
	private OutlinedLabel headingLabel;
	/** This is the label for the background*/
	private JLabel backgroundLabel;
	/** These are the buttons for register and login*/
	private JButton registerButton, loginButton;
	/** This is a textArea (rectangular) providing information to user*/
	private JTextArea askReg;
	/** This is the sound player to play sound*/
	private soundPlayer playSound;
	/** This is the textfield for username (user can type in their username)*/
	private JTextField usernameField;
	/** This is the textfield for id (player and instructor can type in their id or -1 if player don't have one)*/
	private JTextField idField;
	/** This is the textfield for password (developer can type in their password)*/
	private JPasswordField passwordField;
	/** This is a boolean variable to check if the account is correct*/
	private boolean correctAccount;

	// RGB values to create a new colour
	private Color darkCyan = new Color(80, 128, 130);

	/**
     * Constructs a new InitialPage instance.
     */
	public InitialPage() {
		Users u = new Users(true);

		initializeGUI();
	}

	/**
     * Initializes the graphical user interface of the InitialPage.
     */
	private void initializeGUI() {
		setTitle("Recipe Disaster");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout(10, 10));

		// create a label with an empty icon for background image
		backgroundLabel = new JLabel();
		add(backgroundLabel);
		backgroundLabel.setLayout(new BorderLayout());

		// create heading label, set alignment on the page and the font size, style etc
		headingLabel = new OutlinedLabel("RECIPE DISASTER");
		headingLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 90));
		headingLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		headingLabel.setHorizontalAlignment(SwingConstants.CENTER);

		// add the headinglabel to the background at the top
		backgroundLabel.add(headingLabel, BorderLayout.NORTH);

		// create a main panel and make it transparent so the image is visible
		// set the borders using gbc spacing
		mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setOpaque(false);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 200, 80, 200));
		mainPanel.addKeyListener(this); // Add the keyboard listener to the main panel
		mainPanel.setFocusable(true); // Make the main panel focusable to receive key events
		mainPanel.requestFocusInWindow(); // Request focus for the main panel

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// add the main panel to the background
		backgroundLabel.add(mainPanel);

		// create a second panel for the user selection
		// add background to it, text, text style etc
		JPanel comboBoxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		comboBoxPanel.setBackground(darkCyan);

		JLabel userTypeLabel = new JLabel("Select User Type:");
		userTypeLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		userTypeLabel.setForeground(Color.white);

		// add the user types strings
		String[] userTypes = { "Player", "Instructor", "Developer" };
		userTypeComboBox = new JComboBox<>(userTypes);

		userTypeComboBox.setSelectedItem("Player");

		// Keyboard shortcut added
		userTypeComboBox.addKeyListener(this);
		userTypeComboBox.addKeyListener(this);
		userTypeComboBox.setFocusable(true);
		userTypeComboBox.requestFocusInWindow();

		// create a register button and set font style, size etc
		registerButton = new JButton("Register");
		registerButton.setFont(new Font("Trebuchet MS", Font.BOLD, 15));

		// create a soundPlayer instance
		playSound = new soundPlayer();

		// create an actionListener for the button
		// play a sound once pressed
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Register usertype: " + getUserType());
				registerPage registerFrame = new registerPage(getUserType());
				registerFrame.setVisible(true);
				setVisible(false);
				// try-catch block in accase the sound file not found
				try {

					playSound.playSoundOnce("button sound.wav");

				} catch (Exception ex) {
					System.out.println("Sound not found");
				}
			}
		});

		// add the labels and created buttons to the second panel
		comboBoxPanel.add(userTypeLabel);
		comboBoxPanel.add(userTypeComboBox);
		comboBoxPanel.add(registerButton);

		// add the second panel to the main panel, use gbc for vertical spacing
		mainPanel.add(comboBoxPanel, gbc);
		gbc.weighty = 1;

		// create a new panel, set spacing using gbc, set font style, size etc
		// add it to the main panel
		userDetailPanel = new JPanel(new GridBagLayout());
		userDetailPanel.setOpaque(false);
		userDetailPanel.setBorder(BorderFactory.createEmptyBorder(120, 0, 0, 0));
		userDetailPanel.setLayout(new BoxLayout(userDetailPanel, BoxLayout.Y_AXIS));
		mainPanel.add(userDetailPanel, gbc);

		// create an actionListener to select strings
		userTypeComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateUserDetailsPanel(userTypeComboBox.getSelectedItem().toString());
			}
		});

		updateUserDetailsPanel(userTypeComboBox.getSelectedItem().toString());

		// method for accessing and stretching the image to fit full screen
		// add a ComponentListener to the JFrame
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				// try-catch block in case there's no picture
				try {

					// scale the image icon every time the JFrame is resized
					// use the relative path to the file
					ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("initial page.png"))
							.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH));
					backgroundLabel.setIcon(imageIcon);
				} catch (NullPointerException exception) {
					System.out.println("Image not found");
				}
			}
		});

	}

	/**
     * Updates the user details panel based on the selected user type.
     * 
     * @param userType The selected user type.
     */
	private void updateUserDetailsPanel(String userType) {

		userDetailPanel.removeAll();

		// set spacing using gbc
		userDetailPanel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		if (!"Select".equals(userType)) {

			// ad vertical glue to move components to the center vertically
			userDetailPanel.add(Box.createVerticalGlue());

			// create a new panel at the centre, add background panel color, fix spacing
			JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 50));
			inputPanel.setBackground(darkCyan);
			inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

			// Keyboard
			inputPanel.addKeyListener(this);
			inputPanel.setFocusable(true); // Make the panel focusable to receive key events
			inputPanel.requestFocusInWindow(); // Request focus for the panel

			// create a label, set font style, size etc
			// set the spacing at the center horizontally
			JLabel usernameLabel = new JLabel("Username:");
			usernameLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));

			usernameLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
			usernameLabel.setForeground(Color.white);
			usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			inputPanel.add(Box.createHorizontalGlue());
			inputPanel.add(usernameLabel);
			inputPanel.add(Box.createHorizontalGlue());

			// create fields for username, ID and password, and add them to the panel
			// set the dimensions for the fields
			usernameField = new JTextField(20);
			usernameField.addKeyListener(this);
			usernameField.setFocusable(true);// Make the usernameField focusable to receive key events
			usernameField.requestFocusInWindow();// Request focus for the main panel
			usernameField.setPreferredSize(new Dimension(200, 35));
			inputPanel.add(usernameField);

			idField = new JTextField(10);
			passwordField = new JPasswordField(10);
			passwordField.addKeyListener(this); // add KeyListener to passwordField
			passwordField.setFocusable(true);// Make the passwordField focusable to receive key events
			passwordField.requestFocusInWindow();// Request focus for the main panel
			passwordField.setPreferredSize(new Dimension(200, 35));

			if ("Instructor".equals(userType)) {

			}
			if ("Developer".equals(userType)) {

				// create a label for the password, set the spacing, font style, size etc
				// add to the panel
				JLabel passLabel = new JLabel("Password: ");
				passLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
				passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
				passLabel.setForeground(Color.white);
				inputPanel.add(Box.createHorizontalGlue());
				inputPanel.add(passLabel);
				inputPanel.add(Box.createHorizontalGlue());

				inputPanel.add(passwordField);
			}

			// create a button for login
			// and set the style for the button
			// add to the panel and set the spacing at the center horizontally
			loginButton = new JButton("Login");
			loginButton.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
			loginButton.setPreferredSize(new Dimension(200, 35));
			loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

			loginButton.addKeyListener(this); // add KeyListener to loginButton
			loginButton.setFocusable(true); // Make the passwordField focusable to receive key events
			loginButton.requestFocusInWindow(); // Request focus for the main panel
			inputPanel.add(Box.createHorizontalGlue());

			inputPanel.add(loginButton);
			inputPanel.add(Box.createHorizontalGlue());

			// create a soundPlayer instance
			playSound = new soundPlayer();

			loginButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					String username = usernameField.getText();
					String ID = idField.getText();
					String password = new String(passwordField.getPassword());
					
					correctAccount = Login.checkAccount(username, password);
					System.out.println("user:" + username + " password: " + password);
					System.out.println(correctAccount);

					if (correctAccount == true) {
						if("Instructor".equals(getUserType())) {
							Users u = new Users(true);
							username.equals(u.getUser(username));
							User user = u.getUser(username);
							if(user.getRole() == 1) {
								dispose();
								new instructorPage().setVisible(true);
							}else {
								JOptionPane.showMessageDialog(InitialPage.this, "Username doesn't match usertype. Please try again.");
							}
								
						}
						else if("Developer".equals(getUserType())){
							Users u = new Users(true);
							username.equals(u.getUser(username));
							User user = u.getUser(username);
							if(user.getRole() == 2) {
								dispose();
								menu menu = new menu(user,u.getUser(username).getLevel() );
								menu.setVisible(true);
							}else {
								JOptionPane.showMessageDialog(InitialPage.this, "Username doesn't match usertype. Please try again.");

							}
						}
						else {
							Users u = new Users(true);
							username.equals(u.getUser(username));
							User user = u.getUser(username);
							if(user.getRole() == 0) {
								dispose();
								menu menu = new menu(user,u.getUser(username).getLevel() );
								menu.setVisible(true);
							}else {
								JOptionPane.showMessageDialog(InitialPage.this, "Username doesn't match usertype. Please try again.");

							}
						}
						
					} else {
						JOptionPane.showMessageDialog(InitialPage.this, "Login failed. Please try again.");
					}

					// try-catch block in case the sound file not found
					try {

						playSound.playSoundOnce("button sound.wav");

					} catch (Exception ex) {
						System.out.println("Sound not found");
					}

				}
			});

			// add text underneath the panel
			askReg = new JTextArea("Don't have an account yet? Please register first!");
			askReg.setEditable(false);
			askReg.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
			askReg.setPreferredSize(new Dimension(440, 50));
			askReg.setForeground(Color.white);
			askReg.setBackground(darkCyan);

			// add both input panel and text to the panel, use gbc for spacing
			userDetailPanel.add(inputPanel, gbc);
			userDetailPanel.add(askReg, gbc);

			// add vertical glue again to center components vertically
			userDetailPanel.add(Box.createVerticalGlue());
		}

		userDetailPanel.revalidate();
		userDetailPanel.repaint();
	}

	/**
     * Retrieves the selected user type from the combo box.
     * 
     * @return The selected user type.
     */
	public String getUserType() {
		String type = userTypeComboBox.getSelectedItem().toString();
		return type;

	}

	/**
	 * This is the method from the KeyListener interface that we need to implement
	 * This method will recognize the key events when the key is typed, then perform action respect to the key event
	 * @param e KeyEvent, key being used to type
	 */ 
	@Override
	public void keyTyped(KeyEvent e) {

	}

	/**
	 * This is the keyPresed method from the KeyListener interface that we need to implement
	 * This method will recognize the key events when the key is pressed on keyboard, then perform action respect to the key being pressed
	 * @param e KeyEvent, the key being pressed on keyboard
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.out.println("Escape key pressed " + "keycode: " + e.getKeyCode());
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			String username = usernameField.getText();
			String ID = idField.getText();
			String password = new String(passwordField.getPassword());

			correctAccount = Login.checkAccount(username, password);
			System.out.println("user:" + username + " password: " + password);
			System.out.println(correctAccount);

			if (correctAccount == true) {
				if("Instructor".equals(getUserType())) {
					dispose();
					new instructorPage().setVisible(true);;
				}
				else if("Developer".equals(getUserType())){
					Users u = new Users(true);
					username.equals(u.getUser(username));
					User user = u.getUser(username);
					menu menu = new menu(user,u.getUser(username).getLevel() );
					dispose();
					menu.setVisible(true);
				}
				else {
					Users u = new Users(true);
					username.equals(u.getUser(username));
					User user = u.getUser(username);
					menu menu = new menu(user,u.getUser(username).getLevel() );
					dispose();
					menu.setVisible(true);
				}
				
			} else {
				JOptionPane.showMessageDialog(InitialPage.this, "Login failed. Please try again.");
			}

			System.out.println("Enter key released " + "keycode: " + e.getKeyCode());
			// try-catch block in case the sound file not found
			try {

				playSound.playSoundOnce("button sound.wav");

			} catch (Exception ex) {
				System.out.println("Sound not found");
			}
		}
	}

	/**
	 * This is the keyPresed method from the KeyListener interface that we need to implement
	 * This method will recognize the key events when the key is released on keyboard, then perform action respect to the key being pressed
	 * @param e KeyEvent, the key being pressed on keyboard
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// keyReleased = called whenever a button is released
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.out.println("Escape key released " + "keycode: " + e.getKeyCode());
			System.exit(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			dispose();
		}

	}

	/**
	 * This is the main method, it will create a new InitialPage object
	 * @param args
	 */
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new InitialPage().setVisible(true);

			}
		});
	}
}