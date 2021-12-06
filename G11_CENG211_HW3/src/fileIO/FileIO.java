package fileIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import content.CasualGame;
import content.Game;
import content.IndefiniteGame;
import content.Movie;
import content.StoryGame;

public class FileIO {
	
	private final String FILE_NAME = "contents.csv";
	private ArrayList<Movie> movieArrayList;
	private ArrayList<Game> gameArrayList;
	
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
			Movie movie = new Movie(Integer.parseInt(info[0]),Integer.parseInt(info[1]),info[2],Integer.parseInt(info[3]),Integer.parseInt(info[4]),Double.parseDouble(info[5]));
			movieArrayList.add(movie);
			break;
		case 1:
			IndefiniteGame indefiniteGame = new IndefiniteGame(Integer.parseInt(info[0]),Integer.parseInt(info[1]),info[2],Integer.parseInt(info[3]),Integer.parseInt(info[4]));
			gameArrayList.add(indefiniteGame);
			break;
		case 2:
			StoryGame storyGame = new StoryGame(Integer.parseInt(info[0]),Integer.parseInt(info[1]),info[2],Integer.parseInt(info[3]),Integer.parseInt(info[4]));
			gameArrayList.add(storyGame);
			break;
		case 3:
			CasualGame casualGame = new CasualGame(Integer.parseInt(info[0]),Integer.parseInt(info[1]),info[2],Integer.parseInt(info[3]),Integer.parseInt(info[4]));
			gameArrayList.add(casualGame);
			break;
		default: break;
		}
		
	}
	
	public ArrayList<Movie> getMovieArrayList() {
		readFile();
		return movieArrayList;
	}
	
	public ArrayList<Game> getGameArrayList() {
		readFile();
		return gameArrayList;
	}

}
