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
    private JButton createTeamButton;
    private JButton logoutButton;


    public Home(Controller controller, JFrame loginFrame) {
        homeFrame = new JFrame("Home");
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setContentPane(homePanel);
        loginFrame.setVisible(false);
        usernameHomeLabel.setText(controller.getCurrentUserUsername());
        roleHomeLabel.setText(controller.getCurrentUserRole()); //Label che mostra il nome dell'utente dopo il login
        homeFrame.pack();
        //homeFrame.setVisible(true);
        //System.out.println(controller.getCurrentUserRole());



        //Gestione home utente
        if (controller.getCurrentUserRole().equals("User")) {
            createTeamButton.setVisible(false);
            hackathonActionButtonLabel.setText("Enter the title of the hackathon you want to subscribe to");
            changeJudgeStatusButton.setVisible(true);
            changeJudgeStatusButton.setText("Sign as Judge");
            changeJudgeStatusButton.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e)
               {
                    controller.makeUserJudge();
                    homeFrame.setVisible(false);
                    loginFrame.setVisible(true);
                    homeFrame.dispose();
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
                    }else
                    {
                        JOptionPane.showMessageDialog(homeFrame, "Couldn't find the specified hackathon title");
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
                hackathonActionButton.addActionListener(new ActionListener() {
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
                changeJudgeStatusButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.makeJudgeUser();
                        homeFrame.setVisible(false);
                        loginFrame.setVisible(true);
                        homeFrame.dispose();
                    }
                });
            }


            //Bisogna stampare una lista di tutti gli hackathon gestiti dal giudice






            //Gestione home partecipante
        } else if (controller.getCurrentUserRole().equals("Participant") ) {
            if (controller.checkParticipantTeam()) {    //Se true, l'utente appartiene ad un team.

            } else {
                //Rimossi tutti gli elementi che mostrano informazioni riguardanti l'hackathon, il partecipante non fa parte di un team
                hackathonActionButton.setVisible(false);
                hackathonActionButtonLabel.setVisible(false);
                changeJudgeStatusButton.setVisible(false);
                homeTable.setVisible(false);
                hackathonTitleTextField.setVisible(false);
                adaptiveHomeTableTitleLabel.setText("You're not in a team, create one or wait for an invite");
                createTeamButton.setText("Create Team");
            }
            //Bisogna stampare una lista contenente solo l'hackathon a cui l'utente è iscritto












            //Gestione home leader
        }else if(controller.getCurrentUserRole().equals("Leader")) {
            //Qui deve esserci la pubblicazione di update per il team
            //Solo il leader può visionare le opinioni sugli update.
















            //Gestione home organizzatore
        }else if (controller.getCurrentUserRole().equals("Organizer")) {
            hackathonActionButtonLabel.setText("Enter the title of the hackathon you want to manage");
            changeJudgeStatusButton.setVisible(false);
            createTeamButton.setVisible(false);
            hackathonActionButton.setText("Go to hackathon");
            adaptiveHomeTableTitleLabel.setText("Your organized hackathons");

            //hackathonActionButton.addActionListener(new ActionListener() {});*    Action listener che deve porta in "OrganizerHackathonManager"


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
