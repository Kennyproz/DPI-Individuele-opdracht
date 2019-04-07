package mix.eventlisteners;


import mix.model.messages.InvalidScoreMessage;


public interface ScoreNotEqualListener {
    void onScoreNotEqual(InvalidScoreMessage invalidScoreMessage, String correlationId);
}
