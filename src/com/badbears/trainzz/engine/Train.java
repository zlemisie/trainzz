package com.badbears.trainzz.engine;

import java.util.Random;

public class Train implements ITrain {
	
	int speed;
	
	public Train(){
	setRandomSpeed();
	
	}

	private void setRandomSpeed(){
		Random r = new Random();
		speed = r.nextInt(10) + 11;
	}
}
