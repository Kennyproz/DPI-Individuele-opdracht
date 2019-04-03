package receiver;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.io.IOException;
import java.util.Scanner;

public class ReceiverClub implements MessageListener {

    int clubnumber;

    public ReceiverClub(int clubnumber) {
        this.clubnumber = clubnumber;
        ReceiverSender receiverSender = new ReceiverSender(true,"ReceiverClub");
        receiverSender.ReceiveMessage(this);
        System.out.println("Started listening to incoming messages on ReceiverClub"+clubnumber);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("SoccerInfoCenter has been started");
        System.out.println("What is your teamNumber?");
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        ReceiverClub rs;
        String teamnumber = null;
        while (!exit && teamnumber==null){
            System.out.println("Select your team number (exit to leave):");
            String input = scanner.nextLine();
            if(input != null){
                if("exit".equals(input)){
                    exit = true;
                    System.out.println("Exit");
                }
                if(tryParseInt(input)){
                    System.out.println("You have selected team " +  input);
                    teamnumber = input;
                    rs = new ReceiverClub(Integer.parseInt(input));
                }

            }
        }
        scanner.close();


    }

    @Override
    public void onMessage(Message message) {
        try {
            if(message.getIntProperty("askingclubnumber") == clubnumber){
                System.out.println("message received for me" + message);
            } else {
                System.out.println("Received message that is not for me..");
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static boolean tryParseInt(String value){
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
