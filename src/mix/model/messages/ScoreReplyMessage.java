package mix.model.messages;

import mix.model.domain.Score;
import mix.model.domain.Team;

import java.util.Calendar;
import java.util.List;

public class ScoreReplyMessage {
    int clubnumber;
    int matchnumber;
    Score score;

    Calendar minute;
    List<Team> LeagueTable;

    public int getClubnumber() {
        return clubnumber;
    }

    public void setClubnumber(int clubnumber) {
        this.clubnumber = clubnumber;
    }

    public int getMatchnumber() {
        return matchnumber;
    }

    public void setMatchnumber(int matchnumber) {
        this.matchnumber = matchnumber;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
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

    public ScoreReplyMessage(int clubnumber, int matchnumber, Score score) {
        this.clubnumber = clubnumber;
        this.matchnumber = matchnumber;
        this.score = score;
    }
}
