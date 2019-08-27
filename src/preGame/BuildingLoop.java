package preGame;

import gui.Field;

public class BuildingLoop {

	private Field fieldpane;
	private RaceTrackElements raceTrackElements;
	
	public BuildingLoop( Field fieldpane, RaceTrackElements raceTrackElements) {
		this.fieldpane = fieldpane;
		this.raceTrackElements = raceTrackElements;
	}
	
	public void drawCircle() {
		RoadEnd trackStart =  fieldpane.getTrackStart();
		Turn turn = new Turn(trackStart, 30, true, "East", 90, 16);
		raceTrackElements.addTurn(turn);
		Turn turn2 = new Turn(turn.getTurnEnd(), 30, true, "North", 90, 16);
		raceTrackElements.addTurn(turn2);;
		fieldpane.repaint();
	}
}
