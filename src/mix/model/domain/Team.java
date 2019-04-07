package mix.model.domain;

public class Team {

    int clubnumber;
    String name;
    int goals;

    public int getClubnumber() {
        return clubnumber;
    }

    public void setClubnumber(int clubnumber) {
        this.clubnumber = clubnumber;
    }

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


    public Team() {
    }

    public Team(int clubnumber, String name) {
        //this.goals = 0;
        this.clubnumber = clubnumber;
        this.name = name;
    }

    public Team(int clubnumber, int goals, String name) {
        this.clubnumber = clubnumber;
        this.goals = goals;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Team{" +
                "clubnumber=" + clubnumber +
                ", name='" + name + '\'' +
                ", goals=" + goals +
                '}';
    }
}
