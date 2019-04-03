package mix.model.domain;

public class Score {

    Team teamOne;
    Team teamTwo;



    public Score(Team teamOne, Team teamTwo) {
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
    }

    @Override
    public String toString() {
        return "Score{" + teamOne.getName() + teamOne.getGoals() + " - "  + teamTwo.getGoals() + teamTwo.getName() + '}';
    }
}
