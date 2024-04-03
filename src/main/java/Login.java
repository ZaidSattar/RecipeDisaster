package src.main.java;
/**
 * Various methods related to logging into the game
 * 
 * @author Collin Zhong
 *
 */
public class Login {

	/**
	 * Determines if the given username belongs to a user who has no password. Under
	 * normal circumstances, only a Player should use this.
	 * 
	 * @param username The username that is being used in the login attempt
	 * @return True if the username corresponds to a user with no password, false if
	 *         otherwise.
	 */
	public static boolean checkAccount(String username) {
		return checkAccount(username, "");
	}

	/**
	 * Determines if the given username and password belong to a user. Under normal
	 * circumstances, only a Developer should use this.
	 * 
	 * @param username The username that is being used in the login attempt
	 * @param password The password that is being used in the login attempt
	 * @return True if the username and password correspond to a user, false if
	 *         otherwise.
	 */
	public static boolean checkAccount(String username, String password) {
		System.out.println("Searching for account {" + username + "}");
		Users u = new Users();
		User user = u.getUser(username);
		if (user == null) {
			System.out.println("User is nulll");
			return false;
		}
		System.out.println("Checking " + Users.users.size() + " users");
		System.out.println("\tuser:  " + user.getUsername());
		if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
			return true;
		}
		System.out.println("idk what is going on");
		return false;
	}

	/**
	 * Determines if the given username and instructor ID belong to a user. Under
	 * normal circumstances, only an Instructor should use this.
	 * 
	 * @param username The username that is being used in the login attempt
	 * @param id       The ID that is being used in the login attempt
	 * @return True if the username and ID correspond to a uesr, false if otherwise.
	 */
	public static boolean checkAccount(String username, int id) {
		System.out.println("Searching for account {" + username + "}");
		Users u = new Users();
		User user = u.getUser(username);
		if (user == null) {
			return false;
		}
		System.out.println("Checking " + Users.users.size() + " users");
		System.out.println("\tuser:  " + user);
		if (user.getUsername().equals(username) && user.getInstructor() == id) {
			return true;
		}
		return false;
	}

	/**
	 * Determines if the given username is valid; i.e. it does not fail any of the
	 * criteria.
	 * 
	 * @param username The username to be checked
	 * @return True if the username is valid, false if otherwise.
	 */
	public static boolean isValidUsername(String username) {
		// usernames cannot contain a comma
		if (username.contains(",")) {
			return false;
		}
		// usernames cannot be empty
		if (username.equals("")) {
			return false;
		}
		return true;
	}

}
