package com.badbears.trainzz.engine;

public class StraightElement extends TrackElement {

	public StraightElement(int x1, int y1, int x2, int y2) {
		super(x1, y1, x2, y2);
	}
	
	@Override
	public double getLength() {
		return this.getStartPoint().getDistance(this.getEndPoint());
	}

	@Override
	public ICoordinates calculateCoordinates(double progress) {
		return new Coordinates((int)(this.getStartPoint().getX()+(this.getEndPoint().getX()-this.getStartPoint().getX())*progress/100),
				(int)(this.getStartPoint().getY()+(this.getEndPoint().getY()-this.getStartPoint().getY())*progress/100));
	
	}
}
