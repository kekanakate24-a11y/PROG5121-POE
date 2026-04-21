
package userregistration;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Messages {
    private String messageID;
    private int messageNumber;
    private String recepient;
    private String messageText;
    private String messageHash;
    
    private static int totalMessages = 0;
    
    // message contructor
    public Message(int messageNumber, String recepient, String messageText){
        
        this.messageID = generateMessageID();
        this.messageNumber = messageNumber;
        this.recepient = recepient;
        this.messageText = messageText;
        this.messageHash = createMessageHash;
    }
    
    // random 10 digit message ID
    private String generateMessageID(){
        Random rand = new Random();
        long num = (long)(Math.random()*1_000_000_0000L);
        return String.format("%010d", num);
    }
    
    // South african phone number validation
    public static boolean checkMessageLength(String msg){
        return msg.length() <=250;
    }
    
            
            
}
