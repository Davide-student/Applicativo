package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Hackathon {
    private int code;    //Codice identificativo dell hackathon
    private String title;
    private int maxTeamNumber;  //Numero massimo di team per l'inizio dell'evento
    private int minTeamNumber;  //Numero minimo di team per l'inizio dell'evento
    private int maxTeamSize;    //Numero massimo di partecipanti per un solo team
    private int minTeamSize;    //Numero minimo di partecipanti per un solo team
    private LocalDate registrationStartDate;
    private boolean registrationStatus;
    private boolean hackathonStatus;    //Se settato a false, l'hackathon è terminato.
    private LocalDate startDate;
    private LocalDate endDate;   //Viene valorizzata con la chiamata del metodo "end". Assume la data corrente alla chiamata
    private String problemDescription;    //Valorizzato da un giudice
    private ArrayList<Judge> judgesList;    //Lista dei giudici che si occupano dell'hackathon
    private Organizer eventOrganizer;    //Indica l'organizzatore dell'hackathon
    private Location location; //Sede fisica in cui si tiene l'hackathon
    private ArrayList<Team> scores;  //Contiene i nomi dei team ed i relativi punteggi, ordinati secondo i punteggi (Ossia le chiavi)
    private ArrayList<Participant> participantsList;


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
        this.participantsList = new ArrayList<Participant>();
        this.registrationStatus = true;    //Quando l'hackathon viene creato, le registrazioni sono aperte
        this.scores = new ArrayList<Team>();
    }

    //Metodi setter e getter necessari

        public Organizer getEventOrganizer() {
        return this.eventOrganizer;
        }

        public void setStartDate(LocalDate startDate) {
            this.startDate = startDate;
        }

        public void setEndDate(LocalDate endDate) {
            this.endDate = endDate;
        }

        public void setDescription(String problemDescription) {
            this.problemDescription = problemDescription;
        }

        public void setRegistrationStatus(boolean status) {
            this.registrationStatus = status;
        }
        public boolean getRegistrationStatus() {
            return this.registrationStatus;
        }
        public String getTitle()
        {
            return this.title;
        }
        public LocalDate getStartDate()
        {
            return this.startDate;
        }
    /*public TreeMap<Integer, String> getScores() //restituisce la classifica di fine hackathon.
    {
        return this.scores;
    }*/

    //Metodi della classe

        public void addJudge(Judge judge) {
            this.judgesList.add(judge);
        }

        //Questo metodo viene richiamato dal metodo in organizer "endHackathon" quando questo termina l'Hackathon.
        public void end(ArrayList<Team> teams){ //Alla fine dell'hackathon, viene definita la classifica dei team
            scores.addAll(teams);   //I team partecipanti all'hackathon vengono inseriti nella lista di classifica
            Collections.sort(scores, new teamsComparator());    //La lista "Scores" viene ordinata rispetto al "finalProjectRating" di team. Così è creata la classifica finale


        }
        public void addParticipant(Participant participant){
            this.participantsList.add(participant);
        }
}
