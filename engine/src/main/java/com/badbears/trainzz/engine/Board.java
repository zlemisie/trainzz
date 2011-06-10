package com.badbears.trainzz.engine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
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
	public ITrain addTrain() {
		ITrackElement startElement = drawEndElement();
		if (startElement == null) throw new RuntimeException("BLAD");
		Set<ITrackElement> nextElements = getNextElements(startElement);
		ConnectionType connectionType = startElement.getConnectionType((ITrackElement) nextElements.toArray()[0]);
		ITrain train = new Train(drawEndElement(), 1000, connectionType.reverse());
		trains.add(train);
		return train;
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
	
	public Set<ITrackElement> getEndElementsWithNoTrainsOnIt() {
		Set<ITrackElement> endElements = getEndElements();
		Iterator<ITrackElement> elementIterator = endElements.iterator();
		while (elementIterator.hasNext()) {
			ITrackElement endElement =  elementIterator.next();
			for (ITrain train:trains) {
				if (train.getCurrentElement().equals(endElement)) {
					elementIterator.remove();
				}
			}
		}
		return endElements;
	}

	@Override
	public ITrackElement drawEndElement() {
		Random random = new Random();
		Set<ITrackElement> endElements = getEndElementsWithNoTrainsOnIt();
		Integer drawedElementNumber = random.nextInt(endElements.size());
		return (ITrackElement) endElements.toArray()[drawedElementNumber];
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
		Iterator<ITrain> trainsIterator = trains.iterator();
		while (trainsIterator.hasNext()) {
			ITrain train = trainsIterator.next();
			if (!train.isDestinationReached()) {
				train.calculatePostion(milis);
				if (train.getCurrentElementProgress() >= 100) {
					ITrackElement nextElement = findNextElement(train.getCurrentElement(), train.getLastElement());
					if (nextElement != null) {
						train.goToNextElement(nextElement, nextElementProgressCount(train, nextElement));					
					} else {
						train.reachDestination();
						trainsIterator.remove();
					}
				}
			}
		}
	}
	
	@Override
	public List<ITrain> detectColllisions() {
		List<ITrain> collidedTrains = new LinkedList<ITrain>();
		Iterator<ITrain> outerTrainIterator = trains.iterator();
		Iterator<ITrain> innerTrainIterator = trains.iterator();
		while (outerTrainIterator.hasNext()) {
			while (innerTrainIterator.hasNext()) {
				ITrain outerTrain = outerTrainIterator.next();
				ITrain innerTrain = innerTrainIterator.next();
				if (!outerTrain.equals(innerTrain)) {
					if (outerTrain.calculateCoordinates().getDistance(innerTrain.calculateCoordinates()) < 5.0) {
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
