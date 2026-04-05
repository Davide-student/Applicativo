package controller;
import java.lang.Integer.*;
import model.*;
import java.util.ArrayList;
import java.time.LocalDate;

public class Controller {
    private ArrayList<Hackathon> hackathonsList;
    private ArrayList<User> membersList;
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
        this.membersList = new ArrayList<User>();
        this.judgesList = new ArrayList<Judge>();
        this.participantsList = new ArrayList<Participant>();
        this.organizersList = new ArrayList<Organizer>();
        this.locationsList = new ArrayList<Location>();
        this.leadersList = new ArrayList<Leader>();
        this.currentUser = null;
        this.currentUserRole = null;
        //Hard coding degli organizzatori
        Organizer organizer1 = new Organizer("davide", "1");
        Organizer organizer2 = new Organizer("francesco", "2");
        Organizer organizer3 = new Organizer("carlo", "3");
        organizersList.add(organizer1);
        organizersList.add(organizer2);
        organizersList.add(organizer3);
        User user1 = new User("davide", "1");
        User user2 = new User("francesco", "2");
        User user3 = new User("carlo", "3");
        membersList.add(user1);
        membersList.add(user2);
        membersList.add(user3);
    }
    public boolean checkCredentials(String username, String password)   //Effettua un controllo per verificare l'esistenza delle credenziali inserite dall'utente a login
    {
        for(User user : membersList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {    //Controlla "mentre" le credenziali non sono riscontrate
                this.currentUser = user;
                if(currentUserRole == null)     //Se l'utente ha già effettuato il login almeno una volta, il suo ruolo non deve essere resettato
                {
                    this.currentUserRole = this.searchForUserInRoles(user);
                    //System.out.println(this.currentUserRole);
                }
                return true;
            }
        }
            return false;
    }







    /*Codice gestione leader*/
    public boolean createTeam(String teamTitle)
    {
        Team team = null;
        Participant participant  = getCurrentParticipant();
        Hackathon hackathon = participant.getHackathon();
        if(hackathon.getTeams().size() == hackathon.getMaxTeamNumber())   //Numero di team massimo raggiunto
        {
            return false;
        }
        for(Hackathon h : hackathonsList){
            if(!h.getTeams().isEmpty()) {
                for (Team t : h.getTeams()) {
                    if (t.getName().equals(teamTitle)) {
                        return false;
                    }
                }
            }
        }
        team = participant.createHackathonTeam(teamTitle, participant.getHackathon());
        participant.setTeam(team);
        Leader leader = new Leader(participant.getUsername(), participant.getPassword(), team, participant.getHackathon());
        currentUserRole = "Leader";
        leadersList.add(leader);
        return true;
    }


    public boolean addUpdate(String title, String description, String version)
    {
        Leader leader = getCurrentLeader();
        Team team = leader.getTeam();
        for(Update u : team.getUpdates()) {
            if(u.getTitle().equals(title)) {//Esiste già un update con lo stesso titolo
               return false;
            }
        }
        leader.publishTeamUpdate(title, description, version);


        return true;
    }

    public boolean inviteParticipant(String username, String message)
    {
        Leader leader = getCurrentLeader();
        Hackathon hackathon = leader.getHackathon();
        for(Participant participant : hackathon.getParticipantsList())
        {
            if(participant.getUsername().equals(username))
            {
                leader.createInvite(message, participant);
                return true;
            }
        }

        return false;
    }
    /*Codice gestione leader*/

    /*Codice gestione organizzatore*/
    public boolean createHackathon(String title, String maxNumberTeam, String minNumberTeam, String maxTeamSize, String minTeamSize, String locationAddress, String street, String CAP, String door )
    {
        for(Hackathon h : hackathonsList) { //Hackathon con questo titolo già esistente
            if(h.getTitle().equals(title)) {
                return false;
            }
        }
        int maxNT;
        int minNT;
        int maxTS;
        int minTS;
        int c;  //CAP
        int d;  //door
        try{   //Il valore inserito potrebbe non essere un numero intero valido
            maxNT = Integer.parseInt(maxNumberTeam);
            maxTS = Integer.parseInt(maxTeamSize);
            minNT = Integer.parseInt(minNumberTeam);
            minTS = Integer.parseInt(minTeamSize);
            c = Integer.parseInt(CAP);
            d = Integer.parseInt(door);
            Location location = new Location(locationAddress, street, c, d);
            locationsList.add(location);    //Mancato controllo sulla location (Una location non può ospitare più hackathon nello stesso momento)
            Organizer organizer = getCurrentOrganizer();
            Hackathon hackathon = organizer.createHackathon(title, maxNT, minNT, maxTS, minTS, location);
            hackathonsList.add(hackathon);
       } catch (NumberFormatException e) {
           //System.out.println(e.getMessage());
            System.exit(-1);
        }

        return true;
    }

    public void endHackathon(String hackathonTitle)
    {
        Organizer hackathonOrganizer = getCurrentOrganizer();

        for(Hackathon h : hackathonOrganizer.getOrganizedHackathons()) {
            if(h.getTitle().equals(hackathonTitle)) {
                hackathonOrganizer.removeOrganizedHackathon(h);
                for(Judge j : h.getJudgesList())
                {
                    j.removeJudgedHackathon(h);
                }
                h.removeAllJudges();
                participantsList.removeAll(h.getParticipantsList());    //Rimuove tutti i partecipanti
                h.removeAllParticipants();
                h.end();
                hackathonsList.remove(h);
                break;
            }
        }
    }

    public void closeHackathonRegister(String hackathonTitle)
    {
        Organizer hackathonOrganizer = null;
        for (Hackathon h : hackathonsList) {
            if(h.getTitle().equals(hackathonTitle)) {
                hackathonOrganizer = h.getEventOrganizer();
                hackathonOrganizer.closeHackathonSubscription(h);
                ArrayList <Participant> UnsubscribedParticipants = h.removeUnsubscribedParticipants();
                /*for (Participant p : UnsubscribedParticipants) {
                    System.out.println("rimossi" + p.getUsername());
                }*/
                if(UnsubscribedParticipants.size() > 0) {
                    participantsList.removeAll(UnsubscribedParticipants);
                }
                break;
            }
        }
    }


    public void manageJudgeInvite(String sender, String receiver, String description, String hackathonTitle)   //L'organizzatore invita un giudice ad un hackathon
    {
        Organizer organizer = getCurrentOrganizer();
        Hackathon hackathon = null;
        for(Hackathon h : hackathonsList) {
            if(h.getTitle().equals(hackathonTitle)) {
                hackathon = h;
            }
        }
        for(Judge j : judgesList) {
            if(receiver.equals(j.getUsername()))
            {
                Invite invite = organizer.inviteJudge(hackathon, description, j);
                j.addInvite(invite);
                break;
            }
        }
    }


    public boolean checkOrganizerHackathonTitle(String hackathonTitle)
    {
        Organizer organizer = getCurrentOrganizer();
        ArrayList<Hackathon> organizedHackathon = organizer.getOrganizedHackathons();
        for(Hackathon h : organizedHackathon) {
            if(h.getTitle().equals(hackathonTitle)) {
                return true;
            }
        }
        return false;
    }
    /*Codice gestione organizzatore*/


    public boolean searchForJudge(String username)
    {
        for(Judge j : judgesList) {
            if(j.getUsername().equals(username)) {
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

    public boolean acceptParticipantInvite(String teamTitle) {
        Participant participant = getCurrentParticipant();
        Hackathon hackathon = participant.getHackathon();
        for(Team t: hackathon.getTeams()) {
            if(t.getName().equals(teamTitle)) { //Il team esiste per l'hackathon corrente
                for(Invite inv: participant.getInvitesList())
                {
                    if(inv.getTeam().equals(t)) {   //Il partecipante ha effettivamente ricevuto un invito dal team specificato
                        participant.getInvitesList().remove(inv);
                        participant.setTeam(t);
                        t.addMember(participant);
                        return true;
                    }
                }
            }
        }

        return false;   //Il team specificato non ha mandato inviti o non esiste
    }

    public boolean refuseParticipantInvite(String teamTitle)
    {
        Participant participant = getCurrentParticipant();
        Hackathon hackathon = participant.getHackathon();
        for(Team t: hackathon.getTeams()) {
            if(t.getName().equals(teamTitle)) {
                for (Invite inv: participant.getInvitesList()) {
                    if(inv.getTeam().equals(t)) {   //L'invito al partecipante esiste e viene rimosso
                        participant.getInvitesList().remove(inv);
                        return true;
                    }
                }
            }
        }
        return false;   //L'invito da parte del team specificato non esiste
    }

    public void leaveHackathon()    //Solo il leader deve uscire manualmente dell'hackathon
    {
        Leader leader = getCurrentLeader();
        leadersList.remove(leader);
    }





    /*Codice gestione home partecipante*/










    /*Codice gestione home giudice*/
    public boolean isJudgeBusy(String username) //Serve a capire se il giudice può cambiare ruolo(tornare ad essere un utente standard)
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
        judgesList.add(judge);
        this.currentUserRole = "Judge";
    }
    public void makeJudgeUser() //Il giudice decide si segnarsi nuovamente come semplice utente
    {
        for(Judge judge: judgesList) {
            if(judge.getUsername().equals(currentUser.getUsername())) //Il giudice viene rimosso dalla lista giudici
            {
                judge.setInvitesList(null);  //Gli inviti presenti nella casella di posta del giudice vengono rimossi
                judgesList.remove(judge);
                break;
            }
        }
        this.currentUserRole = "User";
    }

    public boolean publishOpinion(String hackathonTitle, String updateTitle, String teamTitle, String description)    //Il giudice pubblica un'opinione per un update di un team.
    {
        Judge judge = getCurrentJudge();
        for(Hackathon hackathon: judge.getJudgedHackathons())
        {
            if(hackathon.getTitle().equals(hackathonTitle)) {   //check sull'hackathon
                for(Team team: hackathon.getTeams()) {  //check sul team
                    if(team.getName().equals(teamTitle)) {

                        for(Update update: team.getUpdates())   //check sull'update
                        {

                            if(update.getTitle().equals(updateTitle)) {

                                judge.publishOpinion(update, description);
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }
    public boolean giveVoteToTeam(String hackathonTitle, String teamTitle, String vote)   //Nota: Nessuno vieta al giudice di mettere più voti, servirebbe un controllo su quello
    {
        int score = 0;
        try {
            score = Integer.parseInt(vote);
        } catch (NumberFormatException e) {
            System.exit(20);
        }
        if (score < 0 || score > 10) {
            return false;
        }
        Judge judge = getCurrentJudge();
        for (Hackathon hackathon : judge.getJudgedHackathons())
        {
            if(hackathonTitle.equals(hackathon.getTitle())) {
                for(Team team : hackathon.getTeams()) {
                    if(team.getName().equals(teamTitle)) {
                        judge.publishRating(team, score);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean acceptJudgeInvite(String hackathonTitle) {
        for(Hackathon hackathon : hackathonsList) {
            if(hackathon.getTitle().equals(hackathonTitle)) {
                hackathon.addJudge(getCurrentJudge());
                getCurrentJudge().addJudgedHackathon(hackathon);

                for(Invite inv : getCurrentJudge().getInvitesList()) {
                    if(inv.getHackathon().getTitle().equals(hackathonTitle)) {
                        getCurrentJudge().removeInvite(inv);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean refuseJudgeInvite(String hackathonTitle)
    {
        for(Hackathon hackathon : hackathonsList) {
            if(hackathon.getTitle().equals(hackathonTitle)) {   //hackathon esistente
                for(Invite inv : getCurrentParticipant().getInvitesList()) {
                    if(inv.getHackathon().getTitle().equals(hackathonTitle)) {  //Invito trovato, viene eliminato
                        getCurrentJudge().removeInvite(inv);
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /*Codice gestione home giudice*/

    public boolean publishProblem(String hackathonTitle, String problemDescription)    //Pubblica descrizione del problema da risolvere per l'hackathon specificato
    {
        Judge judge = getCurrentJudge();
        for(Hackathon h : judge.getJudgedHackathons()) {
            if(h.getTitle().equals(hackathonTitle)) {
                if(h.getProblemDescription().equals(""))
                {
                    h.setProblemDescription(problemDescription);
                    return true;
                }
            }
        }
        return false;
    }





    public String searchForUserInRoles(User user)   //Controllo se l'utente è presente come organizzatore, partecipante o giudice
    {
        for(Leader leader: leadersList)
        {
            if(user.getUsername().equals(leader.getUsername()))
            {
                return "Leader";
            }
        }
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
        for(Hackathon h: hackathonsList) {
            if(h.getTitle().equals(hackathonTitle)) {
                if(!(h.getParticipantsList().size() == h.getMaxParticipantsNumber()))
                {
                    Participant participant = currentUser.subscribeToHackathon(h);
                    participantsList.add(participant);
                    currentUserRole = "Participant";
                    return true;
                }
            }
        }
        return false;
    }
    public boolean checkHackathonStatus()   //Controlla se l'hackathon è terminato o meno
    {
        if(currentUserRole.equals("Participant")) {
            Participant participant = getCurrentParticipant();
            for (Hackathon h : hackathonsList) {
                if (h.getTitle().equals(participant.getHackathon().getTitle())) {
                    return h.getHackathonStatus();
                }
            }
        }else{  //Il partecipante è un leader
            Leader leader = getCurrentLeader();
            for(Hackathon h : hackathonsList) {
                if(h.getTitle().equals(leader.getHackathon().getTitle())) {
                    return h.getHackathonStatus();
                }
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

    public String[][] getProblemDescription()
    {
        Hackathon hackathon;
        String[][] problemDescription = new String[1][1];
        Leader leader = getCurrentLeader();
        hackathon = leader.getHackathon();
        problemDescription[0][0] = hackathon.getProblemDescription();

        return problemDescription;
    }
    public String[][] getParticipantInvites()
    {
        String[][] invitesData = null;

        ArrayList<Invite> invites = getCurrentParticipant().getInvitesList();
        if(invites.size()> 0) {
            invitesData = new String[invites.size()][3];
            int rowIndex = 0;
            for (Invite invite : invites) {
                invitesData[rowIndex][0] = invite.getTeam().getName();
                invitesData[rowIndex][1] = invite.getText();
                invitesData[rowIndex][2] = invite.getSender().getUsername();
                rowIndex++;
            }
        }

        return invitesData;
    }
    public String[][] getJudgeInvites()
    {
        String[][] invitesData = null;
        Judge judge = getCurrentJudge();
        ArrayList<Invite> invites = judge.getInvitesList();
        if(invites.size() > 0) {
            invitesData = new String[invites.size()][3];
            int rowIndex = 0;
            for (Invite invite : invites) {
                invitesData[rowIndex][0] = invite.getHackathon().getTitle();
                invitesData[rowIndex][1] = invite.getText();
                invitesData[rowIndex][2] = invite.getSender().getUsername();
                rowIndex++;
            }
        }
        return invitesData;
    }


    public String[][] getTeamsAndUpdates(String hackathonTitle)
    {
        String[][] teamsUpdatesInfo = null;
        for(Hackathon hackathon: hackathonsList) {
            if(hackathon.getTitle().equals(hackathonTitle)) {
                if(hackathon.getTeams().size() > 0)
                {
                    teamsUpdatesInfo = new String[hackathon.getTeams().size()][3];
                    for(Team team: hackathon.getTeams()) {
                        if(team.getUpdates().size() > 0) {
                            int rowIndex = 0;
                            teamsUpdatesInfo[rowIndex][0] = team.getName();
                            teamsUpdatesInfo[rowIndex][1] = team.getLatestUpdate().getDescription();
                            teamsUpdatesInfo[rowIndex][2] = team.getLatestUpdate().getTitle();
                            rowIndex++;
                        }

                    }
                }
            }
        }
        return teamsUpdatesInfo;

    }
    public String[][] getJudges()
    {
        int judgesCount = judgesList.size();
        String[][] judges = new String[judgesCount][1];
        int rowIndex = 0;
        for(Judge judge: judgesList) {
            judges[rowIndex][0] = judge.getUsername();
            rowIndex++;
        }

        return judges;
    }
    public String[][] getHackathonResults()
    {
        Leader leader = getCurrentLeader();
        String[][] hackathonResults = null;
        Hackathon hackathon = leader.getHackathon();
        hackathonResults = new String[hackathon.getScores().size()][3];
        int rowIndex = 0;
        for(Team team: hackathon.getScores()) {
            hackathonResults[rowIndex][0] = team.getName();
            hackathonResults[rowIndex][1] = Integer.toString(team.getFinalProjectRating());
            rowIndex++;
        }
        return hackathonResults;

    }
    public Leader getCurrentLeader()
    {
        for(Leader leader : leadersList) {
            if(leader.getUsername().equals(currentUser.getUsername()))
            {
                return leader;
            }
        }
        return null;
    }
    public Object[][] getTeamInfoTable(Object[][] table) //Usato da un partecipante
    {
        Participant participant = getCurrentParticipant();
        Team participantTeam = participant.getTeam();
        table = new Object[1][3];
        table[0][0] = participantTeam.getName();
        table[0][1] = getTeamLeader().getUsername();
        table[0][2] = participantTeam.getMembersNumber();

        return table;
    }
    public Leader getTeamLeader()
    {
        Participant participant = getCurrentParticipant();
        Team team = participant.getTeam();
        for(Leader leader : leadersList) {
            if(leader.getTeam().equals(team));
            {
                return leader;
            }
        }
        return null;
    }
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

    public Participant getCurrentParticipant()
    {
        for(Participant participant : participantsList) {
            if(participant.getUsername().equals(currentUser.getUsername()))
            {
                return participant;
            }
        }
        return null;
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

    public String[][] getLatestUpdateOpinions() //Usato da leader
    {
        Leader leader = getCurrentLeader();
        if(leader.getTeam().getUpdates().size() > 0)
        {
            Update latestUpdate = leader.getTeam().getLatestUpdate();
            String [][] updateOpinions = new String[latestUpdate.getOpinions().size()][2];
            int rowIndex = 0;
            for(Opinion opinions : latestUpdate.getOpinions())
            {
                updateOpinions[rowIndex][0] = opinions.getDescription();
                updateOpinions[rowIndex][1] = opinions.getJudge().getUsername();
                rowIndex++;
            }
            return updateOpinions;
        }
        return null;    //Non ci sono opinioni da mostrare
    }
}

