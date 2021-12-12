package simulation;


public interface ISimulation {

    //calls needed methods in the needed order to simulate 5 days of the company
    void simulateFiveDays();

    //creates game and movie critics and adds them to related queues
    void createQueuesAndStacks();

    //updates the content stacks for given day
    void updateStacks(int dayNumber);

    //assigns movies at the stack to the critics at the queue
    //critics evaluates the movies
    void evaluateMovies();
    
    //if there are any games to rate and available critics, assigns the games to critics
    public void evaluateNewGames();
}
