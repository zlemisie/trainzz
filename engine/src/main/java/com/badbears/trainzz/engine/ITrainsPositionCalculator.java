package com.badbears.trainzz.engine;

public interface ITrainsPositionCalculator {

	void calculateTrainsPositions(float milis, Iterable<ITrain> trains,
			Iterable<ITrackElement> elements);

}
