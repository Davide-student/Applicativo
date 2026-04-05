package model;


public class Leader extends Participant {
    //Costruttore.
    public Leader(String username, String password, Team team, Hackathon hackathon) {
           super(username, password, hackathon);
           this.team = team;   //Il leader ha sicuramente un team.
    }
    public void publishTeamUpdate(String title, String description, String release) {   //Viene creato un'update
        Update update = new Update(title, description, release, team);                                   //La descrizione è intesa come file di testo
        team.addUpdate(update);
    }
    public void createInvite(String text, Participant receiver){
        Invite invite = new Invite(this.team, text, receiver, this); //Viene creato un invito per cui si specificano mittente e destinatario
        receiver.addInvite(invite);
    }
}