import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;

import swarm.*;

/**
 * @author Linus Lotz
 * 
 */

class MasterSwarmThread extends SwarmThread
{
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	private BTConnection connection;
	private String deviceName;
	
	public MasterSwarmThread(String dev) {
		deviceName = dev;
		connect();
	}

	public ObjectOutputStream getOutputStream() {
		return outputStream;
	}
	
	private synchronized void connect()
	{
		connection = Bluetooth.connect(Bluetooth.getKnownDevice(deviceName));
		try {
			inputStream = new ObjectInputStream(connection.openInputStream());
			outputStream = new ObjectOutputStream(connection.openOutputStream());
		} catch (IOException e) {
			//shit
		}
	}
	
	private synchronized void disconnect() {
		try {
			inputStream.close();
		} catch (IOException e) {
			// egal
		}
		try {
			outputStream.close();
		} catch (IOException e) {
			// egal
		}
		connection.close();
		
	}
	
	private void check() throws IOException
	{
		try {
			inputStream.wait(50);
		} catch (InterruptedException e) {
			//What happend? disconnect?
			//disconnect();
			//connect();
		}
		if(inputStream.available() > 0)
			readData(inputStream);
		Thread.yield();
	}
	
	private void readData(ObjectInputStream in)
	{
		
	}
	@Override
	public void run() {
		while(true)
		{
			try {
				check();
			} catch (IOException e) {
				// OMGOMGOMGOMG
			}
		}
	}
}

public class SkyNet
{
	public static MasterSwarmThread etThread, walleThread, no5Thread;
	public static void main (String[] aArg)
	throws Exception
	{
		etThread = new MasterSwarmThread("E.T.");
		walleThread = new MasterSwarmThread("Wall-E");
		no5Thread = new MasterSwarmThread("No.5");
		etThread.start();
		walleThread.start();
		no5Thread.start();
	}
}
