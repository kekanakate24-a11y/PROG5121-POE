
package userregistration;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;


public class Messages {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    private static int totalMessages = 0;

    // Constructor
    public Messages(int messageNumber, String recipient, String messageText) {
        this.messageID = generateMessageID();
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }

    // Generate random 10 digit message ID
    private String generateMessageID() {
        Random rand = new Random();
        long num = (long)(Math.random() * 1_000_000_0000L);
        return String.format("%010d", num);
    }

    // Validate SA phone number
    public static boolean checkRecipientCell(String number) {
        return number.matches("^\\+27\\d{9}$");
    }

    // Validate message length
    public static boolean checkMessageLength(String msg) {
        return msg.length() <= 250;
    }

    // Create message hash
    private String createMessageHash() {
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        return messageID.substring(0, 2) + ":" +
               messageNumber + ":" +
               (firstWord + lastWord).toUpperCase();
    }

    // Display message info
    public String printMessage() {
        return "\n--- Message Sent ---" +
               "\nMessage ID: " + messageID +
               "\nMessage Hash: " + messageHash +
               "\nRecipient: " + recipient +
               "\nMessage: " + messageText;
    }

    // Save message to JSON file
    public void storeMessageJSON() {
        JSONArray messageList = new JSONArray();
        JSONObject messageDetails = new JSONObject();

        messageDetails.put("MessageID", messageID);
        messageDetails.put("MessageHash", messageHash);
        messageDetails.put("Recipient", recipient);
        messageDetails.put("Message", messageText);

        messageList.add(messageDetails);

        try (FileWriter file = new FileWriter("messages.json", true)) {
            file.write(messageList.toJSONArray());
            file.write(System.lineSeparator());
            System.out.println("Message stored in JSON file.");
        } catch (IOException e) {
            System.out.println("Error writing to JSON file.");
        }
    }

    // Message menu (send, discard, store)
    public void messageMenu() {
        Scanner input = new Scanner(System.in);

        System.out.println("\nChoose an option:");
        System.out.println("1) Send Message");
        System.out.println("2) Discard Message");
        System.out.println("3) Store Message");

        int choice = input.nextInt();

        switch (choice) {
            case 1:
                totalMessages++;
                System.out.println(printMessage());
                break;

            case 2:
                System.out.println("Message discarded.");
                break;

            case 3:
                storeMessageJSON();
                break;

            default:
                System.out.println("Invalid option.");
        }
    }

    public static int returnTotalMessages() {
        return totalMessages;
    }
}
