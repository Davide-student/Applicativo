package DAO;
import java.util.ArrayList;

public interface DAORetrieverI { //DAOInterface

    public void retrieveHackathonsData(ArrayList<String> hackathonTitles, ArrayList<Integer> maxTeamNumbers, ArrayList<Integer> minTeamNumbers, ArrayList<Integer> maxTeamSizes, ArrayList<Integer> minTeamSizes, ArrayList<String> registrationsStartDates, ArrayList<Boolean> registrationStatuses, ArrayList<Boolean> hackathonStatuses, ArrayList<String> startDates, ArrayList<String> endDates, ArrayList<String> problemDescriptions, ArrayList<String> hackathonLocations, ArrayList<String> hackathonOrganizers);

    public void retrieveOrganizersData(ArrayList<String> organizerUsernames, ArrayList<String> organizerPasswords);

    public void retrieveTeamsData(ArrayList<String> teamNames, ArrayList<Integer> teamFinalProjectRatings, ArrayList<String> teamHackathons);

    public void retrieveUsersData(ArrayList<String> userUsernames, ArrayList<String> userPasswords);

    public void retrieveParticipantsData(ArrayList<String> participantUsernames, ArrayList<String> participantPasswords, ArrayList<String> participantHackathons, ArrayList<String> participantTeams);

    public void retrieveJudgesData(ArrayList<String> judgeUsernames, ArrayList<String> judgePasswords);

    public void retrieveLeadersData(ArrayList<String> leaderUsernames, ArrayList<String> leaderPasswords, ArrayList<String> leaderHackathons, ArrayList<String> leaderTeams);

    public void retrieveLocationsData(ArrayList<String> locationAddresses, ArrayList<String> locationStreets, ArrayList<Integer> locationCAPS, ArrayList<Integer> locationDoorNumbers);

    public void retrieveOpinionsData(ArrayList<String> opinionDescriptions, ArrayList<String> opinionJudges, ArrayList<String> opinionUpdates);

    public void retrieveUpdatesData(ArrayList<String> updateTitles, ArrayList<String> updateDescriptions, ArrayList<String> updateReleaseNumbers, ArrayList<String> updateTeams);

    public void retrieveInvitesData(ArrayList<String> inviteTeams, ArrayList<String> inviteHackathons, ArrayList<String> inviteSenders, ArrayList<String> inviteReceivers, ArrayList<String> inviteTexts);

    public void retrieveHackathonJudgesData(ArrayList<String> assocJudgesUsernames, ArrayList<String> assocHackathonsTitles);

    public void retrieveRatings(ArrayList<Integer> ratingValues, ArrayList<String> teamNames);
    public void closeConnection();
}