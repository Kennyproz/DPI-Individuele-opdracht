package mix.model.messages;

public class InvalidScoreMessage {
    TeamReplyMessage teamReplyMessageOne;
    TeamReplyMessage teamReplyMessageTwo;

    public TeamReplyMessage getTeamReplyMessageOne() {
        return teamReplyMessageOne;
    }

    public void setTeamReplyMessageOne(TeamReplyMessage teamReplyMessageOne) {
        this.teamReplyMessageOne = teamReplyMessageOne;
    }

    public TeamReplyMessage getTeamReplyMessageTwo() {
        return teamReplyMessageTwo;
    }

    public void setTeamReplyMessageTwo(TeamReplyMessage teamReplyMessageTwo) {
        this.teamReplyMessageTwo = teamReplyMessageTwo;
    }

    public InvalidScoreMessage() {
    }

    public InvalidScoreMessage(TeamReplyMessage teamReplyMessageOne, TeamReplyMessage teamReplyMessageTwo) {
        this.teamReplyMessageOne = teamReplyMessageOne;
        this.teamReplyMessageTwo = teamReplyMessageTwo;
    }

    @Override
    public String toString() {
        return "InvalidScoreMessage{" +
                "teamReplyMessageOne=" + teamReplyMessageOne +
                ", teamReplyMessageTwo=" + teamReplyMessageTwo +
                '}';
    }
}
