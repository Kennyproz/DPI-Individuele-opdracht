package mix.model.domain;

import java.util.List;

public class League {

    List<Club> clubs;

    public League() {
    }

    public List<Club> getClubs() {
        return clubs;
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
    }

    public void addClubToLeague(Club club){
        this.clubs.add(club);
    }
}
