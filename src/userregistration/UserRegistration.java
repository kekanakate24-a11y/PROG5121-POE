
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
    }
    
}
