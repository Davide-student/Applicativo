package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import controller.*;
import model.Organizer;

//L'organizzatore deve poter:
/*
* Chiudere le iscrizioni (lo stato delle registrazioni viene settato a false)
* Far terminare l'hackathon (Viene rimosso il leader del, i partecipanti all'hackathon, il riferimento all'hackathon dell'organizzatore, il riferimento all'hackathon per i giudici)
* Può mandare inviti ai giudici
*
*/



public class OrganizerHackathonManager {
    public JFrame organizerManagerFrame;
    private JLabel titleLabel;
    private JTable judgesTable;
    private JTextField judgeUsernameField;
    private JButton inviteButton;
    private JButton closeRegistrationsButton;
    private JButton endHackathonButton;
    private JLabel hackathonTitleLabel;
    private JTextArea inviteDescriptionArea;
    private JButton backToHomeButton;
    private JPanel organizerManagerPanel;

    public OrganizerHackathonManager(Controller controller, JFrame loginFrame, JFrame homeFrame, String hackathonTitle) {
        organizerManagerFrame = new JFrame("Organizer Manager");
        organizerManagerFrame.setContentPane(organizerManagerPanel);
        organizerManagerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        organizerManagerFrame.setVisible(true);
        organizerManagerFrame.pack();
        hackathonTitleLabel.setText(hackathonTitle);
        closeRegistrationsButton.addActionListener(new ActionListener() {   //Chiusura delle registrazioni di un hackathon
           @Override
           public void actionPerformed(ActionEvent e) {
               controller.closeHackathonRegister(hackathonTitle);
               organizerManagerFrame.setVisible(false);
               homeFrame.setVisible(true);
               organizerManagerFrame.dispose();
           }
        });
        endHackathonButton.addActionListener(new ActionListener() { //Chiusura di un hackathon
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.endHackathon(hackathonTitle);
                organizerManagerFrame.setVisible(false);
                homeFrame.setVisible(false);
                loginFrame.setVisible(true);
                organizerManagerFrame.dispose();
                homeFrame.dispose();
                controller.logout();
            }

        });
        inviteButton.addActionListener(new ActionListener() {   //Invito di un giudice
            @Override
            public void actionPerformed(ActionEvent e) {
                String judgeUsername = judgeUsernameField.getText();
                String inviteDescription = inviteDescriptionArea.getText();
                if(controller.searchForJudge(judgeUsername))
                {
                    controller.manageJudgeInvite(controller.getCurrentUser().getUsername(), judgeUsername, inviteDescription, hackathonTitle);
                    organizerManagerFrame.setVisible(false);
                    loginFrame.setVisible(true);
                    homeFrame.dispose();
                    organizerManagerFrame.dispose();
                    controller.logout();

                }else{
                    JOptionPane.showMessageDialog(organizerManagerFrame, "Entered judge username isn't valid.");
                }
            }
        });
        String[] judgesTableTitle = {"Username"};
        String[][] judgesUsernames = null;
        judgesUsernames = controller.getJudges();
        DefaultTableModel tableModel = new DefaultTableModel(judgesUsernames, judgesTableTitle);
        judgesTable.setModel(tableModel);
        backToHomeButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               organizerManagerFrame.setVisible(false);
               homeFrame.setVisible(true);
               organizerManagerFrame.dispose();
           }
        });


    }
}
