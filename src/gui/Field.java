package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import preGame.RaceTrackElements;
import preGame.RoadEnd;
import preGame.Street;
import preGame.Turn;

public class Field extends JPanel {
	private Graphics2D g2d;
	private int heigth;
	private int width;
	private RaceTrackElements raceTrackElements;
	private RoadEnd trackStart;
	private RoadEnd trackEnd;
	
	public Field(int height, int width, RaceTrackElements raceTrackElements) {
		this.heigth= height;
		this.width = width;
		this.setBackground(Color.WHITE);
		this.raceTrackElements = raceTrackElements;
		this.trackStart = new RoadEnd((width/2)+50, 100, (width/2)+50, 120);
		this.trackEnd = new RoadEnd((width/2)-50, 100, (width/2)-50, 120);
	}

	public Dimension getPreferredSize() {
        return new Dimension(width, heigth);
    }
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D) g;
		drawStart();
		drawCircle();
		drawStreet();
//		drawStraights();
//		tempWrapper();
		
	}
	private void drawCircle() {
		for (Turn turn : raceTrackElements.getTurns()) {
			drawTurn(turn.getInnerCoordinates(), turn.getOuterCoordinates());
		}
		
	}
	private void drawStreet() {
		for (Street street: raceTrackElements.getStreets()) {
			drawStraigth(street.getRoadStart(), street.getRoadEnd());
		}
	}
	

	
	/*This takes a super coordinate list of [x,y] coordinates and draws that polygon 
	 * 
	 */
	public void drawTurn(int[][] coordinates, int[][] other_coordinates) {
		for (int i = 0; i < coordinates.length - 1; i++) {
			g2d.drawLine(coordinates[i][0], coordinates[i][1], coordinates[i+1][0],
					coordinates[i+1][1]);
		}
		for (int i = 0; i < other_coordinates.length - 1; i++) {
			g2d.drawLine(other_coordinates[i][0], other_coordinates[i][1],
					other_coordinates[i+1][0], other_coordinates[i+1][1]);
		}
	}
	/*
	 * 
	 */
	
	public void drawStraigth(RoadEnd roadStart, RoadEnd roadEnd) {
		g2d.drawLine(roadStart.getX1(), roadStart.getY1(), roadEnd.getX1() , roadEnd.getY1());
		g2d.drawLine(roadStart.getX2(), roadStart.getY2(), roadEnd.getX2(), roadEnd.getY2());
		
	}
	
	/*This takes a polygonCount, circle radius and x_ start and y_start and calculates the coordinates of every point in the polygon 
	 * 
	 * 
	 */
	
	public List<List<Double>> calcEdgesPolygonCors(int polygonCount, int circleRadius, int x_start, int y_start) {
		double xCircleStart = x_start;
		double yCircleStart = y_start+circleRadius;
		double length_of_sides = circleRadius * Math.sin((2*Math.PI)/polygonCount);
		double one_corner = ((Math.PI*(polygonCount -2))/polygonCount);
		double angle = (2 * Math.PI)/ polygonCount; 
		List<List<Double>> coordinates = new ArrayList();
		for (int i = 0; i < polygonCount; i++) {
			ArrayList<Double> temp_list = new ArrayList();
			  Double x = xCircleStart + circleRadius * Math.cos(2 * Math.PI * i / polygonCount);
			  Double y =  yCircleStart + circleRadius * Math.sin(2 * Math.PI * i / polygonCount);
			  temp_list.add(x);
			  temp_list.add(y);
			  coordinates.add(temp_list);
			}
		return coordinates;
	}
	
	private void drawStart() {
		g2d.drawLine(width/2, 100, width/2, 120);
		g2d.drawLine((width/2)-50, 100, (width/2)+50, 100);
		g2d.drawLine((width/2)-50, 120, (width/2)+50, 120);



	}
	public RoadEnd getTrackStart() {
		return trackStart;
	}
	public RoadEnd getTrackEnd() {
		return trackEnd;
	}
















	
	
}
