package com.badbears.trainzz.engine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Board implements IBoard {

	int x,y;
	ArrayList<Train> trains;
	private Set<ITrackElement> elements;
	
	public void addTrain(ITrackElement element, ITrain train) {
		
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
		
	
	
}
