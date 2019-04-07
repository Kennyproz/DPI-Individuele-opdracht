package mix.gateway;

import mix.eventlisteners.ScoreAskingListener;
import mix.model.messages.ScoreAskingMessage;
import mix.model.messages.ScoreReplyMessage;
import mix.model.serializers.ScoreSerializer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ClubClientApplicationGateway {

    private ScoreSerializer scoreSerializer;
    private MessageSenderGateway sender;
    private MessageReceiverGateway receiver;

    private List<ScoreAskingListener> scoreAskingListeners;

    public ClubClientApplicationGateway(String sender, String receiver) {
        this.sender = new MessageSenderGateway(sender);
        this.receiver = new MessageReceiverGateway(receiver);
        this.scoreSerializer = new ScoreSerializer();
        this.scoreAskingListeners = new ArrayList<>();


        this.receiver.setListener(new MessageListener(){
            @Override
            public void onMessage(Message message) {
                receivedMessage(message);
            }
        });
    }

    public void onScoreAskingArrived(ScoreAskingMessage sam, String correlationId){
        for(ScoreAskingListener listener : scoreAskingListeners){
            listener.onScoreAsking(sam,correlationId);
        }
    }

    private void receivedMessage(Message message){
        System.out.println("Received message on ClubClientApplicationGateway: " + message);

        try{
            if(message.getStringProperty("messageType").equals("ScoreAskingMessage")){
                ScoreAskingMessage sam = scoreSerializer.stringToAskingScore(((TextMessage)message).getText());
                onScoreAskingArrived(sam,message.getJMSCorrelationID());
            } else if(message.getStringProperty("messageType").equals("ScoreReplyMessage")){
                ScoreReplyMessage srm = scoreSerializer.stringToReplyScore(((TextMessage)message).getText());
                System.out.println("You have received a score : " + srm.getScore());
            } else{
                System.out.println("idk what this is sorry lol");
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    public void addScoreAskingListener(ScoreAskingListener listener){
        this.scoreAskingListeners.add(listener);
    }

    public void sendScoreReply(ScoreReplyMessage scoreReplyMessage, String correlationId) {
        String scoreReplyString = scoreSerializer.replyToString(scoreReplyMessage);
        Message message = sender.createTextMessage(scoreReplyString,correlationId,"ScoreReplyMessage",0);
        sender.send(message);
    }
}
