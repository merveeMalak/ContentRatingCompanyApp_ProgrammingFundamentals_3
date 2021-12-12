
/** This class has the main method of the Content Rating Company Application.
 * It calls Simulation class to start the app.
 *
 * @author Merve Malak     260201043
 * @author Berfin Yucak    280201096
 */

package content_rating_company;

import simulation.ISimulation;
import simulation.Simulation;

public class ContentRatingCompanyApp {
	
	public static void  main(String[] args) {
		
		ISimulation simulation = new Simulation();
        simulation.simulateFiveDays();
	}

}
