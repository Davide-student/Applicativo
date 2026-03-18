package model;

public class Judge extends User
{
    private ArrayList<Hackathon> judgedHackathons;






    //Costruttore
    public Judge(String username, String password)
    {
        super(username, password);  //Richiamo il costruttore di user
        this.judgedHackathons = new ArrayLyst<Hackathon>();
    }

    //Metodi del domain model
    public boolean acceptInvite(Hackathon hackathon)
    {
        judgeHackathons.add(hackathon);
        return true;    //Il giudice ha accettato l'invito, l'organizzatore viene notificato
    }
    public boolean refuseInvite()
    {
        return false;   //Il giudice ha rifiutato l'invito, l'organizzatore viene notificato
    }
    
    public void createOpinion(Update update, String description)
    {
            Opinion opinion = new Opinion(this.username, description); //Viene create un'opinione (L'username passato corrisponde username)
                                                                       //"username" del giudice che crea l'opinione.
            update.addOpinion(opinion);  //L'opinione viene assegnata all'update
    }
    public void publishRating(Team team, int rating)
    {
        team.ratings.add(rating);   //Viene aggiunto il voto di "istanza judge" alla lista dei voti associati al team.
    }
    public void publishProblemDescription(Hackathon hackathon, File description)
    {
        hackathon.setDescription(description);
    }
    
}