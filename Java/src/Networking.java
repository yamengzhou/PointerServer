import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.Arrays;


public class Networking {
	private static final int LENGTH = 16;
	private CursorControl cc;
	public Networking(){}
	
	public void connecting() throws IOException{
		
		int bytesRead;

		// Create cursor controller
		cc = new CursorControl();
		
		// Create socket
		ServerSocket servsock = new ServerSocket(1149);
		while(true){
			//System.out.println("Waiting...");
			long start = System.currentTimeMillis();
			Socket sock = servsock.accept();
			//System.out.println("Accepted connection: " + sock);
			
			// receive file
			byte[] mybytearray = new byte[LENGTH];
			InputStream is = sock.getInputStream();
			bytesRead = is.read(mybytearray, 0, mybytearray.length);
/*
			float f = ByteBuffer.wrap(mybytearray, 0, 4).getFloat();
			System.out.println("The actual data is: "+ f);
			
*/
			ByteBuffer bytebuffer = ByteBuffer.wrap(mybytearray, 0, bytesRead);
			FloatBuffer floatbuffer = bytebuffer.asFloatBuffer();
			float[] result = new float[floatbuffer.remaining()];
			floatbuffer.get(result);
			
			long end = System.currentTimeMillis();
			cc.CalculateLoc(result);
			cc.moveMouse();
			
			//System.out.println(Arrays.toString(result));
			
			
			//System.out.println(end - start);
			sock.close();
		}
	}
}
