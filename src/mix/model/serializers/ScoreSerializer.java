package mix.model.serializers;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mix.model.messages.ScoreAskingMessage;
import mix.model.messages.ScoreReplyMessage;

import java.io.IOException;

public class ScoreSerializer  extends Serializer {


    public ScoreSerializer() {
        objectMapper = new ObjectMapper();
    }

    public String askingScoreToString(ScoreAskingMessage scoreAskingMessage){
        return objectToString(scoreAskingMessage);
    }

    public ScoreAskingMessage stringToAskingScore(String score){
        ScoreAskingMessage scoreAskingMessage = null;
        try{
            scoreAskingMessage = objectMapper.readValue(score,ScoreAskingMessage.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scoreAskingMessage;
    }

    public String replyToString(ScoreReplyMessage scoreReply){
        return objectToString(scoreReply);
    }

    public ScoreReplyMessage stringToReplyScore(String reply){
        ScoreReplyMessage scoreReply = null;
        try {
            scoreReply = objectMapper.readValue(reply,ScoreReplyMessage.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scoreReply;
    }


}
