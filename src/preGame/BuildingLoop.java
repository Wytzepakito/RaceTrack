package preGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gui.Field;

public class BuildingLoop {

	private Field fieldpane;
	private RaceTrackElements raceTrackElements;
	
	public BuildingLoop( Field fieldpane, RaceTrackElements raceTrackElements) {
		this.fieldpane = fieldpane;
		this.raceTrackElements = raceTrackElements;
	}
	
	public void drawCircle() {

		makeTrack();
		fieldpane.repaint();
	}

	private void  makeTrack() {
		// add the start as a street
		
		Street start = new Street(fieldpane.getTrackEnd(), "East", 100);
		raceTrackElements.addStreet(start);
		
		Street randStreet = null;
		Turn randTurn = null;
		boolean chanceTurnChosen = false;
		int count = 0;
		RoadEnd roadEnd = fieldpane.getTrackStart();
		String compasOut = "East";
		while (count != 8 ) {
			boolean itemCorrect = false;
			int triedSometimes = 0;
			while(itemCorrect==false && triedSometimes != 10) {
				boolean chanceTurn = getRndCoinFlip();
				boolean chanceClockWise = getRndCoinFlipTurns();
				randStreet = makeStreet(roadEnd, compasOut);
				randTurn = makeTurn(roadEnd, compasOut, chanceClockWise);
				if (chanceTurn) {

					if((checkTurnCrossTrack(randTurn)==false) && (randTurn.checkOutOfBounds(fieldpane.getWidth(), fieldpane.getHeight())==false)){
						itemCorrect = true;
						chanceTurnChosen = true;
					}
				} else  {
					if((checkStreetCrossTrack(randStreet)== false) && (randStreet.checkOutOfBounds(fieldpane.getWidth(), fieldpane.getHeight()) == false)) {
						itemCorrect = true;
						chanceTurnChosen = false;
					}
				}
				triedSometimes +=1;
			}
			if (chanceTurnChosen) {
				System.out.println("A TURN");
				System.out.println(randTurn.toString());
				System.out.println(randTurn.getRoadEndsString());
				raceTrackElements.addTurn(randTurn);
				roadEnd = randTurn.getTurnEnd();
				compasOut = randTurn.getCompasOut();
			} else  {
				System.out.println("A ROAD");
				System.out.println(randStreet.toString());
				raceTrackElements.addStreet(randStreet);
				roadEnd = randStreet.getRoadEnd();
				compasOut = randStreet.getCompasOut();
			}
			count+=1;
		}
	}

	private boolean checkStreetCrossTrack(Street street) {
		boolean theyCross = false;
		boolean noneCrossed = false;
		List<List<Integer>> newLine1 = street.getCoordinates().get(0);
		List<List<Integer>> newLine2 = street.getCoordinates().get(1);
		while (theyCross == false && noneCrossed == false) {
			for (Turn turn : raceTrackElements.getTurns()) {
				for (int i = 0; i < turn.getInnerCoordinates().size() - 1; i++) {
					List<List<Integer>> oldLine1 = turn.getInnerCoordinates().subList(i, i + 2);
					List<List<Integer>> oldLine2 = turn.getOuterCoordinates().subList(i, i + 2);
					if (doLinesCross(newLine1, oldLine1) | doLinesCross(newLine1, oldLine2)
							| doLinesCross(newLine2, oldLine1) | doLinesCross(newLine2, oldLine2)) {
						theyCross = true;
					}
				}
			}
			for (Street oldStreet : raceTrackElements.getStreets()) {
				List<List<Integer>> oldLine1 = oldStreet.getCoordinates().get(0);
				List<List<Integer>> oldLine2 = oldStreet.getCoordinates().get(1);
				if (doLinesCross(newLine1, oldLine1) | doLinesCross(newLine1, oldLine2)
						| doLinesCross(newLine2, oldLine1) | doLinesCross(newLine2, oldLine2)) {
					theyCross = true;
				}
				
			}
			noneCrossed = true;
			
	}
		return theyCross;
	}

	private boolean checkTurnCrossTrack(Turn turnToCheck) {
		boolean theyCross = false;
		boolean noneCrossed = false;

		while (theyCross == false && noneCrossed == false) {
			for (Turn oldTurn : raceTrackElements.getTurns()) {
				for (int i = 0; i < oldTurn.getInnerCoordinates().size() - 1; i++) {
					List<List<Integer>> oldLine1 = oldTurn.getInnerCoordinates().subList(i, i + 2);
					List<List<Integer>> oldLine2 = oldTurn.getOuterCoordinates().subList(i, i + 2);
					for (int j = 0; j < turnToCheck.getInnerCoordinates().size() - 1; j++) {
						List<List<Integer>> newLine1 = turnToCheck.getInnerCoordinates().subList(j, j + 2);
						List<List<Integer>> newLine2 = turnToCheck.getOuterCoordinates().subList(j, j + 2);
						if (doLinesCross(oldLine1, newLine1) | doLinesCross(oldLine1, newLine2)
								| doLinesCross(oldLine2, newLine1) | doLinesCross(oldLine2, newLine2)) {
							theyCross = true;
						}
					}
				}
				
			}
			for (Street oldStreet : raceTrackElements.getStreets()) {
				List<List<Integer>> oldLine1 = oldStreet.getCoordinates().get(0);
				List<List<Integer>> oldLine2 = oldStreet.getCoordinates().get(1);
				for (int i = 0; i < turnToCheck.getInnerCoordinates().size() - 1; i++) {
					List<List<Integer>> newLine1 = turnToCheck.getInnerCoordinates().subList(i, i + 2);
					List<List<Integer>> newLine2 = turnToCheck.getOuterCoordinates().subList(i, i + 2);
					if (doLinesCross(oldLine1, newLine1) | doLinesCross(oldLine1, newLine2)
							| doLinesCross(oldLine2, newLine1) | doLinesCross(oldLine2, newLine2)) {
						theyCross = true;
					}

				}
				
			}
			noneCrossed = true;

		}
		return theyCross;

	}
	
