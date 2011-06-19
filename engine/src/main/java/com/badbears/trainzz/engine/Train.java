package com.badbears.trainzz.engine;

import java.util.Random;

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

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result
//				+ ((currentElement == null) ? 0 : currentElement.hashCode());
//		result = prime
//				* result
//				+ ((currentElementFromDirection == null) ? 0
//						: currentElementFromDirection.hashCode());
//		long temp;
//		temp = Double.doubleToLongBits(currentElementProgress);
//		result = prime * result + (int) (temp ^ (temp >>> 32));
//		result = prime * result + (destinationReached ? 1231 : 1237);
//		result = prime * result
//				+ ((lastElement == null) ? 0 : lastElement.hashCode());
//		result = prime * result + speed;
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Train other = (Train) obj;
//		if (currentElement == null) {
//			if (other.currentElement != null)
//				return false;
//		} else if (!currentElement.equals(other.currentElement))
//			return false;
//		if (currentElementFromDirection != other.currentElementFromDirection)
//			return false;
//		if (Double.doubleToLongBits(currentElementProgress) != Double
//				.doubleToLongBits(other.currentElementProgress))
//			return false;
//		if (destinationReached != other.destinationReached)
//			return false;
//		if (lastElement == null) {
//			if (other.lastElement != null)
//				return false;
//		} else if (!lastElement.equals(other.lastElement))
//			return false;
//		if (speed != other.speed)
//			return false;
//		return true;
//	}

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
