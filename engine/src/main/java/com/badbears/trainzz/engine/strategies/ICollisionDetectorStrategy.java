package com.badbears.trainzz.engine.strategies;

import java.util.List;

import com.badbears.trainzz.engine.ITrain;

public interface ICollisionDetectorStrategy {

	List<ITrain> detectColllisions(List<ITrain> trains);

}
