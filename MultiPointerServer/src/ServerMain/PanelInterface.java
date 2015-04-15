package ServerMain;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JPanel;
import javax.swing.JFrame;


public class PanelInterface extends JPanel{
	
	protected static final int WIDTH = 800, HEIGHT = 600;
	protected static final int DEPTH = 1;
	
	private EchoThread thread = new EchoThread();
	@Override
	public String getName(){
		return "Paints";
	}
	
	@Override
	public int getWidth(){
		return WIDTH;
	}
	
	@Override
	public int getHeight(){
		return HEIGHT;
	}
	
	
	
	public PanelInterface(){
	}
	
	@Override
	public void paint(Graphics gl){
		Graphics2D g = (Graphics2D)gl;
		
		// Paint the entire background using a GradientPaint
		g.setPaint(new GradientPaint(0,0,new Color(0,255,255),
				WIDTH,HEIGHT,new Color(0,255,255)));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// Paint circles based on users data
		if(!EchoThread.userPositions.isEmpty()){
			
			System.out.println("Start drawing....");
			// Get users position data from the stack
			//ArrayList<Float> pos = EchoThread.userPositions.pop();
			
			// Calculate laser point position
			//Point pOnScreen = CalculateLoc(pos);
			
			g.setPaint(new GradientPaint(100,0,new Color(0,0,0),
					WIDTH,HEIGHT,new Color(0,0,0)));
			g.setStroke(new BasicStroke(15));
			//g.drawOval(pOnScreen.x, pOnScreen.y, 30, 30);
			g.drawOval(400, 300, 30, 30);
			System.out.print("");
		}
		
	}
	
	public void initialization(){
		
		
		JFrame f = new JFrame();
		f.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		
		f.setContentPane(new PanelInterface());
		f.setSize(WIDTH,HEIGHT);
		f.setVisible(true);
	}
	
	// Calculate laser point position
	public Point CalculateLoc(ArrayList<Float> vec){
		
		float[] rot_vec = new float[4];
		
		for(int i = 0; i < vec.size(); ++i)
			rot_vec[i] = vec.get(i);
		
		System.out.println("current ang : " + rot_vec[0]+ " " + rot_vec[1]+ " " + rot_vec[2]);
		//System.out.println("current angles: " + angles[0]+ " " + angles[1] + " " + angles[2]);
	/*	
		for(float i: angles){
			if(i < boundary[0] || i > boundary[1])
				return;
		}
	*/
		Point current = new Point();
		
		current.x = (int) (0.5 * WIDTH *(1 - DEPTH * Math.tan((double)rot_vec[2])));
		current.y = (int) (0.5 * HEIGHT *(1 - DEPTH * Math.tan((double)rot_vec[0])));
		
		current.x = current.x >= WIDTH ? WIDTH: current.x;
		current.x = current.x <= 0 ? 0 : current.x;
		
		current.y = current.y >= HEIGHT ? HEIGHT: current.y;
		current.y = current.y <= 0 ? 0 : current.y;
		
		return current;
		//System.out.println("The location on the screen is: " + current.x + " " + current.y);
	}
	
	
}
