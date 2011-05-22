package com.badbears.trainzz.android;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.badbears.trainzz.engine.ICoordinates;
import com.badbears.trainzz.engine.ITrain;

public class TrainSprite extends Sprite {

	private ITrain train;
	
	public TrainSprite(ITrain train, TextureRegion textureRegion) {
//		super(train.calculateCoordinates().getX(), train.calculateCoordinates().getY(), 1, 1, textureRegion);
		super(100, 100, textureRegion);
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
	        	  this.setPosition(coordinates.getX(), coordinates.getY());
	          } else {
	        	  this.setVisible(false);
	        	  this.clearUpdateHandlers();
	          }
	          
	  }
	  
}
