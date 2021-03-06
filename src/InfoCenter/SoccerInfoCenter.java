package InfoCenter;

import client.ClubClient;
import mix.database.LeagueSetup;
import mix.database.SoccerDatabase;
import mix.eventlisteners.ScoreAskingListener;
import mix.eventlisteners.TeamReplyListener;
import mix.gateway.ClubClientApplicationGateway;
import mix.gateway.ClubReceiverApplicationGateway;
import mix.gateway.DatabaseApplicationGateway;
import mix.model.domain.Club;
import mix.model.domain.League;
import mix.model.domain.Team;
import mix.model.messages.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SoccerInfoCenter{

    private ClubClientApplicationGateway clubClientAG;
    private ClubReceiverApplicationGateway clubReceiverAG;
    private League soccerLeague;


    public SoccerInfoCenter() {
        setupLeague();

        clubClientAG = new ClubClientApplicationGateway("CenterToClient","ClientToCenter");
        clubReceiverAG = new ClubReceiverApplicationGateway("RivalToCenter");
        clubClientAG.addScoreAskingListener(new ScoreAskingListener() {
            @Override
            public void onScoreAsking(ScoreAskingMessage scoreAskingMessage, String correlationId) {
                List<Club> rivals =  getRivalClubs(scoreAskingMessage);
                //TODO add to db
                TeamAskingMessage teamAskingMessage = new TeamAskingMessage(scoreAskingMessage.getClubnumber(),clubReceiverAG.getMatchNumber(),rivals.get(0),rivals.get(1));
//                ClubClient.DB.getTeamAskingMessages().add(teamAskingMessage);
                clubReceiverAG.sendTeamAsking(scoreAskingMessage,teamAskingMessage,correlationId,getRivals(scoreAskingMessage));
            }
        });
        clubReceiverAG.addTeamReplyListener(new TeamReplyListener() {
            @Override
            public void onTeamReply(ScoreAskingMessage scoreAskingMessage, TeamReplyMessage teamReplyMessage, String correlationId) {
                ScoreReplyMessage scoreReplyMessage = new ScoreReplyMessage(teamReplyMessage.getClubnumber(),teamReplyMessage.getMatchnumber(),teamReplyMessage.getScore());
                //TODO add to db
//                ClubClient.DB.getScoreReplyMessages().add(scoreReplyMessage);
                clubClientAG.sendScoreReply(scoreReplyMessage,correlationId);
            }
        });

        System.out.println("Started listening to incoming messages on SoccerInfoCenter");
    }

    public static void main(String[] args) throws IOException {

        SoccerInfoCenter sic = new SoccerInfoCenter();
        System.out.println("SoccerInfoCenter has been started");




        System.in.read();
    }

    public List<String> getRivals(ScoreAskingMessage scoreAskingMessage){
        List<String> rivals = new ArrayList<>();
        Club current = soccerLeague.getClubByNumber(scoreAskingMessage.getClubnumber());
        int i = 0;
        for(Club c : soccerLeague.getClubs()){
            if(c.getClubnumber()!= current.getClubnumber() && i < 2){
                System.out.println("Message had been send to rival: " + c.getName() + "1");
                rivals.add(c.getName() + "1");
                i++;
            }
        }
        return rivals;
    }

    public List<Club> getRivalClubs(ScoreAskingMessage scoreAskingMessage){
        List<Club> rivals = new ArrayList<>();
        Club current = soccerLeague.getClubByNumber(scoreAskingMessage.getClubnumber());
        int i = 0;
        for (Club c : soccerLeague.getClubs()){
            if(c.getClubnumber()!= current.getClubnumber() && i < 2){
                rivals.add(c);
                i++;
            }
        }
        return rivals;
    }

    public void setupLeague(){
        LeagueSetup setup = new LeagueSetup();
        this.soccerLeague = setup.getLeague(1);

        System.out.println("League is set up and contains of the following clubs:" );
        for (Club c : soccerLeague.getClubs()){
            System.out.println("Name= " + c.getName() + " Clubnumber = "+ c.getClubnumber());
//            for(Team t : c.getTeams()){
//                System.out.println(t);
//            }
        }


    }

}
