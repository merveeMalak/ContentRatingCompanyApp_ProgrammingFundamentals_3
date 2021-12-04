package worker;

public class MovieCritic extends Worker {
	
	private double opinion;

	public MovieCritic(double opinion) {
		this.opinion = opinion;
	}
	
	public MovieCritic() {
		this(0.0);
	}

	public double getOpinion() {
		return opinion;
	}
	
	public void setOpinion(double opinion) {
		this.opinion = opinion;
	}
	
	

}
