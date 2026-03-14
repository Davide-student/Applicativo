package model;

import java.util.LocalDate;
import java.io.File;
import java.util.TreeMap;
import java.util.ArrayList;

public class Hackathon
{
	private int code;	//Codice identificativo dell hackathon
	private String title[30];
	private int maxTeamNumber;  //Numero massimo di team per l'inizio dell'evento
	private int minTeamNumber;  //Numero minimo di team per l'inizio dell'evento
    private int maxTeamSize;    //Numero massimo di partecipanti per un solo team
    private int minTeamSize;    //Numero minimo di partecipanti per un solo team
	private Date registrationStartDate;
	private Date startDate;
	private Date endDate;
	private File problemDescription;	//Valorizzato da un giudice
	private ArrayList<Judge> judgesList;	//Lista dei giudici che si occupano dell'hackathon
	private Organizer eventOrganizer;	//Indica l'organizzatore dell'hackathon
	private Location location; //Sede fisica in cui si tiene l'hackathon
    private TreeMap<Integer, String> scores;  //Contiene i nomi dei team ed i relativi punteggi, ordinati secondo i punteggi (Ossia le chiavi)
}

public Hackathon(String title, int maxTeamNumber, int minTeamNumber, int maxTeamSize, int minTeamSize, Date registrationStartDate, Organizer eventOrganizer, Location location)
{
        this.title = title;
        this.maxTeamNumber = maxTeamNumber;
        this.minTeamNumber = minTeamNumber;
        this.maxTeamSize = maxTeamSize;
        this.minTeamSize = minTeamSize:
        this.registrationDate = registrationDate;
        this.eventOrganizer = eventOrganizer;
        this.location = location;
        scores = new TreeMap<Integer, String>();
}

//Metodi setter e getter necessari

public void setStartDate(Date startDate)
{
    this.startDate = startDate;
}

public void setEndDate(Date endDate)
{
    this.endDate = endDate;
}

public void setDescription(File problemDescription)
{
    this.File = problemDescription;
}
//Metodi della classe

public void addJudge(Judge j)
{
    this.judgeList.add(j);
}

public void addScoreForTeam(Team team)
{
    this.scores.put(team.score, team.title);  
                                    //Il team viene aggiunto alla lista di punteggi, quando poi il giudice 
                                    //termina l'hackathon, questa lista
                                    //viene ordinata rispetto ai punteggi assegnati    
}

public void endHackathon(ArrayList<Team> teams) //Alla fine dell'hackathon, viene definita la classifica dei team
{
    for(Team t: teams)
    {
        this.addScoreForTeam(t);    //Aggiunge il team alla classifica (TreeMap). "this" indica l'istanza corrente di class           
                                    //(L'hackathon)
    }
}




