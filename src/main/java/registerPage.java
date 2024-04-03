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
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A JFrame subclass representing the registration page for the game.
 * The userText and passwordText will be collaborate with the addUser method from
 * Login.java to add the users as user register.
 * 
 * @version %I%, %G%
 * @author viktoriya.li
 * @author zaid.sattar
 * @author regina.liang
 * @author collin.zhong
 */
public class registerPage extends JFrame{

	/** The user's username */
    private JTextField userText;
    /** The user's ID */
    private JTextField ID;
    /** The user's password*/
    private JPasswordField passwordText;
    /** The button for registeration*/
    private JButton registerButton;
    /** The button opens InitialPage*/
    private JButton InitialPageButton;
    /** The panel for this registeration page*/
    private JPanel panel;
    /** The user type for user*/
    private static String type;
    /** The background label*/
    private JLabel backgroundLabel;
    /** The sound player to play sound*/
    private soundPlayer playSound;
    /** A text area to mention if user does not have an instructor*/
    private JTextArea noInstructor;
    /** This is to identify which page is this, for example: registerPage will be identify as "r",
     * this is to add keyboard shortcuts in keyboard.java"
     */
    private String page = "r";

    /** RGB values to create a new color */
    private Color darkCyan = new Color(80, 128, 130);

    /**
     * registerPage constructor. Constructs the registerPage frame with the specified user type.
     * @param userType The type of user registering
     */
    public registerPage(String userType) {
        type = userType;
        setTitle("Register Page");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        backgroundLabel = new JLabel();
        add(backgroundLabel);
        backgroundLabel.setLayout(new GridBagLayout());

        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.setOpaque(false);
        wrapperPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        backgroundLabel.add(wrapperPanel);
        

        /** create a panel to display */
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(darkCyan);
        
        
        
        JLabel userLabel = new JLabel("User:");
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        userLabel.setForeground(Color.white);
        panel.add(userLabel);

        /** TextField for user input their username */
        userText = new JTextField(25);
        userText.setMaximumSize(userText.getPreferredSize());
        userText.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(userText);

        user(type);

        /**Register button*/
        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        registerButton.setPreferredSize(new Dimension(200, 35));
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        registerButton.addActionListener(new ActionListener() {
        	/**
        	 * actionPerformed to set the action for registerButton
        	 * @param e this is the type of action
        	 */
            public void actionPerformed(ActionEvent e) {
                // try-catch block in case the sound file not found
                try {
                    playSound.playSoundOnce("button sound.wav");

                } catch (Exception ex) {
                    System.out.println("Sound not found");
                }
                try {
                	String user = userText.getText();
                    String password = "";
                    String IDText;
                    int id = -1;
                    boolean validUser = Register.isValidUsername(user);
                    boolean validPassword = false;
                    boolean validID = false;
                    boolean success = false;

                    if ("Developer".equals(type)) {
                        password = new String(passwordText.getPassword());
                        validPassword = Register.isValidPassword(password);
                    } else if ("Instructor".equals(type)) {
                        IDText = ID.getText();
                        try {
                            id = Integer.parseInt(IDText);
                            // Use the parsed ID
                        } catch (NumberFormatException ex) {
                            // Handle the case where IDText is not a valid integer
                            JOptionPane.showMessageDialog(registerPage.this, "Invalid ID. Please enter a valid number.");
                        }
                        validID = Register.isValidInstructor(id);
                    } else if ("Player".equals(type)) {
                        IDText = ID.getText();
                        if (IDText.equals("-1")) {
                            validID = false;
                        } else {
                        	try {
                                id = Integer.parseInt(IDText);
                                // Use the parsed ID
                            } catch (NumberFormatException ex) {
                                // Handle the case where IDText is not a valid integer
                                JOptionPane.showMessageDialog(registerPage.this, "Invalid ID. Please enter a valid number.");
                            }
                            validID = Register.isValidInstructor(id);
                        }
                    }

                    if (validUser && validPassword) {
                    	User u = new User(user,2, password);
                    	Register.registerUser(u); // Register the user
                        JOptionPane.showMessageDialog(registerPage.this, "Register successful!");
                        success = true;
                    } else if (validUser && validID) {
                    	User u = new User(user,1,"",0,0,id);
                    	Register.registerUser(u);
                        JOptionPane.showMessageDialog(registerPage.this, "Register successful!");
                        success = true;
                        
                    } else if (validUser) {
                    	User u = new User(user, 0,"", 0 ,0);
                    	Register.registerUser(u);
                        JOptionPane.showMessageDialog(registerPage.this, "Register successful!");
                        success = true;
                    } else {
                        playSound.playSoundOnce("fail sound.wav");
                    }

                    if (success) {
                        playSound.playSoundOnce("congrats sound.wav");
                        InitialPage initial = new InitialPage();
                        initial.setVisible(true);
                        setVisible(false);
                    }
                    else {
                        JOptionPane.showMessageDialog(registerPage.this, "Register failed");
                    }
//                    
                } catch (Exception ex) {
                    System.out.println("Error in registerPage.registerButton");
                    ex.printStackTrace();
                }
                

            }
        });
        panel.add(registerButton);

        playSound = new soundPlayer();

        /** panel at the top*/ 
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(darkCyan);
        


        panel.add(topPanel, BorderLayout.NORTH);

        /**InitialPage button*/
        InitialPageButton = new JButton("Back to Login");
        InitialPageButton.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        InitialPageButton.setPreferredSize(new Dimension(200, 35));
        InitialPageButton.addActionListener(new ActionListener() {
            @Override
            /**
             * This method is to get the action performed by InitialButton 
             * @param e
             */
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new InitialPage().setVisible(true);
                // try-catch block in case the sound file not found
                try {

                    playSound.playSoundOnce("button sound.wav");

                } catch (Exception ex) {
                    System.out.println("Sound not found");
                }
            }

        });
        topPanel.add(InitialPageButton);

        /**centered the fields */ 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        wrapperPanel.add(panel, gbc);

        setVisible(true);

        /**method for accessing and stretching the image to fit full screen */ 
        
        addComponentListener(new ComponentAdapter() {
        	/** 
        	 * Add a ComponentListener to the JFrame 
        	 * @param e
        	 * */ 
            public void componentResized(ComponentEvent e) {
                /** try-catch block in case there's no picture*/
                try {

                    /**scale the image icon every time the JFrame is resized*/
                    /** use the relative path to the file*/
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
     * Initializes the user interface components based on the user type.
     * @param userType The type of user registering
     */
    public void user(String userType) {
        if ("Developer".equals(userType)) {
            JLabel passwordLabel = new JLabel("Password:");
            passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            passwordLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
            passwordLabel.setForeground(Color.white);
            panel.add(passwordLabel);

            passwordText = new JPasswordField(25);
            passwordText.setMaximumSize(passwordText.getPreferredSize());
            passwordText.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(passwordText);

        } else {
            JLabel IDLabel = new JLabel("Instructor ID:");
            IDLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            IDLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
            IDLabel.setForeground(Color.WHITE);
            panel.add(IDLabel);

            ID = new JTextField(25);
            ID.setMaximumSize(ID.getPreferredSize());
            ID.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(ID);

            noInstructor = new JTextArea("  Don't have an instructor? Please type in '-1'.  ");
            noInstructor.setEditable(false);
            noInstructor.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
            noInstructor.setForeground(Color.white);
            noInstructor.setBackground(darkCyan);

            panel.add(noInstructor);

        }
    }

	/**
     * Main method to run the registration page.
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
    }
}