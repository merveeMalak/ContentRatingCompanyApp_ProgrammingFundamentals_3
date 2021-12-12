package content_rating_company;

import simulation.ISimulation;
import simulation.Simulation;

public class ContentRatingCompanyApp {
	
	public static void  main(String[] args) {
		
		ISimulation simulation = new Simulation();
        simulation.simulateFiveDays();
	}

}
