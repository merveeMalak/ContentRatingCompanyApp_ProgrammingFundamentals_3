package models.movie;

import models.Content;

public class Movie extends Content {

    private int year;


    public Movie(){
        super();
        this.year = 0;

        setContentId(0);
    }

    public Movie(int arrivalDay, int contentId, String name, int year, int duration, double averageRating){
        super(arrivalDay, contentId, name, duration, averageRating);
        this.year = year;

    }

    public Movie(String[] info){
        this(Integer.parseInt(info[0]), Integer.parseInt(info[1]), info[2], Integer.parseInt(info[3]), Integer.parseInt(info[4]), Double.parseDouble(info[5]));
    }
    /*
        public Movie(Movie movie){
            this(movie.getArrivalDay(), movie.getContentId(), movie.getName(), movie.year, movie.getDuration(), movie.getAverageRating());
        }
    */
    public int getYear() {
        return year;
    }


    @Override
    public double calculateCritic(double criticOption) {
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