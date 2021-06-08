package thread;


import model.RectangleFigure;
import ui.AngelaccesoriosGUI;

public class Graphic1Thread extends Thread {

	private AngelaccesoriosGUI angGui;
	private RectangleFigure one;
	private RectangleFigure two;
	private RectangleFigure three;
	private RectangleFigure four;

	public Graphic1Thread(RectangleFigure on, RectangleFigure tw, RectangleFigure te, RectangleFigure fr, AngelaccesoriosGUI gui) {
		angGui = gui;
		one = on;
		two = tw;
		three = te;
		four = fr;
	}

	public void run() {
		angGui.getAppsImageV().setVisible(true);
		angGui.getFirstRectangle().setVisible(true);
		try {
			Thread.sleep(2000);
			moveImage(1);
			moveRectangleToLeft(one, 1);
			angGui.getFourthRectangle().setVisible(true);
			moveImage(1);
			moveRectangleToLeft(one, 1);
			moveRectangleToLeft(four, 4);
			angGui.getThirdRectangle().setVisible(true);
			moveRectangleUp(four, 4);
			moveRectangleToRight(one,1);
			moveImage(2);
			angGui.getThirdRectangle().setVisible(false);
			moveRectangleDown(one,1);
			moveImage(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public void moveImage(int side) throws InterruptedException {
		int x = 0;
		int cont = 0;
		while(cont<24) {
			if(side==1) {
				angGui.getAppsImageV().setLayoutX((angGui.getAppsImageV().getLayoutX())-x);
				Thread.sleep(15);
				x+=2;
			}else {
				angGui.getAppsImageV().setLayoutX((angGui.getAppsImageV().getLayoutX())+x);
				Thread.sleep(15);
				x+=2;
			}
			cont+=2;
		}
	}

	public void moveRectangleToLeft(RectangleFigure r, int num) throws InterruptedException {
		int x=0;
		while(x<128) {
			r.goLeft();
			Thread.sleep(10);
			callGUI(num);
			x+=4;
		}
	}
	
	public void moveRectangleToRight(RectangleFigure r, int num) throws InterruptedException {
		int x=0;
		while(x<128) {
			r.goRight();
			Thread.sleep(10);
			callGUI(num);
			x+=4;
		}
	}
	
	public void moveRectangleUp(RectangleFigure r, int num) throws InterruptedException {
		int x = 0;
		while(x<40) {
			r.goUp();
			Thread.sleep(15);
			callGUI(num);
			Thread.sleep(100);
			angGui.getFourthRectangle().setHeight(angGui.getFourthRectangle().getHeight()-x);
			callGUI(num);
			x+=2;
		}
	}
	
	public void moveRectangleDown(RectangleFigure r, int num) throws InterruptedException {
		int x = 0;
		while(x<40) {
			r.goDown();
			Thread.sleep(15);
			callGUI(num);
			Thread.sleep(100);
			angGui.getFirstRectangle().setHeight(angGui.getFirstRectangle().getHeight()-x);
			callGUI(num);
			x+=2;
		}
	}
	
	public void callGUI(int num) {
		if(num==1) {
			angGui.updateGUI1();
		}else {
			angGui.updateGUI4();
		}
	}
}
