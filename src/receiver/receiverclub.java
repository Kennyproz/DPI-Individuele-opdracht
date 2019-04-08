package receiver;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.io.IOException;
import java.util.Scanner;

public class ReceiverClub extends ClubReceiver{
    public ReceiverClub(String clubname, int clubnumber) {
            super(clubname,clubnumber);
        }
}


