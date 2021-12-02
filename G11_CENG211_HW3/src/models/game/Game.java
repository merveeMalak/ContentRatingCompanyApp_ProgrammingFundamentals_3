package models.game;

import models.Content;

public abstract class Game extends Content {

    public Game(){
        super();
    }
    public Game(int arrivalDay, int contentId, String name, int duration, int averageRating){
        super(arrivalDay,contentId,name,duration,averageRating);
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
