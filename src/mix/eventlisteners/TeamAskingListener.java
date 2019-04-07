package mix.eventlisteners;

import mix.model.messages.TeamAskingMessage;

public interface TeamAskingListener {
    void onTeamAsking(TeamAskingMessage message, String correlationId);
}
