package model;



public class Opinion
{
    private String description;
    private String judgeUsername;   //All'opinione viene associato solo "username" di giudice e non un reference di tipo "Judge".

    //Costruttore
    public Opinion(String judgeUsername, String description)
    {
        this.judgeUsername = judgeUsername;
        this.description = description;
    }

}