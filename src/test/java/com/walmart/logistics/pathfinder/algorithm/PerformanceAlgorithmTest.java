/**
 * 
 */
package com.walmart.logistics.pathfinder.algorithm;

import java.util.Iterator;

import org.graphstream.algorithm.AStar;
import org.graphstream.algorithm.Dijkstra;
import org.graphstream.algorithm.Dijkstra.Element;
import org.graphstream.algorithm.generator.BarabasiAlbertGenerator;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Path;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.walmart.logistics.pathfinder.common.Constants;


/**
 * @author ronan.sayao
 *
 */

public class PerformanceAlgorithmTest {
	
	private static final Logger logger = LoggerFactory.getLogger(PerformanceAlgorithmTest.class);
	
	
	
	
	Graph graph;
	
	@Before
	public void init() {
		generateGraph();	
	}
	
	/**
	 * 
	 * @return
	 */
	private void generateGraph() {
		
		graph = new SingleGraph("Barabàsi-Albert");
		// Between 1 and 3 new links per node added.
		Generator gen = new BarabasiAlbertGenerator(3);
		// Generate 100 nodes:
		
		gen.addSink(graph);
		gen.begin();
		for(int i=1; i< 40000; i++) {
		    gen.nextEvents();
		}
		gen.end();
		
		for (Iterator<Edge> iterator = graph.getEdgeIterator(); iterator.hasNext();) {
			Edge edge = (Edge) iterator.next();
			edge.addAttribute(Constants.FIELD_WEIGHT, 5);
		}
				
	}
	
	/**
	 * 
	 */
	public void executeAstar() {
					
		Long startMilisAStar = System.currentTimeMillis();
		logger.info("testAStarAlgorithm() - Method has started: "+startMilisAStar);
		
		int distanceAstar = 0;
		AStar astar = new AStar(graph);
		astar.compute(Constants.ORIGIN, Constants.DESTINATION);
		
		if (!astar.noPathFound()) {
			Path pathAstar = astar.getShortestPath();
	
			Iterator<? extends Edge> iterator = (Iterator<? extends Edge>) pathAstar.getEachEdge().iterator();
			while (iterator.hasNext()) {
				
				Edge edgeElement = (Edge) iterator.next();
				distanceAstar = distanceAstar + new Integer((int) edgeElement.getAttribute(Constants.FIELD_WEIGHT));
				
			}
			
			logger.info("Path AStar: "+pathAstar.toString());
		} else {
			
			logger.info("No path found.");
		}
		logger.info("Distance AStar: "+distanceAstar);
		logger.info("testAStarAlgorithm() - Method has spent AStar: "+(System.currentTimeMillis()-startMilisAStar)+" ms");
	}
	
	/**
	 * 
	 */
	public void executeDijkstra() {
				
		Long startMilisAStar = System.currentTimeMillis();
		logger.info("testDijkstraAlgorithm() - Method has started: "+startMilisAStar);
	
		Dijkstra dijkstra = new Dijkstra(Element.EDGE, "", Constants.FIELD_WEIGHT);
		dijkstra.setSource(graph.getNode(Constants.ORIGIN));
        dijkstra.init(graph);
        dijkstra.compute();
        
        if (dijkstra.getPath(graph.getNode(Constants.DESTINATION)).empty()) {
			logger.info("No path found.");
		}
	    	    
	    logger.info("Path Dijkstra: "+dijkstra.getPath(graph.getNode(Constants.DESTINATION)));
	    int distance = (int) dijkstra.getPathLength(graph.getNode(Constants.DESTINATION));
		logger.info("Distance Dijkstra: "+distance);
		logger.info("testDijkstraAlgorithm() - Method has spent: "+(System.currentTimeMillis()-startMilisAStar)+" ms");
		
	}
	
	/**
	 * Unit test to check the performance of the algorithm.
	 */
	@Test
	public void testPerformance() {
		
		this.executeDijkstra();
		this.executeAstar();
	}
	
	

}
