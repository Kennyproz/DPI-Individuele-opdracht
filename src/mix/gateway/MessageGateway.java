package mix.gateway;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public abstract class MessageGateway {

    protected Connection connection;
    protected Session session;
    protected Destination destination;
    protected Context jndiContext;

    public void setup(String channelName){
        try {
            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                    "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");

            // connect to the Destination called “myFirstChannel”
            // queue or topic: “queue.myFirstDestination” or
            // “topic.myFirstDestination”
            props.put(("queue." + channelName), channelName);

            this.jndiContext = null;
            this.jndiContext = new InitialContext(props);
            ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext
                    .lookup("ConnectionFactory");
            this.connection = connectionFactory.createConnection();
            this.session = this.connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            this.setDestination(channelName);

        } catch (NamingException e) {
            e.printStackTrace();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    abstract void setDestination(String channelName);
}
