package simulation;

public interface ISimulation {
	
	void updateStacks(int dayNumber);
	void createCriticQueues();
	void simulateFiveDays();
	void evaluateMovies();
	void evaluateNewGames();
	void continueEvaluatingGames();

}
