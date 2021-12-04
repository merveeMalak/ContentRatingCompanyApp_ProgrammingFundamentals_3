package simulation;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import content.Game;
import content.Movie;
import worker.GameCritic;
import worker.MovieCritic;

public class Simulation implements ISimulation {
	
	public Simulation() {
		
	}
	
	Stack<Game> gameStack = new Stack<Game>();
	Stack<Movie> movieStack = new Stack<Movie>();
	
	Queue<GameCritic> gameCriticQueue = new LinkedList<GameCritic>();
	Queue<MovieCritic> movieCriticQueue = new LinkedList<MovieCritic>();
	
	MovieCritic movieCritic1 = new MovieCritic(0.1);
	MovieCritic movieCritic2 = new MovieCritic(-0.2);
	MovieCritic movieCritic3 = new MovieCritic(0.3);
	
	GameCritic gameCritic1 = new GameCritic(8,5);
	GameCritic gameCritic2 = new GameCritic(8,9);
	GameCritic gameCritic3 = new GameCritic(8,-3);
	GameCritic gameCritic4 = new GameCritic(8,2);
	GameCritic gameCritic5 = new GameCritic(8,-7);
	

}
