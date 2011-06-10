package com.badbears.trainzz.engine;

public enum ConnectionType {

	START_POINT,
	END_POINT;
	
	public ConnectionType reverse() {
		ConnectionType result = null;
		if (this == START_POINT) {
			result = END_POINT;
		} else if (this == END_POINT) {
			result = START_POINT;
		}
		return result;
	}
}
