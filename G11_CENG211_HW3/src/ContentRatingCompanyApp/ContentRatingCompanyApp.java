package ContentRatingCompanyApp;

import simulation.ISimulation;
import simulation.Simulation;

public class ContentRatingCompanyApp {

    public static void main(String[] args) {
        ISimulation simulation = new Simulation();
        simulation.simulateFiveDays();
    }
}