package worker;

import content.Game;

public class GameCritic extends Worker{
	
	private int shift;
	private int opinion;
	
	public GameCritic() {
		this(8,0);
	}
	
	public GameCritic(int shift, int opinion) {
		this.shift = shift;
		this.opinion = opinion;	
	}
	
	public int getShift() {
		return shift;
	}
	
	public void setShift(int shift) {
		this.shift = shift;
	}
	
	public int getOpinion() {
		return opinion;
	}
	
	public void setOpinion(int opinion) {
		this.opinion = opinion;
	}
	
	public String toString() {
		return "Shift: "+getShift()+"Opinion: "+getOpinion();
	}
	
	public boolean equals(Object other){
        if (other == null){
            return false;
        }
        else if (getClass() != other.getClass()){
            return false;
        }
        else {
        GameCritic otherGameCritic = (GameCritic) other;
        return (this.shift==otherGameCritic.shift && this.opinion==otherGameCritic.opinion);}

	}
	
	public int rateContent(Game game) {
		return (int)(game.calculateCritic(this.opinion));
		
	}
}
