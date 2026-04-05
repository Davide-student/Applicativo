package model;
import java.util.ArrayList;

public class Team {
    private String name;
    private int finalProjectRating;
    private ArrayList<Participant> teamMembers;
    private ArrayList<Integer> ratings;
    private ArrayList<Update> updates;
    private Hackathon hackathon;

    //Costruttore.
    public Team(String name, Hackathon hackathon) {
        this.name = name;
        this.teamMembers = new ArrayList<Participant>();
        this.ratings = new ArrayList<Integer>();
        this.updates = new ArrayList<Update>();
        this.hackathon = hackathon;
        this.finalProjectRating = 0;
    }
    //Aggiunta dei membri al team.
    public void addMember(Participant member) {
        this.teamMembers.add(member);
    }
    public void addRating(int rating){
        this.ratings.add(rating);
    }
    public void computeFinalProjectRating() {
        for(Integer rating : ratings) {
            this.finalProjectRating += rating;
        }
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
    public int getMembersNumber() {
        return this.teamMembers.size();
    }
    public Hackathon getHackathon() {
        return this.hackathon;
    }
    public ArrayList<Update> getUpdates()
    {
        return this.updates;
    }
    public Update getLatestUpdate()
    {
        return this.updates.get(this.updates.size()-1);
    }
    public ArrayList<Participant> getTeamMembers()
    {
        return this.teamMembers;
    }
}
