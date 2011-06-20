package com.badbears.trainzz.engine;


public class Train implements ITrain {

	private int speed;
	private ITrackElement lastElement;
	private ITrackElement currentElement;
	private double currentElementProgress;
	private ConnectionType currentElementFromDirection;
	private boolean destinationReached;
	private boolean collided;

	public Train(ITrackElement currentElement, int speed, ConnectionType startPoint) {
		this.currentElement = currentElement;
		this.currentElementProgress = 0;
		this.speed = speed;
		this.currentElementFromDirection = startPoint;
	}

	@Override
	public ITrackElement getCurrentElement() {
		return currentElement;
	}

	@Override
	public double getCurrentElementProgress() {
		return currentElementProgress;
	}

	@Override
	public void calculatePostion(float pSecondsElapsed) {
		double currentProgress = speed * pSecondsElapsed / currentElement.getLength();
		currentElementProgress = currentElementProgress + currentProgress ;
	}
	
	@Override
	public void goToNextElement(ITrackElement nextElement, double nextElementProgress) {
		this.lastElement = currentElement;
		this.currentElement = nextElement;
		this.currentElementProgress = nextElementProgress;
	}

	@Override
	public ICoordinates calculateCoordinates() {
		ICoordinates coordinates = null;
		ITrackElement trackElement = this.getCurrentElement();
		
		double progress = this.getCurrentElementProgress();
		updateCurrentElementFromDirection();
		if (currentElementFromDirection == ConnectionType.END_POINT) {
			coordinates = trackElement.calculateCoordinates(100 - progress);
		} else if (currentElementFromDirection ==  ConnectionType.START_POINT) {
			coordinates = trackElement.calculateCoordinates(progress);
		} 
		return coordinates;
	}
	
	private void updateCurrentElementFromDirection() {
		ConnectionType newConnectionType = this.currentElement.getConnectionType(this.lastElement);
		if (newConnectionType != null) {
			this.currentElementFromDirection = newConnectionType;
		}
	}

	@Override
	public ITrackElement getLastElement() {
		return lastElement;
	}

	@Override
	public void reachDestination() {
		this.destinationReached = true;
		this.currentElement = null;
		this.currentElementProgress = 0;
	}
	
	@Override
	public boolean isDestinationReached() {
		return destinationReached;
	}

	@Override
	public boolean isCollided() {
		return collided;
	}

	@Override
	public void collide() {
		this.collided = true;
	}

	@Override
	public String toString() {
		return "Train [currentElement=" + currentElement
				+ ", currentElementProgress=" + currentElementProgress
				+ ", currentElementFromDirection="
				+ currentElementFromDirection + "]";
	}
}
