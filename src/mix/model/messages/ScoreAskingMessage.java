package mix.model.messages;

import java.util.Calendar;

public class ScoreAskingMessage {
    int clubnumber;
    int team;
    int opponentclubnumber;
    String score;
    Calendar minute;

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

    public int getOpponentclubnumber() {
        return opponentclubnumber;
    }

    public void setOpponentclubnumber(int opponentclubnumber) {
        this.opponentclubnumber = opponentclubnumber;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Calendar getMinute() {
        return minute;
    }

    public void setMinute(Calendar minute) {
        this.minute = minute;
    }

    public ScoreAskingMessage() {

    }

    public ScoreAskingMessage(int clubnumber, int team) {
        this.clubnumber = clubnumber;
        this.team = team;
    }
}
