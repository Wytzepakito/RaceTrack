package preGame;

import java.util.ArrayList;
import java.util.Arrays;
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
			while(itemCorrect==false) {
				System.out.println("New try");
				boolean chanceTurn = getRndCoinFlip();
				boolean chanceClockWise = getRndCoinFlipTurns();
				randStreet = makeStreet(roadEnd, compasOut);
				randTurn = makeTurn(roadEnd, compasOut, chanceClockWise);
				if (chanceTurn) {
					if((checkTurnCrossTrack(randTurn)==false) && (randTurn.checkOutOfBounds(fieldpane.getWidth(), fieldpane.getHeight())==false)){
						System.out.println("Was it correct yes?");
						System.out.println(checkTurnCrossTrack(randTurn));
						System.out.println("Is it out of bounds?");
						System.out.println(randTurn.checkOutOfBounds(fieldpane.getWidth(), fieldpane.getHeight()));
						
						itemCorrect = true;
						chanceTurnChosen = true;
					}
				} else  {
					if((checkStreetCrossTrack(randStreet)== false) && (randStreet.checkOutOfBounds(fieldpane.getWidth(), fieldpane.getHeight()) == false)) {
						System.out.println("Did it cross its track");
						System.out.println(checkStreetCrossTrack(randStreet));
						System.out.println("Is it out of bounds?");
						System.out.println(randTurn.checkOutOfBounds(fieldpane.getWidth(), fieldpane.getHeight()));
						itemCorrect = true;
						chanceTurnChosen = false;
					}
				}
				triedSometimes +=1;
			}
			if (chanceTurnChosen) {
				raceTrackElements.addTurn(randTurn);
				roadEnd = randTurn.getTurnEnd();
				compasOut = randTurn.getCompasOut();
			} else  {
				roadEnd = randStreet.getRoadEnd();
				compasOut = randStreet.getCompasOut();
			}
			count+=1;
			System.out.println("=====================================================================================================================================");
		}
	}

	private boolean checkStreetCrossTrack(Street street) {
		boolean theyCross = false;
		boolean noneCrossed = false;
		int[][] newLine1 = street.getCoordinates()[0];
		int[][] newLine2 = street.getCoordinates()[1];
		while (theyCross == false && noneCrossed == false) {
			for (Turn turn : raceTrackElements.getTurns()) {
				for (int i = 0; i < turn.getInnerCoordinates().length - 1; i++) {
					int[][] oldLine1 =  Arrays.copyOfRange(turn.getInnerCoordinates(), i, i+2);
					int[][] oldLine2 =  Arrays.copyOfRange(turn.getOuterCoordinates(), i, i+2);
					if (doLinesCross(newLine1, oldLine1) | doLinesCross(newLine1, oldLine2)
							| doLinesCross(newLine2, oldLine1) | doLinesCross(newLine2, oldLine2)) {
						theyCross = true;
						System.out.println("LINES CROSSE!!!!!!");
					}
				}
			}
			for (Street oldStreet : raceTrackElements.getStreets()) {
				int[][] oldLine1 = oldStreet.getCoordinates()[0];
				int[][] oldLine2 = oldStreet.getCoordinates()[1];
				if (doLinesCross(newLine1, oldLine1) | doLinesCross(newLine1, oldLine2)
						| doLinesCross(newLine2, oldLine1) | doLinesCross(newLine2, oldLine2)) {
					theyCross = true;
					System.out.println("LINES CROSSE!!!!!!");
				}
				
			}
			noneCrossed = true;
			
	}
		System.out.println("they cross");
		System.out.println(theyCross);
		return theyCross;
	}

	private boolean checkTurnCrossTrack(Turn turnToCheck) {
		boolean theyCross = false;
		boolean noneCrossed = false;

		for (Turn oldTurn : raceTrackElements.getTurns()) {
			for (int i = 0; i < oldTurn.getInnerCoordinates().length - 1; i++) {
				int[][] oldLine1 = Arrays.copyOfRange(oldTurn.getInnerCoordinates(), i, i + 2);
				int[][] oldLine2 = Arrays.copyOfRange(oldTurn.getOuterCoordinates(), i, i + 2);
				for (int j = 0; j < turnToCheck.getInnerCoordinates().length - 1; j++) {
					int[][] newLine1 = Arrays.copyOfRange(turnToCheck.getInnerCoordinates(), j, j + 2);
					int[][] newLine2 = Arrays.copyOfRange(turnToCheck.getOuterCoordinates(), j, j + 2);

					if (doLinesCross(oldLine1, newLine1) | doLinesCross(oldLine1, newLine2)
							| doLinesCross(oldLine2, newLine1) | doLinesCross(oldLine2, newLine2)) {
						System.out.println("IIIT CROSSED");
						theyCross = true;
					}
				}
			}

		}
		for (Street oldStreet : raceTrackElements.getStreets()) {
			int[][] oldLine1 = oldStreet.getCoordinates()[0];
			int[][] oldLine2 = oldStreet.getCoordinates()[1];
			for (int i = 0; i < turnToCheck.getInnerCoordinates().length - 1; i++) {
				int[][] newLine1 = Arrays.copyOfRange(turnToCheck.getInnerCoordinates(), i, i + 2);
				int[][] newLine2 = Arrays.copyOfRange(turnToCheck.getOuterCoordinates(), i, i + 2);
				if (doLinesCross(oldLine1, newLine1) | doLinesCross(oldLine1, newLine2)
						| doLinesCross(oldLine2, newLine1) | doLinesCross(oldLine2, newLine2)) {
					theyCross = true;
					System.out.println("IIIT CROSSED");
				}

			}

		}
		noneCrossed = true;

		return theyCross;

	}
	
	public boolean doLinesCross(int[][] line1, int[][] line2) {
		// all lines follow the format [[x1, y1][x2, y2]]
		boolean result = false;
		// line are vertical and x is same if they overlap then they cross
		if (line1[0][0]==line1[1][0] && line2[0][0]== line2[1][0]) {
			// the Xs of those vertical lines are the same so check if the ys overlap
			if (line2[0][0]== line1[1][0]) {
			result = overlapCheckerY(line1, line2);
			}else if (line1[0][0]!= line2[0][0]) {
				// lines are vertical but x is not the same so the result is automatically false(parallel lines never meet)
				result = false;
			}
		}  else if ((line1[0][0]==line1[1][0]) && (line2[0][0]) != line2[1][0]) {
			// one of the lines is vertical check if that x is in the line segment we got for the other line
			double x0 = line1[0][0];
			if (Math.min(line2[0][0], line2[1][0]) < x0 && x0 < Math.max(line2[0][0], line2[1][0])){
				result = true;
			}
		} else if (line2[0][0]==line2[1][0] && (line1[0][0] != line1[1][0])) {
			// one of the lines is vertical check if that x is in the line segment we got for the other line
			double x0 = line2[0][0];
			if (Math.min(line1[0][0], line1[1][0]) < x0 && x0 < Math.max(line1[0][0], line1[1][0])) {
				result = true;
			}
		} 
	else if (!(line1[1][0]==line1[0][0]) && (!(line2[0][0]==line2[1][0]))) {
			// none of the lines is vertical so we need to make equations for each
			// make equation of y= ax +b
			double a1 = (line1[1][1] - line1[0][1]) / (line1[1][0] - line1[0][0]);
			double b1 = line1[0][1] - a1 * line1[0][0];
			double a2 = (line2[1][1] - line2[0][1]) / (line2[1][0] - line2[0][0]);
			double b2 = line2[0][1] - a2 * line2[0][0];
			// lines are parallel and on the same "track" or y so we need to check if the Xs
			// overlap
			if (a1 == a2 & b1 == b2) {
				result = overlapCheckerX(line1, line2);
			} else {
				// intersection of lines at x = 
				double x0 = ((b1 - b2) / (a1 - a2));
				boolean inside_xrange1 =false;
				boolean inside_xrange2 =false;
				// check if the x0 is inside the xrange of the first line
				if (Math.min(line1[0][0], line1[1][0]) < x0 && x0 < Math.max(line1[0][0], line1[1][0])
						) {
					inside_xrange1 = true;
				}
				// check if the x0 is inside the xrange of the second line 
				if ( Math.min(line2[0][0], line2[1][0]) < x0 && x0 < Math.max(line2[0][0], line2[1][0])) {
					inside_xrange2 = true;
				}
				// if x0 is inside the xrange of both lines then the result is true
				if (inside_xrange1 && inside_xrange2) {
					result = true;
				}
			}
		}
		return result;
	}
	private boolean overlapCheckerY(int[][] line1, int[][] line2) {
		boolean result = false;
		if(((Math.min(line1[0][1], line1[1][1]) < Math.max(line2[0][1], line2[1][1])) | (Math.max(line1[0][1], line1[1][1])) > Math.min(line2[0][1], line2[1][1]))){
			result= true;
		}
		return result;
	}
	
	private boolean overlapCheckerX(int[][] line1, int[][] line2) {
		boolean result = false;
		if((Math.min(line1[0][0], line1[1][0]) < Math.max(line2[0][0], line2[1][0])) | (Math.max(line1[0][0], line1[1][0]) > Math.min(line2[0][0], line2[1][0]))){
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
