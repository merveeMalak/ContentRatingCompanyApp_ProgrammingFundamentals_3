package simulation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import content.Game;
import content.Movie;
import fileIO.FileIO;
import worker.GameCritic;
import worker.MovieCritic;

public class Simulation implements ISimulation {
	
	public Simulation() {
		
	}
	
	//empty stacks for contents are created
	Stack<Game> gameStack = new Stack<Game>();
	Stack<Movie> movieStack = new Stack<Movie>();
	
	//empty queues for critics are created
	Queue<GameCritic> gameCriticQueue = new LinkedList<>();
	Queue<MovieCritic> movieCriticQueue = new LinkedList<>();
	
	//empty linked lists are created for the evaluated content
	LinkedList<Movie> evaluatedMovies = new LinkedList<>();
	LinkedList<Game> evaluatedGames = new LinkedList<>();
	
	//creates game and movie critics and adds them to related queues 
	public void createCriticQueues() {
		
		//movie critics are created
		MovieCritic movieCritic1 = new MovieCritic(0.1);
		MovieCritic movieCritic2 = new MovieCritic(-0.2);
		MovieCritic movieCritic3 = new MovieCritic(0.3);
		
		//movie critics are added to the movie critic queue
		movieCriticQueue.add(movieCritic1);
		movieCriticQueue.add(movieCritic2);
		movieCriticQueue.add(movieCritic3);
		
		//game critics are created
		GameCritic gameCritic1 = new GameCritic(8,5);
		GameCritic gameCritic2 = new GameCritic(8,9);
		GameCritic gameCritic3 = new GameCritic(8,-3);
		GameCritic gameCritic4 = new GameCritic(8,2);
		GameCritic gameCritic5 = new GameCritic(8,-7);
		
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
		
		ArrayList<Movie> movieList = file.getMovieArrayList();
		for (Movie movie: movieList) {
			if (movie.getArrivalDay() == dayNumber) {
				movieStack.add(movie);
			}
		}
		
		ArrayList<Game> gameList = file.getGameArrayList();
		for (Game game: gameList) {
			if (game.getArrivalDay() == dayNumber) {
				gameStack.add(game);
			}
		}
		
	}
	
	//add prints
	public void evaluateMovies() {
		
		for(int x = 0; x<movieCriticQueue.size(); x++) {
			
			if (!(movieStack.isEmpty())) {
				Movie movieToRate = movieStack.pop();
				MovieCritic movieCriticToRate = movieCriticQueue.poll();
				double evaluatedRate = movieCriticToRate.rateContent(movieToRate);
				movieToRate.setEvaluatedRate(evaluatedRate);
				evaluatedMovies.add(movieToRate);
				movieCriticQueue.add(movieCriticToRate);
				System.out.println();
			}
			else {break;}
		}
	}
	
	public void evaluateGames() {
		
	}
	
	//createCriticQueues();
	//updateStacks(1); -----> do not forget!!!
	public void simulateFiveDays() {
		
		for(int i=1; i<6; i++) {
			
			evaluateMovies();
			
		}
	}
	
	
	

}
