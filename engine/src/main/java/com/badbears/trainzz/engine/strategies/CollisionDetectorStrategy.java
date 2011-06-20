package com.badbears.trainzz.engine.strategies;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.badbears.trainzz.engine.ITrain;

public class CollisionDetectorStrategy implements ICollisionDetectorStrategy {

	@Override
	public List<ITrain> detectCollisions(List<ITrain> trains) {
		List<ITrain> collidedTrains = new LinkedList<ITrain>();
		Iterator<ITrain> outerTrainIterator = trains.iterator();
		Iterator<ITrain> innerTrainIterator = trains.iterator();
		while (outerTrainIterator.hasNext()) {
			ITrain outerTrain = outerTrainIterator.next();
			while (innerTrainIterator.hasNext()) {
				ITrain innerTrain = innerTrainIterator.next();
				if (!outerTrain.equals(innerTrain)) {
					if (outerTrain.calculateCoordinates().getDistance(innerTrain.calculateCoordinates()) < 10.0) {
						collidedTrains.add(innerTrain);
						innerTrain.collide();
						
						collidedTrains.add(outerTrain);
						outerTrain.collide();
						System.out.println("Collisiont!");
					}
				}
			}
		}
		trains.removeAll(collidedTrains);
		return collidedTrains;
	}
	
}
