import java.util.Scanner;
import java.util.ArrayList;
import javaapplication7.LOGIN;


       

public class QuickChatApp_1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        LOGIN authSystem = new LOGIN();
        
        System.out.println("--- Welcome to QuickChat: System Account Creation ---");
        System.out.print("Enter First Name: ");
        String fName = input.nextLine();
        System.out.print("Enter Last Name: ");
        String lName = input.nextLine();
        authSystem.setNames(fName, lName);

        // 1. Account Registration Loop (Part 1)
        boolean registered = false;
        while (!registered) {
            System.out.print("Create Username (Max 5 chars, must include '_'): ");
            String user = input.nextLine();
            System.out.print("Create Password (Min 8 chars, Upper, Number, Special): ");
            String pass = input.nextLine();
            System.out.print("Enter SA Cell Phone Number: ");
            String cell = input.nextLine();

            // Call the registration method from the Login class
            String regResult = authSystem.registerUser(user, pass, cell);
            System.out.println("\n" + regResult);

            // If registration is successful, break the loop
            if (regResult.contains("successfully added")) {
                registered = true;
            } else {
                System.out.println("Registration failed due to formatting errors. Please try again.\n");
            }
        }

        // 2. Authentication Login Loop (Part 1)
        boolean loggedIn = false;
        System.out.println("\n--- Access Portal: Sign-In ---");
        while (!loggedIn) {
            System.out.print("Enter Username: ");
            String loginUser = input.nextLine();
            System.out.print("Enter Password: ");
            String loginPass = input.nextLine();

            // Verify credentials using the Login class
            loggedIn = authSystem.loginUser(loginUser, loginPass);
            System.out.println(authSystem.returnLoginStatus(loggedIn));

            if (!loggedIn) {
                System.out.println("Access Denied. Let's try that again.\n");
            }
        }

        // 3. Application Menu Loop (Part 2)
        System.out.println("\nWelcome to QuickChat.");
        System.out.print("How many messages would you like to handle this session?: ");
        int maxMessages = input.nextInt();
        input.nextLine(); // Clear scanner buffer window

        ArrayList<MESSAGE> messageStore = new ArrayList<>();
        int totalMessagesSentCount = 0;
        int currentMessageCounter = 0;

        boolean running = true;
        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Select an option (1-3): ");
            
            int choice = input.nextInt();
            input.nextLine(); // Clear scanner buffer

            switch (choice) {
                case 1:
                    // Check if user exceeded the number of messages they defined at startup
                    if (currentMessageCounter >= maxMessages) {
                        System.out.println("You have reached your declared limit of " + maxMessages + " messages for this session.");
                        break;
                    }

                    System.out.print("\nEnter Recipient Cell Number: ");
                    String recipient = input.nextLine();
                    System.out.print("Enter Message text (Max 250 characters): ");
                    String text = input.nextLine();

                    // Instantiate a new Message object
                    MESSAGE currentMsg = new MESSAGE(currentMessageCounter, recipient, text);
                    
                    // Validate message character limit length rules
                    String lengthCheck = currentMsg.validateMessageLength();
                    if (lengthCheck.contains("exceeds")) {
                        System.out.println(lengthCheck);
                        System.out.println("Message discarded due to excessive length.");
                        break; 
                    }

                    // Process dynamic options task choice selection menu
                    System.out.println("\nMessage Processing Options:");
                    System.out.println("-> Type 'Send' to dispatch");
                    System.out.println("-> Type 'Store' to process later");
                    System.out.println("-> Type '0' to disregard and delete");
                    System.out.print("Your choice: ");
                    String action = input.nextLine().trim();

                    if (action.equalsIgnoreCase("Send")) {
                        currentMsg.setStatus("Sent");
                        totalMessagesSentCount++;
                        System.out.println("Message successfully sent");
                        
                        // Display full details immediately on screen after sending as required by brief
                        System.out.println("\n--- Sent Dispatch Information ---");
                        System.out.println(currentMsg.printMessageDetails());
                    } else if (action.equalsIgnoreCase("Store")) {
                        currentMsg.setStatus("Stored");
                        System.out.println("Message successfully stored");
                    } else {
                        currentMsg.setStatus("Disregarded");
                        System.out.println("Press 0 to delete the message -> Message Deleted.");
                    }

                    messageStore.add(currentMsg);
                    currentMessageCounter++;
                    break;

                case 2:
                    // Required placeholder message output matching requirements
                    System.out.println("\nComing Soon.");
                    break;

                case 3:
                    running = false;
                    // Accumulated totals displayed once program is exited
                    System.out.println("\nTotal count of successfully dispatched messages: " + totalMessagesSentCount);
                    System.out.println("Thank you for using QuickChat. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid input choice. Please select 1, 2, or 3.");
            }
        }
        input.close();
    }
}
    
    
