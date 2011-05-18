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
	
	private ITrackElement findNextElement(ITrackElement element) {
		Set<ITrackElement> nextElements = this.getNextElements(element);
		return (ITrackElement) (nextElements.toArray())[0];
	}

	@Override
	public void addTrackElement(ITrackElement element) {
		this.elements.add(element);
	}
	
	@Override
	public void calculateTrainsPositions(int milis) {
		for (ITrain train:trains) {
			train.calculatePostion(milis);
			if (train.getCurrentElementProgress() >= 100) {
//				train.goToNextElement(findNextElement(train.getCurrentElement()), 0);
				ITrackElement e = findNextElement(train.getCurrentElement());
				train.goToNextElement(e, progressCount(train, e));
			}
		}
	}

	private double progressCount(ITrain train, ITrackElement e) {
		return (train.getCurrentElementProgress()-100)*train.getCurrentElement().getLength()/e.getLength();
	}

	@Override
	public Set<ITrackElement> getElements() {
		return elements;
	}

}
