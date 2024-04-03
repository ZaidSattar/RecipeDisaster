package src.main.java;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UsersTest {
	private Users users;
	

	@BeforeEach
    void setUp() {
		System.out.println("test start:");
        users = new Users(true); // Initialize with user.csv file
    }

	@Test
	void testSortScores() {
		System.out.println("test sortScores");
		ArrayList<User> u = users.sortScores();
		assertEquals("11thuser", u.get(0).getUsername());
	}

	@Test
	void testAddUserUser() {
		System.out.println("test addUser with user parameter only");
		User user1 = new User("test",1,"sdfsdf",204,3,0);
		users.addUser(user1);
		assertEquals(user1.getScore(), users.getUser("test").getScore());
		assertEquals(user1.getUsername(), users.getUser("test").getUsername());
		assertEquals(user1.getRole(), users.getUser("test").getRole());
		assertEquals(user1.getPassword(), users.getUser("test").getPassword());
		
	}

	@Test
	void testAddUserUserBoolean() {
		System.out.println("test addUser with skip overwrite check");
		User user3 = new User("tester4",0,"",24,1,0);
		User user4 = new User("tester4",0,"",24,1,0);
		users.addUser(user3);
		users.addUser(user4,true);
		assertEquals(user3.getUsername(), users.getUser("tester4").getUsername());
		assertEquals(user3.getScore(), users.getUser("tester4").getScore());
	}

	@Test
	void testEditUser() {
		System.out.println("test editUser");
		User editedUser = new User("rainbowdash", 1, "newpassword", 200, 0, -1);
        users.editUser(editedUser, "rainbowdash"); 
        User retrievedUser = users.getUser("rainbowdash");
        assertNotNull(retrievedUser); 
        assertEquals("rainbowdash", retrievedUser.getUsername());
        //assertEquals(1, retrievedUser.getRole());
        //assertEquals("newpassword", retrievedUser.getPassword());
        //assertEquals(200, retrievedUser.getScore());
        //assertEquals(0, retrievedUser.getLevel());
	}


	@Test
	void testGetUser() {
		System.out.println("test getUser");
		User existingUser = users.getUser("pinkiepie"); 
        assertNotNull(existingUser); 
        assertEquals("pinkiepie", existingUser.getUsername());
	}
	
	
	@AfterEach
    void tearDown() {
        System.out.println("test finished.");
    }
	

}

