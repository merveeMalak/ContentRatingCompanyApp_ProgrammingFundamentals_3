package content.movie;

import content.Content;

public class Movie extends Content implements IMovie {
	
	private int year;    //holds year as a int
    private double averageRating;   //holds averageRating as a double
    private double evaluateRate;    //holds evaluateRate as a double

    //default constructor
    public Movie(){
        super();
        this.year = 0;
        this.averageRating = 0.0;
        this.evaluateRate = 0.0;
        setContentId(0);
    }

    //full arguments constructor
    public Movie(int arrivalDay, int contentId, String name, int year, int duration, double averageRating){
        super(arrivalDay, contentId, name, duration);
        this.year = year;
        this.averageRating = averageRating;
        this.evaluateRate = 0.0;

    }

    //takes all arguments as a string array
    public Movie(String[] info){
        this(Integer.parseInt(info[0]), Integer.parseInt(info[1]), info[2], Integer.parseInt(info[3]), Integer.parseInt(info[4]), Double.parseDouble(info[5]));
    }

    //copy constructor
    public Movie(Movie movie){
            this(movie.getArrivalDay(), movie.getContentId(), movie.getName(), movie.year, movie.getDuration(), movie.getAverageRating());
        }


    public double getAverageRating() {
        return averageRating;
    }

    public int getYear() {
        return year;
    }

    public double getEvaluateRate() {
        return evaluateRate;
    }

    @Override
    public void setEvaluatedRate(double evaluatedRate) {
        this.evaluateRate = evaluatedRate;
    }

    @Override
    public double calculateCritic(double criticOption) {
        return this.averageRating + ((getDuration() - 150) * 0.01) - ((2021 - getYear()) * 0.01) + criticOption;
    }
    @Override
    public String toString() {
        return super.toString() +  " *Duration: " + getDuration() + " minutes" + " *Year: " + getYear() + "*Evaluated Rate: " + this.evaluateRate;
    }

    public boolean equals(Object other){
        if (other == null){
            return false;
        }
        else if (getClass() != other.getClass()){
            return false;
        }
        Movie otherMovie = (Movie) other;
        return ((getArrivalDay() == otherMovie.getArrivalDay()) &&
                getContentId() == otherMovie.getContentId() &&
                getName().equals(otherMovie.getName()) &&
                getYear() == otherMovie.getYear() &&
                getDuration() == otherMovie.getDuration() &&
                this.averageRating == otherMovie.averageRating &&
                this.evaluateRate == otherMovie.evaluateRate);
    }
}
