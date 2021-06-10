package thread;


import model.RectangleFigure;
import ui.AngelaccesoriosGUI;

public class Graphic1Thread extends Thread {

	private AngelaccesoriosGUI angGui;
	private RectangleFigure one;
	private RectangleFigure two;

	public Graphic1Thread(RectangleFigure on, RectangleFigure tw, AngelaccesoriosGUI gui) {
		angGui = gui;
		one = on;
		two = tw;
	}

	public void run() {
		while(angGui.getStop()==false) {
			try {
				angGui.getAppsImageV().setLayoutX(277);
				Thread.sleep(50);
				angGui.getAppsImageV().setLayoutY(124);
				Thread.sleep(50);
				angGui.getAppsImageV().setVisible(true);
				Thread.sleep(3000);
				angGui.getFirstRectangle().setVisible(true);
				Thread.sleep(50);
				moveImage(1);
				moveRectangleToLeft(one, 1);
				angGui.getSecondRectangle().setVisible(true);
				moveImage(1);
				moveRectangleToLeft(one, 1);
				moveRectangleToLeft(two, 2);
				angGui.getThirdRectangle().setVisible(true);
				moveRectangleUp(two, 2);
				moveRectangleToRight(one,1);
				moveImage(2);
				angGui.getThirdRectangle().setVisible(false);
				moveRectangleDown(one,1);
				moveImage(2);
				Thread.sleep(1000);
				angGui.getFirstRectangle().setVisible(false);
				Thread.sleep(50);
				one.setxCoordinate(412);
				Thread.sleep(50);
				one.setyCoordinate(138);
				Thread.sleep(50);
				two.setxCoordinate(412);
				Thread.sleep(50);
				two.setyCoordinate(138);
				Thread.sleep(50);
				angGui.getFirstRectangle().setLayoutX(412);
				Thread.sleep(50);
				angGui.getFirstRectangle().setLayoutY(138);
				Thread.sleep(50);
				angGui.getSecondRectangle().setLayoutX(412);
				Thread.sleep(50);
				angGui.getSecondRectangle().setLayoutY(138);
				Thread.sleep(50);
				angGui.getFirstRectangle().setWidth(92);
				Thread.sleep(50);
				angGui.getFirstRectangle().setHeight(170);
				Thread.sleep(50);
				angGui.getSecondRectangle().setWidth(92);
				Thread.sleep(50);
				angGui.getSecondRectangle().setHeight(170);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	public void moveImage(int side) throws InterruptedException {
		int x = 0;
		int cont = 0;
		while(cont<24) {
			if(side==1) {
				angGui.getAppsImageV().setLayoutX((angGui.getAppsImageV().getLayoutX())-x);
				Thread.sleep(40);
				x+=2;
			}else {
				angGui.getAppsImageV().setLayoutX((angGui.getAppsImageV().getLayoutX())+x);
				Thread.sleep(40);
				x+=2;
			}
			cont+=2;
		}
	}

	public void moveRectangleToLeft(RectangleFigure r, int num) throws InterruptedException {
		int x=0;
		while(x<128) {
			r.goLeft();
			Thread.sleep(30);
			callGUI(num);
			x+=4;
		}
	}

	public void moveRectangleToRight(RectangleFigure r, int num) throws InterruptedException {
		int x=0;
		while(x<128) {
			r.goRight();
			Thread.sleep(30);
			callGUI(num);
			x+=4;
		}
	}

	public void moveRectangleUp(RectangleFigure r, int num) throws InterruptedException {
		int x = 0;
		while(x<40) {
			r.goUp();
			Thread.sleep(30);
			callGUI(num);
			Thread.sleep(100);
			angGui.getSecondRectangle().setHeight(angGui.getSecondRectangle().getHeight()-x);
			callGUI(num);
			x+=2;
		}
	}

	public void moveRectangleDown(RectangleFigure r, int num) throws InterruptedException {
		int x = 0;
		while(x<40) {
			r.goDown();
			Thread.sleep(30);
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
			angGui.updateGUI2();
		}
	}
}
