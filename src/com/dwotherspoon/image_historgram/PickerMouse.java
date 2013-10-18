package com.dwotherspoon.image_historgram;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/* Worlds most pointless interface, links straight back */

public class PickerMouse implements MouseListener {
	private HistogramPanel caller;
	
	public PickerMouse(HistogramPanel sender) {
		caller = sender;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		caller.clickUpdate(e.getPoint());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
