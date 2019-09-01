package preGame;

import java.util.ArrayList;

public class RaceTrackElements {
	private ArrayList<Turn> turns;
	private ArrayList<Street> streets;
	public RaceTrackElements() {
		turns = new ArrayList<Turn>();
		streets = new ArrayList<Street>();
	}

	
	public ArrayList<Turn> getTurns() {
		return turns;
	}
	public void setTurns(ArrayList<Turn> turns) {
		this.turns = turns;
	}
	public void addTurn(Turn turn) {
		this.turns.add(turn);
	}
	public ArrayList<Street> getStreets() {
		return streets;
	}
	public void setStreets(ArrayList<Street> streets) {
		this.streets = streets;
	}
	public void addStreet(Street street) {
		this.streets.add(street);
	}
	
	
}
