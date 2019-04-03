package InfoCenter;

import mix.model.messages.AskingReply;
import mix.model.messages.TeamAskingMessage;
import receiver.ReceiverSender;

import javax.jms.Message;
import javax.jms.MessageListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class SoccerInfoCenter implements MessageListener {

    private HashMap<String, AskingReply> hashMap;

    public SoccerInfoCenter() {
        hashMap = new HashMap<>();
        ReceiverSender rs = new ReceiverSender(true,"SoccerInfoCenterEntrance");
        rs.ReceiveMessage(this);
        System.out.println("Started listening to incoming messages on SoccerInfoCenter");
    }

    public static void main(String[] args) throws IOException {

        SoccerInfoCenter sic = new SoccerInfoCenter();
        System.out.println("SoccerInfoCenter has been started");



        System.in.read();
    }

    @Override
    public void onMessage(Message message) {
        System.out.println("Received Message on SoccerInfoCenter" + message);
        TeamAskingMessage tam = new TeamAskingMessage();
        AskingReply askingReply = new AskingReply(tam,null);


    }
}
