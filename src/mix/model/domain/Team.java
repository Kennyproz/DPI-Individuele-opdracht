package mix.model.domain;

public class Team {

    Club club;
    String name;
    int goals;

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team(Club club, String name) {
        this.club = club;
        this.name = name;
    }

    public Team(Club club, int goals, String name) {
        this.club = club;
        this.goals = goals;
        this.name = name;
    }
}
