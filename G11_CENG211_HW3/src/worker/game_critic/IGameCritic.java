package worker.game_critic;

import content.game.IGame;

import worker.IWorker;

public interface IGameCritic extends IWorker {
    public int rateContent(IGame game);
    public int getShift();
}
