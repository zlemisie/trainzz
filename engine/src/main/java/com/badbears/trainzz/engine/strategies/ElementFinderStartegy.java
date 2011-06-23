package com.badbears.trainzz.engine.strategies;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.badbears.trainzz.engine.ConnectionType;
import com.badbears.trainzz.engine.ITrackElement;

public class ElementFinderStartegy implements IElementFinderStartegy {

	private Random random;
	
	public ElementFinderStartegy() {
		 random = new Random();
	}
	
	@Override
	public Set<ITrackElement> getNextElements(ITrackElement element, Iterable<ITrackElement> elements) {
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
	public ITrackElement findNextElement(ITrackElement currentElement, ITrackElement lastElement, Iterable<ITrackElement> elements) {
		ITrackElement nextElement = null;
		Set<ITrackElement> nextElements = this.getNextElements(currentElement, elements);
		if (lastElement != null) {
			nextElements.remove(lastElement);
			Set<ITrackElement> elementsToRemove = new HashSet<ITrackElement>();
			ConnectionType connectionType = currentElement.getConnectionType(lastElement).reverse();
			for (ITrackElement trackElement:nextElements) {
				if (currentElement.getConnectionType(trackElement) != connectionType) {
					elementsToRemove.add(trackElement);
				}
			}
			nextElements.removeAll(elementsToRemove);
		}

		if (!nextElements.isEmpty()) {
			nextElement = (ITrackElement) (nextElements.toArray())[random.nextInt(nextElements.size())];
		}
		return nextElement;
	}
	
}
