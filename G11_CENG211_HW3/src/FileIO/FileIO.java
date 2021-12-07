package FileIO;

import content.game.*;
import content.movie.IMovie;
import content.movie.Movie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class FileIO {

    private final String FILE_NAME = "contents.csv";
    private List<IMovie> movieArrayList;
    private List<IGame> gameArrayList;

    public FileIO() {
        this.movieArrayList = new ArrayList<IMovie>();
        this.gameArrayList = new ArrayList<IGame>();
    }

    private void readFile() {
        try {
            Scanner scanner = new Scanner(new File(FILE_NAME));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                decideContentType(line);
            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }

    private void decideContentType(String line) {
        String[] info = line.split(",");
        int contentId = Integer.parseInt(info[1]);
        switch (contentId) {
            case 0:
                IMovie movie = new Movie(info);
                movieArrayList.add(movie);
                break;
            case 1:
                IGame indefiniteGame = new IndefiniteGame(info);
                gameArrayList.add(indefiniteGame);
                break;
            case 2:
                IGame storyGame = new StoryGame(info);
                gameArrayList.add(storyGame);
                break;
            case 3:
                IGame casualGame = new CasualGame(info);
                gameArrayList.add(casualGame);
                break;
            default: break;
        }

    }

    private List<IMovie> getMovieArrayList() {
        readFile();
        return movieArrayList;
    }

    public Stack<IMovie> getIndexDayOfMovie(int dayNumber){
        List<IMovie> movieList = getMovieArrayList();
        Stack<IMovie> movieStack = new Stack<IMovie>();
        for (IMovie movie: movieList) {
            if (movie.getArrivalDay() == dayNumber) {
                movieStack.add(movie);
            }
        }
        return movieStack;
    }

    public Stack<IGame> getIndexOfDayGame(int dayNumber){
        List<IGame> gameList = getGameArrayList();
        Stack<IGame> gameStack = new Stack<IGame>();
        for (IGame game: gameList) {
            if (game.getArrivalDay() == dayNumber) {
                gameStack.add(game);
            }
        }
        return gameStack;
    }
    private List<IGame> getGameArrayList() {
        readFile();
        return gameArrayList;
    }

}