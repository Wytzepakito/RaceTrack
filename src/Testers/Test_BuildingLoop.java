package Testers;

import java.util.Arrays;

import gui.Field;
import junit.framework.TestCase;
import preGame.BuildingLoop;
import preGame.RaceTrackElements;





public class Test_BuildingLoop extends TestCase {
	
	
	public void testDoLinesCross() {
		RaceTrackElements raceTrackElements = new RaceTrackElements();
		Field field = new Field(800, 700, raceTrackElements);
		BuildingLoop buildingLoop = new BuildingLoop(field, raceTrackElements );
		// Crossing lines, line one ,y decreases, x decreases, line two, y decreases, x decreases 
		int[][] line1 = {{3,3},{2,2}};
		int[][] line2 = {{}, {}}
		buildingLoop.doLinesCross(line1, line2)
		assertEquals();
	}

}