package GUI;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import controller.*;

public class OrganizerHackathonCreate {
    public JFrame organizerFrame;
    private JPanel organizerHackathonCreatePanel;
    private JTextField titleField;
    private JTextField maxTeamNumberField;
    private JTextField minTeamNumberField;
    private JTextField maxTeamSizeField;
    private JTextField minTeamSizeField;
    private JTextField locationAddressField;
    private JTextField locationCAPField;
    private JTextField locationStreetField;
    private JTextField locationDoorField;
    private JLabel titleLabel;
    private JLabel maxTeamNumberLabel;
    private JLabel minTeamNumberlabel;
    private JLabel maxTeamSizeLabel;
    private JLabel locationCAPLabel;
    private JLabel locationAddressLabel;
    private JLabel locationStreetLabel;
    private JLabel locationDoorLabel;
    private JButton createButton;

    public OrganizerHackathonCreate(Controller controller, JFrame loginFrame, JFrame homeFrame) {
        organizerFrame = new JFrame("Hackathon creator");
        organizerFrame.setContentPane(organizerHackathonCreatePanel);
        organizerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //titleLabel.setVisible(true);
        organizerFrame.setVisible(true);
        organizerFrame.pack();
        //System.out.println("Sono entrato.");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String title = titleField.getText();
                String maxTeamNumber = maxTeamNumberField.getText();
                String minTeamNumber = minTeamNumberField.getText();
                String maxTeamSize = maxTeamSizeField.getText();
                String minTeamSize = minTeamSizeField.getText();
                String locationAddress = locationAddressField.getText();
                String locationCAP = locationCAPField.getText();
                String locationStreet = locationStreetField.getText();
                String locationDoor = locationDoorField.getText();

                if(controller.createHackathon(title, maxTeamNumber, minTeamNumber, maxTeamSize, minTeamSize, locationAddress, locationStreet, locationCAP, locationDoor)) {
                    organizerFrame.setVisible(false);
                    loginFrame.setVisible(true);
                    organizerFrame.dispose();
                    homeFrame.dispose();
                    controller.logout();
                }else{
                    JOptionPane.showMessageDialog(organizerFrame, "Title is already used");
                }
            }
        });
    }



}
