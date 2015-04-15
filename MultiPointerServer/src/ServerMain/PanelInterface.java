package ServerMain;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class PanelInterface extends JPanel{
	
	protected static final int WIDTH = 800, HEIGHT = 600;
	protected static final int DEPTH = 1;
	private static int x = 400;
	private static int y = 300;
	
	private BufferedImage buffer;
	
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
		this(1);
	}
	
	public PanelInterface(int a){
		initialization();
	}
	
	@Override
	public void paintComponent(Graphics gl){
		
		super.paintComponent(gl);
        gl.drawImage(buffer, 0, 0, this);
	}
	

	public void initialization(){
		this.buffer = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_ARGB);
		setBackground(Color.BLACK); 
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDoubleBuffered(false);
		Timer timer = new Timer(40,new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				
				paintHelper(buffer.getGraphics());
				
			}
		});
		timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
        //startTime = System.currentTimeMillis();
        timer.start();
		
	}
	private void paintHelper(Graphics gl){
		// Paint circles based on users data
		Graphics2D g = (Graphics2D)gl;
		/*
		System.out.println("Start painting...");
		// Paint the entire background using a GradientPaint
		g.setPaint(new GradientPaint(0,0,new Color(0,255,255),
				WIDTH,HEIGHT,new Color(0,255,255)));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		*/
		synchronized( EchoThread.class){
		
			Stack<ArrayList<Float>> userPoses = EchoThread.userPositions;
			if(userPoses.isEmpty()){
				System.out.println("Stack is empty...");
				return;
			}
			
			System.out.println("Start drawing....");
			// Get users position data from the stack
			ArrayList<Float> pos = userPoses.pop();
			
			// Calculate laser point position
			Point pOnScreen = CalculateLoc(pos);
			
			gl.setColor(new Color(0,255,0));
			//g.setStroke(new BasicStroke(15));
			g.drawOval(pOnScreen.x, pOnScreen.y, 30, 30);
			//g.fillRect(x++, y--, 30, 30);
			
			System.out.println(""+ x + " " + y);
			//invalidate();
			repaint();
		}
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
	/*
	public class Animation{
		private Graphics2D gl;
		private long startTime;
		private BufferedImage buffer;
		
		public Animation(){
			
		}
		
		public Animation(Graphics2D gl){
			this.gl = gl;
		}
		
		public void start(){

			this.buffer = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_ARGB);
			setDoubleBuffered(false);
			Timer timer = new Timer(40,new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					
					paintHelper(buffer.getGraphics());
					
				}
			});
			timer.setRepeats(true);
            timer.setCoalesce(true);
            timer.setInitialDelay(0);
            startTime = System.currentTimeMillis();
            timer.start();
		}
		
		private synchronized void paintHelper(Graphics gl){
			// Paint circles based on users data
			Graphics2D g = (Graphics2D)gl;
			System.out.println("Start painting...");
			// Paint the entire background using a GradientPaint
			g.setPaint(new GradientPaint(0,0,new Color(0,255,255),
					WIDTH,HEIGHT,new Color(0,255,255)));
			g.fillRect(0, 0, WIDTH, HEIGHT);
			
			synchronized( EchoThread.class){
			
				Stack<ArrayList<Float>> userPoses = EchoThread.userPositions;
				if(userPoses.isEmpty()){
					System.out.println("Stack is empty...");
					//return;
				}
				
				System.out.println("Start drawing....");
				// Get users position data from the stack
				//ArrayList<Float> pos = userPoses.pop();
				
				// Calculate laser point position
				//Point pOnScreen = CalculateLoc(pos);
				
				g.setColor(new Color(0,255,0));
				//g.setStroke(new BasicStroke(15));
				//g.drawOval(pOnScreen.x, pOnScreen.y, 30, 30);
				//g.fillOval(x, y, 30, 30);
				
				System.out.println(""+ x + " " + y);
				repaint();
			}
		}
		
	}
	*/
}
