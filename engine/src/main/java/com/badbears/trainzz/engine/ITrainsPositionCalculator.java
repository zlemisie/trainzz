package com.badbears.trainzz.engine;

public interface ITrainsPositionCalculator {

	void calculateTrainsPositions(float pSecondsElapsed, Iterable<ITrain> trains,
			Iterable<ITrackElement> elements);

}
