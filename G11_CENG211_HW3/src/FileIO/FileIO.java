package FileIO;

import models.game.CasualGame;
import models.game.Game;
import models.game.IndefiniteGame;
import models.game.StoryGame;
import models.movie.Movie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileIO {

    private final String FILE_NAME = "contents.csv";
    private List<Movie> movieArrayList;
    private List<Game> gameArrayList;

    public FileIO() {
        this.movieArrayList = new ArrayList<Movie>();
        this.gameArrayList = new ArrayList<Game>();
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
                Movie movie = new Movie(info);
                movieArrayList.add(movie);
                break;
            case 1:
                Game indefiniteGame = new IndefiniteGame(info);
                gameArrayList.add(indefiniteGame);
                break;
            case 2:
                Game storyGame = new StoryGame(info);
                gameArrayList.add(storyGame);
                break;
            case 3:
                Game casualGame = new CasualGame(info);
                gameArrayList.add(casualGame);
                break;
            default: break;
        }

    }

    public List<Movie> getMovieArrayList() {
        readFile();
        return movieArrayList;
    }


    public List<Game> getGameArrayList() {
        readFile();
        return gameArrayList;
    }

}