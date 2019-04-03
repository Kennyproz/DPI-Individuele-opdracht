package client;

import javax.jms.Message;
import javax.jms.MessageListener;
import java.io.IOException;
import java.util.Calendar;

import mix.model.messages.ScoreAskingMessage;
import receiver.ReceiverSender;


public class ClubClient implements MessageListener {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome, Select as which club you'd like to send a message:");

        System.out.println("Press something to ask a score");

        System.in.read();
        ScoreAskingMessage sam = new ScoreAskingMessage(1,1);
        Calendar calendar = Calendar.getInstance();
        String correlationId = (Integer.toString(sam.getClubnumber())+calendar.getTimeInMillis());

        ReceiverSender rs = new ReceiverSender(false,"SoccerInfoCenterEntrance");
        rs.SendMessage(sam,"ScoreAskingMessage",null,correlationId);
        System.out.println("Message had been sended");
        System.in.read();

    }

    @Override
    public void onMessage(Message message) {

    }
}
