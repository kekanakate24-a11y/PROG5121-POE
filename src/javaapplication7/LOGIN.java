
package javaapplication7;

 import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LOGIN {
    

    private String registeredUsername;
    private String registeredPassword;
    private String firstName;
    private String lastName;
    private String registeredCellNumber;

    // Setters for names since they are used in the welcome messaging
    public void setNames(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Check if Username contains an underscore and is no more than 5 characters long
    public boolean checkUserName(String username) {
        if (username == null) return false;
        return username.contains("_") && username.length() <= 5;
    }

    // Check Password Complexity
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) return false;

        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        // Common regex pattern for special characters
        Pattern special = Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>/?~`|\\\\]");

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isDigit(c)) hasDigit = true;
        }

        Matcher hasSpecialMatcher = special.matcher(password);
        if (hasSpecialMatcher.find()) hasSpecial = true;

        return hasUpper && hasDigit && hasSpecial;
    }

    // Regular Expression-based cell phone checker for international code (+27 or 27) and matching constraints
    public boolean checkCellPhoneNumber(String cellNumber) {
        if (cellNumber == null) return false;
        // Matches +27 or 27 followed by 7 to 9 digits depending on exact localized interpretation
        String regex = "^(\\+27|27)\\d{7,9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cellNumber);
        return matcher.matches();
    }

    // Registers the user and returns appropriate status messages
    public String registerUser(String username, String password, String cellNumber) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        // Successfully capture fields
        this.registeredUsername = username;
        this.registeredPassword = password;
        this.registeredCellNumber = cellNumber;

        return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
    }

    // Verifies login credentials match registration data
    public boolean loginUser(String username, String password) {
        if (this.registeredUsername == null || this.registeredPassword == null) {
            return false;
        }
        return this.registeredUsername.equals(username) && this.registeredPassword.equals(password);
    }

    // Returns dynamic login welcome or error feedback 
    public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            return "Welcome " + this.firstName + ", " + this.lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}

