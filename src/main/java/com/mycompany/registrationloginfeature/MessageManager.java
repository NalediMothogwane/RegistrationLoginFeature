package com.mycompany.registrationloginfeature;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
 
/**
 *
 * @author NALEDI
 */
public class MessageManager extends Messages {
     // ----------------------------------------------------------------
    // NEW CONSTRUCTOR — used by unit tests:
    //   new MessageManager("+27834557896", "Did you get the cake?", "Sent")
    // Delegates straight to the Messages(recipient, messageText, flag)
    // constructor, so getMessageID(), getMessageHash(), getFlag(), etc.
    // ----------------------------------------------------------------
    public MessageManager(String recipient, String messageText, String flag) {
        super(recipient, messageText, flag);
    }
 
    // ----------------------------------------------------------------
    // NO-ARG CONSTRUCTOR — used when you just need a manager object
    //   MessageManager manager = new MessageManager();
    // ----------------------------------------------------------------
    public MessageManager () {
        
    }
 
    // --------------------------------------------------

    // ----------------------------------------------------------------
    // Five arrays 
    // ----------------------------------------------------------------
    private Messages[] sentMessages        = new Messages[100];
    private Messages[] disregardedMessages = new Messages[100];
    private Messages[] storedMessages      = new Messages[100];
    private String[]   messageHashes       = new String[100];
    private String[]   messageIDs          = new String[100];

    private int sentCount        = 0;
    private int disregardedCount = 0;
    private int storedCount      = 0;
    private int hashCount        = 0;
    private int idCount          = 0;

   
    // ----------------------------------------------------------------
    // Sort a message into the correct array based on its flag
    // ----------------------------------------------------------------
    public void sortMessage(Messages msg) {
        String flag = msg.getFlag().toLowerCase();

        if (flag.equals("sent")) {
            sentMessages[sentCount] = msg;
            sentCount++;
            messageHashes[hashCount] = msg.getMessageHash();
            hashCount++;
            messageIDs[idCount] = msg.getMessageID();
            idCount++;

        } else if (flag.equals("stored")) {
            storedMessages[storedCount] = msg;
            storedCount++;

        } else if (flag.equals("disregard")) {
            disregardedMessages[disregardedCount] = msg;
            disregardedCount++;
        }
    }

    // ----------------------------------------------------------------
    // Load messages from the JSON file (written by Messages.storeMessage())
    // Called once at app start-up, before the menu loop.
    // ----------------------------------------------------------------
    public void loadStoredMessagesFromFile() {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(Messages.JSON_FILE));
            JSONArray array = (JSONArray) obj;

            storedCount = 0;  // reset before loading

            for (int i = 0; i < array.size(); i++) {
                JSONObject jsonMsg  = (JSONObject) array.get(i);
                String recipient    = (String) jsonMsg.get("recipient");
                String messageText  = (String) jsonMsg.get("message");
                String flag         = (String) jsonMsg.get("flag");

                // CHANGE: uses the 3-arg constructor (recipient, body, flag)
                Messages msg = new Messages(recipient, messageText, flag);
                storedMessages[storedCount] = msg;
                storedCount++;
            }

