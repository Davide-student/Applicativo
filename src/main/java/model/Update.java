package model;

import java.util.ArrayList;

public class Update
{
    private String description;   //Contiene la descrizione dell'update
    private String title;
    private int releaseNumber;  //Indica la versione associata all'update, da intendersi SOLO come numero intero (La versione viene specificata dal leader)
    private ArrayList<Opinion> opinions;
    

    //Construttore
    //"reaseNumber" potrebbe anche essere una string a cui viene concatenato il "Numero di version".
    public Update(String title, String description, int releaseNumber /*String releaseNumber*/)
    {
        this.title = title;
        this.description = description;
        this.releaseNumber = releaseNumber;
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
    public int getReleaseNumber()
    {
        return this.releaseNumber;
    }

    public void addOpinion(Opinion opinion)   //"text" indica il testo incluso nell'opinione del giudice
    {
        this.opinions.add(opinion);
    }
    

}