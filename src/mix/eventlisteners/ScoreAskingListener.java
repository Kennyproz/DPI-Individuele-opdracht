package mix.eventlisteners;

import mix.model.messages.ScoreAskingMessage;

public interface ScoreAskingListener {
    void onScoreAsking(ScoreAskingMessage scoreAskingMessage, String correlationId);
}
