package com.badbears.trainzz.engine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Board implements IBoard {

	private List<ITrain> trains;
	private Set<ITrackElement> elements;
	
	public Board() {
		trains = new ArrayList<ITrain>();
		elements = new HashSet<ITrackElement>();
	}
	
	@Override
	public void addTrain(ITrain train) {
		trains.add(train);
	}
	
	@Override
	public Set<ITrackElement> getEndElements() {
		Set<ITrackElement> result = new HashSet<ITrackElement>();
		for (ITrackElement element:elements) {
			Set<ITrackElement> nextElements = this.getNextElements(element);
			if (nextElements.isEmpty()
					|| nextElements.size() == 1) {
				result.add(element);
			}
		}
		return result;
	}

	@Override
	public Set<ITrackElement> getNextElements(ITrackElement element) {
		Set<ITrackElement> result = new HashSet<ITrackElement>();
		for (ITrackElement iterabaleElement:elements) {
			if (!element.equals(iterabaleElement)
					&& element.connects(iterabaleElement)) {
				result.add(iterabaleElement);
			}
		}
		return result;
	}
	
	private ITrackElement findNextElement(ITrackElement currentElement, ITrackElement lastElement) {
		ITrackElement nextElement = null;
		Set<ITrackElement> nextElements = this.getNextElements(currentElement);
		if (lastElement != null) {
			nextElements.remove(lastElement);
		}
		if (!nextElements.isEmpty()) {
			nextElement = (ITrackElement) (nextElements.toArray())[0];
		}
		return nextElement;
	}

	@Override
	public void addTrackElement(ITrackElement element) {
		this.elements.add(element);
	}
	
	@Override
	public void calculateTrainsPositions(float milis) {
		for (ITrain train:trains) {
			if (!train.isDestinationReached()) {
				train.calculatePostion(milis);
				if (train.getCurrentElementProgress() >= 100) {
					ITrackElement nextElement = findNextElement(train.getCurrentElement(), train.getLastElement());
					if (nextElement != null) {
						train.goToNextElement(nextElement, nextElementProgressCount(train, nextElement));					
					} else {
						train.reachDestination();
					}
				}
			}
		}
	}

	private double nextElementProgressCount(ITrain train, ITrackElement nextElement) {
		return (train.getCurrentElementProgress()-100)*train.getCurrentElement().getLength()/nextElement.getLength();
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
