package client;

import java.io.IOException;
import java.util.Scanner;

import mix.eventlisteners.ScoreReplyListener;
import mix.gateway.SoccerCenterApplicationGateway;
import mix.model.messages.ScoreAskingMessage;
import mix.model.messages.ScoreReplyMessage;


public class ClubClient {

    private SoccerCenterApplicationGateway soccerCenterAG;


    public ClubClient() {
        soccerCenterAG = new SoccerCenterApplicationGateway("ClientToCenter","CenterToClient");
        soccerCenterAG.addScoreReplyListener(new ScoreReplyListener() {
            @Override
            public void onScoreReply(ScoreAskingMessage scoreAskingMessage, ScoreReplyMessage scoreReplyMessage) {
                System.out.println("Score received: " + scoreReplyMessage.getScore());
            }
        });
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome, Select as which club you'd like to send a message:");
        ClubClient clubClient = new ClubClient();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit){
            System.out.println("Typ the clubnumber you want to send a message as (1-5) (type exit to quit)");
            String input = scanner.nextLine();
            if(input != null){
                if("exit".equals(input)){
                    exit = true;
                    System.out.println("Exit");
                }
                if(tryParseInt(input)){
                    if(Integer.parseInt(input) >= 1 && Integer.parseInt(input) <= 5){
                        ScoreAskingMessage sam = new ScoreAskingMessage(Integer.parseInt(input),1);
                        clubClient.soccerCenterAG.askForScore(sam);
                        System.out.println("Message had been sended with clubnumber " + input);
                    } else{
                        System.out.println("Number not right");
                    }
                }

            }
        }
        scanner.close();

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
