package com.dwotherspoon.image_histogram;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JFileChooser;


public class Gui {
	private JFrame f;
	private JMenuBar menu;
	private JMenuItem saveItem;
	private JMenuItem openItem;
	private JTabbedPane histogramPane;
	private HistogramPanel redCmp;
	private HistogramPanel  greenCmp;
	private HistogramPanel  blueCmp;
	private ImgPanel imgPanel;
	
	public Gui() { //setup our gui
		f = new JFrame("Histogram Manipulator");
		f.setLayout(null); //Layout Manager? Pah.
		f.setSize(850, 500);
		f.setResizable(false);
		
		menu = new JMenuBar();
		menu.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		openItem = new JMenuItem("Open");
		openItem.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				openImage();
			}
			
		} );
		menu.add(openItem);
		
		saveItem = new JMenuItem("Save");
		saveItem.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveImage();	
			}
			
		});
		menu.add(saveItem);
		
		imgPanel = new ImgPanel();
		imgPanel.setLocation(10, 10);
		imgPanel.setSize(400, 400);
	
		histogramPane = new JTabbedPane();
		histogramPane.setSize(400,400);
		histogramPane.setLocation(440, 10);
		
		redCmp = new HistogramPanel(HistogramPanel.Colour.red, imgPanel );
		greenCmp = new HistogramPanel(HistogramPanel.Colour.green, imgPanel);
		blueCmp = new HistogramPanel(HistogramPanel.Colour.blue, imgPanel);
		
		histogramPane.addTab("Red", redCmp);
		histogramPane.addTab("Green", greenCmp);
		histogramPane.addTab("Blue", blueCmp);	
		
		f.setJMenuBar(menu);
		f.add(imgPanel);
		f.add(histogramPane);
		f.setVisible(true);
		histogramPane.setVisible(true);
	}

	private void openImage() {
		JFileChooser chooser = new JFileChooser();
		if (chooser.showOpenDialog(f) == JFileChooser.APPROVE_OPTION) {
			try {
			    imgPanel.setImage(ImageIO.read(chooser.getSelectedFile()));
			    redCmp.recalc();
			    blueCmp.recalc();
			    greenCmp.recalc();
			} 
			catch (IOException e) {
				//TODO handle something
			}
		}
	}
	
	private void saveImage() {
		JFileChooser chooser = new JFileChooser();
		if (chooser.showOpenDialog(f) == JFileChooser.APPROVE_OPTION) {
			//TODO make saves possible
		}
	}

}
