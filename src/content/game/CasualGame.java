package content.game;

public class CasualGame extends Game {
	
	//default constructor
    public CasualGame(){
        super();
        this.setContentId(3);
    }

    //full arguments constructor
    public CasualGame(int arrivalDay, int contentId, String name, int duration, int averageRating){
        super(arrivalDay, contentId, name, duration, averageRating);
    }

    //takes all arguments as a string array
    public CasualGame(String[] info){
        super(info);
    }

    //copy constructor
    public CasualGame(CasualGame casualGame){
        this(casualGame.getArrivalDay(),casualGame.getContentId(),casualGame.getName(),casualGame.getDuration(),casualGame.getAverageRating());
    }


    @Override
    public int calculateCritic(int criticOption) {
        return (int) (getAverageRating() + ((getDuration() - 3) * 3) + criticOption);
    }



    @Override
    public String toString() {
        return super.toString();
    }
    public boolean equals(Object other){
        return super.equals(other);
    }


}
