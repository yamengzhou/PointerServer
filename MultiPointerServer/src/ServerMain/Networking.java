/******************************************************************/
// Author: Yameng Zhou
// Date: April 15
// Description: This is the source code for a cellphone pointer, Server
// Side.
// This is only the test version.
//
/******************************************************************/

package ServerMain;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Networking implements Runnable{
	private Thread t;
	private static int PORT;
	private String netName = "Networking module";
	public Networking(){}
	
	public Networking(int PORT){
		this.PORT = PORT;
	}
	
	@SuppressWarnings("resource")
	public void run(){
		if(PORT == 0){
			System.err.println("No port number is set or the port number is not valid");
			return;
		}
		
		ServerSocket serverSocket = null;
		Socket socket =null;
		
		try{
			serverSocket = new ServerSocket(PORT);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		while(true){
			try{
				socket = serverSocket.accept();
			}catch(IOException e){
				System.out.println("I/O error: " + e);
			}
			
			new EchoThread(socket).start();
		}
	}
	
	public void start(){
		if(t == null){
			t = new Thread(this,netName);
			t.start();
		}
	}
}
