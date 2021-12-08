package simulation;

import content.game.IGame;
import worker.game_critic.IGameCritic;

public interface ISimulation {
	
	//calls needed methods in the needed order to simulate 5 days of the company
	void simulateFiveDays();
		
	//creates game and movie critics and adds them to related queues
	void createCriticQueues();
	
	//updates the content stacks for given day
	void updateStacks(int dayNumber);
	
	//assigns movies at the stack to the critics at the queue
    //critics evaluates the movies
	void evaluateMovies();
	
	//if there are any games to rate and available critics, assigns the games to critics
	void evaluateNewGames();
	
	//makes critics continue their evaluations from where they left of
	void continueEvaluatingGames();
	
	//evaluates casual games
	void evaluateCasualGame(IGame gameToRate,IGameCritic gameCriticToRate);
	
	//evaluates indefinite games
	void evaluateIndefiniteGame(IGame gameToRate,IGameCritic gameCriticToRate);
	
	//evaluates story games
	void evaluateStoryGame(IGame gameToRate,IGameCritic gameCriticToRate);
	
	//if evaluation is done, finishes up the necessary steps
	void organizeEvaluations(boolean evaluationStatus,IGame gameToRate, IGameCritic gameCriticToRate);
	
	//makes necessary arrangements if the critic has more than enough time for the job at hand
	void sufficienCriticShift(IGameCritic gameCriticToRate, IGame gameToRate, int duration);
	
	//makes necessary arrangements if the critic hasn't got enough time for the job at hand
	void insufficienCriticShift(IGameCritic gameCriticToRate, IGame gameToRate, int duration);
	
	//makes necessary arrangements if the critic has just enough time for the job at hand
	void equalCriticShift(IGameCritic gameCriticToRate, IGame gameToRate);
	
	//redirects critics to proper places according to their shifts
	void redirectCritics(int isShiftEnded, IGame gameToRate, IGameCritic gameCriticToRate);
	
	//prints evaluated games of the day and clears the daily lists after
	void printDailyEvaluatedGames();
	
	//resets shifts of the critics for the new day
	void resetShifts();
	
	
}
