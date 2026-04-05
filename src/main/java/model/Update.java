package model;

import java.util.ArrayList;

public class Update
{
    private String description;   //Contiene la descrizione dell'update
    private String title;
    private String releaseNumber;  //Indica la versione associata all'update, da intendersi SOLO come numero intero (La versione viene specificata dal leader)
    private Team team;
    private ArrayList<Opinion> opinions;
    

    //Construttore
    //"reaseNumber" potrebbe anche essere una string a cui viene concatenato il "Numero di version".
    public Update(String title, String description, String releaseNumber, Team team /*String releaseNumber*/)
    {
        this.title = title;
        this.description = description;
        this.releaseNumber = releaseNumber;
        this.opinions = new ArrayList<Opinion>();
        this.team = team;
    }

    //metodi setter e getter
    public String getTitle()
    {
        return this.title;
    }
    public String getDescription()
    {
        return this.description;
    }
    public String getRelease()
    {
        return this.releaseNumber;
    }

    public void addOpinion(Opinion opinion)   //"text" indica il testo incluso nell'opinione del giudice
    {
        this.opinions.add(opinion);
    }
    public ArrayList<Opinion> getOpinions()
    {
        return this.opinions;
    }
    

}