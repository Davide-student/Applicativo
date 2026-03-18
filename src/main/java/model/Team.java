package model;
import java.util.ArrayList;

public class Team {
    private String name;
    private int memberNumber;
    private int finalProjectRating;
    private ArrayList<Participant> teamMembers;
    private Hackathon hackathon;

    //Costruttore.
    public Team(String name) {
        this.name = name;
        this.teamMembers = new ArrayList<Participant>();
        this.memberNumber = 0;
    }
    //Aggiunta dei membri al team.
    public void addMember(Participant member) {
        this.teamMembers.add(member);
    }
}
