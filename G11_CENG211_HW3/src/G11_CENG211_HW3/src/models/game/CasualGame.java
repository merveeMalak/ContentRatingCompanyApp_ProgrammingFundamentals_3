package models.game;

import models.Content;

public class CasualGame extends Game {

    public CasualGame(){
        super();
        this.setContentId(3);
    }

    public CasualGame(int arrivalDay, int contentId, String name, int duration, int averageRating){
        super(arrivalDay, contentId, name, duration, averageRating);
    }

    public CasualGame(String[] info){
        super(info);
    }
/*
    public CasualGame(CasualGame casualGame){
        super(casualGame);
    }

 */
    @Override
    public double calculateCritic(double criticOption) {
        return getAverageRating() + ((getDuration() - 3) * 3) + criticOption;
    }
    @Override
    public String toString() {
        return super.toString();
    }
    public boolean equals(Object other){
        return super.equals(other);
    }

}
