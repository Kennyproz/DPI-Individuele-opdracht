package receiver;

import client.ClubClient;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import mix.eventlisteners.ScoreNotEqualListener;
import mix.eventlisteners.TeamAskingListener;
import mix.eventlisteners.TeamReplyListener;
import mix.gateway.SoccerCenterApplicationGateway;
import mix.model.domain.Club;
import mix.model.domain.Score;
import mix.model.domain.Team;
import mix.model.messages.InvalidScoreMessage;
import mix.model.messages.TeamAskingMessage;
import mix.model.messages.TeamReplyMessage;
import sun.plugin2.message.Message;

import java.io.IOException;
import java.util.Scanner;

public abstract class ClubReceiver {

    int clubnumber;
    private static SoccerCenterApplicationGateway soccerCenterAG;


    public ClubReceiver(String clubname,int clubnumber) {
        this.clubnumber = clubnumber;
        this.soccerCenterAG = new SoccerCenterApplicationGateway("RivalToCenter",clubname+clubnumber);

        System.out.println("Started listening to incoming messages on "+clubname+clubnumber);
    }


    public static void main(String[] args) throws IOException {
        System.out.println("ReceiverClub has been started");
        Scanner scanner = new Scanner(System.in);
        ClubReceiver rs;
        String clubname = getStringInput(scanner,"Select your clubname ","You have selected club ",true);
        String teamnumber = getStringInput(scanner,"Select your team ","You have selected team ", false);
        rs = new ReceiverClub(clubname,Integer.parseInt(teamnumber));
        rs.soccerCenterAG.addTeamAskingListener(new TeamAskingListener() {
            @Override
            public void onTeamAsking(TeamAskingMessage message, String correlationId) {
                receivedMessage(message, correlationId);
            }
        });
        rs.soccerCenterAG.addScoreNotEqualListener(new ScoreNotEqualListener() {
            @Override
            public void onScoreNotEqual(InvalidScoreMessage invalidScoreMessage, String correlationId) {
                receivedNotEqualMessage(invalidScoreMessage,correlationId);
            }
        });
    }


    public static String getStringInput(Scanner scanner, String question, String answer,boolean isString){
        boolean exit = false;

        if(isString){
            String str = null;
            while (!exit && str==null){
            System.out.println(question + " (exit to leave):");
            String input = scanner.nextLine();
            if(input != null){
                if("exit".equals(input)){
                    exit = true;
                    System.out.println("Exit");
                }
                if(input != null){
                    System.out.println(answer +  input);
                    str = input;
                }

            }
        }
            return str;
        } else {
            String s = null;
            while (!exit && s==null){
                System.out.println(question + " (exit to leave):");
                String input = scanner.nextLine();
                if(input != null){
                    if("exit".equals(input)){
                        exit = true;
                        System.out.println("Exit");
                    }
                    if(tryParseInt(input)){
                        System.out.println(answer +  input);
                        s = input;
                    }
                }
            }
            return s;
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

    public static void receivedMessage(TeamAskingMessage message, String correlationId){
        Scanner scanner = new Scanner(System.in);
        System.out.println("A club has asked what the score is of your game");
        System.out.println("Please enter the score:");

        int ownGoals = Integer.parseInt(getStringInput(scanner,"How many goals does "+ message.getReceiver().getName() + " have? ", "You have entered: ",false));
        int opponentGoals = Integer.parseInt(getStringInput(scanner,"How many goals does " + message.getOpponentReceiver().getName() +" have", "You have entered: ",false));

        Team own = message.getReceiver().getTeams().get(0);
        own.setGoals(ownGoals);
        Team other = message.getOpponentReceiver().getTeams().get(0);
        other.setGoals(opponentGoals);
        Score score = new Score(own,other);

        TeamReplyMessage teamReplyMessage = new TeamReplyMessage(message.getClubnumber(),score,message.getMatchnumber());

        System.out.println("The score that you have entered is: " + message.getReceiver().getName() + " " + ownGoals + " - " + opponentGoals + " " + message.getOpponentReceiver().getName());
        //TODO add to db
//        ClubClient.DB.getTeamReplyMessages().add(teamReplyMessage);
        soccerCenterAG.sendTeamReply(teamReplyMessage,message);
        System.out.println("Message succesfully send back to SoccerInfoCenter");
    }

    public static void receivedNotEqualMessage(InvalidScoreMessage invalidScoreMessage, String correlationId){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Your score did not correspond with the score of your opponent.");
        System.out.println("Which score is right?");
        System.out.println("1:" + invalidScoreMessage.getTeamReplyMessageOne().getScore());
        System.out.println("2:" + invalidScoreMessage.getTeamReplyMessageTwo().getScore());
        String answer = getStringInput(scanner,"Typ the score number that is right (1 or 2) ","You have selected score ",false);
        //TODO add to db
//        ClubClient.DB.getInvalidScoreMessages().add(invalidScoreMessage);
        if(Integer.parseInt(answer) == 1){
            soccerCenterAG.sendTeamReply(invalidScoreMessage.getTeamReplyMessageOne(),correlationId);
        } else if (Integer.parseInt(answer) == 2){
            soccerCenterAG.sendTeamReply(invalidScoreMessage.getTeamReplyMessageTwo(),correlationId);
        }



    }

}
