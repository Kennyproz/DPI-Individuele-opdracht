package mix.gateway;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import mix.eventlisteners.ScoreAskingListener;
import mix.eventlisteners.TeamReplyListener;
import mix.model.domain.Aggregator;
import mix.model.messages.*;
import mix.model.serializers.ScoreSerializer;
import mix.model.serializers.TeamSerializer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClubReceiverApplicationGateway {


    private static int aggregationId = 1;
    private static int matchNumber = 1;
    private TeamSerializer teamSerializer;
    private MessageSenderGateway messageMaker;
    private MessageReceiverGateway receiver;

    private HashMap<String,ScoreAskingMessage> allScoreAsking;
    private HashMap<String,List<String>> allDestinations;

    private List<TeamReplyListener> teamReplyListeners;
    private List<Aggregator> aggregators;

    public static int getMatchNumber() {
        return matchNumber;
    }

    public ClubReceiverApplicationGateway(String receiver) {
        this.teamSerializer = new TeamSerializer();
        this.receiver = new MessageReceiverGateway(receiver);
        this.messageMaker = new MessageSenderGateway("none");

        this.allScoreAsking = new HashMap<>();
        this.allDestinations = new HashMap<>();

        this.teamReplyListeners = new ArrayList<>();
        this.aggregators = new ArrayList<>();

        this.receiver.setListener(new MessageListener(){
            @Override
            public void onMessage(Message message) {
                receivedMessage(message);
            }
        });
    }


    public void onTeamReply(TeamReplyMessage teamReplyMessage,String correlationId,int aggregationId){
        ScoreAskingMessage scoreAskingMessage = allScoreAsking.get(correlationId);
        Aggregator aggregator = null;
        for(Aggregator a : aggregators){
            if(a.getAggregationId() == aggregationId){
                aggregator = a;
            }
        }
        aggregator.addTeamReplyMessage(teamReplyMessage);

        if(aggregator.recievedAllMessages()){
            TeamReplyMessage trm = aggregator.getAllReplies().get(0);
            TeamReplyMessage trmo = aggregator.getAllReplies().get(1);

            if(trm.getScore().getTeamOne().getGoals() == trmo.getScore().getTeamOne().getGoals() && trm.getScore().getTeamTwo().getGoals() == trmo.getScore().getTeamTwo().getGoals()){
                System.out.println("Scores are the same, continue");
                for(TeamReplyListener teamReplyListener : teamReplyListeners){
                    teamReplyListener.onTeamReply(scoreAskingMessage,teamReplyMessage,correlationId);
                }
            } else{
                System.out.println("Scores are not the same, they have been sent back for confirmation..");
                InvalidScoreMessage invalidScoreMessage = new InvalidScoreMessage();
                invalidScoreMessage.setTeamReplyMessageOne(trm);
                invalidScoreMessage.setTeamReplyMessageTwo(trmo);
                sendInvalidScore(invalidScoreMessage,correlationId,aggregationId,allDestinations.get(correlationId));
            }
        }
    }

    public void sendInvalidScore(InvalidScoreMessage invalidScoreMessage,String correlationId, int aggregationId, List<String> destinations){
        Message message = messageMaker.createTextMessage(teamSerializer.invalidReplyToString(invalidScoreMessage),correlationId,"InvalidScoreMessage",aggregationId);
        setTotalMessages(destinations,message,correlationId);
    }

    public void sendTeamAsking(ScoreAskingMessage scoreAskingMessage,TeamAskingMessage teamAskingMessage,String correlationId, List<String> destinations){
        allScoreAsking.put(correlationId,scoreAskingMessage);

        teamAskingMessage.setMatchnumber(matchNumber);
        teamAskingMessage.setClubnumber(scoreAskingMessage.getClubnumber());
        teamAskingMessage.setTeam(1);

        allDestinations.put(correlationId,destinations);
        Message message = messageMaker.createTextMessage(teamSerializer.askingTeamToString(teamAskingMessage),correlationId,"TeamAskingMessage",aggregationId);

        setTotalMessages(destinations,message,correlationId);
    }

    private void setTotalMessages(List<String> destinations, Message message, String correlationId){
        int totalMessages = destinations.size();
        for(String destination : destinations){
            MessageSenderGateway messageSenderGateway = new MessageSenderGateway(destination);
            messageSenderGateway.send(message);
        }

        Aggregator aggregator = new Aggregator(aggregationId,correlationId,totalMessages);
        aggregators.add(aggregator);
        matchNumber++;
        aggregationId++;

    }



    private void receivedMessage(Message message){
        System.out.println("Received message on ClubRivalApplicationGateway: " + message);
        try{
            if(message.getStringProperty("messageType").equals("TeamReplyMessage")){
                TeamReplyMessage teamReplyMessage = teamSerializer.stringToReplyTeam(((TextMessage)message).getText());
                onTeamReply(teamReplyMessage,message.getJMSCorrelationID(),message.getIntProperty("aggregationId"));
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    public void addTeamReplyListener(TeamReplyListener teamReplyListener){
        this.teamReplyListeners.add(teamReplyListener);
    }
}
