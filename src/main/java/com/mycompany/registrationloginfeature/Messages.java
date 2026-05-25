package com.mycompany.registrationloginfeature;

import java.util.Random;

public class Messages {
    // Attributes
    private String messageId;
    private int messageNumber;
    private String recipient;
    private String messageBody;
    private String messageHash;

    // Static counter to track successfully sent messages globally
    private static int totalMessagesSent = 0;

    // Constructor
    public Messages(int messageNumber, String recipient, String messageBody) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageBody = messageBody;
        this.messageId = generateMessageId();
        this.messageHash = createMessageHash();
    }

    // Generates a random 10-digit number as a String
    private String generateMessageId() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    // 1. Check Message ID Length
    public boolean checkMessageID() {
        return this.messageId != null && this.messageId.length() <= 10;
    }

    // 2. Validate Message Length
    public String checkMessageLength(String message) {
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int exceededBy = message.length() - 250;
            return "Message exceeds 250 characters by " + exceededBy + "; please reduce the size.";
        }
    }

    // 3. Validate Recipient Cell Number 
    public String checkRecipientCell(String cellNumber) {
        if (cellNumber.matches("\\+27\\d{9}")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // 4. Generate Message Hash using clean indexOf() approach
    public String createMessageHash() {
        String firstTwo = this.messageId.substring(0, 2);
        String body = this.messageBody.trim();

        String firstWord = "";
        String lastWord = "";

        int firstSpace = body.indexOf(" ");
        int lastSpace = body.lastIndexOf(" ");

        if (firstSpace == -1) {
            // Only one word exists in the message
            firstWord = body;
            lastWord = body;
        } else {
            firstWord = body.substring(0, firstSpace);
            lastWord = body.substring(lastSpace + 1);
        }

        // Clean off basic trailing punctuation marks
        firstWord = firstWord.replace(",", "").replace(".", "").replace("?", "").replace("!", "");
        lastWord = lastWord.replace(",", "").replace(".", "").replace("?", "").replace("!", "");

        String hash = firstTwo + ":" + this.messageNumber + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }

    // 5.Sub-menu switch case located inside the class
    public String sentMessage(int choice) {
        switch (choice) {
            case 1:
                totalMessagesSent++;
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                return "Message successfully stored.";
            default:
                return "Invalid selection.";
        }
    }

    // 6. Return message details
    public String printMessages() {
        return "Message ID: " + messageId + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + messageBody + "\n";
    }

    // 7. Get total accumulated sent messages
    public int returnTotalMessages() {
        return totalMessagesSent;
    }

    // 8. JSON Storage Research Stub
    public void storeMessage() {
        String jsonFormat = "{\n" +
                "  \"messageId\": \"" + messageId + "\",\n" +
                "  \"messageHash\": \"" + messageHash + "\",\n" +
                "  \"recipient\": \"" + recipient + "\",\n" +
                "  \"message\": \"" + messageBody + "\"\n" +
                "}";
        System.out.println("--> [JSON File Export Simulated]:\n" + jsonFormat);
    }
}