package model;

public class Invite
{
    private Team team;
    private Hackathon hackathon;
    private String text;    //Messaggio incluso con l'invito inviato
    private User receiver;   //Reference al destinatario dell'invito
    private User sender;  //Reference al mittente dell'invito
    


    //Signatures differenti per i diversi inviti
    
    //Costruttore per invite a "Participant
    public Invite(Team team, String text, Participant receiver, Leader sender)  //Costruttore usato da istanze di "Leader"
    {
        this.team = team;
        this.text = text;
        this.receiver = receiver;
        this.sender = sender;
    }
    //Costruttore per invite a "Judge"
    public Invite(Hackathon hackathon, String text, Judge receiver, Organizer sender) //Costruttore usato da instanze di "Organizer"
    {
        this.hackathon = hackathon;
        this.text = text;
        this.receiver = receiver;
        this.sender = sender;
    }
    public Team getTeam(){
        return this.team;
    }

    //Metodi getter e setter
    public Hackathon getHackathon(){
        return this.hackathon;
    }
}