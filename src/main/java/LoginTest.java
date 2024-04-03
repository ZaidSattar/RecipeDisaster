package src.main.java;
import org.junit.Assert;
import org.junit.Test;

public class LoginTest {


    /**
     * Test checking an account that exists with a correct password.
     * Assumes the environment has a user "existingUser" with password "correctPassword".
     */

    @Test
    public void testIsValidUsernameValid() {
        boolean result = Login.isValidUsername("validUsername");
        Assert.assertTrue("The username should be considered valid.", result);
    }

    /**
     * Test checking the validity of a username containing a comma.
     */
    @Test
    public void testIsValidUsernameContainsComma() {
        boolean result = Login.isValidUsername("invalid,username");
        Assert.assertFalse("The username should be considered invalid due to containing a comma.", result);
    }

    /**
     * Test checking the validity of an empty username.
     */
    @Test
    public void testIsValidUsernameEmpty() {
        boolean result = Login.isValidUsername("");
        Assert.assertFalse("The username should be considered invalid as it is empty.", result);
    }
}
