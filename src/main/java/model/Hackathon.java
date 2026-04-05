package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Hackathon {
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
    private ArrayList<Team> teamsList;

    public Hackathon(String title, int maxTeamNumber, int minTeamNumber, int maxTeamSize, int minTeamSize, Organizer eventOrganizer, Location location) {
        this.title = title;
        this.maxTeamNumber = maxTeamNumber;
        this.minTeamNumber = minTeamNumber;
        this.maxTeamSize = maxTeamSize;
        this.minTeamSize = minTeamSize;
        this.registrationStartDate = LocalDate.now();
        this.eventOrganizer = eventOrganizer;
        this.hackathonStatus = true;
        this.location = location;
        this.problemDescription = "";
        this.startDate = null;
        this.endDate = null;
        this.judgesList = new ArrayList<Judge>();
        this.teamsList = new ArrayList<Team>();
        this.registrationStatus = true;    //Quando l'hackathon viene creato, le registrazioni sono aperte
        this.scores = new ArrayList<Team>();
        this.participantsList = new ArrayList<>();
    }

    //Metodi setter e getter necessari
    public ArrayList<Judge> getJudgesList()
    {
        return this.judgesList;
    }
    public int getMaxParticipantsNumber()   //Restituisce il numero massimo di partecipanti possibili per l'hackathon
    {
        return maxTeamNumber * maxTeamSize;
    }
    public int getMaxTeamNumber() {
        return this.maxTeamNumber;
    }
    public ArrayList<Participant> getParticipantsList() {
        return this.participantsList;
    }
    public void addTeam(Team team){
        this.teamsList.add(team);
    }
    public boolean getHackathonStatus() {
        return this.hackathonStatus;
    }
    public ArrayList<Team> getScores() {
        return this.scores;
    }
    public Organizer getEventOrganizer() {
        return this.eventOrganizer;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setRegistrationStatus(boolean status) {
        this.registrationStatus = status;
    }
    public boolean getRegistrationStatus() {
        return this.registrationStatus;
    }
    public ArrayList<Team> getTeams() {
        return this.teamsList;
    }
    public String getTitle()
    {
        return this.title;
    }
    public LocalDate getStartDate()
    {
        return this.startDate;
    }
    public String getProblemDescription()
    {
        return this.problemDescription;
    }
    public void setProblemDescription(String problemDescription)
    {
        this.problemDescription = problemDescription;
    }
    /*public TreeMap<Integer, String> getScores() //restituisce la classifica di fine hackathon.
    {
        return this.scores;
    }*/

    //Metodi della classe

    public void removeAllJudges()
    {
        this.judgesList.clear();
    }
    public void removeAllParticipants()
    {
        this.participantsList.clear();
    }
    public void addJudge(Judge judge) {
        this.judgesList.add(judge);
    }

    //Questo metodo viene richiamato dal metodo in organizer "endHackathon" quando questo termina l'Hackathon.
    public void end(){ //Alla fine dell'hackathon, viene definita la classifica dei team
        for(Team team : teamsList){ //Calcolo i punteggi finali dei team
            team.computeFinalProjectRating();
        }
        scores.addAll(teamsList);   //I team partecipanti all'hackathon vengono inseriti nella lista di classifica
        Collections.sort(scores, new teamsComparator());    //La lista "Scores" viene ordinata rispetto al "finalProjectRating" di team. Così è creata la classifica finale
        //Restano i riferimenti ai singoli partecipanti, servono a visionare la classifica
        participantsList.clear();
        teamsList.clear();
        this.hackathonStatus = false;
    }
    public void addParticipant(Participant participant){
        this.participantsList.add(participant);
        if(participantsList.size() == getMaxParticipantsNumber())
        {
            setRegistrationStatus(false);
        }
    }
    public ArrayList<Participant> removeUnsubscribedParticipants()
        {
            ArrayList <Participant> unsubscribedParticipants = new ArrayList<Participant>();

            for (Participant participant: participantsList)
            {
                if(participant.getTeam() == null)
                {
                    unsubscribedParticipants.add(participant);
                }
            }
            participantsList.removeAll(unsubscribedParticipants);   //Rimuove i partecipanti non iscritti dalla lista dei partecipanti
            return unsubscribedParticipants;
        }
}
