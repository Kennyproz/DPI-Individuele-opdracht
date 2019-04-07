package mix.model.domain;

public class Score {

    Team teamOne;
    Team teamTwo;

    public Team getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(Team teamOne) {
        this.teamOne = teamOne;
    }

    public Team getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(Team teamTwo) {
        this.teamTwo = teamTwo;
    }

    public Score() {
    }

    public Score(Team teamOne, Team teamTwo) {
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
    }

    @Override
    public String toString() {
        return "Score{" + teamOne.getName() + teamOne.getGoals() + " - "  + teamTwo.getGoals() + teamTwo.getName() + '}';
    }
}
