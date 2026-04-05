package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Organizer extends User {
    //Lista degli hackaton gestiti.
    private ArrayList<Hackathon> organizedHackathons;


    public Organizer(String username, String password) {
           super(username, password);
           this.organizedHackathons = new ArrayList<Hackathon>();
    }
    //Creazione di un hackathon.
    public Hackathon createHackathon(String title, int maxTeamNumber, int minTeamNumber, int maxTeamSize, int minTeamSize, Location location) {
           Hackathon newHackathon = new Hackathon(title, maxTeamNumber, minTeamNumber, maxTeamSize, minTeamSize, this,  location);
                     organizedHackathons.add(newHackathon);
                     return newHackathon;
    }
    //Chiusura iscrizioni
    public void closeHackathonSubscription(Hackathon hackathon) {
                hackathon.setRegistrationStatus(false);
                hackathon.setStartDate(LocalDate.now());
    }
    //Chiusura dell' hackathon.
    public void endHackathon(Hackathon hackathon) {
                hackathon.end();
    }
    //Invito del giudice
    public Invite inviteJudge(Hackathon hackathon, String text, Judge judge){
                Invite invite = new Invite(hackathon, text, judge, this);
                return invite;
    }
    public void removeOrganizedHackathon(Hackathon hackathon) {
        organizedHackathons.remove(hackathon);
    }

    //metodi setter e getter
    public int getNumberOfOrganizedHackathons()
    {
        return this.organizedHackathons.size();
    }

    public ArrayList<Hackathon> getOrganizedHackathons()
    {
        return this.organizedHackathons;
    }
    public void addOrganizedHackathon(Hackathon hackathon)
    {
        this.organizedHackathons.add(hackathon);
    }




}

