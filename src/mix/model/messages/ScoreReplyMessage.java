package mix.model.messages;

import mix.model.domain.Team;

import java.util.Calendar;
import java.util.List;

public class ScoreReplyMessage {
    String clubnumber;
    int opponentclubnumber;
    int team;
    String score;
    Calendar minute;
    List<Team> LeagueTable;

    public String getClubnumber() {
        return clubnumber;
    }

    public void setClubnumber(String clubnumber) {
        this.clubnumber = clubnumber;
    }

    public int getOpponentclubnumber() {
        return opponentclubnumber;
    }

    public void setOpponentclubnumber(int opponentclubnumber) {
        this.opponentclubnumber = opponentclubnumber;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
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

    public List<Team> getLeagueTable() {
        return LeagueTable;
    }

    public void setLeagueTable(List<Team> leagueTable) {
        LeagueTable = leagueTable;
    }

    public ScoreReplyMessage() {
    }
}
