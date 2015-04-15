package ServerMain;

import javax.swing.JApplet;

public class ServerMain extends JApplet{
	private static final  int PORT = 1149;
	/*
	public static void main(String[] args){
		
		System.out.println("Start networking module");
		Networking net = new Networking(PORT);
		net.start();
		System.out.println("Start drawing panel");
		PanelInterface panel = new PanelInterface();
		setContentPane(panel);
	}
	*/
	
	@Override
	public void init(){
		System.out.println("Start networking module");
		Networking net = new Networking(PORT);
		net.start();
		System.out.println("Start drawing panel");
		setSize(800, 600);
		PanelInterface panel = new PanelInterface();
		setContentPane(panel);
	}
}