            System.out.println("Loaded " + storedCount + " stored message(s) from file.");

        } catch (Exception e) {
            System.out.println("Could not load stored messages file: " + e.getMessage());
        }
    }

    // ----------------------------------------------------------------
    // a) Display sender ID and recipient of all stored messages
    // ----------------------------------------------------------------
    public void displayStoredSendersAndRecipients() {
        if (storedCount == 0) {
            System.out.println("No stored messages found.");
            return;
        }
        System.out.println("\n--- Stored Messages: Sender & Recipient ---");
        for (int i = 0; i < storedCount; i++) {
            System.out.println("Sender ID : " + storedMessages[i].getMessageID());
            System.out.println("Recipient : " + storedMessages[i].getRecipient());
            System.out.println("-------------------------------------------");
        }
    }

    // ----------------------------------------------------------------
    // b) Return the longest stored message body
    // ----------------------------------------------------------------
    public String getLongestStoredMessage() {
        if (storedCount == 0) {
            return "No stored messages found.";
        }
        String longest = storedMessages[0].getMessageText();
        for (int i = 1; i < storedCount; i++) {
            if (storedMessages[i].getMessageText().length() > longest.length()) {
                longest = storedMessages[i].getMessageText();
            }
        }
        return longest;
    }

    // ----------------------------------------------------------------
    // c) Search for a message by ID across sent and stored arrays
    // ----------------------------------------------------------------
    public void searchByMessageID(String searchID) {
        boolean found = false;

        for (int i = 0; i < storedCount; i++) {
            if (storedMessages[i].getMessageID().equals(searchID)) {
                System.out.println("Recipient : " + storedMessages[i].getRecipient());
                System.out.println("Message   : " + storedMessages[i].getMessageText());
                found = true;
            }
        }
        for (int i = 0; i < sentCount; i++) {
            if (sentMessages[i].getMessageID().equals(searchID)) {
                System.out.println("Recipient : " + sentMessages[i].getRecipient());
                System.out.println("Message   : " + sentMessages[i].getMessageText());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No message found with ID: " + searchID);
        }
    }

    // ----------------------------------------------------------------
    // d) Search all sent + stored messages for a particular recipient
    // ----------------------------------------------------------------
    public void searchByRecipient(String recipientNumber) {
        boolean found = false;
        System.out.println("\n--- Messages for " + recipientNumber + " ---");

        for (int i = 0; i < sentCount; i++) {
            if (sentMessages[i].getRecipient().equals(recipientNumber)) {
                System.out.println(sentMessages[i].getMessageText());
                found = true;
            }
        }
        for (int i = 0; i < storedCount; i++) {
            if (storedMessages[i].getRecipient().equals(recipientNumber)) {
                System.out.println(storedMessages[i].getMessageText());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No messages found for: " + recipientNumber);
        }
    }

    // ----------------------------------------------------------------
    // e) Delete a stored message by its hash; shift array left
    // ----------------------------------------------------------------
    public String deleteByHash(String hash) {
        int indexToDelete = -1;

        for (int i = 0; i < storedCount; i++) {
            if (storedMessages[i].getMessageHash().equals(hash)) {
                indexToDelete = i;
                break;
            }
        }
        if (indexToDelete == -1) {
            return "No message found with that hash.";
        }

        String deletedText = storedMessages[indexToDelete].getMessageText();

        for (int i = indexToDelete; i < storedCount - 1; i++) {
            storedMessages[i] = storedMessages[i + 1];
        }
        storedMessages[storedCount - 1] = null;
        storedCount--;

        return "Message: \"" + deletedText + "\" successfully deleted.";
    }

    // ----------------------------------------------------------------
    // f) Display a full report of all stored messages
    // ----------------------------------------------------------------
    public void displayReport() {
        if (storedCount == 0) {
            System.out.println("No stored messages to report.");
            return;
        }
        System.out.println("\n========== STORED MESSAGES REPORT ==========");
        for (int i = 0; i < storedCount; i++) {
            System.out.println("Message   : " + storedMessages[i].getMessageText());
            System.out.println("Hash      : " + storedMessages[i].getMessageHash());
            System.out.println("Recipient : " + storedMessages[i].getRecipient());
            System.out.println("--------------------------------------------");
        }
        System.out.println("============================================");
    }

    // ----------------------------------------------------------------
    // Getters – used by unit tests
    // CHANGE: return type is Messages[] not Message[]
    // ----------------------------------------------------------------
    public int getSentCount()              { return sentCount;        }
    public Messages[] getSentMessages()    { return sentMessages;     }
    public int getStoredCount()            { return storedCount;      }
    public Messages[] getStoredMessages()  { return storedMessages;   }
    public int getDisregardedCount()       { return disregardedCount; }
    public Messages[] getDisregardedMessages() { return disregardedMessages; }
}