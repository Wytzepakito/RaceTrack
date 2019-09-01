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
		List<List<List<Integer>>> all_Coordinates = new ArrayList<>();
		List<List<Integer>> line_One = new ArrayList<>();
		List<Integer> line_One_Pnt1 =  new ArrayList<>();
		line_One_Pnt1.add(roadStart.getX1());
		line_One_Pnt1.add(roadStart.getY1());
		List<Integer> line_One_Pnt2 = new ArrayList<>();
		line_One_Pnt2.add(roadEnd.getX1());
		line_One_Pnt2.add(roadEnd.getY2());
		line_One.add(line_One_Pnt1);
		line_One.add(line_One_Pnt2);
		
		List<List<Integer>> line_Two = new ArrayList<>();
		List<Integer> line_Two_Pnt1 = new ArrayList<>();
		line_Two_Pnt1.add(roadStart.getX2());
		line_Two_Pnt1.add(roadStart.getY2());
		List<Integer> line_Two_Pnt2 = new ArrayList<>();
		line_Two_Pnt2.add(roadEnd.getX2());
		line_Two_Pnt2.add(roadEnd.getY2());
		line_Two.add(line_Two_Pnt1);
		line_Two.add(line_Two_Pnt2);
		
		all_Coordinates.add(line_One);
		all_Coordinates.add(line_Two);
		
		return all_Coordinates;
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