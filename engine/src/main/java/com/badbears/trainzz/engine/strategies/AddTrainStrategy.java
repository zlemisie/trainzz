package com.badbears.trainzz.engine.strategies;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.badbears.trainzz.engine.ConnectionType;
import com.badbears.trainzz.engine.ITrackElement;
import com.badbears.trainzz.engine.ITrain;
import com.badbears.trainzz.engine.Train;

public class AddTrainStrategy implements IAddTrainStrategy {

	IElementFinderStartegy elementFinderStrategy;
	
	public AddTrainStrategy() {
		elementFinderStrategy = new ElementFinderStartegy();
	}
	
	@Override
	public ITrain addTrain(Iterable<ITrackElement> elements, Iterable<ITrain> trains) {
		ITrain train = null;
		ITrackElement startElement = drawEndElement(elements, trains);
		if (startElement != null) {
			Set<ITrackElement> nextElements = elementFinderStrategy.getNextElements(startElement, elements);
			ConnectionType connectionType = startElement.getConnectionType((ITrackElement) nextElements.toArray()[0]);
			train = new Train(startElement, 2500, connectionType.reverse());
		}
		return train;
	}
	
	private Set<ITrackElement> getEndElements(Iterable<ITrackElement> elements) {
		Set<ITrackElement> result = new HashSet<ITrackElement>();
		for (ITrackElement element:elements) {
			Set<ITrackElement> nextElements = elementFinderStrategy.getNextElements(element, elements);
			if (nextElements.isEmpty()
					|| nextElements.size() == 1) {
				result.add(element);
			}
		}
		return result;
	}
	
	private Set<ITrackElement> getEndElementsWithNoTrainsOnIt(Iterable<ITrackElement> elements, Iterable<ITrain> trains) {
		Set<ITrackElement> endElements = this.getEndElements(elements);
		Iterator<ITrackElement> elementIterator = endElements.iterator();
		List<ITrackElement> elementsToRemove = new ArrayList<ITrackElement>();
		while (elementIterator.hasNext()) {
			ITrackElement endElement =  elementIterator.next();
			for (ITrain train:trains) {
				if (train.getCurrentElement().equals(endElement)) {
					elementsToRemove.add(endElement);
				}
			}
		}
		endElements.removeAll(elementsToRemove);
		return endElements;
	}

	private ITrackElement drawEndElement(Iterable<ITrackElement> elements, Iterable<ITrain> trains) {
		Random random = new Random();
		Set<ITrackElement> endElements = getEndElementsWithNoTrainsOnIt(elements, trains);
		Integer drawedElementNumber = random.nextInt(endElements.size());
		return (ITrackElement) endElements.toArray()[drawedElementNumber];
	}
	
}
