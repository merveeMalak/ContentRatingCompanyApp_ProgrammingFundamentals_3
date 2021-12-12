package simulation;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import content.game.IGame;
import content.movie.IMovie;
import content.movie.Movie;
import fileIO.FileIO;
import worker.game_critic.GameCritic;
import worker.game_critic.IGameCritic;
import worker.movie_critic.IMovieCritic;
import worker.movie_critic.MovieCritic;

public class Simulation implements ISimulation {
	
	public Simulation() {}

    //empty stacks for contents are created
    Stack<IGame> gameStack = new Stack<IGame>();
    Stack<IMovie> movieStack = new Stack<IMovie>();

    //empty queues for critics are created
    Queue<IGameCritic> gameCriticQueue = new LinkedList<>();
    Queue<IMovieCritic> movieCriticQueue = new LinkedList<>();

    //empty linked lists are created for the evaluated content
    List<IMovie> evaluatedMovies = new LinkedList<>();
    List<IGame> evaluatedGames = new LinkedList<>();
    
    //calls needed methods in the needed order to simulate 5 days of the company
    public void simulateFiveDays() {
        createCriticQueues();
        for(int i=1; i<6; i++) {
        	System.out.println(i + ". day:");
            updateStacks(i);
            evaluateMovies();
            continueEvaluatingGames();
            evaluateNewGames();
            printDailyEvaluatedGames();
            resetShifts();
        }
    }

    //creates game and movie critics and adds them to related queues
    public void createCriticQueues() {

        //movie critics are created
        IMovieCritic movieCritic1 = new MovieCritic(1,0.1);
        IMovieCritic movieCritic2 = new MovieCritic(2,-0.2);
        IMovieCritic movieCritic3 = new MovieCritic(3, 0.3);

        //movie critics are added to the movie critic queue
        movieCriticQueue.add(movieCritic1);
        movieCriticQueue.add(movieCritic2);
        movieCriticQueue.add(movieCritic3);

        //game critics are created
        IGameCritic gameCritic1 = new GameCritic(1,8,5);
        IGameCritic gameCritic2 = new GameCritic(2,8,9);
        IGameCritic gameCritic3 = new GameCritic(3,8,-3);
        IGameCritic gameCritic4 = new GameCritic(4,8,2);
        IGameCritic gameCritic5 = new GameCritic(5,8,-7);

        //game critics are added to game critic queue
        gameCriticQueue.add(gameCritic1);
        gameCriticQueue.add(gameCritic2);
        gameCriticQueue.add(gameCritic3);
        gameCriticQueue.add(gameCritic4);
        gameCriticQueue.add(gameCritic5);
    }

    //updates the content stacks for given day
    public void updateStacks(int dayNumber) {

        FileIO file = new FileIO();
        movieStack = file.getIndexDayOfMovie(dayNumber);
        gameStack = file.getIndexOfDayGame(dayNumber);
        for (int x=0; x<gameStack.size();x++) {
        	System.out.println(gameStack.get(x).getName());
        }
        
    }

    //assigns movies at the stack to the critics at the queue
    //critics evaluates the movies
    public void evaluateMovies() {

        for(int x = 0; x<movieCriticQueue.size(); x++) {

            if (!(movieStack.isEmpty())) {
                IMovie movieToRate = movieStack.pop();
                IMovieCritic movieCriticToRate = movieCriticQueue.poll();
                double evaluatedRate = movieCriticToRate.rateContent(movieToRate);
                movieToRate.setEvaluatedRate(evaluatedRate);
                evaluatedMovies.add(movieToRate);
                movieCriticQueue.add(movieCriticToRate);
                System.out.println(movieCriticToRate.getCriticId() + ". movie critic evaluated " + movieToRate.getName());
            }
            else {break;}
        }
    }
    
    //lists for unfinished evaluations: critics who will continue the other day and the games they are evaluating
    Queue<IGameCritic> gameCriticsInProgress = new LinkedList<>();
    List<IGame> gamesInProgress = new LinkedList<>();
    
    //list of critics who reached the maximum number of hours to work
    List<IGameCritic> gameCriticsEndedShifts = new LinkedList<>();
    
    //lists of evaluated games and critic id's belonging the day at hand
    List<IGame> dailyEvaluatedGames = new LinkedList<>();
    List<Integer> dailyEvaluatedCritics = new LinkedList<>();
    
    //if there are any games to rate and available critics, assigns the games to critics
    public void evaluateNewGames() {
    	
    	if(!(gameStack.isEmpty())) {
    		
    		for (int i=0; i<gameStack.size(); i++) {
    			
    			if(!(gameCriticQueue.isEmpty())) {
    				
    				IGame gameToRate = gameStack.pop();
    				IGameCritic gameCriticToRate = gameCriticQueue.poll();
    				
    				System.out.println(gameCriticToRate.getCriticId()+". game critic works on "+gameToRate.getName());
    				
    				switch (gameToRate.getContentId()) {
    				case 1:
    					int duration1 = 4;
    					evaluateGame(gameToRate, gameCriticToRate,duration1);
    					break;
    				case 2:
    					int duration2 = gameToRate.getDuration();
    					evaluateGame(gameToRate, gameCriticToRate,duration2);
    					break;
    				case 3:
    					int duration3 = gameToRate.getDuration()*3;
    					evaluateGame(gameToRate, gameCriticToRate,duration3);
    					break;
    				default: 
    					break;	
    				}
    			}	
			}
    	}
    }
    
    //if evaluation is done, finishes up the necessary steps
    private void organizeEvaluations(boolean evaluationStatus,IGame gameToRate, IGameCritic gameCriticToRate) {
    	if (evaluationStatus==true) {
    		dailyEvaluatedGames.add(gameToRate);
    		dailyEvaluatedCritics.add(gameCriticToRate.getCriticId());
    		evaluatedGames.add(gameToRate);
    	}
    }
    
