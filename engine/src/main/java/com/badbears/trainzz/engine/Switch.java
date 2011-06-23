package com.badbears.trainzz.engine;

import java.util.Set;

public class Switch implements ITrackElement {

	Set<ISwitchPair> switchPairs;
	
	public Switch(Set<ISwitchPair> switchPairs) {
		this.switchPairs = switchPairs;
	}
	
	@Override
	public ICoordinates getStartPoint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ICoordinates getEndPoint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean connects(ITrackElement anotherElement) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ICoordinates calculateCoordinates(double progress) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConnectionType getConnectionType(ITrackElement anotherElement) {
		// TODO Auto-generated method stub
		return null;
	}

}
