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
	public void addTrain(ITrackElement element) {
		ITrain train = new Train(element);
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

	@Override
	public void addTrackElement(ITrackElement element) {
		this.elements.add(element);
	}

}
