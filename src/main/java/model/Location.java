package model;


public class Location
{
	private String address[40];
	private String street[40];
	private int CAP;
	private int doorNumber;	//Indica l'interno in un edificio
    private Hackathon currentHackathon; //Questo attributo viene valorizzato mentre la sede è occupata per un                                                                 //hackathon. Quando la sede non è più occupata, viene valorizzato null e 
                                        //mostrato nella lista delle sedi disponibili agli organizzatori.

    //Costruttore per istanze di "Location"
    public Location(String address, String street, int CAP, int doorNumber) 
    {
        this.address = address;
        this.street = street;
        this.CAP = CAP;
        this.doorNumber = doorNumber;
        this.currentHackathon = null;
    }
    //getter e setter
    public void setCurrentHackathon(Hackathon currentHackathon) //Quando la sede viene occupata, "currentHackathon" viene valorizzato.
    {  
        this.currentHackathon = currentHackathon;
    }
    public String getAddress()
    {
        return this.address;
    }
    public String getStreet()
    {
        return this.street;
    }
    public int getCAP()
    {
        return this.CAP;
    }
    public int doorNumber()
    {
        return this.doorNumber;
    }
    public Hackathon getcurrentHackathon()
    {
        return this.currentHackathon;
    }
    
}