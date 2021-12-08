package content.game;

import content.Content;

public abstract class Game extends Content implements IGame {
	
	 private int averageRating; //holds averageRating as a int
	    private int evaluateRate;  //holds evaluateRate as a int

	    //default constructor
	    public Game(){
	        super();
	        this.averageRating = 0;
	        this.evaluateRate = 0;
	    }

	    //full arguments constructor
	    public Game(int arrivalDay, int contentId, String name, int duration, int averageRating){
	        super(arrivalDay,contentId,name,duration);
	        this.averageRating = 0;
	        this.evaluateRate = 0;
	    }

	    //takes all arguments as a string array
	    public Game(String[] info){
	            this(Integer.parseInt(info[0]), Integer.parseInt(info[1]), info[2], Integer.parseInt(info[3]), Integer.parseInt(info[4]));
	    }

	    //starts getter methods
	    public int getAverageRating(){
	        return this.averageRating;
	    }

	    public int getEvaluateRate() {
	        return evaluateRate;
	    }
	    //ends getter methods


	    @Override
	    public void setEvaluatedRate(int evaluatedRate) {
	        this.evaluateRate = evaluatedRate;
	    }

	    public boolean equals(Object other){
	        if (other == null){
	            return false;
	        }
	        else if (getClass() != other.getClass()){
	            return false;
	        }
	        Game otherGame = (Game) other;
	        return ((getArrivalDay() == otherGame.getArrivalDay()) &&
	                getContentId() == otherGame.getContentId() &&
	                getName().equals(otherGame.getName()) &&
	                getDuration() == otherGame.getDuration() &&
	                this.averageRating == otherGame.averageRating &&
	                this.evaluateRate == otherGame.evaluateRate);
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

	    public abstract int calculateCritic(int criticOpinion);

}
