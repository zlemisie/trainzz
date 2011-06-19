package com.badbears.trainzz.engine;

public abstract class TrackElement implements ITrackElement {

	private ICoordinates startPoint;
	private ICoordinates endPoint;
	
	public TrackElement(ICoordinates startPoint, ICoordinates endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	public TrackElement(int x1, int y1, int x2, int y2) {
		ICoordinates startPoint = new Coordinates(x1, y1);
		ICoordinates endPoint = new Coordinates(x2, y2);
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
		
	@Override
	public ICoordinates getStartPoint() {
		return startPoint;
	}

	@Override
	public ICoordinates getEndPoint() {
		return endPoint;
	}
	
	public ICoordinates getPoint(ConnectionType connectionType) {
		ICoordinates result = null;
		if (connectionType == ConnectionType.START_POINT) {
			result = startPoint; 
		} else if (connectionType == ConnectionType.END_POINT) {
			result = endPoint; 
		}
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((endPoint == null) ? 0 : endPoint.hashCode());
		result = prime * result
				+ ((startPoint == null) ? 0 : startPoint.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrackElement other = (TrackElement) obj;
		if (endPoint == null) {
			if (other.endPoint != null)
				return false;
		} else if (!endPoint.equals(other.endPoint))
			return false;
		if (startPoint == null) {
			if (other.startPoint != null)
				return false;
		} else if (!startPoint.equals(other.startPoint))
			return false;
		return true;
	}
	
	@Override
	public boolean connects(ITrackElement anotherElement) {
		boolean result = getConnectionType(anotherElement) != null;
		return result;
	}
	
	@Override
	public ConnectionType getConnectionType(ITrackElement anotherElement) {
		ConnectionType result = null;
		if (anotherElement != null) {
			if (anotherElement.getStartPoint().equals(this.getStartPoint())
					|| anotherElement.getEndPoint().equals(this.getStartPoint())) {
				result = ConnectionType.START_POINT;
			} else if (anotherElement.getStartPoint().equals(this.getEndPoint())
					|| anotherElement.getEndPoint().equals(this.getEndPoint())) {
				result = ConnectionType.END_POINT;
			}
		}
		return result;
	}
	
	@Override
	public abstract ICoordinates calculateCoordinates(double progress);

	@Override
	public String toString() {
		return "TrackElement [startPoint=" + startPoint + ", endPoint="
				+ endPoint + "]";
	}
	

}
