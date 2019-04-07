package mix.eventlisteners;

import mix.model.messages.ScoreAskingMessage;
import mix.model.messages.TeamReplyMessage;

public interface TeamReplyListener {
    void onTeamReply(ScoreAskingMessage scoreAskingMessage, TeamReplyMessage teamReplyMessage, String correlationId);
}
