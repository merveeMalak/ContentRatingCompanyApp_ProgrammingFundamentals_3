package simulation;

import FileIO.FileIO;
import content.game.CasualGame;
import content.game.IGame;
import content.game.IndefiniteGame;
import content.movie.IMovie;
import worker.game_critic.GameCritic;
import worker.game_critic.IGameCritic;
import worker.movie_critic.IMovieCritic;
import worker.movie_critic.MovieCritic;

import java.util.*;

public class Simulation implements ISimulation {

    public Simulation() {

    }

    //empty stacks for contents are created
    Stack<IGame> gameStack = new Stack<IGame>();
    Stack<IMovie> movieStack = new Stack<IMovie>();

    //empty queues for critics are created
    Queue<IGameCritic> gameCriticQueue = new LinkedList<>();
    Queue<IMovieCritic> movieCriticQueue = new LinkedList<>();

    //empty linked lists are created for the evaluated content
    List<IMovie> evaluatedMovies = new LinkedList<>();
    List<IGame> evaluatedGames = new LinkedList<>();

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

    //add prints
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

    public void evaluateGames() {
        for (int i = 0; i < gameCriticQueue.size(); i++){
            if (!(gameStack.empty())){
                IGame gameToRate = gameStack.pop();
                IGameCritic gameCriticToRate = gameCriticQueue.poll();
                int totalShift = 0;
                if (gameToRate instanceof CasualGame){

                }
                else if (gameToRate instanceof IndefiniteGame){
                    int evaluatedRate = gameCriticToRate.rateContent(gameToRate);
                    gameToRate.setEvaluatedRate(evaluatedRate);
                    evaluatedGames.add(gameToRate);
                    totalShift += 4;
                    gameCriticQueue.add(gameCriticToRate);
                }
                else{

                }

            }
        }
    }

    //createCriticQueues();
    //updateStacks(1); -----> do not forget!!!
    public void simulateFiveDays() {
        createCriticQueues();
        for(int i=1; i<6; i++) {
            System.out.println(i + ". day:");
            updateStacks(i);
            evaluateMovies();

        }
    }




}
