package worker.movie_critic;

import content.movie.IMovie;
import worker.IWorker;

public interface IMovieCritic extends IWorker {
    public double rateContent(IMovie movie);
}
