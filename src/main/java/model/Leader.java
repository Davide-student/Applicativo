package model;


public class Leader extends Participant {
    //Costruttore.
    public Leader(String username, String password) {
           super(username, password);
    }
    public void publishTeamUpdate(Team team, Update update) {
                team.addUpdate(update);
    }
    /*
    public void sendInviteToParticipant(Participant){

    }
    */
}