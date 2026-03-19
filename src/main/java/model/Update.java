package model;
import java.io.File;

public class Update
{
    private File description;   //Contiene la descrizione dell'update
    private String title;
    private int releaseNumber;  //Indica la versione associata all'update, da intendersi SOLO come numero intero (La versione viene specificata dal leader)
    private ArrayList<Opinion> opinions;
    

    //Construttore
    //"reaseNumber" potrebbe anche essere una string a cui viene concatenato il "Numero di version".
    public Update(String title, File description, int releaseNumber /*String releaseNumber*/)
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
    public File getDescription()
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