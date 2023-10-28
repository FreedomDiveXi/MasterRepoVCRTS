import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

    private User user;

    @BeforeEach
    public void setup() {
        user = new User("JohnDoe", "password1234");
    }

    @Test
    public void testUserConstructorValid() {
        assertEquals("JohnDoe", user.getUsername());
    }

    @Test
    public void testUserConstructorInvalidUsername() {
        assertThrows(IllegalArgumentException.class, () -> new User("", "password1234"));
    }

    @Test
    public void testUserConstructorInvalidPasswordShort() {
        assertThrows(IllegalArgumentException.class, () -> new User("JohnDoe", "pass"));
    }

    @Test
    public void testUserConstructorInvalidPasswordNoDigit() {
        assertThrows(IllegalArgumentException.class, () -> new User("JohnDoe", "password"));
    }

    @Test
    public void testLoginValid() {
        assertTrue(user.login("password1234"));
    }

    @Test
    public void testLoginInvalid() {
        assertFalse(user.login("wrongPassword"));
    }

    @Test
    public void testChangePassword() {
        user.changePassword("newPass123");
        assertTrue(user.login("newPass123"));
    }

    @Test
    public void testChangePasswordInvalidShort() {
        assertThrows(IllegalArgumentException.class, () -> user.changePassword("short"));
    }

    @Test
    public void testChangePasswordInvalidNoDigit() {
        assertThrows(IllegalArgumentException.class, () -> user.changePassword("newpassword"));
    }

    // Add more tests as required.
}
