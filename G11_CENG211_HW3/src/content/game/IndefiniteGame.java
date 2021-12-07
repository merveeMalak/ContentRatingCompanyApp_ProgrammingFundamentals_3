package content.game;

public class IndefiniteGame extends Game {

    //default constructor
    public IndefiniteGame(){
        super();
        this.setContentId(1);
    }

    //full arguments constructor
    public IndefiniteGame(int arrivalDay, int contentId, String name, int duration, int averageRating){
        super(arrivalDay, contentId, name, duration, averageRating);
    }

    //takes all arguments as a string array
    public IndefiniteGame(String[] info){
        super(info);
    }

    //copy constructor
    public IndefiniteGame(IndefiniteGame indefiniteGame){
        this(indefiniteGame.getArrivalDay(), indefiniteGame.getContentId(), indefiniteGame.getName(),indefiniteGame.getDuration(),indefiniteGame.getAverageRating());
    }


    @Override
    public int calculateCritic(int criticOption) {
        return (int) (getAverageRating() + ((10 - getDuration()) * 0.25)  + criticOption);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public boolean equals(Object other){
        return super.equals(other);
    }
}
