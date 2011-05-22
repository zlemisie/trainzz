package com.badbears.trainzz.android;
import java.util.Random;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.primitive.Line;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import com.badbears.trainzz.engine.Board;
import com.badbears.trainzz.engine.ConnectionType;
import com.badbears.trainzz.engine.IBoard;
import com.badbears.trainzz.engine.ITrackElement;
import com.badbears.trainzz.engine.ITrain;
import com.badbears.trainzz.engine.StraightElement;
import com.badbears.trainzz.engine.Train;


public class HelloWorld extends BaseExample {

        private static final int CAMERA_WIDTH = 320;
        private static final int CAMERA_HEIGHT = 480;
	
//		private static final int CAMERA_WIDTH = 48;
//		private static final i nt CAMERA_HEIGHT = 32;
        
        private static final long RANDOM_SEED = 1234567890;



        private static final int LINE_COUNT = 100;

        private Camera mCamera;

        private Texture mTexture;

        private TiledTextureRegion mSnapdragonTextureRegion;
        private TiledTextureRegion mHelicopterTextureRegion;
        private TiledTextureRegion mBananaTextureRegion;
        private TiledTextureRegion mFaceTextureRegion;
        
        private TextureRegion myTextureRegion;

        @Override
        public Engine onLoadEngine() {
                this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
                return new Engine(new EngineOptions(true, ScreenOrientation.PORTRAIT, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera));
        }

        @Override
        public void onLoadResources() {
                this.mTexture = new Texture(512, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

                TextureRegionFactory.setAssetBasePath("gfx/");
                this.mSnapdragonTextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTexture, this, "greenstar.png", 0, 0, 4, 3);
                this.mHelicopterTextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTexture, this, "greenstar.png", 400, 0, 2, 2);
                this.mBananaTextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTexture, this, "greenstar.png", 0, 180, 4, 2);
                this.mFaceTextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTexture, this, "greenstar.png", 132, 180, 2, 1);

                this.myTextureRegion = TextureRegionFactory.createFromAsset(this.mTexture, this, "greenstar.png", 0, 0);
                
                this.mEngine.getTextureManager().loadTexture(this.mTexture);
        }

        @Override
        public Scene onLoadScene() {
                this.mEngine.registerUpdateHandler(new FPSLogger());

                final Scene scene = new Scene(1);
                scene.setBackground(new ColorBackground(0.09804f, 0.6274f, 0.8784f));

//                /* Quickly twinkling face. */
//                final AnimatedSprite face = new AnimatedSprite(100, 50, this.mFaceTextureRegion);
//                face.animate(100);
//                scene.getLastChild().attachChild(face);
//
//                /* Continuously flying helicopter. */
//                final AnimatedSprite helicopter = new AnimatedSprite(320, 50, this.mHelicopterTextureRegion);
//                helicopter.animate(new long[] { 100, 100 }, 1, 2, true);
//                scene.getLastChild().attachChild(helicopter);
//
//                /* Snapdragon. */
//                final AnimatedSprite snapdragon = new AnimatedSprite(300, 200, this.mSnapdragonTextureRegion);
//                snapdragon.animate(100);
//                scene.getLastChild().attachChild(snapdragon);
//
//                /* Funny banana. */
//                final AnimatedSprite banana = new AnimatedSprite(100, 220, this.mBananaTextureRegion);
//                banana.animate(100);
//                scene.getLastChild().attachChild(banana);
        		
                IBoard board = createBoard();
                
                final Random random = new Random(RANDOM_SEED);

                for (ITrackElement element:board.getElements()) {

                	final Line line = new Line(
                			element.getStartPoint().getX(), 
                			element.getStartPoint().getY(), 
                			element.getEndPoint().getX(), 
                			element.getEndPoint().getY(), 5);
                	
                    line.setColor(random.nextFloat(), random.nextFloat(), random.nextFloat());
                    scene.getLastChild().attachChild(line);
                }
                

	              
	            final Sprite sprite = new Sprite(100, 100, this.myTextureRegion) {
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
	                          
	                          this.setPosition(this.getX()+1, this.getY()+1); 
	                          
	                  }
	            };
	            
	            scene.getLastChild().attachChild(sprite);
	            scene.registerTouchArea(sprite);
	            scene.registerUpdateHandler(new IUpdateHandler() {
	            	 
	                @Override
	                public void onUpdate(float pSecondsElapsed) {
	                        // TODO Auto-generated method stub
	                        
	                       
	                }

					@Override
					public void reset() { 
						// TODO Auto-generated method stub
						
					}
	            });

                return scene;
        }

		private IBoard createBoard() {
			IBoard board = new Board();
			ITrackElement element = new StraightElement(1, 1, 10, 10);
			board.addTrackElement(element);
			ITrackElement element2 = new StraightElement(10, 10, 10, 30);
			board.addTrackElement(element2);
			
			ITrain train = new Train(element, 1000, ConnectionType.START_POINT);
			board.addTrain(train );
			return board;
		}

        @Override
        public void onLoadComplete() {

        }
        
}