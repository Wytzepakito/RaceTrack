package Testers;

import java.awt.EventQueue;


import gui.MainFrame;
import preGame.RoadEnd;
import preGame.Turn;

public class TurnTester {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				RoadEnd roadEnd = new RoadEnd(818, 100, 818, 120);
				Turn turn = new Turn(roadEnd, 30, true, "East", 90, 16);
				System.out.println(turn.toString());
				


			}
		});
	}

}
