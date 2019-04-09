package mix.gateway;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.ArrayList;
import java.util.List;

public class  DatabaseApplicationGateway {
    private MessageReceiverGateway messageReceiverGateway;

    private List<Message> allMessages;
    private List<MessageListener> messageListeners;
    private DeadLetterGateway deadLetterGateway;

    public DatabaseApplicationGateway() {
        messageListeners = new ArrayList<>();
        deadLetterGateway = new DeadLetterGateway();
        messageReceiverGateway = new MessageReceiverGateway("DatabaseChannel");
        messageReceiverGateway.setListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                ReceivedMessage(message);
            }
        });
        allMessages = new ArrayList<>();
    }

    public void addMessageListener(MessageListener messageListener){
        this.messageListeners.add(messageListener);

    }

    public void ReceivedMessage(Message message){
        allMessages.add(message);
        try {
            System.out.println(message.getStringProperty("messageType") +" has been received on the DatabaseChannel: " + message);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
