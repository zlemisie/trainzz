package com.badbears.trainzz.engine;

public interface ITrain {

	public abstract double getCurrentElementProgress();

	public abstract ITrackElement getCurrentElement();

	public abstract void calculatePostion(int milis);

	public abstract void goToNextElement(ITrackElement nextElement, double nextElementProgress);

}