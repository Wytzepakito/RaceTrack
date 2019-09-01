package preGame;

import java.util.ArrayList;
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
	private List<List<Integer>> innerCoordinates;
	private List<List<Integer>> outerCoordinates;
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
		System.out.println(innerCoordinates);
		System.out.println(outerCoordinates);
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

	public List<List<Integer>> coordinateCalculator(int polygonCount, int circleRadius) {
		List<List<Integer>> coordinates = new ArrayList();
		for (int i = 0; i < polygonCount; i++) {
			ArrayList<Integer> temp_list = new ArrayList();
			Double x = x_CircleCenter + circleRadius * Math.cos(2 * Math.PI * i / polygonCount);
			Double y = y_CircleCenter + circleRadius * Math.sin(2 * Math.PI * i / polygonCount);
			temp_list.add(x.intValue());
			temp_list.add(y.intValue());
			coordinates.add(temp_list);
		}
		return coordinates;
	}
	private List<List<Integer>> circleCutterCounterClockWise(List<List<Integer>> coordinates){
	int ninetyDeg = polygonCount/4;
	List<List<Integer>> small_coordinates =  null;


	if (compasIn=="East") {
		if (degrees==90) {
			 small_coordinates =  coordinates.subList(0, 5);
			 firstCoordinatesEnd = true;
			 compasOut= "North";
		}else if (degrees==180) {
			small_coordinates =  coordinates.subList(12, 15);
			small_coordinates.addAll(coordinates.subList(0, 5));
			firstCoordinatesEnd = true;
			compasOut= "West";
		}else if (degrees==270) {
				small_coordinates = coordinates.subList(8, 15);
				small_coordinates.addAll(coordinates.subList(0, 5));
				firstCoordinatesEnd = true;
				compasOut= "South";
			}
		} else if (compasIn == "North") {
			if (degrees == 90) {
				firstCoordinatesEnd = true;
				small_coordinates = coordinates.subList(12, 15);
				small_coordinates.add(coordinates.get(0));
				compasOut= "West";
			} else if (degrees == 180) {
				firstCoordinatesEnd = true;
				small_coordinates = coordinates.subList(8, 15);
				small_coordinates.add(coordinates.get(0));
				compasOut= "South";
			} else if (degrees == 270) {
				firstCoordinatesEnd = true;
				small_coordinates = coordinates.subList(4, 15);
				small_coordinates.add(coordinates.get(0));
				compasOut= "East";
			}
		} else if (compasIn=="South") {
			if (degrees == 90) {
				small_coordinates = coordinates.subList(4, 9);
				compasOut= "East";
				firstCoordinatesEnd = true;
			} else if (degrees == 180) {
				small_coordinates = coordinates.subList(0, 9);
				compasOut= "North";
				firstCoordinatesEnd = true;
			} else if (degrees == 270) {
				small_coordinates = coordinates.subList(12, 15);
				small_coordinates.addAll(coordinates.subList(0, 9));
				compasOut= "West";
				firstCoordinatesEnd = true;
			}
		} else if (compasIn=="West") {
			if (degrees == 90) {
				small_coordinates = coordinates.subList(8, 13);
				firstCoordinatesEnd = true;
				compasOut= "South";
			} else if (degrees == 180) {
				small_coordinates = coordinates.subList(4, 13);
				firstCoordinatesEnd = true;
				compasOut= "East";
			} else if (degrees == 270) {
				small_coordinates = coordinates.subList(0, 13);
				firstCoordinatesEnd = true;
				compasOut= "North";
			}
		}
	return small_coordinates;
	}
	
	private List<List<Integer>> circleCutterClockWise(List<List<Integer>> coordinates){
	int ninetyDeg = polygonCount/4;
	List<List<Integer>> small_coordinates =  null;
	List<List<Integer>> small_coordinates2 =  null;
//	small_coordinates.addAll(small_coordinates2);
	if (compasIn=="East") {
		if (degrees==90) {
			 small_coordinates =  coordinates.subList(12, 16);
			 small_coordinates.addAll(coordinates.subList(0, 1));
			 compasOut= "South";
		}else if (degrees==180) {
			small_coordinates =  coordinates.subList(12, 16);
			small_coordinates.addAll(coordinates.subList(0, 5));
			compasOut= "West";
		}else if (degrees==270) {
				small_coordinates = coordinates.subList(12, 16);
				small_coordinates.addAll(coordinates.subList(0, 9));
				compasOut= "North";
			}
		} else if (compasIn == "North") {
			if (degrees == 90) {
				small_coordinates = coordinates.subList(8, 13);
				compasOut= "East";
			} else if (degrees == 180) {
				small_coordinates = coordinates.subList(8, 16);
				small_coordinates.addAll(coordinates.subList(0, 1));
				compasOut= "South";
			} else if (degrees == 270) {
				small_coordinates = coordinates.subList(8, 16);
				small_coordinates.addAll(coordinates.subList(0, 5));
				compasOut= "West";
			}
		} else if (compasIn=="South") {
			if (degrees == 90) {
				small_coordinates = coordinates.subList(0, 5);
				compasOut= "West";
			} else if (degrees == 180) {
				small_coordinates = coordinates.subList(0, 9);
				compasOut= "North";
			} else if (degrees == 270) {
				small_coordinates = coordinates.subList(0, 13);
				compasOut= "East";
			}
		} else if (compasIn=="West") {
			if (degrees == 90) {
				small_coordinates = coordinates.subList(4, 9);
				compasOut= "North";
			} else if (degrees == 180) {
				small_coordinates = coordinates.subList(4, 13);
				compasOut= "East";
			} else if (degrees == 270) {
				small_coordinates = coordinates.subList(4, 16);
				small_coordinates.addAll(coordinates.subList(0, 1));
				compasOut= "South";
			}
		}
	return small_coordinates;
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
		for(List<Integer> coordinate : outerCoordinates) {
			if (coordinate.get(0)> width || coordinate.get(0)< 0|| coordinate.get(1)> height || coordinate.get(1) < 0) {
				outOfBounds = true;
			}
		}
		return outOfBounds;
	}
	
	private boolean checkInnerOutOfBounds(int width, int height){
		boolean outOfBounds = false;
		for(List<Integer> coordinate : innerCoordinates) {
			if (coordinate.get(0)> width || coordinate.get(0)< 0|| coordinate.get(1)> height || coordinate.get(1) < 0) {
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
		turnEnd = new RoadEnd(innerCoordinates.get(0).get(0), innerCoordinates.get(0).get(1), outerCoordinates.get(0).get(0), outerCoordinates.get(0).get(1));
		} else {
			int size = innerCoordinates.size();
			turnEnd = new RoadEnd(innerCoordinates.get(size-1).get(0), innerCoordinates.get(size-1).get(1), outerCoordinates.get(size-1).get(0), outerCoordinates.get(size-1).get(1));
		}
	}

	@Override
	public String toString() {
		String string = "compas out: "+ compasOut + " degrees:" + degrees + " clockwise:" + clockwise; 
		return string;	
	}
	public String getRoadEndsString() {
		String string = "RoadStart:"+ turnStart.toString() + "RoadEnd:" + turnStart.toString();
		return string;
	}
	public String getCompasOut() {
		return compasOut;
	}
	public List<List<Integer>> getInnerCoordinates() {
		return innerCoordinates;
	}
	public List<List<Integer>> getOuterCoordinates() {
		return outerCoordinates;
	}
	public String getCompasIn() {
		return compasIn;
	}
	
	
}
