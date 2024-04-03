package src.main.java;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Represents the gameplay window where the game is played.
 * 
 * @author regina.liang
 * @author viktoriya.li
 */
public class gameplay extends JFrame implements KeyListener{
	
	private JLabel headingLabel;
	private JPanel panel;
	private JButton menu;
	private JLabel backgroundLabel;
	private soundPlayer playSound;
	private User user;
	
	/**
     * Constructs a new gameplay window.
     */
	public gameplay(String username, String filename) {
		
		//call the user
		Users u = new Users(true);
        this.user = u.getUser(username);
        int level = user.getLevel();
		
		// Set the window for menu
		setTitle("Gameplay Page");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		// create a label with an empty icon initially and set the layout
		backgroundLabel = new JLabel();
        add(backgroundLabel);
        backgroundLabel.setLayout(new GridBagLayout());
		
		panel = new JPanel(new BorderLayout());
		panel.setOpaque(false);
        add(panel, BorderLayout.CENTER);
        panel.addKeyListener(this); // add keyboard shortcut
		panel.setFocusable(true); // allow to panel to receive keyboard key events
		panel.requestFocusInWindow(); // Request focus for the  panel
        
        
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(topPanel, BorderLayout.NORTH);
        topPanel.setOpaque(false);
        // Add keyboard shortcuts
        topPanel.addKeyListener(this); // add keyboard listener
		topPanel.setFocusable(true); // allow to panel to receive keyboard key events
		topPanel.requestFocusInWindow(); // Request focus for the  panel
        
        JLabel menuLabel = new JLabel("Quit the game:");
        menuLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        topPanel.add(menuLabel);
        
        playSound = new soundPlayer();
        
        menu = new JButton("Quit");
        menu.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		menu.setPreferredSize(new Dimension(100, 35));
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "<html><center>Are you sure?<br>Progress will not be saved.</center></html>", "Confirmation", JOptionPane.YES_NO_OPTION);

            	if (choice == JOptionPane.YES_OPTION) {
                    setVisible(false); // Hide the gameplay window
                    new menu(user,level);
                    playSound.playSoundOnce("button sound.wav");
                    
                }
                else {
                	// nothing, return to the gameplay page
                }
                
            }
        });
        
        topPanel.add(menu);

        memoryGameScreen play = new memoryGameScreen(username, 10, filename, backgroundLabel);
        
        
        backgroundLabel.setLayout(new BorderLayout());
        backgroundLabel.add(panel, BorderLayout.NORTH);

		add(backgroundLabel, BorderLayout.CENTER);
//        finishGameScreen don = new finishGameScreen(username,filename,backgroundLabel);

        
        setVisible(true);
        
        
        // method for accessing and stretching the image to fit full screen
        // add a ComponentListener to the JFrame
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // try-catch block in case there's no picture
                try {

                    // scale the image icon every time the JFrame is resized
                    // use the relative path to the file
                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("game.png"))
                            .getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH));
                    backgroundLabel.setIcon(imageIcon);
                } catch (NullPointerException exception) {
                    System.out.println("Image not found");
                }
            }
        });

	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Q key to quit and return to menu
		if(e.getKeyCode() == KeyEvent.VK_Q) {
			System.out.println("Q pressed" + "keycode: " + e.getKeyCode());
			int choice = JOptionPane.showConfirmDialog(null, "<html><center>Are you sure?<br>Progress will not be saved.</center></html>", "Confirmation", JOptionPane.YES_NO_OPTION);
        	if (choice == JOptionPane.YES_OPTION) {
                setVisible(false); // Hide the gameplay window
                dispose();
                new menu(user, user.getLevel());
            }
            else {
            	// nothing, return to the gameplay page
            }
		}
		
		// Escape key to exit
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.out.println("Escape key released " + "keycode: " + e.getKeyCode());
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	/**
     * The main function for the program.
     * @param args The command-line arguments.
     */
	public static void main(String[] args) {
		new gameplay("pinkiepie","recipe0.csv");
	}

	

}