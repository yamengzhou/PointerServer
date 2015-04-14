import java.awt.AWTException;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;


public class CursorControl {
	private static Point start;
	private static Point current;
	private final static int WIDTH = 1920;
	private final static int HEIGHT = 1080;
	private final static float DEPTH = 1.414f;
	private final static int BOUNDARY = 40;
	private static float[] boundary;
	private float[] angles = new float[3];
	
	public CursorControl(){
		start = new Point(0,0);
		current = new Point(0,0);
		angles[0] = 0;
		angles[1] = 0;
		angles[2] = 0;
		
		boundary = new float[4];
		
		boundary[0] = (float)(Math.toRadians(-BOUNDARY));
		boundary[1] = (float)(Math.toRadians(BOUNDARY));
		
	}
	
	public void CalculateLoc(float[] rot_vec){
		
		System.out.println("current ang : " + rot_vec[0]+ " " + rot_vec[1]+ " " + rot_vec[2]);
		//System.out.println("current angles: " + angles[0]+ " " + angles[1] + " " + angles[2]);
	/*	
		for(float i: angles){
			if(i < boundary[0] || i > boundary[1])
				return;
		}
	*/
		current.x = (int) (0.5 * WIDTH *(1 - DEPTH * Math.tan((double)rot_vec[2])));
		current.y = (int) (0.5 * HEIGHT *(1 - DEPTH * Math.tan((double)rot_vec[0])));
		
		current.x = current.x >= WIDTH ? WIDTH: current.x;
		current.x = current.x <= 0 ? 0 : current.x;
		
		current.y = current.y >= HEIGHT ? HEIGHT: current.y;
		current.y = current.y <= 0 ? 0 : current.y;
		
		
		//System.out.println("The location on the screen is: " + current.x + " " + current.y);
	}
	
	public void moveMouse() {
	    GraphicsEnvironment ge = 
	    GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice[] gs = ge.getScreenDevices();

	    // Search the devices for the one that draws the specified point.
	    for (GraphicsDevice device: gs) { 
	        GraphicsConfiguration[] configurations =
	            device.getConfigurations();
	        for (GraphicsConfiguration config: configurations) {
	            Rectangle bounds = config.getBounds();
	            if(bounds.contains(current)) {
	                // Set point to screen coordinates.
	                Point b = bounds.getLocation(); 
	                Point s = new Point(current.x - b.x, current.y - b.y);

	                try {
	                    Robot r = new Robot(device);
	                    
	                    r.mouseMove(s.x, s.y);
	                    
	                    while(true){
	                    	if(s.x == start.x && s.y == start.y)
	                    		break;
	                    	if(s.x < start.x)
	                    		r.mouseMove(--start.x, start.y);
	                    	if(s.y < start.y)
	                    		r.mouseMove(start.x, --start.y);
	                    	if(s.x > start.x)
	                    		r.mouseMove(++start.x, start.y);
	                    	if(s.y > start.y)
	                    		r.mouseMove(start.x, ++start.y);
	                    }
	                    
	                } catch (AWTException e) {
	                    e.printStackTrace();
	                }
	                
	                start.x = s.x;
	                start.y = s.y;
	                return;
	            }
	        }
	    }
	    // Couldn't move to the point, it may be off screen.
	    return;
	}
	
	
	
}
