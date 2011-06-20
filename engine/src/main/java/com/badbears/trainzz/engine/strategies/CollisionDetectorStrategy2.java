package com.badbears.trainzz.engine.strategies;

import java.util.LinkedList;
import java.util.List;

import com.badbears.trainzz.engine.ICoordinates;
import com.badbears.trainzz.engine.ITrain;

public class CollisionDetectorStrategy2 implements ICollisionDetectorStrategy {

	@Override
	public List<ITrain> detectCollisions(List<ITrain> trains) {
		List<ITrain> collidedTrains = new LinkedList<ITrain>();
		for (ITrain innerTrain: trains) {
			for (ITrain outerTrain:trains) {
				if (!outerTrain.equals(innerTrain)) {
					if (isCollided(outerTrain, innerTrain)) {
						collidedTrains.add(innerTrain);
						innerTrain.collide();
						
						collidedTrains.add(outerTrain);
						outerTrain.collide();
						System.out.println("Collision!");
					}
				}
			}
		}
		trains.removeAll(collidedTrains);
		return collidedTrains;
	}

	private boolean isCollided(ITrain outerTrain, ITrain innerTrain) {
		
		boolean result = true;
		int left1, left2, right1, right2, top1, top2, bottom1, bottom2;
		ICoordinates outerTrainCoordinates = outerTrain.calculateCoordinates();
		ICoordinates innerTrainCoordinates = innerTrain.calculateCoordinates();

		left1 = outerTrainCoordinates.getX();
		left2 = innerTrainCoordinates.getX();
		right1 = outerTrainCoordinates.getX() + 10;
		right2 = innerTrainCoordinates.getX() + 10;
		top1 = outerTrainCoordinates.getY();
		top2 = innerTrainCoordinates.getY();
		bottom1 = outerTrainCoordinates.getY() + 10;
		bottom2 = innerTrainCoordinates.getY() + 10;
		
		if (bottom1 < top2) {
			result = false;
		} else if (top1 > bottom2) {
			result = false;
		} else if (right1 < left2) { 
			result = false;
		} else if (left1 > right2) {
			result = false;
		}

		return result;
	}
}
