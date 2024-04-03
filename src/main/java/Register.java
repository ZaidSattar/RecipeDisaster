package src.main.java;
import java.util.ArrayList;

/**
 * Contains methods that pertain to registration of new accounts.
 * 
 * @author Collin Zhong
 *
 */
public class Register {

	/**
	 * Determines if a username is valid for registering for a new account. Does
	 * this by checking if there is already a user with the same username, as well
	 * as if the username meets all criteria.
	 * 
	 * @param username The username to be checked
	 * @return True if the username is valid, false if otherwise.
	 */
	public static boolean isValidUsername(String username) {
		FileEditor fe = new FileEditor("users");
		ArrayList<String> usernames = fe.getColumn(0);
		// is it a duplicate?
		// is it a valid username? (from login)
		return (!usernames.contains(username) && Login.isValidUsername(username));
	}

	/**
	 * Determines if a password is valid for registering for a new account. Uses the
	 * same checks as a valid username.
	 * 
	 * @param password The password to be checked
	 * @return True if the password is valid, false if otherwise.
	 */
	public static boolean isValidPassword(String password) {
		return Login.isValidUsername(password);
	}
	
	public static boolean isValidInstructor(int id) {
		Users u = new Users(true);
		for(User user : Users.users) {
			if (user.getInstructor() == id) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Registers a new user by adding them to the static ArrayList, then adding them
	 * to the CSV file.
	 * 
	 * @param user The user to be registered
	 */
	public static void registerUser(User user) {
		Users u = new Users();
		u.addUser(user);
	}

}
