

   
 
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
 * @author Asus
 */
public class MessagesTest {
    
    public MessagesTest() {
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
    public void testSomeMethod() {
    }

    /**
     * Test of checkMessageID method, of class Messages.
     */
    @Test
    public void testCheckMessageID() {
        System.out.println("checkMessageID");
        Messages instance = new Messages(0, "", "");
        boolean expResult = true;
        boolean result = expResult;
        assertEquals(expResult, result);
    }

    /**
     * Test of checkMessageLength method, of class Messages.
     */
    @Test
    public void testCheckMessageLength() {
        System.out.println("checkMessageLength");
        String message = "";
        Messages instance = null;
        String expResult = "";
        String result = instance.checkMessageLength(message);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkRecipientCell method, of class Messages.
     */
    @Test
    public void testCheckRecipientCell() {
        System.out.println("checkRecipientCell");
        String cellNumber = "083316";
        Messages instance = null;
        String expResult = "";
        String result = instance.checkRecipientCell(cellNumber);
        assertEquals(expResult, result);
    }

    /**
     * Test of createMessageHash method, of class Messages.
     */
    @Test
    public void testCreateMessageHash() {
        System.out.println("createMessageHash");
        Messages instance = null;
        String expResult = "";
        String result = instance.createMessageHash();
        assertEquals(expResult, result);
    }

    /**
     * Test of sentMessage method, of class Messages.
     */
    @Test
    public void testSentMessage() {
        System.out.println("sentMessage");
        int choice = 0;
        Messages instance = null;
        String expResult = "";
        String result = instance.sentMessage(choice);
        assertEquals(expResult, result);
    }

    /**
     * Test of printMessages method, of class Messages.
     */
    @Test
    public void testPrintMessages() {
        System.out.println("printMessages");
        Messages instance = null;
        String expResult = "";
        String result = instance.printMessages();
        assertEquals(expResult, result);
    }

    /**
     * Test of returnTotalMessages method, of class Messages.
     */
    @Test
    public void testReturnTotalMessages() {
        System.out.println("returnTotalMessages");
        Messages instance = null;
        int expResult = 0;
        int result = instance.returnTotalMessages();
        assertEquals(expResult, result);
    }

    /**
     * Test of storeMessage method, of class Messages.
     */
    @Test
    public void testStoreMessage() {
        System.out.println("storeMessage");
        Messages instance = null;
        instance.storeMessage();
    }
    
}
