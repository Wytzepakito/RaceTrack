package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Field extends JPanel {
	private Graphics2D g2d;
	private int heigth;
	private int width;

	
	public Field(int height, int width) {
		this.heigth= height;
		this.width = width;
		this.setBackground(Color.WHITE);
		
		
	}

	public Dimension getPreferredSize() {
        return new Dimension(width, heigth);
    }
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D) g;
		drawCircle();
	}
//	private ArrayList<Integer[]> racetrackElements(){
//		ArrayList<Integer[]> raceTrackElements = new ArrayList<Integer[]>();
//	//	raceTrackElements.add();
	}
	
	private void drawCircle() {
		g2d.drawLine(200, 210, 250, 210);
		g2d.drawLine(200, 200, 250, 200);
		g2d.drawLine(250, 210, 300, 250);
		g2d.drawLine(250, 200, 310, 250);
//		g2d.drawLine(220, 180, 235, 165);
//		g2d.drawLine(210, 200, 220, 190);
//		g2d.drawLine(220, 190, 225, 185);
//		g2d.drawLine(225, 185, 230, 180);
//		g2d.drawLine(230, 180, 245, 165);
		

	}
	
	
}
