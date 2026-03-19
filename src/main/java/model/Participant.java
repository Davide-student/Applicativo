package model;
import java.util.ArrayList;
import java.util.List;

public class Participant extends User {

    private Team team;
    private ArrayList<Invite> invitesList;
    
    //Costruttore
    public Participant(String username, String password) {
        super(username, password);
        this.team = null;   //Inizialmente il partecipante non fa parte di team;
        }
    //Creazione team.
    public void createHackathonTeam(String teamName) {
        Team newTeam = new Team(teamName);
        newTeam.addMember(this);
    }
    public void receiveInvite(Invite invite) {
        this.invitesList.add(invite);
    }
    public void acceptInvite(Invite invite) {
        this.team = team;
        team.addMember(this);   //L'utente accetta l'invito e viene aggiunto al team.
        this.invitesList.remove(invite);    //L'invito è stato accettato, viene rimosso dalla "casella di posta" ("invitesList")
    }
    public void refuseInvite(Invite invite) {  //In caso di rifiuto dell'invito, non è prevista alcuna notifica al leader mittente.
    
        this.invitesList.remove(invite);
    }
}
