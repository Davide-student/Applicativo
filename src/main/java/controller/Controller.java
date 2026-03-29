package controller;
import model.*;
import java.util.ArrayList;
import java.time.LocalDate;

public class Controller {
    private ArrayList<Hackathon> hackathonsList;
    private ArrayList<Team> teamsList;
    private ArrayList<User> membersList;
    private ArrayList<Update> updatesList;
    private ArrayList<Opinion> opinionsList;
    private ArrayList<Invite> invitesList;
    private ArrayList<Judge> judgesList;
    private ArrayList<Participant> participantsList;
    private ArrayList<Organizer> organizersList;
    private ArrayList<Location> locationsList;
    private ArrayList <Leader> leadersList;
    private User currentUser;   //Rappresenta l'utente che ha effettuato il login
    private String currentUserRole;    //Rappresenta il ruolo occupato dall'utente che ha effettuato il login, sulla piattaforma

    public Controller()
    {
        this.hackathonsList = new ArrayList<Hackathon>();
        this.teamsList = new ArrayList<Team>();
        this.membersList = new ArrayList<User>();
        this.updatesList = new ArrayList<Update>();
        this.opinionsList = new ArrayList<Opinion>();
        this.invitesList = new ArrayList<Invite>();
        this.judgesList = new ArrayList<Judge>();
        this.participantsList = new ArrayList<Participant>();
        this.organizersList = new ArrayList<Organizer>();
        this.locationsList = new ArrayList<Location>();
        this.leadersList = new ArrayList<Leader>();
        this.currentUser = null;
        this.currentUserRole = null;
        /*User user1 = new User("davideo4", "superlol");
        membersList.add(user1);
        Organizer organizer = new Organizer("davideo4", "superlol");
        organizersList.add(organizer);
        Location location = new Location("Via felicità", "Marianella", 20, 2);
        Hackathon hackathon = new Hackathon("Hackathon figo", 1, 1, 1, 1, organizer, location);
        organizer.addOrganizedHackathon(hackathon);
        hackathon.setStartDate(LocalDate.now());
        hackathonsList.add(hackathon);
        User user = new User("d", "d");
        membersList.add(user);
        Participant participant = new Participant("d", "d");
        participantsList.add(participant);
        Judge judge = new Judge("d", "d");
        judge.addJudgedHackathon(hackathon);
        judgesList.add(judge);*/
    }
    public boolean checkCredentials(String username, String password)   //Effettua un controllo per verificare l'esistenza delle credenziali inserite dall'utente a login
    {
        for(User user : membersList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {    //Controlla "mentre" le credenziali non sono riscontrate
                this.currentUser = user;
                if(currentUserRole == null)     //Se l'utente ha già effettuato il login almeno una volta, il suo ruolo non deve essere resettato
                {
                    this.currentUserRole = this.searchForUserInRoles(user);
                    System.out.println(this.currentUserRole);
                }
                return true;
            }
        }
            return false;
    }



    public boolean checkRegistrationAvailability(String username, String password){   //Controlla se esistono utenti con il nickname selezionato
        if(username.isEmpty() || password.isEmpty())    //L'utente non ha inserito credenziali di registrazione, non può registrarsi
        {
            return false;
        }
        for(User user : membersList) {
            if (user.getUsername().equals(username)) {
                return false;   //l'username è già usato, l'utente viene notificato
            }
        }
            //L'username non è già usato, l'utente viene registrato
                addMember(username, password);
                return true;
    }
    public void addMember(String username, String password)   //Un utente si è registrato, viene aggiunto alla piattaforma
    {
            this.membersList.add(new User(username, password));
            /*System.out.println("User " + username + " added to list");*/
        //Qui servirebbe chiamata all'interfaccia DAO per l'aggiunta dell'utente;

    }



    /*Codice gestione home partecipante*/

    public boolean checkParticipantTeam()
    {
        for(Participant participant : participantsList) {
            if(participant.getUsername().equals(currentUser.getUsername())) {   //Cerca il partecipante tra i partecipanti
                if(participant.getTeam() != null)   //Il partecipante fa parte di un team
                {
                    return true;
                }
            }
        }

        return false;
    }






    /*Codice gestione home partecipante*/










    /*Codice gestione home giudice*/
    public boolean isJudgeBusy(String username)
    {
        for(Judge judge : judgesList) {
            if(judge.getUsername().equals(username)) {
                if(!judge.getJudgedHackathons().isEmpty())  //Se il giudice si occupa di almeno un hackathon
                {
                    return true;
                }
            }
        }
        return false;
    }

    public void makeUserJudge()  //L'utente decide di segnarsi come giudice per gli hackathon
    {
        Judge judge = new Judge(currentUser.getUsername(), currentUser.getPassword());  //Il nuovo giudice viene aggiunto alla lista di giudici
        this.currentUserRole = "Judge";
    }
    public void makeJudgeUser() //Il giudice decide si segnarsi nuovamente come semplice utente
    {
        for(Judge judge: judgesList) {
            if(judge.getUsername().equals(currentUser.getUsername())) //Il giudice viene rimosso dalla lista giudici
            {
                judge.setInvitesList(null);  //Gli inviti presenti nella casella di posta del giudice vengono rimossi
                judgesList.remove(judge);
            }
        }
        this.currentUserRole = "User";
    }

