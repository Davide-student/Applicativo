package DAO;
import java.util.ArrayList;

public interface DAOUpdaterI {

    //Insert
    public void insertUser(String username, String password);
    public void insertJudge(String username, String password);
    public void insertOpinion(String updateTitle, String judgeUsername, String description);
    public void insertRating(String teamTitle, int rating);
    public void insertLeader(String teamName, String hackathonTitle, String username, String password);
    public void insertLocation(String address, int CAP, String street, int doorNumber);
    public void insertHackathon(String title, int maxTeamNumber, int minTeamNumber, int maxTeamSize, int minTeamSize, String registrationStartDate, String locationAddress, String organizerUsername, boolean status, boolean registrationStatus, String problemDescription);
    public void insertTeam(String teamName, int finalProjectRating, String hackathonTitle);
    public void insertUpdate(String teamName, String updateTitle, String description, String version);
    public void insertInvite(String hackathonTitle, String receiverUsername, String senderUsername, String teamName, String message);
    public void insertParticipant(String username, String password, String hackathonTitle);
    public void insertJudgeToHackathon(String judgeUsername, String hackathonTitle);


    //Update
    public void updateHackathonDescription(String hackathonTitle, String problemDescription);
    public void updateHackathonRegister(boolean status, String hackathonTitle, String startDate);
    public void updateParticipantTeam(String username, String teamName);
    public void updateLocationHackathon(String hackathonTitle, String locationAddress);
    public void updateHackathonStatus(boolean status, String hackathonTitle);


    //delete
    public void deleteRatings(String teamName);
    public void deleteUnsubscribedParticipants(String hackathonTitle);
    public void deleteJudge(String username, String password);
    public void deleteInvite(String username, String teamName, String hackathonName);
    public void deleteLeader(String username);
    public void deleteTeam(String hackathonName, String teamName);
    public void deleteAllParticipants(String hackathonTitle);
    public void deleteHackathonJudges(String hackathonTitle);
    public void deleteHackathon(String hackathonTitle);
    public void deleteHackathonLocation(String hackathonTitle, String locationAddress);
    public void deleteUpdatesAndOpinions(String teamName);
    public void deleteHackathonInvites(String hackathonTitle);

}
