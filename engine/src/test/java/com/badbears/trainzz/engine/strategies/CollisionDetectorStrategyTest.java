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
	
	@Before
	public void setUp() throws Exception {
		collisionDetectorStrategy = new CollisionDetectorStrategy();
		
		board = new Board();
		trackElement = new StraightElement(1, 1, 10, 10);
		board.addTrackElement(trackElement);
		ITrackElement trackElement2 = new StraightElement(10, 10, 10, 30);
		board.addTrackElement(trackElement2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDetectCollisions() {
		ITrain train = new Train(trackElement, 0, ConnectionType.START_POINT);
		ITrain train2 = new Train(trackElement, 0, ConnectionType.START_POINT);
		board.addTrain(train);
		board.addTrain(train2);
		board.calculateTrainsPositions(0);
		List<ITrain> collidedTrains = collisionDetectorStrategy.detectCollisions(board.getTrains());
		Assert.assertEquals(2, collidedTrains.size());
	}



}
