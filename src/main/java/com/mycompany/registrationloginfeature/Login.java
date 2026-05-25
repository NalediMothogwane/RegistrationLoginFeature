/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registrationloginfeature;
import java.util.regex.Pattern;

/**
 *
 * @author NALEDI
 */
public class Login {//start of login class
    // Variables
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String cellPhoneNumber;
    private boolean isLoggedIn;

    // Constructor
    public Login(String username, String firstName, String lastName, String password, String cellPhoneNumber) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.isLoggedIn = false;
    }

    // Method: checks that username contains a"_"and is no more than 5 characters long
    public boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    }

    // Method: checks password complexity
    //checks that password contains a uppercase,a number,special chacter and is no more than 8 characters long
    public boolean checkPasswordComplexity(){
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasNumber = password.matches(".*\\d.*");
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
        boolean longEnough = password.length() >= 8;

        return hasUppercase && hasNumber && hasSpecialChar && longEnough;
    }
        
    // Method: check cell phone number using regex
    //cell
    public boolean checkCellPhoneNumber() {
        // Regex: must start with +, followed by country code and up to 10 digits
        String regex = "^\\+\\d{11,13}$";
        return Pattern.matches(regex, cellPhoneNumber);
    }

    // Method: register user
    
      
     public String registerUser(){
    
    if(!checkUserName()){
        return "Username is not correctly formatted , please ensure that your username contains an underscore and is no more than five characters in length.";
    }
 
    if (!checkPasswordComplexity()){
        return "Password is not correctly formatted , please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
    }
    
    if (!checkCellPhoneNumber()){
        return "Cell phone number incorrectly formatted or does not contain international code.";
    }
    return "User registered successfully.";
}

    // Method: login user
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        if (this.username.equals(enteredUsername) && this.password.equals(enteredPassword)) {
            isLoggedIn = true;
            return true;
        } else {
            isLoggedIn = false;
            return false;
        }
    }

    // Method: return login status
    public String returnLoginStatus(boolean loginstatus) {
        if (isLoggedIn) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    
}

    
}//End of login class
