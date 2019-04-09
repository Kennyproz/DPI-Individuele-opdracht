package mix.database;

import mix.model.messages.*;

import javax.jms.Message;
import java.util.ArrayList;
import java.util.List;

public class SoccerDatabase {

    List<TeamAskingMessage> teamAskingMessages;
    List<ScoreAskingMessage> scoreAskingMessages;
    List<ScoreReplyMessage> scoreReplyMessages;
    List<TeamReplyMessage> teamReplyMessages;
    List<InvalidScoreMessage> invalidScoreMessages;
    List<Message> deadLetterMessages;

    public List<TeamAskingMessage> getTeamAskingMessages() {
        return teamAskingMessages;
    }

    public List<ScoreAskingMessage> getScoreAskingMessages() {
        return scoreAskingMessages;
    }

    public List<ScoreReplyMessage> getScoreReplyMessages() {
        return scoreReplyMessages;
    }

    public List<TeamReplyMessage> getTeamReplyMessages() {
        return teamReplyMessages;
    }

    public List<InvalidScoreMessage> getInvalidScoreMessages() {
        return invalidScoreMessages;
    }

    public List<Message> getDeadLetterMessages() {
        return deadLetterMessages;
    }

    public void setTeamAskingMessages(List<TeamAskingMessage> teamAskingMessages) {
        this.teamAskingMessages = teamAskingMessages;
    }

    public void setScoreAskingMessages(List<ScoreAskingMessage> scoreAskingMessages) {
        this.scoreAskingMessages = scoreAskingMessages;
    }

    public void setScoreReplyMessages(List<ScoreReplyMessage> scoreReplyMessages) {
        this.scoreReplyMessages = scoreReplyMessages;
    }

    public void setTeamReplyMessages(List<TeamReplyMessage> teamReplyMessages) {
        this.teamReplyMessages = teamReplyMessages;
    }

    public void setInvalidScoreMessages(List<InvalidScoreMessage> invalidScoreMessages) {
        this.invalidScoreMessages = invalidScoreMessages;
    }

    public void setDeadLetterMessages(List<Message> deadLetterMessages) {
        this.deadLetterMessages = deadLetterMessages;
    }

    public SoccerDatabase() {
        teamAskingMessages = new ArrayList<>();
        teamReplyMessages = new ArrayList<>();
        scoreAskingMessages = new ArrayList<>();
        scoreReplyMessages = new ArrayList<>();
        deadLetterMessages = new ArrayList<>();
        invalidScoreMessages = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "SoccerDatabase{" +
                "teamAskingMessages=" + teamAskingMessages +
                ", scoreAskingMessages=" + scoreAskingMessages +
                ", scoreReplyMessages=" + scoreReplyMessages +
                ", teamReplyMessages=" + teamReplyMessages +
                ", invalidScoreMessages=" + invalidScoreMessages +
                ", deadLetterMessages=" + deadLetterMessages +
                '}';
    }
}
