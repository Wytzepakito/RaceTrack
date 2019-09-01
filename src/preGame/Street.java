package preGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Street {
	private RoadEnd roadStart;
	private String compas;
	private int length;
	private RoadEnd roadEnd;
	
	public Street(RoadEnd roadStart, String compas, int length) {
		this.roadStart = roadStart;
		this.compas = compas;
		this.length = length;
		giveLength();
	}
	public void giveLength() {
		if (compas=="North") {
			roadEnd = new RoadEnd(roadStart.getX1(), roadStart.getY1()-length, roadStart.getX2(), roadStart.getY2()-length );
		}else if (compas=="South") {
			roadEnd = new RoadEnd(roadStart.getX1(), roadStart.getY1()+length, roadStart.getX2(), roadStart.getY2()+length );
		} else if (compas == "West") {
			roadEnd = new RoadEnd(roadStart.getX1()-length, roadStart.getY1(), roadStart.getX2()-length, roadStart.getY2());
		} else if (compas == "East") {
			roadEnd = new RoadEnd(roadStart.getX1()+length, roadStart.getY1(), roadStart.getX2()+length, roadStart.getY2());
		}

	}
	public List<List<List<Integer>>> getCoordinates(){
		List<List<List<Integer>>> all_coordinates = new ArrayList<>();
		int[][] line_One = {{roadStart.getX1(), roadStart.getY1()},{roadEnd.getX1(), roadEnd.getY1()}};
		int[][] line_Two = {{roadStart.getX2(), roadStart.getY2()},{roadEnd.getX2(), roadEnd.getY2()}};
		all_coordinates.add( convertArrayToList(line_One));
		all_coordinates.add(convertArrayToList(line_Two));
		return all_coordinates;
	} 
	private List<List<Integer>> convertArrayToList(int[][] array) {
		List<List<Integer>> List = new ArrayList<>();
		for(int[] miniArray: array) {
			ArrayList<Integer> miniList = new ArrayList<Integer>();
			for (int i : miniArray) {
				Integer integer = i;
				miniList.add(integer);
			}
			List.add( miniList);
		}
		return List;
	}
	public boolean checkOutOfBounds(int width, int height) {
		boolean outOfBounds= false;
		if(checkOutOfBoundsX(width) || checkOutOfBoundsY(height)) {
			outOfBounds= true;
		}
		return outOfBounds;
	}
	private boolean checkOutOfBoundsX(int width) {
		boolean outOfBounds = false;
		if(roadEnd.getX1()> width || roadEnd.getX1() < 0 || roadEnd.getX2() >  width || roadEnd.getX2() < 0) {
			outOfBounds = true;
		}
		return outOfBounds;
	}
	private boolean checkOutOfBoundsY(int height) {
		boolean outOfBounds = false;
		if (roadEnd.getY1()> height || roadEnd.getY1() <0 || roadEnd.getY2()> height|| roadEnd.getY2() < 0) {
			outOfBounds = true;
		}
		return outOfBounds;
	}

	public String getCompasOut() {
		return compas;
	}
	public void setCompas(String compas) {
		this.compas = compas;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public RoadEnd getRoadStart() {
		return roadStart;
	}
	public void setRoadStart(RoadEnd roadStart) {
		this.roadStart = roadStart;
	}
	public RoadEnd getRoadEnd() {
		return roadEnd;
	}
	public void setRoadEnd(RoadEnd roadEnd) {
		this.roadEnd = roadEnd;
	}
	@Override
	public String toString() {
		String string = roadStart.toString() + "Length" + this.length+ roadEnd.toString();
		return string;
		
	}
	
	

}