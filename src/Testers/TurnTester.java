package Testers;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import gui.MainFrame;
import preGame.BuildingLoop;
import preGame.RaceTrackElements;
import preGame.RoadEnd;
import preGame.Turn;

public class TurnTester {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				RaceTrackElements raceTrackElements = new RaceTrackElements();
				//BuildingLoop buildingLoop = new BuildingLoop(raceTrackElements);
				List<List<Integer>> line1 = new ArrayList<>();
				List<Integer> line1_pnt1 = new ArrayList<>();
				List<Integer> line1_pnt2  = new ArrayList<>();
				List<List<Integer>> line2 = new ArrayList<>();
				List<Integer> line2_pnt1  = new ArrayList<>();
				List<Integer> line2_pnt2  = new ArrayList<>();
				
		//		buildingLoop.doLinesCross(line1, line2)
				


			}
		});
	}

}
