public class Opinion
{
    private File description;
    private String judgeUsername;   //All'opinione viene associato solo "username" di giudice e non un reference di tipo "Judge".

    //Costruttore
    public Opinion(String judgeUsername, File description)
    {
        this.judgeUsername = judgeUsername;
        this.description = description;
    }

}