package com.badbears.trainzz.engine;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Switch implements ITrackElement {

	private ICoordinates location;
	private Set<ISwitchPair> switchPairs;
	
	public Switch(int x, int y, ISwitchPair... switchPairs) {
		this.location = new Coordinates(x, y);
		this.switchPairs = new HashSet<ISwitchPair>(Arrays.asList(new SwitchPair()));
	}
	
	@Override
	public ICoordinates getStartPoint() {
		return location;
	}

	@Override
	public ICoordinates getEndPoint() {
		return location;
	}

	@Override
	public boolean connects(ITrackElement anotherElement) {
		return anotherElement.getConnectionType(this) != null;
	}

	@Override
	public double getLength() {
		return 0;
	}

	@Override
	public ICoordinates calculateCoordinates(double progress) {
		return location;
	}

	@Override
	public ConnectionType getConnectionType(ITrackElement anotherElement) {
		return anotherElement.getConnectionType(this);
	}

}
