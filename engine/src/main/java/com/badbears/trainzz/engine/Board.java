package com.badbears.trainzz.engine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.badbears.trainzz.engine.strategies.AddTrainStrategy;
import com.badbears.trainzz.engine.strategies.CollisionDetectorStrategy2;
import com.badbears.trainzz.engine.strategies.IAddTrainStrategy;
import com.badbears.trainzz.engine.strategies.ICollisionDetectorStrategy;

public class Board implements IBoard {

	private List<ITrain> trains;
	private Set<ITrackElement> elements;
	private IAddTrainStrategy addTrainStrategy;
	private ICollisionDetectorStrategy collisionDetector;
	private ITrainsPositionCalculator trainsPositionCalculator;
	
	public Board() {
		trains = new ArrayList<ITrain>();
		elements = new HashSet<ITrackElement>();
		addTrainStrategy = new AddTrainStrategy();
		collisionDetector = new CollisionDetectorStrategy2();
		trainsPositionCalculator = new TrainsPositionCalculator();
	}
	
	@Override
	public ITrain onUpdate(float pSecondsElapsed) {
		List<ITrain> trainsReached  = calculateTrainsPositions(pSecondsElapsed);
	    List<ITrain> trainsCollided = detectColllisions();

	    ITrain trainAdded = null;
	    if (getTrains().size() < 6) {
	    	trainAdded = addTrain();
	    }
	    return trainAdded;
	}
	
	@Override
	public void addTrain(ITrain train) {
		trains.add(train);
	}
	
	@Override
	public ITrain addTrain() {
		ITrain train = addTrainStrategy.addTrain(elements, trains);
		if (train != null) {
			trains.add(train);
		}
		return train;
	}

	@Override
	public void addTrackElement(ITrackElement element) {
		this.elements.add(element);
	}
	
	@Override
	public List<ITrain> calculateTrainsPositions(float pSecondsElapsed) {
		return trainsPositionCalculator.calculateTrainsPositions(pSecondsElapsed, trains, elements);
	}
	
	@Override
	public List<ITrain> detectColllisions() {
		return collisionDetector.detectCollisions(trains);
	}

	@Override
	public Set<ITrackElement> getElements() {
		return elements;
	}

	@Override
	public List<ITrain> getTrains() {
		return trains;
	}

}
