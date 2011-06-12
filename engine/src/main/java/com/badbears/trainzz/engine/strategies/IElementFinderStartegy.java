package com.badbears.trainzz.engine.strategies;

import java.util.Set;

import com.badbears.trainzz.engine.ITrackElement;

public interface IElementFinderStartegy {

	Set<ITrackElement> getNextElements(ITrackElement element,
			Iterable<ITrackElement> elements);

	public abstract ITrackElement findNextElement(ITrackElement currentElement, ITrackElement lastElement,
			Iterable<ITrackElement> elements);

}
