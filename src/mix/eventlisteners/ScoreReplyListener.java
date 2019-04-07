package mix.eventlisteners;

import mix.model.messages.ScoreAskingMessage;
import mix.model.messages.ScoreReplyMessage;

public interface ScoreReplyListener {
    void onScoreReply(ScoreAskingMessage scoreAskingMessage, ScoreReplyMessage scoreReplyMessage);
}
