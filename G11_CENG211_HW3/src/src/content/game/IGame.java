package content.game;

import content.IContent;

public interface IGame extends IContent {
	
	/**
     * calculates game rating according to criticOpinion and returns as a int
     */
    public int calculateCritic(int criticOption);

    public int getEvaluateRate();

    void setEvaluatedRate(int evaluatedRate);

}
