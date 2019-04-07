package mix.database;

import mix.model.domain.Club;
import mix.model.domain.League;

import java.util.ArrayList;
import java.util.List;

public class LeagueSetup {


    List<League> leagueList = new ArrayList<>();

    public List<League> getLeagueList() {
        return leagueList;
    }

    public void setLeagueList(List<League> leagueList) {
        this.leagueList = leagueList;
    }

    public League getLeague(int number){
        return this.leagueList.get(number-1);
    }

    public LeagueSetup() {
        this.setUp();
    }

    public void setUp(){
        //Creating league
        League league1 = new League(1);

        Club BarcaB = new Club(1,"BARCA");
        Club RealB = new Club(2, "REAL");
        Club PSVB = new Club(3, "PHILLIPS");
        Club AJAXB = new Club(4, "AMSTERDAM");
        Club CHELSEAB = new Club(5, "CHELSEA");
        Club ManUB = new Club(6, "ManchesterUB");

        league1.addClubToLeague(BarcaB);
        league1.addClubToLeague(RealB);
        league1.addClubToLeague(PSVB);
        league1.addClubToLeague(AJAXB);
        league1.addClubToLeague(CHELSEAB);
        league1.addClubToLeague(ManUB);
        leagueList.add(league1);

    }



}
