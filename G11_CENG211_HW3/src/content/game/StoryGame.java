package content.game;


public class StoryGame extends Game {

    //default constructor
    public StoryGame(){
        super();
        this.setContentId(2);
    }

    //full arguments constructor
    public StoryGame(int arrivalDay, int contentId, String name, int duration, int averageRating){
        super(arrivalDay, contentId, name, duration, averageRating);
    }

    //takes all arguments as a string array
    public StoryGame(String[] info){
        super(info);
    }

    //copy constructor
    public StoryGame(StoryGame storyGame){
        this(storyGame.getArrivalDay(),storyGame.getContentId(),storyGame.getName(),storyGame.getDuration(),storyGame.getAverageRating());
    }


    @Override
    public int calculateCritic(int criticOption) {
        return (int) (getAverageRating() + (getDuration() * 0.25)  + criticOption);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public boolean equals(Object other){
        return super.equals(other);
    }
}
