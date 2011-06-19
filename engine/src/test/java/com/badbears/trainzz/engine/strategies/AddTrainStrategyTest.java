package com.badbears.trainzz.engine.strategies;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.badbears.trainzz.engine.Board;
import com.badbears.trainzz.engine.Coordinates;
import com.badbears.trainzz.engine.IBoard;
import com.badbears.trainzz.engine.ICoordinates;
import com.badbears.trainzz.engine.ITrackElement;
import com.badbears.trainzz.engine.ITrain;
import com.badbears.trainzz.engine.StraightElement;

public class AddTrainStrategyTest {

	IBoard board;
	IAddTrainStrategy addTrainStrategy;
	ICoordinates startPoint;
	ICoordinates endPoint;
	
	@Before
	public void setUp() throws Exception {
		addTrainStrategy = new AddTrainStrategy();
		board = new Board();
		
		startPoint = new Coordinates(1,1);
		endPoint = new Coordinates (10,30);
		ITrackElement element = new StraightElement(startPoint.getX(), startPoint.getY(), 10, 10);
		board.addTrackElement(element);
		ITrackElement element2 = new StraightElement(10, 10, endPoint.getX(), endPoint.getY());
		board.addTrackElement(element2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddTrain() {
		ITrain train = addTrainStrategy.addTrain(board.getElements(), board.getTrains());
		board.getTrains().add(train);
		ITrackElement trackElement = train.getCurrentElement();
		ITrain train2 = addTrainStrategy.addTrain(board.getElements(), board.getTrains());
		board.getTrains().add(train2);
		ITrackElement trackElement2 = train2.getCurrentElement();
		Assert.assertNotSame(trackElement, trackElement2);
		train.calculatePostion(0);
		train2.calculatePostion(0);
		// eliminates random track elements selection
		if (trackElement.getStartPoint().equals(startPoint)) {
			Assert.assertEquals(startPoint, train.calculateCoordinates());
			Assert.assertEquals(endPoint, train2.calculateCoordinates());
		} else {
			Assert.assertEquals(startPoint, train2.calculateCoordinates());
			Assert.assertEquals(endPoint, train.calculateCoordinates());
		}
		

	}



}
