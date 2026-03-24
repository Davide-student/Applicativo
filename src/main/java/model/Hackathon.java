package model;

import java.time.LocalDate;
import java.io.File;
import java.util.TreeMap;
import java.util.ArrayList;

public class Hackathon {
    private int code;    //Codice identificativo dell hackathon
    private String title;
    private int maxTeamNumber;  //Numero massimo di team per l'inizio dell'evento
    private int minTeamNumber;  //Numero minimo di team per l'inizio dell'evento
    private int maxTeamSize;    //Numero massimo di partecipanti per un solo team
    private int minTeamSize;    //Numero minimo di partecipanti per un solo team
    private LocalDate registrationStartDate;
    private boolean registrationStatus;
    private LocalDate startDate;
    private LocalDate endDate;   //Viene valorizzata con la chiamata del metodo "end". Assume la data corrente alla chiamata
    private File problemDescription;    //Valorizzato da un giudice
    private ArrayList<Judge> judgesList;    //Lista dei giudici che si occupano dell'hackathon
    private Organizer eventOrganizer;    //Indica l'organizzatore dell'hackathon
    private Location location; //Sede fisica in cui si tiene l'hackathon
    private TreeMap<Integer, String> scores;  //Contiene i nomi dei team ed i relativi punteggi, ordinati secondo i punteggi (Ossia le chiavi)


    public Hackathon(String title, int maxTeamNumber, int minTeamNumber, int maxTeamSize, int minTeamSize, Organizer eventOrganizer, Location location) {
        this.title = title;
        this.maxTeamNumber = maxTeamNumber;
        this.minTeamNumber = minTeamNumber;
        this.maxTeamSize = maxTeamSize;
        this.minTeamSize = minTeamSize;
        this.registrationStartDate = null;
        this.eventOrganizer = eventOrganizer;
        this.location = location;
        this.startDate = null;
        this.endDate = null;
        this.judgesList = new ArrayList<Judge>();
        this.registrationStatus = false;    //Quando l'hackathon viene creato, le registrazioni sono chiuse
        this.scores = new TreeMap<Integer, String>();
    }

    //Metodi setter e getter necessari

        public void setStartDate(LocalDate startDate) {
            this.startDate = startDate;
        }

        public void setEndDate(LocalDate endDate) {
            this.endDate = endDate;
        }

        public void setDescription(File problemDescription) {
            this.problemDescription = problemDescription;
        }

        public void setRegistrationStatus(boolean status) {
            this.registrationStatus = status;
        }

    /*public TreeMap<Integer, String> getScores() //restituisce la classifica di fine hackathon.
    {
        return this.scores;
    }*/

    //Metodi della classe

        public void addJudge(Judge j) {
            this.judgesList.add(j);
        }

        public void addScoreForTeam(Team team) {
            this.scores.put(team.getFinalProjectRating(), team.getName());
            //Il team viene aggiunto alla lista di punteggi, quando poi il giudice
            //termina l'hackathon, questa lista
            //viene ordinata rispetto ai punteggi assegnati
        }

        //Questo metodo viene richiamato dal metodo in organizer "endHackathon" quando questo termina l'Hackathon.
        public void end(ArrayList<Team> teams) //Alla fine dell'hackathon, viene definita la classifica dei team
        {
            for (Team t : teams) {
                this.addScoreForTeam(t);    //Aggiunge il team alla classifica (TreeMap). "this" indica l'istanza                                            //corrente di classe(L'hackathon)
            }

            this.endDate = LocalDate.now();

        }
}
