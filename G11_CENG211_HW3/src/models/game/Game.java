package models.game;

import models.Content;
import models.movie.Movie;

public abstract class Game extends Content {

    public Game(){
        super();
    }

    public Game(int arrivalDay, int contentId, String name, int duration, int averageRating){
        super(arrivalDay,contentId,name,duration,averageRating);
    }

    public Game(String[] info){
        super(Integer.parseInt(info[0]), Integer.parseInt(info[1]), info[2], Integer.parseInt(info[3]), Double.parseDouble(info[4]));
    }

    public boolean equals(Object other){
        if (other == null){
            return false;
        }
        else if (getClass() != other.getClass()){
            return false;
        }
        Game otherMovie = (Game) other;
        return ((getArrivalDay() == otherMovie.getArrivalDay()) &&
                getContentId() == otherMovie.getContentId() &&
                getName().equals(otherMovie.getName()) &&
                getDuration() == otherMovie.getDuration() &&
                getAverageRating() == otherMovie.getAverageRating() &&
                getEvaluatedRate() == otherMovie.getEvaluatedRate());
    }

    @Override
    public String toString() {
        String hour;
        if (getDuration() == 1){
            hour = "hour";
        }else{
            hour = "hours";
        }
        return super.toString() +  " *Duration: " + getDuration() + " " + hour ;
    }
}
