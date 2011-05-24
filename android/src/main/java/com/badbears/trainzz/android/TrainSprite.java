package com.badbears.trainzz.android;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.badbears.trainzz.engine.ICoordinates;
import com.badbears.trainzz.engine.ITrain;

public class TrainSprite extends Sprite {

	private ITrain train;
	private int width = 10;
	private int height = 10;
	
	public TrainSprite(ITrain train, TextureRegion textureRegion) {
		super(0, 0, textureRegion);
	  	this.setHeight(height);
	  	this.setWidth(width);
		this.train = train;
	}
	
	  @Override
	  public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
	                  float pTouchAreaLocalX, float pTouchAreaLocalY)
	  {
		  	this.setPosition(pSceneTouchEvent.getX() - this.getWidth()/2, pSceneTouchEvent.getY() - this.getHeight()/2); 
		  	return true;
	  }
	  
	  @Override
	  protected void onManagedUpdate(float pSecondsElapsed) {
	          super.onManagedUpdate(pSecondsElapsed);

	          if (!train.isDestinationReached()) {
	        	  ICoordinates coordinates = train.calculateCoordinates();
	        	  this.setPosition(coordinates.getX()-(width/2), coordinates.getY()-(height/2));
	          } else {
	        	  this.setVisible(false);
	        	  this.clearUpdateHandlers();
	          }
	          
	  }
	  
}
