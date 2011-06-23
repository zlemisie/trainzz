package com.badbears.trainzz.engine;

public class SwitchPair implements ISwitchPair {

	private ITrackElement from;
	private ITrackElement to;
	
	public SwitchPair() {
		
	}
	
	public SwitchPair(ITrackElement from, ITrackElement to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public String toString() {
		return "SwitchPair [from=" + from + ", to=" + to + "]";
	}
	
}
