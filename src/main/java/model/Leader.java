package model;


public class Leader extends Participant {
    //Costruttore.
    public Leader(String username, String password, Team team) {
           super(username, password);
            this.team = team;   //Il leader ha sicuramente un team.
    }
    public void publishTeamUpdate(Team team, String title, String description, int releaseNumber) {   //Viene creato un'update
        Update update = new Update(title, description, releaseNumber);                                   //La descrizione è intesa come file di testo
        team.addUpdate(update);
    }
    public void createInvite(String text, Participant receiver){
        Invite invite = new Invite(this.team, text, receiver, this); //Viene creato un invito per cui si specificano mittente e destinatario
    }
}