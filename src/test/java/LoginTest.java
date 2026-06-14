package com.mycompany.registrationloginfeature;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    
    public LoginTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testCheckUserName() {
        Login instance = new Login("Kyl_1", "", "", "", "");
        boolean expResult = true;
        boolean result = instance.checkUserName();
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckUserNameFalse() {
        Login instance = new Login("Kylbnnk_1", "", "", "", "");
        boolean expResult = false;
        boolean result = instance.checkUserName();
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckPasswordComplexity() {
        Login instance = new Login("", "", "", "SkypeSa@25", "");
        boolean expResult = true;
        boolean result = instance.checkPasswordComplexity();
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckPasswordComplexityFalse() {
        Login instance = new Login("", "", "", "Simplepassword", "");
        boolean expResult = false;
        boolean result = instance.checkPasswordComplexity();
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckCellPhoneNumber() {
        Login instance = new Login("", "", "", "", "+27664601652");
        boolean expResult = true;
        boolean result = instance.checkCellPhoneNumber();
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckCellPhoneNumberFalse() {
        Login instance = new Login("", "", "", "", "27664601652");
        boolean expResult = false;
        boolean result = instance.checkCellPhoneNumber();
        assertEquals(expResult, result);
    }

    @Test
    public void testRegisterUser() {
        Login instance = new Login("Kyl_1", "", "", "SkypeSa@25", "27664601652");
        String expResult = "Cell phone number incorrectly formatted or does not contain international code.";
        String result = instance.registerUser();
        assertEquals(expResult, result);
    }

    @Test
    public void testRegisterUserUsername() {
        Login instance = new Login("Kylbnnk_1", "", "", "", "");
        String expResult = "Username is not correctly formatted , please ensure that your username contains an underscore and is no more than five characters in length.";
        String result = instance.registerUser();
        assertEquals(expResult, result);
    }

    @Test
    public void testRegisterUserPassword() {
        Login instance = new Login("Kyl_1", "", "", "simple", "");
        String expResult = "Password is not correctly formatted , please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        String result = instance.registerUser();
        assertEquals(expResult, result);
    }

    @Test
    public void testReturnLoginStatus() {
        Login instance = new Login("Kyl_1", "", "", "SkypeSa@25", "+27664601652");
        String expResult = "Username or password incorrect, please try again.";
        String result = instance.returnLoginStatus(false);
        assertEquals(expResult, result);
    }
}
    
