package src.main.java;

public class User {
	private String username;
	private int role;
	private String password;
	private int level;
	private int score;
	private int instructor;

	/**
	 * Constructor. Overload: default Player
	 * 
	 * @param username
	 */
	public User(String username, int instructor) {
		this.username = username;
		this.role = 0;
		this.password = "";
		this.level = 0;
		this.score = 0;
		this.instructor = instructor;
	}

	/**
	 * Constructor. Overload: new User
	 * 
	 * @param username
	 * @param role
	 * @param password
	 */
	public User(String username, int role, String password) {
		this.username = username;
		this.role = role;
		this.password = password;
		this.level = 0;
		this.score = 0;
		this.instructor = -1;
	}

	/**
	 * Constructor. Overload: existing user
	 * 
	 * @param username
	 * @param role
	 * @param password
	 * @param level
	 * @param score
	 */
	public User(String username, int role, String password, int score, int level, int instructor) {
		this.username = username;
		this.role = role;
		this.password = password;
		this.level = level;
		this.score = score;
		this.instructor = instructor;
	}

	/**
	 * Constructor. Overload: existing user, no instructor ID
	 * 
	 * @param username
	 * @param role
	 * @param password
	 * @param level
	 * @param score
	 */
	public User(String username, int role, String password, int score, int level) {
		this.username = username;
		this.role = role;
		this.password = password;
		this.level = level;
		this.score = score;
		this.instructor = -1;
	}

	
	public void updateUser() {
		FileEditor fe = new FileEditor("users.csv");
		String line = username + "," + role + "," + password + "," + score + "," + level + "," + instructor;
		fe.overwriteLine(username, line);
	}

	public void addScore(int score) {
		this.score += score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void addLevel(int level) {
		this.level += level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getUsername() {
		return username;
	}

	public int getRole() {
		return role;
	}

	public String getPassword() {
		return password;
	}

	public int getLevel() {
		return level;
	}

	public int getScore() {
		return score;
	}

	public int getInstructor() {
		return this.instructor;
	}

	public void setInstructor(int instructor) {
		this.instructor = instructor;
	}

	@Override
	public User clone() {
		return new User(this.username, this.role, this.password, this.score, this.level, this.instructor);
	}

	public boolean equals(User otherUser) {
		if (this.username.equals(otherUser.username)) {
			if (this.role == otherUser.role) {
				if (this.password.equals(otherUser.password)) {
					if (this.score == otherUser.score) {
						if (this.level == otherUser.level) {
							if (this.instructor == otherUser.instructor) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o instanceof User) {
			return this.equals(o);
		}
		return false;
	}

}
