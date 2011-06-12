package com.badbears.trainzz.engine.strategies;

import com.badbears.trainzz.engine.ITrackElement;
import com.badbears.trainzz.engine.ITrain;

public interface IAddTrainStrategy {

	ITrain addTrain(Iterable<ITrackElement> elements, Iterable<ITrain> trains);

}
