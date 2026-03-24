package model;
import java.util.Comparator;

//Questa classe è utilizzata per effettuare l'ovveride del metodo di "Comparator" "compare()"
public class teamsComparator implements Comparator<Team> {
    @Override   //Override di "compare(Type, Type)"
    //Il compare viene effettuate rispetto al punteggio finale dei team.
    public int compare(Team o1, Team o2) {
        if(o1.getFinalProjectRating() > o2.getFinalProjectRating())
        {
            return 1;
        }else if (o1.getFinalProjectRating() < o2.getFinalProjectRating())
        {
            return -1;
        }else
        {
            return 0;
        }
    }
}
