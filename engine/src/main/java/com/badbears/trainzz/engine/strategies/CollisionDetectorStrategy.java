package com.badbears.trainzz.engine.strategies;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.badbears.trainzz.engine.ITrain;

public class CollisionDetectorStrategy implements ICollisionDetectorStrategy {

	@Override
	public List<ITrain> detectColllisions(List<ITrain> trains) {
		List<ITrain> collidedTrains = new LinkedList<ITrain>();
		Iterator<ITrain> outerTrainIterator = trains.iterator();
		Iterator<ITrain> innerTrainIterator = trains.iterator();
		while (outerTrainIterator.hasNext()) {
			while (innerTrainIterator.hasNext()) {
				ITrain outerTrain = outerTrainIterator.next();
				ITrain innerTrain = innerTrainIterator.next();
				if (!outerTrain.equals(innerTrain)) {
					if (outerTrain.calculateCoordinates().getDistance(innerTrain.calculateCoordinates()) < 10.0) {
						collidedTrains.add(innerTrain);
						outerTrainIterator.remove();
						innerTrain.collide();
						
						collidedTrains.add(outerTrain);
						innerTrainIterator.remove();
						outerTrain.collide();
					}
				}
			}
		}
		return collidedTrains;
	}
	
}
