/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.registrationloginfeature;
import java.util.Scanner;

/**
 *
 * @author NALEDI
 */
public class RegistrationLoginFeature {//Start of RegistrationLoginFeature

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Collect registration details
        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter first name: ");
        String firstName = sc.nextLine();

        System.out.print("Enter last name: ");
        String lastName = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        System.out.print("Enter South African cell phone number: ");
        String cellPhoneNumber = sc.nextLine();

        // Create Login object
        Login user = new Login(username, firstName, lastName, password, cellPhoneNumber);

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
            
            System.out.println("===Login===");
        
        if(user.checkUserName()&& user.checkPasswordComplexity()){
            System.out.println("Welcome <User first name>User lastname> it is great to see you again.");
        }else{
            System.out.println("Username or Password incorrect, please try again.");
        }
        
       
    



    }
}

        
    
}//End of RegistrationLoginFeature class
