package GUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.*;


public class Home {
    public JFrame homeFrame;
    private JPanel homePanel;
    private JLabel usernameHomeLabel;
    private JLabel roleHomeLabel;
    private JLabel loggedAsHomeLabel;
    private JLabel staticRoleHomeLabel;
    private JPanel titlePanel;
    private JPanel userInfoPanel;
    private JLabel adaptiveHomeTableTitleLabel;
    private JTable homeTable;
    private JPanel homeTablePanel;
    private JScrollPane homeTableScrollPanel;


    public Home(Controller controller, JFrame loginFrame) {
        homeFrame = new JFrame("Home");
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setContentPane(homePanel);
        loginFrame.setVisible(false);
        usernameHomeLabel.setText(controller.getCurrentUserUsername());
        roleHomeLabel.setText(controller.getCurrentUserRole()); //Label che mostra il nome dell'utente dopo il login
        homeFrame.pack();
        homeFrame.setVisible(true);
        if (controller.getCurrentUserRole().equals("User")) {
            String[] columnNames = {"Title", "Start date", "Subscribe button"};
            Object[][] data = null;
            data = controller.getUserTableData(data);
            /*for(int row = 0; row < data.length; row++ )
            {
               data[row][2] = 
            }*/
            adaptiveHomeTableTitleLabel.setText("Hackathons with active subscriptions");
            DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
            homeTable.setModel(tableModel);
            //Bisogna stampare una lista di tutti gli hackathon con iscrizioni aperte
        } else if (controller.getCurrentUserRole().equals("Judge")) {
            //Bisogna stampare una lista di tutti gli hackathon gestiti dal giudice
        } else if (controller.getCurrentUserRole().equals("Participant")) {
            //Bisogna stampare una lista contenente solo l'hackathon a cui l'utente è iscritto
        } else if (controller.getCurrentUserRole().equals("Organizer")) {
            //Bisogna stampare una lista contenente tutti gli hackathon organizzati dall'organizzatore
        }


    }

}
