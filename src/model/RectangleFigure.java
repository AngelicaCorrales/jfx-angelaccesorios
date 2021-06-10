package model;

public class RectangleFigure {
	private double xCoordinate;
	private double yCoordinate;
	
	/**
	*This is the constructor of the class. <br>
	*<b>name:</b> RectangleFigure. <br>
	*<b>pre</b>: the variables x, y, are already initialized. <br>
	*<b>post:</b> the attributes and relationships of the class have been initialized.<br>
	*@param x Is an integer variable that indicates the x coordinate<br>
	*@param y Is an integer variable that indicates the y coordinate<br>
	*/
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
		yCoordinate-=2;
	}
	
	public void goDown() {
		yCoordinate+=6;
	}
	
	public void goRight() {
		xCoordinate+=4;
	}
	
	public void goLeft() {
		xCoordinate-=4;
	}
}
