package mix.gateway;

import mix.eventlisteners.ScoreAskingListener;
import mix.eventlisteners.ScoreNotEqualListener;
import mix.eventlisteners.ScoreReplyListener;
import mix.eventlisteners.TeamAskingListener;
import mix.model.domain.Score;
import mix.model.messages.*;
import mix.model.serializers.ScoreSerializer;
import mix.model.serializers.TeamSerializer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class SoccerCenterApplicationGateway {

    private ScoreSerializer scoreSerializer;
    private TeamSerializer teamSerializer;
    private MessageSenderGateway sender;
    private MessageReceiverGateway receiver;

    private List<TeamAskingListener> teamAskingListeners;
    private List<ScoreReplyListener> scoreReplyListeners;
    private List<ScoreNotEqualListener> scoreNotEqualListeners;

    private HashMap<String, Integer> allAggregations;
    private HashMap<String, ScoreAskingMessage> allScoreAsking;
    private HashMap<TeamAskingMessage,String> allTeamAsking;
    private HashMap<TeamReplyMessage,String> allTeamFailing;

    public SoccerCenterApplicationGateway(String sender, String receiver) {
        this.scoreSerializer = new ScoreSerializer();
        this.teamSerializer = new TeamSerializer();
        this.sender = new MessageSenderGateway(sender);
        this.receiver = new MessageReceiverGateway(receiver);

        this.teamAskingListeners = new ArrayList<>();
        this.scoreReplyListeners = new ArrayList<>();
        this.scoreNotEqualListeners = new ArrayList<>();

        this.allAggregations = new HashMap<>();
        this.allScoreAsking = new HashMap<>();
        this.allTeamAsking = new HashMap<>();
        this.allTeamFailing = new HashMap<>();

        this.receiver.setListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                receivedMessage(message);
            }
        });
    }

    public void askForScore(ScoreAskingMessage scoreAskingMessage){
        String askingString = scoreSerializer.askingScoreToString(scoreAskingMessage);
        Calendar calendar = Calendar.getInstance();
        String correlationId = (Integer.toString(scoreAskingMessage.getClubnumber()) + calendar.getTimeInMillis());

        Message message = sender.createTextMessage(askingString,correlationId,"ScoreAskingMessage",0);
        sender.send(message);
    }

    public void onScoreReplyArrived(ScoreReplyMessage scoreReplyMessage, String correlationId){
        ScoreAskingMessage scoreAskingMessage = allScoreAsking.get(correlationId);
        for(ScoreReplyListener scoreReplyListener : scoreReplyListeners){
            scoreReplyListener.onScoreReply(scoreAskingMessage,scoreReplyMessage);
        }
    }

    public void onTeamAsking(TeamAskingMessage teamAskingMessage, String correlationId, int aggregationId){
        allTeamAsking.put(teamAskingMessage,correlationId);
        allAggregations.put(correlationId,aggregationId);
        for(TeamAskingListener listener : teamAskingListeners){
            listener.onTeamAsking(teamAskingMessage,correlationId);
        }
    }

    public void sendTeamReply(TeamReplyMessage teamReplyMessage, TeamAskingMessage teamAskingMessage){
        String correlationId = allTeamAsking.get(teamAskingMessage);
        int aggregationId = allAggregations.get(correlationId);
        String teamReplyMessageString = teamSerializer.replyToString(teamReplyMessage);
        Message message = sender.createTextMessage(teamReplyMessageString,correlationId,"TeamReplyMessage",aggregationId);
        sender.send(message);
    }

    public void sendTeamReply(TeamReplyMessage teamReplyMessage, String correlationId){
        int aggregationId = allAggregations.get(correlationId);
        String teamReplyMessageString = teamSerializer.replyToString(teamReplyMessage);
        Message message = sender.createTextMessage(teamReplyMessageString,correlationId,"TeamReplyMessage",aggregationId);
        sender.send(message);
    }

    public void onInvalidScore(InvalidScoreMessage invalidScoreMessage, String correlationId,int aggregationId){
        allTeamFailing.put(invalidScoreMessage.getTeamReplyMessageOne(),correlationId);
        allTeamFailing.put(invalidScoreMessage.getTeamReplyMessageTwo(),correlationId);
        allAggregations.put(correlationId,aggregationId);
        for(ScoreNotEqualListener listener : scoreNotEqualListeners){
            listener.onScoreNotEqual(invalidScoreMessage,correlationId);
        }
    }

    private void receivedMessage(Message message){
        System.out.println("Received message on SoccerCenterApplicationGateway: " + message);
        try{
            if(message.getStringProperty("messageType").equals("TeamAskingMessage")){
                TeamAskingMessage sam = teamSerializer.stringToAskingTeam(((TextMessage)message).getText());
                onTeamAsking(sam,message.getJMSCorrelationID(),message.getIntProperty("aggregationId"));
            } else if(message.getStringProperty("messageType").equals("ScoreReplyMessage")){
                ScoreReplyMessage scoreReplyMessage = scoreSerializer.stringToReplyScore(((TextMessage)message).getText());
                onScoreReplyArrived(scoreReplyMessage,message.getJMSCorrelationID());
            } else if(message.getStringProperty("messageType").equals("InvalidScoreMessage")){
                InvalidScoreMessage invalidScoreMessage = teamSerializer.stringToInvalidMessage(((TextMessage)message).getText());
                System.out.println(invalidScoreMessage);
                onInvalidScore(invalidScoreMessage,message.getJMSCorrelationID(),message.getIntProperty("aggregationId"));
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void addScoreReplyListener(ScoreReplyListener scoreReplyListener){
        this.scoreReplyListeners.add(scoreReplyListener);
    }

    public void addTeamAskingListener(TeamAskingListener teamAskingListener){
        this.teamAskingListeners.add(teamAskingListener);
    }

    public void addScoreNotEqualListener(ScoreNotEqualListener scoreNotEqualListener){
        this.scoreNotEqualListeners.add(scoreNotEqualListener);
    }




}
