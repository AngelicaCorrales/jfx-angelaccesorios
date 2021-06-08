package thread;


import javafx.application.Platform;
import model.Angelaccesorios;
import ui.AngelaccesoriosGUI;

public class ProgressThread extends Thread{
	
	private Angelaccesorios angelaccesorios;
	private AngelaccesoriosGUI angelaccesoriosgui;
	private double numLines;
	
	public ProgressThread(AngelaccesoriosGUI aagui,Angelaccesorios aa, double num) {
		angelaccesoriosgui=aagui;
		angelaccesorios=aa;
		numLines=num;
	}
	
	public  void run() {
		
		while(angelaccesorios.getNumProgress()<numLines-1) {
			double numPr=angelaccesorios.getNumProgress();
			try {
				sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Platform.runLater(new Thread() {

				public  void run() {
					double progress=numPr/numLines;

					angelaccesoriosgui.getProgressBar().setProgress(progress);
				}
			});

			try {
				sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		angelaccesoriosgui.getProgressBar().setProgress(1);
		try {
			sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		angelaccesorios.setNumProgress(0);

	}
}
