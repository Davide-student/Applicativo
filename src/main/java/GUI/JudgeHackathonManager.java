package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.*;

public class JudgeHackathonManager {
    public JFrame judgeManagerFrame;
    private JPanel judgeManagerPanel;
    private JButton backToHomeButton;
    private JTable teamsTable;
    private JTextField voteField;
    private JTextField teamTitleField;
    private JTextArea opinionArea;
    private JButton publishVoteButton;
    private JButton publishOpinionButton;
    private JTextField updateTitleField;
    private JLabel updateTitleLabel;
    private JTextArea publishHackathonProblemArea;
    private JButton publishHackathonProblemButton;
    private JLabel publishHackathonProblemLabel;

    public JudgeHackathonManager(Controller controller, JFrame loginframe, JFrame homeFrame, String hackathonTitle) {
        judgeManagerFrame = new JFrame();
        judgeManagerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        judgeManagerFrame.setContentPane(judgeManagerPanel);
        judgeManagerFrame.setTitle("Judge manager panel");
        //homeFrame.setVisible(false);
        judgeManagerFrame.pack();
        String [] columnNames = {"Team title", "Update description", "Update title"};
        String[][] data = null;
        data = controller.getTeamsAndUpdates(hackathonTitle);   //Informazioni su teams e updates (più recenti)
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        teamsTable.setModel(tableModel);

        //Pubblicazione opinione per update
        publishOpinionButton.addActionListener(new ActionListener() {       //Pubblica un opinione forniti il nome del team ed il titolo dell'update
            @Override
            public void actionPerformed(ActionEvent e) {
                String updateTitle = updateTitleField.getText();
                String teamTitle = teamTitleField.getText();
                String opinion = opinionArea.getText();
                if(controller.publishOpinion(hackathonTitle, updateTitle, teamTitle, opinion))
                {
                    judgeManagerFrame.setVisible(false);
                    homeFrame.setVisible(true);
                    judgeManagerFrame.dispose();
                    controller.logout();
                }else
                {
                    JOptionPane.showMessageDialog(judgeManagerFrame, "Couldn't find update title or team title");
                }

            }
        });
        //Pubblicazione descrizione per hackathon
        publishHackathonProblemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String problemDescription = publishHackathonProblemArea.getText();
                if(controller.publishProblem(hackathonTitle, problemDescription)){
                    judgeManagerFrame.setVisible(false);
                    loginframe.setVisible(true);
                    judgeManagerFrame.dispose();
                    homeFrame.dispose();
                    controller.logout();
                }else {
                    JOptionPane.showMessageDialog(judgeManagerFrame, "Problem description has already been published");
                }
            }
        });


        //Pubblicazione voto per team
        publishVoteButton.addActionListener(new ActionListener() {  //Pubblica un voto per il team
            @Override
            public void actionPerformed(ActionEvent e) {
                String teamTitle = teamTitleField.getText();
                String vote = voteField.getText();
                if(controller.giveVoteToTeam(hackathonTitle, teamTitle, vote)) {
                    judgeManagerFrame.setVisible(false);
                    homeFrame.setVisible(true);
                    judgeManagerFrame.dispose();
                    controller.logout();
                }else{
                    JOptionPane.showMessageDialog(judgeManagerFrame, "Given vote is invalid");
                }

            }
        });












        backToHomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                judgeManagerFrame.setVisible(false);
                homeFrame.setVisible(true);
                judgeManagerFrame.dispose();
            }
        });
    }
}
