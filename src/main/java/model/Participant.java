package model;
import java.util.ArrayList;
import java.util.List;

public class Participant extends User {
    public Participant(String username, String password) {
        super(username, password);
        }
    //Creazione team.
    public void createHackathonTeam(String teamName) {
        Team newTeam = new Team(teamName);
        newTeam.addMember(this);
    }
    /*
    public void receiveTeamInvite() {
    }
    public void manageTeamInvite() {
    }
    */
    }
}
