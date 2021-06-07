package model;

public class CircleFigure {
	
	private double xCoordinate;
	private double yCoordinate;
	private double radius;
	
	public CircleFigure(double x, double y, double r) {
		setxCoordinate(x);
		setyCoordinate(y);
		setRadius(r);
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

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public void clickOn() {
		radius +=10;
	}
	
	public void goToCenter() {
		xCoordinate-=60;
		yCoordinate-=50;
	}
	
	public void goToRight() {
		xCoordinate-=130;
	}
}
