package models.game;

import models.Content;

public class StoryGame extends Game {
    public StoryGame(){
        super();
        this.setContentId(2);
    }

    public StoryGame(int arrivalDay, int contentId, String name, int duration, int averageRating){
        super(arrivalDay, contentId, name, duration, averageRating);
    }
    public StoryGame(String[] info){
        super(info);
    }
/*
    public StoryGame(StoryGame storyGame){
        super(storyGame);
    }

 */
    @Override
    public double calculateCritic(double criticOption) {
        return getAverageRating() + (getDuration() * 0.25)  + criticOption;
    }
    @Override
    public String toString() {
        return super.toString();
    }

    public boolean equals(Object other){
        return super.equals(other);
    }
}
