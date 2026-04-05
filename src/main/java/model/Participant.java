package model;
import java.util.ArrayList;

public class Participant extends User {

    protected Team team;
    private Hackathon hackathon;
    private ArrayList<Invite> invitesList;
    
    //Costruttore
    public Participant(String username, String password, Hackathon hackathon) {
        super(username, password);
        this.team = null;   //Inizialmente il partecipante non fa parte di team;
        this.invitesList = new ArrayList<Invite>();
        this.hackathon = hackathon;
    }
    //Creazione team.
    public Team createHackathonTeam(String teamName, Hackathon hackathon) {
        Team newTeam = new Team(teamName, hackathon);
        newTeam.addMember(this);
        hackathon.addTeam(newTeam);
        return newTeam;
    }
    public void receiveInvite(Invite invite) {
        this.invitesList.add(invite);
    }
    public void acceptInvite(Invite invite) {
        this.team = team;
        team.addMember(this);   //Il partecipante accetta l'invito e viene aggiunto al team.
        this.invitesList.remove(invite);    //L'invito è stato accettato, viene rimosso dalla "casella di posta" ("invitesList")
    }
    public void refuseInvite(Invite invite) {  //In caso di rifiuto dell'invito, non è prevista alcuna notifica al leader mittente.
    
        this.invitesList.remove(invite);
    }
    public void addInvite(Invite invite) {
        this.invitesList.add(invite);
    }
    //Metodi setter e getter
    public Team getTeam() {
        return this.team;
    }
    public void setTeam(Team team) {
        this.team = team;
    }
    public ArrayList<Invite> getInvitesList(){
        return this.invitesList;
    }
    public Hackathon getHackathon() {
        return this.hackathon;
    }

}
