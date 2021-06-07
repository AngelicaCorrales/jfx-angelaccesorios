package thread;

import model.CircleFigure;
import ui.AngelaccesoriosGUI;

public class PointerThread extends Thread{
	
	private AngelaccesoriosGUI angGui;
	private CircleFigure pointer;

	public PointerThread(CircleFigure p, AngelaccesoriosGUI gui) {
		pointer = p;
		angGui = gui;
	}
	
	public void run() {
		try {
			Thread.sleep(1500);
			pointer.goToCenter();
			angGui.updateGUI();
			Thread.sleep(300);
			pointer.clickOn();
			angGui.updateGUI();
			Thread.sleep(200);
			pointer.setRadius(15);
			angGui.updateGUI();
			pointer.goToRight();
			angGui.updateGUI();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
