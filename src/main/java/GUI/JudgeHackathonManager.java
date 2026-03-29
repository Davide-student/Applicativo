package GUI;

import javax.swing.*;
import java.awt.*;
import controller.*;

public class JudgeHackathonManager {
    public JFrame judgeManagerFrame;
    private JPanel judgeManagerPanel;

    public JudgeHackathonManager(Controller controller, JFrame loginframe, JFrame homeFrame, String hackathonTitle) {
        judgeManagerFrame = new JFrame();
        judgeManagerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        judgeManagerFrame.setContentPane(judgeManagerPanel);
        judgeManagerFrame.setTitle("Judge manager panel");
        //homeFrame.setVisible(false);
        judgeManagerFrame.pack();
    }
}
