package model;

import java.util.ArrayList;
import java.io.File;

public class Judge extends User
{
    private ArrayList<Hackathon> judgedHackathons;  //Lista degli hackathon a cui "Judge" partecipa
    private ArrayList<Invite> invitesList;  //Lista di inviti ricevuti da "Organizer" per essere giudice di un certo evento

    
    //Costruttore
    public Judge(String username, String password)
    {
        super(username, password);  //Richiamo il costruttore di user
        this.judgedHackathons = new ArrayList<Hackathon>();
    }
    public void addJudgedHackathon(Hackathon hackathon)
    {
        this.judgedHackathons.add(hackathon);
    }

    //Metodi getter e setter
    public ArrayList<Hackathon> getJudgedHackathons()
    {
        return this.judgedHackathons;
    }
    public int getJudgedHackathonsNumber()
    {
        return this.judgedHackathons.size();
    }
    public void setInvitesList(ArrayList<Invite> invitesList)
    {
        this.invitesList = invitesList;
    }
    //Metodi del domain model
    public void createOpinion(Update update, String description)
    {
            Opinion opinion = new Opinion(this.username, description); //Viene create un'opinione (L'username passato corrisponde username)
                                                                       //"username" del giudice che crea l'opinione.
            update.addOpinion(opinion);  //L'opinione viene assegnata all'update
    }
    public void publishRating(Team team, int rating)
    {
        team.addRating(rating);   //Viene aggiunto il voto di "istanza judge" alla lista dei voti associati al team.
    }
    public void publishProblemDescription(Hackathon hackathon, String description)
    {
        hackathon.setDescription(description);
    }

    public void receiveInvite(Invite invite)    //In caso di rifiuto dell'invito, il "Judge" mittente non viene notificato.
    {
        this.invitesList.add(invite);
    }
    public void acceptInvite(Invite invite) 
    {
        this.judgedHackathons.add(invite.getHackathon());
        invite.getHackathon().addJudge(this);
        this.invitesList.remove(invite); //L'invito è stato accettato, viene rimosso dalla "casella di posta" ("invitesList").
    }
    public void refuseInvite(Invite invite)
    {
        this.invitesList.remove(invite);
    }

    
}