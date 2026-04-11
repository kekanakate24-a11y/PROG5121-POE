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
    
    
    
    
}
