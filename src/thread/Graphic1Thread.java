package thread;


import model.RectangleFigure;
import ui.AngelaccesoriosGUI;

public class Graphic1Thread extends Thread {
	
	private AngelaccesoriosGUI angGui;
	private RectangleFigure one;
	private RectangleFigure two;
	private RectangleFigure three;
	private RectangleFigure four;
	private RectangleFigure five;
	
	public Graphic1Thread(RectangleFigure on, RectangleFigure tw, RectangleFigure te, RectangleFigure fr, RectangleFigure fv, AngelaccesoriosGUI gui) {
		angGui = gui;
		one = on;
		two = tw;
		three = te;
		four = fr;
		five = fv;
	}
	
	public void run() {
		angGui.getAppsImageV().setVisible(true);
		try {
			Thread.sleep(2000);
			int x = 2;
			while(x<24) {
				angGui.getAppsImageV().setLayoutX((angGui.getAppsImageV().getLayoutX())-x);
				Thread.sleep(15);
				x+=2;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
