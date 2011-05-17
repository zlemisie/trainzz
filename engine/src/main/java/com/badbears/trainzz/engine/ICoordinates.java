package com.badbears.trainzz.engine;

public interface ICoordinates {

	public abstract void setY(int y);

	public abstract int getY();

	public abstract void setX(int x);

	public abstract int getX();

	public abstract double getDistance(ICoordinates anotherCoordinate);

}