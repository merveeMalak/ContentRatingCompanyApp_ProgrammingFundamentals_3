package worker.movie_critic;

import content.movie.IMovie;
import worker.Worker;


public class MovieCritic extends Worker implements IMovieCritic {

    private double opinion;

    public MovieCritic(int criticId,double opinion) {
        super(criticId);
        this.opinion = opinion;
    }

    public MovieCritic() {
        this(0,0.0);
    }

    public double getOpinion() {
        return opinion;
    }

    public void setOpinion(double opinion) {
        this.opinion = opinion;
    }

    @Override
    public String toString() {
        return "Opinion: "+getOpinion();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null){
            return false;
        }
        else if (getClass() != other.getClass()){
            return false;
        }
        else {
            MovieCritic otherMovieCritic = (MovieCritic) other;
            return (this.opinion==otherMovieCritic.opinion);}
    }

    public double rateContent(IMovie movie) {
        return movie.calculateCritic(this.opinion);
    }

}