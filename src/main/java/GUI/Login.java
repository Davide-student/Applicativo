package GUI;
import controller.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {
    private JPanel loginPanel;
    private static JFrame loginFrame;    //Il jframe deve essere statico perché sia sempre presente durante l'esecuzione del programma
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private JButton signInButton;
    private JButton registerButton;
    private JLabel usernameLabel;
    private JLabel mottoTextField;
    private JPanel enterCredentialsPanel;
    private final Controller controller;   //Riferimento al controller del BCE pattern, final perché una volta inizializzato dal costruttore "Login()" non deve essere modificato.

    public static void main(String[] args) {

        loginFrame = new JFrame("Login");    //Creazione jframe
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setContentPane(new Login().loginPanel);
        loginFrame.pack();
        loginFrame.setVisible(true);

    }

    public Login() {
        this.controller = new Controller();
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = String.valueOf(passwordTextField.getPassword());
                if (controller.checkCredentials(username, password)) {    //Le credenziali sono verificate, l'utente viene trasferito alla home adattiva
                    Home homeGUI = new Home(controller, loginFrame);
                    homeGUI.homeFrame.setVisible(true);
                    loginFrame.setVisible(false);
                } else {  //L'utente non è registrato o le credenziali sono errate, viene notificato.
                    JOptionPane.showMessageDialog(loginFrame, "Invalid username or password");
                }

            }
        });
        registerButton.addActionListener(new ActionListener() { //L'utente clicca sul tasto "Register", passa al form di registrazione
            @Override
            public void actionPerformed(ActionEvent e) {
                Register registerGUI = new Register(controller, loginFrame);
                registerGUI.registerFrame.setVisible(true);
                loginFrame.setVisible(false);
            }
        });
    }

}
