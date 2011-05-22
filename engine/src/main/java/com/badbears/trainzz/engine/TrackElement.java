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
		boolean result = false;
		if (anotherElement.getStartPoint().equals(this.getStartPoint())
				|| anotherElement.getStartPoint().equals(this.getEndPoint())
				|| anotherElement.getEndPoint().equals(this.getStartPoint())
				|| anotherElement.getEndPoint().equals(this.getEndPoint())) {
					result = true;
				}
		return result;
	}
	
	@Override
	public abstract ICoordinates calculateCoordinates(double progress);
	

}
