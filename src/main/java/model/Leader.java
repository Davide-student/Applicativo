package model;


public class Leader extends Participant {
    //Costruttore.
    public Leader(String username, String password, Team team) {
           super(username, password);
            this.team = team;   //Il leader ha sicuramente un team.
    }
    public void publishTeamUpdate(Team team, Update update) {
                team.addUpdate(update);
    }
    public void createInvite(String text, Participant receiver){
        Invite invite = new invite(text, receiver, this); //Viene creato un invito per cui si specificano mittente e destinatario
        invite.inviteMember(this.team, invite);
    }
}