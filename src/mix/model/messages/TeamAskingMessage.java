package mix.model.messages;

public class TeamAskingMessage {

    int clubnumber;
    int team;
    int aggregationId;

    public TeamAskingMessage() {
    }

    public TeamAskingMessage(int clubnumber, int team, int aggregationId) {
        this.clubnumber = clubnumber;
        this.team = team;
        this.aggregationId = aggregationId;
    }
}
