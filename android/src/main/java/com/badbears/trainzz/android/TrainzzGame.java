package com.badbears.trainzz.android;
import java.util.List;
import java.util.Set;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.primitive.Line;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;

import com.badbears.trainzz.engine.Board;
import com.badbears.trainzz.engine.IBoard;
import com.badbears.trainzz.engine.ITrackElement;
import com.badbears.trainzz.engine.ITrain;
import com.badbears.trainzz.engine.StraightElement;
import com.badbears.trainzz.engine.Switch;


public class TrainzzGame extends BaseExample {

        private static final int CAMERA_WIDTH = 320;
        private static final int CAMERA_HEIGHT = 480;
      
        private Camera mCamera;
        private Texture mGreenTexture;
        private Texture mBlueTexture;
        private TextureRegion myGreenTextureRegion;
        private TextureRegion myBlueTextureRegion;

        @Override
        public Engine onLoadEngine() {
                this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
                return new Engine(new EngineOptions(true, ScreenOrientation.PORTRAIT, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera));
        }

        @Override
        public void onLoadResources() {
                this.mGreenTexture = new Texture(512, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                this.mBlueTexture = new Texture(512, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
                TextureRegionFactory.setAssetBasePath("gfx/");
                this.myGreenTextureRegion = TextureRegionFactory.createFromAsset(this.mGreenTexture, this, "greenstar.png", 0, 0);
                this.myBlueTextureRegion = TextureRegionFactory.createFromAsset(this.mBlueTexture, this, "bluestar.png", 0, 0);
                this.mEngine.getTextureManager().loadTexture(this.mGreenTexture);
                this.mEngine.getTextureManager().loadTexture(this.mBlueTexture);
        }

        @Override
        public Scene onLoadScene() {
                this.mEngine.registerUpdateHandler(new FPSLogger());

                final Scene scene = new Scene(1);
                scene.setBackground(new ColorBackground(0.09804f, 0.6274f, 0.8784f));

                final IBoard board = createBoard();
                
                drawTrackElements(scene, board.getElements());
                drawTrains(scene, board.getTrains());
	            registerUpdateHandlers(scene, board);

                return scene;
        }

		private void registerUpdateHandlers(final Scene scene,
			final IBoard board) {
				scene.registerUpdateHandler(new IUpdateHandler() {
				 
			    @Override
			    public void onUpdate(float pSecondsElapsed) {
			            ITrain train = board.onUpdate(pSecondsElapsed);
			        	if (train != null) {
			        		TrainSprite trainSprite = new TrainSprite(train, myGreenTextureRegion);
			      	  		scene.getLastChild().attachChild(trainSprite);
			        	}
			    }

				@Override
				public void reset() { 

				}
				
			});
		}

		private void drawTrains(final Scene scene, final List<ITrain> trains) {
			for (ITrain train:trains) {
				  TrainSprite trainSprite = new TrainSprite(train, this.myGreenTextureRegion);
				  scene.getLastChild().attachChild(trainSprite);
				  scene.registerTouchArea(trainSprite);
			}
		}

		private void drawTrackElements(final Scene scene, final Set<ITrackElement> trackElements) {
			for (ITrackElement element:trackElements) {
				final Line line = new Line(
						element.getStartPoint().getX(), 
						element.getStartPoint().getY(), 
						element.getEndPoint().getX(), 
						element.getEndPoint().getY(), 5);
				
			    line.setColor(1.0f, 1.0f, 1.0f);
			    scene.getLastChild().attachChild(line);
			}
			
			for (ITrackElement element:trackElements) {
				// TODO_MSL refactor!
				if (element instanceof Switch) {
					SwitchSprite switchSprite = new SwitchSprite(element, this.myBlueTextureRegion);
					scene.getLastChild().attachChild(switchSprite);
					scene.registerTouchArea(switchSprite);
					scene.registerUpdateHandler(switchSprite);
				}
			}
		}

		private IBoard createBoard() {
			IBoard board = new Board();
			
			// x 320, y 480
			ITrackElement e1 = new StraightElement(80, 0, 160, 100);
			board.addTrackElement(e1);
			
			ITrackElement e1a = new StraightElement(240, 0, 160, 100);
			board.addTrackElement(e1a);
			
			ITrackElement e2 = new StraightElement(160, 100, 80, 200);
			board.addTrackElement(e2);
			ITrackElement e3 = new StraightElement(160, 100, 240, 200);
			board.addTrackElement(e3);
			ITrackElement e4 = new StraightElement(80, 200, 40, 300);
			board.addTrackElement(e4);
			ITrackElement e5 = new StraightElement(240, 200, 280, 300);
			board.addTrackElement(e5);
			ITrackElement e6 = new StraightElement(80, 200, 120, 300);
			board.addTrackElement(e6);
			
			ITrackElement s1 = new Switch(80, 200);
			board.addTrackElement(s1);
			
			ITrackElement e7 = new StraightElement(240, 200, 200, 300);
			board.addTrackElement(e7);
			ITrackElement e8 = new StraightElement(120, 300, 160, 400);
			board.addTrackElement(e8);
			ITrackElement e9 = new StraightElement(200, 300, 200, 400);
			board.addTrackElement(e9);
			ITrackElement e10 = new StraightElement(40, 300, 200, 400);
			board.addTrackElement(e10);
			ITrackElement e11 = new StraightElement(280, 300, 200, 400);
			board.addTrackElement(e11);
			
			
//			ITrackElement element = new StraightElement(0, 50, 100, 100);
//			board.addTrackElement(element);
//			ITrackElement element2 = new StraightElement(100, 100, 100, 300);
//			board.addTrackElement(element2);
//			ITrackElement element3 = new StraightElement(100, 300, 250, 50);
//			board.addTrackElement(element3);
//			ITrackElement element4 = new StraightElement(250, 50, 150, 150);
//			board.addTrackElement(element4);
//			
//			ITrackElement element4a = new StraightElement(150, 150, 250, 75);
//			board.addTrackElement(element4a);
//			
//			ITrackElement element5 = new StraightElement(150, 150, 320, 400);
//			board.addTrackElement(element5);
//			
//			ITrackElement element5a = new StraightElement(250, 75, 320, 50);
//			board.addTrackElement(element5a);
//			
//			ITrackElement element6 = new StraightElement(0, 480, 100, 380);
//			board.addTrackElement(element6);
//			ITrackElement element7 = new StraightElement(100, 380, 150, 400);
//			board.addTrackElement(element7);
//			ITrackElement element8 = new StraightElement(150, 400, 200, 400);
//			board.addTrackElement(element8);
//			ITrackElement element9 = new StraightElement(200, 400, 300, 400);
//			board.addTrackElement(element9);
//			ITrackElement element10 = new StraightElement(300, 400, 320, 420);
//			board.addTrackElement(element10);
						
			return board;
		}

        @Override
        public void onLoadComplete() {

        }
        
}