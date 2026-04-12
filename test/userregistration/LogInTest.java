/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package userregistration;


/**
 *
 * @author kekan
 */
import org.junit.Test;
import static org.junit.Assert.*;

public class LogInTest {
    
   


    LogIn login = new LogIn();

    // ===== Username Tests ====
    @Test
    public void testUsernameCorrectlyFormatted() {
        assertTrue(login.checkUsername("kyl_1"));
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        assertFalse(login.checkUsername("kyle!!!!!!!"));
    }

    // ===== Password Tests =====
    @Test
    public void testPasswordMeetsComplexity() {
        assertTrue(login.checkPasswordComplexity("Ch&sec@ke99"));
    }

    @Test
    public void testPasswordDoesNotMeetComplexity() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    // ===== Cell Number Tests =====
    @Test
    public void testCellPhoneNumberCorrect() {
        assertTrue(login.checkCellPhoneNumber("+27831234567"));
    }

    @Test
    public void testCellPhoneNumberIncorrect() {
        assertFalse(login.checkCellPhoneNumber("0831234567"));
    }

    // ===== Registration Tests =====
    @Test
    public void testRegisterUserSuccess() {
        String result = login.registerUser("abc_1", "Passw0rd!", "+27831234567");
        assertEquals("User successfully registered.", result);
    }

    @Test
    public void testRegisterUserFailUsername() {
        String result = login.registerUser("abcdef", "Passw0rd!", "+27831234567");
        assertTrue(result.contains("Username is not correctly formatted"));
    }

    // ===== Login Tests =====
    @Test
    public void testLoginSuccess() {
        login.registerUser("abc_1", "Passw0rd!", "+27831234567");
        assertTrue(login.loginUser("abc_1", "Passw0rd!"));
    }

    @Test
    public void testLoginFail() {
        login.registerUser("abc_1", "Passw0rd!", "+27831234567");
        assertFalse(login.loginUser("abc_1", "wrong"));
    }
}