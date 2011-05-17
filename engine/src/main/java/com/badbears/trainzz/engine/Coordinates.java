package com.badbears.trainzz.engine;

public class Coordinates implements ICoordinates {

	private int x;
	private int y;
	
	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	@Override
	public double getDistance(ICoordinates anotherCoordinate) {
		double a =  Math.abs(this.getX() - anotherCoordinate.getX());
		double b = Math.abs(this.getY() - anotherCoordinate.getY());		
		double result = Math.sqrt(a*a+b*b);
		return result;
	}

	
	
}
