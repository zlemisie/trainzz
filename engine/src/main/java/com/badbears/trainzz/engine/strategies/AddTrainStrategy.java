package com.badbears.trainzz.engine.strategies;

import java.util.HashSet;
import java.util.Iterator;
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
			train = new Train(drawEndElement(elements, trains), 2500, connectionType);
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

	private ITrackElement drawEndElement(Iterable<ITrackElement> elements, Iterable<ITrain> trains) {
		Random random = new Random();
		Set<ITrackElement> endElements = getEndElementsWithNoTrainsOnIt(elements, trains);
		Integer drawedElementNumber = random.nextInt(endElements.size());
		return (ITrackElement) endElements.toArray()[drawedElementNumber];
	}
	
}