    /*Codice gestione home giudice*/





    public String searchForUserInRoles(User user)   //Controllo se l'utente è presente come organizzatore, partecipante o giudice
    {
        for(Participant participant: participantsList) {  //
            if(user.getUsername().equals(participant.getUsername()))
            {
                return "Participant";   //L'utente è partecipante ad un hackathon
            }
        }
        for(Judge judge: judgesList)
        {
            if(user.getUsername().equals(judge.getUsername()))
            {
                return "Judge"; //L'utente è un giudice
            }
        }
        for(Organizer organizer: organizersList)
        {
            if(user.getUsername().equals(organizer.getUsername()))
            {
                return "Organizer"; //L'utente è un organizzatore
            }
        }
        for(Leader leader: leadersList)
        {
            if(user.getUsername().equals(leader.getUsername()))
            {
                return "Leader";
            }
        }
        return "User";  //L'utente è un comune user
    }

    public boolean subscribeUserToHackathon(String hackathonTitle)
    {

        for(Hackathon hackathon: hackathonsList) {
            if(hackathon.getTitle().equals(hackathonTitle)) {

                Participant participant = new Participant(currentUser.getUsername(), currentUser.getPassword());
                hackathon.addParticipant(participant);
                currentUserRole = "Participant";
                return true;
            }
        }
        return false;
    }


    public void logout()
    {
        this.currentUserRole = null;
        this.currentUser = null;
    }













    //Metodi getter e setter
    public Object[][] getOrganizedHackathonsTable(Object[][] table)
    {
        Organizer organizer = getCurrentOrganizer();
        int organizedHackathonsNumber = organizer.getNumberOfOrganizedHackathons();
        table = new Object[organizedHackathonsNumber][3];
        int rowIndex = 0;
        for(Hackathon hackathon: organizer.getOrganizedHackathons()) {
            table[rowIndex][0] = hackathon.getTitle();
            table[rowIndex][1] = hackathon.getStartDate();
            table[rowIndex][2] = hackathon;
        }

        return table;
    }

    public Object[][] getJudgedHackathonsTable(Object[][] table)   //Fornisce gli hackathon per cui il giudice sta lavorando
    {
        Judge judge = getCurrentJudge();
        int judgedHackathonsNumber = judge.getJudgedHackathonsNumber(); //Numero di hackathon gestiti dal giudice
        table = new Object[judgedHackathonsNumber][3];  //Ho bisogna di una tabella abbastanza grande da contenere tutti gli hackathon
        int rowIndex = 0;
        for(Hackathon hackathon: judge.getJudgedHackathons())
        {
            table[rowIndex][0] = hackathon.getTitle();
            table[rowIndex][1] = hackathon.getStartDate();
            table[rowIndex][2] = hackathon;
            rowIndex++;
        }


        return table;
    }

    public Organizer getCurrentOrganizer()
    {
        for(Organizer organizer: organizersList) {
            if (currentUser.getUsername().equals(organizer.getUsername())) {
                return organizer;
            }
        }
        return null;
    }

    public Judge getCurrentJudge()
    {
        for(Judge judge: judgesList) {
            if(currentUser.getUsername().equals(judge.getUsername())) {
                return judge;
            }
        }
        return null;
    }
    public String getCurrentUserRole()
    {
        return currentUserRole;
    }
    public String getCurrentUserUsername()
    {
        return currentUser.getUsername();
    }
    public User getCurrentUser()
    {
        return currentUser;
    }
    public Object[][] getUserTableData(Object [][] data)    //Questo metodo serve ad ottenere i dati da immettere nella JTable mostrata nella home
    {
        int dataSize = this.getNumberOfHackathonWithOpenSubscriptions();    //per inizializzare la matrice "data" devo conoscere il numero di hackathon con iscrizioni aperte
        data = new Object[dataSize][3];
        int rowIndex = 0;
        for(Hackathon hackathon: hackathonsList) {
            //Prelevo le informazioni riguardanti gli hackathon con iscrizioni aperte.
            if(hackathon.getRegistrationStatus()) { //Se le iscrizioni sono aperte, l'hackathon viene aggiunto
                data[rowIndex][0] = hackathon.getTitle();
                data[rowIndex][1] = hackathon.getStartDate();
                data[rowIndex][2] = hackathon;   //Hackathon a cui ci si potrà iscrivere tramite button
                rowIndex++;
            }
        }
        return data;
    }


    public int getNumberOfHackathonWithOpenSubscriptions()
    {
        int openSubCount = 0;
        for(Hackathon hackathon : hackathonsList) {
            if(hackathon.getRegistrationStatus())   //Controllo sullo stato delle registrazioni di hackathon
            {
                openSubCount++;
            }
        }
        return openSubCount;
    }
}

