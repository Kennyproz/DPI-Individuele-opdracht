package mix.model.serializers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mix.model.messages.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;

public class TeamSerializer extends Serializer {


    public TeamSerializer() {
        objectMapper = new ObjectMapper();
    }

    public String askingTeamToString(TeamAskingMessage teamAskingMessage){
        return objectToString(teamAskingMessage);
    }

    public TeamAskingMessage stringToAskingTeam(String team){
        TeamAskingMessage teamAskingMessage = null;
        try{
            teamAskingMessage = objectMapper.readValue(team,TeamAskingMessage.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teamAskingMessage;
    }

    public String replyToString(TeamReplyMessage teamReply){
        return objectToString(teamReply);
    }

    public TeamReplyMessage stringToReplyTeam(String reply){
        TeamReplyMessage teamReply = null;
        try {
            teamReply = objectMapper.readValue(reply,TeamReplyMessage.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teamReply;
    }

    public String invalidReplyToString(InvalidScoreMessage invalidScoreMessage){
        return objectToString(invalidScoreMessage);
    }

    public InvalidScoreMessage stringToInvalidMessage(String reply){
        InvalidScoreMessage invalidScoreMessage = null;
        try {
            invalidScoreMessage = objectMapper.readValue(reply, InvalidScoreMessage.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return invalidScoreMessage;
    }

}
