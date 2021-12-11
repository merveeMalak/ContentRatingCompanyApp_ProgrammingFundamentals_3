package content.movie;

import content.IContent;

public interface IMovie  extends IContent {
	
	/**
     * calculates movie rating according to criticOpinion and returns as a double
     */
    public double calculateCritic(double criticOption);

    public int getYear();
	
    void setEvaluatedRate(double evaluatedRate);

    public double getEvaluateRate();

}
