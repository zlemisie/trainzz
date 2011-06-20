package com.badbears.trainzz.engine;

import java.util.List;
import java.util.Set;

public interface IBoard {

	public void addTrackElement(ITrackElement element);
	public abstract void addTrain(ITrain train);
	public abstract void calculateTrainsPositions(float pSecondsElapsed);
	public abstract Set<ITrackElement> getElements();
	public abstract List<ITrain> getTrains();
	public abstract List<ITrain> detectColllisions();
	public abstract ITrain addTrain();
	public abstract ITrain onUpdate(float pSecondsElapsed);
	
}