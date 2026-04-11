
package userregistration;

import java.util.Scanner;

public class UserRegistration {

    
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        LogIn login = new LogIn();
        
        System.out.println(" REGISTER NEW ACCOUNT ");
        
        System.out.print("Enter username: ");
        String username = input.nextLine();
        
        System.out.print("Enter password: ");
        String password = input.nextLine();
        
        System.out.print("Enter cell phone number (+27): ");
        String cell = input.nextLine();
        
        String registrationResult = login.registerUser(username, password, cell);
        
        System.out.println(registrationResult);
    
    
    // logging in the user
    if (registrationResult.equals("User successfully registered.")){
        System.out.println(" LOGIN ");
        
        System.out.print("Enter username: ");
        String logInUser = input.nextLine();
        
        System.out.print("Enter password: ");
        String logInPass = input.nextLine();
        
        
    boolean loginStatus = login.loginUser(logInUser, logInPass);
    System.out.println(login.returnLoginStatus(loginStatus));
    }
  }
}
