package ServerMain;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Stack;
/******************************************************************/
//Author: Yameng Zhou
//Date: April 15
//Description: This is the source code for a cellphone pointer, Server
//Side.
//This is only the test version.
//
/******************************************************************/
public class EchoThread extends Thread{
	public static Stack<ArrayList<Float>> userPositions = new Stack<ArrayList<Float>>();
	protected Socket socket;
	protected int LENGTH = 16;
	
	public EchoThread(){}
	
	public EchoThread(Socket clientSocket){
		this.socket = clientSocket;
	}
	

	@Override
	public void run(){
		try{
			int bytesRead;
			
			byte[] mybytearray = new byte[LENGTH];
			InputStream is = socket.getInputStream();
			bytesRead = is.read(mybytearray, 0, mybytearray.length);
	/*
			float f = ByteBuffer.wrap(mybytearray, 0, 4).getFloat();
			System.out.println("The actual data is: "+ f);
			
	*/
			ByteBuffer bytebuffer = ByteBuffer.wrap(mybytearray, 0, bytesRead);
			FloatBuffer floatbuffer = bytebuffer.asFloatBuffer();
			float[] result = new float[floatbuffer.remaining()];
			floatbuffer.get(result);
			
			//System.out.println("The result from cellphone is: " + result[0] + " " + result[1] + " " + result[2]);
			
			ArrayList<Float> freshPos = new ArrayList<Float>();
			for(int i = 0; i < result.length; ++i)
				freshPos.add(result[i]);
			
			synchronized(userPositions){
				userPositions.push(freshPos);
			}
			socket.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
}
