package mix.model.domain;

import java.util.ArrayList;
import java.util.List;

public class League {

    List<Club> clubs;
    int leaguenumber;

    public int getLeaguenumber() {
        return leaguenumber;
    }

    public void setLeaguenumber(int leaguenumber) {
        this.leaguenumber = leaguenumber;
    }

    public List<Club> getClubs() {
        return clubs;
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
    }


    public League(int leaguenumber) {
        this.leaguenumber = leaguenumber;
        clubs = new ArrayList<>();
    }

    public Club getClubByNumber(int clubnumber){
        for(Club c : this.clubs){
            if(c.clubnumber == clubnumber){
                return c;
            }
        }
        return null;
    }


    public void addClubToLeague(Club club){
        this.clubs.add(club);
    }
}
