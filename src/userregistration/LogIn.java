/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userregistration;

/**
 *
 * @author kekan
 */
import java.util.regex.Pattern;

public class LogIn {
    
    private String storedUsername;
    private String storedPassword;
    private String storedCell;
    private String firstName;
    private String lastName;
    

 // username checks for underscore ( _ ) and length <= 5 characters
    public boolean checkUsername(String username) {
        return username.contains("_")&&username.length()<=5;
    }
//password check for number, special character, capital letter and length >=8 characters
    public boolean checkPasswordComplexity(String password) {
       String regex = "(?=.[A-Z])(=.*[0-9](?=.*[!@#$%^&*].{8,}$";
       return Pattern.matches(regex, password);
    }
// phone number check if south african with international code(+27)
    public boolean checkCellPhoneNumber(String number) {
        String regex = "\\+27[0-9]{9}$";
        return Pattern.matches(regex, number);
    }
    // registration method 
   
    // username
    public String registerUser(String username, String password, String cell) {
        if (!checkUsername(username)){
            return "Password is not correctly formatted; please ensure that the useername contains an underscore and is no more than five characters in length.";
        }
    // password
    if (!checkPasswordComplexity(password)) {
        return "Password not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number and a special character";
    } 
    
    // cell number
    if (!checkCellPhoneNumber(cell)){
        return "Cell phone number incorrectly formatted or does not contain international code.";
    } 
    storedUsername = username;
    
    storedPassword = password;
    storedCell = cell;
    
    return "User successfully registered.";
    }
    
// login check
    public boolean loginUser(String username, String password) {
        return username.equals(storedUsername)&&password.equals(storedPassword);
    }
// login message
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
// login display
    public String returnLoginStatus(boolean status) {
        if (status){
            return "Welcome back" + firstName + "," + lastName + "It is great to see you again.";
        } else {
            return "Username or password incorrect, please try again";
        }
    }
    
}
