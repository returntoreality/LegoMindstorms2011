package swarm;
import lejos.nxt.comm.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Swarm{
	
	static class ClientSwarmThread extends SwarmThread
	{
		public ClientSwarmThread() {
			BTConnection swarmcon = Bluetooth.waitForConnection();
			try {
				SkyIn = new ObjectInputStream(swarmcon.openInputStream());
				SkyOut = new ObjectOutputStream(swarmcon.openOutputStream());
			} catch (IOException e) {
				// y r u no work
			}
		}
	}
	
	static private ObjectInputStream SkyIn;
	static private ObjectOutputStream SkyOut;
	static private Thread swarmthread;
	static void joinSwarm(){
		
		//start the fuckin' swarmthread NOW!
		swarmthread = new ClientSwarmThread();
		swarmthread.start();
		
	}

}
