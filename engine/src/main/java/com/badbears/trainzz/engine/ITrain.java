package com.badbears.trainzz.engine;

public interface ITrain {

	public abstract double getCurrentElementProgress();

	public abstract ITrackElement getCurrentElement();

	public abstract void calculatePostion(float milis);

	public abstract void goToNextElement(ITrackElement nextElement, double nextElementProgress);

	public abstract ICoordinates calculateCoordinates();

	public abstract ITrackElement getLastElement();

	public abstract boolean isDestinationReached();

	public abstract void reachDestination();

}