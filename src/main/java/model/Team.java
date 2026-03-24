package model;
import java.util.ArrayList;

public class Team {
    private String name;
    private int memberNumber;
    private int finalProjectRating;
    private ArrayList<Participant> teamMembers;
    private ArrayList<Integer> ratings;
    private ArrayList<Update> updates;
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
        this.memberNumber++;
    }
    public void addRating(int rating){
        this.ratings.add(rating);
    }
    public void addUpdate(Update update){
        this.updates.add(update);
    }
    //Metodi setter e getter
    public int getFinalProjectRating() {
        return this.finalProjectRating;
    }
    public String getName(){
        return this.name;
    }
}
