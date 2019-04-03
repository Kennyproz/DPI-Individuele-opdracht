package mix.model.domain;

import java.util.ArrayList;
import java.util.List;

public class Club {
    int clubnumber;
    String name;
    List<Team> teams;


    public Club(int clubnumber, String name) {
        this.clubnumber = clubnumber;
        this.name = name;
        this.createTeams();
    }

    public Club(int clubnumber, String name, List<Team> teams) {
        this.clubnumber = clubnumber;
        this.name = name;
        this.teams = teams;
    }

    private void createTeams(){
        List<Team> teams = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            Team team = new Team(this,"Team" + i);
            teams.add(team);
        }
        this.teams = teams;
    }
}
