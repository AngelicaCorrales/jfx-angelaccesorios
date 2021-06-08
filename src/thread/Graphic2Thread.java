package thread;

import ui.AngelaccesoriosGUI;

public class Graphic2Thread extends Thread{
	private AngelaccesoriosGUI angGui;

	public Graphic2Thread(AngelaccesoriosGUI gui) {
		angGui = gui;

	}

	public void run() {

		boolean change=false;
		boolean up=true;
		int y = 2;
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(angGui.getGo()) {
			if(!change) {
				angGui.getIvHeadphones().setVisible(true);
				try {
					sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(up) {
					angGui.getIvHeadphones().setLayoutY((angGui.getIvHeadphones().getLayoutY())-y);
					try {
						sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					angGui.getIvHeadphones().setLayoutY((angGui.getIvHeadphones().getLayoutY())+y);
					try {
						sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if(angGui.getIvHeadphones().getLayoutY()==10) {
					up=false;
				}

				if(angGui.getIvHeadphones().getLayoutY()==280) {
					up=true;
					change=true;
					angGui.getIvHeadphones().setVisible(false);
					try {
						sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					angGui.getIvSpeaker().setVisible(true);
					try {
						sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//x=263
					//y=280
				}

			}else {
				angGui.getIvSpeaker().setVisible(true);
				if(up) {
					angGui.getIvSpeaker().setLayoutY((angGui.getIvSpeaker().getLayoutY())-y);
					try {
						sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					angGui.getIvSpeaker().setLayoutY((angGui.getIvSpeaker().getLayoutY())+y);
					try {
						sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if(angGui.getIvSpeaker().getLayoutY()==10) {
					up=false;
				}

				if(angGui.getIvSpeaker().getLayoutY()==280) {
					up=true;
					change=false;
					angGui.getIvSpeaker().setVisible(false);
					try {
						sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					angGui.getIvHeadphones().setVisible(true);
					try {
						sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//x=287
					//y=280
				}
			}			


		}

	}
}
