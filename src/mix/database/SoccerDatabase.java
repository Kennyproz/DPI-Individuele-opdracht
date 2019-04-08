package mix.database;

import mix.model.messages.*;

import java.util.ArrayList;
import java.util.List;

public class SoccerDatabase {

    List<TeamAskingMessage> teamAskingMessages;
    List<ScoreAskingMessage> scoreAskingMessages;
    List<ScoreReplyMessage> scoreReplyMessages;
    List<TeamReplyMessage> teamReplyMessages;
    List<InvalidScoreMessage> invalidScoreMessages;

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

    public SoccerDatabase() {
        teamAskingMessages = new ArrayList<>();
        teamReplyMessages = new ArrayList<>();
        scoreAskingMessages = new ArrayList<>();
        scoreReplyMessages = new ArrayList<>();
    }

    public void addTeamAskingMessage(TeamAskingMessage message){
        teamAskingMessages.add(message);
    }

    public void addTeamReplyMessage(TeamReplyMessage message){
        teamReplyMessages.add(message);
    }

    public void addScoreAskingMessage(ScoreAskingMessage message){
        scoreAskingMessages.add(message);
    }

    public void addScoreReplyMessage(ScoreReplyMessage message){
        scoreReplyMessages.add(message);
    }

    public void addInvalidScoreMessage(InvalidScoreMessage message){
        invalidScoreMessages.add(message);
    }


}
