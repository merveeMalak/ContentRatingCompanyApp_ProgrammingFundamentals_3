package simulation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import content.game.IGame;
import content.movie.IMovie;
import fileIO.FileIO;
import worker.game_critic.GameCritic;
import worker.game_critic.IGameCritic;
import worker.movie_critic.IMovieCritic;
import worker.movie_critic.MovieCritic;

public class Simulation implements ISimulation {
	
	public Simulation() {
		createQueuesAndStacks();
	}

	//empty stacks for contents are created
    private Stack<IGame> gameStack;
    private Stack<IMovie> movieStack;

    //empty queues for critics are created
    private Queue<IGameCritic> gameCriticQueue;
    private Queue<IMovieCritic> movieCriticQueue;

    //empty linked lists are created for the evaluated content
    private ArrayList<IMovie> evaluatedMovies;
    private ArrayList<IGame> evaluatedGames;
    
    //lists for unfinished evaluations: critics who will continue the other day and the games they are evaluating
    private ArrayList<IGameCritic> gameCriticsInProgress;
    private ArrayList<IGame> gamesInProgress;

    //list of critics who reached the maximum number of hours to work
    private ArrayList<IGameCritic> gameCriticsEndedShifts;

    //lists of evaluated games and critic id's belonging the day at hand
    private ArrayList<IGame> dailyEvaluatedGames;
    private ArrayList<Integer> dailyEvaluatedCritics;
    
    //creates all required queues and stacks
    public void createQueuesAndStacks(){
        gameStack = new Stack<IGame>();
        movieStack = new Stack<IMovie>();
        gameCriticQueue = new LinkedList<IGameCritic>();
        movieCriticQueue = new LinkedList<IMovieCritic>();
        evaluatedMovies = new ArrayList<IMovie>();
        evaluatedGames = new ArrayList<IGame>();
        gameCriticsInProgress = new ArrayList<IGameCritic>();
        gamesInProgress = new ArrayList<IGame>();
        gameCriticsEndedShifts = new ArrayList<IGameCritic>();
        dailyEvaluatedGames = new ArrayList<IGame>();
        dailyEvaluatedCritics = new ArrayList<Integer>();
        createCriticQueues();
    }
    
    public void simulateFiveDays(){
        evaluateFiveDays();
        printRatings();
    }
    
    //calls needed methods in the needed order to simulate 5 days of the company
    private void evaluateFiveDays() {
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
			if (evaluatedRate>100) {
				evaluatedRate=100;
			}
			gameToRate.setEvaluatedRate(evaluatedRate);
			evaluationStatus = true;
		}
		else if (gameCriticToRate.getShift()==duration) {
			int evaluatedRate = gameCriticToRate.rateContent(gameToRate);
			if (evaluatedRate>100) {
				evaluatedRate=100;
			}
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
    		
    		int criticNumber = 0;
    		for (IGame game: tempGamesInProgress) {
        				
        		IGame gameToContinue = game;
        		IGameCritic criticToContinue = gameCriticsInProgress.get(criticNumber);
        		criticNumber++;
        		
        		System.out.println(criticToContinue.getCriticId()+". game critic works on "+gameToContinue.getName());
        		
        		boolean evaluationStatus = false;
        		int isShiftEnded = 0;
        		
        		if (criticToContinue.getShift()>0) {
        			int evaluatedRate = criticToContinue.rateContent(gameToContinue);
        			if (evaluatedRate>100) {
        				evaluatedRate=100;
        			}
        			gameToContinue.setEvaluatedRate(evaluatedRate);
        			evaluationStatus = true;
        		}
        		else if (criticToContinue.getShift()==0) {
        			int evaluatedRate = criticToContinue.rateContent(gameToContinue);
        			if (evaluatedRate>100) {
        				evaluatedRate=100;
        			}
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
    	
    	ArrayList<IGameCritic> tempGameCritics = new ArrayList<>();
    	
    	for(int i=0; i<gameCriticsInProgress.size(); i++) {   		
    		IGameCritic gameCritic = gameCriticsInProgress.get(i);
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
    private void printRatings() {
        System.out.println("Ratings:");
        printMovieRatings();
        printGameRatings();
    }
    
    private void printMovieRatings() {
        for (IMovie movie : evaluatedMovies) {
            System.out.println(movie.getName() + " (" + movie.getYear() + "), " +String.format("%,.2f", movie.getEvaluateRate()) );
        }
    }
    
    //prints evaluated games and their ratings
    private void printGameRatings() {
    	for(int i=0; i<evaluatedGames.size(); i++) {
    		System.out.println(evaluatedGames.get(i).getName()+", "+evaluatedGames.get(i).getEvaluateRate());
    	}
    }
    
}