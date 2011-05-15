package com.badbears.trainzz.engine;

import java.util.Random;

public class Train implements ITrain {

	private int speed;
	private ITrackElement currentElement;
	private double currentElementProgress;

	public Train(ITrackElement currentElement) {
		this.currentElement = currentElement;
		currentElementProgress = 0;
		setRandomSpeed();
	}

	private void setRandomSpeed() {
		Random r = new Random();
		speed = r.nextInt(10) + 11;
	}

	@Override
	public ITrackElement getCurrentElement() {
		return currentElement;
	}

	@Override
	public double getCurrentElementProgress() {
		return currentElementProgress;
	}
}
