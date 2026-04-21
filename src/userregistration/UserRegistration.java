
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
        
        System.out.print("Enter first name: ");
        String firstName = input.nextLine();
        
        System.out.print("Enter last name: ");
        String lastName = input.nextLine();

      
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
    
    // quick chat message app
    if(loginStatus){
        System.out.println("WELCOME TO QUICKCHAT");
        System.out.print("How many messages do you want to print?");
        int maxMessages = input.nextInt();
        input.nextLine();
        
        int messageCount = 0;
        
        while(true){
            System.out.println("MAIN MENU");
            System.out.println("Option 1) Send Messages");
            System.out.println("Option 2) Show recently sent messages ");
            System.out.println("Option 3) Quit");
            int option = input.nextLine();
            
            switch (option){
                
                //option 1 send messages
                case 1:
                    if(messageCount >= maxMessages){
                        System.out.println("You have reached your message limit");
                        
                        break;
                    
                    System.out.print("Enter recepient phone number (+27...)");
                    String recepient = input.nextLine();
                    
                    if(!Message.checkRecepientCell(recepient)) {System.out.println("Invalid phone number.");
                        break;
                    }
                    System.out.print("Enter message with maximum 250 characters: ");
                    String text = input.nextLine();
                    
                    if(!checkMessageLength(text)){
                       System.out.println("Message exceeds 250 characters.");
                    break;
                    }
                    Messages newMsg = new Message(messageCount + 1, recepient, text);
                    newMsg.messageMenu();
                    
 }      
}}}}}