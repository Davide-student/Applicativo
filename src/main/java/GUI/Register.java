package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.*;

public class Register {
    public JFrame registerFrame;
    private JTextField usernameRegTextField;
    private JPasswordField passwordRegTextField;
    private JButton registerButton;
    private JLabel usernameRegLabel;
    private JLabel passwordRegLabel;
    private JButton goBackToLoginButton;
    private JPanel registerPanel;
    private JPanel enterCredentialsPanel;

    public Register(Controller controller, JFrame loginFrame) {
        registerFrame = new JFrame("Register");
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerFrame.setContentPane(registerPanel);
        registerFrame   .setExtendedState(JFrame.MAXIMIZED_BOTH);
        registerFrame.pack();
        goBackToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBackToLogin(loginFrame);
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameRegTextField.getText();
                String password = String.valueOf(passwordRegTextField.getPassword());
                if (controller.checkRegistrationAvailability(username, password)) {
                    //L'utente si registra, l'username inserito è disponibile
                    goBackToLogin(loginFrame);  //L'utente viene reindirizzato al form di login dopo essersi registrato

                } else {
                    JOptionPane.showMessageDialog(registerFrame, "This username is already in use or at least one form field is empty");
                }
            }
        });


    }

    public void goBackToLogin(JFrame loginFrame) {
        //Listener del bottone per tornare al form di login (Entry point)
        registerFrame.setVisible(false);
        loginFrame.setVisible(true);
        registerFrame.dispose();
    }

}
