package model;

import java.util.LocalDate;
import java.io.File;
import java.util.ArrayList;

public class Hackathon
{
	private int code;	//Codice identificativo dell hackathon
	private String title[30];
	private int maxTeamNumber;
	private int minTeamNumber;
	private Date registrationStartDate;
	private Date startDate;
	private Date endDate;
	private maxNumberParticipants;
	private numberParticipants;
	private File problemDescription;	//Valorizzato da un giudice
	private ArrayList<Judge> judgesList;	//Lista dei giudici che si occupano dell'hackathon
	private organizer eventOrganizer;	//Indica l'organizzatore dell'hackathon
	private Location location; //Sede fisica in cui si tiene l'hackathon
}