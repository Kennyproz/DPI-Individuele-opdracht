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
}
