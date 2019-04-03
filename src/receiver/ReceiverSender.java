package receiver;

import com.fasterxml.jackson.databind.ObjectMapper;
import mix.model.messages.ScoreAskingMessage;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class ReceiverSender {
    Connection connection; // to connect to the JMS
    Session session; // session for creating consumers

    Destination sendDestination;
    Destination receiveDestination; //reference to a queue/topic destination

    MessageConsumer consumer; // for receiving messages
    MessageProducer producer; // for sending messages

    String destination;
    boolean isReciever;

    public ReceiverSender(boolean isReciever, String destination) {
        this.isReciever = isReciever;
        this.destination = destination;
        setup();
    }

    public void setup(){
        try {
            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                    "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");

            // connect to the Destination called “myFirstChannel”
            // queue or topic: “queue.myFirstDestination” or
            // “topic.myFirstDestination”
            props.put(("queue." + destination), destination);

            Context jndiContext = new InitialContext(props);
            ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext
                    .lookup("ConnectionFactory");
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // connect to the destination
            if(isReciever){
                receiveDestination = (Destination) jndiContext.lookup(destination);
                consumer = session.createConsumer(receiveDestination);
            } else{
                sendDestination = (Destination) jndiContext.lookup(destination);
                producer = session.createProducer(sendDestination);
            }

        } catch(NamingException | JMSException e){
            e.printStackTrace();
        }
    }

    public void ReceiveMessage(MessageListener listener) {
        try {
            connection.start(); // this is needed to start receiving messages
            consumer.setMessageListener(listener);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void SendMessage(Object object, String body, Message message,String key){
        try {
            Message msg = session.createTextMessage(body);
            if(message != null){
                msg.setJMSCorrelationID(message.getJMSCorrelationID());
            }
            if (key != null ){
                msg.setJMSCorrelationID(key);
            }

            ObjectMapper om = new ObjectMapper();
            //msg.setJMSCorrelationID("Test");
            //System.out.println(msg.getJMSCorrelationIDAsBytes());
            // if(msg.getStringProperty("messageType")!= null){
            msg.setStringProperty("messageType", body);
            // }
            // create a text message
            if(object instanceof ScoreAskingMessage)
            {
                msg.setJMSCorrelationID(Integer.toString(((ScoreAskingMessage) object).getClubnumber()));
                // String content = om.writeValueAsString(object);
            }
            // send the message
            producer.send(msg);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
