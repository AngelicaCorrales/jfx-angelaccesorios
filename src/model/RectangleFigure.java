package model;

public class RectangleFigure {
	private double xCoordinate;
	private double yCoordinate;
	
	public RectangleFigure(double x, double y) {
		setxCoordinate(x);
		setyCoordinate(y);
	}

	public double getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(double xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public double getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(double yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public void goUp() {
		yCoordinate+=5;
	}
	
	public void goRight() {
		xCoordinate+=5;
	}
	
	public void goLeft() {
		xCoordinate-=5;
	}
}
