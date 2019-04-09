package mix.gateway;

import client.ClubClient;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.ArrayList;
import java.util.List;

public class DeadLetterGateway {
    private MessageReceiverGateway messageReceiverGateway;
    private MessageSenderGateway notifyClient;

    public DeadLetterGateway() {
        notifyClient = new MessageSenderGateway("DlqToClient");
        messageReceiverGateway = new MessageReceiverGateway("ActiveMQ.DLQ");
        messageReceiverGateway.setListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                ReceivedMessage(message);
            }
        });
    }

    public void ReceivedMessage(Message message){
        notifyClient.send(message);
        System.out.println("Message has expired and entered the DeadLetterQueue: " + message);
    }




}
