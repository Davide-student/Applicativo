package model;



public class Opinion
{
    private String description;
    private Judge judge;
    private Update update;

    //Costruttore
    public Opinion(Judge judge, String description, Update update)
    {
        this.update = update;
        this.judge = judge;
        this.description = description;
    }
    public String getDescription()
    {
        return this.description;
    }
    public Judge getJudge()
    {
        return this.judge;
    }

}