import java.util.Random;
public class MESSAGE {
    


    private String messageId;
    private int messageNumber;
    private String recipientCell;
    private String messageText;
    private String messageHash;
    private String status; // "Sent", "Stored", "Disregarded"

    // Constructor autogenerates the unique ID and numbers
    public MESSAGE(int messageCount, String recipientCell, String messageText) {
        this.messageNumber = messageCount;
        this.recipientCell = recipientCell;
        this.messageText = messageText;
        this.messageId = generateMessageID();
        this.messageHash = createMessageHash();
        this.status = "Disregarded"; // default
    }

    // Autogenerates a 10-digit random tracking number as a string
    private String generateMessageID() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    public boolean checkMessageID() {
        return this.messageId != null && this.messageId.length() <= 10;
    }

    // Confirms if the recipient follows length boundaries and starts with correct prefix codes
    public String checkRecipientCell() {
        if (recipientCell.startsWith("+27") || recipientCell.startsWith("27") || recipientCell.startsWith("0")) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    // Generates uppercase text hash format: "FirstTwoDigits:MessageNumber:FIRSTWORDLASTWORD"
    public String createMessageHash() {
        String firstTwoDigits = this.messageId.substring(0, 2);
        
        // Clean and split message to extract words securely
        String cleanedMsg = this.messageText.trim().replaceAll("[^a-zA-Z0-9 ]", "");
        String[] words = cleanedMsg.split("\\s+");
        
        String firstWord = words.length > 0 ? words[0] : "EMPTY";
        String lastWord = words.length > 0 ? words[words.length - 1] : "EMPTY";
        
        String hash = firstTwoDigits + ":" + this.messageNumber + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }

    // Verifies text size constraints and calculates overflow metrics if failed
    public String validateMessageLength() {
        if (this.messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int overflow = this.messageText.length() - 250;
            return "Message exceeds 250 characters by " + overflow + "; please reduce the size.";
        }
    }

    // Accessors and Mutators
    public String getMessageId() { return messageId; }
    public String getMessageHash() { return messageHash; }
    public String getRecipientCell() { return recipientCell; }
    public String getMessageText() { return messageText; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // Generates readable payload block for standard reporting
    public String printMessageDetails() {
        return "Message ID: " + messageId + 
               "\nMessage Hash: " + messageHash + 
               "\nRecipient: " + recipientCell + 
               "\nMessage: " + messageText + 
               "\nStatus: " + status + "\n";
    }
}

