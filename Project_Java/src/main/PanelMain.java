package main;

import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.TexturePaint;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class PanelMain extends JPanel{

	private static final int WIDTH = 800, HEIGHT = 375;
	
	public String getName(){
		return "Paints";
	}
	
	public int getWidth(){
		return WIDTH;
	}
	
	public int getHeight(){
		return HEIGHT;
	}
	
	public void paint(Graphics gl){
		Graphics2D g = (Graphics2D) gl;
		
		// Paint the entire background using a GradientPaint
		g.setPaint(new GradientPaint(0,0,new Color(255,255,255),WIDTH,HEIGHT,
				new Color(255,255,255)));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setPaint(new GradientPaint(100,0,new Color(0,0,0),WIDTH,HEIGHT,
				new Color(0,0,0)));
		g.setStroke(new BasicStroke(15));
		g.drawOval(500, 200, 30, 30);
		
	}
	
	public static void main(String[] args){
		JFrame f = new JFrame();
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		
		f.setContentPane(new PanelMain());
		f.setSize(800, 375);
		f.setVisible(true);
	}
}
