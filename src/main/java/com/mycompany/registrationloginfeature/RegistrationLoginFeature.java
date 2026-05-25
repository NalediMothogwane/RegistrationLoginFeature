/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.registrationloginfeature;
import java.util.Scanner;





public class RegistrationLoginFeature {//Start of RegistrationLoginFeature

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

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

        // --- Login attempt only if registration successful ---
        if (registrationMessage.equals("User registered successfully.")) {
            System.out.print("Enter username to login: ");
            String loginUsername = sc.nextLine();

            System.out.print("Enter password to login: ");
            String loginPassword = sc.nextLine();
            //Login section
            System.out.println("===Login===");
            
            System.out.print("Enter username: ");
            String enteredUsername = sc.nextLine();
            
            System.out.print("Enter password: ");
            String enteredPassword = sc.nextLine();
            
            //Returns a login message in the Login class
            boolean loginstatus= user.loginUser(regUsername, regPassword);
            System.out.println(user.returnLoginStatus(loginstatus));
        
        }
        boolean loggedIn=true;
    
   
    
    if (loggedIn) {
            System.out.println("\nWelcome to QuickChat.");
            boolean running = true;

            while (running) {
                System.out.println("\n==== MAIN MENU ====");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.print("Select an option: ");
                
                int option = sc.nextInt();
                sc.nextLine(); // Clear buffer

                switch (option) {
                    case 1:
                        System.out.print("How many messages do you wish to enter? ");
                        int numMessages = sc.nextInt();
                        sc.nextLine(); // Clear buffer

                        //String array 
                        Messages[] messageArray = new Messages[numMessages];

                        for (int i = 0; i < numMessages; i++) {
                            System.out.println("\n--- Message " + (i + 1) + " of " + numMessages + " ---");
                            
                            // 1. Collect and Validate Recipient
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

                            // 2. Collect and Validate Message Body
                            String msgBody = "";
                            while (true) {
                                System.out.print("Enter Message (Max 250 chars): ");
                                msgBody = sc.nextLine();
                                Messages tempCheck = new Messages(i, recipient, msgBody);
                                String status = tempCheck.checkMessageLength(msgBody);
                                
                                if (status.equals("Message ready to send.")) {
                                    System.out.println("Message sent");
                                    break;
                                } else {
                                    System.out.println("Please enter a message of less than 250 characters.");
                                }
                            }

                            // 3. Save instance inside our single array
                            messageArray[i] = new Messages(i, recipient, msgBody);
                            
                            // 4. Inner sub-menu handling options
                            System.out.println("\nWhat would you like to do with this message?");
                            System.out.println("1 - Send Message");
                            System.out.println("2 - Disregard Message");
                            System.out.println("3 - Store Message to send later");
                            System.out.print("Choice: ");
                            int subChoice = sc.nextInt();
                            sc.nextLine(); // Clear buffer

                            // Calling the switch case inside the object array position
                            String innerResult = messageArray[i].sentMessage(subChoice);
                            System.out.println(innerResult);

                            // Execute JSON saving if user selects choice 3
                            if (subChoice == 3) {
                                messageArray[i].storeMessage();
                            }

                            // 5. Display complete summary details requested by prompt
                            System.out.println("\n=== MESSAGE DETAILS ===");
                            System.out.print(messageArray[i].printMessages());
                            System.out.println("=======================");
                        }
                        
                        // 6. Accumulate totals at the end of the batch run
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

                    default:
                        System.out.println("Invalid selection. Try again.");
                }
            }
        }

        System.out.println("\nProgram ended. Thank you!");  
        sc.close();
    
}
        
}//End of RegistrationLoginFeature class
