package model;

import java.util.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Organizer extends User {
    //Lista degli hackaton gestiti.
    private List<Hackathon> managedHackathons;


    public Organizer(String username, String password) {
           super(username, password);
           this.managedHackathons = new ArrayList<Hackathon>();
    }
    //Creazione di un hackathon.
    public Hackathon createHackathon(String title, int maxTeamNumber, int minTeamNumber, int maxTeamSize, int minTeamSize, Location location) {
           Hackathon newHackathon = new Hackathon(title, maxTeamNumber, minTeamNumber, maxTeamSize, minTeamSize, location);
                     managedHackathons.add(newHackathon);
                     return newHackathon;
    }
    //Apertura iscrizioni all'hackathon.
    public void openHackathonSubscription(Hackathon hackathon) {
                hackathon.setRegistrationStatus(true);
    }
    //Chiusura iscrizioni
    public void closeHackathonSubscription(Hackathon hackathon) {
                hackathon.setRegistrationStatus(false);
    }
    // Avvio di un hackathon.
    public void startHackathon(Hackathon hackathon) {
                hackathon.setStartDate(LocalDate.now());
    }

    //Chiusura dell' hackathon.
    public void endHackathon(Hackathon hackathon, ArrayList(Team)) {
                hackathon.end(Team);
    }
    //Invito del giudice
    public void inviteJudge(Hackathon hackathon, Judge judge) {
                hackathon.addJudge(judge);
    }








}

