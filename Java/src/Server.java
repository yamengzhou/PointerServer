import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.Arrays;


public class Server {
	
	public static void main(String[] args) throws IOException{
		Networking net = new Networking();
		net.connecting();
	}
}
