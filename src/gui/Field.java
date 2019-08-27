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
//		drawStraights();
//		tempWrapper();
		
	}
	private void drawCircle() {
		for (Turn turn : raceTrackElements.getTurns()) {
			drawTurn(turn.getSmall_Inner(), turn.getSmall_Outer());
		}
		
	}
	
//	public void tempWrapper() {
//		int polygonCount = 16 ;
//		int x_start = (width/2)+50;
//		int y_start = 100;
//		int x_start2 = 150;
//		List<List<Double>> super_list = calcEdgesPolygonCors(polygonCount, 50, x_start, y_start);
//		drawTurn(super_list);
//		int y_start2 = 120;
//		List<List<Double>> super_list2 = calcEdgesPolygonCors(polygonCount, 30, x_start, y_start2);
//		drawTurn(super_list2);
//		
//		x_start = 100;
//		y_start = 100;
//		List<List<Double>> super_list3 = calcEdgesPolygonCors(polygonCount, 30, x_start, y_start);
//		drawTurn(super_list3);
//		x_start = 100;
//		y_start = 100;
//		List<List<Double>> super_list4 = calcEdgesPolygonCors(polygonCount, 50, x_start, y_start);
//		drawTurn(super_list4);
//		
//	}

	
	/*This takes a super coordinate list of [x,y] coordinates and draws that polygon 
	 * 
	 */
	public void drawTurn(List<List<Integer>> coordinates, List<List<Integer>> other_coordinates) {
		for (int i = 0; i < coordinates.size() - 1; i++) {
			g2d.drawLine(coordinates.get(i).get(0), coordinates.get(i).get(1), coordinates.get(i + 1).get(0),
					coordinates.get(i + 1).get(1));
		}
		for (int i = 0; i < other_coordinates.size() - 1; i++) {
			g2d.drawLine(other_coordinates.get(i).get(0), other_coordinates.get(i).get(1),
					other_coordinates.get(i + 1).get(0), other_coordinates.get(i + 1).get(1));
		}
	}
	/*
	 * 
	 */
	
	public void drawStraigth(List<List<Integer>> coordinates) {
		for (List<Integer> one_line :  coordinates) {
			g2d.drawLine(one_line.get(0), one_line.get(2),one_line.get(3),one_line.get(4));
		}
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
