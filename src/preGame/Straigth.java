package preGame;

import java.util.ArrayList;
import java.util.List;

public class Straigth {
	private RoadEnd roadStart;
	private String compas;
	private int length;
	private RoadEnd roadEnd;
	
	public Straigth(RoadEnd roadStart, String compas, int length) {
		this.roadStart = roadStart;
		this.compas = compas;
		this.length = length;
		giveLength();
	}
	public void giveLength() {
		if (compas=="North") {
			roadEnd.setY1(roadStart.getY1()-length);
			roadEnd.setY2(roadStart.getY2()-length);
			roadEnd.setX1(roadStart.getX1());
			roadEnd.setX2(roadStart.getX2());
		}else if (compas=="South") {
			roadEnd.setY1(roadStart.getY1()+length);
			roadEnd.setY2(roadStart.getY2()+length);
			roadEnd.setX1(roadStart.getX1());
			roadEnd.setX2(roadStart.getX2());
		} else if (compas == "West") {
			roadEnd.setY1(roadStart.getY1());
			roadEnd.setY2(roadStart.getY2());
			roadEnd.setX1(roadStart.getX1()+length);
			roadEnd.setX2(roadStart.getX2()+length);

		} else if (compas == "East") {
			roadEnd.setY1(roadStart.getY1());
			roadEnd.setY2(roadStart.getY2());
			roadEnd.setX1(roadStart.getX1()-length);
			roadEnd.setX2(roadStart.getX2()-length);
		}

	}
	public List<int[]> getCoordinates(){
		ArrayList<int[]> all_coordinates =  new ArrayList<int[]>();
		int[] line_One = {roadStart.getX1(), roadStart.getY1(),roadEnd.getX1(), roadEnd.getY1()};
		int[] line_Two = {roadStart.getX2(), roadStart.getY2(),roadEnd.getX2(), roadEnd.getY2()};
		all_coordinates.add( line_One);
		all_coordinates.add(line_Two);
		return all_coordinates;
		
	} 

	public String getCompas() {
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

}