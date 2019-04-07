package mix.model.domain;

import mix.model.messages.TeamAskingMessage;
import mix.model.messages.TeamReplyMessage;

import java.util.ArrayList;
import java.util.List;

public class Aggregator {
    private int aggregationId;
    private String correlationId;
    private int expectedReplies;
    private List<TeamReplyMessage> allReplies;


    public List<TeamReplyMessage> getAllReplies() {
        return allReplies;
    }

    public Aggregator(int aggregationId, String correlationId, int expectedReplies) {
        this.aggregationId = aggregationId;
        this.correlationId = correlationId;
        this.expectedReplies = expectedReplies;
        this.allReplies = new ArrayList<>();
    }

    public int getAggregationId() {
        return aggregationId;
    }


    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }


    public void addTeamReplyMessage(TeamReplyMessage teamReplyMessage){
        allReplies.add(teamReplyMessage);
    }

    public TeamReplyMessage getReply(){

       // If both score the same continue, else send messsage back

        for (TeamReplyMessage tr : allReplies){

        }
//        for(BankInterestReply bir : allReplies){
//            if (bir.getInterest() < lowestInterest.getInterest()){
//                lowestInterest = bir;
//            }
//        }
//        return lowestInterest;
        return null;
    }

    public boolean recievedAllMessages(){
        return expectedReplies == allReplies.size();
    }

    public boolean scoreAreEqual(){
        return true;

    }
}