	public boolean doLinesCross(List<List<Integer>> line1, List<List<Integer>> line2) {
		// all lines follow the format [[x1, y1][x2, y2]]
		boolean result = false;
		// line are vertical and x is same if they overlap then they cross
		if (line1.get(0).get(0)==line1.get(1).get(0) && line2.get(0).get(0)== line2.get(1).get(0) && line2.get(0).get(0)==line1.get(0).get(0)) {
			result = overlapCheckerY(line1, line2);
		} else if (line1.get(0).get(0)==line1.get(1).get(0) && line2.get(0).get(0)== line2.get(1).get(0) && line2.get(0).get(0)!=line1.get(0).get(0)) {
			// lines are vertical but x is not the same so the result is automatically false(parallel lines never meet)
			result = false;
		} else if (line1.get(0).get(0)==line1.get(1).get(0)) {
			// one of the lines is vertical check if that x is in the line segment we got for the other line
			double x0 = line1.get(0).get(0);
			if (Math.min(line2.get(0).get(0), line2.get(1).get(0)) < x0 && x0 < Math.max(line2.get(0).get(0), line2.get(1).get(0))){
				result = true;
			}
		} else if (line2.get(0).get(0)==line2.get(1).get(0)) {
			// one of the lines is vertical check if that x is in the line segment we got for the other line
			double x0 = line2.get(0).get(0);
			if (Math.min(line1.get(0).get(0), line1.get(1).get(0)) < x0
					&& x0 < Math.max(line1.get(0).get(0), line1.get(1).get(0))) {
				result = true;
			}
		} 
	else if (!(line1.get(1).get(0).equals(line1.get(0).get(0))) && (!line2.get(0).get(0).equals(line2.get(1).get(0)))) {
			// none of the lines is vertical so we need to make equations for each
			// make equation of y= ax +b
			double a1 = (line1.get(1).get(1) - line1.get(0).get(1)) / (line1.get(1).get(0) - line1.get(0).get(0));
			double b1 = line1.get(0).get(1) - a1 * line1.get(0).get(0);
			double a2 = (line2.get(1).get(1) - line2.get(0).get(1)) / (line2.get(1).get(0) - line2.get(0).get(0));
			double b2 = line2.get(0).get(1) - a2 * line2.get(0).get(0);
			// lines are parallel and on the same "track" or y so we need to check if the Xs
			// overlap
			if (a1 == a2 & b1 == b2) {
				result = overlapCheckerX(line1, line2);
			} else {
				// intersection of lines
				double x0 = ((b1 - b2) / (a1 - a2));
				if (Math.min(line1.get(0).get(0), line1.get(1).get(0)) < x0
						&& x0 < Math.max(line1.get(0).get(1), line1.get(1).get(0))
						&& Math.min(line2.get(0).get(0), line2.get(1).get(0)) < x0
						&& x0 < Math.max(line2.get(0).get(0), line2.get(1).get(0))) {
					result = true;
				}

			}
		}
		return result;
	}
	private boolean overlapCheckerY(List<List<Integer>> line1, List<List<Integer>> line2) {
		boolean result = false;
		if(((Math.min(line1.get(0).get(1), line1.get(1).get(1)) < Math.max(line2.get(0).get(1), line2.get(1).get(1))) | (Math.max(line1.get(0).get(1), line1.get(1).get(1)) > Math.min(line2.get(0).get(1), line2.get(1).get(1))))){
			result= true;
		}
		return result;
	}
	
	private boolean overlapCheckerX(List<List<Integer>> line1, List<List<Integer>> line2) {
		boolean result = false;
		if((Math.min(line1.get(0).get(0), line1.get(1).get(0)) < Math.max(line2.get(0).get(0), line2.get(1).get(0))) | (Math.max(line1.get(0).get(0), line1.get(1).get(0)) > Math.min(line2.get(0).get(0), line2.get(1).get(0)))){
				result = true;
				
		}
		return result;
	}
	
	private Turn makeTurn(RoadEnd roadEnd, String compasOut, boolean clockwise) {
		int[] radii = {10,20,30,40,50,60,70,80,90,100,200,300};
		int[] degRank = {90,90,90,90,90, 180, 270};
		Turn randTurn = new Turn(roadEnd, getRnd(radii), clockwise, compasOut, getRnd(degRank), 16);
		return randTurn;
	}
	private Street makeStreet(RoadEnd roadEnd, String compasOut) {
		// radii is the plural of radius :D
		int[] radii = {10,20,30,40,50,60,70,80,90,100,200,300};
		Street street = new Street(roadEnd, compasOut, getRnd(radii));
		return street;
	}
	
	private int getRnd(int[] myArray) {
	//initialization
	Random generator = new Random();
	int randomIndex = generator.nextInt(myArray.length);
	return myArray[randomIndex];
	}
	
	private boolean getRndCoinFlip() {
		double x = Math.random();
		if(x>0.5) {
			return true;
		}else {
			return false;
		}
	}
	private boolean getRndCoinFlipTurns() {
		double x = Math.random();
		if(x>0.75) {
			return true;
		}else {
			return false;
		}
	}
}
