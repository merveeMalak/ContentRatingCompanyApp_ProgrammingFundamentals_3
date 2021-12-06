package content;

public class Movie extends Content {
	
	private int year;
	private double evaluatedRate;

    public Movie(){
        super();
        this.year = 0;
        this.evaluatedRate = 0.0;
        setContentId(0);
    }

    public Movie(int arrivalDay, int contentId, String name, int year, int duration, double averageRating){
        super(arrivalDay, contentId, name, duration, averageRating);
        this.year = year;
        this.evaluatedRate = 0.0;
    }
/*
    public Movie(Movie movie){
        this(movie.getArrivalDay(), movie.getContentId(), movie.getName(), movie.year, movie.getDuration(), movie.getAverageRating());
    }
*/
    public int getYear() {
        return year;
    }
    
    public double getEvaluatedRate() {
    	return evaluatedRate;
    }
    
    public void setEvaluatedRate(double evaluatedRate) {
    	this.evaluatedRate = evaluatedRate;
    }

    @Override
    public double calculateCritic(int criticOption) {
        return getAverageRating() + ((getDuration() - 150) * 0.01) - ((2021 - getYear()) * 0.01) + criticOption;
    }
    @Override
    public String toString() {
        return super.toString() +  " *Duration: " + getDuration() + " minutes" + " *Year: " + getYear() + "*Evaluated Rate: "+getEvaluatedRate();
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
                getAverageRating() == otherMovie.getAverageRating() &&
        		getEvaluatedRate() == otherMovie.getEvaluatedRate());
    }

}
