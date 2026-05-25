/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.registrationloginfeature;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author NALEDI
 */
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

    /**
     * Test of checkUserName method, of class Login.
     */
    @Test
    public void testCheckUserName() {
        System.out.println("checkUserName");
        Login instance = new Login("Kyl_1", "", "", "", "");
        boolean expResult = true;
        boolean result = instance.checkUserName();
        assertEquals(expResult, result);
    }
    @Test
    public void testCheckUserNameFalse() {
        System.out.println("checkUserName");
        Login instance = new Login("Kylbnnk_1", "", "", "", "");
        boolean expResult = false;
        boolean result = instance.checkUserName();
        assertEquals(expResult, result);
    }

    /**
     * Test of checkPasswordComplexity method, of class Login.
     */
    @Test
    public void testCheckPasswordComplexity() {
        System.out.println("checkPasswordComplexity");
        Login instance = new Login ("SkypeSa@25","","","","");     
        boolean expResult = true;
        boolean result = instance.checkPasswordComplexity();
        assertEquals(expResult, result);
        
        
    }
    @Test
    public void testCheckPasswordComplexityFalse() {
        System.out.println("checkPasswordComplexity");
        Login instance = new Login ("SkypeSahgjrt@25","","","","");     
        boolean expResult = false;
        boolean result = instance.checkPasswordComplexity();
        assertEquals(expResult, result);
        
    }
    /**
     * Test of checkCellPhoneNumber method, of class Login.
     */
    @Test
    public void testCheckCellPhoneNumber() {
        System.out.println("checkCellPhoneNumber");
        Login instance = new Login ("+27664601652","","","","");
        boolean expResult = true;
        boolean result = instance.checkCellPhoneNumber();
        assertEquals(expResult, result);
        
    }
    @Test
    public void testCheckCellPhoneNumberFalse() {
        System.out.println("checkCellPhoneNumber");
        Login instance = new Login ("27664601652","","","","");
        boolean expResult = false;
        boolean result = instance.checkCellPhoneNumber();
        assertEquals(expResult, result);
        
}
    /**
     * Test of registerUser method, of class Login.
     */
    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        Login instance = new Login ("27664601652","","","","");
        String expResult = "Cell phone number incorrectly formatted or does not contain international code";
        String result = instance.registerUser();
        assertEquals(expResult, result);
    }
    @Test
    public void testRegisterUserUsername() {
        System.out.println("registerUser");
        Login instance = new Login ("Kylbnnk_1","","","","");
        String expResult = "Username is not correctly formatted , please ensure that your username contains an underscore and is no more than five characters in length.";
        String result = instance.registerUser();
        assertEquals(expResult, result);
    }
    /**
     * Test of loginUser method, of class Login.
     */
    @Test
    public void testRegisterUserPassword() {
        System.out.println("registerUser");
        Login instance = new Login ("SkypeSa@25","","","","");
        String expResult = "Password is not correctly formatted , please ensure that the password contains at least eight characters, a capital letter, a number, and a special character." ;
        String result = instance.registerUser();
        assertEquals(expResult, result);
    }


    /**
     * Test of returnLoginStatus method, of class Login.
     */
    @Test
    public void testReturnLoginStatus() {
        System.out.println("returnLoginStatus");
        boolean loginstatus = false;
        Login instance = new Login ("27664601652","","","","");
        String expResult = "Username or password incorrect, please try again.";
        String result = instance.returnLoginStatus(loginstatus);
        assertEquals(expResult, result);
    }
    
}
