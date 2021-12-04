package worker;

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
	
	




	

}
