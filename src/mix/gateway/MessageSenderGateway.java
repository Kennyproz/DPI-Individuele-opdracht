package mix.gateway;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.naming.NamingException;

public class MessageSenderGateway extends MessageGateway{


    private MessageProducer producer;

    public MessageProducer getProducer() {
        return producer;
    }

    public MessageSenderGateway(String channelName) {
        setup(channelName);
    }

    public Message createTextMessage(String body, String correlationId,String type,int aggregationId){
        Message message = null;
        try {
            message = session.createTextMessage(body);
            message.setJMSCorrelationID(correlationId);
            message.setStringProperty("messageType",type);
            message.setIntProperty("aggregationId",aggregationId);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return message;
    }


    public void send(Message msg){
        try {
            producer.send(msg);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    void setDestination(String channelName) {
        // connect to the destination
        try {
            this.destination = (Destination) jndiContext.lookup(channelName);
            this.producer = this.session.createProducer(this.destination);
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
