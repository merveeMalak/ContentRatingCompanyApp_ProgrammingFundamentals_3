package worker.game_critic;

import content.game.Game;
import content.game.IGame;
import worker.Worker;

public class GameCritic extends Worker implements IGameCritic {

    private int shift;
    private int opinion;

    public GameCritic() {
        this(0,8,0);
    }

    public GameCritic(int criticId, int shift, int opinion) {
        super(criticId);
        this.shift = shift;
        this.opinion = opinion;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    public int getOpinion() {
        return opinion;
    }

    public void setOpinion(int opinion) {
        this.opinion = opinion;
    }

    public String toString() {
        return "Shift: "+getShift()+"Opinion: "+getOpinion();
    }

    public boolean equals(Object other){
        if (other == null){
            return false;
        }
        else if (getClass() != other.getClass()){
            return false;
        }
        else {
            GameCritic otherGameCritic = (GameCritic) other;
            return (this.shift==otherGameCritic.shift && this.opinion==otherGameCritic.opinion);}

    }

    public int rateContent(Game game) {
        return (int)(game.calculateCritic(this.opinion));

    }

    @Override
    public int rateContent(IGame game) {
        return game.calculateCritic(this.opinion);
    }


}