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
	private List<List<Integer>> small_Inner;
	private List<List<Integer>> small_Outer;
	
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
		List<List<Integer>> innerCoordinates =  coordinateCalculator( polygonCount, innerCircleRadius);
		List<List<Integer>> outerCoordinates = coordinateCalculator( polygonCount, outerCircleRadius);
		small_Inner = innerCoordinates;
		small_Outer = outerCoordinates;
		if (clockwise) {
			small_Inner = circleCutterClockWise(innerCoordinates);
			small_Outer = circleCutterClockWise(outerCoordinates);
		} else {
//			List<List<Integer>> smallInnerCoordinates = circleCutterCounterClockWise(innerCoordinates);
//			List<List<Integer>> smallOuterCoordinates = circleCutterCounterClockWise(outerCoordinates);
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
			y_CircleCenter = Math.max(turnStart.getY1(), turnStart.getY2()) + innerCircleRadius;
			x_CircleCenter = turnStart.getX1();
		} else {
			y_CircleCenter = Math.min(turnStart.getY1(), turnStart.getY2()) - innerCircleRadius;
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
			y_CircleCenter = Math.min(turnStart.getY1(), turnStart.getY2()) - innerCircleRadius;
			x_CircleCenter = turnStart.getX1();
		} else {
			y_CircleCenter = Math.max(turnStart.getY1(), turnStart.getY2()) + innerCircleRadius;
			x_CircleCenter = turnStart.getX1();
		}
	}

	public List<List<Integer>> coordinateCalculator(int polygonCount, int circleRadius) {
		double length_of_sides = circleRadius * Math.sin((2*Math.PI)/polygonCount);
		double one_corner = ((Math.PI*(polygonCount -2))/polygonCount);
		double angle = (2 * Math.PI)/ polygonCount; 
		List<List<Integer>> coordinates = new ArrayList();
		for (int i = 0; i < polygonCount; i++) {
			ArrayList<Integer> temp_list = new ArrayList();
			  Double x = x_CircleCenter + circleRadius * Math.cos(2 * Math.PI * i / polygonCount);
			  Double y =  y_CircleCenter + circleRadius * Math.sin(2 * Math.PI * i / polygonCount);
			  temp_list.add(x.intValue());
			  temp_list.add(y.intValue());
			  coordinates.add(temp_list);
			}
		return coordinates;
	}
	
	private List<List<Integer>> circleCutterClockWise(List<List<Integer>> coordinates){
	int ninetyDeg = polygonCount/4;
	List<List<Integer>> small_coordinates =  null;
	List<List<Integer>> small_coordinates2 =  null;
//	small_coordinates.addAll(small_coordinates2);
	if (compasIn=="East") {
		if (degrees==90) {
			 small_coordinates =  coordinates.subList(0, 2);
			 small_coordinates2 =  coordinates.subList(2, 5);
		}else if (degrees==180) {
			small_coordinates =  coordinates.subList(12, 16);
			small_coordinates2 =  coordinates.subList(0, 5);
		}else if (degrees==270) {
				small_coordinates = coordinates.subList(8, 16);
				small_coordinates2 = coordinates.subList(0, 5);
			}
		} else if (compasIn == "North") {
			if (degrees == 90) {
				small_coordinates = coordinates.subList(0, 2);
				small_coordinates2 = coordinates.subList(2, 5);
			} else if (degrees == 180) {
				small_coordinates = coordinates.subList(12, 16);
				small_coordinates2 = coordinates.subList(0, 5);
			} else if (degrees == 270) {
				small_coordinates = coordinates.subList(8, 16);
				small_coordinates2 = coordinates.subList(0, 5);
			}
		}
	small_coordinates.addAll(small_coordinates2);
	return small_coordinates;
	}
	
	public RoadEnd getTurnEnd() {
		return turnEnd;
	}
	private void setTurnEnd() {
		int size = small_Inner.size();
		turnEnd = new RoadEnd(small_Inner.get(size-1).get(0), small_Inner.get(size-1).get(0), small_Outer.get(size-1).get(0), small_Outer.get(size-1).get(1));
	}
	public List<List<Integer>> getSmall_Inner() {
		return small_Inner;
	}
	public List<List<Integer>> getSmall_Outer() {
		return small_Outer;
	}
	@Override
	public String toString() {
		String string = "Circle radius in,out: " + innerCircleRadius +"," + outerCircleRadius+" Circle x,y: " + x_CircleCenter+","+y_CircleCenter;
		
		return string;
		
	}
	
}
