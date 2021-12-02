package models.game;

import models.Content;

public class IndefiniteGame extends Content {
    public IndefiniteGame(){
        super();
        this.setContentId(1);
    }

    public IndefiniteGame(int arrivalDay, int contentId, String name, int duration, int averageRating){
        super(arrivalDay, contentId, name, duration, averageRating);
    }
/*
    public IndefiniteGame(IndefiniteGame indefiniteGame){
        super(indefiniteGame);
    }

 */
    @Override
    public double calculateCritic(int criticOption) {
        return getAverageRating() + ((10 - getDuration()) * 0.25)  + criticOption;
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
