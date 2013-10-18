package com.dwotherspoon.image_histogram;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/* Class for histogram panels, assumes 400*400 */
public class HistogramPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3169654766438742070L;

	public enum Colour {
		red, green, blue;
	}
	
	private int[] col_count;
	private int pick1;//x axis of pickers
	private int pick2;
	private int pickY; //yaxis
	private Color col;
	private Color box;
	private ImgPanel src;
	private BufferedImage img;
	
	public HistogramPanel(Colour c, ImgPanel srcPanel) {
		switch (c) {
			case red:
				col = java.awt.Color.RED;
				break;
			case green:
				col = java.awt.Color.GREEN;
				break;
			case blue:
				col = java.awt.Color.BLUE;
				break;
		}
		src = srcPanel;
		img = src.getSImage();
		countPixels();
		pickY = 350;
		pick1 = 21;
		pick2 = (20+256);//seed points
		box = new Color(Color.gray.getRed(), Color.gray.getGreen(), Color.gray.getBlue(), 128);
		this.addMouseListener(new PickerMouse(this));
	}
	
	private void countPixels() {
		System.out.println("counting");
		col_count = new int[256]; //zero out count array
		if (Color.RED.equals(col)) {
			for (int row = 0; row<img.getHeight(); row++) {
				for (int col = 0; col<img.getWidth(); col++) {
					col_count[(img.getRGB(col, row) >> 16) & 0xFF]++;
				}
			}
		}
		else if (Color.BLUE.equals(col)) {
			for (int row = 0; row<400; row++) {
				for (int col = 0; col<400; col++) {
					col_count[(img.getRGB(col, row) >> 8) & 0xFF]++;
				}
			}
		}
		else {
			for (int row = 0; row<400; row++) {
				for (int col = 0; col<400; col++) {
					col_count[(img.getRGB(col, row)) & 0xFF]++;
				}
			}
		}
	}
	
	public void recalc() { //updates counts for when image is changed
		img = src.getSImage();
		countPixels();
		super.repaint();
	}
	
	public void clickUpdate(Point p) { //bit messy but C'est la vie.
		if (p.y >= (pickY-20) && p.y <= (pickY+20) ) { //kinda near the axis?
			if (Math.abs(pick1 - p.x) < Math.abs(pick2 - p.x)) {  //move picker 1, it's closer
				if (21 > p.x) {
					pick1 = 21;
				}
				else if ((20+256) < p.x) {
					pick1 = (20+256);
				}
				else {
					pick1 = p.x;
				}
				super.repaint();
			}
			else { //mover picker 2
				if (21 > p.x) {
					pick2 = 21;
				}
				else if ((20+256) < p.x) {
					pick2 = (20+256);
				}
				else {
					pick2 = p.x;
				}
				super.repaint();
			}
		}
		if (Color.RED.equals(col)) {
			src.setRed(pick1-21, pick2-21);
		}
		else if (Color.BLUE.equals(col)) {
			src.setBlue(pick1-21, pick2-21);
		}
		else {
			src.setGreen(pick1-21, pick2-21);
		}
	}
	
	private void drawPicker(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.drawLine(pick1-7, pickY+7, pick1, pickY); //picker1
		g.drawLine(pick1, pickY, pick1+7, pickY+7);
		g.drawLine(pick1-7, pickY+7, pick1+7, pickY+7);
		
		g.drawLine(pick2-7, pickY+7, pick2, pickY); //picker2
		g.drawLine(pick2, pickY, pick2+7, pickY+7);
		g.drawLine(pick2-7, pickY+7, pick2+7, pickY+7);
		
		g.setColor(box);
		g.fillRect(pick1, (pickY-340), (pick2-pick1), 340);
	}
	
	private void drawHistogram(Graphics g) {
		g.setColor(col);
		for (int c = 0; c < 256; c++) {
			g.drawLine((21+c), 350, (21+c), (int)((350-(col_count[c]*0.25))));
		}
		g.setColor(Color.black);
		g.drawLine(20, 10, 20, 350);
		g.drawLine(20, 350, 370, 350);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		drawHistogram(g);
		drawPicker(g);
	}

}
