package models.game;

import models.Content;

public class CasualGame extends Content {

    public CasualGame(){
        super();
        this.setContentId(3);
    }

    public CasualGame(int arrivalDay, int contentId, String name, int duration, int averageRating){
        super(arrivalDay, contentId, name, duration, averageRating);
    }
/*
    public CasualGame(CasualGame casualGame){
        super(casualGame);
    }

 */
    @Override
    public double calculateCritic(int criticOption) {
        return getAverageRating() + ((getDuration() - 3) * 3) + criticOption;
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
