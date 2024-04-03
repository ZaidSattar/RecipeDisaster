package src.main.java;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("testuser", 0, "password", 100, 5, 1);
    }

    @Test
    public void testAddScore() {
        user.addScore(50);
        assertEquals(150, user.getScore());
    }

    @Test
    public void testSetScore() {
        user.setScore(200);
        assertEquals(200, user.getScore());
    }

    @Test
    public void testAddLevel() {
        user.addLevel(2);
        assertEquals(7, user.getLevel());
    }

    @Test
    public void testSetLevel() {
        user.setLevel(10);
        assertEquals(10, user.getLevel());
    }

    @Test
    public void testGetters() {
        assertEquals("testuser", user.getUsername());
        assertEquals(0, user.getRole());
        assertEquals("password", user.getPassword());
        assertEquals(100, user.getScore());
        assertEquals(5, user.getLevel());
        assertEquals(1, user.getInstructor());
    }

    @Test
    public void testEquals() {
        User sameUser = new User("testuser", 0, "password", 100, 5, 1);
        assertTrue(user.equals(sameUser));
    }

    @Test
    public void testNotEquals() {
        User differentUser = new User("differentuser", 1, "differentpassword", 200, 10, 2);
        assertFalse(user.equals(differentUser));
    }
}

