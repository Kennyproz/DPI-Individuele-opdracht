package model.messages;

import model.domain.Team;

import java.util.Calendar;

public class ScoreReplyMessage {
    int clubnumber;
    int opponentclubnumber;
    int team;
    String score;
    Calendar minute;
    List<Team> LeagueTable;
}
