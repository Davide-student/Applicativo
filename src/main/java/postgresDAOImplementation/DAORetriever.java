package postgresDAOImplementation;

import DAO.DAORetrieverI;

import java.util.ArrayList;
import database.DatabaseConnection;
import java.sql.*;

public class DAORetriever implements DAORetrieverI {

    Connection connection;
    public DAORetriever() {
        try {

            connection = DatabaseConnection.getInstance().connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void closeConnection()
    {
        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void retrieveHackathonsData(ArrayList<String> hackathonTitles, ArrayList<Integer> maxTeamNumbers, ArrayList<Integer> minTeamNumbers, ArrayList<Integer> maxTeamSizes, ArrayList<Integer> minTeamSizes, ArrayList<String> registrationsStartDates, ArrayList<Boolean> registrationStatuses, ArrayList<Boolean> hackathonStatuses, ArrayList<String> startDates, ArrayList<String> endDates, ArrayList<String> problemDescriptions, ArrayList<String> locations, ArrayList<String> hackathonOrganizers)
    {
        ResultSet rs = null;
        String queryText = "SELECT * FROM hackathon.public.event";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            rs = query.executeQuery();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        /*try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        if(rs != null) {
            try {
                while (rs.next()) {
                    hackathonTitles.add(rs.getString("title"));
                    maxTeamNumbers.add(rs.getInt("maxTeamNumber"));
                    minTeamNumbers.add(rs.getInt("minTeamNumber"));
                    maxTeamSizes.add(rs.getInt("maxTeamSize"));
                    minTeamSizes.add(rs.getInt("minTeamSize"));
                    registrationsStartDates.add(rs.getString("registrationStartDate"));
                    registrationStatuses.add(rs.getBoolean("registrationStatus"));
                    hackathonStatuses.add(rs.getBoolean("hackathonStatus"));
                    startDates.add(rs.getString("startDate"));
                    endDates.add(rs.getString("endDate"));
                    hackathonOrganizers.add(rs.getString("eventorganizerid"));
                    problemDescriptions.add(rs.getString("problemDescription"));
                    locations.add(rs.getString("locationID"));
                }
            }catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
    public void retrieveOrganizersData(ArrayList<String> organizerUsernames, ArrayList<String> organizerPasswords)
    {
        ResultSet rs = null;
        String queryText = "SELECT * FROM hackathon.public.organizer";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            rs = query.executeQuery();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        /*try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        if(rs != null) {
            try {
                while (rs.next()) {
                    organizerUsernames.add(rs.getString("username"));
                    organizerPasswords.add(rs.getString("password"));
                }
            }catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    public void retrieveTeamsData(ArrayList<String> teamNames, ArrayList<Integer> teamFinalProjectRatings, ArrayList<String> teamHackathons)
    {
        ResultSet rs = null;
        String queryText = "SELECT * FROM public.team";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            rs = query.executeQuery();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        /*try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        if(rs != null) {
            try {
                while (rs.next()) {
                   teamNames.add(rs.getString("name"));
                   teamFinalProjectRatings.add(rs.getInt("finalProjectRating"));
                   teamHackathons.add(rs.getString("hackathonID"));
                }
            }catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
    public void retrieveUsersData(ArrayList<String> usernames, ArrayList<String> passwords)
    {
        ResultSet rs = null;
        String queryText = "SELECT * FROM hackathon.public.user";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            rs = query.executeQuery();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        /*try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        if(rs != null) {
            try {
                while (rs.next()) {
                    usernames.add(rs.getString("username"));
                    passwords.add(rs.getString("password"));
                }
            }catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
    public void retrieveParticipantsData(ArrayList<String> participantUsernames, ArrayList<String> participantPasswords, ArrayList<String> participantHackathons, ArrayList<String> participantTeams)
    {
        ResultSet rs = null;
        String queryText = "SELECT * FROM hackathon.public.participant";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            rs = query.executeQuery();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        /*try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        if(rs != null) {
            try {
                while (rs.next()) {
                    participantUsernames.add(rs.getString("username"));
                    participantPasswords.add(rs.getString("password"));
                    participantHackathons.add(rs.getString("hackathonID"));
                    participantTeams.add(rs.getString("teamID"));
                }
            }catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        }

    }
    public void retrieveJudgesData(ArrayList<String> judgeUsernames, ArrayList<String> judgePasswords)
    {
        ResultSet rs = null;
        String queryText = "SELECT * FROM hackathon.public.judge";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            rs = query.executeQuery();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        /*try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        if(rs != null) {
            try {
                while (rs.next()) {
                    judgeUsernames.add(rs.getString("username"));
                    judgePasswords.add(rs.getString("password"));
                }
            }catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        }

    }
    public void retrieveLeadersData(ArrayList<String> leaderUsernames, ArrayList<String> leaderPasswords, ArrayList<String> leaderHackathons, ArrayList<String> leaderTeams)
    {
        ResultSet rs = null;
        String queryText = "SELECT * FROM hackathon.public.leader";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            rs = query.executeQuery();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        /*try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        if(rs != null) {
            try {
                while (rs.next()) {
                    leaderUsernames.add(rs.getString("username"));
                    leaderPasswords.add(rs.getString("password"));
                    leaderHackathons.add(rs.getString("hackathonID"));
                    leaderTeams.add(rs.getString("teamID"));
                }
            }catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
    public void retrieveLocationsData(ArrayList<String> locationAddresses, ArrayList<String> locationStreets, ArrayList<Integer> locationCAPS, ArrayList<Integer> locationDoorNumbers)
    {
        ResultSet rs = null;
        String queryText = "SELECT * FROM hackathon.public.location";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            rs = query.executeQuery();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        /*try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        if(rs != null) {
            try {
                while (rs.next()) {
                   locationAddresses.add(rs.getString("address"));
                   locationStreets.add(rs.getString("street"));
                   locationCAPS.add(rs.getInt("cap"));
                   locationDoorNumbers.add(rs.getInt("doorNumber"));
                }
            }catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
    public void retrieveOpinionsData(ArrayList<String> opinionDescriptions, ArrayList<String> opinionJudges, ArrayList<String> opinionUpdates)
    {
        ResultSet rs = null;
        String queryText = "SELECT * FROM hackathon.public.opinion";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            rs = query.executeQuery();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        /*try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        if(rs != null) {
            try {
                while (rs.next()) {
                    opinionDescriptions.add(rs.getString("description"));
                    opinionJudges.add(rs.getString("judgeID"));
                    opinionUpdates.add(rs.getString("updateID"));
                }
            }catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
    public void retrieveUpdatesData(ArrayList<String> updateTitles, ArrayList<String> updateDescriptions, ArrayList<String> updateReleaseNumbers, ArrayList<String> updateTeams)
    {
        ResultSet rs = null;
        String queryText = "SELECT * FROM hackathon.public.update";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            rs = query.executeQuery();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        /*try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        if(rs != null) {
            try {
                while (rs.next()) {
                   updateTitles.add(rs.getString("title"));
                   updateDescriptions.add(rs.getString("description"));
                   updateReleaseNumbers.add(rs.getString("releaseNumber"));
                   updateTeams.add(rs.getString("teamID"));
                }
            }catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
    public void retrieveInvitesData(ArrayList<String> inviteTeams, ArrayList<String> inviteHackathons, ArrayList<String> inviteSenders, ArrayList<String> inviteReceivers, ArrayList<String> inviteTexts)
    {
        ResultSet rs = null;
        String queryText = "SELECT * FROM hackathon.public.invite";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            rs = query.executeQuery();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        /*try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        if(rs != null) {
            try {
                while (rs.next()) {
                   inviteTexts.add(rs.getString("text"));
                   inviteHackathons.add(rs.getString("hackathonid"));
                   inviteSenders.add(rs.getString("senderid"));
                   inviteReceivers.add(rs.getString("receiverid"));
                   inviteTeams.add(rs.getString("teamid"));

                }
            }catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
    public void retrieveHackathonJudgesData(ArrayList<String> assocJudgesUsernames, ArrayList<String> assocHackathonsTitles)
    {
        ResultSet rs = null;
        String queryText = "SELECT * FROM hackathon.public.hackathonjudge";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            rs = query.executeQuery();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        /*try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        if(rs != null) {
            try {
                while (rs.next()) {
                    assocJudgesUsernames.add(rs.getString("judgeID"));
                    assocHackathonsTitles.add(rs.getString("hackathonID"));
                }
            }catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    public void retrieveRatings(ArrayList<Integer> ratingValues, ArrayList<String> teamNames)
    {
        ResultSet rs = null;
        String queryText = "SELECT * FROM hackathon.public.rating";
        try {
            PreparedStatement query = connection.prepareStatement(queryText);
            rs = query.executeQuery();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        /*try {
            connection.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        if(rs != null) {
            try {
                while (rs.next()) {
                    ratingValues.add(rs.getInt("value"));
                    teamNames.add(rs.getString("teamID"));
                }
            }catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

}
