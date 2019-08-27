package preGame;

import java.util.ArrayList;

public class RaceTrackElements {
	private ArrayList<Turn> turns;
	private ArrayList<Straigth> straights;
	public RaceTrackElements() {
		turns = new ArrayList<Turn>();
		straights = new ArrayList<Straigth>();
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
	public ArrayList<Straigth> getStraights() {
		return straights;
	}
	public void setStraights(ArrayList<Straigth> straights) {
		this.straights = straights;
	}
	public void addStraight(Straigth straight) {
		this.straights.add(straight);
	}
	
	
}
