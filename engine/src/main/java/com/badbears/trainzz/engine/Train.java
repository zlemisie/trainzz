package com.badbears.trainzz.engine;

import java.util.Random;

public class Train implements ITrain {

	private int speed;
	private ITrackElement lastElement;
	private ITrackElement currentElement;
	private double currentElementProgress;
	private ConnectionType currentElementFromDirection;
	private boolean destinationReached;

	public Train(ITrackElement currentElement, int speed, ConnectionType startPoint) {
		this.currentElement = currentElement;
		this.currentElementProgress = 0;
		this.speed = speed;
		this.currentElementFromDirection = startPoint;
//		setRandomSpeed();
	}

	private void setRandomSpeed() {
		Random r = new Random();
		speed = r.nextInt(10) + 1;
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
	public void calculatePostion(float milis) {
		double currentProgress = speed * milis / currentElement.getLength();
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
}
