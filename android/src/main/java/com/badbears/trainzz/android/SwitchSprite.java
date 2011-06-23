package com.badbears.trainzz.android;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.badbears.trainzz.engine.ICoordinates;
import com.badbears.trainzz.engine.ITrackElement;

public class SwitchSprite extends Sprite {

	private ITrackElement trackElement;
	private int width = 10;
	private int height = 10;
	private boolean touched = false;
	
	public SwitchSprite(ITrackElement trackElement, TextureRegion textureRegion) {
		super(0, 0, textureRegion);
	  	this.setHeight(height);
	  	this.setWidth(width);
		this.trackElement = trackElement;
		ICoordinates coordinates = trackElement.getStartPoint();
		this.setColor(0.2f, 0.2f, 0.75f);
		this.setPosition(coordinates.getX()-(width/2), coordinates.getY()-(height/2));
	}
	
	  @Override
	  public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
	                  float pTouchAreaLocalX, float pTouchAreaLocalY)
	  {
		if (pSceneTouchEvent.isActionDown()) {
			if (!touched) {
				this.setColor(0.75f, 0.2f, 0.2f);
			} else {
				this.setColor(0.2f, 0.2f, 0.75f);
			}
			touched = !touched;
		}
		return true;
	  }
	  
	  @Override
	  protected void onManagedUpdate(float pSecondsElapsed) {
	          super.onManagedUpdate(pSecondsElapsed);

	          ICoordinates coordinates = trackElement.getStartPoint();
	          this.setPosition(coordinates.getX()-(width/2), coordinates.getY()-(height/2));

	  }
	  
}
