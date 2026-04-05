package GUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Home {
    public JFrame homeFrame;
    private JPanel homePanel;
    private JLabel usernameHomeLabel;
    private JLabel roleHomeLabel;
    private JLabel loggedAsHomeLabel;
    private JLabel staticRoleHomeLabel;
    private JPanel titlePanel;
    private JLabel adaptiveHomeTableTitleLabel;
    private JTable homeTable;
    private JScrollPane homeTableScrollPanel;
    private JTextField hackathonTitleTextField;
    private JButton hackathonActionButton;
    private JButton changeJudgeStatusButton;
    private JLabel hackathonActionButtonLabel;
    private JButton createButton;
    private JButton logoutButton;
    private JLabel invitesTableLabel;
    private JScrollPane invitesTableScrollpane;
    private JTable invitesTable;
    private JTextField inviteTextField;
    private JLabel inviteFormLabel;
    private JButton acceptInviteButton;
    private JButton refuseInviteButton;
    private JPanel leaderPanel;
    private JTextField updateTitleText;
    private JTextArea updateDescriptionArea;
    private JTextField updateVersionText;
    private JButton createUpdateButton;
    private JTextField inviteParticipantUsernameText;
    private JLabel inviteParticipantLabel;
    private JButton inviteParticipantButton;
    private JTextField inviteParticipantMessageText;
    private JLabel inviteParticipantMessageLabel;
    private JLabel inviteParticipantUsernameLabel;
    private JTextArea inviteParticipantMessageArea;
    private JButton leaveHackathonButton;
    private JLabel createUpdateLabel;
    private JLabel updateTitleLabel;
    private JLabel updateDescriptionLabel;
    private JLabel updateVersionLabel;
    private JPanel opinionsPanel;
    private JTable opinionsTable;
    private JLabel opinionsTableLabel;
    private JScrollPane opinionsScrollPane;


    public Home(Controller controller, JFrame loginFrame) {
        homeFrame = new JFrame("Home");
        homeFrame.setContentPane(homePanel);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(false);
        usernameHomeLabel.setText(controller.getCurrentUserUsername());
        roleHomeLabel.setText(controller.getCurrentUserRole()); //Label che mostra il nome dell'utente dopo il login
        homeFrame.pack();
        //homeFrame.setVisible(true);
        //System.out.println(controller.getCurrentUserRole());

        //Visibilità componenti GUI iniziali
        leaveHackathonButton.setVisible(false);
        invitesTable.setVisible(false);
        acceptInviteButton.setVisible(false);
        refuseInviteButton.setVisible(false);
        inviteTextField.setVisible(false);
        updateTitleText.setVisible(false);
        updateDescriptionArea.setVisible(false);
        updateVersionText.setVisible(false);
        createUpdateButton.setVisible(false);
        leaveHackathonButton.setVisible(false);
        //Componenti visualizzazione opinioni
        opinionsPanel.setVisible(false);
        opinionsScrollPane.setVisible(false);
        opinionsTable.setVisible(false);
        opinionsTableLabel.setVisible(false);
        //Componenti creazione update
        updateDescriptionArea.setVisible(false);
        updateTitleText.setVisible(false);
        updateVersionText.setVisible(false);
        createUpdateButton.setVisible(false);
        createButton.setVisible(false);
        updateTitleLabel.setVisible(false);
        updateDescriptionLabel.setVisible(false);
        updateVersionLabel.setVisible(false);
        createUpdateLabel.setVisible(false);
        //Componenti invito partecipanti
        inviteParticipantButton.setVisible(false);
        inviteParticipantLabel.setVisible(false);
        inviteParticipantUsernameLabel.setVisible(false);
        inviteParticipantUsernameText.setVisible(false);
        inviteParticipantMessageLabel.setVisible(false);
        inviteParticipantMessageArea.setVisible(false);
        inviteParticipantButton.setVisible(false);
        inviteFormLabel.setVisible(false);



        //Gestione home utente
        if (controller.getCurrentUserRole().equals("User")) {
            invitesTable.setVisible(false);
            invitesTableLabel.setVisible(false);
            createButton.setVisible(false);
            inviteParticipantButton.setVisible(false);
            hackathonActionButtonLabel.setText("Enter the title of the hackathon you want to subscribe to");
            if (controller.checkParticipantTeam()) //Se l'utente partecipa ad un hackathon, questi componenti vanno rimossi
            {
                changeJudgeStatusButton.setVisible(false);
                hackathonActionButtonLabel.setVisible(false);
                invitesTable.setVisible(false);
            }
                changeJudgeStatusButton.setVisible(true);
                changeJudgeStatusButton.setText("Sign as Judge");
                changeJudgeStatusButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.makeUserJudge();
                        homeFrame.setVisible(false);
                        loginFrame.setVisible(true);
                        homeFrame.dispose();
                        controller.logout();
                    }
                });
            String[] columnNames = {"Title", "Start date"};
            Object[][] data = null;
            data = controller.getUserTableData(data);
            adaptiveHomeTableTitleLabel.setText("Hackathons with active subscriptions");
            //DefaultTableModel tableButtonsModel = new DefaultTableModel(subscriptionButtons, buttonName);
            DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
            homeTable.setModel(tableModel);
            hackathonActionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    String hackathonTitle = hackathonTitleTextField.getText();
                    if(controller.subscribeUserToHackathon(hackathonTitle))
                    {
                        homeFrame.setVisible(false);
                        loginFrame.setVisible(true);
                        homeFrame.dispose();
                        controller.logout();
                    }else
                    {
                        JOptionPane.showMessageDialog(homeFrame, "Couldn't find the specified hackathon title or hackathon is already full");
                    }
                }

            });
            //homeButtonsTable.setModel(tableButtonsModel);
            //Bisogna stampare una lista di tutti gli hackathon con iscrizioni aperte










            //Gestione home giudice
        } else if (controller.getCurrentUserRole().equals("Judge")) {
            if (controller.isJudgeBusy(controller.getCurrentUserUsername()))    //Se il giudice è si occupa di almeno un hackathon allora true
            {
                hackathonActionButtonLabel.setText("Enter the title of the hackathon you want to manage");
                adaptiveHomeTableTitleLabel.setText("Your managed hackathons");
                hackathonActionButton.setText("Go to hackathon");
                changeJudgeStatusButton.setVisible(false);//L'utente è un giudice impegnato, non può cambiare ruolo
                String[] columnNames = {"Title", "Start date"};
                Object[][] data = null;
                data = controller.getJudgedHackathonsTable(data);
                DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
                homeTable.setModel(tableModel);
                hackathonActionButton.addActionListener(new ActionListener() {          //Manager hackathon giudice
                    @Override
                    public void actionPerformed(ActionEvent e)   //
                    {
                        String hackathonTitle = hackathonTitleTextField.getText();   //Prende il titolo dell'hackathon inserito
                        JudgeHackathonManager judgeManagerGUI = new JudgeHackathonManager(controller, loginFrame, homeFrame, hackathonTitle);
                        judgeManagerGUI.judgeManagerFrame.setVisible(true);
                        homeFrame.setVisible(false);
                    }
                });
            } else {    //Il giudice non si occupa di nessun hackathon
                hackathonActionButton.setVisible(false);
                hackathonActionButtonLabel.setVisible(false);
                changeJudgeStatusButton.setText("Unsign from judge");
                changeJudgeStatusButton.setVisible(true);   //Il giudice non assegnato ad alcun hackathon dedice di non fare più il giudice
                changeJudgeStatusButton.addActionListener(new ActionListener() {        //Cambio ruolo giudice
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.makeJudgeUser();
                        homeFrame.setVisible(false);
                        loginFrame.setVisible(true);
                        homeFrame.dispose();
                        controller.logout();
                    }
                });
            }
            invitesTableLabel.setVisible(true);
            invitesTable.setVisible(true);
            acceptInviteButton.setVisible(true);
            refuseInviteButton.setVisible(true);
            inviteTextField.setVisible(true);
            String[] columnNames = {"Hackathon name", "Description", "Sender"};
            Object[][] data = null;
            data = controller.getJudgeInvites();
            DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);    //Tabella che mostra gli inviti ricevuti
            invitesTable.setModel(tableModel);
            inviteFormLabel.setText("Enter the title of the hackathon of the invite you want to accept or refuse");
            acceptInviteButton.addActionListener(new ActionListener() { //Il giudice accetta l'invito all'hackathon
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    String hackathonTitle = inviteTextField.getText();
                    if(controller.acceptJudgeInvite(hackathonTitle)) {
                        homeFrame.setVisible(false);
                        loginFrame.setVisible(true);
                        homeFrame.dispose();
                        controller.logout();
                    }else{
                        JOptionPane.showMessageDialog(homeFrame, "Couldn't find the specified hackathon");
                    }
                }
            });
            refuseInviteButton.addActionListener(new ActionListener() { //Il giudice rifiuta l'invito
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    String hackathonTitle = inviteTextField.getText();

                    if(controller.refuseJudgeInvite(hackathonTitle))
                    {
                        homeFrame.setVisible(false);
                        loginFrame.setVisible(true);
                        homeFrame.dispose();
                        controller.logout();
                    }else{
                        JOptionPane.showMessageDialog(homeFrame, "Couldn't find the specified hackathon");
                    }
                }
            });

            //Devo aggiungere un bottone di inserimento della descrizione per l'hackathon









            //Gestione home partecipante    -(Finito)
        } else if (controller.getCurrentUserRole().equals("Participant") ) {
            if (controller.checkParticipantTeam() ) {    //Se true, l'utente appartiene ad un team di un hackathon.
                if(controller.checkHackathonStatus()) {
                    adaptiveHomeTableTitleLabel.setText("Your team's info");
                    hackathonActionButton.setVisible(false);
                    hackathonActionButtonLabel.setVisible(false);
                    changeJudgeStatusButton.setVisible(false);
                    hackathonTitleTextField.setVisible(false);
                    invitesTableScrollpane.setVisible(false);
                    invitesTableLabel.setVisible(false);
                    createButton.setVisible(false);
                    String[] infoColumns = {"Team title", "Team leader", "Number of members"};
                    Object[][] teamInfo = null;
                    teamInfo = controller.getTeamInfoTable(teamInfo);
                    DefaultTableModel tableModel = new DefaultTableModel(teamInfo, infoColumns);
                    homeTable.setModel(tableModel);
                }else { //L'utente è in un team ma l'hackathon è terminato, deve visualizzare i risultati ed il bottone di uscita
                    invitesTableLabel.setVisible(false);
                    invitesTableScrollpane.setVisible(false);
                    invitesTable.setVisible(false);
                    opinionsTable.setVisible(false);
                    opinionsScrollPane.setVisible(false);
                    invitesTableScrollpane.setVisible(false);
                    hackathonActionButtonLabel.setVisible(false);
                    hackathonActionButton.setVisible(false);
                    leaveHackathonButton.setVisible(true);
                    homeTable.setVisible(false);
                    /*String[] scoresColumns = {"Team title", "Final score"};
                    String[][]scores = null;
                    scores = controller.getHackathonResults();
                    DefaultTableModel tableModel = new DefaultTableModel(scores, scoresColumns);
                    homeTable.setModel(tableModel);*/
                    leaveHackathonButton.setText("Leave hackathon");
                    leaveHackathonButton.setVisible(true);
                    leaveHackathonButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            controller.leaveHackathon();
                            homeFrame.setVisible(false);
                            loginFrame.setVisible(true);
                            homeFrame.dispose();
                            controller.logout();
                        }
                    });
                }
            }else {
                //Rimossi tutti gli elementi che mostrano informazioni riguardanti l'hackathon, il partecipante non fa parte di un team
                hackathonActionButton.setVisible(false);
                hackathonActionButtonLabel.setVisible(false);
                changeJudgeStatusButton.setVisible(false);
                hackathonTitleTextField.setVisible(false);
                adaptiveHomeTableTitleLabel.setText("You're not in a team, create one or wait for an invite");
                createButton.setText("Create Team");
                createButton.setVisible(true);
                hackathonTitleTextField.setVisible(true);
                createButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        //Codice per la creazione di un team, il partecipante diventa un leader
                        String teamTitle = hackathonTitleTextField.getText();
                        if(controller.createTeam(teamTitle))
                        {
                            homeFrame.setVisible(false);
                            loginFrame.setVisible(true);
                            homeFrame.dispose();
                            controller.logout();
                        }else{
                            JOptionPane.showMessageDialog(homeFrame, "Entered team name is already used");
                        }


                    }
                });
                //Mostra la tabella degli inviti ricevuti
                String[] inviteColumns = {"Team title", "Description", "Leader"};
                String[][] invitesInfo = null;
                invitesInfo = controller.getParticipantInvites();
                DefaultTableModel tableModel = new DefaultTableModel(invitesInfo, inviteColumns);
                invitesTable.setModel(tableModel);


                //Codice gestione inviti al partecipante
                invitesTable.setVisible(true);
                invitesTableLabel.setVisible(true);
                inviteFormLabel.setVisible(true);
                inviteFormLabel.setText("Enter the title of the team you want to accept the invite");
                inviteTextField.setVisible(true);   //Casella di testo per accettazione invito
                acceptInviteButton.setVisible(true);
                refuseInviteButton.setVisible(true);
                acceptInviteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String teamTitle = inviteTextField.getText();
                        if (controller.acceptParticipantInvite(teamTitle)) {
                            homeFrame.setVisible(false);
                            loginFrame.setVisible(true);
                            homeFrame.dispose();
                            controller.logout();
                        } else {
                            JOptionPane.showMessageDialog(homeFrame, "Couldn't find the specified team");
                        }
                    }
                });
                refuseInviteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String teamTitle = inviteTextField.getText();
                        if (controller.refuseParticipantInvite(teamTitle)) {
                            homeFrame.setVisible(false);
                            loginFrame.setVisible(true);
                            homeFrame.dispose();
                            controller.logout();
                        } else {
                            JOptionPane.showMessageDialog(homeFrame, "Couldn't find the specified team");
                        }
                    }
                });

            }
            //Bisogna stampare una lista contenente solo l'hackathon a cui l'utente è iscritto












            //Gestione home leader
        }else if(controller.getCurrentUserRole().equals("Leader")) {
            hackathonActionButton.setVisible(false);    //Rimuove il tasto di subscribe di user
            //Qui deve esserci la pubblicazione di update per il team
            //Solo il leader può visionare le opinioni sugli update.
            //hackathonActionButtonLabel.setVisible(true);

            //Bisogna creare l'aggiunta di update ed il resoconto di team.
            hackathonActionButton.setVisible(true);
            changeJudgeStatusButton.setVisible(false);
            hackathonTitleTextField.setVisible(false);
            createButton.setVisible(false);
            if(!controller.checkHackathonStatus()){       //Questo si verifica quando l'hackathon è terminato
                //Viene mostrata la classifica
                invitesTableLabel.setVisible(false);
                invitesTableScrollpane.setVisible(false);
                invitesTable.setVisible(false);
                opinionsTable.setVisible(false);
                opinionsScrollPane.setVisible(false);
                invitesTableScrollpane.setVisible(false);
                hackathonActionButtonLabel.setVisible(false);
                hackathonActionButton.setVisible(false);
                String[] scoresColumns = {"Team title", "Final score"};
                String[][] scores = null;
                scores = controller.getHackathonResults();
                DefaultTableModel tableModel = new DefaultTableModel(scores, scoresColumns);
                homeTable.setModel(tableModel);
                leaveHackathonButton.setText("Leave hackathon");
                leaveHackathonButton.setVisible(true);
                leaveHackathonButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.leaveHackathon();
                        homeFrame.setVisible(false);
                        loginFrame.setVisible(true);
                        homeFrame.dispose();
                        controller.logout();
                    }
                });

            }else{  //Se l'hackathon non è terminato

                hackathonActionButton.setVisible(false);
                changeJudgeStatusButton.setVisible(false);
                hackathonTitleTextField.setVisible(false);
                createButton.setVisible(false);
                homeTable.setVisible(true);

                //Tabella che mostra la descrizione del problema fornita dai giudici
                String[] tableColumns = {"Problem description"};
                String[][] description = null;
                description = controller.getProblemDescription();
                DefaultTableModel tableModel = new DefaultTableModel(description, tableColumns);
                homeTable.setModel(tableModel);

                invitesTableLabel.setVisible(false);
                invitesTable.setVisible(false);
                invitesTableScrollpane.setVisible(false);
                //Componenti creazione update
                updateTitleLabel.setVisible(true);
                updateVersionLabel.setVisible(true);
                updateDescriptionArea.setVisible(true);
                updateTitleText.setVisible(true);
                updateVersionText.setVisible(true);
                updateDescriptionLabel.setVisible(true);
                opinionsPanel.setVisible(true);
                opinionsScrollPane.setVisible(true);
                createUpdateButton.setVisible(true);
                //Componenti invito partecipanti
                inviteParticipantButton.setVisible(true);
                inviteParticipantLabel.setVisible(true);
                inviteParticipantUsernameLabel.setVisible(true);
                inviteParticipantUsernameText.setVisible(true);
                inviteParticipantMessageLabel.setVisible(true);
                inviteParticipantMessageArea.setVisible(true);
                inviteParticipantButton.setVisible(true);
                createUpdateButton.addActionListener(new ActionListener() {   //Creazione di un update
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String updateTitle = updateTitleText.getText(); //Indica il nome dell'update (Univoco sulla piattaforma)
                        String description = updateDescriptionArea.getText(); //Indica la descrizione dell'update
                        String version = updateVersionText.getText(); //Indica la release dell'update
                        if(controller.addUpdate(updateTitle, description, version))
                        {
                            homeFrame.setVisible(false);
                            loginFrame.setVisible(true);
                            homeFrame.dispose();
                            controller.logout();
                        }else{
                            JOptionPane.showMessageDialog(homeFrame, "Can't use this update title. Try another");
                        }
                    }
                });
                inviteParticipantButton.addActionListener(new ActionListener() {    //Gestione inviti ai partecipanti
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String username = inviteParticipantUsernameText.getText();
                        String message = inviteParticipantMessageArea.getText();
                        if(controller.inviteParticipant(username, message))
                        {
                            homeFrame.setVisible(false);
                            loginFrame.setVisible(true);
                            homeFrame.dispose();
                            controller.logout();
                        }else {
                            JOptionPane.showMessageDialog(homeFrame, "Wrong participant username entered");
                        }
                    }
                });
                //Mostra l'update più recente e le relative opinioni
                opinionsPanel.setVisible(true);
                opinionsTable.setVisible(true);
                opinionsTableLabel.setVisible(true);
                String[] updateOpinionsColumns={"Opinion", "Publisher's username"};
                String[][] updateOpinions = controller.getLatestUpdateOpinions();
                if(updateOpinions != null)
                {
                    DefaultTableModel opinionsTableModel = new DefaultTableModel(updateOpinions, updateOpinionsColumns);
                    opinionsTable.setModel(opinionsTableModel);
                }
            }















            //Gestione home organizzatore   (Finito)
        }else if (controller.getCurrentUserRole().equals("Organizer")) {
            hackathonActionButtonLabel.setText("Enter the title of the hackathon you want to manage");
            changeJudgeStatusButton.setVisible(false);
            createButton.setVisible(true);
            invitesTableLabel.setVisible(false);
            invitesTableScrollpane.setVisible(false);
            createButton.setText("Create new hackathon");
            createButton.addActionListener(new ActionListener() {       //L'organizzatore decide di creare un nuovo hackathon
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    OrganizerHackathonCreate OrganizerGUI = new OrganizerHackathonCreate(controller, loginFrame, homeFrame);
                    OrganizerGUI.organizerFrame.setVisible(true);
                    homeFrame.setVisible(false);
                }
            });
            hackathonActionButton.setText("Go to hackathon");
            adaptiveHomeTableTitleLabel.setText("Your organized hackathons");

            hackathonActionButton.addActionListener(new ActionListener() {  //Porta al pannelo di gestione hackathon
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    String hackathonTitle = hackathonTitleTextField.getText();
                    if(controller.checkOrganizerHackathonTitle(hackathonTitle)) {
                        OrganizerHackathonManager organizerHackathonManagerGUI = new OrganizerHackathonManager(controller, loginFrame, homeFrame, hackathonTitle);
                        organizerHackathonManagerGUI.organizerManagerFrame.setVisible(true);
                        homeFrame.setVisible(false);
                    }else {
                        JOptionPane.showMessageDialog(homeFrame, "Entered a wrong hackathon title");
                    }
                }
            });


            String[] columnNames = {"Title", "Start date"};
            Object[][] data = null;
            data = controller.getOrganizedHackathonsTable(data);
            DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
            homeTable.setModel(tableModel);

            //Bisogna stampare una lista contenente tutti gli hackathon organizzati dall'organizzatore
        }
        logoutButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               controller.logout();
               homeFrame.setVisible(false);
               loginFrame.setVisible(true);
               homeFrame.dispose();
           }
        });

    }

}
