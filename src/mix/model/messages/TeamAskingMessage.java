package mix.model.messages;

import mix.model.domain.Club;

public class TeamAskingMessage {

    int clubnumber;
    int team;
    int matchnumber;
    Club receiver;
    Club opponentReceiver;


    public Club getReceiver() {
        return receiver;
    }

    public void setReceiver(Club receiver) {
        this.receiver = receiver;
    }

    public Club getOpponentReceiver() {
        return opponentReceiver;
    }

    public void setOpponentReceiver(Club opponentReceiver) {
        this.opponentReceiver = opponentReceiver;
    }

    public int getClubnumber() {
        return clubnumber;
    }

    public void setClubnumber(int clubnumber) {
        this.clubnumber = clubnumber;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public int getMatchnumber() {
        return matchnumber;
    }

    public void setMatchnumber(int matchnumber) {
        this.matchnumber = matchnumber;
    }

    public TeamAskingMessage() {
    }

    public TeamAskingMessage(int clubnumber, int team, int matchnumber) {
        this.clubnumber = clubnumber;
        this.team = team;
        this.matchnumber = matchnumber;
    }

    public TeamAskingMessage(int clubnumber, int matchnumber, Club receiver, Club opponentReceiver) {
        this.clubnumber = clubnumber;
        this.matchnumber = matchnumber;
        this.receiver = receiver;
        this.opponentReceiver = opponentReceiver;
    }
}
