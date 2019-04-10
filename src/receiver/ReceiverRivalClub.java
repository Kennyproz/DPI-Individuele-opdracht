package receiver;

import javax.jms.MessageListener;

public class ReceiverRivalClub extends ClubReceiver {
    public ReceiverRivalClub(String clubname,int clubnumber) {
        super(clubname,clubnumber);
    }
}
