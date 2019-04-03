package mix.gateway;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.naming.NamingException;

public class MessageReceiverGateway extends MessageGateway {

    private MessageConsumer consumer;

    public MessageReceiverGateway(String channelName) {
        setup(channelName);
    }


    public void setListener(MessageListener ml){
        try {
            this.connection.start();
            consumer.setMessageListener(ml);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    void setDestination(String channelName) {
        try {
            this.destination = (Destination) jndiContext.lookup(channelName);
            this.consumer = this.session.createConsumer(this.destination);
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
