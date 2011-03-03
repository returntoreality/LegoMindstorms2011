import lejos.nxt.*;
/**
 * @author Linus Lotz
 * 
 */


public class SkyNet
{

	
  public static void main (String[] aArg)
  throws Exception
  {
	  LCD.drawString("ALARM!!!!", 0, 0);
	  while(true)
	  {
		  Sound.setVolume(100);
		  Sound.buzz();
		  Thread.sleep(100);
	  }
  }
}
