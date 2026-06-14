/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.registrationloginfeature;
import java.util.Scanner;
import java.util.Scanner;






public class RegistrationLoginFeature {//Start of RegistrationLoginFeature
     static Scanner sc = new Scanner(System.in);
        static MessageManager manager = new MessageManager();

        

    public static void main(String[] args) {
        // Collect registration details
        System.out.print("Enter username: ");
        String regUsername = sc.nextLine(); 

        System.out.print("Enter first name: ");
        String firstName = sc.nextLine();

        System.out.print("Enter last name: ");
        
        String lastName = sc.nextLine(); 

        System.out.print("Enter password: ");
        String regPassword = sc.nextLine();

        System.out.print("Enter South African cell phone number: ");
        String cellPhoneNumber = sc.nextLine();

        // Create Login object
        Login user = new Login(regUsername, firstName, lastName, regPassword, cellPhoneNumber);

        // --- Registration checks ---
        if (user.checkUserName()) {
            System.out.println("Username successfully captured.");
        } else {
            System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
        }

        if (user.checkPasswordComplexity()) {
            System.out.println("Password successfully captured.");
        } else {
            System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
        }

        if (user.checkCellPhoneNumber()) {
            System.out.println("Cell phone number successfully added.");
        } else {
            System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
        }

        // --- Registration summary ---
        String registrationMessage = user.registerUser();
        System.out.println(registrationMessage);
   
        // ----------------------------------------------------------------
        // LOGIN
        // ----------------------------------------------------------------
        if (registrationMessage.equals("User registered successfully.")) {
            System.out.print("Enter username to login: ");
            String enteredUsername = sc.nextLine();

            System.out.print("Enter password to login: ");
            String enteredPassword = sc.nextLine();

            boolean loginstatus = user.loginUser(enteredUsername, enteredPassword);
            System.out.println(user.returnLoginStatus(loginstatus));

            // ----------------------------------------------------------------
            // MAIN MENU — only accessible after successful login
            // ----------------------------------------------------------------
            if (loginstatus) {
                System.out.println("\nWelcome to QuickChat.");

                // Part 3: Load any previously stored messages from file
                manager.loadStoredMessagesFromFile();

                boolean running = true;

                while (running) {
                    System.out.println("\n==== MAIN MENU ====");
                    System.out.println("1) Send Messages");
                    System.out.println("2) Show recently sent messages");
                    System.out.println("3) Quit");
                    System.out.println("4) Stored Messages");
                    System.out.print("Select an option: ");

                    int option = sc.nextInt();
                    sc.nextLine(); // clear buffer

                    switch (option) {

                        case 1:
                            System.out.print("How many messages do you wish to enter? ");
                            int numMessages = sc.nextInt();
                            sc.nextLine(); // clear buffer

                            Messages[] messageArray = new Messages[numMessages];

                            for (int i = 0; i < numMessages; i++) {
                                System.out.println("\n--- Message " + (i + 1) + " of " + numMessages + " ---");

                                // Validate recipient
                                String recipient = "";
                                while (true) {
                                    System.out.print("Enter Recipient Cell Number: ");
                                    recipient = sc.nextLine();
                                    Messages tempCheck = new Messages(i, recipient, "");
                                    String status = tempCheck.checkRecipientCell(recipient);
                                    System.out.println(status);
                                    if (status.equals("Cell phone number successfully captured.")) {
                                        break;
                                    }
                                }

                                // Validate message body
                                String msgBody = "";
                                while (true) {
                                    System.out.print("Enter Message (Max 250 chars): ");
                                    msgBody = sc.nextLine();
                                    Messages tempCheck = new Messages(i, recipient, msgBody);
                                    String status = tempCheck.checkMessageLength(msgBody);
                                    if (status.equals("Message ready to send.")) {
                                        System.out.println("Message ready to send.");
                                        break;
                                    } else {
                                        System.out.println(status);
                                    }
                                }

                                // Save message in array
                                messageArray[i] = new Messages(i, recipient, msgBody);

                                // Sub-menu
                                System.out.println("\nWhat would you like to do with this message?");
                                System.out.println("1 - Send Message");
                                System.out.println("2 - Disregard Message");
                                System.out.println("3 - Store Message to send later");
                                System.out.print("Choice: ");
                                int subChoice = sc.nextInt();
                                sc.nextLine(); // clear buffer

                                String innerResult = messageArray[i].sentMessage(subChoice);
                                System.out.println(innerResult);

                                // Sort message into correct array (Part 3)
                                manager.sortMessage(messageArray[i]);

                                // Display message details
                                System.out.println("\n=== MESSAGE DETAILS ===");
                                System.out.print(messageArray[i].printMessages());
                                System.out.println("=======================");
                            }

                            if (numMessages > 0) {
                                System.out.println("\n>>> Total Accumulated Messages Successfully Sent: "
                                        + messageArray[0].returnTotalMessages() + " <<<");
                            }
                            break;

                        case 2:
                            System.out.println("Coming Soon.");
                            break;

                        case 3:
                            running = false;
                            break;

                        case 4:
                            storedMessagesMenu();
                            break;

                        default:
                            System.out.println("Invalid selection. Try again.");
                    }
                }
            }
        }

        System.out.println("\nProgram ended. Thank you!");
        sc.close();
    }

    // ----------------------------------------------------------------
    // Part 3: Stored Messages sub-menu (options a to f)
    // ----------------------------------------------------------------
    public static void storedMessagesMenu() {
        boolean inMenu = true;

        while (inMenu) {
            System.out.println("\n--- Stored Messages ---");
            System.out.println("a) Display sender and recipient of all stored messages");
            System.out.println("b) Display the longest stored message");
            System.out.println("c) Search for a message by ID");
            System.out.println("d) Search messages for a particular recipient");
            System.out.println("e) Delete a message using the message hash");
            System.out.println("f) Display report");
            System.out.println("g) Back to main menu");
            System.out.print("Choose: ");

            String choice = sc.nextLine().trim().toLowerCase();

            switch (choice) {
                case "a":
                    manager.displayStoredSendersAndRecipients();
                    break;

                case "b":
                    System.out.println("Longest message: " + manager.getLongestStoredMessage());
                    break;

                case "c":
                    System.out.print("Enter message ID: ");
                    String id = sc.nextLine().trim();
                    manager.searchByMessageID(id);
                    break;

                case "d":
                    System.out.print("Enter recipient number: ");
                    String recipientSearch = sc.nextLine().trim();
                    manager.searchByRecipient(recipientSearch);
                    break;

                case "e":
                    System.out.print("Enter message hash: ");
                    String hash = sc.nextLine().trim();
                    System.out.println(manager.deleteByHash(hash));
                    break;

                case "f":
                    manager.displayReport();
                    break;

                case "g":
                    inMenu = false;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }



        
        

    }
