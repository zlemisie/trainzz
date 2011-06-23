package com.badbears.trainzz.engine;

import java.util.List;

public interface ITrainsPositionCalculator {

	List<ITrain> calculateTrainsPositions(float pSecondsElapsed, Iterable<ITrain> trains,
			Iterable<ITrackElement> elements);

}
