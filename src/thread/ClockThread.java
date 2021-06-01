package thread;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Platform;
import ui.AngelaccesoriosGUI;

public class ClockThread extends Thread{
	
	public final static String TIME_FORMAT = "yyyy-MM-dd h:mm:ss a";
	private SimpleDateFormat format;
	private AngelaccesoriosGUI angelaccesoriosGUI;
	
	public ClockThread(AngelaccesoriosGUI aagui) {
		format=new SimpleDateFormat(TIME_FORMAT);
		angelaccesoriosGUI=aagui;
	}
	
	public void run() {
		while(angelaccesoriosGUI.getRunClock()) {
			Date date=new Date();
			String clock=format.format(date);
			Platform.runLater(new Thread() {
				public void run() {
					angelaccesoriosGUI.getLbClock().setText(clock);
				}
			});
			
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
