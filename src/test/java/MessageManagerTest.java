/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import com.mycompany.registrationloginfeature.MessageManager;
import com.mycompany.registrationloginfeature.Messages;
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
public class MessageManagerTest {
    
    public MessageManagerTest() {
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
     * Test of checkMessageID method - ID should be 10 characters or less.
     */
    @Test
    public void testCheckMessageID() {
        System.out.println("checkMessageID");
        Messages instance = new Messages(0, "+27831234567", "Hello there");
        boolean result = instance.checkMessageID();
        assertTrue(result); // generated ID is always 10 digits
    }

    /**
     * Test checkMessageLength - message within 250 characters should pass.
     */
    @Test
    public void testCheckMessageLengthValid() {
        System.out.println("checkMessageLength - valid");
        Messages instance = new Messages(0, "+27831234567", "Hello there");
        String result = instance.checkMessageLength("Hello there");
        assertEquals("Message ready to send.", result);
    }

    /**
     * Test checkMessageLength - message over 250 characters should fail.
     */
    @Test
    public void testCheckMessageLengthTooLong() {
        System.out.println("checkMessageLength - too long");
        Messages instance = new Messages(0, "+27831234567", "Hello");
        String longMessage = "A".repeat(260); // 260 characters
        String result = instance.checkMessageLength(longMessage);
        assertTrue(result.contains("exceeds 250 characters"));
    }

    /**
     * Test checkRecipientCell - valid SA number should pass.
     */
    @Test
    public void testCheckRecipientCellValid() {
        System.out.println("checkRecipientCell - valid");
        Messages instance = new Messages(0, "+27831234567", "Hello");
        String result = instance.checkRecipientCell("+27831234567");
        assertEquals("Cell phone number successfully captured.", result);
    }

    /**
     * Test checkRecipientCell - number without + should fail.
     */
    @Test
    public void testCheckRecipientCellInvalid() {
        System.out.println("checkRecipientCell - invalid");
        Messages instance = new Messages(0, "0831234567", "Hello");
        String result = instance.checkRecipientCell("0831234567");
        assertTrue(result.contains("incorrectly formatted"));
    }

    /**
     * Test createMessageHash - should return a non-null uppercase string.
     */
    @Test
    public void testCreateMessageHash() {
        System.out.println("createMessageHash");
        Messages instance = new Messages(0, "+27831234567", "Hello World");
        String result = instance.createMessageHash();
        assertNotNull(result);
        assertEquals(result, result.toUpperCase()); // hash must be uppercase
        assertTrue(result.contains(":")); // hash format has colons
    }

    /**
     * Test sentMessage with choice 1 - should return "Message successfully sent."
     */
    @Test
    public void testSentMessageSend() {
        System.out.println("sentMessage - send");
        Messages instance = new Messages(0, "+27831234567", "Hello");
        String result = instance.sentMessage(1);
        assertEquals("Message successfully sent.", result);
        assertEquals("Sent", instance.getFlag());
    }

    /**
     * Test sentMessage with choice 2 - should return disregard message.
     */
    @Test
    public void testSentMessageDisregard() {
        System.out.println("sentMessage - disregard");
        Messages instance = new Messages(0, "+27831234567", "Hello");
        String result = instance.sentMessage(2);
        assertEquals("Press 0 to delete the message.", result);
        assertEquals("Disregard", instance.getFlag());
    }

    /**
     * Test sentMessage with choice 3 - should return stored message.
     */
    @Test
    public void testSentMessageStore() {
        System.out.println("sentMessage - store");
        Messages instance = new Messages(0, "+27831234567", "Hello");
        String result = instance.sentMessage(3);
        assertEquals("Message successfully stored.", result);
        assertEquals("Stored", instance.getFlag());
    }

    /**
     * Test printMessages - should contain all required fields.
     */
    @Test
    public void testPrintMessages() {
        System.out.println("printMessages");
        Messages instance = new Messages(0, "+27831234567", "Hello World");
        String result = instance.printMessages();
        assertTrue(result.contains("Message ID:"));
        assertTrue(result.contains("Message Hash:"));
        assertTrue(result.contains("Recipient:"));
        assertTrue(result.contains("Message:"));
    }

    /**
     * Test returnTotalMessages - should return a non-negative number.
     */
    @Test
    public void testReturnTotalMessages() {
        System.out.println("returnTotalMessages");
        Messages instance = new Messages(0, "+27831234567", "Hello");
        int result = instance.returnTotalMessages();
        assertTrue(result >= 0);
    }
}

    
    