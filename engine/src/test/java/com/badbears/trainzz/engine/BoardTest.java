package com.badbears.trainzz.engine;

import java.util.Set;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	IBoard board;
	
	@Before
	public void setUp() throws Exception {
		board = new Board();
		ITrackElement element = new StraightElement(1, 1, 10, 10);
		board.addTrackElement(element);
		ITrackElement element2 = new StraightElement(10, 10, 10, 30);
		board.addTrackElement(element2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetEndElements() {
		Set<ITrackElement> elements = board.getElements();
		Assert.assertEquals(elements.size(), 2);
	}
	
	@Test
	public void testAddTrainEnywhere() {
		ITrain train1 = board.addTrain();
		ITrain train2 =	board.addTrain();
		Assert.assertFalse(train1.getCurrentElement().equals(train2.getCurrentElement()));
	}

}
