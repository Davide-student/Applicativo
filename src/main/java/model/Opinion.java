package model;



public class Opinion
{
    private String description;
    private Judge judge;

    //Costruttore
    public Opinion(Judge judge, String description)
    {
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