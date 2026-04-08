package postgresDAOImplementation;

import DAO.DAOUpdaterI;
import java.util.ArrayList;
import database.DatabaseConnection;
import java.sql.*;



public class DAOUpdater implements DAOUpdaterI {
    Connection connection = null;

    public DAOUpdater() {
        try {
            connection = DatabaseConnection.getInstance().connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }




    //Insert
    public void insertUser(String username, String password)
    {
        try {
            connection = DatabaseConnection.getInstance().connection;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        String queryText = "INSERT INTO hackathon.public.user(username, password )VALUES('" + username + "','" + password + "');";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void insertJudge(String username, String password)
    {
        try {
            connection = DatabaseConnection.getInstance().connection;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        String queryText = "INSERT INTO hackathon.public.judge(username, password) VALUES('" + username + "','" + password + "')";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void insertOpinion(String updateTitle, String judgeUsername, String description)
    {
        try {
            connection = DatabaseConnection.getInstance().connection;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        String queryText = "INSERT INTO hackathon.public.opinion(updateid, judgeid, description) VALUES('" + updateTitle + "','" + judgeUsername + "','" + description + "')";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void insertRating(String teamTitle, int rating)
    {
        try {
            connection = DatabaseConnection.getInstance().connection;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        String queryText = "INSERT INTO hackathon.public.rating(teamid, value) VALUES('" + teamTitle + "'," + rating + ")";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void insertLeader(String teamName, String hackathonTitle, String username, String password)
    {
        try {
            connection = DatabaseConnection.getInstance().connection;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        String queryText = "INSERT INTO hackathon.public.leader(username, password, teamid, hackathonid) VALUES('" + username + "' ,'" + password + "', '" + teamName + "', '" + hackathonTitle + "')";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void insertLocation(String address, int CAP, String street, int doorNumber)
    {
        try {
            connection = DatabaseConnection.getInstance().connection;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        String queryText = "INSERT INTO hackathon.public.location(address, street, cap, doornumber, hackathonid) VALUES ('" + address + "', '" + street + "', '" + CAP + "', '" + doorNumber + "', null)";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void insertHackathon(String title, int maxTeamNumber, int minTeamNumber, int maxTeamSize, int minTeamSize, String locationAddress, String organizerUsername, String registrationStartDate, boolean status, boolean registrationStatus, String problemDescription)
    {
        try {
            connection = DatabaseConnection.getInstance().connection;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        String queryText = "INSERT INTO hackathon.public.event(title, maxteamnumber, minteamnumber, maxteamsize, minteamsize, registrationstartdate, registrationstatus, hackathonstatus, startdate, enddate, problemdescription, eventorganizerid, locationID) VALUES('" + title +"', '" + maxTeamNumber + "', '" + minTeamNumber + "', '" + maxTeamSize + "', '" + minTeamSize + "','" + registrationStartDate + "', '" + registrationStatus + "', '" + status + "',NULL, NULL, '" + problemDescription + "', '" + organizerUsername + "', '" + locationAddress + "')";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void insertTeam(String teamName, int finalProjectRating, String hackathonTitle)
    {
        try {
            connection = DatabaseConnection.getInstance().connection;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        String queryText = "INSERT INTO hackathon.public.team(name, finalProjectRating, hackathonID) VALUES('"+ teamName +"', '" + finalProjectRating + "', '" + hackathonTitle + "')";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void insertUpdate(String teamName, String updateTitle, String description, String version)
    {
        try {
            connection = DatabaseConnection.getInstance().connection;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        String queryText = "INSERT INTO hackathon.public.update(title, description, releasenumber, teamid) VALUES('" + updateTitle+ "', '" + description + "', '" + version + "', '" + teamName + "')";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void insertInvite(String hackathonTitle, String receiverUsername, String senderUsername, String teamName, String message) {
        try {
            connection = DatabaseConnection.getInstance().connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String queryText;
        if (teamName == null) { //L'invito è per un giudice
             queryText = "INSERT INTO hackathon.public.invite(text, teamID, hackathonID, senderID, receiverID) VALUES('" + message + "', null, '" + hackathonTitle + "', '" + senderUsername + "', '" + receiverUsername + "')";
        }else   //L'invito è per un partecipante
        {
            queryText = "INSERT INTO hackathon.public.invite(text, teamID, hackathonID, senderID, receiverID) VALUES('" + message + "', '" + teamName + "', null, '" + senderUsername + "', '" + receiverUsername + "')";
        }
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void insertParticipant(String username, String password, String hackathonTitle)
    {
        try {
            connection = DatabaseConnection.getInstance().connection;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        String queryText = "INSERT INTO hackathon.public.participant(username, password, teamID, hackathonID) VALUES('" + username + "', '" + password + "', null, '" + hackathonTitle + "')";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void insertJudgeToHackathon(String judgeUsername, String hackathonTitle)
    {
        try {
            connection = DatabaseConnection.getInstance().connection;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        String queryText = "INSERT INTO hackathon.public.hackathonJudge(judgeID, hackathonID) VALUES('"+ judgeUsername + "', '" + hackathonTitle +"')";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    //Update
    public void updateLocationHackathon(String hackathonTitle, String locationAddress)
    {
        try {
            connection = DatabaseConnection.getInstance().connection;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        String queryText = "UPDATE hackathon.public.location set hackathonid = '" + hackathonTitle +"' WHERE address = '" + locationAddress + "'";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateHackathonDescription(String hackathonTitle, String problemDescription)
    {
        try {
            connection = DatabaseConnection.getInstance().connection;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        String queryText = "UPDATE hackathon.public.event SET problemDescription = '" + problemDescription +"' WHERE title = '" + hackathonTitle + "'";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateHackathonRegister(boolean status, String hackathonTitle, String startDate)
    {
        try {
            connection = DatabaseConnection.getInstance().connection;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        String queryText = "UPDATE hackathon.public.event SET registrationStatus = '" + status + "', startdate = '" + startDate + "' WHERE title = '" + hackathonTitle + "'";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateParticipantTeam(String username, String teamName)
    {
        try {
            connection = DatabaseConnection.getInstance().connection;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        String queryText = "UPDATE hackathon.public.participant SET teamID = '" + teamName + "'WHERE username = '" + username + "'";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateHackathonStatus(boolean status, String hackathonTitle)
    {
        try {
            connection = DatabaseConnection.getInstance().connection;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        String queryText = "UPDATE hackathon.public.event SET hackathonStatus = '" + status + "' WHERE title = '" + hackathonTitle + "'";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    //delete
    public void deleteRatings(String teamName)
    {
        try {
            connection = DatabaseConnection.getInstance().connection;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        String queryText = "DELETE FROM rating WHERE teamID = '" + teamName + "'";

        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteUnsubscribedParticipants(String hackathonTitle)
    {
        try {
            connection = DatabaseConnection.getInstance().connection;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        String queryText = "DELETE FROM hackathon.public.participant WHERE hackathonid = '" + hackathonTitle + "' AND teamid is null";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteJudge(String username, String password)
    {
        try {
            connection = DatabaseConnection.getInstance().connection;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        String queryText = "DELETE FROM hackathon.public.judge WHERE(username = '" + username + "')";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteInvite(String username, String teamName, String hackathonName)
    {
        try {
            connection = DatabaseConnection.getInstance().connection;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        String queryText;
        if(teamName != null) {  //Invito a partecipante
            queryText = "DELETE FROM hackathon.public.invite WHERE(receiverid = '" + username + "' AND teamID = '" + teamName + "')";
        }else   //Invito a giudice
        {
            queryText = "DELETE FROM hackathon.public.invite WHERE(receiverid = '" + username + "' AND hackathonID = '" + hackathonName + "')";
        }
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteLeader(String username)
    {
        try {
            connection = DatabaseConnection.getInstance().connection;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        String queryText = "DELETE FROM hackathon.public.leader WHERE username = '" + username + "'";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteTeam(String hackathonName, String teamName)
    {
        try {
            connection = DatabaseConnection.getInstance().connection;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        String queryText = "DELETE FROM hackathon.public.team WHERE (name = '" + teamName + "' AND hackathonID = '" + hackathonName + "')";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            query.executeQuery();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteAllParticipants(String hackathonTitle) {
        try {
            connection = DatabaseConnection.getInstance().connection;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        String queryText = "DELETE FROM hackathon.public.participant WHERE hackathonID = '" + hackathonTitle + "'";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteHackathonJudges(String hackathonTitle)
    {
        try {
            connection = DatabaseConnection.getInstance().connection;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        String queryText = "DELETE FROM hackathon.public.hackathonjudge WHERE hackathonID = '" + hackathonTitle + "'";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteHackathon(String hackathonTitle)
    {
        try {
            connection = DatabaseConnection.getInstance().connection;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        String queryText = "DELETE FROM hackathon.public.event WHERE title = '" + hackathonTitle + "'";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteUpdatesAndOpinions(String teamName)
    {
        try {
            connection = DatabaseConnection.getInstance().connection;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        String queryText = "DELETE FROM hackathon.public.update WHERE (teamid = '" + teamName + "')";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteHackathonLocation(String hackathonTitle, String locationAddress)
    {
        try {
            connection = DatabaseConnection.getInstance().connection;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        String queryRemoveFkHackathon = "UPDATE hackathon.public.event SET locationID = null WHERE title = '" + hackathonTitle + "'";
        String queryRemoveFkLocation = "UPDATE hackathon.public.location set hackathonID = null WHERE address = '" + locationAddress + "'";
        String queryDeleteLocation = "DELETE from hackathon.public.location WHERE address = '" + locationAddress + "'";
        try {
            PreparedStatement query = connection.prepareStatement(queryRemoveFkHackathon);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            PreparedStatement query = connection.prepareStatement(queryRemoveFkLocation);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            PreparedStatement query = connection.prepareStatement(queryDeleteLocation);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteHackathonInvites(String hackathonTitle)
    {
        try {
            connection = DatabaseConnection.getInstance().connection;
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        String queryText = "DELETE FROM invite INNER JOIN hackathon.public.participant ON participant.username = invite.receiverID WHERE participant.hackathonID = ' " + hackathonTitle + "' AND teamID is NULL";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }












}


