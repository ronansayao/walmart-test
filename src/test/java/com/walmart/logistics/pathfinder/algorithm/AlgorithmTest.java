/**
 * 
 */
package com.walmart.logistics.pathfinder.algorithm;

import java.util.Iterator;
import java.util.List;

import junit.framework.Assert;

import org.graphstream.algorithm.AStar;
import org.graphstream.algorithm.Dijkstra;
import org.graphstream.algorithm.Dijkstra.Element;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Path;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.walmart.logistics.pathfinder.common.CampinasRegionMap;
import com.walmart.logistics.pathfinder.common.Constants;
import com.walmart.logistics.pathfinder.model.Movement;
import com.walmart.logistics.pathfinder.model.Point;

/**
 * @author ronan.sayao
 *
 */


public class AlgorithmTest {
	
	private List<Point> points;
	private List<Movement> movements;
	
	private static final Logger logger = LoggerFactory.getLogger(AlgorithmTest.class);
		
	/**
	 * Test to get the best path between two locales using A Star algorithm.
	 * @param origin
	 * @param destination
	 * @return
	 */
	
	public int testAStarAlgorithm (String origin, String destination) {
		
		org.graphstream.graph.Graph graph = new SingleGraph("example");
		
		CampinasRegionMap map = new CampinasRegionMap();
		points = map.getPoints();
		movements = map.getMovements();
		
		for (Point point : points) {
			graph.addNode(point.getName());
		}
		graph.setAutoCreate(true);
		for (Movement movement : movements) { 
			graph.addEdge(movement.getSource()+movement.getDestination(), movement.getSource(), movement.getDestination()).setAttribute(Constants.FIELD_WEIGHT, movement.getDistance());
		}
		
		Long startMilisAStar = System.currentTimeMillis();
		logger.info("testAStarAlgorithm() - Method has started: "+startMilisAStar);
		
		int distanceAstar = 0;
		AStar astar = new AStar(graph);
		astar.compute(origin, destination);
		
		if (!astar.noPathFound()) {
			Path pathAstar = astar.getShortestPath();
	
			Iterator<? extends Edge> iterator = (Iterator<? extends Edge>) pathAstar.getEachEdge().iterator();
			while (iterator.hasNext()) {
				
				Edge edgeElement = (Edge) iterator.next();
				distanceAstar = distanceAstar + new Integer((int) edgeElement.getAttribute(Constants.FIELD_WEIGHT));
				
			}
			logger.info("Path AStar: "+pathAstar.toString());
		}
		logger.info("Distance AStar: "+distanceAstar);
		logger.info("testAStarAlgorithm() - Method has spent AStar: "+(System.currentTimeMillis()-startMilisAStar)+" ms");
        
        return distanceAstar;
		
	}
	
	/**
	 * Test to verify the best path using 2 algorithms.
	 */
	
	@Test
	public void verifyShortestDistance() {
		
		String origin = new String("Limeira");
		String destination = new String("Campinas");
		
		int distanceGraphStreamDijkstra = testDijkstraGraphStreamAlgorithm(origin, destination);
		int distanceAStar = testAStarAlgorithm(origin, destination);
			
		Assert.assertEquals(distanceAStar, distanceGraphStreamDijkstra);
		
	}
	
	/**
	 * Test to get the best path between two locales using Dijkstra algorithm.
	 * @param origin
	 * @param destination
	 * @return
	 */
	public int testDijkstraGraphStreamAlgorithm(String origin, String destination) {
	
		org.graphstream.graph.Graph graph = new SingleGraph("example");
		
		CampinasRegionMap map = new CampinasRegionMap();
		points = map.getPoints();
		movements = map.getMovements();
		
		for (Point point : points) {
			graph.addNode(point.getName());
		}
		graph.setAutoCreate(true);
		for (Movement movement : movements) { 
			graph.addEdge(movement.getSource()+movement.getDestination(), movement.getSource(), movement.getDestination()).setAttribute(Constants.FIELD_WEIGHT, movement.getDistance());
		}
		
		Long startMilisAStar = System.currentTimeMillis();
		logger.info("testAStarAlgorithm() GS - Method has started: "+startMilisAStar);
	
		Dijkstra dijkstra = new Dijkstra(Element.EDGE, "", Constants.FIELD_WEIGHT);
		dijkstra.setSource(graph.getNode(origin));
        dijkstra.init(graph);
        dijkstra.compute();

        logger.info(dijkstra.getPath(graph.getNode(destination)).toString());
	    	    
	    logger.info("Path Dijkstra GS: "+dijkstra.getPath(graph.getNode(destination)));
	    int distance = (int) dijkstra.getPathLength(graph.getNode(destination));
		logger.info("Distance Dijkstra GS: "+distance);
		logger.info("testDijkstraAlgorithm() GS - Method has spent: "+(System.currentTimeMillis()-startMilisAStar)+" ms");
	    
	    return distance;
	}
	

}
