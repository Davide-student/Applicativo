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
    private JButton subscribeToHackathonButton;


    public Home(Controller controller, JFrame loginFrame) {
        homeFrame = new JFrame("Home");
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setContentPane(homePanel);
        loginFrame.setVisible(false);
        usernameHomeLabel.setText(controller.getCurrentUserUsername());
        roleHomeLabel.setText(controller.getCurrentUserRole()); //Label che mostra il nome dell'utente dopo il login
        homeFrame.pack();
        homeFrame.setVisible(true);
        //System.out.println(controller.getCurrentUserRole());
        if (controller.getCurrentUserRole().equals("User")) {
            String[] columnNames = {"Title", "Start date"};
            Object[][] data = null;
            data = controller.getUserTableData(data);
            adaptiveHomeTableTitleLabel.setText("Hackathons with active subscriptions");
            //DefaultTableModel tableButtonsModel = new DefaultTableModel(subscriptionButtons, buttonName);
            DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
            homeTable.setModel(tableModel);
            subscribeToHackathonButton.addActionListener(new ActionListener() {
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
        } else if (controller.getCurrentUserRole().equals("Judge")) {
            //Bisogna stampare una lista di tutti gli hackathon gestiti dal giudice
        } else if (controller.getCurrentUserRole().equals("Participant")) {
            //Bisogna stampare una lista contenente solo l'hackathon a cui l'utente è iscritto
        } else if (controller.getCurrentUserRole().equals("Organizer")) {
            //Bisogna stampare una lista contenente tutti gli hackathon organizzati dall'organizzatore
        }


    }

}
