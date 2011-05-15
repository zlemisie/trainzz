package com.badbears.trainzz.engine;

import java.util.Set;

public interface IBoard {

	public Set<ITrackElement> getEndElements();
	public Set<ITrackElement> getNextElements(ITrackElement element);
	public void addTrackElement(ITrackElement element);
	public abstract void addTrain(ITrackElement element);
	
}