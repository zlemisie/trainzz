package com.badbears.trainzz.engine;

import static org.junit.Assert.fail;

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
	public void testAddTrain() {
		Set<ITrackElement> elements = board.getEndElements();
		Assert.assertEquals(elements.size(), 2);
		for (ITrackElement element:elements) {
			//ITrain train = new Train(element);
			board.addTrain(element);
		}

	}

	@Test
	public void testGetEndElements() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNextElements() {
		fail("Not yet implemented");
	}

}
