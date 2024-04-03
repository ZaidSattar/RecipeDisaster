package src.main.java;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * This is the class implements the KeyListener and it is design for keyboard shortcuts. (A helper class)
 * It is meant to avoid implements the KeyListener interface for every pages, by simply make this class and
 * call the methods from other class that need a keyboard shortcut.
 * @author regina.liang
 *
 */
public class keyboard implements KeyListener{
	/**
	 * This variable is used to identify which page have called this class for keyboard shortcuts so it won't mix up
	 */
	private String page;
	private User user;
	private String username;
	
	/**
	 * Constructor for keyboard
	 * 
	 * @param page this variable is used to identify which page is requesting a keyboard shortcut,and provide it accordingly
	 */
	public keyboard(String page) {
		this.page = page;
	}
	public keyboard(User user) {
		this.user = user;
	}

	/**
	 * This is the method from the KeyListener interface that we need to implement
	 * This method will recognize the key events when the key is typed, then perform action respect to the key event
	 * @param e KeyEvent, key being used to type
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// keyTyped = Invoked when a key is typed. Uses KeyChar, char output
		char key = e.getKeyChar();
		System.out.println(key);
	}

	/**
	 * This is the keyPresed method from the KeyListener interface that we need to implement
	 * This method will recognize the key events when the key is pressed on keyboard, then perform action respect to the key being pressed
	 * @param e KeyEvent, the key being pressed on keyboard
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// keyPressed = Invoked when a physical key is pressed down. Uses KeyCode, int output
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.out.println("Escape key released " + "keycode: " + e.getKeyCode());
			System.exit(0);
		}	
		if(e.getKeyCode() == KeyEvent.VK_Q) {
			System.out.println("Q pressed" + "keycode: " + e.getKeyCode());
			
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
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.out.println("Escape key released " + "keycode: " + e.getKeyCode());
			System.exit(0);
		}	
		if(e.getKeyCode() == KeyEvent.VK_Q) {
			System.out.println("Q relseased" + "keycode: " + e.getKeyCode());
		}
	}

	/**
	 * Main method for the class
	 * @param args
	 */
	public static void main(String[] args) {
		
	}
}
