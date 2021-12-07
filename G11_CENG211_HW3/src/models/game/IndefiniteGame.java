package models.game;

public class IndefiniteGame extends Game {
    public IndefiniteGame(){
        super();
        this.setContentId(1);
    }

    public IndefiniteGame(int arrivalDay, int contentId, String name, int duration, int averageRating){
        super(arrivalDay, contentId, name, duration, averageRating);
    }

    public IndefiniteGame(String[] info){
        super(info);
    }
/*
    public IndefiniteGame(IndefiniteGame indefiniteGame){
        super(indefiniteGame);
    }

 */
    @Override
    public double calculateCritic(double criticOption) {
        return getAverageRating() + ((10 - getDuration()) * 0.25)  + criticOption;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public boolean equals(Object other){
        return super.equals(other);
    }
}
