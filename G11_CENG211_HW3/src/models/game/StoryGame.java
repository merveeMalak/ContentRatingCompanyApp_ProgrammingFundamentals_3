package models.game;

import models.Content;

public class StoryGame extends Content {
    public StoryGame(){
        super();
        this.setContentId(2);
    }

    public StoryGame(int arrivalDay, int contentId, String name, int duration, int averageRating){
        super(arrivalDay, contentId, name, duration, averageRating);
    }
/*
    public StoryGame(StoryGame storyGame){
        super(storyGame);
    }

 */
    @Override
    public double calculateCritic(int criticOption) {
        return getAverageRating() + (getDuration() * 0.25)  + criticOption;
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
