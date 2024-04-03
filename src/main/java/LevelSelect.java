package src.main.java;
import java.awt.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * The {@code LevelSelect} class extends {@code JFrame} to create a level selection screen for a game.
 * It reads from 'completedLevels.csv' to determine which levels are unlocked and displays them accordingly.
 * Each level is represented by a button, and unlocked levels can be selected to play.
 * The screen also includes a "Main Menu" button to navigate back to the main menu.
 * This dynamic UI adapts to different window sizes, ensuring a consistent user experience.
 *
 * @version 1.0
 * @author zaid.sattar
 */
public class LevelSelect extends JFrame {
    private OutlinedLabel headingLabel;
    private Set<String> unlockedLevels = new HashSet<>();
    private User user;

    /**
     * Constructs a new {@code LevelSelect} frame initializing the UI components and loading the unlocked levels.
     */
    public LevelSelect(User user) {
        super("Select Level");
        loadUnlockedLevels();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        headingLabel = new OutlinedLabel("Select Level:");
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headingLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 48));

        JPanel imagePanel = new JPanel() {
            ImageIcon imageIcon = new ImageIcon("levelSelecter.png");

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        imagePanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;

        gbc.insets = new Insets(10, 0, 20, 0);
        gbc.gridy = 1;
        imagePanel.add(headingLabel, gbc);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 20, 10));
        buttonPanel.setOpaque(false);
        gbc.gridy = 2;
        imagePanel.add(buttonPanel, gbc);
        
        keyboard kb = new keyboard(user);
        buttonPanel.addKeyListener(kb); // add keyboard shortcut
        buttonPanel.setFocusable(true); // allow to panel to receive keyboard key events
        buttonPanel.requestFocusInWindow(); // Request focus for the  panel
        
        this.user = user;
        if(user.getRole() == 2) {
        	allLevels();
        }

        for (int i = 1; i <= 8; i++) {
        	boolean isUnlocked;
        	JButton button;
            if(i<8) {
            	isUnlocked = unlockedLevels.contains("Level" + i);
                button = createStyledButton("Level " + i, isUnlocked, user);
                buttonPanel.add(button);
            }
            else {
            	isUnlocked = unlockedLevels.contains("Level" + i);
                button = createStyledButton("Spiral Abyss " + i, isUnlocked, user);
                buttonPanel.add(button);
            }
        }


        JButton menuButton = new JButton("Main Menu");
        menuButton.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        menuButton.setPreferredSize(new Dimension(200, 50));
        menuButton.addActionListener(e -> new menu(user, user.getLevel()));

        gbc.gridy = 3;
        gbc.insets = new Insets(20, 0, 0, 0);
        imagePanel.add(menuButton, gbc);

        getContentPane().add(imagePanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Creates a styled button for each level with specific text and unlocked status.
     * Unlocked levels are interactable and change color on mouse hover.
     *
     * @param text     The text for the button, typically the level name.
     * @param unlocked Whether the level is unlocked and selectable.
     * @return A JButton configured for the level selection screen.
     */
    private JButton createStyledButton(String text, boolean unlocked, User user) {
    	JButton button;
    	if(text.contains("Sprial"))
        	button = new JButton("Sprial Abyss");
    	else
    		button = new JButton(text);
        button.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        if (unlocked) {
            button.setForeground(Color.WHITE);
            button.setBackground(new Color(231, 76, 60));
            button.setToolTipText("Click to Play!");
            button.addActionListener(e -> {
                // Call Gameplay.java when the button is clicked
//                gameplay play = new gameplay(user.getUsername(),"recipe0.csv");
                // Extract level number from button text
                int levelNumber = Integer.parseInt(text.replaceAll("\\D+", "")) - 1;
                String fileName = "recipe" + levelNumber + ".csv";
                // Instantiate gameplay with the correct CSV file based on level
                dispose();
                new gameplay(user.getUsername(), fileName);
            });

        } else {
            button.setForeground(Color.GRAY);
            button.setBackground(new Color(150, 150, 150));
            button.setToolTipText("Locked Level");
        }
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(150, 50));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setContentAreaFilled(true);

        button.setEnabled(unlocked);

        // Mouse listener for visual effects, remains unchanged
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (unlocked) {
                    button.setBackground(new Color(243, 156, 18));
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (unlocked) {
                    button.setBackground(new Color(231, 76, 60));
                }
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (unlocked) {
                    button.setBackground(new Color(211, 84, 0));
                }
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                if (unlocked) {
                    button.setBackground(new Color(243, 156, 18));
                }
            }
        });

        return button;
    }



    /**
     * Loads the unlocked levels from 'completedLevels.csv' into a set for determining which levels to display as unlocked.
     */
    private void loadUnlockedLevels() {
    	String line;
        try (BufferedReader br = new BufferedReader(new FileReader("completedLevels.csv"))) {
            while ((line = br.readLine()) != null) {
                unlockedLevels.add(line.trim());
            }
        
        } catch (IOException e) {
            System.out.println("Error reading completedLevels.csv: " + e.getMessage());
            // Handle IO exception gracefully
        }
    }
    
    /**
     * This is to Display all the level for developer to access the levels.
     */
    private void allLevels() {
    	// Unlock all levels programmatically
        for (int i = 1; i <= 8; i++) {
            unlockedLevels.add("Level" + i);
        }
    }

    /**
     * The main method to run the level selection screen.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
    ///    SwingUtilities.invokeLater(LevelSelect::new);
    }
}