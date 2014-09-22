/**
 * 
 */
package com.walmart.logistics.pathfinder.algorithm;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.walmart.logistics.pathfinder.common.CampinasRegionMap;
import com.walmart.logistics.pathfinder.model.Graph;
import com.walmart.logistics.pathfinder.model.Movement;
import com.walmart.logistics.pathfinder.model.Point;

/**
 * @author ronan.sayao
 *
 */
public class DijkstraAlgorithmTest {
	
	private List<Point> points;
	private List<Movement> movements;
	
	private static final Logger logger = LoggerFactory.getLogger(DijkstraAlgorithmTest.class);	
	
	/**
	 * 
	 */

	@Test
	public void testExcute() {
		
		CampinasRegionMap map = new CampinasRegionMap();
		points = map.getPoints();
		movements = map.getMovements();
		
		
		Long startMilis = System.currentTimeMillis();
		logger.debug("Method has started: "+startMilis);
		
		// Lets check from location Loc_1 to Loc_10
		Graph graph = new Graph(points, movements);
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
		dijkstra.execute(points.get(0).toString());
		LinkedList<String> path = dijkstra.getPath(points.get(11).toString());
		int distance = dijkstra.getShortestDistance(points.get(11).toString());
		assertNotNull(path);
		assertTrue(path.size() > 0);
		
		for (String track : path) {
		  System.out.println(track);
		}
		System.out.println("Distance: "+distance);
		logger.debug("Method has spent: "+(System.currentTimeMillis()-startMilis)+" ms");
	}

}
