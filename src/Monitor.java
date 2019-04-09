import client.ClubClient;
import mix.database.SoccerDatabase;
import mix.gateway.DatabaseApplicationGateway;
import mix.model.serializers.ScoreSerializer;
import mix.model.serializers.TeamSerializer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.IOException;
import java.lang.management.MonitorInfo;
import java.util.Scanner;

public class Monitor {

    private static ScoreSerializer scoreSerializer;
    private static TeamSerializer teamSerializer;
    public static SoccerDatabase database = new SoccerDatabase();
    DatabaseApplicationGateway databaseApplicationGateway;


    public Monitor() {
        scoreSerializer = new ScoreSerializer();
        teamSerializer = new TeamSerializer();
        databaseApplicationGateway = new DatabaseApplicationGateway();
        databaseApplicationGateway.addMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                receivedMessage(message);
            }
        });
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Monitor started..");
        Scanner scanner = new Scanner(System.in);
        Monitor monitor = new Monitor();
        boolean exit = false;

        while (!exit){
            String input = scanner.nextLine();
            if(input != null){
                if("exit".equals(input)){
                    exit = true;
                    System.out.println("Exit");
                }
                System.out.println(database.toString());
            }
        }
        scanner.close();
    }

    public void receivedMessage(Message message){
        System.out.println(message);
        String type = null;
        try {
            type = message.getStringProperty("messageType");
            switch (type){
                case "ScoreAskingMessage":
                    database.getScoreAskingMessages().add(this.scoreSerializer.stringToAskingScore((((TextMessage)message).getText())));
                    break;
                case "ScoreReplyMessage":
                    database.getScoreReplyMessages().add(this.scoreSerializer.stringToReplyScore((((TextMessage)message).getText())));
                    break;
                case "TeamAskingMessage":
                    database.getTeamAskingMessages().add(this.teamSerializer.stringToAskingTeam((((TextMessage)message).getText())));
                    break;
                case "TeamReplyMessage":
                    database.getTeamReplyMessages().add(this.teamSerializer.stringToReplyTeam((((TextMessage)message).getText())));
                    break;
                case "InvalidScoreMessage":
                    database.getInvalidScoreMessages().add(this.teamSerializer.stringToInvalidMessage((((TextMessage)message).getText())));
                    break;
                default:
                    System.out.println(message);
                    break;
                }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
