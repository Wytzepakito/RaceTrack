package preGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Turn {
	private int innerCircleRadius;
	private int outerCircleRadius;
	private int polygonCount;
	private RoadEnd turnStart;
	private boolean clockwise;
	private int x_CircleCenter;
	private int y_CircleCenter;
	private String compasIn;
	private RoadEnd turnEnd;
	private String compasOut;
	private int degrees;
	private int[][] innerCoordinates;
	private int[][] outerCoordinates;
	private boolean firstCoordinatesEnd;
	
	public Turn(RoadEnd turnStart, int innerCircleRadius, boolean clockwise, String compasIn, int degrees, int polygonCount) {
		this.turnStart = turnStart;
		this.innerCircleRadius = innerCircleRadius;
		this.outerCircleRadius = innerCircleRadius+20;
		this.degrees = degrees;
		this.polygonCount= polygonCount;
		this.clockwise = clockwise;
		this.compasIn = compasIn;
		compasHandler();
		calcCoordinates();
		setTurnEnd();
	}
	public void calcCoordinates() {
		innerCoordinates =  coordinateCalculator( polygonCount, innerCircleRadius);
		outerCoordinates = coordinateCalculator( polygonCount, outerCircleRadius);
		if (clockwise) {
			innerCoordinates = circleCutterClockWise(innerCoordinates);
			outerCoordinates = circleCutterClockWise(outerCoordinates);
		} else {
			innerCoordinates = circleCutterCounterClockWise(innerCoordinates);
			outerCoordinates = circleCutterCounterClockWise(outerCoordinates);
		}

	}
	
	public void compasHandler() {
		if (compasIn=="North") {
			findNorthCircleCenter();
		} else if (compasIn=="West") {
			findWestCircleCenter();
		}else if (compasIn=="South") {
			findSouthCircleCenter();
		}else if (compasIn=="East") {
			findEastCircleCenter();
		}
		
	}
	public void findNorthCircleCenter() {
		if (clockwise) {
			x_CircleCenter = Math.max(turnStart.getX1(), turnStart.getX2()) + innerCircleRadius;
			y_CircleCenter = turnStart.getY1();
		}else {
			x_CircleCenter = Math.min(turnStart.getX1(),turnStart.getX2()) - innerCircleRadius;
			y_CircleCenter = turnStart.getY1();
		}
	}
	public void findWestCircleCenter() {
		
		if (clockwise) {
			y_CircleCenter = Math.min(turnStart.getY1(), turnStart.getY2()) - innerCircleRadius;
			x_CircleCenter = turnStart.getX1();
		} else {
			y_CircleCenter = Math.max(turnStart.getY1(), turnStart.getY2()) + innerCircleRadius;
			x_CircleCenter = turnStart.getX1();
		}

	}
	
	public void findSouthCircleCenter() {
		if (clockwise) {
			x_CircleCenter = Math.min(turnStart.getX1(), turnStart.getX2())- innerCircleRadius;
			y_CircleCenter = turnStart.getY1();
		} else {
			x_CircleCenter = Math.max(turnStart.getX1(), turnStart.getX2()) + innerCircleRadius;
			y_CircleCenter = turnStart.getY1();
		}
	}
	
	public void findEastCircleCenter() {
		if (clockwise) {
			y_CircleCenter = Math.max(turnStart.getY1(), turnStart.getY2()) + innerCircleRadius;
			x_CircleCenter = turnStart.getX1();
		} else {
			y_CircleCenter = Math.min(turnStart.getY1(), turnStart.getY2()) - innerCircleRadius;
			x_CircleCenter = turnStart.getX1();
		}
	}

	public int[][] coordinateCalculator(int polygonCount, int circleRadius) {
		List<int[]> coordinates = new ArrayList();
		for (int i = 0; i < polygonCount; i++) {
			Double x = x_CircleCenter + circleRadius * Math.cos(2 * Math.PI * i / polygonCount);
			Double y = y_CircleCenter + circleRadius * Math.sin(2 * Math.PI * i / polygonCount);
			int[] temp_list = new int[] {x.intValue(), y.intValue()};
			coordinates.add(temp_list);
		}
		int[][] coordinatesInt = new int[coordinates.size()][2];
		for(int i = 0; i < coordinates.size(); i++) {
			coordinatesInt[i] = coordinates.get(i);
		}
		return coordinatesInt;
	}
	private int[][] circleCutterCounterClockWise(int[][] coordinates){
	int ninetyDeg = polygonCount/4;
	int[][] small_coordinates =  null;
	int[][] small_coordinates2 = null;
	if (compasIn=="East") {
		if (degrees==90) {
			 small_coordinates = Arrays.copyOfRange(coordinates, 0, 3);
			 small_coordinates2 = Arrays.copyOfRange(coordinates, 3, 5);
			 firstCoordinatesEnd = true;
			 compasOut= "North";
		}else if (degrees==180) {
			 small_coordinates = Arrays.copyOfRange(coordinates, 12, 15);
			 small_coordinates2 = Arrays.copyOfRange(coordinates, 0, 5);
			firstCoordinatesEnd = true;
			compasOut= "West";
		}else if (degrees==270) {
			 small_coordinates = Arrays.copyOfRange(coordinates, 8, 15);
			 small_coordinates2 = Arrays.copyOfRange(coordinates, 0, 5);
				firstCoordinatesEnd = true;
				compasOut= "South";
			}
		} else if (compasIn == "North") {
			if (degrees == 90) {
				firstCoordinatesEnd = true;
				 small_coordinates = Arrays.copyOfRange(coordinates, 12, 15);
				 small_coordinates2 = Arrays.copyOfRange(coordinates, 0, 2);
				compasOut= "West";
			} else if (degrees == 180) {
				firstCoordinatesEnd = true;
				 small_coordinates = Arrays.copyOfRange(coordinates, 8, 15);
				 small_coordinates2 = Arrays.copyOfRange(coordinates, 0, 2);
				compasOut= "South";
			} else if (degrees == 270) {
				firstCoordinatesEnd = true;
				 small_coordinates = Arrays.copyOfRange(coordinates, 4, 15);
				 small_coordinates2 = Arrays.copyOfRange(coordinates, 0, 2);
				compasOut= "East";
			}
		} else if (compasIn=="South") {
			if (degrees == 90) {
				 small_coordinates = Arrays.copyOfRange(coordinates, 4, 6);
				 small_coordinates2 = Arrays.copyOfRange(coordinates, 6, 9);
				compasOut= "East";
				firstCoordinatesEnd = true;
			} else if (degrees == 180) {
				 small_coordinates = Arrays.copyOfRange(coordinates, 0, 3);
				 small_coordinates2 = Arrays.copyOfRange(coordinates, 3, 9);
				compasOut= "North";
				firstCoordinatesEnd = true;
			} else if (degrees == 270) {
				 small_coordinates = Arrays.copyOfRange(coordinates, 12, 15);
				 small_coordinates2 = Arrays.copyOfRange(coordinates, 0, 9);
				compasOut= "West";
				firstCoordinatesEnd = true;
			}
		} else if (compasIn=="West") {
			if (degrees == 90) {
				 small_coordinates = Arrays.copyOfRange(coordinates, 8, 10);
				 small_coordinates2 = Arrays.copyOfRange(coordinates, 10, 13);
				firstCoordinatesEnd = true;
				compasOut= "South";
			} else if (degrees == 180) {
				small_coordinates = Arrays.copyOfRange(coordinates, 4, 8);
				small_coordinates2 = Arrays.copyOfRange(coordinates, 8, 13);
				firstCoordinatesEnd = true;
				compasOut= "East";
			} else if (degrees == 270) {
				small_coordinates = Arrays.copyOfRange(coordinates, 0, 5);
				small_coordinates2 = Arrays.copyOfRange(coordinates, 5, 13);
				firstCoordinatesEnd = true;
				compasOut= "North";
			}
		}
	int[][] super_small_coordinates = mergeArrays(small_coordinates,small_coordinates2);

	return super_small_coordinates;
	}
	
	private int[][] circleCutterClockWise(int[][] coordinates){
	int ninetyDeg = polygonCount/4;
	int[][] small_coordinates =  null;
	int[][] small_coordinates2 = null;
	if (compasIn=="East") {
		if (degrees==90) {
			 small_coordinates = Arrays.copyOfRange(coordinates, 12, 16);
			 small_coordinates2 = Arrays.copyOfRange(coordinates, 0, 2);
			 compasOut= "South";
		}else if (degrees==180) {

			small_coordinates = Arrays.copyOfRange(coordinates, 12, 16);
			small_coordinates2 = Arrays.copyOfRange(coordinates, 0, 5);
			compasOut= "West";
		}else if (degrees==270) {
			small_coordinates = Arrays.copyOfRange(coordinates, 12, 16);
			small_coordinates2 = Arrays.copyOfRange(coordinates, 0, 9);
			compasOut= "North";
			}
		} else if (compasIn == "North") {
			if (degrees == 90) {
				small_coordinates = Arrays.copyOfRange(coordinates, 8, 10);
				small_coordinates2 = Arrays.copyOfRange(coordinates, 10, 13);
				compasOut= "East";
			} else if (degrees == 180) {
				small_coordinates = Arrays.copyOfRange(coordinates, 8, 16);
				small_coordinates2 = Arrays.copyOfRange(coordinates, 0, 2);
				compasOut= "South";
			} else if (degrees == 270) {
				small_coordinates = Arrays.copyOfRange(coordinates, 8, 16);
				small_coordinates2 = Arrays.copyOfRange(coordinates, 0, 5);
				compasOut= "West";
			}
		} else if (compasIn=="South") {
			if (degrees == 90) {
			 small_coordinates = Arrays.copyOfRange(coordinates, 0, 3);
			 small_coordinates2 = Arrays.copyOfRange(coordinates, 3, 5);
				compasOut= "West";
			} else if (degrees == 180) {
			 small_coordinates = Arrays.copyOfRange(coordinates, 0, 5);
			 small_coordinates2 = Arrays.copyOfRange(coordinates, 5, 9);
				compasOut= "North";
			} else if (degrees == 270) {
			 small_coordinates = Arrays.copyOfRange(coordinates, 0, 9);
			 small_coordinates2 = Arrays.copyOfRange(coordinates, 9, 13);
				compasOut= "East";
			}
		} else if (compasIn=="West") {
			if (degrees == 90) {
			 small_coordinates = Arrays.copyOfRange(coordinates, 4, 7);
			 small_coordinates2 = Arrays.copyOfRange(coordinates, 7, 9);
				compasOut= "North";
			} else if (degrees == 180) {
			 small_coordinates = Arrays.copyOfRange(coordinates, 4, 8);
			 small_coordinates2 = Arrays.copyOfRange(coordinates, 8, 13);
				compasOut= "East";
			} else if (degrees == 270) {
			 small_coordinates = Arrays.copyOfRange(coordinates, 4, 16);
			 small_coordinates2 = Arrays.copyOfRange(coordinates, 0, 1);
				compasOut= "South";
			}
		}
	
	
	int[][] super_small_coordinates = mergeArrays(small_coordinates,small_coordinates2);
	return super_small_coordinates;
	}
	public boolean checkOutOfBounds(int width, int height) {
		boolean outOfBounds = false;
		if (checkOuterOutOfBounds(width, height)|| checkInnerOutOfBounds(width, height)) {
			outOfBounds = true;
		}
		return outOfBounds;
	}
	
	private boolean checkOuterOutOfBounds(int width, int height){
		boolean outOfBounds = false;
		for(int[] coordinate : outerCoordinates) {
			if (coordinate[0] > width || coordinate[0] < 0|| coordinate[1]> height || coordinate[1] < 0) {
				outOfBounds = true;
			}
		}
		return outOfBounds;
	}
	
	private boolean checkInnerOutOfBounds(int width, int height){
		boolean outOfBounds = false;
		for(int[] coordinate : innerCoordinates) {
			if (coordinate[0] > width || coordinate[0] < 0|| coordinate[1] > height || coordinate[1] < 0) {
				outOfBounds = true;
			}
		}
		return outOfBounds;
	}
	
	public RoadEnd getTurnEnd() {
		return turnEnd;
	}
	private void setTurnEnd() {
		if (firstCoordinatesEnd) {
		turnEnd = new RoadEnd(innerCoordinates[0][0], innerCoordinates[0][1], outerCoordinates[0][0], outerCoordinates[0][1]);
		} else {
			int size = innerCoordinates.length;
			turnEnd = new RoadEnd(innerCoordinates[size-1][0], innerCoordinates[size-1][1], outerCoordinates[size-1][0], outerCoordinates[size-1][1]);
		}
	}
	
	private int[][] mergeArrays(int[][] array1, int[][] array2) {
		int[][] mergedArray = new int[array1.length+array2.length][];
		int index = array1.length;

		for (int i = 0; i < array1.length; i++) {
			mergedArray[i] = array1[i];
		}
		for (int i = 0; i < array2.length; i++) {
			mergedArray[i + index] = array2[i];    
		}
		return mergedArray;
		
	}

	@Override
	public String toString() {
		String string = "compas out: "+ compasOut + " degrees:" + degrees + " clockwise:" + clockwise; 
		return string;	
	}
	public String getRoadEndsString() {
		String string = "RoadStart:"+ turnStart.toString() + "RoadEnd:" + turnEnd.toString();
		return string;
	}
	public String getCompasOut() {
		return compasOut;
	}
	public int[][] getInnerCoordinates() {
		return innerCoordinates;
	}
	public int[][] getOuterCoordinates() {
		return outerCoordinates;
	}
	public String getCompasIn() {
		return compasIn;
	}
	
	
}
