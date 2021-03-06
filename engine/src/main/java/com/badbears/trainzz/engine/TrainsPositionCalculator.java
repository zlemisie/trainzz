package com.badbears.trainzz.engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badbears.trainzz.engine.strategies.ElementFinderStartegy;
import com.badbears.trainzz.engine.strategies.IElementFinderStartegy;

public class TrainsPositionCalculator implements ITrainsPositionCalculator {

	IElementFinderStartegy elementFinderStrategy;
	
	public TrainsPositionCalculator() {
		elementFinderStrategy = new ElementFinderStartegy();
	}
	
	@Override
	public List<ITrain> calculateTrainsPositions(float pSecondsElapsed, Iterable<ITrain> trains, Iterable<ITrackElement> elements) {
		List<ITrain> trainReached = new ArrayList<ITrain>();
		Iterator<ITrain> trainsIterator = trains.iterator();
		while (trainsIterator.hasNext()) {
			ITrain train = trainsIterator.next();
			if (!train.isDestinationReached()) {
				train.calculatePostion(pSecondsElapsed);
				if (train.getCurrentElementProgress() >= 100) {
					ITrackElement nextElement = elementFinderStrategy.findNextElement(train.getCurrentElement(), train.getLastElement(), elements);
					if (nextElement != null) {
						train.goToNextElement(nextElement, nextElementProgressCount(train, nextElement));					
					} else {
						train.reachDestination();
						trainsIterator.remove();
						trainReached.add(train);
					}
				}
			}
		}
		return trainReached;
	}
	
	private double nextElementProgressCount(ITrain train, ITrackElement nextElement) {
		return (train.getCurrentElementProgress()-100)*train.getCurrentElement().getLength()/nextElement.getLength();
	}
	
}