    //redirects critics to proper places according to their shifts
    private void redirectCritics(int isShiftEnded, IGame gameToRate, IGameCritic gameCriticToRate) {
    	
    	switch (isShiftEnded) {
    	case 0://still has hours to work
    		gameCriticQueue.add(gameCriticToRate);
    		break;
    	case 1://critic's shift is ended
    		gameCriticsEndedShifts.add(gameCriticToRate);
    		break;
    	case 2://will continue the same work the other day
    		gameCriticsInProgress.add(gameCriticToRate);
    		gamesInProgress.add(gameToRate);
    		break;
    	default: break;
    	}
    }
    
    //evaluates games, evaluatinStatus==true means evaluation is done
    //isShift ended refers to shift status of the critic
    private void evaluateGame(IGame gameToRate,IGameCritic gameCriticToRate,int duration) {
    	
		boolean evaluationStatus = false;
		int isShiftEnded = 0;
		
		if (gameCriticToRate.getShift()>duration) {
			gameCriticToRate.setShift(gameCriticToRate.getShift()-duration);
			int evaluatedRate = gameCriticToRate.rateContent(gameToRate);
			gameToRate.setEvaluatedRate(evaluatedRate);
			evaluationStatus = true;
		}
		else if (gameCriticToRate.getShift()==duration) {
			int evaluatedRate = gameCriticToRate.rateContent(gameToRate);
			gameToRate.setEvaluatedRate(evaluatedRate);
			evaluationStatus = true;
			isShiftEnded = 1;
		}
		else {
			gameCriticToRate.setShift(gameCriticToRate.getShift()-duration);
			isShiftEnded = 2;
		}
		
		organizeEvaluations(evaluationStatus,gameToRate,gameCriticToRate);
		redirectCritics(isShiftEnded,gameToRate,gameCriticToRate);

    }

    //makes critics continue their evaluations from where they left of
    public void continueEvaluatingGames() {
    	
    	if (!(gamesInProgress.isEmpty())) {
    		
    		List<IGame> tempGamesInProgress = gamesInProgress;
    		gamesInProgress.clear();
    		
    		for (IGame game: tempGamesInProgress) {
        		
        		IGame gameToContinue = game;
        		IGameCritic criticToContinue = gameCriticsInProgress.poll();
        		
        		System.out.println(criticToContinue.getCriticId()+". game critic works on "+gameToContinue.getName());
        		
        		boolean evaluationStatus = false;
        		int isShiftEnded = 0;
        		
        		if (criticToContinue.getShift()>0) {
        			int evaluatedRate = criticToContinue.rateContent(gameToContinue);
        			gameToContinue.setEvaluatedRate(evaluatedRate);
        			evaluationStatus = true;
        		}
        		else if (criticToContinue.getShift()==0) {
        			int evaluatedRate = criticToContinue.rateContent(gameToContinue);
        			gameToContinue.setEvaluatedRate(evaluatedRate);
        			evaluationStatus = true;
        			isShiftEnded = 1;
        		}
        		else {
        			isShiftEnded = 2;
        		}
        		
        		organizeEvaluations(evaluationStatus,gameToContinue,criticToContinue);
        		redirectCritics(isShiftEnded,gameToContinue,criticToContinue);
        	}
    		
    	}
    }  
    
    //prints evaluated games of the day and clears the daily lists after
    public void printDailyEvaluatedGames() {

    	for (int i=0; i<dailyEvaluatedGames.size();i++) {
    		int criticId = dailyEvaluatedCritics.get(i);
    		System.out.println(criticId+". game critic evaluated "+dailyEvaluatedGames.get(i).getName());
    	}
    	dailyEvaluatedCritics.clear();
		dailyEvaluatedGames.clear();
    }
    
    //resets shifts of the critics for the new day
    public void resetShifts() {
    	
    	Queue<IGameCritic> tempGameCritics = new LinkedList<>();
    	for(int i=0; i<gameCriticsInProgress.size(); i++) {   		
    		IGameCritic gameCritic = gameCriticsInProgress.poll();
    		gameCritic.setShift(gameCritic.getShift()+8);
    		tempGameCritics.add(gameCritic);
    	}
    	gameCriticsInProgress = tempGameCritics;
    	
    	for (int i=0;i<gameCriticsEndedShifts.size();i++) {
    		gameCriticQueue.add(gameCriticsEndedShifts.get(i));
    	}
    	
    	List<IGameCritic> tempGameCritic = new LinkedList<>();
		while (!(gameCriticQueue.isEmpty())) {
			tempGameCritic.add(gameCriticQueue.poll());
		}
		
		for (int i = 0; i<tempGameCritic.size();i++) {
			tempGameCritic.get(i).setShift(8);
			gameCriticQueue.add(tempGameCritic.get(i));
		}		
    }
        
	//prints evaluated movies,games and their ratings
    public void printRatings() {
    	System.out.println("Ratings:");
    	printMovieRatings();
    	printGameRatings();
    }
    
    //prints evaluated movies, their year and evaluated ratings
    private void printMovieRatings() {
    	for(int i=0; i<evaluatedMovies.size(); i++) {
    		Movie movie = (Movie) evaluatedMovies.get(i);
    		System.out.println(movie.getName()+" ("+movie.getYear()+"), "+String.format("%,.2f", movie.getEvaluateRate()));
    	}
    }
    
    //prints evaluated games and their ratings
    private void printGameRatings() {
    	for(int i=0; i<evaluatedGames.size(); i++) {
    		System.out.println(evaluatedGames.get(i).getName()+", "+evaluatedGames.get(i).getEvaluateRate());
    	}
    }
    
}