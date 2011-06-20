package com.badbears.trainzz.engine.strategies;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.badbears.trainzz.engine.Board;
import com.badbears.trainzz.engine.ConnectionType;
import com.badbears.trainzz.engine.IBoard;
import com.badbears.trainzz.engine.ITrackElement;
import com.badbears.trainzz.engine.ITrain;
import com.badbears.trainzz.engine.StraightElement;
import com.badbears.trainzz.engine.Train;

public class CollisionDetectorStrategyTest {

	IBoard board;
	ICollisionDetectorStrategy collisionDetectorStrategy;
	
	ITrackElement trackElement;
	ITrackElement trackElement2;
	
	@Before
	public void setUp() throws Exception {
		collisionDetectorStrategy = new CollisionDetectorStrategy();
		
		board = new Board();
		trackElement = new StraightElement(0, 0, 100, 0);
		board.addTrackElement(trackElement);
		trackElement2 = new StraightElement(100, 0, 200, 0);
		board.addTrackElement(trackElement2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDetect2Trains2ElementsCollisions() {
		ITrain train = new Train(trackElement, 2500, ConnectionType.START_POINT);
		ITrain train2 = new Train(trackElement, 2500, ConnectionType.START_POINT);
		board.addTrain(train);
		board.addTrain(train2);
		for (int i=0; !train.isDestinationReached(); i=i+1) {
			board.calculateTrainsPositions(i);
			whereAreTrains(board);
			List<ITrain> collidedTrains = collisionDetectorStrategy.detectCollisions(board.getTrains());
			Assert.assertEquals("time elapsed = "+i,2, collidedTrains.size());
			for (ITrain collidedTrain:collidedTrains) {
				board.addTrain(collidedTrain);
			}
		}
	}
	
	@Test
	public void testDetect2Trains2ElementsCollisionsBackwards() {
		ITrain train = new Train(trackElement2, 2500, ConnectionType.END_POINT);
		ITrain train2 = new Train(trackElement2, 2500, ConnectionType.END_POINT);
		board.addTrain(train);
		board.addTrain(train2);
		for (int i=0; !train.isDestinationReached(); i=i+1) {
			board.calculateTrainsPositions(i);
			whereAreTrains(board);
			List<ITrain> collidedTrains = collisionDetectorStrategy.detectCollisions(board.getTrains());
			Assert.assertEquals("time elapsed = "+i,2, collidedTrains.size());
			for (ITrain collidedTrain:collidedTrains) {
				board.addTrain(collidedTrain);
			}
		}
	}
	
	@Test
	public void testDetect2Trains2ElementsCollisionsCounter() {
		ITrain train = new Train(trackElement, 2500, ConnectionType.START_POINT);
		ITrain train2 = new Train(trackElement2, 2500, ConnectionType.END_POINT);
		board.addTrain(train);
		board.addTrain(train2);
		for (int i=0; !train.isDestinationReached(); i=i+1) {
			board.calculateTrainsPositions(1);
			whereAreTrains(board);
			List<ITrain> collidedTrains = collisionDetectorStrategy.detectCollisions(board.getTrains());
			if (!collidedTrains.isEmpty()) {
				System.out.println("Collision!");
			}
//			Assert.assertEquals("time elapsed = "+i,2, collidedTrains.size());
			for (ITrain collidedTrain:collidedTrains) {
				board.addTrain(collidedTrain);
			}
		}
	}
	
	@Test
	public void testDetectCollisionsGame() {
		
		IBoard board = new Board();
		ITrackElement element = new StraightElement(0, 50, 100, 100);
		board.addTrackElement(element);
		ITrackElement element2 = new StraightElement(100, 100, 100, 300);
		board.addTrackElement(element2);
		ITrackElement element3 = new StraightElement(100, 300, 250, 50);
		board.addTrackElement(element3);
		ITrackElement element4 = new StraightElement(250, 50, 150, 150);
		board.addTrackElement(element4);
		ITrackElement element5 = new StraightElement(150, 150, 320, 400);
		board.addTrackElement(element5);
		
		ITrackElement element6 = new StraightElement(0, 480, 100, 380);
		board.addTrackElement(element6);
		ITrackElement element7 = new StraightElement(100, 380, 150, 400);
		board.addTrackElement(element7);
		ITrackElement element8 = new StraightElement(150, 400, 200, 400);
		board.addTrackElement(element8);
		ITrackElement element9 = new StraightElement(200, 400, 300, 400);
		board.addTrackElement(element9);
		ITrackElement element10 = new StraightElement(300, 400, 320, 420);
		board.addTrackElement(element10);	
		
//		ITrain train = new Train(element6, 2500, ConnectionType.START_POINT);
		ITrain train2 = new Train(element10, 2500, ConnectionType.END_POINT);
		board.addTrain();
		board.addTrain();
		board.addTrain();
		board.addTrain();
//		board.addTrain(train);
//		board.addTrain(train2);
		for (int i=0; true; i=i+1) {
			List<ITrain> collidedTrains = collisionDetectorStrategy.detectCollisions(board.getTrains());
			board.calculateTrainsPositions(0.5f);
			whereAreTrains(board);
			if (!collidedTrains.isEmpty()) {
				System.out.println("Collision!");
			}
//			Assert.assertEquals("time elapsed = "+i,2, collidedTrains.size());
			for (ITrain collidedTrain:collidedTrains) {
				board.addTrain(collidedTrain);
			}
		}
	}
	

	
	private void whereAreTrains(IBoard board) {
		for (ITrain train:board.getTrains()) {
			System.out.println(train.calculateCoordinates());
		}
		
	}
}
