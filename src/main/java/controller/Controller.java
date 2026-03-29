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
        Organizer organizer = new Organizer("davideo4", "superlol");
        Location location = new Location("Via felicità", "Marianella", 20, 2);
        Hackathon hackathon = new Hackathon("Hackathon figo", 1, 1, 1, 1, organizer, location);
        hackathon.setStartDate(LocalDate.now());
        hackathonsList.add(hackathon);
    }
    public boolean checkCredentials(String username, String password)   //Effettua un controllo per verificare l'esistenza delle credenziali inserite dall'utente a login
    {
        /*
        for(User user : usersList)
        {
            System.out.println(user.getUsername());
        }*/
        for(User user : membersList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {    //Controlla "mentre" le credenziali non sono riscontrate
                this.currentUser = user;
                if(currentUserRole == null)     //Se l'utente ha già effettuato il login almeno una volta, il suo ruolo non deve essere resettato
                {
                    this.currentUserRole = this.searchForUserInRoles(user);
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

    //Metodi getter e setter
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
        int rowIndex = 0;
        data = new Object[dataSize][3];
        for(Hackathon hackathon: hackathonsList) {
            //Prelevo le informazioni riguardanti gli hackathon con iscrizioni aperte.
            data[rowIndex][0] = hackathon.getTitle();
            data[rowIndex][1] = hackathon.getStartDate().toString();
            data[rowIndex][2] = hackathon;   //Hackathon a cui ci si potrà iscrivere tramite button
            rowIndex++;
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

