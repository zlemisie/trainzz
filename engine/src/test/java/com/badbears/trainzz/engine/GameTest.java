package com.badbears.trainzz.engine;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameTest {

	IBoard board;
	ITrain train;
	
	@Before
	public void setUp() throws Exception {
		board = new Board();
		ITrackElement element = new StraightElement(1, 1, 10, 10);
		board.addTrackElement(element);
		ITrackElement element2 = new StraightElement(10, 10, 10, 30);
		board.addTrackElement(element2);
		
		train = new Train(element, 1000, ConnectionType.START_POINT);
		board.addTrain(train );
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void mainLoopTest() throws Exception {
		for (int i=1;i<100;i++) {
			long startTime = System.currentTimeMillis();
			Thread.sleep(5);
			long endTime =System.currentTimeMillis();
			int dTime = (int)(endTime - startTime);
			board.calculateTrainsPositions(dTime);
//			System.out.println(train.getCurrentElementProgress());
		}
	}

}
