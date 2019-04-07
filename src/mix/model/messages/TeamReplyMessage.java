package mix.model.messages;

import mix.model.domain.Score;

import java.util.List;

public class TeamReplyMessage {

    int clubnumber;
    //List<Score> scores;
    Score score;
    int matchnumber;


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

    public TeamReplyMessage() {
    }

    public TeamReplyMessage(int clubnumber, Score score) {
        this.clubnumber = clubnumber;
        this.score = score;
    }

    public TeamReplyMessage(int clubnumber, Score score, int matchnumber) {
        this.clubnumber = clubnumber;
        this.score = score;
        this.matchnumber = matchnumber;
    }

//    public void addScore(Score score)
//    {
//        this.scores.add(score);
//    }
}
