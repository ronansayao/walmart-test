/**
 * 
 */
package com.walmart.logistics.pathfinder.algorithm;

import java.util.ArrayList;
import java.util.Iterator;

import org.graphstream.algorithm.AStar;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.walmart.logistics.pathfinder.common.Constants;
import com.walmart.logistics.pathfinder.exception.PathNotFoundException;
import com.walmart.logistics.pathfinder.strategy.AlgorithmStrategy;
import com.walmart.logistics.pathfinder.vo.PathEntriesVO;
import com.walmart.logistics.pathfinder.vo.RouteVO;

/**
 * @author ronan.sayao
 *
 */
public class AStarAlgorithmImpl implements AlgorithmStrategy {
	
	private static final Logger logger = LoggerFactory.getLogger(AStarAlgorithmImpl.class);

	/* (non-Javadoc)
	 * @see com.walmart.logistics.pathfinder.strategy.AlgorithmStrategy#pathFinder(org.graphstream.graph.Graph, com.walmart.logistics.pathfinder.vo.PathEntriesVO)
	 */
	@Override
	public RouteVO pathFinder(Graph graph, PathEntriesVO pathEntriesVO) throws PathNotFoundException {
		
		Long startMilisAStar = System.currentTimeMillis();
		logger.info("pathFinder() - Method has started: "+startMilisAStar);
		
		RouteVO routeVO = new RouteVO();
		
		int distanceAstar = 0;
		AStar astar = new AStar(graph);
		astar.compute(pathEntriesVO.getOrigin(), pathEntriesVO.getDestination());
		
		if (!astar.noPathFound()) {
			Path pathAstar = astar.getShortestPath();
	
			Iterator<? extends Edge> iterator = (Iterator<? extends Edge>) pathAstar.getEachEdge().iterator();
			while (iterator.hasNext()) {
				
				Edge edgeElement = (Edge) iterator.next();
				distanceAstar = distanceAstar + new Integer((int) edgeElement.getAttribute(Constants.FIELD_WEIGHT));
				
			}
			routeVO.setPath(new ArrayList<String>());
	        
	        for (Node node : pathAstar.getNodePath()) {
	        	String nodeName = node.getId();
	        	routeVO.getPath().add(nodeName);
	        }
			logger.info("Path AStar: "+pathAstar.toString());
		} else {
			throw new PathNotFoundException(pathEntriesVO.getOrigin(), pathEntriesVO.getDestination());
		}
		routeVO.setTotalCost((distanceAstar/pathEntriesVO.getAutonomy())*pathEntriesVO.getGasprice());
				
		logger.info("Distance AStar: "+distanceAstar);
		logger.info("pathFinder() - Method has spent AStar: "+(System.currentTimeMillis()-startMilisAStar)+" ms");
		
		return routeVO;
	}

}
