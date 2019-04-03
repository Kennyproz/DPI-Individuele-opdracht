import mix.model.domain.Club;
import mix.model.domain.League;

import java.util.ArrayList;
import java.util.List;

public class LeagueSetup {


    List<League> leagueList = new ArrayList<>();
    List<Club> clubList = new ArrayList<>();


    public LeagueSetup() {
        this.setUp();
    }

    public void setUp(){
        //Creating league
        League league1 = new League();

        Club BarcaB = new Club(1,"Barca B");
        Club RealB = new Club(2, "Real B");
        Club PSVB = new Club(3, "Phillips B");
        Club AJAXB = new Club(4, "Amsterdam B");
        Club CHELSEAB = new Club(5, "CHELSEA B");
        Club ManUB = new Club(6, "Manchester UB");

        league1.addClubToLeague(BarcaB);
        league1.addClubToLeague(RealB);
        league1.addClubToLeague(PSVB);
        league1.addClubToLeague(AJAXB);
        league1.addClubToLeague(CHELSEAB);
        league1.addClubToLeague(ManUB);



    }


}
