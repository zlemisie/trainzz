package com.badbears.trainzz.engine;

public interface ITrackElement {

	public ICoordinates getStartPoint();
	public ICoordinates getEndPoint();
	public abstract boolean connects(ITrackElement anotherElement);
		
}