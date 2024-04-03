package src.main.java;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Iterator;

public class Users {
	public static ArrayList<User> users;

	public Users() {
	}

	public Users(boolean val) {
		if (val) {
			try {
				users = new ArrayList<User>();
				FileEditor fe = new FileEditor("users.csv");
				BufferedReader br = fe.readFile();
				String line = br.readLine();
				while (line != null) {
					String[] splitLine = line.split(",");
					String username = splitLine[0];
					int role = Integer.valueOf(splitLine[1]);
					String password = splitLine[2];
					int score = Integer.valueOf(splitLine[3]);
					int level = Integer.valueOf(splitLine[4]);
					users.add(new User(username, role, password, score, level));
					System.out.println("User {" + username + "} added");
					line = br.readLine();
				}
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Returns an ArrayList of all users, sorted by their score from highest to
	 * lowest.
	 * 
	 * @return
	 */
	public ArrayList<User> sortScores() {
		ArrayList<User> sortAL = new ArrayList<User>();
		Iterator<User> itr = users.iterator();
		while (itr.hasNext()) {
			sortAL.add(itr.next().clone());
		}
		int i, j;
		boolean swapped;
		for (i = 0; i < sortAL.size(); i++) {
			swapped = false;
			for (j = 0; j < sortAL.size() - i - 1; j++) {
				if (sortAL.get(j).getScore() < sortAL.get(j + 1).getScore()) {
					User temp = sortAL.get(j);
					sortAL.set(j, sortAL.get(j + 1));
					sortAL.set(j + 1, temp);
					swapped = true;
				}
			}

			if (swapped == false)
				break;
		}
		return sortAL;
	}

	/**
	 * Adds a user to the ArrayList and updates the CSV file with the user's
	 * details.
	 * 
	 * Does not add a user if: the user already exists within the CSV file, or if
	 * there is a duplicate username.
	 * 
	 * @param user
	 */
	public void addUser(User user) {
		for (User u : users) {// if duplicate of user: do not add
			if (user.equals(u)) {
				return;
			}
		}
		Iterator<User> itr = users.iterator();
		while (itr.hasNext()) { // if duplicate username: do not add
			if (user.getUsername().equals(itr.next().getUsername())) {
				return;
			}
		}
		if (!Login.isValidUsername(user.getUsername())) {
			return;
		}
		addUser(user, true);
	}

	/**
	 * Adds a user to the ArrayList and updates the CSV file with the user's
	 * details.
	 * 
	 * Overload: skips the checks for whether a user is valid or not and adds the
	 * user directly.
	 * 
	 * @param user
	 * @param val
	 */
	public void addUser(User user, boolean val) {
		if (val) {
			users.add(user);
			overwriteUsers();
			System.out.println("New user added: " + user.getUsername());
		}
	}

	public void editUser(User user, String username) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUsername().equals(username)) {
				users.set(i, user);
				System.out.println("User has been overwritten.");
			}
		}
		overwriteUsers();
	}

	/**
	 * Overwrites the users.csv file with the current contents of the users
	 * ArrayList.
	 */
	public void overwriteUsers() {
		FileEditor fe = new FileEditor("users.csv");
		fe.resetFile();
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			String line = user.getUsername() + "," + user.getRole() + "," + user.getPassword() + "," + user.getScore()
					+ "," + user.getLevel() + "," + user.getInstructor();
			fe.addLine(line);
		}

	}

	/**
	 * Gets a user with the username from the CSV file
	 * 
	 * @param username
	 */
	public User getUser(String username) {
		System.out.println("Searching for user with the username {" + username + "}.");
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				System.out.println("User {" + username + "} found.");
				return user;
			}
		}
		System.out.println("No user with the username {" + username + "} was found.");
		return null;
	}

}
