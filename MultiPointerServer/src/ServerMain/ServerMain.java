package ServerMain;

public class ServerMain {
	private static final  int PORT = 1149;
	
	public static void main(String[] args){
		
		System.out.println("Start networking module");
		Networking net = new Networking(PORT);
		net.start();
		System.out.println("Start drawing panel");
		PanelInterface panel = new PanelInterface();
		panel.initialization();
	}
}
