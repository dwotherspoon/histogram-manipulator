package com.dwotherspoon.image_histogram;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImgPanel extends JPanel {
	private BufferedImage img;
	private BufferedImage src;
	
	private int red_min = 0;
	private int red_max = 255;
	private int green_min = 0;
	private int green_max = 255;
	private int blue_min = 0;
	private int blue_max = 255;
	
	public ImgPanel() {
		src = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
		img = new BufferedImage(400 ,400, BufferedImage.TYPE_INT_RGB);
	}
	
	public BufferedImage getSImage() {
		return src;
	}
	
	public void setRed(int min, int max) {
		red_min = min;
		red_max = max;
		updateImg();
		super.repaint();
	}
	
	public void setGreen(int min, int max) {
		green_min = min;
		green_max = max;
		updateImg();
		super.repaint();
	}
	
	public void setBlue(int min, int max) {
		blue_min = min;
		blue_max = max;
		updateImg();
		super.repaint();
	}
	
	public void setImage(BufferedImage im) {
		img = new BufferedImage(400,400, BufferedImage.TYPE_INT_RGB);
		src = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
		Graphics g = img.createGraphics();
		Graphics e = src.createGraphics();
		g.drawImage(im, 0, 0, 400, 400, null);
		e.drawImage(im, 0, 0, 400, 400, null);
		g.dispose();
		e.dispose();
		super.repaint();
	}
	
	private void updateImg() { 
		int r,g,b;
		for (int row = 0; row < 400; row++) {
			for (int col = 0; col < 400; col++) {
				r = (src.getRGB(col, row) >> 16) & 0xFF;
				g = (src.getRGB(col, row) >> 8) & 0xFF;
				b = src.getRGB(col, row) & 0xFF;
				
				img.setRGB(col, row, (((r >= red_min && r <= red_max) ? r : 0)<<16) + (((g >= green_min && g <= green_max) ? g : 0)<<8) + ((b >= blue_min && b <= blue_max) ? b : 0) );
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	    g.drawImage(img, 0, 0, null);
	}
	private static final long serialVersionUID = -7650841729340028854L;

}
