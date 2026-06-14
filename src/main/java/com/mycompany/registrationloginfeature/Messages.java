package com.mycompany.registrationloginfeature;

import java.util.Random;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileWriter;

public class Messages {

    // ----------------------------------------------------------------
    // Attributes
    // ----------------------------------------------------------------
    private String messageId;
    private int    messageNumber;
    private String recipient;
    private String messageBody;
    private String messageHash;
    private String flag;           // "Sent" | "Stored" | "Disregard"

    private static int totalMessagesSent = 0;
    static final String JSON_FILE = "stored_messages.json";

    // ----------------------------------------------------------------
    // CONSTRUCTOR 1 – used by your Part 2 menu (messageNumber + recipient + body)
    // ----------------------------------------------------------------
    public Messages(int messageNumber, String recipient, String messageBody) {
        this.messageNumber = messageNumber;
        this.recipient     = recipient;
        this.messageBody   = messageBody;
        this.messageId     = generateMessageId();
        this.messageHash   = createMessageHash();
        this.flag          = "";
    }

    // ----------------------------------------------------------------
    // CONSTRUCTOR 2 – used by MessageManager.loadStoredMessagesFromFile()
    //                 and by the Part 3 unit tests:
    //                 new Message(recipient, messageText, flag)
    // ----------------------------------------------------------------
    public Messages(String recipient, String messageBody, String flag) {
        this.messageNumber = 0;          // not used when loading from file
        this.recipient     = recipient;
        this.messageBody   = messageBody;
        this.flag          = flag;
        this.messageId     = generateMessageId();
        this.messageHash   = createMessageHash();
    }
    //No-arg constructor – required because MessageManager extends Messages.
    public Messages() {
    this.messageNumber = 0;
    this.recipient     = "";
    this.messageBody   = "";
    this.flag          = "";
    this.messageId     = generateMessageId();
    this.messageHash   = "";
}

    // ----------------------------------------------------------------
    // Generate a random 10-digit Message ID
    // ----------------------------------------------------------------
    private String generateMessageId() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    // ----------------------------------------------------------------
    // 1. Check Message ID is at most 10 characters
    // ----------------------------------------------------------------
    public boolean checkMessageID() {
        return this.messageId != null && this.messageId.length() <= 10;
    }

    // ----------------------------------------------------------------
    // 2. Validate message length (max 250 characters)
    // ----------------------------------------------------------------
    public String checkMessageLength(String message) {
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int exceededBy = message.length() - 250;
            return "Message exceeds 250 characters by " + exceededBy
                 + "; please reduce the size.";
        }
    }

    // ----------------------------------------------------------------
    // 3. Validate recipient cell number (+27 followed by 9 digits)
    // ----------------------------------------------------------------
    // Cell phone number validation implemented using Java regex (Pattern matching).
// Reference: https://www.w3schools.com/java/java_regex.asp
    public String checkRecipientCell(String cellNumber) {
        if (cellNumber.matches("\\+27\\d{9}")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain "
                 + "an international code. Please correct the number and try again.";
        }
    }

    // ----------------------------------------------------------------
    // 4. Generate Message Hash
    //    Format: <first 2 chars of ID>:<messageNumber>:<firstWord><lastWord>
    //    All uppercase
    // ----------------------------------------------------------------
    public String createMessageHash() {
        String firstTwo = this.messageId.substring(0, 2);
        String body     = this.messageBody.trim();

        String firstWord;
        String lastWord;

        int firstSpace = body.indexOf(" ");
        int lastSpace  = body.lastIndexOf(" ");

        if (firstSpace == -1) {
            firstWord = body;
            lastWord  = body;
        } else {
            firstWord = body.substring(0, firstSpace);
            lastWord  = body.substring(lastSpace + 1);
        }

        firstWord = firstWord.replace(",","").replace(".","").replace("?","").replace("!","");
        lastWord  = lastWord .replace(",","").replace(".","").replace("?","").replace("!","");

        String hash = firstTwo + ":" + this.messageNumber + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }

    // ----------------------------------------------------------------
    // 5. Sub-menu: Send / Disregard / Store
    //    Sets the flag so MessageManager.sortMessage() routes correctly.
    // ----------------------------------------------------------------
    public String sentMessage(int choice) {
        switch (choice) {
            case 1:
                totalMessagesSent++;
                this.flag = "Sent";
                return "Message successfully sent.";
            case 2:
                this.flag = "Disregard";
                return "Press 0 to delete the message.";
            case 3:
                this.flag = "Stored";
                storeMessage();            // persist to JSON file
                return "Message successfully stored.";
            default:
                return "Invalid selection.";
        }
    }

    // ----------------------------------------------------------------
    // 6. Print full message details
    //    Order per spec: Message ID, Message Hash, Recipient, Message
    // ----------------------------------------------------------------
    public String printMessages() {
        return "Message ID: "   + messageId   + "\n"
             + "Message Hash: " + messageHash + "\n"
             + "Recipient: "    + recipient   + "\n"
             + "Message: "      + messageBody + "\n";
    }

    // ----------------------------------------------------------------
    // 7. Return accumulated total of sent messages
    // ----------------------------------------------------------------
    public int returnTotalMessages() {
        return totalMessagesSent;
    }

    // ----------------------------------------------------------------
    // 8. storeMessage() – Part 2 requirement
    //
    //    Saves this message to stored_messages.json using json-simple.
    //    Research approach:
    //      a) Parse the existing JSON array from the file (or start fresh).
    //      b) Build a new JSONObject with all required fields.
    //      c) Append it to the array.
    //      d) Write the entire updated array back to the file.
    //
    //    The "flag" field is included so Part 3 can reload and sort messages.
    //    The "messageId" and "messageHash" fields are included so the
    //    Part 3 report can display them after loading from file.
    // ----------------------------------------------------------------
    // JSON storage implemented using the json-simple library.
// Reference: https://code.google.com/archive/p/json-simple/
    public void storeMessage() {
        try {
            // Step 1: Read existing data from file (start fresh if missing)
            JSONArray existingMessages = new JSONArray();
            try {
                JSONParser parser = new JSONParser();
                Object parsed = parser.parse(new FileReader(JSON_FILE));
                existingMessages = (JSONArray) parsed;
            } catch (Exception e) {
                // File not found yet – that is fine, we start with empty array
            }

            // Step 2: Build a JSONObject for this message
            JSONObject entry = new JSONObject();
            entry.put("messageId",   this.messageId);
            entry.put("messageHash", this.messageHash);
            entry.put("recipient",   this.recipient);
            entry.put("message",     this.messageBody);
            entry.put("flag",        this.flag.isEmpty() ? "Stored" : this.flag);

            // Step 3: Append to the existing array
            existingMessages.add(entry);

            // Step 4: Write the full updated array back to the file
            FileWriter writer = new FileWriter(JSON_FILE);
            writer.write(existingMessages.toJSONString());
            writer.flush();
            writer.close();

            System.out.println("Message successfully stored to " + JSON_FILE);

        } catch (Exception e) {
            System.out.println("Error saving message to file: " + e.getMessage());
        }
    }

    // ----------------------------------------------------------------
    // Getters – required by MessageManager (Part 3) and unit tests
    // ----------------------------------------------------------------
    public String getMessageID()   { return messageId;   }
    public String getMessageHash() { return messageHash; }
    public String getRecipient()   { return recipient;   }
    public String getMessageText() { return messageBody; }
    public String getFlag()        { return flag;        }
}