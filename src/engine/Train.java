package engine;

import java.util.Random;

public class Train {
	
	int speed;
	
	public Train(){
	setRandomSpeed();
	
	}

	private void setRandomSpeed(){
		Random r = new Random();
		speed = r.nextInt(10) + 11;
	}
}
